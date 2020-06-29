package io.github.paulooorg.api.infrastructure.request;

import java.util.List;

import javax.ws.rs.core.UriInfo;

import io.github.paulooorg.api.infrastructure.request.pagination.Pagination;
import io.github.paulooorg.api.infrastructure.request.sorting.Sorting;

public class RequestOptions {
	private Pagination pagination;
	
	private List<Sorting> sorting;
	
	public RequestOptions(UriInfo uriInfo) {
		pagination = new Pagination(uriInfo);
		sorting = Sorting.create(uriInfo);
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public List<Sorting> getSorting() {
		return sorting;
	}

	public void setSorting(List<Sorting> sorting) {
		this.sorting = sorting;
	}
}
