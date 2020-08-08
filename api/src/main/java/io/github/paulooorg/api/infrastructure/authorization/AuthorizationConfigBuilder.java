package io.github.paulooorg.api.infrastructure.authorization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.HttpMethod;

import io.github.paulooorg.api.model.entities.ProfileCode;

public class AuthorizationConfigBuilder {
	private AuthorizationConfigBuilder() {
	}
	
	public static ResourceStep authorizeRequests() {
		return new BuilderSteps();
	}
	
	public interface ResourceStep {
		MethodStep resource(String resourcePath);
	}
	
	public interface MethodStep {
		ProfileStep allMethods();
		
		ProfileStep methods(String ...methods);
	}
	
	public interface ProfileStep {
		BuildStep profiles(ProfileCode ...profilesCodes);
		
		AuthenticationStep permitAll();
	}
	
	public interface AuthenticationStep {
		BuildStep authenticated();
		
		BuildStep notAuthenticated();
	}
	
	public interface BuildStep {
		ResourceStep and();
		Set<Authorization> build();
	}
	
	private static class BuilderSteps implements ResourceStep, MethodStep, ProfileStep, AuthenticationStep, BuildStep {
		private static final String[] ALL_HTTP_METHODS = {HttpMethod.GET, HttpMethod.POST, HttpMethod.DELETE, 
				HttpMethod.PUT, HttpMethod.PATCH, HttpMethod.OPTIONS, HttpMethod.HEAD};
		
		private String resourcePath;
		
		private List<String> httpMethods = new ArrayList<>();
		
		private List<ProfileCode> profilesCodes = new ArrayList<>();
		
		private Set<Authorization> authorizations = new HashSet<>();

		private Boolean permitAll = false;
		
		private Boolean authenticated = false;
		
		private void resetFields() {
			resourcePath = null;
			httpMethods = new ArrayList<>();
			profilesCodes = new ArrayList<>();
			permitAll = false;
			authenticated = false;
		}
		
		@Override
		public ResourceStep and() {
			this.authorizations.addAll(build());
			resetFields();
			return this;
		}

		@Override
		public Set<Authorization> build() {
			if (permitAll) {
				authorizations.add(new Authorization(resourcePath, httpMethods, null, permitAll, authenticated));
			} else {
				for (ProfileCode profileCode : profilesCodes) {
					authorizations.add(new Authorization(resourcePath, httpMethods, profileCode));
				}
			}
			return authorizations;
		}

		@Override
		public BuildStep profiles(ProfileCode... profilesCodes) {
			this.profilesCodes = Arrays.asList(profilesCodes);
			return this;
		}

		@Override
		public ProfileStep allMethods() {
			this.httpMethods = Arrays.asList(ALL_HTTP_METHODS);
			return this;
		}

		@Override
		public ProfileStep methods(String... methods) {
			this.httpMethods = Arrays.asList(methods);
			return this;
		}

		@Override
		public MethodStep resource(String resourcePath) {
			this.resourcePath = resourcePath;
			return this;
		}

		@Override
		public BuildStep authenticated() {
			this.authenticated = true;
			return this;
		}

		@Override
		public BuildStep notAuthenticated() {
			this.authenticated = false;
			return this;
		}

		@Override
		public AuthenticationStep permitAll() {
			this.permitAll = true;
			return this;
		}
	}
}
