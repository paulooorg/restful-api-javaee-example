package io.github.paulooorg.api.model.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import io.github.paulooorg.api.model.dto.ClientDTO;

public class ValidClientDTOValidator implements ConstraintValidator<ValidClientDTO, ClientDTO> {
	@Override
	public boolean isValid(ClientDTO value, ConstraintValidatorContext context) {
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate("{invalidDocument}").addPropertyNode("document").addConstraintViolation();
		//context.buildConstraintViolationWithTemplate("{invalidDocument}").addPropertyNode("documentType").addConstraintViolation();
		return value.getDocumentType().getValidator().isValid(value.getDocument());
	}
}
