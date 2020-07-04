package io.github.paulooorg.api.infrastructure.json;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {
	@Override
	public LocalDate deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException, JsonProcessingException {
		DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE;
		return LocalDate.parse(jsonParser.getText(), dtf);
	}
}
