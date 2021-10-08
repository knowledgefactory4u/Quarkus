package com.knf.dev.demo.controller

import com.knf.dev.demo.model.User
import com.knf.dev.demo.repository.UserRepository
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType


@Path("/api/users")
class UserController {
    @Inject
    var userRepository: UserRepository? = null

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getUsers(): kotlin.collections.List<User?>? {
        return userRepository!!.findAll()
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getUser(@PathParam("id") id: Long?): User? {
        return userRepository!!.findById(id!!)
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    fun updateUser(user: User?) {
        userRepository!!.update(user)
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun addUser(user: User?) {
        userRepository!!.insert(user)
    }

    @DELETE
    @Path("/{id}")
    fun deleteUser(@PathParam("id") id: Long?) {
        userRepository!!.deleteById(id!!)
    }
}