package edu.jktheking.processflow.framework.core;

/**
 * 
 * Provides the interface to get the composite processor from  processor registry.
 * */
public interface CompositeProcessorRegistryReader {

	
	<C extends CompositeProcessor<? extends PreProcessor>> C getCompositePreProcessor(CompositeProcessorId id);

	<C extends CompositeProcessor<? extends PostProcessor>> C getCompositePostProcessor(CompositeProcessorId id);

	<C extends CompositeProcessor<? extends CardinalProcessor>> C getCompositeCardinalProcessor(
			CompositeProcessorId id);
}
