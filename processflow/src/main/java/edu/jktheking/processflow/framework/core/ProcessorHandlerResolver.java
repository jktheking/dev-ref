package edu.jktheking.processflow.framework.core;

import edu.jktheking.processflow.support.HandlerType;
import edu.jktheking.processflow.support.KeyValueRegistryReader;

/**
 * Resolves the handler associated with each processor.
 * 
 * */
public interface ProcessorHandlerResolver {

	KeyValueRegistryReader<HandlerType,ProcessorHandler> processorHandlerRegistry();

	default ProcessorHandler resolveProcessorHandler(ProcessorContext context) throws RuntimeProcessorException{
		KeyValueRegistryReader<HandlerType,ProcessorHandler> registry = processorHandlerRegistry();
		return registry.find(context.getHandlerType())
				.orElse(registry.get(HandlerType.DEFAULT));

	}

}
