package io.github.paulooorg.api.resources.hateoas;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.UriInfo;

import io.github.paulooorg.api.infrastructure.hateoas.LinkDTO;
import io.github.paulooorg.api.model.dto.DTO;

public abstract class GenericResourceHateoasTemplate<D extends DTO> {
	private UriInfo uriInfo;
	
	public GenericResourceHateoasTemplate(UriInfo uriInfo) {
		this.uriInfo = uriInfo;
	}
	
	public List<LinkDTO> getLinksForCreate(D dto) {
		List<LinkDTO> links = new ArrayList<>();
		links.add(new LinkDTO(Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder().path(dto.getId().toString())).rel("self").build(), HttpMethod.GET));
		return links;
	}
	
	public List<LinkDTO> getLinksForUpdate(D dto) {
		List<LinkDTO> links = new ArrayList<>();
		links.add(new LinkDTO(Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build(), HttpMethod.GET));
		return links;
	}
	
	public List<LinkDTO> getLinksForGetAll(D dto) {
		List<LinkDTO> links = new ArrayList<>();
		links.add(new LinkDTO(Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder().path(dto.getId().toString())).rel("self").build(), HttpMethod.GET));
		return links;
	}
	
	public List<LinkDTO> getLinksForFindById(D dto) {
		List<LinkDTO> links = new ArrayList<>();
		links.add(new LinkDTO(Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build(), HttpMethod.GET));
		return links;
	}
}
