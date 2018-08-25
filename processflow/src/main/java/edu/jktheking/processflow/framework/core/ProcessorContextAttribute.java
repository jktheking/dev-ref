package edu.jktheking.processflow.framework.core;

/**
 * ProcessorContext Attribute key
 */
public enum ProcessorContextAttribute {

	;

	private String value;

	private ProcessorContextAttribute(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
