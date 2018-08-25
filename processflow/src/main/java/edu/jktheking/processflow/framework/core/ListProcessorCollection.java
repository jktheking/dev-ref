package edu.jktheking.processflow.framework.core;

import java.util.ListIterator;
/**
 * List based extension of ProcessorCollection.
 * {@link ProcessorCollection}
 * */
public interface ListProcessorCollection<P extends Processor> extends ProcessorCollection<P> {

	ListIterator<P> listIterator();

	ListIterator<P> listIterator(int index);
	
}
