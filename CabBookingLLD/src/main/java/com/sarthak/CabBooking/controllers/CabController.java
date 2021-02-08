package com.sarthak.CabBooking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.sarthak.CabBooking.manager.CabManager;

@RestController
public class CabController {

	@Autowired
	private CabManager cabManager;
	
}
