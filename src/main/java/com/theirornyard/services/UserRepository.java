package com.theirornyard.services;

import com.theirornyard.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jonathandavidblack on 6/23/16.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    public User findByName(String name);
}
