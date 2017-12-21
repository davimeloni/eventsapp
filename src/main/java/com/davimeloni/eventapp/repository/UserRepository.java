package com.davimeloni.eventapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.davimeloni.eventapp.models.User;

public interface UserRepository extends CrudRepository<User, String> {
	
	User findByUsername(String username);

}
