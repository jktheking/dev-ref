package edu.jktheking.processflow.framework.core;

public interface ChainedCompositeProcessor<P extends Processor> extends CompositeProcessor<P> {

	void process(ProcessorContext context, ProcessorId start, ProcessorId end) throws RuntimeProcessorException;

	void process(ProcessorContext context, ProcessorId start, ProcessorId end, ProcessorExecutor<? super P> executor)
			throws RuntimeProcessorException;

	void reverseProcess(ProcessorContext context) throws RuntimeProcessorException;

	void reverseProcess(ProcessorContext context, ProcessorExecutor<? super P> executor)
			throws RuntimeProcessorException;

	void reverseProcess(ProcessorContext context, ProcessorId start, ProcessorId end) throws RuntimeProcessorException;

	void reverseProcess(ProcessorContext context, ProcessorId start, ProcessorId end,
			ProcessorExecutor<? super P> executor) throws RuntimeProcessorException;


}
