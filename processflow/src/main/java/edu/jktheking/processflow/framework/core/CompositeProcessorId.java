package edu.jktheking.processflow.framework.core;

/**
 * Identifier for composite processor.
 */
public enum CompositeProcessorId {

	ACTIVATION_PRE_PROCESSOR(ProcessorType.PRE_PROCESSOR),
	//
	ACTIVATION_POST_PROCESSOR(ProcessorType.POST_PROCESSOR),
	//
	ACTIVATION_CARDINAL_PROCESSOR(ProcessorType.CARDINAL_PROCESSOR),

	// can have other processors composite id
	;

	private final ProcessorType type;
	private final String id;

	CompositeProcessorId(ProcessorType type) {
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
