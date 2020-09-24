package io.github.paulooorg.api.resources;

import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.UriInfo;

import io.github.paulooorg.api.infrastructure.authentication.LoggedUser;
import io.github.paulooorg.api.infrastructure.authentication.SecurityContext;
import io.github.paulooorg.api.infrastructure.exception.ApiExceptions;
import io.github.paulooorg.api.infrastructure.hateoas.LinkDTO;
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
    
    @Context
    private UriInfo uriInfo;
    
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
	public LinkDTO changePassword(@PathParam("id") Long id, ChangePasswordDTO changePasswordDTO) {
		new BeanValidator<ChangePasswordDTO>().validate(changePasswordDTO);
		userService.changePassword(id, changePasswordDTO);
		return createLink();
	}
	
	private LinkDTO createLink() {
		return new LinkDTO(
				Link.fromUriBuilder(uriInfo.getBaseUriBuilder().path(LoginResource.class)).rel("login").build(),
				HttpMethod.POST);
	}
}
