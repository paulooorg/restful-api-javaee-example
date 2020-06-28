package io.github.paulooorg.api.service;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.transaction.Transactional;

import io.github.paulooorg.api.model.dto.UserDTO;
import io.github.paulooorg.api.model.entities.User;
import io.github.paulooorg.api.repository.UserRepository;

public class UserService {
    @Inject
    private UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findBy(id);
    }

    @Transactional
    public Long create(User user) {
        return userRepository.save(user).get().getId();
    }

    @Transactional
    public void update(Long id, UserDTO dto) {
        User user = userRepository.findBy(id).get();
        user.setUsername(dto.getUsername());
        user.setName(dto.getName());
        userRepository.save(user);
    }

    @Transactional
    public void delete(Long id) {
        userRepository.remove(userRepository.findBy(id).get());
    }
}
