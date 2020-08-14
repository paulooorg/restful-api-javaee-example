package io.github.paulooorg.api.model.dto;

import java.util.ArrayList;
import java.util.List;

import io.github.paulooorg.api.infrastructure.hateoas.LinkDTO;

public abstract class DTO {
	protected Long id;
	
	protected List<LinkDTO> links = new ArrayList<>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<LinkDTO> getLinks() {
		return links;
	}

	public void setLinks(List<LinkDTO> links) {
		this.links = links;
	}
}
