package io.github.paulooorg.api.infrastructure.request.criteria.field;

import java.lang.reflect.Constructor;

import javax.persistence.criteria.Expression;

public class DefaultFieldFactory implements FieldValueFactory {
	@Override
	public boolean isApplicable(Expression<?> field) {
		return true;
	}

	@Override
	public Object create(Expression<?> field, String value) {
		try {
			Constructor<?> stringConstructor = field.getJavaType().getConstructor(new Class[] { String.class });
	        return stringConstructor.newInstance(value);
		} catch (Exception e) {
			return value;	
		}
	}
}
