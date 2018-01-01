package com.davimeloni.eventapp.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.davimeloni.eventapp.models.Event;
import com.davimeloni.eventapp.models.User;
import com.davimeloni.eventapp.repository.UserRepository;
import com.davimeloni.eventapp.security.ImplementsUserDetailsService;
import com.davimeloni.eventapp.service.UserService;

@Controller
public class IndexController {
	
	@Autowired
	public UserRepository ur;
	@Autowired
	public UserService userService;

	@RequestMapping("/")
	public ModelAndView index() {

		ModelAndView mv = new ModelAndView("index");

		UserDetails userDetails = null;
		//UserDetails userDetails =
		//		(UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			userDetails = (UserDetails) auth.getPrincipal();
			mv.addObject("user", userDetails);
		}		

		System.out.println(userDetails);

		return mv;

	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView("register");
		mav.addObject("user", new User());
		
		return mav;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerUser (HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("user") User user) {
		
		
		//ur.save(user);
		System.out.println(user);
		userService.saveUser(user);
		
		return "redirect:/login";
	}

}
