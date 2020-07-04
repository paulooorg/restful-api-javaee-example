package io.github.paulooorg.api.infrastructure.json;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class CustomObjectMapperFactory {
	public static ObjectMapper create() {
		ObjectMapper customObjectMapper = new ObjectMapper();
		customObjectMapper.registerModule(createLocalDateTimeModule());
		customObjectMapper.registerModule(createLocalDateModule());
		return customObjectMapper;
	}
	
	private static SimpleModule createLocalDateTimeModule() {
		return new SimpleModule()
				.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer())
				.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
	}
	
	private static SimpleModule createLocalDateModule() {
		return new SimpleModule()
				.addSerializer(LocalDate.class, new LocalDateSerializer())
				.addDeserializer(LocalDate.class, new LocalDateDeserializer());
	}
}
