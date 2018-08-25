package edu.jktheking.processflow.framework.core;

/**
 * Represents the handler associated with each processor.
 * */
@FunctionalInterface
public interface ProcessorHandler {
	public void handle(ProcessorContext processorContext) throws ProcessorException;

}
