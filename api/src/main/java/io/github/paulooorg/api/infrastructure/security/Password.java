package io.github.paulooorg.api.infrastructure.security;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.github.paulooorg.api.infrastructure.exception.BusinessException;

@Embeddable
public class Password {
	@NotBlank
	@NotNull
	private String value;

	@NotNull
	private byte[] salt;
	
	public Password() {
	}
	
	public Password(String password) {
		salt = getSalt();
		value = hashPassword(password, salt);
	}
	
	private String hashPassword(String password, byte[] salt) {
		try {
			final int iterationCount = 65536;
            final int keyLength = 128;
			KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterationCount, keyLength);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] hash = factory.generateSecret(spec).getEncoded();
			return String.format("%x", new BigInteger(hash));
		} catch (Exception e) {
			throw new BusinessException("internalServerError", new Object[] {});
		}
	}
	
	public boolean isEquals(String password) {
		return hashPassword(password, salt).equals(this.value);
	}
	
	public String getValue() {
		return value;
	}
	
	private byte[] getSalt() {
		SecureRandom sr = new SecureRandom();
		final int saltLength = 16;
		byte[] salt = new byte[saltLength];
		sr.nextBytes(salt);
		return salt;
	}
}
