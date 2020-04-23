package io.github.paulooorg.api.repository;

import io.github.paulooorg.api.model.entities.BaseEntity;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

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
}
