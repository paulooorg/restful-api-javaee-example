package io.github.paulooorg.api.resources;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.github.paulooorg.api.infrastructure.authentication.LoginDTO;
import io.github.paulooorg.api.infrastructure.authentication.TokenDTO;
import io.github.paulooorg.api.model.dto.KeyDTO;
import io.github.paulooorg.api.service.LoginService;
import io.github.paulooorg.api.service.RefreshTokenService;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("login")
public class LoginResource {
	@Inject
	private LoginService loginService;
	
	@Inject
	private RefreshTokenService refreshTokenService;
	
	@POST
	public TokenDTO login(LoginDTO login) {
		return loginService.login(login);
	}
	
	@GET
	@Path("refresh")
	public TokenDTO refresh(KeyDTO refreshToken) {
		return refreshTokenService.refresh(refreshToken.getKey());
	}
}
