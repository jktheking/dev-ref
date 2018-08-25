package edu.jktheking.processflow.framework.core;

import java.util.Iterator;

import edu.jktheking.processflow.utils.Assert;
/**
 * super-type abstraction for processors container.
 * 
 * */
public interface ProcessorCollection<P extends Processor> extends Iterable<P> {
	
	
	int size();
	
	default boolean isEmpty() {
		return size() == 0;
	}

	default boolean contains(P processor) {
		Assert.notNull(processor, "processor cannot be null for contains check!");
		Iterator<P> it = iterator();
		while (it.hasNext()) {
			if (processor.equals(it.next()))
				return true;
		}

		return false;
	}

	default boolean contains(ProcessorId processorId) {
		Assert.notNull(processorId, "processorId class cannot be null for contains check!");
		Iterator<P> it = iterator();
		while (it.hasNext()) {
			if (processorId.equals(it.next().id()))
				return true;
		}

		return false;
	}


}
