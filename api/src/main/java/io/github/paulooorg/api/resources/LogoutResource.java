package io.github.paulooorg.api.resources;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.github.paulooorg.api.service.RefreshTokenService;
import io.github.paulooorg.api.util.Cookies;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("logout")
public class LogoutResource {
	@Inject
	private RefreshTokenService refreshTokenService;
	
	@GET
	public Response logout(@CookieParam("refresh-token") Cookie refreshToken) {
		if (refreshToken != null) {
			refreshTokenService.blockByToken(refreshToken.getValue());
			return Response.ok().cookie(Cookies.reset(refreshToken)).build();
		}
		return Response.ok().build();
	}
}
