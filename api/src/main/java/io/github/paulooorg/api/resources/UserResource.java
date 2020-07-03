package io.github.paulooorg.api.resources;

import javax.inject.Inject;
import javax.ws.rs.Path;

import io.github.paulooorg.api.model.dto.UserDTO;
import io.github.paulooorg.api.model.dto.mapper.EntityMapper;
import io.github.paulooorg.api.model.dto.mapper.UserMapper;
import io.github.paulooorg.api.model.entities.User;
import io.github.paulooorg.api.service.EntityService;
import io.github.paulooorg.api.service.UserService;

@Path("user")
public class UserResource extends AbstractGenericResource<UserDTO, User> {
    @Inject
    private UserService userService;

	@Override
	public EntityMapper<UserDTO, User> getMapper() {
		return UserMapper.INSTANCE;
	}

	@Override
	public EntityService<UserDTO, User> getService() {
		return userService;
	}
}
