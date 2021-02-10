package com.sarthak.CabBooking.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sarthak.CabBooking.exception.TripNotFoundException;
import com.sarthak.CabBooking.manager.CabManager;
import com.sarthak.CabBooking.manager.TripManager;
import com.sarthak.CabBooking.model.Trip;

@RestController
public class TripController {

	@Autowired
	private TripManager tripManager;
	
	@Autowired
	private CabManager cabManager;

	@PostMapping("/trip/{id}/end")
	public ResponseEntity<String> endTrip(@PathVariable("id") int tripId) throws TripNotFoundException {
		Trip trip = tripManager.getTripById(tripId);
		
		tripManager.endTrip(trip);
		
		cabManager.updateAvailability(trip.getCab(), true);
		
		return ResponseEntity.ok("Trip Sucessfully Completed");
		
	}
	
	@GetMapping("/trip/{id}")
	public ResponseEntity<Trip> getTripInfo(@PathVariable("id") int tripId) throws TripNotFoundException {
		Trip trip = tripManager.getTripById(tripId);
		
		return new ResponseEntity<Trip>(trip, HttpStatus.OK);
	}
	
	@GetMapping("/trip/rider/{id}")
	public ResponseEntity<List<Trip>> getTripHistoryForRider(@PathVariable("id") int riderId){
		return new ResponseEntity<List<Trip>>(tripManager.getTripsByRider(riderId), HttpStatus.OK);
	}
	
	@GetMapping("/trip/cab/{id}")
	public ResponseEntity<List<Trip>> getTripHistoryForCab(@PathVariable("id") int cabId){
		return new ResponseEntity<List<Trip>>(tripManager.getTripsByCab(cabId) ,HttpStatus.OK);
	}
	
	
}
