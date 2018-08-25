package edu.jktheking.processflow.framework.core;

/**
 * This exception class is to be used for terminating the chain for a request.
 * </br>
 * This exception has be cought in the chain invoker class to send the response
 * back to the client.
 * 
 * @since Aug 17, 2018 5:20:39 PM
 */
public class TerminatingProcessorException extends RuntimeProcessorException {

	private String terminationCause;

	private static final long serialVersionUID = 1L;

	public TerminatingProcessorException(String terminationCause, String message, AppState... appStates) {
		super(message, appStates);
		this.terminationCause = terminationCause;
	}

	public TerminatingProcessorException(String terminationCause, String message, Throwable cause,
			AppState... appStates) {
		super(message, cause, appStates);
		this.terminationCause = terminationCause;
	}

	public TerminatingProcessorException(String terminationCause, String message, Throwable cause) {
		super(message, cause);
		this.terminationCause = terminationCause;
	}

	public TerminatingProcessorException(String terminationCause, String message) {
		super(message);
		this.terminationCause = terminationCause;
	}

	public TerminatingProcessorException(String terminationCause, Throwable cause) {
		super(cause);
		this.terminationCause = terminationCause;
	}

	public String getTerminationCause() {
		return terminationCause;
	}

}
