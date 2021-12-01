package com.community.client.controllers;

import com.community.client.models.Community;
import com.community.client.models.UserObject;
import com.community.client.services.CommunityService;
import com.community.client.services.UserObjectService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class CommunityController {
    private final CommunityService communityService;
    private final UserObjectService userObjectService;

    public CommunityController(CommunityService communityService,UserObjectService userObjectService) {
        this.communityService = communityService;
        this.userObjectService = userObjectService;
    }

    //end point to create/update a community information
    @PostMapping("/api/add-community/{userId}")
    private Community addCommunity(@RequestBody Community community, @PathVariable Long userId){
        Community savedCommunity =communityService.saveCommunity(community);
        UserObject userObject = userObjectService.getUserById(userId);
        Set<Community> communitySet = userObject.getCommunitySet();
        communitySet.add(savedCommunity);
        userObject.setCommunitySet(communitySet);
        //the user who create this community will automatically join this community
        userObjectService.saveUser(userObject);
        return savedCommunity;
    }

    ///This method is for joining the community
   @PutMapping("/api/update-community/userid/{userId}/community-id/{communityId}")
    private UserObject addUserToCommunity(@PathVariable Long userId, @PathVariable Long communityId){
        UserObject userObject = userObjectService.getUserById(userId);
        Community community = communityService.getCommunityById(communityId);
       Set<Community> communitySet = userObject.getCommunitySet();
       communitySet.add(community);
       userObject.setCommunitySet(communitySet);
        return userObjectService.saveUser(userObject);
    }

    //end point to get all communities
    @GetMapping("/api/communities")
    private Set<Community>  getAllCommunities(){
        return communityService.getAllCommunities();
    }

    @GetMapping("/api/get-community/{id}")
    private Community getCommunityById(@PathVariable Long id){
        return communityService.getCommunityById(id);
    }
}
