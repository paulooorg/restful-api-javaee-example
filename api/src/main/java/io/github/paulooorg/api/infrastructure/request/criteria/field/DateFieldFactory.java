package io.github.paulooorg.api.infrastructure.request.criteria.field;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import javax.persistence.criteria.Expression;

import io.github.paulooorg.api.infrastructure.request.criteria.expression.CriteriaExpressions;

public class DateFieldFactory implements FieldValueFactory {
	@Override
	public boolean isApplicable(Expression<?> field) {
		return CriteriaExpressions.isDate(field);
	}

	@Override
	public Object create(Expression<?> field, String value) {
		if (field.getJavaType().equals(LocalDateTime.class)) {
			DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
			return LocalDateTime.parse(value, dtf);
		}
		
		if (field.getJavaType().equals(LocalDate.class)) {
			DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE;
			return LocalDate.parse(value, dtf);
		}
		
		if (field.getJavaType().equals(Date.class)) {
			DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
			return Date.from(LocalDateTime.parse(value, dtf).atZone(ZoneId.systemDefault()).toInstant());
		}
		
		throw new IllegalArgumentException("Unsupported field type " + field.getJavaType().getSimpleName());
	}
}
