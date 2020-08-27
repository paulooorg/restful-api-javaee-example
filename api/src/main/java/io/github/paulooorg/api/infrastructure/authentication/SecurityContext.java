package io.github.paulooorg.api.infrastructure.authentication;

import java.util.Optional;

public class SecurityContext {
	public Optional<LoggedUser> getLoggedUser() {
		return Optional.ofNullable(new ThreadLocalLoggedUser().getLoggedUser());
	}
}
