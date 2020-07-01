package io.github.paulooorg.api.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.Logger;

import io.github.paulooorg.api.infrastructure.request.RequestOptions;
import io.github.paulooorg.api.infrastructure.request.filtering.Filtering;
import io.github.paulooorg.api.infrastructure.request.pagination.Page;
import io.github.paulooorg.api.infrastructure.request.pagination.Pagination;
import io.github.paulooorg.api.infrastructure.request.sorting.Sorting;
import io.github.paulooorg.api.model.entities.BaseEntity;

public abstract class AbstractEntityRepository<E extends BaseEntity<PK>, PK extends Serializable> implements EntityRepository<E, PK> {
    @Inject
    private EntityManager em;

    @Inject
    private Logger logger;
    
    private final Class<E> entityType;

    public AbstractEntityRepository(Class<E> entityType) {
        this.entityType = entityType;
    }

    @Override
    public Optional<E> save(E entity) {
        if (entity.getId() == null) {
            em.persist(entity);
        } else {
            em.merge(entity);
        }
        em.flush();
        return Optional.of(entity);
    }

    @Override
    public void remove(E entity) {
        em.remove(entity);
        em.flush();
    }

    @Override
    public Optional<E> findBy(PK primaryKey) {
        return Optional.of(em.find(entityType, primaryKey));
    }

    @Override
    public List<E> findAll() {
        return em.createQuery("SELECT e FROM " + entityType.getSimpleName() + " e ").getResultList();
    }
    
    @Override
    public Page<E> findAll(RequestOptions requestOptions) {
    	CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    	
    	// Options
    	Pagination pagination = requestOptions.getPagination();
    	List<Sorting> sorting = requestOptions.getSorting();
    	List<Filtering> filtering = requestOptions.getFiltering();
    	
    	// Count
    	CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
    	Root<E> fromCount = countQuery.from(entityType);
    	countQuery.select(criteriaBuilder.count(fromCount)).where(createFilters(filtering, criteriaBuilder, fromCount).toArray(new Predicate[0]));
    	Long count = em.createQuery(countQuery).getSingleResult();
    	
    	// List
    	CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(entityType);
    	Root<E> from = criteriaQuery.from(entityType);
    	
    	List<Order> orderBy = new ArrayList<>();
    	for (Sorting sort : sorting) {
    		if (sort.getOrder().isDESC()) {
    			orderBy.add(criteriaBuilder.desc(from.get(sort.getField())));
    		}
    		if (sort.getOrder().isASC()) {
    			orderBy.add(criteriaBuilder.asc(from.get(sort.getField())));
    		}
    	}
    	
    	if (!orderBy.isEmpty()) {
    		criteriaQuery.orderBy(orderBy);
    	}
    	
    	CriteriaQuery<E> select = criteriaQuery.select(from).where(createFilters(filtering, criteriaBuilder, from).toArray(new Predicate[0]));
    	TypedQuery<E> typedQuery = em.createQuery(select);
    	typedQuery.setFirstResult((pagination.getPage() - 1) * pagination.getPerPage());
    	typedQuery.setMaxResults(pagination.getPerPage());
    	List<E> result = typedQuery.getResultList();
    	
    	// Page
    	long numberOfPages = count % pagination.getPerPage() == 0 ? count / pagination.getPerPage() : count / pagination.getPerPage() + 1;
    	return new Page<E>(result, count, numberOfPages, Long.valueOf(pagination.getPage()));
    }
    
    private List<Predicate> createFilters(List<Filtering> filtering, CriteriaBuilder criteriaBuilder, Root<E> from) {
    	List<Predicate> predicates = new ArrayList<>();
    	for (Filtering filter : filtering) {
    		Predicate predicate = filter.getOperator().getPredicateCreator().create(filter, criteriaBuilder, from);
    		predicates.add(predicate);
    	}
    	return predicates;
    }
}
