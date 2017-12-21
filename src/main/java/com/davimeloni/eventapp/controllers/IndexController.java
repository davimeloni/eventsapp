package com.davimeloni.eventapp.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.davimeloni.eventapp.models.Event;
import com.davimeloni.eventapp.models.User;

@Controller
public class IndexController {
	
	@RequestMapping("/")
	public ModelAndView index() {
		
		ModelAndView mv = new ModelAndView("index");
		
		User user = new User();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			user = (User) auth.getPrincipal();
			mv.addObject("user", user);
		}
		
		System.out.println(user);
		
		return mv;

	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}

}
