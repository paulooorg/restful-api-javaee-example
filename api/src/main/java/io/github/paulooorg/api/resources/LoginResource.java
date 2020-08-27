package io.github.paulooorg.api.resources;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.github.paulooorg.api.infrastructure.authentication.LoginDTO;
import io.github.paulooorg.api.infrastructure.authentication.TokenDTO;
import io.github.paulooorg.api.model.entities.RefreshToken;
import io.github.paulooorg.api.service.LoginService;
import io.github.paulooorg.api.service.RefreshTokenService;
import io.github.paulooorg.api.util.Cookies;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("login")
public class LoginResource {
	@Inject
	private LoginService loginService;
	
	@Inject
	private RefreshTokenService refreshTokenService;
	
	@POST
	public Response login(LoginDTO login) {
		TokenDTO tokenDTO = loginService.login(login);
		RefreshToken refreshToken = refreshTokenService.findByToken(tokenDTO.getRefreshToken()).get(); 
		return Response.ok().entity(tokenDTO).cookie(Cookies.createRefreshTokenCookie(refreshToken)).build();
	}
	
	@GET
	@Path("refresh")
	public Response refresh(@CookieParam("refresh-token") Cookie refreshTokenCookie) {
		TokenDTO tokenDTO = refreshTokenService.refresh(refreshTokenCookie);
		RefreshToken refreshToken = refreshTokenService.findByToken(tokenDTO.getRefreshToken()).get();
		return Response.ok().entity(tokenDTO).cookie(Cookies.createRefreshTokenCookie(refreshToken)).build();
	}
}
