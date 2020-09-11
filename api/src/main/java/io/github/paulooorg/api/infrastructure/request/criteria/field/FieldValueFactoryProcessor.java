package io.github.paulooorg.api.infrastructure.request.criteria.field;

import java.util.List;

import javax.persistence.criteria.Expression;

public class FieldValueFactoryProcessor {
	private List<FieldValueFactory> fieldValueFactories;
	
	public FieldValueFactoryProcessor(List<FieldValueFactory> fieldValueFactories) {
		this.fieldValueFactories = fieldValueFactories;
	}
	
	public Object execute(Expression<?> field, String value) {
		for (FieldValueFactory fieldValueFactory : fieldValueFactories) {
			if (fieldValueFactory.isApplicable(field)) {
				return fieldValueFactory.create(field, value);
			}
		}
		throw new IllegalArgumentException("No factory applicable for field value");
	}
}
