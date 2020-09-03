package io.github.paulooorg.api.resources;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.apache.logging.log4j.Logger;

import io.github.paulooorg.api.infrastructure.exception.ApiExceptions;
import io.github.paulooorg.api.infrastructure.exception.BusinessException;
import io.github.paulooorg.api.infrastructure.exception.ErrorCodes;
import io.github.paulooorg.api.infrastructure.validation.BeanValidator;
import io.github.paulooorg.api.model.dto.DTO;
import io.github.paulooorg.api.model.dto.mapper.EntityMapper;
import io.github.paulooorg.api.model.entities.PersistentEntity;
import io.github.paulooorg.api.resources.hateoas.GenericResourceHateoas;
import io.github.paulooorg.api.resources.hateoas.GenericResourceHateoasTemplate;
import io.github.paulooorg.api.service.EntityService;
import io.github.paulooorg.api.util.Resources;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public abstract class AbstractGenericResource<D extends DTO, E extends PersistentEntity> extends AbstractGenericListResource<D, E> implements FormResource<D> {
	@Inject
	private Logger logger;
	
	@Context
	private UriInfo uriInfo;
	
	public abstract EntityMapper<D, E> getMapper();
	
	public abstract EntityService<D, E> getService();

	public GenericResourceHateoasTemplate<D> getHateoasTemplate() {
		return new GenericResourceHateoas<D>(uriInfo);
	}
	
	@Override
	public D create(D dto) {
		logger.debug("REST request from {} with path {} to create entity", getClass().getSimpleName(), Resources.getPathValue(getClass()));
    	new BeanValidator<D>().validate(dto);
    	D created = getMapper().entityToDTO(getService().create(getMapper().DTOToEntity(dto)));
    	created.setLinks(getHateoasTemplate().getLinksForCreate(created));
		return created;
	}

	@Override
	public D update(Long id, D dto) {
		logger.debug("REST request from {} with path {} to update entity", getClass().getSimpleName(), Resources.getPathValue(getClass()));
		try {
			D updated = getMapper().entityToDTO(getService().update(id, dto));
			updated.setLinks(getHateoasTemplate().getLinksForUpdate(updated));
			return updated;
		} catch (BusinessException e) {
			throwEntityIdNotFoundIfNeeded(e, id);
			throw e;
		}
	}

	@Override
	public void delete(Long id) {
		logger.debug("REST request from {} with path {} to delete entity", getClass().getSimpleName(), Resources.getPathValue(getClass()));
		try {
			getService().delete(id);
		} catch (BusinessException e) {
			throwEntityIdNotFoundIfNeeded(e, id);
			throw e;
		}
	}
	
	private void throwEntityIdNotFoundIfNeeded(BusinessException e, Long id) {
		if (e.getErrorCode().equals(ErrorCodes.ENTITY_ID_NOT_FOUND)) {
			throw ApiExceptions.entityIdNotFound(id);
		}
	}
}
