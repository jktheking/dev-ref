package edu.jktheking.processflow.framework.core;

public  class ProcessorBootstrapException extends ProcessorException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public ProcessorBootstrapException(Throwable cause) {
		super(cause);
	}

	public ProcessorBootstrapException(String message) {
		super(message);
	}
	
  
	public ProcessorBootstrapException(String message, Throwable cause) {
		super(message, cause);
	}

}
