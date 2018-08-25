package edu.jktheking.processflow.framework.core;

/**
 * Identifier for processor.
 */
public enum ProcessorId {
	UNKNOWN_ID(ProcessorType.UNKNOWN_TYPE);
	

	private final ProcessorType type;
	private final String id;

	ProcessorId(ProcessorType type) {
		this.type = type;
		this.id = String.format("%s:%s", type.name(), name());
	}

	public ProcessorType getType() {
		return type;
	}

	public String getId() {
		return id;
	}

}
