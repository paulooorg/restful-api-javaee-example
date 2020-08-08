package io.github.paulooorg.api.infrastructure.authentication;

import java.util.Date;

import javax.crypto.SecretKey;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.paulooorg.api.infrastructure.app.ApplicationProperties;
import io.github.paulooorg.api.model.entities.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {
	private static final Logger logger = LogManager.getLogger(JwtUtil.class);
	
	private static final String privateKey;
	
	private static final long expiration;
	
	static {
		privateKey = ApplicationProperties.get("jwt.privatekey");
		expiration = Long.valueOf(ApplicationProperties.get("jwt.expiration"));
	}
	
	public boolean isAccessTokenValid(String accessToken) {
		try {
			Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parseClaimsJws(accessToken);
			return true;
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
			return false;
		}
	}
	
	public boolean isRefreshTokenValid(String refreshToken) {
		return false;
	}
	
	public TokenDTO createTokenDTO(User user) {
		Date expirationDate = getExpirationDate();
		String accessToken = Jwts.builder()
				.setIssuer("api")
				.setIssuedAt(new Date())
				.setSubject(user.getId().toString())
				.signWith(getSecretKey())
				.setExpiration(expirationDate)
				.compact();

	   String refreshToken = "TODO";
	   
	   return new TokenDTO(accessToken, expirationDate, refreshToken);
	}
	
	private SecretKey getSecretKey() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(JwtUtil.privateKey));
	}
	
	private Date getExpirationDate() {
		return new Date(System.currentTimeMillis() + expiration);
	}
	
	public static String prepareAccessToken(String accessToken) {
		return accessToken.replace("Bearer ", "");
	}
	
	public Long getSubject(String accessToken) {
		return Long.valueOf(Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parseClaimsJws(accessToken).getBody().getSubject());
	}
}
