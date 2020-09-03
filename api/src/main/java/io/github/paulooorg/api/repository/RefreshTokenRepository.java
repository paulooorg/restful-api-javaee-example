package io.github.paulooorg.api.repository;

import java.util.Optional;

import io.github.paulooorg.api.model.entities.RefreshToken;
import io.github.paulooorg.api.model.entities.User;

public class RefreshTokenRepository extends AbstractEntityRepository<RefreshToken, Long> {
	public RefreshTokenRepository() {
		super(RefreshToken.class);
	}
	
	public Optional<RefreshToken> findByToken(String token) {
		return Optional.ofNullable(em.createQuery("select t from RefreshToken t where t.token = :token", RefreshToken.class)
			.setParameter("token", token)
			.getSingleResult());
	}
	
	public void block(String token) {
		Optional<RefreshToken> refreshToken = findByToken(token);
		if (refreshToken.isPresent()) {
			RefreshToken blockedRefreshToken = refreshToken.get();
			blockedRefreshToken.block();
			save(blockedRefreshToken);
		}
	}
	
	public Optional<RefreshToken> findLastByUser(User user) {
		try {
			return Optional.ofNullable(em.createQuery("select t from RefreshToken t where t.user.id = :userId order by t.expiration desc", RefreshToken.class)
					.setParameter("userId", user.getId())
					.setMaxResults(1)
					.getSingleResult());
		} catch (Exception e) {
			return Optional.empty();
		}
	}
}
