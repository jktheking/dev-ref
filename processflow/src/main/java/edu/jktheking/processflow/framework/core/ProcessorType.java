package edu.jktheking.processflow.framework.core;
/**
 * 
 * Enum representing the processorType.
 * */
public enum ProcessorType {

	//
	PRE_PROCESSOR(Processor.class),
	//
	POST_PROCESSOR(Processor.class),
	//
	CARDINAL_PROCESSOR(Processor.class),
	//
	UNKNOWN_TYPE(Processor.class);

	private final Class<?> processorInterface;

	private ProcessorType(Class<? extends Processor> processorInterface) {
		this.processorInterface = processorInterface;

	}

	public static ProcessorType valueOf(Class<? extends Processor> subTypeIntf) {
		for (ProcessorType superType : values()) {
			// no need to check against UNKNOWN
			if (superType.equals(ProcessorType.UNKNOWN_TYPE)) {
				continue;
			}
			if (superType.processorInterface.isAssignableFrom(subTypeIntf)) {
				return superType;
			}
		}
		throw new IllegalArgumentException(subTypeIntf.getName() + " is not a valid Processor subtype!");

	}

	public Class<?> getProcessorInterface() {
		return processorInterface;
	}

}
