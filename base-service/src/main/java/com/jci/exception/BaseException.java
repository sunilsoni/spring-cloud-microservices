package com.jci.exception;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.jci.enums.ErrorEnum;
import com.jci.enums.ModuleEnum;
import com.jci.locale.BundleUTF8Control;



/**
 * The Class BaseException.
 */
public class BaseException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7613126628186143461L;

	/** The detail message. */
	private String detailMessage;

	/** The error. */
	private ErrorEnum error;

	/** The arguments. */
	private Object arguments;

	/** The locale. */
	private Locale locale;

	/** The module. */
	private ModuleEnum module = ModuleEnum.UNKNOWN;

	/**
	 * Instantiates a new base exception.
	 */
	public BaseException() {
		super();
	}

	/**
	 * Instantiates a new base exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public BaseException(String message, Throwable cause) {
		super(message, cause);
		detailMessage = message;
	}

	/**
	 * Instantiates a new base exception.
	 *
	 * @param message the message
	 */
	public BaseException(String message) {
		super(message);
		detailMessage = message;
	}

	/**
	 * Instantiates a new base exception.
	 *
	 * @param cause the cause
	 */
	public BaseException(Throwable cause) {
		super(cause);
		detailMessage = cause.toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return getLocalizedMessage(error, Locale.getDefault(), arguments);
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getLocalizedMessage()
	 */
	@Override
	public String getLocalizedMessage() {
		return getLocalizedMessage(error, locale, arguments);
	}

	/**
	 * Gets the localized message.
	 *
	 * @param error the error
	 * @param locale the locale
	 * @param arguments the arguments
	 * @return the localized message
	 */
	protected String getLocalizedMessage(ErrorEnum error, Locale locale, Object... arguments) {

		String result = detailMessage;

		if (error != null && StringUtils.isNotBlank(error.KEY)) {
			ResourceBundle messages = ResourceBundle.getBundle("locale/errors", locale, new BundleUTF8Control());

			if (messages.containsKey(error.KEY)) {

				result = messages.getString(error.KEY);

				if (ArrayUtils.isNotEmpty(arguments)) {
					result = MessageFormat.format(result, arguments);
				}
			} else {
				result = "Message witk key = " + error.KEY + " not found ...";
			}
		}

		return result;
	}

	/**
	 * Gets the error.
	 *
	 * @return the error
	 */
	public ErrorEnum getError() {
		return error;
	}

	/**
	 * Sets the error.
	 *
	 * @param error the new error
	 */
	public void setError(ErrorEnum error) {
		this.error = error;
	}

	/**
	 * Gets the arguments.
	 *
	 * @return the arguments
	 */
	public Object getArguments() {
		return arguments;
	}

	/**
	 * Sets the arguments.
	 *
	 * @param arguments the new arguments
	 */
	public void setArguments(Object arguments) {
		this.arguments = arguments;
	}

	/**
	 * Gets the locale.
	 *
	 * @return the locale
	 */
	public Locale getLocale() {
		return locale;
	}

	/**
	 * Sets the locale.
	 *
	 * @param locale the new locale
	 */
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	/**
	 * Gets the module.
	 *
	 * @return the module
	 */
	public ModuleEnum getModule() {
		return module;
	}

}