package io.github.paulooorg.api.infrastructure.authentication;

public class SecurityContext {
	public LoggedUser getLoggedUser() {
		return new ThreadLocalLoggedUser().getLoggedUser();
	}
}
