package io.github.paulooorg.api.infrastructure.request.sorting;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

public class Desc implements OrderCreator {
	@Override
	public Order create(Sorting sort, CriteriaBuilder criteriaBuilder, Root<?> from) {
		return criteriaBuilder.desc(from.get(sort.getField()));
	}
}
