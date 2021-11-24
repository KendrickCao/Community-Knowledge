package com.community.client.controllers;

import com.community.client.models.Community;
import com.community.client.models.Project;
import com.community.client.services.CommunityService;
import com.community.client.services.ProjectService;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
public class ProjectController {
    private final ProjectService projectService;
    private final CommunityService communityService;

    public ProjectController(ProjectService projectService, CommunityService communityService) {
        this.projectService = projectService;
        this.communityService = communityService;
    }

    //end point to create/update a project(when create a project,the community is the Required field)
    @PostMapping("/api/add-project/communityId/{communityId}")
    public Project addProject(@RequestBody Project project,@PathVariable Long communityId){
        Community community = communityService.getCommunityById(communityId);
       //We will take the user as part of request payload
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


