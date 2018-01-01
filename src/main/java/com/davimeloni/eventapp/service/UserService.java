package com.davimeloni.eventapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.davimeloni.eventapp.models.Role;
import com.davimeloni.eventapp.models.User;
import com.davimeloni.eventapp.repository.RoleRepository;
import com.davimeloni.eventapp.repository.UserRepository;

@Service("userService")
public class UserService {

	@Autowired
	private UserRepository ur;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private RoleRepository rr;

	public void saveUser(User user) {

		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

		Role userRole = rr.findByUserRole("ROLE_USER");

		List<Role> roles = new ArrayList<>();
		roles.add(userRole);

		user.setRoles(roles);

		ur.save(user);
		
	}

}
