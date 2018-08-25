package edu.jktheking.processflow.sample;

import java.util.Set;

import edu.jktheking.processflow.framework.core.AppState;
import edu.jktheking.processflow.framework.core.CardinalProcessor;
import edu.jktheking.processflow.framework.core.ProcessorBase;
import edu.jktheking.processflow.framework.core.ProcessorHandlerResolver;
import edu.jktheking.processflow.support.ProcessorId;

public class SampleCardinalProcessorA extends ProcessorBase implements CardinalProcessor {

	protected SampleCardinalProcessorA(ProcessorId processorId, ProcessorHandlerResolver processorHandlerResolver,
			Set<AppState> escapeStates) {
		super(processorId, processorHandlerResolver, escapeStates);

	}
}
