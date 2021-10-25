package com.ryanfinken.PersonalBlog.data;

import com.ryanfinken.PersonalBlog.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    public User findByUsername(String username);
}
