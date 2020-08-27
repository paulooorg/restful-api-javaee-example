package io.github.paulooorg.api.model.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;

import io.github.paulooorg.api.infrastructure.security.Password;

@Entity
public class User extends PersistentEntity {
	@Column(unique = true)
    private String username;

    @Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "value", column = @Column(name = "password")),
		@AttributeOverride(name = "salt", column = @Column(name = "salt", columnDefinition = "bytea")),
	})
    private Password password;

    private String name;

    @Email
    @Column(unique = true)
    private String email;
    
    @Column(name = "last_login")
    private LocalDateTime lastLogin;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Profile> profiles;
    
    private boolean active = false;
    
    public void addProfilesCodes(List<ProfileCode> profilesCodes) {
    	if (profiles == null) {
    		profiles = new ArrayList<>();
    	}
    	for (ProfileCode profileCode : profilesCodes) {
    		Profile profile = new Profile();
    		profile.setProfileCode(profileCode);
    		profile.setUser(this);
    		profiles.add(profile);
    	}
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public Password getPassword() {
		return password;
	}

	public void setPassword(Password password) {
		this.password = password;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}

	public List<Profile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<Profile> profiles) {
		this.profiles = profiles;
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Set<ProfileCode> getProfilesCodes() {
		return profiles.stream().map(p -> p.getProfileCode()).collect(Collectors.toSet());
	}
}
