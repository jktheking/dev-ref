package edu.jktheking.processflow.sample;

import java.util.Set;

import edu.jktheking.processflow.framework.core.AppState;
import edu.jktheking.processflow.framework.core.PreProcessor;
import edu.jktheking.processflow.framework.core.ProcessorBase;
import edu.jktheking.processflow.framework.core.ProcessorHandlerResolver;
import edu.jktheking.processflow.support.ProcessorId;

public class SamplePreProcessor extends ProcessorBase implements PreProcessor {

	protected SamplePreProcessor(ProcessorId processorId, ProcessorHandlerResolver processorHandlerResolver,
			Set<AppState> escapeStates) {
		super(processorId, processorHandlerResolver, escapeStates);

	}
}
