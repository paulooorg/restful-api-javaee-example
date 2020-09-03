package io.github.paulooorg.api.infrastructure.exception;

import javax.ws.rs.core.Response;

public class ApiExceptions {
	public static ApiException entityIdNotFound(Long id) {
		return new ApiException("entityIdNotFound", new Object[] {id}, ErrorCodes.ENTITY_ID_NOT_FOUND, Response.Status.NOT_FOUND);
	}

	public static ApiException unauthenticatedUser() {
		return new ApiException("unauthenticatedUser", new Object[] {}, ErrorCodes.GENERIC_ERROR, Response.Status.BAD_REQUEST);
	}

	public static ApiException operatorNotFound(String operators) {
		return new ApiException("operatorNotFound", new Object[] {operators}, ErrorCodes.GENERIC_ERROR, Response.Status.BAD_REQUEST);
	}
}
