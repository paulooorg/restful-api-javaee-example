package io.github.paulooorg.api.service;

import javax.inject.Inject;

import io.github.paulooorg.api.model.dto.ModalityDTO;
import io.github.paulooorg.api.model.dto.mapper.EntityMapper;
import io.github.paulooorg.api.model.dto.mapper.ModalityMapper;
import io.github.paulooorg.api.model.entities.Modality;
import io.github.paulooorg.api.repository.EntityRepository;
import io.github.paulooorg.api.repository.ModalityRepository;

public class ModalityService extends AbstractEntityService<ModalityDTO, Modality> {
	@Inject
	private ModalityRepository modalityRepository;
	
	@Override
	public EntityRepository<Modality, Long> getRepository() {
		return modalityRepository;
	}

	@Override
	public EntityMapper<ModalityDTO, Modality> getMapper() {
		return ModalityMapper.INSTANCE;
	}
}
