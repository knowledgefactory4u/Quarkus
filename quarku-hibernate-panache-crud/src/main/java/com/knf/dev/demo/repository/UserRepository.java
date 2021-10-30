package com.knf.dev.demo.repository;

import javax.enterprise.context.ApplicationScoped;
import com.knf.dev.demo.entity.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

}
