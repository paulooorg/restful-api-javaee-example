package io.github.paulooorg.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.transaction.Transactional;

import io.github.paulooorg.api.infrastructure.request.pagination.Page;
import io.github.paulooorg.api.infrastructure.request.pagination.Pagination;
import io.github.paulooorg.api.model.dto.UserDTO;
import io.github.paulooorg.api.model.dto.mapper.UserMapper;
import io.github.paulooorg.api.model.entities.User;
import io.github.paulooorg.api.repository.UserRepository;

public class UserService {
    @Inject
    private UserRepository userRepository;

    public Page<UserDTO> getAll(Pagination pagination) {
    	Page<User> pageUsers = userRepository.findAll(pagination);
    	List<UserDTO> usersDTO = pageUsers.getContent().stream().map(u -> UserMapper.INSTANCE.userToUserDTO(u)).collect(Collectors.toList());
    	Page<UserDTO> pageUsersDTO = new Page<UserDTO>(usersDTO, pageUsers.getTotalCount(), pageUsers.getNumberOfPages(), pageUsers.getCurrentPage());
    	return pageUsersDTO;
    }
    
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
