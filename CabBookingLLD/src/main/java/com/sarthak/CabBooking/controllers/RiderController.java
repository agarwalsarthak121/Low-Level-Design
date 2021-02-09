package com.sarthak.CabBooking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sarthak.CabBooking.manager.CabManager;
import com.sarthak.CabBooking.manager.RiderManager;
import com.sarthak.CabBooking.manager.TripManager;
import com.sarthak.CabBooking.model.Cab;
import com.sarthak.CabBooking.model.Rider;
import com.sarthak.CabBooking.model.Trip;

@RestController
public class RiderController {

	@Autowired
	private RiderManager riderManager;
	
	@Autowired
	private CabManager cabManager;
	
	@Autowired
	private TripManager tripManager;
	
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
	
	@PostMapping("/rider/{id}/book-cab")
	public Trip bookCab(@PathVariable("id") int id, @RequestParam("source") String source, @RequestParam("destination") String destination) throws Exception {
		
		Rider rider = riderManager.getRiderById(id);
		Cab cab = cabManager.getFirstAvailableCab(source);
		
		Trip trip = tripManager.createNewTrip(source, destination, rider, cab);
		
		cabManager.updateAvailability(cab, false);
		cabManager.updateCabLocation(cab, destination);
		
		return trip;
	}
	
	@PostMapping("/rider/end-trip/{id}")
	public ResponseEntity endTrip(@PathVariable("id") int tripId) throws Exception {
		Trip trip = tripManager.getTripById(tripId);
		
		tripManager.endTrip(trip);
		
		cabManager.updateAvailability(trip.getCab(), true);
		
		return ResponseEntity.ok("Trip Sucessfully Completed");
		
	}
	
}
