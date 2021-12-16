package com.community.client.controllers;
import com.community.client.models.AdminAbout;
import com.community.client.models.Contact;
import com.community.client.services.AdminAboutService;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminAboutController {
    //DI for the Service Layer
    private final AdminAboutService adminAboutService;

    public AdminAboutController(AdminAboutService adminAboutService) {
        this.adminAboutService = adminAboutService;
    }
        //API end point will be defined here
        //1.End Point Is to Save the user's information and Enquires
        @PostMapping("/api/new-AdminAbout")
        public AdminAbout saveNewAdminAbout(@RequestBody AdminAbout adminAbout) {
            return adminAboutService.saveAdminAbout(adminAbout);

        }


}
