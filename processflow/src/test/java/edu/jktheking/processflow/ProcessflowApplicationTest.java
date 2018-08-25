package edu.jktheking.processflow;

import org.junit.Before;
import org.junit.Test;

import edu.jktheking.processflow.framework.core.CompositeProcessorRegistryReader;
import edu.jktheking.processflow.framework.core.ProcessorContext;
import edu.jktheking.processflow.sample.ProcessorConfigurer;
import edu.jktheking.processflow.support.CompositeProcessorId;
import edu.jktheking.processflow.support.HandlerType;

public class ProcessflowApplicationTest {
	
	@Before
	public void configure() {
		ProcessorConfigurer.configureProcessors();
	}

	@Test
	public void test() {
		CompositeProcessorRegistryReader registryReader = ProcessorConfigurer.registry;
		
		ProcessorContext context = ProcessorContext.newProcessorContext(HandlerType.DEFAULT);
		
		registryReader.getCompositePreProcessor(CompositeProcessorId.ACTIVATION_PRE_PROCESSOR).process(context);
		registryReader.getCompositeCardinalProcessor(CompositeProcessorId.ACTIVATION_CARDINAL_PROCESSOR).process(context);
		registryReader.getCompositeCardinalProcessor(CompositeProcessorId.ACTIVATION_POST_PROCESSOR).process(context);
	}

}
