package com.sarthak.CabBooking.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sarthak.CabBooking.model.Cab;
import com.sarthak.CabBooking.model.Rider;
import com.sarthak.CabBooking.model.Trip;
import com.sarthak.CabBooking.model.TripStatus;

@Service
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

	public Trip getTripById(int id) throws Exception {
		if(tripMap.containsKey(id))
			return tripMap.get(id);
		
		throw new Exception("Trip not found");
	}
	
	public void endTrip(Trip trip) {
		trip.setStatus(TripStatus.COMPLETED);
	}
	
	public List<Trip> getTripsByRider(int riderId){
		
		List<Trip> trips = new ArrayList<Trip>();
		tripMap.entrySet() 
		          .stream() 
		          .filter(map -> map.getValue().getRider().getId() == riderId)
		          .forEach(map -> trips.add(map.getValue()));
		
		return trips;
	}
	
	public List<Trip> getTripsByCab(int cabId) {

		List<Trip> trips = new ArrayList<Trip>();
		tripMap.entrySet().stream().filter(map -> map.getValue().getCab().getId() == cabId)
				.forEach(map -> trips.add(map.getValue()));

		return trips;
	}
}
