package edu.jktheking.processflow.framework.core;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Optional;
import java.util.Set;

import edu.jktheking.processflow.support.ProcessorId;

/**
 * It is a processor 'unit' for representing single responsibility.
 */
@FunctionalInterface
public interface Processor {
	
	/**
	 * A identifier for this processor.
	 */
	default ProcessorId id() {
		return ProcessorId.UNKNOWN_ID;
	}

	/**
	 * 
	 * This is the functional method of functional interface processor.
	 */
	void process(ProcessorContext context) throws RuntimeProcessorException;

	/**
	 * Plain Vanilla processor will not have any escape state.
	 * 
	 */
	default Set<AppState> escapeStates() {
		return Collections.unmodifiableSet(EnumSet.noneOf(AppState.class)) ;
	}

	/**
	 * Plain Vanilla processor will not have ProcessorHandlerResolver. One
	 * should override {@code  processorHandlerResolver()} method, if the
	 * processor supports the ProcessorHandlerResolver strategy.
	 */
	default Optional<ProcessorHandlerResolver> processorHandlerResolver() {
		return Optional.empty();
	}


}
