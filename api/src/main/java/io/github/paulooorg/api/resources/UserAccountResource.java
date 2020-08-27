package io.github.paulooorg.api.resources;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
	
	@POST
	@Path("reset-password/init")
	public Response initResetPassword(EmailDTO emailDTO) {
		new BeanValidator<EmailDTO>().validate(emailDTO);
		userAccountService.sendRecoveryKey(emailDTO.getEmail());
		return Response.ok().build();
	}
	
	@POST
	@Path("reset-password/finish")
	public Response finishResetPassword(ResetPasswordDTO resetPasswordDTO) {
		new BeanValidator<ResetPasswordDTO>().validate(resetPasswordDTO);
		userAccountService.resetPassword(resetPasswordDTO);
		return Response.ok().build();
	}
	
	@POST
	@Path("activate")
	public Response activate(KeyDTO keyDTO) {
		new BeanValidator<KeyDTO>().validate(keyDTO);
		userAccountService.activate(keyDTO.getKey());
		return Response.ok().build();
	}
	
	@POST
	@Path("register")
	public Response register(UserRegistrationDTO userRegistrationDTO) {
		new BeanValidator<UserRegistrationDTO>().validate(userRegistrationDTO);
		UserDTO userDTO = userService.register(userRegistrationDTO);
		userAccountService.sendActivationKey(userRegistrationDTO.getEmail());
		return Response.ok().entity(userDTO).build();
	}
}
