package io.github.paulooorg.api.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Profile extends PersistentEntity {
	@Enumerated(EnumType.STRING)
	@Column(name = "profile_code")
	private ProfileCode profileCode;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public ProfileCode getProfileCode() {
		return profileCode;
	}

	public void setProfileCode(ProfileCode profileCode) {
		this.profileCode = profileCode;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
