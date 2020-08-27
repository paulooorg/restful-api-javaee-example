package io.github.paulooorg.api.model.dto;

import io.github.paulooorg.api.model.entities.ProfileCode;

public class ProfileDTO extends DTO {
	private ProfileCode profileCode;

	public ProfileCode getProfileCode() {
		return profileCode;
	}

	public void setProfileCode(ProfileCode profileCode) {
		this.profileCode = profileCode;
	}
}
