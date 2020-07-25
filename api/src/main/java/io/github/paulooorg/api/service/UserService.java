package io.github.paulooorg.api.service;

import java.util.Optional;

import javax.inject.Inject;

import io.github.paulooorg.api.model.dto.UserDTO;
import io.github.paulooorg.api.model.dto.mapper.EntityMapper;
import io.github.paulooorg.api.model.dto.mapper.UserMapper;
import io.github.paulooorg.api.model.entities.User;
import io.github.paulooorg.api.repository.EntityRepository;
import io.github.paulooorg.api.repository.UserRepository;

public class UserService extends AbstractEntityService<UserDTO, User> {
    @Inject
    private UserRepository userRepository;

	@Override
	public EntityRepository<User, Long> getRepository() {
		return userRepository;
	}

	@Override
	public EntityMapper<UserDTO, User> getMapper() {
		return UserMapper.INSTANCE;
	}
	
	public Optional<User> findByUsernameAndPassword(String username, String password) {
		return userRepository.findByUsernameAndPassword(username, password);
	}
}
