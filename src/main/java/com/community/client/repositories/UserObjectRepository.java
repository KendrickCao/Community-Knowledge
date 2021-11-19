package com.community.client.repositories;

import com.community.client.models.UserObject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserObjectRepository extends CrudRepository<UserObject, Long> {
    //We override the methods here
    //1. Save the user
    UserObject save(UserObject userObject);

    //2. Retrieve all Users
    Set<UserObject> findAll();

    //3. Get a user by ID
    Optional<UserObject> findById(Long id);

    //4. Get a User by Email
    Optional<UserObject> findUserObjectByEmail(String email);


}
