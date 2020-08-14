package io.github.paulooorg.api.resources;

import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import io.github.paulooorg.api.model.dto.DTO;

public interface FormResource<D extends DTO> {
	@POST
	public Response create(D dto);
	
	@PUT
	@Path("{id}")
	public Response update(@PathParam("id") Long id, D dto);
	
	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") Long id);
}
