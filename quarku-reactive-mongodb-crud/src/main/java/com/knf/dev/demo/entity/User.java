package com.knf.dev.demo.entity;

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.bson.types.ObjectId;

public class User extends ReactivePanacheMongoEntity {

    public ObjectId id;
    private String firstName;
    private String lastName;
    private String emailId;

    public User() {
    }

    public User(ObjectId id, String firstName,
                String lastName, String emailId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public static Multi<User> streamAllUsers() {
        return streamAll();
    }
    public static Uni<User> findUserById(String id) {
        return User.findById(new ObjectId(id));
    }
}
