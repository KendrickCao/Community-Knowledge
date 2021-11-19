package com.community.client.repositories;

import com.community.client.models.Community;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityRepository extends CrudRepository<Community,Long> {
    //1. Save the community details
    Community save(Community community);

    //2. Retrieve
}
