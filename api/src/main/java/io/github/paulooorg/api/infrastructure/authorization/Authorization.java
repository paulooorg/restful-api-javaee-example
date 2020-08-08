package io.github.paulooorg.api.infrastructure.authorization;

import java.util.List;
import java.util.regex.Pattern;

import io.github.paulooorg.api.model.entities.ProfileCode;

public class Authorization {
	private String resourcePath;
	
	private List<String> httpMethods;
	
	private ProfileCode profileCode;
	
	private boolean permitAll = false;
	
	private boolean authenticated = false;
	
	private Pattern pattern;
	
	public Authorization() {
	}

	public Authorization(String resourcePath, List<String> httpMethods, ProfileCode profileCode) {
		this.resourcePath = resourcePath;
		this.httpMethods = httpMethods;
		this.profileCode = profileCode;
		this.pattern = Pattern.compile(this.resourcePath, Pattern.CASE_INSENSITIVE);
	}
	
	public Authorization(String resourcePath, List<String> httpMethods, ProfileCode profileCode, boolean permitAll, boolean authenticated) {
		this.resourcePath = resourcePath;
		this.httpMethods = httpMethods;
		this.profileCode = profileCode;
		this.permitAll = permitAll;
		this.authenticated = authenticated;
		this.pattern = Pattern.compile(this.resourcePath, Pattern.CASE_INSENSITIVE);
	}
	
	public boolean isPermitAllAuthenticated() {
		return permitAll && authenticated;
	}
	
	public boolean isPermitAllNotAuthenticated() {
		return permitAll && !authenticated;
	}
	
	public String getResourcePath() {
		return resourcePath;
	}

	public List<String> getHttpMethods() {
		return httpMethods;
	}

	public ProfileCode getProfileCode() {
		return profileCode;
	}
	
	public boolean isPermitAll() {
		return permitAll;
	}

	public void setPermitAll(boolean permitAll) {
		this.permitAll = permitAll;
	}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	public Pattern getPattern() {
		return pattern;
	}
}
