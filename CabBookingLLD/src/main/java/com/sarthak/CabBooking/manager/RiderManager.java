package com.sarthak.CabBooking.manager;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sarthak.CabBooking.model.Rider;

@Service
public class RiderManager {
	
	private int autoIncrementRiderId = 1;
	private Map<Integer, Rider> riderMap = new HashMap<>();
	
	public Rider getRiderById(int id) throws Exception {
		if(riderMap.containsKey(id))
			return riderMap.get(id);
		
		throw new Exception("Rider not found");
	}
	
	public void addRider(Rider rider) {
		rider.setId(autoIncrementRiderId++);
		riderMap.put(rider.getId(), rider);
	}
	

}
