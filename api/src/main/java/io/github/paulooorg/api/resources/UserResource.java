package io.github.paulooorg.api.resources;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.Logger;

import io.github.paulooorg.api.model.dto.UserDTO;
import io.github.paulooorg.api.model.dto.mapper.UserMapper;
import io.github.paulooorg.api.model.entities.User;
import io.github.paulooorg.api.service.UserService;

@Path("user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
    @Inject
    private UserService userService;

    @Inject
    private Logger logger;

    @GET
    public List<UserDTO> getAll() {
        return userService.getAll().stream().map(u -> UserMapper.INSTANCE.userToUserDTO(u)).collect(Collectors.toList());
    }

    @GET
    @Path("{id}")
    public UserDTO findById(@PathParam("id") Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
        	return UserMapper.INSTANCE.userToUserDTO(user.get());
        }
        return new UserDTO();
    }

    @POST
    public Long create(UserDTO user) {
        return userService.create(UserMapper.INSTANCE.userDTOToUser(user));
    }

    @PUT
    @Path("{id}")
    public void update(@PathParam("id") Long id, UserDTO user) {
        userService.update(id, user);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Long id) {
        userService.delete(id);
    }
}
