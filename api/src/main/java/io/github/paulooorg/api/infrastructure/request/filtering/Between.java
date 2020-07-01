package io.github.paulooorg.api.infrastructure.request.filtering;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class Between implements PredicateCreator {
	@Override
	public Predicate create(Filtering filter, CriteriaBuilder criteriaBuilder, Root<?> from) {
		String leftValue = filter.getValues().get(0);
		String rightValue = filter.getValues().get(1);
		return criteriaBuilder.between(from.get(filter.getField()), leftValue, rightValue);
	}
}
