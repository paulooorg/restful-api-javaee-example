package io.github.paulooorg.api.infrastructure.request.pagination;

import java.util.List;

public class Page<T> {
	private List<T> content;
	
	private Long totalCount;
	
	private Long numberOfPages;
	
	private Long currentPage;

	public Page(List<T> content, Long totalCount, Long numberOfPages, Long currentPage) {
		this.content = content;
		this.totalCount = totalCount;
		this.numberOfPages = numberOfPages;
		this.currentPage = currentPage;
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
}
