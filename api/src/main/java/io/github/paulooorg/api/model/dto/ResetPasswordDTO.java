package io.github.paulooorg.api.model.dto;

import javax.validation.constraints.NotBlank;

public class ResetPasswordDTO {
	@NotBlank
	private String recoveryKey;
	
	@NotBlank
	private String newPassword;

	public String getRecoveryKey() {
		return recoveryKey;
	}

	public void setRecoveryKey(String recoveryKey) {
		this.recoveryKey = recoveryKey;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}
