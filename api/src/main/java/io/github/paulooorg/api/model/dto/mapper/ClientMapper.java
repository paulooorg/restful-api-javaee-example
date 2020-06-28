package io.github.paulooorg.api.model.dto.mapper;

import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import io.github.paulooorg.api.model.dto.ClientDTO;
import io.github.paulooorg.api.model.entities.Client;

public interface ClientMapper {
	ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);
	
	@Mappings({
		@Mapping(source = "name", target = "name"),
		@Mapping(source = "email", target = "email"),
		@Mapping(source = "document", target = "document"),
		@Mapping(source = "documentType", target = "documentType")
	})
	ClientDTO clientToClientDTO(Client client);
	
	@Mappings({
		@Mapping(source = "name", target = "name"),
		@Mapping(source = "email", target = "email"),
		@Mapping(source = "document", target = "document"),
		@Mapping(source = "documentType", target = "documentType")
	})
	Client clientDTOTOClient(ClientDTO clientDTO);
}
