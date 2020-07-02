package io.github.paulooorg.api.infrastructure.request.filtering;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class Between implements PredicateCreator {
	@Override
	public Predicate create(Filtering filter, CriteriaBuilder criteriaBuilder, Root<?> from) {
		if (isDate(from, filter.getField())) {
			DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
			LocalDateTime leftDate = LocalDateTime.parse(filter.getValues().get(0), dtf);
			LocalDateTime rightDate = LocalDateTime.parse(filter.getValues().get(1), dtf);
			return criteriaBuilder.between(from.get(filter.getField()), leftDate, rightDate);
		}
		
		if (isNumber(from, filter.getField())) {
			Long leftValue = Long.valueOf(filter.getValues().get(0));
			Long rightValue = Long.valueOf(filter.getValues().get(1));	
			return criteriaBuilder.between(from.get(filter.getField()), leftValue, rightValue);
		}
		
		String leftValue = filter.getValues().get(0);
		String rightValue = filter.getValues().get(1);
		return criteriaBuilder.between(from.get(filter.getField()), leftValue, rightValue);
	}
	
	public boolean isDate(Root<?> from, String field) {
		Class<?> fieldType = from.get(field).getJavaType();
		List<Class<?>> dateTypes = Arrays.asList(Date.class, LocalDate.class, LocalDateTime.class);
		return dateTypes.contains(fieldType);
	}
	
	public boolean isNumber(Root<?> from, String field) {
		Class<?> fieldType = from.get(field).getJavaType();
		List<Class<?>> numberTypes = Arrays.asList(Long.class, Integer.class, Float.class, Double.class);
		return numberTypes.contains(fieldType);
	}
}
