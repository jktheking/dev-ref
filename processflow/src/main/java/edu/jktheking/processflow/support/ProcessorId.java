package edu.jktheking.processflow.support;

import edu.jktheking.processflow.framework.core.ProcessorType;

/**
 * Identifier for processor.
 */
public enum ProcessorId {
	UNKNOWN_ID(ProcessorType.UNKNOWN_TYPE), 
	//
	SAMPLE_CARDINAL_PROCESSORA(
			ProcessorType.CARDINAL_PROCESSOR), 
	//
	SAMPLE_CARDINAL_PROCESSORB(ProcessorType.CARDINAL_PROCESSOR),
    //
	SAMPLE_PRE_PROCESSOR(ProcessorType.PRE_PROCESSOR), 
	//
	SAMPLE_POST_PROCESSOR(ProcessorType.POST_PROCESSOR);

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
