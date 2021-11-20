package com.community.client.controllers;

import com.community.client.models.UserObject;
import com.community.client.requests.LoginRequest;
import com.community.client.services.UserObjectService;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    //Dependency Injection of the User Object Service
    private UserObjectService userObjectService;

    public LoginController(UserObjectService userObjectService) {
        this.userObjectService = userObjectService;
    }

    @PostMapping("/api/login-user")
    public UserObject loginUser(@RequestBody LoginRequest loginRequest){

        String emailFromLoginRequest = loginRequest.getEmail();
        String passwordFromLoginRequest = loginRequest.getPassword();

        UserObject loggedInUser = null;
        UserObject userObjectFound = userObjectService.getUserByEmail(emailFromLoginRequest);

        if (userObjectFound != null){
            String passwordFromDB = userObjectFound.getPassword();
            if (passwordFromDB.equals(passwordFromLoginRequest)){
                loggedInUser = userObjectFound;
            }
        }
       return loggedInUser;
    }
}
