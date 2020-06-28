package io.github.paulooorg.api.infrastructure.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import io.github.paulooorg.api.infrastructure.i18n.Messages;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {
	@Override
	public Response toResponse(ValidationException exception) {
		ValidationError error = new ValidationError();
		error.setMessage(Messages.get(exception.getI18n(), exception.getParams()));
		error.setCode(ErrorCodes.VALIDATION_ERROR);
		error.setFieldErrors(exception.getFieldErrors());
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
	}
}
