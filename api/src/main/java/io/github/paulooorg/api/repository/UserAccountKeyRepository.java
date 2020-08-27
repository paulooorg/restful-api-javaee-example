package io.github.paulooorg.api.repository;

import java.util.Optional;

import io.github.paulooorg.api.model.entities.UserAccountKey;

public class UserAccountKeyRepository extends AbstractEntityRepository<UserAccountKey, Long> {
	public UserAccountKeyRepository() {
		super(UserAccountKey.class);
	}
	
	public Optional<UserAccountKey> findByKey(String activationKey) {
		UserAccountKey userAccountKey = em.createQuery("select k from UserAccountKey k where k.key = :key", UserAccountKey.class)
			.setParameter("key", activationKey)
			.getSingleResult();
		return Optional.ofNullable(userAccountKey);
	}
}
