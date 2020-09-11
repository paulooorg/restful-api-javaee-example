package io.github.paulooorg.api.infrastructure.request.filtering;

import java.util.Arrays;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import io.github.paulooorg.api.infrastructure.request.criteria.expression.CriteriaExpressionBuilder;
import io.github.paulooorg.api.infrastructure.request.criteria.field.DateFieldFactory;
import io.github.paulooorg.api.infrastructure.request.criteria.field.DefaultFieldFactory;
import io.github.paulooorg.api.infrastructure.request.criteria.field.EnumFieldFactory;
import io.github.paulooorg.api.infrastructure.request.criteria.field.FieldValueFactoryProcessor;

public class Equal implements PredicateCreator {
	@Override
	public Predicate create(Filtering filter, CriteriaBuilder criteriaBuilder, Root<?> from) {
		Expression<?> field = CriteriaExpressionBuilder.build(from, filter.getField());
		Object fieldValue = new FieldValueFactoryProcessor(
				Arrays.asList(new EnumFieldFactory(), new DateFieldFactory(), new DefaultFieldFactory()))
				.execute(field, filter.getValues().get(0));
		return criteriaBuilder.equal(field, fieldValue);
	}
}
