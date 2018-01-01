package com.davimeloni.eventapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class EventappApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventappApplication.class, args);
		
		System.out.println(new BCryptPasswordEncoder().encode("123"));
		
	}
}
