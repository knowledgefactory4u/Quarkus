package com.knf.dev.demo.endpoint;

import com.knf.dev.demo.model.User;
import com.knf.dev.demo.repository.UserRepository;
import java.util.List;
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

@Path("/api/users")
public class UserEndpoint {

    @Inject
    UserRepository userResource;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUsers() {
        return userResource.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("id") Long id) {
        return userResource.findById(id);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateUser(User user) {
        userResource.update(user);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void addUser(User user) {
       userResource.insert(user);

    }

    @DELETE
    @Path("/{id}")
    public void deleteUser(@PathParam("id") Long id) {
        userResource.deleteById(id);
    }
}
 