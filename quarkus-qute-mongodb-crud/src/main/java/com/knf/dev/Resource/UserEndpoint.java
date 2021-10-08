package com.knf.dev.Resource;

import com.knf.dev.model.User;
import com.knf.dev.repository.UserRepository;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateException;
import io.quarkus.qute.TemplateInstance;
import org.bson.types.ObjectId;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/")
public class UserEndpoint {

    private final UserRepository userRepository;

    @Inject
    Template home;

    @Inject
    Template createupdate;

    @Inject
    public UserEndpoint(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getAllUserView()
            throws TemplateException {
        List<User> users = userRepository.listAll();
        return home.data(Map.of("users", users));
    }

    @GET
    @Path("/create")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance createUserView()
            throws TemplateException, IOException {
        User user = new User();
        Map<String, Object> obj = new HashMap<>();
        obj.put("user", user);
        obj.put("isUpdate", false);
        return createupdate.data(obj);
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/create")
    public TemplateInstance createUser
            (@FormParam("firstName") String firstName,
             @FormParam("lastName") String lastName,
             @FormParam("emailId") String emailId)
            throws TemplateException {
        User usr = new User();
        usr.setEmailId(emailId);
        usr.setFirstName(firstName);
        usr.setLastName(lastName);
        userRepository.persist(usr);
        return getAllUserView();
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/update/{id}")
    public TemplateInstance updateUser
            (@PathParam("id") String id)
            throws TemplateException {
        User user = userRepository.findById(new ObjectId(id));
        Map<String, Object> obj = new HashMap<>();
        obj.put("user", user);
        obj.put("isUpdate", true);
        return createupdate.data(obj);
    }

    @POST
    @Path("/update/{id}")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance updateUser
            (@FormParam("firstName") String firstName,
             @FormParam("lastName") String lastName,
             @FormParam("emailId") String emailId,
             @PathParam("id") String id)
            throws TemplateException {
        User usr = new User();
        usr.setEmailId(emailId);
        usr.setFirstName(firstName);
        usr.setLastName(lastName);
        usr.setId(new ObjectId(id));
        userRepository.update(usr);
        return getAllUserView();
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/delete/{id}")
    public TemplateInstance deleteUser(@PathParam("id")
                                               String id)
            throws TemplateException, IOException {
        User user = userRepository.findById(new ObjectId(id));
        userRepository.delete(user);
        return getAllUserView();
    }
}