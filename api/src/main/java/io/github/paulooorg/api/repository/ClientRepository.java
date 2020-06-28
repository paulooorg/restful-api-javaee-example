package io.github.paulooorg.api.repository;

import io.github.paulooorg.api.model.entities.Client;

public class ClientRepository extends AbstractEntityRepository<Client, Long> {
	public ClientRepository() {
		super(Client.class);
	}
}
