package io.github.paulooorg.api.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import io.github.paulooorg.api.infrastructure.request.RequestOptions;
import io.github.paulooorg.api.infrastructure.request.pagination.Page;
import io.github.paulooorg.api.model.entities.BaseEntity;

public interface EntityRepository<E extends BaseEntity<PK>, PK extends Serializable> {
    Optional<E> save(E entity);

    void remove(E entity);

    Optional<E> findBy(PK primaryKey);

    List<E> findAll();
    
    Page<E> findAll(RequestOptions RequestOptions);
}
