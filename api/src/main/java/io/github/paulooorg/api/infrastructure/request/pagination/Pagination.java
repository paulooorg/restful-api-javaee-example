package io.github.paulooorg.api.infrastructure.request.pagination;

import javax.ws.rs.core.UriInfo;

public class Pagination {
	private int page = 1;
	
	private int perPage = 10;
	
	public Pagination(UriInfo uriInfo) {
		String page = uriInfo.getQueryParameters().getFirst("page");
		if (page != null) {
			this.page = Integer.valueOf(page);
			if (this.page == 0) {
				this.page = 1;
			}
		}
		String perPage = uriInfo.getQueryParameters().getFirst("per_page");
		if (perPage != null) {
			this.perPage = Integer.valueOf(perPage);
		}
	}
	
	public Pagination(int page, int perPage) {
		this.page = page;
		this.perPage = perPage;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPerPage() {
		return perPage;
	}

	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}
}
