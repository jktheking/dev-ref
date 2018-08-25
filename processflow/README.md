# processflow framewok
This is a small processflow framework. As of now it has been implemented for processes to be executed in chain or pipeline.
Each process represents unit of work.


Refer the sample package: "edu.jktheking.processflow.sample"

How to configure unit of work in the framework:
===============================================

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


How to use: refer the test package
========== 
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

