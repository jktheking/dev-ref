package edu.jktheking.processflow.sample;

import edu.jktheking.processflow.framework.core.ProcessorContext;
import edu.jktheking.processflow.framework.core.ProcessorException;
import edu.jktheking.processflow.framework.core.ProcessorHandler;

public class SampleCardinalProcessorHandlerA implements ProcessorHandler {

	@Override
	public void handle(ProcessorContext processorContext) throws ProcessorException {
		System.out.println("!Inside" +SampleCardinalProcessorHandlerA.class.getSimpleName());

	}

}
