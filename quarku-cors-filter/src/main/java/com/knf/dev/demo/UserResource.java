package com.knf.dev.demo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/users")
public class UserResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response hello() {
		return Response.ok("Congratulations! CORS enabled").
		     build();
	}

}