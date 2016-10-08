package com.jci.utils;

import com.jci.exception.BaseException;

public class SystemException extends BaseException {

	private static final long serialVersionUID = 8320988710630318178L;

	public SystemException() {
		super();
	}

	public SystemException(Throwable cause) {
		super(cause);
	}

	public SystemException(String message, Throwable cause) {
		super(message, cause);
	}

	public SystemException(String message) {
		super(message);
	}

}