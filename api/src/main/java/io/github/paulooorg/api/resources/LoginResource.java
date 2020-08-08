package io.github.paulooorg.api.resources;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.github.paulooorg.api.infrastructure.authentication.LoginDTO;
import io.github.paulooorg.api.service.LoginService;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("login")
public class LoginResource {
	@Inject
	private LoginService loginService;
	
	@POST
	public Response login(LoginDTO login) {
		return Response.ok().entity(loginService.login(login)).build();
	}
}
