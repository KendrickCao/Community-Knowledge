package com.community.client.repositories;
import com.community.client.models.AdminAbout;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminAboutRepository extends CrudRepository<AdminAbout,Long>{
    //This line of code below will save the user's input (example: queries and user details)
    AdminAboutRepository save(AdminAboutRepository adminAbout);
}
