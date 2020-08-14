package edu.jktheking.concurrent.utils;

import java.util.Collection;
import java.util.Map;

/**
 * 
 * Assertion utility class that assists in validating  method arguments.
 * Useful for identifying programmer errors early and clearly at runtime.
 * 
 * Typically used to validate method arguments rather than
 * configuration properties, to check for cases that are usually programmer
 * errors rather than configuration errors. In contrast to configuration
 * initialization code, there is usually no point in falling back to defaults in
 * such methods.
 * 
 * 
 */
public class Assert {
	/**
	 * Assert that an object is {@code null}.
	 */
	public static void isNull(Object object, String message) {

		if (object != null) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Assert that an object is not {@code null}.
	 */
	public static void notNull(Object object, String message) {
		if (object == null) {
			throw new IllegalArgumentException(message);
		}
	}

	public static void isTrue(boolean expression, String message) {
		if (!expression) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * 
	 * Assert that a collection contains elements; it must not be {@code null} and
	 * must contain at least one element.
	 */
	public static void notEmpty(Collection<?> collection, String message) {
		if (collection == null || collection.isEmpty()) {
			throw new IllegalArgumentException(message); 
		}
	}

	/**
	 * Assert that a Map contains entries; that is, it must not be {@code null} and
	 * must contain at least one entry.
	 */
	public static void notEmpty(Map<?, ?> map, String message) {
		if (map == null || map.isEmpty()) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Assert that the provided object is an instance of the provided class.
	 *
	 */
	public static void isInstanceOf(Class<?> type, Object obj, String message) {
		notNull(type,"Type to check against must not be null");
		if (!type.isInstance(obj)) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Assert that {@code superType.isAssignableFrom(subType)} is {@code true}.
	 */
	public static void isAssignable(Class<?> superType, Class<?> subType,
			String message) {
		notNull(superType, "Super type to check against must not be null");
		if (subType == null || !superType.isAssignableFrom(subType)) {
			throw new IllegalArgumentException(message);
		}
	}
	
	

	public static void isNotCollectionAndMapAssignable(Class<?> subType,
			String message) {
		if (subType == null || (Collection.class.isAssignableFrom(subType)) || (Map.class.isAssignableFrom(subType)))  {
			throw new IllegalArgumentException(message);
		}
	}
	

	/**
	 * Assert that the given String contains valid text content; that is, it must
	 * not be {@code null} and must contain at least one non-whitespace character.
	 */
	public static void isNotBlank(String text, String message) {
		if (StringUtils.isBlank(text)) {
			throw new IllegalArgumentException(message);
		}
	}

	public static <T> void isTypeCastCompatible(Class<T> attributeType, Object attributeInstance, String message) {
		notNull(attributeType, "attributeType for isTypeCastCompatible cannot be null");
		if (attributeInstance != null && !attributeType.isInstance(attributeInstance)) {
			throw new IllegalArgumentException(message);
		}
	}

}
