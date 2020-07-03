package io.github.paulooorg.api.infrastructure.exception;

import javax.ws.rs.core.Response;

public class ApiExceptions {
	public static ApiException entityIdNotFound(Long id) {
		return new ApiException("entityIdNotFound", new Object[] {id}, ErrorCodes.ENTITY_ID_NOT_FOUND, Response.Status.NOT_FOUND);
	}
}
