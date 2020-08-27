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
		authorizations = AuthorizationConfigBuilder.authorizeRequests()
				
				.resource("user.*").allMethods().profiles(ProfileCode.ADMIN).and()
				.resource("user\\/logged").methods("GET").profiles(ProfileCode.USER).and()
				.resource("user\\/[0-9]*\\/change-password").methods("POST").profiles(ProfileCode.USER, ProfileCode.ADMIN).and()
				
				.resource("user-account.*").allMethods().permitAll().notAuthenticated().and()
				
				.resource("simulation").allMethods().profiles(ProfileCode.USER, ProfileCode.ADMIN).and()
				
				.resource("loan.*").allMethods().profiles(ProfileCode.ADMIN).and()
				.resource("loan.*").methods("GET").profiles(ProfileCode.USER, ProfileCode.ADMIN).and()
				.resource("loan\\/[0-9]*\\/payment").methods("GET").profiles(ProfileCode.USER, ProfileCode.ADMIN).and()
				
				.resource("modality.*").allMethods().profiles(ProfileCode.ADMIN).and()
				.resource("modality.*").methods("GET").profiles(ProfileCode.USER, ProfileCode.ADMIN).and()
				
				.resource("client.*").allMethods().profiles(ProfileCode.USER, ProfileCode.ADMIN).and()
				
				.resource("logout.*").allMethods().permitAll().authenticated().and()
				
				.resource("login").methods("POST").permitAll().notAuthenticated().and()
				.resource("login\\/refresh").methods("GET").permitAll().authenticated()
				
				.build();
		
//		authorizations = AuthorizationConfigBuilder.authorizeRequests().resource(".*").allMethods().permitAll().notAuthenticated().build();
	}
	
	public Set<Authorization> getAuthorizations() {
		return authorizations;
	}
}
