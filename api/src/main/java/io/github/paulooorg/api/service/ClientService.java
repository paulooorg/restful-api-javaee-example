package io.github.paulooorg.api.service;

import javax.inject.Inject;

import io.github.paulooorg.api.model.dto.ClientDTO;
import io.github.paulooorg.api.model.dto.mapper.ClientMapper;
import io.github.paulooorg.api.model.dto.mapper.EntityMapper;
import io.github.paulooorg.api.model.entities.Client;
import io.github.paulooorg.api.repository.ClientRepository;
import io.github.paulooorg.api.repository.EntityRepository;

public class ClientService extends AbstractEntityService<ClientDTO, Client> {
	@Inject
	private ClientRepository clientRepository;

	@Override
	public EntityRepository<Client, Long> getRepository() {
		return clientRepository;
	}

	@Override
	public EntityMapper<ClientDTO, Client> getMapper() {
		return ClientMapper.INSTANCE;
	}
}
