package com.sarthak.CabBooking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sarthak.CabBooking.manager.CabManager;
import com.sarthak.CabBooking.model.Cab;

@RestController
public class CabController {

	@Autowired
	private CabManager cabManager;
	
	@PostMapping("cab/register")
	public ResponseEntity registerCab(@RequestParam(name = "name") String name, @RequestParam(name = "city") String city) throws Exception
	{
		Cab cab = new Cab();
		cab.setCity(city);
		cab.setName(name);
		cab.setAvailable(true);
		
		cabManager.addCab(cab);
				
		return ResponseEntity.ok("Cab Registered");
	}
	
	@GetMapping("/cab/{cabId}")
	public Cab getCabInfoById(@PathVariable("cabId") int id) throws Exception {
		return cabManager.getCabById(id);
	}
}
