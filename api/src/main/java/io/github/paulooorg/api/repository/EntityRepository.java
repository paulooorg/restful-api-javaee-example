package io.github.paulooorg.api.repository;

import io.github.paulooorg.api.model.entities.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface EntityRepository<E extends BaseEntity, PK extends Serializable> {
    Optional<E> save(E entity);

    void remove(E entity);

    Optional<E> findBy(PK primaryKey);

    List<E> findAll();
}
