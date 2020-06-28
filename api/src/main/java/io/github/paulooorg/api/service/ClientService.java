package io.github.paulooorg.api.service;

import java.util.List;

import javax.inject.Inject;

import io.github.paulooorg.api.model.entities.Client;
import io.github.paulooorg.api.repository.ClientRepository;

public class ClientService {
	@Inject
	private ClientRepository clientRepository;
	
	public List<Client> getAll() {
		return clientRepository.findAll();
	}
}
