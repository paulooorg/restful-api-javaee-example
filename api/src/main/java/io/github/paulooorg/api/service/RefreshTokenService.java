package io.github.paulooorg.api.service;

import java.util.Optional;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response.Status;

import io.github.paulooorg.api.infrastructure.authentication.JwtUtil;
import io.github.paulooorg.api.infrastructure.authentication.SecurityContext;
import io.github.paulooorg.api.infrastructure.authentication.TokenDTO;
import io.github.paulooorg.api.infrastructure.exception.ApiException;
import io.github.paulooorg.api.infrastructure.exception.ErrorCodes;
import io.github.paulooorg.api.model.entities.RefreshToken;
import io.github.paulooorg.api.repository.RefreshTokenRepository;

public class RefreshTokenService {
	@Inject
	private RefreshTokenRepository refreshTokenRepository;
	
	@Inject
	private SecurityContext securityContext;
	
	public Optional<RefreshToken> findByToken(String token) {
		return refreshTokenRepository.findByToken(token);
	}
	
	public TokenDTO refresh(String refreshToken) {
		String accessToken = securityContext.getLoggedUser().get().getAccessToken();
		Optional<RefreshToken> storedRefreshToken = findByToken(refreshToken);
		if (storedRefreshToken.isPresent() && storedRefreshToken.get().isValid()) {
			if (new JwtUtil().isAccessTokenValid(accessToken)) {
				// Same TokenDTO
				return new TokenDTO(accessToken, new JwtUtil().getExpirationDate(accessToken), refreshToken);
			} else {
				// New TokenDTO
				return new JwtUtil().createTokenDTO(securityContext.getLoggedUser().get().getUser());
			}
		}
		throw new ApiException("refreshTokenExpired", new Object[] {}, ErrorCodes.GENERIC_ERROR, Status.UNAUTHORIZED);
	}

	@Transactional
	public void blockByToken(String token) {
		refreshTokenRepository.block(token);
	}
}
