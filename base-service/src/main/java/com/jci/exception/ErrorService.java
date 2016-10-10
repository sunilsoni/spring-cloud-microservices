package com.jci.exception;

import com.jci.enums.ErrorEnum;



/**
 * The Interface ErrorService.
 */
public interface ErrorService {

	/**
	 * Creates the exception.
	 *
	 * @param <T> the generic type
	 * @param clazz the clazz
	 * @return the t
	 */
	<T extends BaseException> T createException(Class<? extends BaseException> clazz); // NO_UCD (unused code)

	/**
	 * Creates the exception.
	 *
	 * @param <T> the generic type
	 * @param clazz the clazz
	 * @param error the error
	 * @return the t
	 */
	<T extends BaseException> T createException(Class<? extends BaseException> clazz, ErrorEnum error); // NO_UCD (unused code)

	/**
	 * Creates the exception.
	 *
	 * @param <T> the generic type
	 * @param clazz the clazz
	 * @param error the error
	 * @param arguments the arguments
	 * @return the t
	 */
	<T extends BaseException> T createException(Class<? extends BaseException> clazz, ErrorEnum error,Object... arguments); // NO_UCD (unused code)

	/**
	 * Creates the exception.
	 *
	 * @param <T> the generic type
	 * @param clazz the clazz
	 * @param cause the cause
	 * @return the t
	 */
	<T extends BaseException> T createException(Class<? extends BaseException> clazz, Exception cause); // NO_UCD (unused code)

	/**
	 * Creates the exception.
	 *
	 * @param <T> the generic type
	 * @param clazz the clazz
	 * @param cause the cause
	 * @param error the error
	 * @return the t
	 */
	<T extends BaseException> T createException(Class<? extends BaseException> clazz, Exception cause, ErrorEnum error);

	/**
	 * Creates the exception.
	 *
	 * @param <T> the generic type
	 * @param clazz the clazz
	 * @param cause the cause
	 * @param error the error
	 * @param arguments the arguments
	 * @return the t
	 */
	<T extends BaseException> T createException(Class<? extends BaseException> clazz, Exception cause, ErrorEnum error,Object... arguments);

}

