package com.knf.dev;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.quarkiverse.freemarker.TemplatePath;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/")
public class UserEndpoint {

    @Inject
    @TemplatePath("home.ftlh")
    Template home;

    @Inject
    @TemplatePath("create-update.ftlh")
    Template creatUpdate;

    @Inject
    UserResource userResource;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getAllUserView()
            throws TemplateException, IOException {
        List<User> users = userResource.getUsers();
        StringWriter stringWriter = new StringWriter();
        home.process(Map.of("users", users), stringWriter);
        return stringWriter.toString();
    }

    @GET
    @Path("/create")
    @Produces(MediaType.TEXT_HTML)
    public String createUserView()
            throws TemplateException, IOException {
        User user = new User();
        Map<String, Object> obj = new HashMap<>();
        StringWriter stringWriter = new StringWriter();
        obj.put("user", user);
        obj.put("isUpdate", false);
        creatUpdate.process(obj, stringWriter);
        return stringWriter.toString();
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/create")
    public String createUser
            (@FormParam("firstName") String firstName,
             @FormParam("lastName") String
                     lastName, @FormParam("email") String email)
            throws TemplateException, IOException {
        User usr = new User();
        usr.setEmail(email);
        usr.setFirstName(firstName);
        usr.setLastName(lastName);
        userResource.addUser(usr);
        return getAllUserView();
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/update/{id}")
    public String updateUser(@PathParam("id") Long id)
            throws TemplateException, IOException {
        User user = userResource.getUser(id);
        Map<String, Object> obj = new HashMap<>();
        StringWriter stringWriter = new StringWriter();
        obj.put("user", user);
        obj.put("isUpdate", true);
        creatUpdate.process(obj, stringWriter);
        return stringWriter.toString();
    }

    @POST
    @Path("/update/{id}")
    @Produces(MediaType.TEXT_HTML)
    public String createUser(@FormParam("firstName") String firstName,
                             @FormParam("lastName") String
                                     lastName, @FormParam("email")
                                         String email,
                             @PathParam("id") Long id)
            throws TemplateException, IOException {
        User usr = new User();
        usr.setEmail(email);
        usr.setFirstName(firstName);
        usr.setLastName(lastName);
        usr.setId(id);
        userResource.updateUser(usr);
        return getAllUserView();
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/delete/{id}")
    public String deleteUser(@PathParam("id") Long id)
            throws TemplateException, IOException {
        userResource.deleteUser(id);
        return getAllUserView();
    }
}