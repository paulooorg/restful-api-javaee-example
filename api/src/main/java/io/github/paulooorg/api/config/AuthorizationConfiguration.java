package io.github.paulooorg.api.config;

import java.util.Set;

import javax.enterprise.context.ApplicationScoped;

import io.github.paulooorg.api.infrastructure.authorization.Authorization;
import io.github.paulooorg.api.infrastructure.authorization.AuthorizationConfigBuilder;
import io.github.paulooorg.api.model.entities.ProfileCode;

@ApplicationScoped
public class AuthorizationConfiguration {
	private static final Set<Authorization> authorizations;
	
	static {
//		authorizations = AuthorizationConfigBuilder.authorizeRequests()
//				.resource("modality.*").allMethods().profiles(ProfileCode.ADMIN).and()
//				.resource("user.*").allMethods().profiles(ProfileCode.ADMIN).and()				
//				.resource("login.*").allMethods().permitAll().notAuthenticated()
//				.build();
		
		authorizations = AuthorizationConfigBuilder.authorizeRequests().resource(".*").allMethods().permitAll().notAuthenticated().build();
	}
	
	public Set<Authorization> getAuthorizations() {
		return authorizations;
	}
}
