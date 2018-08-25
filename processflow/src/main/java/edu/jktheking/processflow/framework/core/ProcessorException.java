package edu.jktheking.processflow.framework.core;

public abstract class ProcessorException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 214372553799758849L;

	
	public ProcessorException(Throwable cause) {
		super(cause);
	}

	public ProcessorException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProcessorException(String message) {
		super(message);
	}
	
  

}
