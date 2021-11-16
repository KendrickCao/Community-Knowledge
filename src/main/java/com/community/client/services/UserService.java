package com.community.client.services;

import com.community.client.models.UserObject;
import com.community.client.repositories.UserRepository;

import java.util.Optional;
import java.util.Set;

public class UserService {


    public UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Implement the methods from repository
    //1. To save a User
    public UserObject saveUser(UserObject userObject) {
        return userRepository.save(userObject);
    }

    //2. To get all users
    public Set<UserObject> getAllUsers() {
        return userRepository.findAll();
    }

    //3. To get user by id
    public UserObject getUserById(Long id) {
        Optional<UserObject> userObjectOptional = userRepository.findById(id);
        if (userObjectOptional.isPresent()) {
            return userObjectOptional.get();
        } else {
            return null;
        }
    }

    //4. To get user by email
    public UserObject getUserByEmail(String email) {
        Optional<UserObject> userObjectOptional = userRepository.findUserObjectByEmail(email);
        if (userObjectOptional.isPresent()) {
            return userObjectOptional.get();
        } else {
            return null;
        }
    }

}
