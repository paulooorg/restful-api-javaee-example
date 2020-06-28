package io.github.paulooorg.api.infrastructure.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends Error {
	private List<FieldError> fieldErrors = new ArrayList<>();

	public List<FieldError> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(List<FieldError> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}
}
