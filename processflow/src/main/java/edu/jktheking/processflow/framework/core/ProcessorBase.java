package edu.jktheking.processflow.framework.core;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

/**
 * Base class for normal processor classification.
 * 
 * */
public abstract class ProcessorBase implements Processor{

	private final Set<AppState> escapeStates;
	private final ProcessorHandlerResolver processorHandlerResolver;
	private final ProcessorId id;

	protected ProcessorBase(ProcessorId processorId, ProcessorHandlerResolver processorHandlerResolver,
			Set<AppState> escapeStates) {
		super();
		this.id = processorId;
		this.processorHandlerResolver = processorHandlerResolver;
		this.escapeStates = Collections.unmodifiableSet(escapeStates);
	}


	
	@Override
	public Set<AppState> escapeStates() {
		return  escapeStates;
	}
	
	
	@Override
	public Optional<ProcessorHandlerResolver> processorHandlerResolver() {
		return Optional.ofNullable(processorHandlerResolver);
	}

	@Override
	public ProcessorId id() {
		return id;
	}
	
	
	@Override
	public void process(ProcessorContext context) throws RuntimeProcessorException {
		processorHandlerResolver().ifPresent(resolver -> {
			resolver.resolveProcessorHandler(context).handle(context);
		});

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProcessorBase other = (ProcessorBase) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("id=%s", id);
	}

	

	
}
