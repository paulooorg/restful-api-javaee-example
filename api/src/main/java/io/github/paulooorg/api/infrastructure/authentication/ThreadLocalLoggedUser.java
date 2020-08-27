package io.github.paulooorg.api.infrastructure.authentication;

import io.github.paulooorg.api.model.entities.User;

public final class ThreadLocalLoggedUser {
	private static final ThreadLocal<LoggedUser> loggedUser = new ThreadLocal<>();
	
	//TODO: ThreadPool
	public void clear() {
		loggedUser.remove();
	}
	
	public LoggedUser getLoggedUser() {
		LoggedUser loggedUser = ThreadLocalLoggedUser.loggedUser.get();
		if (loggedUser == null) {
			loggedUser = createAnonymousUser();
			ThreadLocalLoggedUser.loggedUser.set(loggedUser);
		}
		return loggedUser;
	}
	
	private LoggedUser createAnonymousUser() {
		LoggedUser loggedUser = new LoggedUser();
		User user = new User();
		user.setUsername("anonymous");
		user.setName(user.getUsername());
		loggedUser.setUser(user);
		return loggedUser;
	}
	
	public void setLoggedUser(LoggedUser loggedUser) {
		ThreadLocalLoggedUser.loggedUser.set(loggedUser);
	}
}
