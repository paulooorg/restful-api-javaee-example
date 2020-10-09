package io.github.paulooorg.api.infrastructure.security;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PasswordTest {
	@Test
	public void shouldValidatePassword() {
		String plainTextPassword = "secretpassword";
		Password password = new Password(plainTextPassword);
		
		assertTrue(password.isEquals(plainTextPassword));
		assertFalse(password.isEquals("wrongpassword"));
	}
}
