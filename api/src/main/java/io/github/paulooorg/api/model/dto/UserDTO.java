package io.github.paulooorg.api.model.dto;

import javax.validation.constraints.NotBlank;

import io.github.paulooorg.api.model.entities.User;

public class UserDTO {
    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    private String name;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.username = user.getUsername();
        this.name = user.getName();
        this.id = user.getId();
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
}
