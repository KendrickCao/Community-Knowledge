package com.community.client.controllers;

import com.community.client.models.UserObject;
import com.community.client.services.UserObjectService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class UserObjectController {
    //Dependency Injection of user object service
    private final UserObjectService userObjectService;

    public UserObjectController(UserObjectService userObjectService) {
        this.userObjectService = userObjectService;
    }

    //end point to create a user
    @PostMapping("/api/add-user")
    private UserObject addUser(@RequestBody UserObject userObject){
        return userObjectService.saveUser(userObject);
    }

    ///end point to get all the users(NOT TO BE USED WITHOUT AUTHORIZATION)
    @GetMapping("/api/users")
    private Set<UserObject> getAllUsers(){
        return userObjectService.getAllUsers();
    }

    //Get a user by ID
    @GetMapping("/api/get-user/{id}")
    private UserObject getUserById(@PathVariable Long id){
        return userObjectService.getUserById(id);
    }

    //Get a User By Email
    @GetMapping("/api/get-user-email/{email}")
    private UserObject getUserByEmail(@PathVariable String email){
        return userObjectService.getUserByEmail(email);
    }
}
