package io.github.paulooorg.api.model.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import io.github.paulooorg.api.model.entities.User;

public class UserDTO {
    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    private String name;

    private LocalDateTime lastLogin;
    
    public UserDTO() {
    }

    public UserDTO(User user) {
        this.username = user.getUsername();
        this.name = user.getName();
        this.id = user.getId();
        this.lastLogin = user.getLastLogin();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public LocalDateTime getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}
}
