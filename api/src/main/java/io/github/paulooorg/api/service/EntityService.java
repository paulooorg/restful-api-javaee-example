package io.github.paulooorg.api.service;

import java.util.Optional;

import io.github.paulooorg.api.infrastructure.request.RequestOptions;
import io.github.paulooorg.api.infrastructure.request.pagination.Page;
import io.github.paulooorg.api.model.dto.DTO;
import io.github.paulooorg.api.model.entities.PersistentEntity;

public interface EntityService<D extends DTO, E extends PersistentEntity> {
	public Page<D> getAll(RequestOptions requestOptions);
	
	public Optional<E> findById(Long id);
	
	public Long create(E entity);
	
	public void update(Long id, D dto);
	
	public void delete(Long id);
}
