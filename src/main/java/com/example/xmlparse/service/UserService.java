package com.example.xmlparse.service;

import com.example.xmlparse.model.user.impl.User;
import com.example.xmlparse.repository.jpa.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Has the role of containing all the registered users and execute simple operations like:
 * - adding user;
 * - searching for user
 * - getting all users
 * - etc.
 */
@AllArgsConstructor
@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    /**
     * Adds a user to the user repository if username is not taken.
     *
     * @param user Unique identifier for the user.
     * @return Message explaining the action that was taken.
     */
    public String addUser(User user) {
        this.userRepository.saveAndFlush(user);

        return "User with username " + user.getUsername() + " was created.";
    }

    /**
     * Searches for a user by username.
     *
     * @param username String representation of the user's name.
     * @return if found returns the found User otherwise - null.
     */
    public User getUser(String username) {
        return this.userRepository.findByUsername(username).orElse(null);
    }

    /**
     * @return Gets all the users in the repository.
     */
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }
}
