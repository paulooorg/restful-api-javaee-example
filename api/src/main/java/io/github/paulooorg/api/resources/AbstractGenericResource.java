package io.github.paulooorg.api.resources;

import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.logging.log4j.Logger;

import io.github.paulooorg.api.infrastructure.exception.ApiExceptions;
import io.github.paulooorg.api.infrastructure.exception.BusinessException;
import io.github.paulooorg.api.infrastructure.exception.ErrorCodes;
import io.github.paulooorg.api.infrastructure.request.RequestOptions;
import io.github.paulooorg.api.infrastructure.validation.BeanValidator;
import io.github.paulooorg.api.model.dto.DTO;
import io.github.paulooorg.api.model.dto.mapper.EntityMapper;
import io.github.paulooorg.api.model.entities.PersistentEntity;
import io.github.paulooorg.api.service.EntityService;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public abstract class AbstractGenericResource<D extends DTO, E extends PersistentEntity> implements GenericResource<D> {
	@Inject
	private Logger logger;
	
	public abstract EntityMapper<D, E> getMapper();
	
	public abstract EntityService<D, E> getService();
	
	@Override
	public Response getAll(UriInfo uriInfo) {
		logger.debug("REST request from {} with path {} to get all entities", getClass().getSimpleName(), getPathValue());
		return Response.status(Response.Status.OK).entity(getService().getAll(new RequestOptions(uriInfo))).build();
	}

	@Override
	public D findById(Long id) {
		Optional<E> entity = getService().findById(id);
	    if (entity.isPresent()) {
	    	return getMapper().entityToDTO(entity.get());
	    }
	    throw ApiExceptions.entityIdNotFound(id);
	}

	@Override
	public Long create(D dto) {
    	new BeanValidator<D>().validate(dto);
        return getService().create(getMapper().DTOToEntity(dto));
	}

	@Override
	public void update(Long id, D dto) {
		try {
			getService().update(id, dto);
		} catch (BusinessException e) {
			throwEntityIdNotFoundIfNeeded(e, id);
			throw e;
		}
	}

	@Override
	public void delete(Long id) {
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
	
	private String getPathValue() {
		Path path = getClass().getAnnotation(Path.class);
		if (path != null) {
			return path.value();
		}
		return "Annotation Path Not Found";
	}
}
