package io.github.paulooorg.api.infrastructure.request.criteria.field;

import javax.persistence.criteria.Expression;

public interface FieldValueFactory {
	boolean isApplicable(Expression<?> field);
	
	Object create(Expression<?> field, String value);
}
