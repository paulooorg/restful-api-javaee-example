package io.github.paulooorg.api.infrastructure.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {
	@Override
	public Response toResponse(Exception exception) {
		Error error = new Error();
		error.setMessage(getStackTrace(exception));
		error.setCode(ErrorCodes.GENERIC_ERROR);
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
	}
	
	private String getStackTrace(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		return sw.toString();
	}
}
