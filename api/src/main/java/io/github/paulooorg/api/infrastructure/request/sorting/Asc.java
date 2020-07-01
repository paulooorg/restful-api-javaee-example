package io.github.paulooorg.api.infrastructure.request.sorting;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Order;

public class Asc implements OrderCreator {
	@Override
	public Order create(Sorting sort, CriteriaBuilder criteriaBuilder, Root<?> from) {
		return criteriaBuilder.asc(from.get(sort.getField()));
	}
}
