package com.sarthak.CabBooking.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sarthak.CabBooking.exception.CabNotAvailableException;
import com.sarthak.CabBooking.exception.CabNotFoundException;
import com.sarthak.CabBooking.model.Cab;

@Service
public class CabManager {
	private int autoIncrementId = 1;
	Map<String, List<Cab>> cabRepo = new HashMap<>();
	
	public void addCab(Cab cab) {
		
		cab.setId(autoIncrementId++);
		
		if(cabRepo.containsKey(cab.getCity()))
			cabRepo.get(cab.getCity()).add(cab);
		else
		{
			List<Cab> lst = new ArrayList<Cab>();
			lst.add(cab);
			cabRepo.put(cab.getCity(), lst);
		}
	}
	
	public Cab getFirstAvailableCab(String city) throws CabNotFoundException {
		boolean isDriverAvailable = cabRepo.get(city).stream().anyMatch(cab -> cab.isAvailable());
		
		if(isDriverAvailable)
			return cabRepo.get(city).stream().filter(cab -> cab.isAvailable()).findFirst().get();
		
		throw new CabNotFoundException("Cab not found");
	}
	
	public void updateAvailability(Cab cab, boolean isAvailable) {
		cab.setAvailable(isAvailable);
	}
	
	public void updateCabLocation(Cab cab, String city) {
		cab.setCity(city);
	}
	
	public Cab getCabById(int id) throws CabNotFoundException {
		Cab cab = null;
		for(Map.Entry<String,List<Cab>> map : cabRepo.entrySet()) {
			List<Cab> cabList = map.getValue();
			cab = cabList.stream().filter(cabItem -> cabItem.getId() == id).findFirst().get();
		}
		
		if(cab == null)
			throw new CabNotAvailableException("Cab not Available for this city");
		
		return cab;
	}
	
}
