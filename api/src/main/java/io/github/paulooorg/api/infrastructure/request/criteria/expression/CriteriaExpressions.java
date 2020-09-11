package io.github.paulooorg.api.infrastructure.request.criteria.expression;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.persistence.criteria.Expression;

public class CriteriaExpressions {
	public static boolean isEnum(Expression<?> field) {
		return field.getJavaType().isEnum();
	}
	
	public static boolean isDate(Expression<?> field) {
		List<Class<?>> dateTypes = Arrays.asList(Date.class, LocalDate.class, LocalDateTime.class);
		return dateTypes.contains(field.getJavaType());
	}
}
