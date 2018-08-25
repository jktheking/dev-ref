package edu.jktheking.processflow.sample;

import edu.jktheking.processflow.framework.core.AppState;
import edu.jktheking.processflow.framework.core.CardinalProcessor;
import edu.jktheking.processflow.framework.core.CompositeProcessor;
import edu.jktheking.processflow.framework.core.DefaultChainedCompositeProcessor;
import edu.jktheking.processflow.framework.core.DefaultCompositeProcessorRegistry;
import edu.jktheking.processflow.framework.core.ListProcessorCollectionBuilder;
import edu.jktheking.processflow.framework.core.PostProcessor;
import edu.jktheking.processflow.framework.core.PreProcessor;
import edu.jktheking.processflow.framework.core.ProcessorBuilder;
import edu.jktheking.processflow.support.CompositeProcessorId;
import edu.jktheking.processflow.support.HandlerType;
import edu.jktheking.processflow.support.ProcessorId;

public class ProcessorConfigurer {

	public static final DefaultCompositeProcessorRegistry registry = new DefaultCompositeProcessorRegistry();

	public static void configureProcessors() {

		// building pre-processor
		PreProcessor pre = new ProcessorBuilder<>(SamplePreProcessor.class, ProcessorId.SAMPLE_PRE_PROCESSOR)
				.withProcessorHandler(HandlerType.DEFAULT, new SamplePreProcessorHandlerA())
				.withProcessorHandler(HandlerType.HANDLERXXX, new SampleCardinalProcessorHandlerB())
				.withEscapeState(AppState.NEW_USER).build();

		// building post-processor
		PostProcessor post = new ProcessorBuilder<>(SamplePostProcessor.class, ProcessorId.SAMPLE_POST_PROCESSOR)
				.withProcessorHandler(HandlerType.DEFAULT, new SamplePostProcessorHandlerA()).build();

		// building cardinal processors
		CardinalProcessor cardinalA = new ProcessorBuilder<>(SampleCardinalProcessorA.class,
				ProcessorId.SAMPLE_CARDINAL_PROCESSORA)
				.withProcessorHandler(HandlerType.DEFAULT, new SampleCardinalProcessorHandlerA()).build();

		CardinalProcessor cardinalB = new ProcessorBuilder<>(SampleCardinalProcessorA.class,
				ProcessorId.SAMPLE_CARDINAL_PROCESSORB)
				.withProcessorHandler(HandlerType.DEFAULT, new SampleCardinalProcessorHandlerB()).build();

		
		CompositeProcessor<PreProcessor> compositePreProcessor = new DefaultChainedCompositeProcessor<>(
				CompositeProcessorId.ACTIVATION_PRE_PROCESSOR,
				new ListProcessorCollectionBuilder<PreProcessor>().withProcessor(pre).build());
		
		
		CompositeProcessor<CardinalProcessor> compositeCardinalProcessor = new DefaultChainedCompositeProcessor<>(
				CompositeProcessorId.ACTIVATION_CARDINAL_PROCESSOR,
				new ListProcessorCollectionBuilder<CardinalProcessor>().withProcessor(cardinalA).withProcessor(cardinalB).build());
		
		CompositeProcessor<PostProcessor> compositePostProcessor = new DefaultChainedCompositeProcessor<>(
				CompositeProcessorId.ACTIVATION_POST_PROCESSOR,
				new ListProcessorCollectionBuilder<PostProcessor>().withProcessor(post).build());

		// adding processors into respective composite processor to registry
		registry.registerCompositePreProcessor(compositePreProcessor);
		registry.registerCompositeCardinalProcessor(compositeCardinalProcessor);
		registry.registerCompositePostProcessor(compositePostProcessor);
	}
}
