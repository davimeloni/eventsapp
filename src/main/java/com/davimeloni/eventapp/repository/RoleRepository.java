package com.davimeloni.eventapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.davimeloni.eventapp.models.Role;

public interface RoleRepository extends CrudRepository<Role, String> {
	
	Role findByUserRole(String userRole);
	
}
