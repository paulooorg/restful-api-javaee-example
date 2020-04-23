package io.github.paulooorg.api.resources;

import io.github.paulooorg.api.model.dto.UserDTO;
import io.github.paulooorg.api.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
    @Inject
    private UserService userService;

    @GET
    public List<UserDTO> getAll() {
        return userService.getAll();
    }

    @GET
    @Path("{id}")
    public UserDTO findById(@PathParam("id") Long id) {
        return userService.findById(id);
    }

    @POST
    public Long create(UserDTO user) {
        return userService.create(user);
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
