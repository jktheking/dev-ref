package edu.jktheking.processflow.framework.core;

/**
 * 
 * Represents abstraction over group of processors working as single unit of work.
 * 
 * */
public interface CompositeProcessor<P extends Processor>{
	
	CompositeProcessorId id();

	void process(ProcessorContext context) throws RuntimeProcessorException;
	
	void process(ProcessorContext context, ProcessorExecutor<? super P> executor) throws RuntimeProcessorException;
	
	void process(ProcessorContext context, ProcessorCollectionExecutor<ProcessorCollection<? extends P>> collectionExecutor) throws RuntimeProcessorException;


}
