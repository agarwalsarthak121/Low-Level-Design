package com.sarthak.CabBooking.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Trip {
	private int id;
	private Cab cab;
	private Rider rider;
	private String sourceCity;
	private String destination;
	private TripStatus status;
}
