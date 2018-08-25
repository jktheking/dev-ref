package edu.jktheking.processflow.support;

import java.util.Map;
import java.util.Optional;
/**
 * Interface for in-memory key-value registry reader
 * */
public interface KeyValueRegistryReader<K,V> {
	
	Map<K, V> allEntry();

	Optional<V> find(K key);

	V get(K key);

	boolean containsKey(K key);
	
	int size();
	
    boolean isEmpty();

}
