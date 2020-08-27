package io.github.paulooorg.api.repository;

import java.util.Optional;

import javax.inject.Inject;

import org.apache.logging.log4j.Logger;

import io.github.paulooorg.api.model.entities.User;

public class UserRepository extends AbstractEntityRepository<User, Long> {
	@Inject
	private Logger logger;
	
    public UserRepository() {
        super(User.class);
    }
    
    public Optional<User> findByUsername(String username) {
    	try {
    		User user = em.createQuery("select u from User u left join fetch u.profiles where lower(u.username) = :username", User.class)
    	    		.setParameter("username", username.toLowerCase())
    	    		.getSingleResult();
    		return Optional.ofNullable(user);
    	} catch (Exception e) {
    		logger.error(e.getMessage(), e);
    		return Optional.empty();
    	}
    }

	public Optional<User> findByEmail(String email) {
		try {
    		User user = em.createQuery("select u from User u left join fetch u.profiles where lower(u.email) = :email", User.class)
    	    		.setParameter("email", email.toLowerCase())
    	    		.getSingleResult();
    		return Optional.ofNullable(user);
    	} catch (Exception e) {
    		logger.error(e.getMessage(), e);
    		return Optional.empty();
    	}
	}
}
