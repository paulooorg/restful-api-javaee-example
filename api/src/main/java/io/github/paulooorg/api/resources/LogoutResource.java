package io.github.paulooorg.api.resources;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.github.paulooorg.api.infrastructure.validation.BeanValidator;
import io.github.paulooorg.api.model.dto.KeyDTO;
import io.github.paulooorg.api.service.RefreshTokenService;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("logout")
public class LogoutResource {
	@Inject
	private RefreshTokenService refreshTokenService;
	
	@GET
	public Response logout(KeyDTO refreshToken) {
		new BeanValidator<KeyDTO>().validate(refreshToken);
		refreshTokenService.blockByToken(refreshToken.getKey());
		return Response.ok().build();
	}
}
