package io.github.paulooorg.api.infrastructure.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import io.github.paulooorg.api.infrastructure.i18n.Messages;

@Provider
public class BusinessExceptionMapper implements ExceptionMapper<BusinessException> {
	@Override
	public Response toResponse(BusinessException exception) {
		Error error = new Error();
		error.setMessage(Messages.get(exception.getI18n(), exception.getParams()));
		error.setCode(ErrorCodes.GENERIC_ERROR);
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
	}
}
