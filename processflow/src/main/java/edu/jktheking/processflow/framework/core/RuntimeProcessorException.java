package edu.jktheking.processflow.framework.core;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

public abstract class RuntimeProcessorException extends ProcessorException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Set<AppState> appStates = EnumSet.noneOf(AppState.class);
	

	public RuntimeProcessorException(Throwable cause) {
		super(cause);
	}

	public RuntimeProcessorException(String message, Throwable cause, AppState... appStates) {
		super(message, cause);
		populateAppStates(appStates);

	}

	public RuntimeProcessorException(String message, AppState... appStates) {
		super(message);
		populateAppStates(appStates);
	}

	public RuntimeProcessorException(String message, Throwable cause) {
		super(message, cause);
	}

	public RuntimeProcessorException(String message) {
		super(message);
	}

	public Set<AppState> getAppStates() {
		return Collections.unmodifiableSet(appStates);
	}

	private void populateAppStates(AppState[] appStates) {
		if (appStates != null && appStates.length > 0) {
			for (AppState appState : appStates) {
				if (appState != null) {
					this.appStates.add(appState);
				}
			}
		}
	}

}
