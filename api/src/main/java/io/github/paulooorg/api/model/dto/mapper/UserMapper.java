package io.github.paulooorg.api.model.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import io.github.paulooorg.api.model.dto.UserDTO;
import io.github.paulooorg.api.model.entities.User;

@Mapper
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	@Mappings({
		@Mapping(source = "id", target = "id"),
		@Mapping(source = "username", target = "username"),
		@Mapping(source = "name", target = "name")
	})
	UserDTO userToUserDTO(User user);
	
	@Mappings({
		@Mapping(source = "id", target = "id"),
		@Mapping(source = "username", target = "username"),
		@Mapping(source = "name", target = "name")
	})
	User userDTOToUser(UserDTO userDTO);
}
