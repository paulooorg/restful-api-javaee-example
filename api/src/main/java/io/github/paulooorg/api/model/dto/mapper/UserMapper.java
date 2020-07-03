package io.github.paulooorg.api.model.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import io.github.paulooorg.api.model.dto.UserDTO;
import io.github.paulooorg.api.model.entities.User;

@Mapper
public interface UserMapper extends EntityMapper<UserDTO, User> {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	@Override
	@Mappings({
		@Mapping(target = "id", ignore = true),
	})
	User merge(UserDTO dto, @MappingTarget User entity);
}
