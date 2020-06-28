package io.github.paulooorg.api.infrastructure.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationException extends BusinessException {
	private static final long serialVersionUID = 1L;

	private List<FieldError> fieldErrors = new ArrayList<>();
	
	public ValidationException(String i18n, Object[] params, List<FieldError> fieldErros) {
		super(i18n, params);
		this.fieldErrors = fieldErros;
	}

	public List<FieldError> getFieldErrors() {
		return fieldErrors;
	}	
}
