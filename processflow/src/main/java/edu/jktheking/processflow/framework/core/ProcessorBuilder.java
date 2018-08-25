package edu.jktheking.processflow.framework.core;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Set;

import edu.jktheking.processflow.support.EnumMapRegistry;
import edu.jktheking.processflow.support.HandlerType;
import edu.jktheking.processflow.support.KeyValueRegistryReader;
import edu.jktheking.processflow.support.ProcessorId;
import edu.jktheking.processflow.utils.Assert;
import edu.jktheking.processflow.utils.ReflectionUtils;
/**
 * Builder class for Processor.
 * 
 * */
public class ProcessorBuilder<P extends Processor> {

	/**
	 * 
	 * Default implementation of {@link ProcessorHandlerResolver}
	 */
	private static final class DefaultProcessorHandlerResolver implements ProcessorHandlerResolver {
		private final EnumMapRegistry<HandlerType, ProcessorHandler>  processorHandlerRegistry;
		private DefaultProcessorHandlerResolver(EnumMapRegistry<HandlerType, ProcessorHandler>  processorHandlerRegistry){
			this.processorHandlerRegistry = processorHandlerRegistry;
		}
		

		@Override
		public KeyValueRegistryReader<HandlerType, ProcessorHandler> processorHandlerRegistry() {
			return processorHandlerRegistry;
		}

	}

	private final EnumMapRegistry<HandlerType, ProcessorHandler>  processorHandlerRegistry; 
	private final Set<AppState> escapeStates;
	private final ProcessorHandlerResolver processHandlerResolver;
	private final ProcessorId processorId;
	private final Class<P> processorClass;

	private static final String ASSERT_NOT_NULL_KEY_MSG_FORMAT = "key cannot be null  for ProcessorBuilder operation: withProcessorHandler(%s key, ProcessorHandler processorHandler)!";
	private static final String ASSERT_NOT_NULL_PROCESSOR_HANDLER = "ProcessorHandler cannot be null  for ProcessorBuilder operation: withProcessorHandler(%s key, ProcessorHandler processorHandler)!";

	/**
	 * Creates ProcessorBuilder with non-default custom ProcessorHandlerResolver
	 * passed in the argument.
	 */
	public ProcessorBuilder(Class<P> processorClass, ProcessorId processorId,
			ProcessorHandlerResolver processHandlerResolver) {
		Assert.notNull(processorId, "processorId cannot be null!");
		this.processorClass = processorClass;
		this.processorId = processorId;
		
		this.escapeStates = EnumSet.noneOf(AppState.class);
		this.processorHandlerRegistry = new EnumMapRegistry<>(HandlerType.class);
		if(processHandlerResolver == null){
			this.processHandlerResolver =  new DefaultProcessorHandlerResolver(this.processorHandlerRegistry);
		}else{
			this.processHandlerResolver = processHandlerResolver;
		}
		
	}

	/**
	 * Creates ProcessorBuilder with DefaultProcessorHandlerResolver.
	 */
	public ProcessorBuilder(Class<P> processorClass, ProcessorId processorId) {
		this(processorClass, processorId,null);
	}

	public ProcessorBuilder<P> withProcessorHandler(HandlerType key, ProcessorHandler processorHandler) {
		Assert.notNull(key, String.format(ASSERT_NOT_NULL_KEY_MSG_FORMAT, key.name()));
		Assert.notNull(processorHandler, String.format(ASSERT_NOT_NULL_PROCESSOR_HANDLER, key.name()));
		processorHandlerRegistry.register(key, processorHandler);
		return this;
	}




	public ProcessorBuilder<P> withProcessorHandlers(EnumMap<HandlerType, ProcessorHandler> processorHandlers) {
		Assert.notEmpty(processorHandlers, "processorHandlers EnumMap cannot be null!");
		processorHandlerRegistry.registerAll(processorHandlers);
		return this;
	}

	public ProcessorBuilder<P> withEscapeState(AppState appState) {
		Assert.notNull(appState, "appState cannot be null!");
		escapeStates.add(appState);
		return this;
	}

	public ProcessorBuilder<P> withEscapeStates(AppState... appStates) {
		Assert.notNull(appStates, "appState array cannot be null!");
		for (AppState state : appStates) {
			withEscapeState(state);
		}
		return this;
	}

	public ProcessorBuilder<P> withEscapeStates(Set<AppState> appStates) {
		Assert.notEmpty(appStates, "appStates cannot be null!");
		escapeStates.addAll(appStates);
		return this;
	}
	

	/**
	 * Builds underlying Processor using three argument constructor
	 * {@code (ProcessorId,ProcessorHandlerResolver,EscapeStateRegistry)}
	 */
	public P build() throws ProcessorBootstrapException {

		try {
			validateProcessorHandlerRegistry();
			validateConstructor(processorClass);
			Class<?> constructorClass[] = { ProcessorId.class, ProcessorHandlerResolver.class,
					Set.class };
			Object constructorArg[] = { processorId, processHandlerResolver, escapeStates };
			return ReflectionUtils.newInstance(processorClass, constructorClass, constructorArg);
		} catch (ProcessorBootstrapException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessorBootstrapException(e);
		}

	}

	private void validateConstructor(Class<? extends P> processorClass) {
		if (!ReflectionUtils.containsDeclaredConstructor(processorClass, ProcessorId.class,
				ProcessorHandlerResolver.class, Set.class)) {
			throw new ProcessorBootstrapException(String.format(
					"%s does not contain constructor:(ProcessorId,ProcessorHandlerResolver,EscapeStateRegistry)! Consider extending from %s",
					processorClass.getName(),ProcessorBase.class.getName()));
		}
	}

	private void validateProcessorHandlerRegistry() {
		// check if ProcessorHandlerRegistry contains default key or not
		if (!processHandlerResolver.processorHandlerRegistry().isEmpty() && !processHandlerResolver
				.processorHandlerRegistry().containsKey(HandlerType.DEFAULT)) {
			throw new ProcessorBootstrapException("There must be a processorHandler registered with key:"
					+ HandlerType.DEFAULT);
		}

	}

}
