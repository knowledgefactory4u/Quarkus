package com.knf.dev.demo.controller;

import com.knf.dev.demo.entity.User;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.bson.types.ObjectId;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class UserController {


    @POST
    public Uni<Response> addUser(User user) {
        return user.persist().
                map(r -> Response.accepted().build());

    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<User> userList() {
        return User.streamAllUsers();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<User> findUserById(@PathParam("id") String id) {
        return User.findUserById(id);
    }

    @DELETE
    @Path("/{id}")
    public Uni<Boolean> deleteUserById(@PathParam("id") String id) {
        return User.deleteById(new ObjectId(id));
    }

    @PUT
    @Path("/{id}")
    public Uni<Response> updateUserById(@PathParam("id") String id,User user) {
        user.setId(new ObjectId(id));
        return user.update().
                map(r -> Response.accepted().build());
    }

}
