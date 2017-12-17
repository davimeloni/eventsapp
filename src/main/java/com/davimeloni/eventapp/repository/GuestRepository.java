package com.davimeloni.eventapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.davimeloni.eventapp.models.Event;
import com.davimeloni.eventapp.models.Guest;


public interface GuestRepository extends CrudRepository<Guest, String> {
	
	Iterable<Guest> findByEvent(Event event);
	Guest findByGuestID(String guestID);

}
