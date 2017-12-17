package com.davimeloni.eventapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.davimeloni.eventapp.models.Event;

public interface EventRepository extends CrudRepository<Event, String> {
	
	Event findByCode(long code);	
	
	
}
