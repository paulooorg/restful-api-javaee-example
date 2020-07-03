package io.github.paulooorg.api.model.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import io.github.paulooorg.api.model.dto.mapper.UserMapper;
import io.github.paulooorg.api.model.entities.User;

public class UserDTOTest {
	@Test
	public void shouldCreateUserDTO() {
		User user = new User();
		user.setUsername("Test Username");
		user.setName("Test Name");
		
		UserDTO userDto = UserMapper.INSTANCE.entityToDTO(user);
		
		assertEquals("Test Username", userDto.getUsername());
		assertEquals("Test Name", userDto.getName());
	}
}
