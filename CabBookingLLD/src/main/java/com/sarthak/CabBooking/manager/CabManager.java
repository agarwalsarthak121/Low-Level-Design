package com.sarthak.CabBooking.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sarthak.CabBooking.model.Cab;

@Service
public class CabManager {
	Map<String, List<Cab>> cabRepo = new HashMap<>();
	
	public void addCab(Cab cab) {
		if(cabRepo.containsKey(cab.getCity()))
			cabRepo.get(cab.getCity()).add(cab);
		else
		{
			List<Cab> lst = new ArrayList<Cab>();
			lst.add(cab);
			cabRepo.put(cab.getCity(), lst);
		}
	}
	
	public Cab getFirstAvailableCab(String city) {
		boolean isDriverAvailable = cabRepo.get(city).stream().anyMatch(cab -> cab.isAvailable());
		
		if(isDriverAvailable)
			return cabRepo.get(city).stream().filter(cab -> cab.isAvailable()).findFirst().get();
		
		return null;
	}
	
	
	
}
