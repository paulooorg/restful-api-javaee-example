package io.github.paulooorg.api.infrastructure.authorization;

import javax.inject.Inject;

import io.github.paulooorg.api.config.AuthorizationConfiguration;
import io.github.paulooorg.api.model.entities.User;

public class AuthorizationChecker {
	@Inject
	private AuthorizationConfiguration authorizationConfiguration;
	
	public boolean isNotPublicAccess(String resourcePath, String httpMethod) {
		for (Authorization authorization : authorizationConfiguration.getAuthorizations()) {
			if (sameResourcePath(authorization, resourcePath) 
					&& authorizationContainsHttpMethod(authorization, httpMethod) 
					&& authorization.isPermitAllNotAuthenticated()) {
				return false;
			}
		}
		return true;
	}
	
	public boolean check(User user, String resourcePath, String httpMethod) {
		for (Authorization authorization : authorizationConfiguration.getAuthorizations()) {
			if (sameResourcePath(authorization, resourcePath) 
					&& (userProfilesCodesContainsAuthorizationProfileCode(user, authorization) || authorization.isPermitAllAuthenticated())
					&& authorizationContainsHttpMethod(authorization, httpMethod)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean sameResourcePath(Authorization authorization, String resourcePath) {
		return authorization.getPattern().matcher(resourcePath).matches();
	}
	
	private boolean userProfilesCodesContainsAuthorizationProfileCode(User user, Authorization authorization) {
		return user.getProfilesCodes().contains(authorization.getProfileCode());
	}
	
	private boolean authorizationContainsHttpMethod(Authorization authorization, String httpMethod) {
		return authorization.getHttpMethods().contains(httpMethod);
	}
}
