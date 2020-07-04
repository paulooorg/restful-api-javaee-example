package io.github.paulooorg.api.infrastructure.json;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class LocalDateSerializer extends JsonSerializer<LocalDate> {
	@Override
	public void serialize(LocalDate value, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
		DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE;
		jsonGenerator.writeString(value.format(dtf));
	}
}
