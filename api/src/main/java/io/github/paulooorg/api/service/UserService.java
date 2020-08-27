package io.github.paulooorg.api.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.transaction.Transactional;

import io.github.paulooorg.api.infrastructure.exception.ApiExceptions;
import io.github.paulooorg.api.infrastructure.exception.BusinessException;
import io.github.paulooorg.api.infrastructure.security.Password;
import io.github.paulooorg.api.model.dto.ChangePasswordDTO;
import io.github.paulooorg.api.model.dto.UserDTO;
import io.github.paulooorg.api.model.dto.UserRegistrationDTO;
import io.github.paulooorg.api.model.dto.mapper.EntityMapper;
import io.github.paulooorg.api.model.dto.mapper.UserMapper;
import io.github.paulooorg.api.model.entities.ProfileCode;
import io.github.paulooorg.api.model.entities.User;
import io.github.paulooorg.api.repository.EntityRepository;
import io.github.paulooorg.api.repository.UserRepository;

public class UserService extends AbstractEntityService<UserDTO, User> {
	private static final String DEFAULT_PASSWORD = "change123";
	
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
	
	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	@Override
	public User create(User entity) {
		beforeCreate(entity);
		return super.create(entity);
	}

	private void beforeCreate(User entity) {
		entity.setPassword(new Password(DEFAULT_PASSWORD));
		entity.setUsername(entity.getUsername().toLowerCase());
		entity.setName(entity.getName().toUpperCase());
		entity.setEmail(entity.getEmail().toLowerCase());
		entity.addProfilesCodes(getDefaultProfilesCodesForBasicUserAccess());
	}
	
	public void updateLastLoginDate(User user) {
		user.setLastLogin(LocalDateTime.now());
		userRepository.save(user);
	}
	
	@Transactional
	public void changePassword(Long userId, ChangePasswordDTO changePasswordDTO) {
		Optional<User> user = findById(userId);
		if (user.isPresent()) {
			if (user.get().getPassword().isEquals(changePasswordDTO.getCurrentPassword())) {
				User userWithNewPassword = user.get();
				userWithNewPassword.setPassword(new Password(changePasswordDTO.getNewPassword()));
			} else {
				throw new BusinessException("invalidCredentials", new Object[] {});
			}
		} else {
			throw ApiExceptions.entityIdNotFound(userId);
		}
	}
	
	@Transactional
	public UserDTO register(UserRegistrationDTO userRegistrationDTO) {
		User user = new User();
		user.setActive(false);
		user.setName(userRegistrationDTO.getName().toUpperCase());
		user.setUsername(userRegistrationDTO.getUsername().toLowerCase());
		user.setPassword(new Password(userRegistrationDTO.getPassword()));
		user.setEmail(userRegistrationDTO.getEmail().toLowerCase());
		user.addProfilesCodes(getDefaultProfilesCodesForBasicUserAccess());
		User savedUser = userRepository.save(user).get();
		return UserMapper.INSTANCE.entityToDTO(savedUser);
	}
	
	public List<ProfileCode> getDefaultProfilesCodesForBasicUserAccess() {
		return Arrays.asList(ProfileCode.USER);
	}
}
