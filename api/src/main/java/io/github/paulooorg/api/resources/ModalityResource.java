package io.github.paulooorg.api.resources;

import javax.inject.Inject;
import javax.ws.rs.Path;

import io.github.paulooorg.api.model.dto.ModalityDTO;
import io.github.paulooorg.api.model.dto.mapper.EntityMapper;
import io.github.paulooorg.api.model.dto.mapper.ModalityMapper;
import io.github.paulooorg.api.model.entities.Modality;
import io.github.paulooorg.api.service.EntityService;
import io.github.paulooorg.api.service.ModalityService;

@Path("modality")
public class ModalityResource extends AbstractGenericResource<ModalityDTO, Modality> {
	@Inject
	private ModalityService modalityService;

	@Override
	public EntityMapper<ModalityDTO, Modality> getMapper() {
		return ModalityMapper.INSTANCE;
	}

	@Override
	public EntityService<ModalityDTO, Modality> getService() {
		return modalityService;
	}
}
