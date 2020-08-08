package io.github.paulooorg.api.infrastructure.authorization;

import java.io.IOException;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import org.apache.logging.log4j.Logger;

import io.github.paulooorg.api.infrastructure.authentication.ThreadLocalLoggedUser;
import io.github.paulooorg.api.infrastructure.exception.BusinessException;
import io.github.paulooorg.api.model.entities.User;

@Provider
@Priority(Priorities.AUTHORIZATION)
public class AuthorizationFilter implements ContainerRequestFilter {
	@Inject
	private Logger logger;
	
	@Inject
	private AuthorizationChecker authorizationChecker;
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		String resourcePath = requestContext.getUriInfo().getPath().substring(1); // Remove first '/'
		String httpMethod = requestContext.getMethod();
		if (authorizationChecker.isNotPublicAccess(resourcePath, httpMethod)) {
			User user = new ThreadLocalLoggedUser().getLoggedUser().getUser();
			boolean authorized = authorizationChecker.check(user, resourcePath, httpMethod);
			if (!authorized) {
				logger.error("Unauthorized access to resource {} with http method {} and user {}", resourcePath, httpMethod, user.getUsername());
				throw new BusinessException("unauthorizedAccess", new Object[] {});
			}
		}
	}
}
