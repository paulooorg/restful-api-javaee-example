package io.github.paulooorg.api.util;

import java.time.Duration;
import java.time.LocalDateTime;

import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.NewCookie;

import io.github.paulooorg.api.model.entities.RefreshToken;

public class Cookies {
	public static NewCookie createRefreshTokenCookie(RefreshToken refreshToken) {
		int maxAge = (int) Duration.between(LocalDateTime.now(), refreshToken.getExpiration()).getSeconds();
		String comment = "refresh-token to generate new access-token";
//		boolean secure = true;
		boolean secure = false;
		String path = null;
		String domain = null;
		
		return new NewCookie("refresh-token", refreshToken.getToken(), path, domain, comment, maxAge, secure);
	}
	
	public static NewCookie reset(Cookie cookie) {
		return new NewCookie(cookie, null, 0, false);
	}
}
