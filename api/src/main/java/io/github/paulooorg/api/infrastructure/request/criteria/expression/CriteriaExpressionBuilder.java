package io.github.paulooorg.api.infrastructure.request.criteria.expression;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.persistence.Embedded;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.paulooorg.api.infrastructure.exception.ApiExceptions;

public class CriteriaExpressionBuilder {
	private static final Logger logger = LogManager.getLogger(CriteriaExpressionBuilder.class);
	
	private static final String REGEX_DEFAULT_SEPARATOR = "\\.";
	
	private static final JoinType DEFAULT_JOINTYPE = JoinType.INNER;
	
	private static final String KEYWORD_GET = "GET";
	
	private static final String KEYWORD_JOIN = "JOIN";
	
	public static Expression<?> build(Root<?> from, String completePath) {
		String[] fields = completePath.split(REGEX_DEFAULT_SEPARATOR);
		String[] junctionsType = createJunctionsType(fields, from, completePath);
		return createPath(fields, junctionsType, from);
	}
	
	private static Path<?> createPath(String[] fields, String[] junctionsType, Root<?> from) {
		Path<?> path = null;
		Join<?, ?> join = null;
		for (int i = 0; i < fields.length; i++) {
			String field = fields[i];
			String junctionType = junctionsType[i];
			
			if (junctionType == KEYWORD_GET && join == null) {
				if (path == null) {
					path = from.get(field);
				} else {
					path = path.get(field);
				}
			} else if (junctionType == KEYWORD_JOIN && path == null) {
				if (join == null) {
					join = from.join(field, DEFAULT_JOINTYPE);
				} else {
					join = join.join(field, DEFAULT_JOINTYPE);
				}
			} else if (junctionType == KEYWORD_GET && join != null) {
				path = join.get(field);
			}
		}
		return path;
	}
	
	private static String[] createJunctionsType(String[] fields, Root<?> from, String completePath) {
		try {
			String[] junctionsType = new String[fields.length];
			Class<?> entityClass = from.getJavaType();
			for (int i = 0; i < fields.length; i++) {
				String field = fields[i];
				Field classField = entityClass.getDeclaredField(field);
				
				Type classType = classField.getGenericType();
				if (classType instanceof ParameterizedType) {
					String className = ((ParameterizedType) classField.getGenericType()).getActualTypeArguments()[0].getTypeName();
					entityClass = Class.forName(className);
				} else {
					entityClass = classField.getType();
				}
				
				String junctionType = KEYWORD_GET;
				if (classField.isAnnotationPresent(Embedded.class) 
						|| classField.isAnnotationPresent(ManyToOne.class)
						|| classField.isAnnotationPresent(OneToOne.class)) {
					junctionType = KEYWORD_GET;
				} else if (classField.isAnnotationPresent(ManyToOne.class) 
							|| classField.isAnnotationPresent(OneToMany.class)) {
					junctionType = KEYWORD_JOIN;
				}
				junctionsType[i] = junctionType;
			}
			return junctionsType;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw ApiExceptions.invalidFilterPath(completePath);
		}
	}
}
