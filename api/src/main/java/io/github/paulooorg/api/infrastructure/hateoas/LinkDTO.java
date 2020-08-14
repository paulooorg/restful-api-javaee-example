package io.github.paulooorg.api.infrastructure.hateoas;

import javax.ws.rs.core.Link;

public class LinkDTO {
	private String rel;
	
	private String href;
	
	private String type;
	
	public LinkDTO() {
	}

	public LinkDTO(Link link, String type) {
		this.rel = link.getRel();
		this.href = link.getUri().toString();
		this.type = type;
	}
	
	public LinkDTO(String rel, String href, String type) {
		this.rel = rel;
		this.href = href;
		this.type = type;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
