package com.theirornyard.services;

import com.theirornyard.entities.Event;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jonathandavidblack on 6/23/16.
 */
public interface EventRepository extends CrudRepository<Event, Integer> {
}
