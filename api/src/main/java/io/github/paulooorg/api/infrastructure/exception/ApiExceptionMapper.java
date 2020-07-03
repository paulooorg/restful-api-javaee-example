package io.github.paulooorg.api.infrastructure.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import io.github.paulooorg.api.infrastructure.i18n.Messages;

@Provider
public class ApiExceptionMapper implements ExceptionMapper<ApiException> {
	@Override
	public Response toResponse(ApiException exception) {
		Error error = new Error();
		error.setMessage(Messages.get(exception.getI18n(), exception.getParams()));
		error.setCode(exception.getErrorCode());
		return Response.status(exception.getHttpStatus()).entity(error).build();
	}
}
