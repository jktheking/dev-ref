package edu.jktheking.processflow.support;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

import edu.jktheking.processflow.utils.Assert;
/**
 * EnumMap based Implementation for {@code KeyValueRegistryReader} and {@code KeyValueRegistryWriter}
 * */
public class EnumMapRegistry<K extends Enum<K>, V>
implements KeyValueRegistryReader<K, V>, KeyValueRegistryWriter<K, V> {

	private static final String KEY_ASSERT_MSG = "key cannot be null for registry operation!";
	private static final String VALUE_ASSERT_MSG = "value cannot be null for registry operation!";

	private final Map<K, V> registry;

	public EnumMapRegistry(Class<K> klass) {
		this.registry = new EnumMap<>(klass);
	}

	@Override
	public void register(K key, V value) {
		Assert.notNull(key, KEY_ASSERT_MSG);
		Assert.notNull(value, VALUE_ASSERT_MSG);
		registry.put(key, value);
		
	}

	@Override
	public void  registerAll(Map<K, V> items) {
		Assert.notEmpty(items, "items to register cannot be empty!");
		items.entrySet().forEach(entry -> {
			if (entry.getKey() != null && entry.getValue() != null) {
				register(entry.getKey(), entry.getValue());
			}
		});
		
	}

	@Override
	public Map<K, V> allEntry() {
		return Collections.unmodifiableMap(registry);
	}

	@Override
	public Optional<V> find(K key) {
		Assert.notNull(key, KEY_ASSERT_MSG);
		return Optional.ofNullable(registry.get(key));

	}

	@Override
	public V get(K key) {
		Assert.notNull(key, KEY_ASSERT_MSG);
		return registry.get(key);
	}

	@Override
	public boolean containsKey(K key) {
		Assert.notNull(key, KEY_ASSERT_MSG);
		return registry.containsKey(key);
	}

	@Override
	public int size() {
		return registry.size();
	}

	@Override
	public boolean isEmpty() {
		return registry.isEmpty();
	}

}
