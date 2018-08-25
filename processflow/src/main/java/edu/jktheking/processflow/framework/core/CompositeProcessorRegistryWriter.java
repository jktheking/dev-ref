package edu.jktheking.processflow.framework.core;

/**
 * 
 * Provides the interface to register the composite processor in  processor registry.
 * */
public interface CompositeProcessorRegistryWriter {

	void registerCompositePreProcessor(CompositeProcessor<? extends PreProcessor> value)
			throws ProcessorBootstrapException;

	void registerCompositePostProcessor(CompositeProcessor<? extends PostProcessor> value)
			throws ProcessorBootstrapException;

	void registerCompositeCardinalProcessor(CompositeProcessor<? extends CardinalProcessor> value)
			throws ProcessorBootstrapException;

}
