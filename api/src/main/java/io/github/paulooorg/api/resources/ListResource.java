package io.github.paulooorg.api.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import io.github.paulooorg.api.infrastructure.request.pagination.Page;
import io.github.paulooorg.api.model.dto.DTO;

public interface ListResource<D extends DTO> {
	@GET
	public Page<D> getAll(@Context UriInfo uriInfo);
	
	@GET
	@Path("{id}")
	public D findById(@PathParam("id") Long id);
}
