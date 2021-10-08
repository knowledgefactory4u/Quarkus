package org.knf.dev.demo.controller;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.knf.dev.demo.data.User;
import org.knf.dev.demo.data.UserData;
import org.knf.dev.demo.exception.CustomException;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/api/v1")
public class UserController {

    @ConfigProperty(name = "knowledgefactory.custom.error.msg.badrequest.numeric")
    String idNumericErrorMsg;

    @ConfigProperty(name = "knowledgefactory.custom.error.msg.usernotfound")
    String userNotFound;

    @Inject
    UserData userData;

    @GET
    @Path("/users/{id}")
    public Response findUserById(@PathParam("id") String id)
            throws CustomException {
        Long user_id = null;
        try {
            user_id = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new CustomException(idNumericErrorMsg);
        }
        User user = userData.getUserById(user_id);
        if (user == null) {
            throw new CustomException(userNotFound);
        }
        return Response.ok().entity(userData.getUserById(user_id)).build();
    }
}