package io.github.paulooorg.api.infrastructure.request.sorting;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import io.github.paulooorg.api.infrastructure.request.criteria.expression.CriteriaExpressionBuilder;

import javax.persistence.criteria.Order;

public class Asc implements OrderCreator {
	@Override
	public Order create(Sorting sort, CriteriaBuilder criteriaBuilder, Root<?> from) {
		Expression<?> field = CriteriaExpressionBuilder.build(from, sort.getField());
		return criteriaBuilder.asc(field);
	}
}
