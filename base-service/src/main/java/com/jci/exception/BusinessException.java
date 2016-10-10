package com.jci.exception;



/**
 * The Class BusinessException.
 */
public class BusinessException extends BaseException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8320988710630318178L;

	/**
	 * Instantiates a new business exception.
	 */
	public BusinessException() {
		super();
	}

	/**
	 * Instantiates a new business exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public BusinessException(String message, Throwable cause) { // NO_UCD (unused code)
		super(message, cause);
	}

	/**
	 * Instantiates a new business exception.
	 *
	 * @param message the message
	 */
	public BusinessException(String message) { // NO_UCD (unused code)
		super(message);
	}

	/**
	 * Instantiates a new business exception.
	 *
	 * @param cause the cause
	 */
	public BusinessException(Throwable cause) { // NO_UCD (unused code)
		super(cause);
	}

}
