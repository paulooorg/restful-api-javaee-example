package io.github.paulooorg.api.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class EmailDTO {
	@NotBlank
	@Email
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
