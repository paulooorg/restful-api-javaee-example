package io.github.paulooorg.api.service;

import java.util.Optional;

import javax.inject.Inject;
import javax.transaction.Transactional;

import io.github.paulooorg.api.infrastructure.authentication.JwtUtil;
import io.github.paulooorg.api.infrastructure.authentication.LoginDTO;
import io.github.paulooorg.api.infrastructure.authentication.TokenDTO;
import io.github.paulooorg.api.infrastructure.exception.BusinessException;
import io.github.paulooorg.api.model.entities.RefreshToken;
import io.github.paulooorg.api.model.entities.User;
import io.github.paulooorg.api.repository.RefreshTokenRepository;

public class LoginService {
	@Inject
	private UserService userService;
	
	@Inject
	private RefreshTokenRepository refreshTokenRepository;
	
	@Transactional
	public TokenDTO login(LoginDTO login) {
		Optional<User> user = userService.findByUsername(login.getUsername());
		if (user.isPresent() && user.get().getPassword().isEquals(login.getPassword()) && user.get().isActive()) {
			userService.updateLastLoginDate(user.get());
			TokenDTO token = new JwtUtil().createTokenDTO(user.get());
			RefreshToken refreshToken = RefreshToken.newRefreshToken(user.get());
			refreshTokenRepository.save(refreshToken);
			token.setRefreshToken(refreshToken.getToken());
			return token;
		}
		throw new BusinessException("invalidCredentials", new Object[] {});
	}
}
