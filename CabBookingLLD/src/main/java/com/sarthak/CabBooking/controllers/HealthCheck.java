package com.sarthak.CabBooking.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {
	
	@GetMapping("/health")
	public String checkHealth() {
		return "Server is Healthy";
	}
}
