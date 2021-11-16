package com.community.client.repositories;

import com.community.client.models.UserObject;

import java.util.Optional;
import java.util.Set;

public interface UserRepository {
    //We override the methods here
    //1. Save the user
    UserObject save(UserObject userObject);

    //2. Retreive all Users
    Set<UserObject> findAll();

    //3. Get a user by ID
    Optional<UserObject> findById(Long id);

    //4. Get a User by Email
    Optional<UserObject> findUserObjectByEmail(String email);
}
