package edu.jktheking.processflow.framework.core;

public class NonTerminatingProcessorException extends RuntimeProcessorException {

	private static final long serialVersionUID = 1L;

	public NonTerminatingProcessorException(String message, AppState... appStates) {
		super(message,appStates);
	}

	public NonTerminatingProcessorException(String message, Throwable cause, AppState... appStates) {
		super(message, cause, appStates);
	}

	public NonTerminatingProcessorException(String message, Throwable cause) {
		super(message, cause);
	}

	public NonTerminatingProcessorException(String message) {
		super(message);
	}

	public NonTerminatingProcessorException(Throwable cause) {
		super(cause);
	}

}
