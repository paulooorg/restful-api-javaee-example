package io.github.paulooorg.api.resources;

import javax.inject.Inject;
import javax.ws.rs.Path;

import io.github.paulooorg.api.model.dto.ClientDTO;
import io.github.paulooorg.api.model.dto.mapper.ClientMapper;
import io.github.paulooorg.api.model.dto.mapper.EntityMapper;
import io.github.paulooorg.api.model.entities.Client;
import io.github.paulooorg.api.service.ClientService;
import io.github.paulooorg.api.service.EntityService;

@Path("client")
public class ClientResource extends AbstractGenericResource<ClientDTO, Client> {
	@Inject
	private ClientService clientService;

	@Override
	public EntityMapper<ClientDTO, Client> getMapper() {
		return ClientMapper.INSTANCE;
	}

	@Override
	public EntityService<ClientDTO, Client> getService() {
		return clientService;
	}
}
