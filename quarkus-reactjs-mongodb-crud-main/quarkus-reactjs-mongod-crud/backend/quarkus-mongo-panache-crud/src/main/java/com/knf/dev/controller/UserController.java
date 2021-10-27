package com.knf.dev.controller;

import com.knf.dev.model.User;
import com.knf.dev.repository.UserRepository;
import io.quarkus.mongodb.panache.PanacheQuery;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/users")
public class UserController {

    private final UserRepository userRepository;

    @Inject
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GET
    public List<User> getUsers() {
        return userRepository.listAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser
            (@PathParam("id") String id) {
        return userRepository.findById(new ObjectId(id));
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void updateUser
            (@PathParam("id") String id, User user) {
        user.setId(new ObjectId(id));
        userRepository.update(user);
    }

    @POST
    public Response addUser(User user) {
        userRepository.persist(user);
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("/{id}")
    public void deleteUser(@PathParam("id") String id) {
        User user = userRepository.findById(new ObjectId(id));
        userRepository.delete(user);
    }
}