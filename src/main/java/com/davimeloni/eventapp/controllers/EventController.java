package com.davimeloni.eventapp.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.davimeloni.eventapp.models.Event;
import com.davimeloni.eventapp.models.Guest;
import com.davimeloni.eventapp.repository.EventRepository;
import com.davimeloni.eventapp.repository.GuestRepository;

@Controller
public class EventController {
	
	@Autowired
	private EventRepository er;
	
	@Autowired
	private GuestRepository gr;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index() {
		return "redirect:/events";
	}

	@RequestMapping(value="/registerEvent", method=RequestMethod.GET)
	public String form() {
		return "event/formEvent";
	}
	
	@RequestMapping(value="/registerEvent", method=RequestMethod.POST)
	public String form(@Valid Event event, BindingResult result, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			attributes.addFlashAttribute("message", "Please verify the fields");
			return "redirect:/registerEvent";
		} 
		
		attributes.addFlashAttribute("message", "Event added successfully");
		er.save(event);
		
		return "redirect:/registerEvent";
	}
	
	@RequestMapping("/deleteevent")
	public String deleteEvent(long code) {
		
		Event event = er.findByCode(code);
		er.delete(event);
		
		return "redirect:/events";
	}
	
	@RequestMapping("/events")
	public ModelAndView EventList() {
		
		ModelAndView mv = new ModelAndView("index");
		Iterable<Event> events = er.findAll();
		
		mv.addObject("events", events);
		
		return mv;
	}
	
	@RequestMapping(value="/{code}", method=RequestMethod.GET)
	public ModelAndView eventDetails(@PathVariable("code") long code) {
		Event event = er.findByCode(code);
		ModelAndView mv = new ModelAndView("event/eventdetails");
		
		mv.addObject("event", event);
		
		Iterable<Guest> guests = gr.findByEvent(event);
		
		mv.addObject("guests", guests);
		
		return mv;
		
	}
	
	@RequestMapping(value="/{code}", method=RequestMethod.POST)
	public String addGuest(@PathVariable("code") long code, @Valid Guest guest, BindingResult result, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			attributes.addFlashAttribute("message", "Please verify the fields");
			return "redirect:/{code}";
		} 
		
		Event event = er.findByCode(code);
		guest.setEvent(event);
	
		gr.save(guest);
		
		attributes.addFlashAttribute("message", "Guest added successfully");
		
		return "redirect:/{code}";
		
	}
	
	@RequestMapping("/deleteguest")
	public String deleteGuest(String guestID) {
		
		Guest guest = gr.findByGuestID(guestID);
		gr.delete(guest);
		
		Event event = guest.getEvent();
		long eventCode = event.getCode();
		
		String codeString = "" + eventCode;
		
		return "redirect:/" + codeString;
		
	}
	
}
