package io.github.paulooorg.api.infrastructure.request.criteria.field;

import javax.persistence.criteria.Expression;

import io.github.paulooorg.api.infrastructure.exception.ApiExceptions;
import io.github.paulooorg.api.infrastructure.request.criteria.expression.CriteriaExpressions;

public class EnumFieldFactory implements FieldValueFactory {
	@Override
	public boolean isApplicable(Expression<?> field) {
		return CriteriaExpressions.isEnum(field);
	}

	@Override
	@SuppressWarnings({"rawtypes", "unchecked"})
	public Object create(Expression<?> field, String value) {
		Class fieldType = field.getJavaType();
		try {
			Enum<?> enumValue = (Enum<?>) Enum.valueOf(fieldType, value);
			return enumValue;
		} catch (Exception e) {
			Object[] enumValues = fieldType.getEnumConstants();
			StringBuilder validValuesBuilder = new StringBuilder();
			for (Object enumValue : enumValues) {
				validValuesBuilder.append(enumValue).append(", ");
			}
			String validValues = validValuesBuilder.toString();
			throw ApiExceptions.invalidValueForEnum(validValues.substring(0, validValues.length() - 2));
		}
	}
}
