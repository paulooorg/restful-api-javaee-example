package io.github.paulooorg.api.infrastructure.request.filtering;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public interface PredicateCreator {
	Predicate create(Filtering filter, CriteriaBuilder criteriaBuilder, Root<?> from);
}
