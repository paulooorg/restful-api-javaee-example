package io.github.paulooorg.api.infrastructure.request.filtering;

import java.util.Arrays;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import io.github.paulooorg.api.infrastructure.exception.ApiExceptions;
import io.github.paulooorg.api.infrastructure.request.criteria.expression.CriteriaExpressionBuilder;
import io.github.paulooorg.api.infrastructure.request.criteria.field.DateFieldFactory;
import io.github.paulooorg.api.infrastructure.request.criteria.field.DefaultFieldFactory;
import io.github.paulooorg.api.infrastructure.request.criteria.field.FieldValueFactoryProcessor;

public class Between implements PredicateCreator {
	@Override
	public Predicate create(Filtering filter, CriteriaBuilder criteriaBuilder, Root<?> from) {
		if (filter.getValues().size() != 2) {
			throw ApiExceptions.betweenOperatorNeedTwoValues();
		}
		
		Expression<?> field = CriteriaExpressionBuilder.build(from, filter.getField());
		
		FieldValueFactoryProcessor fieldValueFactoryProcessor = new FieldValueFactoryProcessor
				(Arrays.asList(new DateFieldFactory(), new DefaultFieldFactory()));
		
		Object leftValue = fieldValueFactoryProcessor.execute(field, filter.getValues().get(0));
		
		Object rightValue = fieldValueFactoryProcessor.execute(field, filter.getValues().get(1));
		
		return between(criteriaBuilder, field, leftValue, rightValue);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private <Y extends Comparable<? super Y>> Predicate between(CriteriaBuilder criteriaBuilder, Expression field, Object leftValue, Object rightValue) {
		return criteriaBuilder.between(field, (Y) leftValue, (Y) rightValue);
	}
}
