package io.github.paulooorg.api.infrastructure.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import io.github.paulooorg.api.infrastructure.exception.ErrorCodes;
import io.github.paulooorg.api.infrastructure.exception.FieldError;
import io.github.paulooorg.api.infrastructure.exception.ValidationException;

public class BeanValidator<T> {
	public void validate(T bean) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<T>> violations = validator.validate(bean);
		if (violations.size() > 0) {
			List<FieldError> fieldErrors = new ArrayList<>();
			for (ConstraintViolation<T> violation : violations) {
				FieldError fieldError = new FieldError();
				fieldError.setMessage(violation.getMessage());
				fieldError.setField(violation.getPropertyPath().toString());
				fieldError.setCode(ErrorCodes.FIELD_ERROR);
				fieldErrors.add(fieldError);
			}
			throw new ValidationException("Validation Exception", null, fieldErrors);
		}
	}
}
