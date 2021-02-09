package com.sarthak.CabBooking.manager;

import java.util.HashMap;
import java.util.Map;

import com.sarthak.CabBooking.model.Cab;
import com.sarthak.CabBooking.model.Rider;
import com.sarthak.CabBooking.model.Trip;
import com.sarthak.CabBooking.model.TripStatus;

public class TripManager {
	
	private int autoIncrementTripId = 1;
	private Map<Integer, Trip> tripMap = new HashMap<>();
	
	public Trip createNewTrip(String source, String destination, Rider rider, Cab cab) {
		Trip trip = new Trip();
		trip.setSourceCity(source);
		trip.setDestination(destination);
		trip.setRider(rider);
		trip.setCab(cab);
		trip.setStatus(TripStatus.IN_PROGRESS);
		trip.setId(autoIncrementTripId++);
		
		tripMap.put(trip.getId(), trip);
		
		return trip;
	}

	public Trip getTripById(int id) {
		return tripMap.get(id);
	}
	
	public void endTrip(Trip trip) {
		trip.setStatus(TripStatus.COMPLETED);
	}
}
