package com.community.client.controllers;

import com.community.client.models.UserObject;
import com.community.client.requests.LoginRequest;
import com.community.client.services.UserObjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Acts as a request handler to handle POST requests from users who are attempting
 * to login by comparing input with user account information stored within a database.
 */
@RestController
public class LoginController {

    //Dependency Injection of the User Object Service
    private UserObjectService userObjectService;

    public LoginController(UserObjectService userObjectService) {
        this.userObjectService = userObjectService;
    }

    /**
     * Compares the email and password from a user login attempt to emails and passwords
     * stored within the user_table database. If the email and password match an existing user,
     * login is successful and a cookie can be set using the information returned by this method.
     * @param loginRequest JSON containing email and password submitted from the frontend.
     * @return loggedInUser JSON containing user information from user_table database.
     */
    @PostMapping("/api/login-user")
    public Object loginUser(@RequestBody LoginRequest loginRequest){

        String emailFromLoginRequest = loginRequest.getEmail();
        String passwordFromLoginRequest = loginRequest.getPassword();

        UserObject loggedInUser = null;

        try {
            UserObject userObjectFound = userObjectService.getUserByEmail(emailFromLoginRequest);

            if (userObjectFound != null) {
                String passwordFromDB = userObjectFound.getPassword();
                if (passwordFromDB.equals(passwordFromLoginRequest)) {
                    loggedInUser = userObjectFound;
                }
            }
            return new ResponseEntity<>(loggedInUser, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
