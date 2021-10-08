package com.knf.dev.repository;

import com.knf.dev.model.User;
import org.bson.Document;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User saveUser(User user);

    User updateUser(String id, User user);

    List<User> findAll();

    Optional<User> findUserById(String id);

    void deleteUserById(String id);
}