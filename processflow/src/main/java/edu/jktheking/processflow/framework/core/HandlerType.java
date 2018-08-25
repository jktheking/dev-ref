package edu.jktheking.processflow.framework.core;

import java.util.HashMap;
import java.util.Map;

import edu.jktheking.processflow.utils.StringUtils;


public enum HandlerType {

	DEFAULT("default"), HANDLERXXX("handlerxxx");

	private final String typeString;

	private HandlerType(String typeString) {
		this.typeString = typeString;
	}
	
	public final String getTypeString() {
		return typeString;
	}
	

	private static Map<String, HandlerType> typeStringMap = new HashMap<>();
	
	static {
		for (HandlerType operator : values()) {
			typeStringMap.put(operator.typeString, operator);
		}
	}

	public static HandlerType valueOfTypeString(String name) {
		if (StringUtils.isNotBlank(name)) {
			HandlerType ret =  typeStringMap.get(name.toLowerCase().trim());
			if (ret == null) {
				throw new IllegalArgumentException(name + " is not a valid Handler!");
			}
			return ret;
		}
		return null;
	}

}
