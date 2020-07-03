package io.github.paulooorg.api.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import io.github.paulooorg.api.model.dto.DTO;

public interface ListResource<D extends DTO> {
	@GET
	public Response getAll(@Context UriInfo uriInfo);
	
	@GET
	@Path("{id}")
	public D findById(@PathParam("id") Long id);
}
