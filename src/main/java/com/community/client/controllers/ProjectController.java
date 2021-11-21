package com.community.client.controllers;

import com.community.client.models.Community;
import com.community.client.models.Project;
import com.community.client.models.UserObject;
import com.community.client.services.CommunityService;
import com.community.client.services.ProjectService;
import com.community.client.services.UserObjectService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class ProjectController {
    private final ProjectService projectService;
    private final CommunityService communityService;
    private final UserObjectService userObjectService;

    public ProjectController(ProjectService projectService, CommunityService communityService, UserObjectService userObjectService) {
        this.projectService = projectService;
        this.communityService = communityService;
        this.userObjectService = userObjectService;
    }

    //end point to create/update a project(when create a project,the community is the Required field)
    @PostMapping("/api/add-project/{userId}/{communityId}")
    private Project addProject(@RequestBody Project project, @PathVariable Long userId,@PathVariable Long communityId){
        Community community = communityService.getCommunityById(communityId);
        //save userId from url to creatorUserId
        project.setCreatorUserId(userId);
        project.setCommunity(community);
        Project savedProject = projectService.saveProject(project);
        Set<Project> projectSet = community.getProjectSet();
        projectSet.add(savedProject);
        community.setProjectSet(projectSet);
        communityService.saveCommunity(community);
        return savedProject;
    }

    //end point to get all project
    @GetMapping("/api/projects")
    private Set<Project> getAllProjects(){
        return projectService.getAllProjects();
    }

    //end point to get one project by id
    @GetMapping("/api/get-project/{id}")
    private Project getProjectById(@PathVariable Long id){
        return projectService.getProjectById(id);
    }



}


