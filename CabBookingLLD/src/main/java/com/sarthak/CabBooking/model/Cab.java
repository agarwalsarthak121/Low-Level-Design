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
	
	@Override
	  public String toString() {
	    return "Cab{" +
	        "id='" + id + '\'' +
	        ", driverName='" + name + '\'' +
	        ", currentLocation=" + city +
	        ", isAvailable=" + isAvailable +
	        '}';
	  }
}
