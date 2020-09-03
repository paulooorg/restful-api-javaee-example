package io.github.paulooorg.api.resources;

import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import io.github.paulooorg.api.infrastructure.authentication.LoggedUser;
import io.github.paulooorg.api.infrastructure.authentication.SecurityContext;
import io.github.paulooorg.api.infrastructure.exception.ApiExceptions;
import io.github.paulooorg.api.infrastructure.validation.BeanValidator;
import io.github.paulooorg.api.model.dto.ChangePasswordDTO;
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

    @Inject
    private SecurityContext securityContext;
    
	@Override
	public EntityMapper<UserDTO, User> getMapper() {
		return UserMapper.INSTANCE;
	}

	@Override
	public EntityService<UserDTO, User> getService() {
		return userService;
	}
	
	@GET
	@Path("logged")
	public UserDTO getLoggedUser() {
		Optional<LoggedUser> loggedUser = securityContext.getLoggedUser();
		if (loggedUser.isPresent()) {
			return UserMapper.INSTANCE.entityToDTO(loggedUser.get().getUser());
		}
		throw ApiExceptions.unauthenticatedUser();
	}
	
	@POST
	@Path("{id}/change-password")
	public void changePassword(@PathParam("id") Long id, ChangePasswordDTO changePasswordDTO) {
		new BeanValidator<ChangePasswordDTO>().validate(changePasswordDTO);
		userService.changePassword(id, changePasswordDTO);
	}
}
