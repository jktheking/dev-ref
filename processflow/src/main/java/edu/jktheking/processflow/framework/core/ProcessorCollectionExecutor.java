package edu.jktheking.processflow.framework.core;

/**
 * Composite processor execution-anvil to execute the underlying {@code ProcessorCollection}. This class provides the
 * opportunity to  processor-framework's client to provide the custom  execution for 
 * processors represented by {@code CompositeProcessor} API.
 * 
 */
@FunctionalInterface
public interface ProcessorCollectionExecutor<C extends ProcessorCollection<? extends Processor>> {

	void execute(C processorCollection, ProcessorContext context) throws RuntimeProcessorException;

}
