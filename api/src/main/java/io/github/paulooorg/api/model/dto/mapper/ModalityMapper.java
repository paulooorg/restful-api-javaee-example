package io.github.paulooorg.api.model.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import io.github.paulooorg.api.model.dto.ModalityDTO;
import io.github.paulooorg.api.model.entities.Modality;

@Mapper
public interface ModalityMapper extends EntityMapper<ModalityDTO, Modality> {
	ModalityMapper INSTANCE = Mappers.getMapper(ModalityMapper.class);

	@Override
	@Mappings({
		@Mapping(target = "id", ignore = true)
	})
	Modality merge(ModalityDTO dto, @MappingTarget Modality entity);
}
