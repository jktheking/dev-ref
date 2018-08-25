package edu.jktheking.processflow.support;

import java.util.Map;

/**
 * Interface for in-memory key-value registry writer
 * */
public interface KeyValueRegistryWriter<K,V> {

	void register(K key, V value);

	void registerAll(Map<K, V> items);


}

