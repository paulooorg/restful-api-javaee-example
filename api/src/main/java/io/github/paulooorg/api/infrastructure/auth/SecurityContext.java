package io.github.paulooorg.api.infrastructure.auth;

public class SecurityContext {
	public LoggedUser getLoggedUser() {
		return new ThreadLocalLoggedUser().getLoggedUser();
	}
}
