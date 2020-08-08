package io.github.paulooorg.api.service;

import java.util.Optional;

import javax.inject.Inject;

import io.github.paulooorg.api.infrastructure.authentication.JwtUtil;
import io.github.paulooorg.api.infrastructure.authentication.LoginDTO;
import io.github.paulooorg.api.infrastructure.authentication.TokenDTO;
import io.github.paulooorg.api.infrastructure.exception.BusinessException;
import io.github.paulooorg.api.model.entities.User;

public class LoginService {
	@Inject
	private UserService userService;
	
	public TokenDTO login(LoginDTO login) {
		Optional<User> user = userService.findByUsernameAndPassword(login.getUsername(), login.getPassword());
		if (user.isPresent()) {
			return new JwtUtil().createTokenDTO(user.get());
		}
		throw new BusinessException("invalidCredentials", new Object[] {});
	}
}
