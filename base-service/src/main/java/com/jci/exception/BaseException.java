package com.jci.exception;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.jci.enums.ErrorEnum;
import com.jci.enums.ModuleEnum;
import com.jci.locale.BundleUTF8Control;

public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 7613126628186143461L;

	private String detailMessage;

	private ErrorEnum error;

	private Object arguments;

	private Locale locale;

	private ModuleEnum module = ModuleEnum.UNKNOWN;

	public BaseException() {
		super();
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
		detailMessage = message;
	}

	public BaseException(String message) {
		super(message);
		detailMessage = message;
	}

	public BaseException(Throwable cause) {
		super(cause);
		detailMessage = cause.toString();
	}

	@Override
	public String getMessage() {
		return getLocalizedMessage(error, Locale.getDefault(), arguments);
	}

	@Override
	public String getLocalizedMessage() {
		return getLocalizedMessage(error, locale, arguments);
	}

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

	public ErrorEnum getError() {
		return error;
	}

	public void setError(ErrorEnum error) {
		this.error = error;
	}

	public Object getArguments() {
		return arguments;
	}

	public void setArguments(Object arguments) {
		this.arguments = arguments;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public ModuleEnum getModule() {
		return module;
	}

}