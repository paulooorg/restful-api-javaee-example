package io.github.paulooorg.api.resources;

import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.logging.log4j.Logger;

import io.github.paulooorg.api.infrastructure.exception.ApiExceptions;
import io.github.paulooorg.api.infrastructure.request.RequestOptions;
import io.github.paulooorg.api.infrastructure.request.pagination.Page;
import io.github.paulooorg.api.model.dto.DTO;
import io.github.paulooorg.api.model.dto.mapper.EntityMapper;
import io.github.paulooorg.api.model.entities.PersistentEntity;
import io.github.paulooorg.api.resources.hateoas.GenericResourceHateoas;
import io.github.paulooorg.api.resources.hateoas.GenericResourceHateoasTemplate;
import io.github.paulooorg.api.service.EntityService;
import io.github.paulooorg.api.util.Resources;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public abstract class AbstractGenericListResource<D extends DTO, E extends PersistentEntity> implements ListResource<D> {
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
	public Response getAll(UriInfo uriInfo) {
		logger.debug("REST request from {} with path {} to get all entities", getClass().getSimpleName(), Resources.getPathValue(getClass()));
		Page<D> page = getService().getAll(new RequestOptions(uriInfo));
		page.createLinks(this.uriInfo);
		page.getContent().stream().<D>map(d -> {d.setLinks(getHateoasTemplate().getLinksForGetAll(d)); return d;}).collect(Collectors.toList());
		return Response.status(Response.Status.OK).entity(page).build();
	}

	@Override
	public D findById(Long id) {
		logger.debug("REST request from {} with path {} to find entity by id", getClass().getSimpleName(), Resources.getPathValue(getClass()));
		Optional<E> entity = getService().findById(id);
	    if (entity.isPresent()) {
	    	D dto = getMapper().entityToDTO(entity.get());
	    	dto.setLinks(getHateoasTemplate().getLinksForFindById(dto));
	    	return dto;
	    }
	    throw ApiExceptions.entityIdNotFound(id);
	}
}
