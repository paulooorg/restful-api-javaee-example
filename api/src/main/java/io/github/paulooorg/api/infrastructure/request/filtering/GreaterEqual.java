package io.github.paulooorg.api.infrastructure.request.filtering;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class GreaterEqual implements PredicateCreator {
	@Override
	public Predicate create(Filtering filter, CriteriaBuilder criteriaBuilder, Root<?> from) {
		return criteriaBuilder.greaterThanOrEqualTo(from.get(filter.getField()), filter.getValues().get(0));
	}
}
