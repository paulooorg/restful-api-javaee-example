package io.github.paulooorg.api.model.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import io.github.paulooorg.api.model.dto.ClientDTO;
import io.github.paulooorg.api.model.entities.Client;

@Mapper
public interface ClientMapper extends EntityMapper<ClientDTO, Client> {
	ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);
	
	@Override
	@Mappings({
		@Mapping(target = "id", ignore = true)
	})
	Client merge(ClientDTO dto, @MappingTarget Client entity);
}
