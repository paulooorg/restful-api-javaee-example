package io.github.paulooorg.api.repository;

import io.github.paulooorg.api.model.entities.Modality;

public class ModalityRepository extends AbstractEntityRepository<Modality, Long> {
	public ModalityRepository() {
		super(Modality.class);
	}
}
