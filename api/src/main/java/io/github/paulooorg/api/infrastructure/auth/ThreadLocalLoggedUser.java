package io.github.paulooorg.api.infrastructure.auth;

public final class ThreadLocalLoggedUser {
	private static final ThreadLocal<LoggedUser> loggedUser = new ThreadLocal<>();
	
	//TODO: ThreadPool
	public void clear() {
		loggedUser.remove();
	}
	
	public LoggedUser getLoggedUser() {
		LoggedUser loggedUser = ThreadLocalLoggedUser.loggedUser.get();
		if (loggedUser == null) {
			loggedUser = new LoggedUser();
			ThreadLocalLoggedUser.loggedUser.set(loggedUser);
		}
		return loggedUser;
	}
	
	public void setLoggedUser(LoggedUser loggedUser) {
		ThreadLocalLoggedUser.loggedUser.set(loggedUser);
	}
}
