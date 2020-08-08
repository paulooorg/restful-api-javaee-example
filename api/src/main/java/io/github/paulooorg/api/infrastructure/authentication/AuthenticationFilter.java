package io.github.paulooorg.api.infrastructure.authentication;

import java.io.IOException;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;

import io.github.paulooorg.api.infrastructure.authorization.AuthorizationChecker;
import io.github.paulooorg.api.infrastructure.exception.BusinessException;
import io.github.paulooorg.api.service.UserService;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
	@Inject
	private UserService userService;
	
	@Inject
	private AuthorizationChecker authorizationChecker;
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		String resourcePath = requestContext.getUriInfo().getPath().substring(1); // Remove fist '/'
		String httpMethod = requestContext.getMethod();
		if (authorizationChecker.isNotPublicAccess(resourcePath, httpMethod)) {
			String accessToken = JwtUtil.prepareAccessToken(getAccessToken(requestContext));
			boolean valid = new JwtUtil().isAccessTokenValid(accessToken);
			if (valid) {
				LoggedUser loggedUser = new LoggedUser();
				loggedUser.setAccessToken(accessToken);
				loggedUser.setUser(userService.findById(new JwtUtil().getSubject(accessToken)).get());
				new ThreadLocalLoggedUser().setLoggedUser(loggedUser);
			} else {
				throw new BusinessException("unauthorizedAccess", new Object[] {});
			}
		}
	}

	private String getAccessToken(ContainerRequestContext requestContext) {
		String accessToken = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		if (accessToken == null) {
			throw new BusinessException("invalidAuthorizationHeader", new Object[] {});
		}
		if (!accessToken.startsWith("Bearer ")) {
			throw new BusinessException("invalidAuthorizationHeader", new Object[] {});
		}
		return accessToken;
	}
}
