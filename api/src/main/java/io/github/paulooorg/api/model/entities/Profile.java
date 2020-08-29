package io.github.paulooorg.api.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Profile extends PersistentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profile_sequence")
	@SequenceGenerator(name = "profile_sequence", sequenceName = "profile_sequence", allocationSize = 3)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "profile_code")
	private ProfileCode profileCode;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Override
    public Long getId() {
        return id;
    }
    
    @Override
    public void setId(Long id) {
    	this.id = id;
    }
	
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
