package edu.jktheking.processflow.framework.core;


import edu.jktheking.processflow.support.CompositeProcessorId;
import edu.jktheking.processflow.support.EnumMapRegistry;
import edu.jktheking.processflow.utils.Assert;


/**
 * Default implementation for {@code CompositeProcessorRegistryReader} and {@code CompositeProcessorRegistryWriter}
 * 
 * */
public class DefaultCompositeProcessorRegistry
implements CompositeProcessorRegistryReader, CompositeProcessorRegistryWriter {

	private final EnumMapRegistry<CompositeProcessorId, ? super CompositeProcessor<? extends Processor>> compositeRegistry = new EnumMapRegistry<>(
			CompositeProcessorId.class);


	@SuppressWarnings("unchecked")
	@Override
	public <C extends CompositeProcessor<? extends PreProcessor>> C getCompositePreProcessor(CompositeProcessorId id) {
		assertCompositeProcessorId(id);
		return (C) compositeRegistry.get(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <C extends CompositeProcessor<? extends PostProcessor>> C getCompositePostProcessor(
			CompositeProcessorId id) {
		assertCompositeProcessorId(id);
		return (C) compositeRegistry.get(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <C extends CompositeProcessor<? extends CardinalProcessor>> C getCompositeCardinalProcessor(
			CompositeProcessorId id) {
		assertCompositeProcessorId(id);
		return (C) compositeRegistry.get(id);
	}


	@Override
	public void registerCompositePreProcessor(CompositeProcessor<? extends PreProcessor> value)
			throws ProcessorBootstrapException {
		assertCompositeProcessorId(value.id());
		assertCompositeProcessorValue(value);
		compositeRegistry.register(value.id(), value);

	}

	@Override
	public void registerCompositePostProcessor(CompositeProcessor<? extends PostProcessor> value)
			throws ProcessorBootstrapException {
		assertCompositeProcessorId(value.id());
		assertCompositeProcessorValue(value);
		compositeRegistry.register(value.id(), value);

	}

	@Override
	public void registerCompositeCardinalProcessor(CompositeProcessor<? extends CardinalProcessor> value)
			throws ProcessorBootstrapException {
		assertCompositeProcessorId(value.id());
		assertCompositeProcessorValue(value);
		compositeRegistry.register(value.id(), value);

	}

	private void assertCompositeProcessorValue(CompositeProcessor<? extends Processor> value) {
		Assert.notNull(value, "composite processor to register cannot be null!");
	}

	private void assertCompositeProcessorId(CompositeProcessorId id) {
		Assert.notNull(id, "id key for registry operation cannot be null!");
	}

}
