package io.github.paulooorg.api.infrastructure.authentication;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class TokenDTO {
	private String tokenType = "Bearer";
	
	private String accessToken;
	
	private LocalDateTime expiresIn;
	
	private String refreshToken;

	public TokenDTO() {
	}
	
	public TokenDTO(String accessToken, Date expiresIn) {
		this.accessToken = accessToken;
		this.expiresIn = expiresIn.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	public TokenDTO(String accessToken, Date expiresIn, String refreshToken) {
		this.accessToken = accessToken;
		this.expiresIn = expiresIn.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		this.refreshToken = refreshToken;
	}
	
	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public LocalDateTime getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(LocalDateTime expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
}
