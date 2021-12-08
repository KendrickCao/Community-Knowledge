package com.community.client.repositories;

import com.community.client.models.Community;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface CommunityRepository extends CrudRepository<Community, Long> {
    // 1. Save the community details
    Community save(Community community);

    // 2. Retrieve all communities (help to show the dropdown list)
    Set<Community> findAll();

    // 3. Get one community by id
    Optional<Community> findCommunityById(Long id);

    @Query(value = "select * from community_table  where community_name like %?1%", nativeQuery = true)
    Set<Community> findAllByKeyword(@Param("keyword") String keyword);

    // 4. Get one community by Name for search function(do it later)
    // Optional<Community> findCommunityByName(String name);

}
