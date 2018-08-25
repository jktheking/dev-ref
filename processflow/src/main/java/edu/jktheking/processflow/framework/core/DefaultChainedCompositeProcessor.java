package edu.jktheking.processflow.framework.core;

import java.util.ListIterator;

import edu.jktheking.processflow.support.CompositeProcessorId;
import edu.jktheking.processflow.support.ProcessorId;
import edu.jktheking.processflow.utils.Assert;
/**
 * Default implementation for {@code ChainedCompositeProcessor}
 * 
 * */
public class DefaultChainedCompositeProcessor<P extends Processor>
implements ChainedCompositeProcessor<P>, ProcessorExecutor<P> {

	private final CompositeProcessorId id;
	private final ListProcessorCollection<P> listProcessorCollection;

	public DefaultChainedCompositeProcessor(CompositeProcessorId id,
			ListProcessorCollection<P> listProcessorCollection) {
		Assert.notNull(id, "CompositeProcessorId cannot be null!");
		Assert.notNull(listProcessorCollection, "listProcessorCollection cannot be null!");
		this.id = id;
		this.listProcessorCollection = listProcessorCollection;
	}

	@Override
	public CompositeProcessorId id() {
		return id;
	}

	@Override
	public void process(ProcessorContext context) throws RuntimeProcessorException {
		process(context, this);

	}

	@Override
	public void process(ProcessorContext context, ProcessorId start, ProcessorId end) throws RuntimeProcessorException {
		process(context, start, end, this);
	}

	@Override
	public void reverseProcess(ProcessorContext context) throws RuntimeProcessorException {
		reverseProcess(context, this);
	}

	@Override
	public void reverseProcess(ProcessorContext context, ProcessorId start, ProcessorId end)
			throws RuntimeProcessorException {
		reverseProcess(context, start, end, this);
	}

	@Override
	public void process(ProcessorContext context, ProcessorExecutor<? super P> executor)
			throws RuntimeProcessorException {
		asserProcessorContextAndExecutor(context, executor);

		for (P processor : listProcessorCollection) {
			executor.execute(processor, context);
		}

	}

	@Override
	public void process(ProcessorContext context, ProcessorId start, ProcessorId end,
			ProcessorExecutor<? super P> executor) throws RuntimeProcessorException {
		asserProcessorContextAndExecutor(context, executor);
		assertStartEndProcessorId(start, end);

		boolean execution = false;

		for (P processor : listProcessorCollection) {

			// starts execution
			if (start.equals(processor.id())) {
				execution = true;
			}

			if (execution) {
				executor.execute(processor, context);
			}

			// ends execution
			if (end.equals(processor.id())) {
				execution = false;
				break;
			}
		}

	}

	@Override
	public void reverseProcess(ProcessorContext context, ProcessorExecutor<? super P> executor)
			throws RuntimeProcessorException {
		asserProcessorContextAndExecutor(context, executor);
		ListIterator<P> listItr = listProcessorCollection.listIterator((listProcessorCollection.size()));
		while (listItr.hasPrevious()) {
			executor.execute(listItr.previous(), context);
		}

	}

	@Override
	public void reverseProcess(ProcessorContext context, ProcessorId start, ProcessorId end,
			ProcessorExecutor<? super P> executor) throws RuntimeProcessorException {

		asserProcessorContextAndExecutor(context, executor);
		assertStartEndProcessorId(start, end);

		ListIterator<P> listItr = listProcessorCollection.listIterator((listProcessorCollection.size()));
		while (listItr.hasPrevious()) {
			P processor = listItr.previous();
			boolean execution = false;

			// starts execution
			if (start.equals(processor.id())) {
				execution = true;
			}

			if (execution) {
				executor.execute(processor, context);
			}

			// ends execution
			if (end.equals(processor.id())) {
				execution = false;
				break;
			}
		}

	}

	@Override
	public void execute(P processor, ProcessorContext context) throws RuntimeProcessorException {
		try {
			if (checkForEscapeStates(processor, context)) {
				// TODO: logger for escaping the execution
				return;
			}
			// TODO: logger if required before processor
			processor.process(context);
			// TODO: logger if required after processor
		} catch (NonTerminatingProcessorException e) {
			// TODO: proper logger
			context.addAllAppState(e.getAppStates());
		} catch (TerminatingProcessorException e) {
			context.addAllAppState(e.getAppStates());
			throw e;
		} catch (Exception e) {
			throw new TerminatingProcessorException("!terminating cause!",e);
		}

	}

	private void asserProcessorContextAndExecutor(ProcessorContext context, ProcessorExecutor<? super P> executor) {
		Assert.notNull(context, "processorContext cannot be null!");
		Assert.notNull(executor, "executor cannot be null!");
	}

	private void assertStartEndProcessorId(ProcessorId start, ProcessorId end) {
		Assert.notNull(start, "start processorId cannot be null!");
		Assert.notNull(end, "end processorId cannot be null!");
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
		@SuppressWarnings("rawtypes")
		DefaultChainedCompositeProcessor other = (DefaultChainedCompositeProcessor) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("ChainedCompositeProcessor [id=%s, %s]", id, listProcessorCollection);
	}

	@Override
	public void process(ProcessorContext context,
			ProcessorCollectionExecutor<ProcessorCollection<? extends P>> collectionExecutor)
					throws RuntimeProcessorException {
		collectionExecutor.execute(listProcessorCollection, context);
	}

}
