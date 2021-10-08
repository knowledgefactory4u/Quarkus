package org.knf.dev.demo.exception;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CustomExceptionHandler
        implements ExceptionMapper<CustomException> {

    @ConfigProperty(name = "knowledgefactory.custom.error.msg.usernotfound")
    String userNotFound;

    @Override
    public Response toResponse(CustomException e) {

        if (e.getMessage().equalsIgnoreCase(userNotFound)) {
            return Response.status(Response.Status.NOT_FOUND).
                    entity(e.getMessage()).build();
        } else {

            return Response.status(Response.Status.BAD_REQUEST).
                    entity(e.getMessage()).build();
        }
    }
}
