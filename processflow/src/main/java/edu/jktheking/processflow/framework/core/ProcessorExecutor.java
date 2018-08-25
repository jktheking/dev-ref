package edu.jktheking.processflow.framework.core;

import edu.jktheking.processflow.utils.CollectionUtils;


/**
 * Single processor execution-anvil to execute the unit work defined by given processor. This class provides the
 * opportunity to  processor-framework's client to provide the custom  execution for each
 * processor contained in {@code CompositeProcessor}.
 * 
 */
@FunctionalInterface
public interface ProcessorExecutor<P extends Processor> {

	void execute(P processor, ProcessorContext context) throws RuntimeProcessorException;

	default boolean checkForEscapeStates(final P processor, final ProcessorContext context) {

		if (CollectionUtils.isNotEmpty(processor.escapeStates())) {
			
			for (AppState escapeState : processor.escapeStates()) {

				if (context.getAppStates().contains(escapeState)) {
					// TODO: logger that escape state found
					return true;
				}
			}
		}

		return false;
	}

}
