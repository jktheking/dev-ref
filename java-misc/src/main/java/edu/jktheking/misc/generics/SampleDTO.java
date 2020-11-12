package edu.jktheking.misc.generics;

import java.util.ArrayList;
import java.util.List;

public class SampleDTO {

	private List<Vehicle> vehicleList = new ArrayList<>();
	
	@SuppressWarnings("unchecked")
	public <T extends List<? extends Vehicle>> T getVehicles(){
		return (T) vehicleList;
	} 
	
	public void setVehicles(List<? extends Vehicle> x){
		vehicleList.add(x.get(0));
	} 
	
}
