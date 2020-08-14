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

import io.github.paulooorg.api.infrastructure.request.RequestOptions;
import io.github.paulooorg.api.infrastructure.request.filtering.Filtering;
import io.github.paulooorg.api.infrastructure.request.pagination.Page;
import io.github.paulooorg.api.infrastructure.request.pagination.Pagination;
import io.github.paulooorg.api.infrastructure.request.sorting.Sorting;
import io.github.paulooorg.api.model.entities.BaseEntity;

public abstract class AbstractEntityRepository<E extends BaseEntity<PK>, PK extends Serializable> implements EntityRepository<E, PK> {
    @Inject
    protected EntityManager em;
    
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
        em.refresh(entity);
        return Optional.of(entity);
    }

    @Override
    public void remove(E entity) {
        em.remove(entity);
        em.flush();
    }

    @Override
    public Optional<E> findBy(PK primaryKey) {
        return Optional.ofNullable(em.find(entityType, primaryKey));
    }

    @Override
    public List<E> findAll() {
        return em.createQuery("SELECT e FROM " + entityType.getSimpleName() + " e ", entityType).getResultList();
    }
    
    @Override
    public Page<E> findAll(RequestOptions requestOptions) {
    	CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    	
    	// Options
    	Pagination pagination = requestOptions.getPagination();
    	List<Sorting> sorting = requestOptions.getSorting();
    	List<Filtering> filtering = requestOptions.getFiltering();
    	
    	// Count
    	Long count = count(filtering);
    	
    	// List
    	CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(entityType);
    	Root<E> from = criteriaQuery.from(entityType);
    	criteriaQuery.orderBy(createSorting(sorting, criteriaBuilder, from));
    	CriteriaQuery<E> select = criteriaQuery.select(from).where(createFilters(filtering, criteriaBuilder, from).toArray(new Predicate[0]));
    	TypedQuery<E> typedQuery = em.createQuery(select);
    	typedQuery.setFirstResult((pagination.getPage() - 1) * pagination.getPerPage());
    	typedQuery.setMaxResults(pagination.getPerPage());
    	List<E> result = typedQuery.getResultList();
    	
    	return new Page<E>(result, count, calculateNumberOfPages(count, pagination), Long.valueOf(pagination.getPage()));
    }
    
    private Long count(List<Filtering> filtering) {
    	CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    	CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
    	Root<E> fromCount = countQuery.from(entityType);
    	countQuery.select(criteriaBuilder.count(fromCount)).where(createFilters(filtering, criteriaBuilder, fromCount).toArray(new Predicate[0]));
    	Long count = em.createQuery(countQuery).getSingleResult();
    	return count;
    }
    
    private long calculateNumberOfPages(Long totalRows, Pagination pagination) {
    	return totalRows % pagination.getPerPage() == 0 ? totalRows / pagination.getPerPage() : totalRows / pagination.getPerPage() + 1;
    }
    
    private List<Order> createSorting(List<Sorting> sorting, CriteriaBuilder criteriaBuilder, Root<E> from) {
    	List<Order> orderBy = new ArrayList<>();
    	for (Sorting sort : sorting) {
    		orderBy.add(sort.getOrder().getOrderCreator().create(sort, criteriaBuilder, from));
    	}
    	return orderBy;
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
