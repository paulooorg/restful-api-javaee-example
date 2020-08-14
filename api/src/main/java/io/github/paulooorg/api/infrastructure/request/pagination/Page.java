package io.github.paulooorg.api.infrastructure.request.pagination;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.UriInfo;

import io.github.paulooorg.api.infrastructure.hateoas.LinkDTO;

public class Page<T> {
	private List<T> content;
	
	private Long totalCount;
	
	private Long numberOfPages;
	
	private Long currentPage;
	
	private List<LinkDTO> links;

	public Page(List<T> content, Long totalCount, Long numberOfPages, Long currentPage) {
		this.content = content;
		this.totalCount = totalCount;
		this.numberOfPages = numberOfPages;
		this.currentPage = currentPage;
	}

	public void createLinks(UriInfo uriInfo) {
		this.links = new ArrayList<>();
		if (totalCount > 0) {
			
			long size = Pagination.DEFAULT_PAGE_SIZE;
			List<String> paramValues = uriInfo.getQueryParameters().get("per_page");
			if (paramValues != null && !paramValues.isEmpty()) {
				size = Long.valueOf(paramValues.get(0));
			}
			
			if (currentPage < numberOfPages) {
				links.add(new LinkDTO(Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()
						.queryParam("per_page", size)
						.queryParam("page", currentPage + 1)).rel("next").build(), HttpMethod.GET));
			}
			
			if (currentPage != numberOfPages) {
				links.add(new LinkDTO(Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()
						.queryParam("per_page", size)
						.queryParam("page", numberOfPages)).rel("last").build(), HttpMethod.GET));
			}

			if (currentPage > 1) {
				links.add(new LinkDTO(Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()
						.queryParam("per_page", size)
						.queryParam("page", currentPage - 1)).rel("previous").build(), HttpMethod.GET));
				
				links.add(new LinkDTO(Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()
						.queryParam("per_page", size)
						.queryParam("page", 1)).rel("first").build(), HttpMethod.GET));
			}
		}
	}
	
	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public Long getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(Long numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public Long getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Long currentPage) {
		this.currentPage = currentPage;
	}

	public List<LinkDTO> getLinks() {
		return links;
	}

	public void setLinks(List<LinkDTO> links) {
		this.links = links;
	}
}
