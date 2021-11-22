package com.community.client.repositories;

import com.community.client.models.Event;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface EventRepository extends CrudRepository <Event, Long> {

    Event save(Event event);

    Set<Event> findAll();

    Optional<Event> findEventById (Long Id);

}
