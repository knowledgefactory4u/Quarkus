package com.knf.dev.demo.controller
import com.knf.dev.demo.entity.User
import com.knf.dev.demo.service.UserService
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/api/users")
class UserController {
    @Inject
    var userResource: UserService? = null

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getUsers(): kotlin.collections.List<User?>? {
        return userResource?.getUsers()
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getUser(@PathParam("id") id: Long?): User? {
        return userResource?.getUser(id)
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    fun updateUser(@PathParam("id") id: Long?, user: User?) {
        userResource?.updateUser(id, user!!)
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun addUser(user: User?): User? {
        return userResource?.addUser(user)
    }

    @DELETE
    @Path("/{id}")
    fun deleteUser(@PathParam("id") id: Long?) {
        userResource?.deleteUser(id)
    }
}