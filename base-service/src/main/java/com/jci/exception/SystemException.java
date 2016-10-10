package com.jci.exception;



/**
 * The Class SystemException.
 */
public class SystemException extends BaseException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8320988710630318178L;

	/**
	 * Instantiates a new system exception.
	 */
	public SystemException() {
		super();
	}

	/**
	 * Instantiates a new system exception.
	 *
	 * @param cause the cause
	 */
	public SystemException(Throwable cause) { // NO_UCD (use default)
		super(cause);
	}

	/**
	 * Instantiates a new system exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public SystemException(String message, Throwable cause) { // NO_UCD (unused code)
		super(message, cause);
	}

	/**
	 * Instantiates a new system exception.
	 *
	 * @param message the message
	 */
	public SystemException(String message) { // NO_UCD (unused code)
		super(message);
	}

}