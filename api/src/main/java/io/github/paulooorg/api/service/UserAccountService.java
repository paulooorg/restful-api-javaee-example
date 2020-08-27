package io.github.paulooorg.api.service;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.inject.Inject;
import javax.transaction.Transactional;

import io.github.paulooorg.api.infrastructure.exception.BusinessException;
import io.github.paulooorg.api.infrastructure.security.Password;
import io.github.paulooorg.api.model.dto.ResetPasswordDTO;
import io.github.paulooorg.api.model.entities.User;
import io.github.paulooorg.api.model.entities.UserAccountKey;
import io.github.paulooorg.api.repository.UserAccountKeyRepository;
import io.github.paulooorg.api.repository.UserRepository;

public class UserAccountService {
	@Inject
	private EmailService emailService;
	
	@Inject
	private UserService userService;
	
	@Inject
	private UserAccountKeyRepository userAccountKeyRepository;
	
	@Inject
	private UserRepository userRepository;
	
	@Transactional
	public void sendRecoveryKey(String email) {
		Optional<User> user = userService.findByEmail(email);
		if (user.isPresent()) {
			UserAccountKey userAccountKey = userAccountKeyRepository.save(UserAccountKey.newRecoveryKey(user.get())).get();
			emailService.send("Recovery Key", "Recovery Key: " + userAccountKey.getKey(), email);
		} else {
			throw new BusinessException("emailNotFound", new Object[] {email});
		}
	}
	
	@Transactional
	public void sendActivationKey(String email) {
		Optional<User> user = userService.findByEmail(email);
		if (user.isPresent()) {
			UserAccountKey userAccountKey = userAccountKeyRepository.save(UserAccountKey.newActivationKey(user.get())).get();
			emailService.send("Activation Key", "Activation Key: " + userAccountKey.getKey(), email);
		} else {
			throw new BusinessException("emailNotFound", new Object[] {email});
		}
	}

	public Optional<UserAccountKey> findByKey(String activationKey) {
		return userAccountKeyRepository.findByKey(activationKey);
	}
	
	@Transactional
	public void activate(String activationKey) { 
		Optional<UserAccountKey> userAccountKey = findByKey(activationKey);
		if (userAccountKey.isPresent()) {
			if (userAccountKey.get().getExpirationDate().isAfter(LocalDateTime.now())) {
				User user = userAccountKey.get().getUser();
				user.setActive(true);
				userRepository.save(user);
			} else {
				throw new BusinessException("activationKeyExpired", new Object[] {});
			}
		} else {
			throw new BusinessException("activationKeyNotFound", new Object[] {});
		}
	}
	
	@Transactional
	public void resetPassword(ResetPasswordDTO resetPasswordDTO) {
		Optional<UserAccountKey> userAccountKey = findByKey(resetPasswordDTO.getRecoveryKey());
		if (userAccountKey.isPresent()) {
			if (userAccountKey.get().getExpirationDate().isAfter(LocalDateTime.now())) {
				User user = userAccountKey.get().getUser();
				user.setPassword(new Password(resetPasswordDTO.getNewPassword()));
				userRepository.save(user);
			} else {
				throw new BusinessException("recoveryKeyExpired", new Object[] {});
			}
		} else {
			throw new BusinessException("recoveryKeyNotFound", new Object[] {});
		}
	}
}
