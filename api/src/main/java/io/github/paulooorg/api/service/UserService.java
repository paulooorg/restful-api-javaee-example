package io.github.paulooorg.api.service;

import io.github.paulooorg.api.model.dto.UserDTO;
import io.github.paulooorg.api.model.entities.User;
import io.github.paulooorg.api.repository.UserRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserService {
    @Inject
    private UserRepository userRepository;

    public List<UserDTO> getAll() {
        return userRepository.findAll().stream().map(u -> new UserDTO(u)).collect(Collectors.toList());
    }

    public UserDTO findById(Long id) {
        Optional<User> user = userRepository.findBy(id);
        if (user.isPresent()) {
            return new UserDTO(user.get());
        }
        return new UserDTO();
    }

    @Transactional
    public Long create(UserDTO user) {
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setUsername(user.getUsername());
        return userRepository.save(newUser).get().getId();
    }

    @Transactional
    public void update(Long id, UserDTO user) {
        User oldUser = userRepository.findBy(id).get();
        oldUser.setUsername(user.getUsername());
        oldUser.setName(user.getName());
        userRepository.save(oldUser);
    }

    @Transactional
    public void delete(Long id) {
        userRepository.remove(userRepository.findBy(id).get());
    }
}
