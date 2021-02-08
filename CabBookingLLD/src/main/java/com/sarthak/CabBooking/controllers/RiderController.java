package com.sarthak.CabBooking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sarthak.CabBooking.manager.RiderManager;
import com.sarthak.CabBooking.model.Rider;

@RestController
public class RiderController {

	@Autowired
	private RiderManager riderManager;
	
	@PostMapping("/rider/register")
	public ResponseEntity registerRider(@RequestParam("name") String name) {
		Rider rider = new Rider();
		rider.setName(name);
		
		riderManager.addRider(rider);
		
		return ResponseEntity.ok("Rider Registered");
	}
	
	@GetMapping("/rider/{id}")
	public Rider getRiderById(@PathVariable("id") int id) throws Exception {
		return riderManager.getRiderById(id);
	}
	
}
