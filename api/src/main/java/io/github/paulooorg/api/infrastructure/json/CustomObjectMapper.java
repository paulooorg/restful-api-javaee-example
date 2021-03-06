package io.github.paulooorg.api.infrastructure.json;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;

@Provider
public class CustomObjectMapper implements ContextResolver<ObjectMapper> {
	@Override
	public ObjectMapper getContext(Class<?> type) {
		return CustomObjectMapperFactory.create();
	}
}
