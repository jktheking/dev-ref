package edu.jktheking.processflow.framework.core;

import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import edu.jktheking.processflow.utils.Assert;

/**
 * Parameter Object for 'process-flow-framework' : acts as both In/Out object.
 * Object of this class is available throughout the execution runtime of 'process-flow-framework'
 * framework. This context object should not be shared among multiple execution
 * flow of the 'process-flow-framework'.
 */
public final class ProcessorContext implements ParameterContext {

	private final String msisdn;
	private final HandlerType operator;
	private final Set<AppState> appStates;
	private final Map<ProcessorContextAttribute, Object> attributeMap;
	
	private ProcessorContext(String msisdn, HandlerType operator) {
		this.msisdn = msisdn;
		this.operator = operator;
		this.attributeMap = new EnumMap<>(ProcessorContextAttribute.class);
		this.appStates = EnumSet.noneOf(AppState.class);
	}

	/**
	 * Method to construct the ProcessorContext
	 */
	public static ProcessorContext newProcessorContext(String msisdn, HandlerType operator) {
		return new ProcessorContext(msisdn, operator);
	}


	public String getMsisdn() {
		return msisdn;
	}

	public HandlerType getOperator() {
		return operator;
	}

	public Set<AppState> getAppStates() {
		return Collections.unmodifiableSet(appStates);
	}

	public ProcessorContext addAppState(AppState appState) {
		this.appStates.add(appState);
		return this;
	}

	public ProcessorContext addAllAppState(Set<AppState> appStates) {
		this.appStates.addAll(appStates);
		return this;
	}

	private <T> String typeCastIncompatibleMessage(ProcessorContextAttribute attribute, Class<T> attributeType,
			Object attributeInstance) {
		return attributeType + " and " + attributeInstance + "is not type cast compatible for attribute:"
				+ attribute.name();
	}

	public <T> ProcessorContext addAttribute(ProcessorContextAttribute attribute, Class<T> attributeType,
			T attributeInstance) {
		Assert.isNotCollectionAndMapAssignable(attributeType, "use addAttributeForXXX method instead");
		Assert.isTypeCastCompatible(attributeType, attributeInstance,
				typeCastIncompatibleMessage(attribute, attributeType, attributeInstance));
		attributeMap.put(attribute, attributeInstance);
		return this;

	}

	public <T> ProcessorContext addOptionalAttribute(ProcessorContextAttribute attribute,
			Optional<T> attributeInstance) {
		Assert.isTypeCastCompatible(Optional.class, attributeInstance,
				typeCastIncompatibleMessage(attribute, Optional.class, attributeInstance));
		attributeMap.put(attribute, attributeInstance);
		return this;

	}

	public <T> ProcessorContext addAttributeForList(ProcessorContextAttribute attribute, T attributeInstance) {
		Assert.isTypeCastCompatible(List.class, attributeInstance,
				typeCastIncompatibleMessage(attribute, List.class, attributeInstance));
		attributeMap.put(attribute, attributeInstance);
		return this;

	}

	public <T> ProcessorContext addAttributeForMap(ProcessorContextAttribute attribute, T attributeInstance) {
		Assert.isTypeCastCompatible(Map.class, attributeInstance,
				typeCastIncompatibleMessage(attribute, Map.class, attributeInstance));
		attributeMap.put(attribute, attributeInstance);
		return this;

	}

	@SuppressWarnings("unchecked")
	public <T> T getAttribute(ProcessorContextAttribute attribute, Class<T> attributeType) {
		Object attributeInstance = attributeMap.get(attribute);
		Assert.isTypeCastCompatible(attributeType, attributeInstance,
				typeCastIncompatibleMessage(attribute, attributeType, attributeInstance));
		return (T) attributeInstance;

	}

	@SuppressWarnings("unchecked")
	public <T> Optional<T> getOptionalAttribute(ProcessorContextAttribute attribute) {
		Object attributeInstance = attributeMap.get(attribute);
		Assert.isTypeCastCompatible(Optional.class, attributeInstance,
				typeCastIncompatibleMessage(attribute, Optional.class, attributeInstance));
		return (Optional<T>) attributeInstance;
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getAttributeForList(ProcessorContextAttribute attribute) {
		Object attributeInstance = attributeMap.get(attribute);
		Assert.isTypeCastCompatible(List.class, attributeInstance,
				typeCastIncompatibleMessage(attribute, List.class, attributeInstance));
		return (List<T>) attributeInstance;
	}

	@SuppressWarnings("unchecked")
	public <K, V> Map<K, V> getAttributeForMap(ProcessorContextAttribute attribute) {
		Object attributeInstance = attributeMap.get(attribute);
		Assert.isTypeCastCompatible(Map.class, attributeInstance,
				typeCastIncompatibleMessage(attribute, Map.class, attributeInstance));
		return (Map<K, V>) attributeInstance;
	}

}
