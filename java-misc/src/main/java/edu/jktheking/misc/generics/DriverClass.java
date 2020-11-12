package edu.jktheking.misc.generics;

import java.util.List;

public class DriverClass {

	
	public static void main(String ...strings) {
		
		SampleDTO dto = new SampleDTO();
		List<Car> cars = dto.getVehicles();
		dto.setVehicles(cars);
		
	}
}
