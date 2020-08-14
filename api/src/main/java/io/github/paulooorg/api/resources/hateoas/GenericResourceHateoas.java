package io.github.paulooorg.api.resources.hateoas;

import javax.ws.rs.core.UriInfo;

import io.github.paulooorg.api.model.dto.DTO;

public class GenericResourceHateoas<D extends DTO> extends GenericResourceHateoasTemplate<D> {
	public GenericResourceHateoas(UriInfo uriInfo) {
		super(uriInfo);
	}
}
