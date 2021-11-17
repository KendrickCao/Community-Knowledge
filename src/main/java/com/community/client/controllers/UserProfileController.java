package com.community.client.controllers;

import com.community.client.models.UserObject;
import com.community.client.models.UserProfile;
import com.community.client.requests.UserProfileRequest;
import com.community.client.services.UserObjectService;
import com.community.client.services.UserProfileService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserProfileController {
    private final UserObjectService userObjectService;
    private final UserProfileService userProfileService;

    public UserProfileController(UserObjectService userObjectService, UserProfileService userProfileService) {
        this.userObjectService = userObjectService;
        this.userProfileService = userProfileService;
    }

    @PutMapping("/api/update-profile/{userId}")
    private UserObject createProfile(@RequestBody UserProfileRequest userProfileRequest , @PathVariable Long userId){

        //steps

        //1 We want to get the UserObject by the ID which we have from the path variable
        UserObject userObject = userObjectService.getUserById(userId);
        //if user exists, then we create a new user profile object
        if(userObject != null){

            //If the profile exists, then load that profile otherwise create a new profile.
            UserProfile userProfile;
            //check if the userObject has a profile associated already
            UserProfile userProfileFound = userObject.getUserProfile();
            if(userProfileFound == null){
                userProfile = new UserProfile();
            }else{
                userProfile = userProfileFound;
            }

            //We set the user profile fields with the payload we have received
            userProfile.setAboutYourSelf(userProfileRequest.getAboutYourSelf());
            userProfile.setAchievements(userProfileRequest.getAchievements());
            userProfile.setHobbies(userProfileRequest.getHobbies());
            userProfile.setPhone(userProfileRequest.getPhone());
            userProfile.setQualifications(userProfileRequest.getQualifications());
            userProfile.setUserObject(userObject);
            userProfileService.saveProfile(userProfile);

            userObject.setName(userProfileRequest.getName());
            userObject.setEmail(userProfileRequest.getEmail());

            return userObjectService.saveUser(userObject);
        }else{
            return  null;
        }
    }
}
