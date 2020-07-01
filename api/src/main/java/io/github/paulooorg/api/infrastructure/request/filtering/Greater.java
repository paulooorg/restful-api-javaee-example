package io.github.paulooorg.api.infrastructure.request.filtering;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class Greater implements PredicateCreator {
	@Override
	public Predicate create(Filtering filter, CriteriaBuilder criteriaBuilder, Root<?> from) {
		return criteriaBuilder.greaterThan(from.get(filter.getField()), filter.getValues().get(0));
	}
}
