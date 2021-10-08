package com.knf.dev.controller;

import com.knf.dev.model.User;
import com.knf.dev.repository.UserRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/api/users")
public class UserController {

    @Inject
    UserRepository userRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser
            (@PathParam("id") String id) {
        Optional<User> user = userRepository.findUserById(id);
        return user.isPresent() ?
                Response.ok(user.get()).build() :
                Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser
            (@PathParam("id") String id, User user) {
        Optional<User> optionalUser = userRepository.findUserById(id);
        if (!optionalUser.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(userRepository.updateUser(id, user)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User user) {
        userRepository.saveUser(user);
        return  Response.status(Response.Status.CREATED).build();
    }

	@DELETE
	@Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
	public Response deleteUser(@PathParam("id") String id) {
        Optional<User> user = userRepository.findUserById(id);
        user.ifPresent(usr -> userRepository.deleteUserById(usr.getId()));
        return user.isPresent() ?
                Response.noContent().build() :
                Response.status(Response.Status.NOT_FOUND).build();
	}
}