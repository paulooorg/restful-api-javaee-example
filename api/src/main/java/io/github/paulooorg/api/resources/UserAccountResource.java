package io.github.paulooorg.api.resources;

import java.util.Arrays;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import io.github.paulooorg.api.infrastructure.hateoas.LinkDTO;
import io.github.paulooorg.api.infrastructure.validation.BeanValidator;
import io.github.paulooorg.api.model.dto.EmailDTO;
import io.github.paulooorg.api.model.dto.KeyDTO;
import io.github.paulooorg.api.model.dto.ResetPasswordDTO;
import io.github.paulooorg.api.model.dto.UserDTO;
import io.github.paulooorg.api.model.dto.UserRegistrationDTO;
import io.github.paulooorg.api.service.UserAccountService;
import io.github.paulooorg.api.service.UserService;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("user-account")
public class UserAccountResource {
	@Inject
	private UserAccountService userAccountService;
	
	@Inject
	private UserService userService;
	
	@Context
	private UriInfo uriInfo;
	
	@POST
	@Path("reset-password/init")
	public LinkDTO initResetPassword(EmailDTO emailDTO) {
		new BeanValidator<EmailDTO>().validate(emailDTO);
		userAccountService.sendRecoveryKey(emailDTO.getEmail());
		return createResetPasswordFinishLink();
	}
	
	@POST
	@Path("reset-password/finish")
	public LinkDTO finishResetPassword(ResetPasswordDTO resetPasswordDTO) {
		new BeanValidator<ResetPasswordDTO>().validate(resetPasswordDTO);
		userAccountService.resetPassword(resetPasswordDTO);
		return createLoginLink();
	}
	
	@POST
	@Path("activate")
	public LinkDTO activate(KeyDTO keyDTO) {
		new BeanValidator<KeyDTO>().validate(keyDTO);
		userAccountService.activate(keyDTO.getKey());
		return createLoginLink();
	}
	
	@POST
	@Path("register")
	public UserDTO register(UserRegistrationDTO userRegistrationDTO) {
		new BeanValidator<UserRegistrationDTO>().validate(userRegistrationDTO);
		UserDTO userDTO = userService.register(userRegistrationDTO);
		userAccountService.sendActivationKey(userRegistrationDTO.getEmail());
		userDTO.setLinks(Arrays.asList(createActivateLink()));
		return userDTO;
	}
	
	private LinkDTO createLoginLink() {
		return new LinkDTO(
				Link.fromUriBuilder(uriInfo.getBaseUriBuilder()
						.path(LoginResource.class)).rel("login").build(),
				HttpMethod.POST);
	}
	
	private LinkDTO createActivateLink() {
		return new LinkDTO(
				Link.fromUriBuilder(uriInfo.getBaseUriBuilder()
						.path(UserAccountResource.class)
						.path("activate")).rel("activate").build(),
				HttpMethod.POST);
	}
	
	private LinkDTO createResetPasswordFinishLink() {
		return new LinkDTO(
				Link.fromUriBuilder(uriInfo.getBaseUriBuilder()
						.path(UserAccountResource.class)
						.path("reset-password/finish")).rel("reset-password-finish").build(),
				HttpMethod.POST);
	}
}
