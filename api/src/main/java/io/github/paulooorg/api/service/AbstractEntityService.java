package io.github.paulooorg.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import io.github.paulooorg.api.infrastructure.exception.BusinessException;
import io.github.paulooorg.api.infrastructure.exception.ErrorCodes;
import io.github.paulooorg.api.infrastructure.request.RequestOptions;
import io.github.paulooorg.api.infrastructure.request.pagination.Page;
import io.github.paulooorg.api.model.dto.DTO;
import io.github.paulooorg.api.model.dto.mapper.EntityMapper;
import io.github.paulooorg.api.model.entities.PersistentEntity;
import io.github.paulooorg.api.repository.EntityRepository;

public abstract class AbstractEntityService<D extends DTO, E extends PersistentEntity> implements EntityService<D, E> {
	public abstract EntityRepository<E, Long> getRepository();
	
	public abstract EntityMapper<D, E> getMapper();
	
	@Override
	public Page<D> getAll(RequestOptions requestOptions) {
		Page<E> page = getRepository().findAll(requestOptions);
		List<D> dto = page.getContent().stream().map(e -> getMapper().entityToDTO(e)).collect(Collectors.toList());
		Page<D> pageDTO = new Page<D>(dto, page.getTotalCount(), page.getNumberOfPages(), page.getCurrentPage());
		return pageDTO;
	}

	@Override
	public Optional<E> findById(Long id) {
		return getRepository().findBy(id);
	}

	@Override
	@Transactional
	public Long create(E entity) {
		return getRepository().save(entity).get().getId();
	}

	@Override
	@Transactional
	public void update(Long id, D dto) {
		Optional<E> entity = getRepository().findBy(id);
		if (entity.isPresent()) {
			E newEntity = getMapper().merge(dto, entity.get());
			getRepository().save(newEntity);
		} else {
			throw new BusinessException("entityIdNotFound", new Object[] {id}, ErrorCodes.ENTITY_ID_NOT_FOUND);
		}
	}

	@Override
	@Transactional
	public void delete(Long id) {
		Optional<E> entity = getRepository().findBy(id);
		if (entity.isPresent()) {
			getRepository().remove(entity.get());
		} else {
			throw new BusinessException("entityIdNotFound", new Object[] {id}, ErrorCodes.ENTITY_ID_NOT_FOUND);
		}
	}
}
