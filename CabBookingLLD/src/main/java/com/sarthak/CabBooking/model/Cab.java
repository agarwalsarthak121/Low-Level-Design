package com.sarthak.CabBooking.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cab {

	private int id;
	private String name;
	private String city;
	private boolean isAvailable;
}
