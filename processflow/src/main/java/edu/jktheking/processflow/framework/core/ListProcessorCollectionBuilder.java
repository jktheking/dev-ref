package edu.jktheking.processflow.framework.core;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Consumer;

import edu.jktheking.processflow.utils.Assert;

/**
 * This class hides the implementation of ListProcessorCollection, so that underlying java.util.List remains unmodifiable. 
 * 
 * */
public class ListProcessorCollectionBuilder<P extends Processor> {
	
	
	private final  ListProcessorCollectionImpl<P> listProcessorCollection;
	

	public ListProcessorCollectionBuilder() {
		super();
		this.listProcessorCollection = new ListProcessorCollectionImpl<>();
	}


	public ListProcessorCollectionBuilder(Collection<? extends P> collection) {
		super();
		this.listProcessorCollection = new ListProcessorCollectionImpl<>(collection);
	}
	
	public ListProcessorCollectionBuilder<P> withProcessor(P processor){
		listProcessorCollection.add(processor);
		return this;
	}
	
	public ListProcessorCollectionBuilder<P> withAllProcessor(Collection<? extends P> collection){
		listProcessorCollection.addAll(collection);
		return this;
	}
	
	public ListProcessorCollection<P> build(){
		return listProcessorCollection;
	}

	/**
	 * LinkedList backed ListProcessorCollection
	 * 
	 * */
	private static  class ListProcessorCollectionImpl<P extends Processor> implements ListProcessorCollection<P> {

		private final List<P> list;

		private ListProcessorCollectionImpl() {
			this.list = new LinkedList<P>();
		}

		private ListProcessorCollectionImpl(Collection<? extends P> collection) {
			this();
			addAll(collection);
		}

		@Override
		public int size() {
			return list.size();
		}

		private void add(P processor) {
			Assert.notNull(processor, "processor to add cannot be null!");
			list.add(processor);

		}

		private void addAll(Collection<? extends P> collection) {
			Assert.notEmpty(collection, "collection to add to chain cannot be null or empty!");
			Iterator<? extends P> it = collection.iterator();
			while (it.hasNext()) {
				P processor = it.next();
				if (processor != null) {
					add(processor);
				}
			}
		}

		@Override
		public Iterator<P> iterator() {
			return newListItrInstance();
		}

		@Override
		public ListIterator<P> listIterator() {
			return newListItrInstance();
		}

		@Override
		public ListIterator<P> listIterator(int index) {
			return new ListItr<P>(list.listIterator(index));
		}

		
		
		@Override
		public String toString() {
			return String.format("ListProcessorCollection=%s", list);
		}

		private ListItr<P> newListItrInstance() {
			return new ListItr<P>(list.listIterator());
		}

		private  static class ListItr<P> implements ListIterator<P> {

			private final ListIterator<P> listItr;

			private ListItr(ListIterator<P> listItr) {
				this.listItr = listItr;
			}

			public boolean hasNext() {
				return listItr.hasNext();
			}

			public P next() {
				return listItr.next();
			}

			public boolean hasPrevious() {
				return listItr.hasPrevious();
			}

			public P previous() {
				return listItr.previous();
			}

			public int nextIndex() {
				throw new UnsupportedOperationException();
			}

			public int previousIndex() {
				throw new UnsupportedOperationException();
			}

			public void remove() {
				throw new UnsupportedOperationException();
			}

			public void set(P e) {
				throw new UnsupportedOperationException();
			}

			public void add(P e) {
				throw new UnsupportedOperationException();
			}

			public void forEachRemaining(Consumer<? super P> action) {
				listItr.forEachRemaining(action);
			}

		}

	}
}
