package io.github.paulooorg.api.model.dto.mapper;

import org.mapstruct.MappingTarget;

import io.github.paulooorg.api.model.dto.DTO;
import io.github.paulooorg.api.model.entities.PersistentEntity;

public interface EntityMapper<D extends DTO, E extends PersistentEntity> {
	D entityToDTO(E entity);
	
	E DTOToEntity(D dto);
	
	E merge(D dto, @MappingTarget E entity);
}
