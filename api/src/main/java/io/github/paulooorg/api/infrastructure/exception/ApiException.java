package io.github.paulooorg.api.infrastructure.exception;

import javax.ws.rs.core.Response;

public class ApiException extends BusinessException {
	private static final long serialVersionUID = 1L;

	private String errorCode;
	
	private Response.Status httpStatus;
	
	public ApiException(String i18n, Object[] params, String errorCode, Response.Status httpStatus) {
		super(i18n, params);
		this.errorCode = errorCode;
		this.httpStatus = httpStatus;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public Response.Status getHttpStatus() {
		return httpStatus;
	}
}
