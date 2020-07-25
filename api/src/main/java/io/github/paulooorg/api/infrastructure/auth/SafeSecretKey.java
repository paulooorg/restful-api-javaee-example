package io.github.paulooorg.api.infrastructure.auth;

import javax.crypto.SecretKey;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

public class SafeSecretKey {
	public static void main(String[] args) {
		System.out.println(getRandomBase64());
	}
	
	public static SecretKey getRandom() {
		return Keys.secretKeyFor(SignatureAlgorithm.HS512);
	}
	
	public static String getRandomBase64() {
		return Encoders.BASE64.encode(getRandom().getEncoded());
	}
}
