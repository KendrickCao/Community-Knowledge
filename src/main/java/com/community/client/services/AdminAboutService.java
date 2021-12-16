package com.community.client.services;
import com.community.client.models.AdminAbout;
import com.community.client.repositories.AdminAboutRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminAboutService {
    //DI the repository
    private final AdminAboutRepository adminAboutRepository;

    public AdminAboutService(AdminAboutRepository adminAboutRepository) {
        this.adminAboutRepository = adminAboutRepository;
    }


    //This is the actual method which we will use in the controller
    public AdminAbout saveAdminAbout(AdminAbout adminAbout){
        AdminAbout savedAdminAbout = adminAboutRepository.save(adminAbout);
        return savedAdminAbout;

    }


}
