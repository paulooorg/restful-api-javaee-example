package io.github.paulooorg.api.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import io.github.paulooorg.api.infrastructure.request.pagination.Page;
import io.github.paulooorg.api.infrastructure.request.pagination.Pagination;
import io.github.paulooorg.api.model.entities.BaseEntity;

public abstract class AbstractEntityRepository<E extends BaseEntity, PK extends Serializable> implements EntityRepository<E, PK> {
    @Inject
    private EntityManager em;

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
    public Page<E> findAll(Pagination pagination) {
    	CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    	
    	// Count
    	CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
    	countQuery.select(criteriaBuilder.count(countQuery.from(entityType)));
    	Long count = em.createQuery(countQuery).getSingleResult();
    	
    	// List
    	CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(entityType);
    	Root<E> from = criteriaQuery.from(entityType);
    	CriteriaQuery<E> select = criteriaQuery.select(from);
    	TypedQuery<E> typedQuery = em.createQuery(select);
    	typedQuery.setFirstResult((pagination.getPage() - 1) * pagination.getPerPage());
    	typedQuery.setMaxResults(pagination.getPerPage());
    	List<E> result = typedQuery.getResultList();
    	
    	// Page
    	long numberOfPages = count % pagination.getPerPage() == 0 ? count / pagination.getPerPage() : count / pagination.getPerPage() + 1;
    	return new Page<E>(result, count, numberOfPages, Long.valueOf(pagination.getPage()));
    }
}
