package edu.jktheking.misc;

import java.lang.reflect.Field;

/**
 * How to get and set private static  field using Java reflection
 * */
public class ReflectEnum {
	;
	private static final String NAME = "Jitendra";
	
	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		
		Field field = ReflectEnum.class.getDeclaredField("NAME");
		field.setAccessible(true); // Suppress Java language access checking
		 
		
		 
		// Get value
		String fieldValue = (String) field.get(null);
		System.out.println(fieldValue); // -> false
		 
		// Set value
		field.set(null, "KING");
		System.out.println(ReflectEnum.NAME); // -> true
		
		
	}

}
