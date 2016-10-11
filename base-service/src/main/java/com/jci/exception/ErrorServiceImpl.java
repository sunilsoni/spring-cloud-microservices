package com.jci.exception;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jci.enums.ErrorEnum;
import com.jci.locale.LocaleService;

/**
 * The Class ErrorServiceImpl.
 */
@Service
public class ErrorServiceImpl implements ErrorService { // NO_UCD (unused code)

	/** The locale service. */
	@Autowired
	private LocaleService localeService;

	/* (non-Javadoc)
	 * @see com.jci.exception.ErrorService#createException(java.lang.Class)
	 */
	@Override
	public <T extends BaseException> T createException(Class<? extends BaseException> clazz) {
		return getException(clazz, null, null, null);
	}

	/* (non-Javadoc)
	 * @see com.jci.exception.ErrorService#createException(java.lang.Class, com.jci.enums.ErrorEnum)
	 */
	@Override
	public <T extends BaseException> T createException(Class<? extends BaseException> clazz, ErrorEnum error) {
		return getException(clazz, null, error, null);
	}

	/* (non-Javadoc)
	 * @see com.jci.exception.ErrorService#createException(java.lang.Class, com.jci.enums.ErrorEnum, java.lang.Object[])
	 */
	@Override
	public <T extends BaseException> T createException(Class<? extends BaseException> clazz, ErrorEnum error,
			Object... arguments) {
		return getException(clazz, null, error, arguments);
	}

	/* (non-Javadoc)
	 * @see com.jci.exception.ErrorService#createException(java.lang.Class, java.lang.Exception)
	 */
	@Override
	public <T extends BaseException> T createException(Class<? extends BaseException> clazz, Exception cause) {
		return getException(clazz, cause, null, null);
	}

	/* (non-Javadoc)
	 * @see com.jci.exception.ErrorService#createException(java.lang.Class, java.lang.Exception, com.jci.enums.ErrorEnum)
	 */
	@Override
	public <T extends BaseException> T createException(Class<? extends BaseException> clazz, Exception cause,
			ErrorEnum error) {
		return getException(clazz, cause, error, null);
	}

	/* (non-Javadoc)
	 * @see com.jci.exception.ErrorService#createException(java.lang.Class, java.lang.Exception, com.jci.enums.ErrorEnum, java.lang.Object[])
	 */
	@Override
	public <T extends BaseException> T createException(Class<? extends BaseException> clazz, Exception cause,
			ErrorEnum error, Object... arguments) {
		return getException(clazz, cause, error, arguments);
	}

	/**
	 * Gets the exception.
	 *
	 * @param <T> the generic type
	 * @param clazz the clazz
	 * @param cause the cause
	 * @param error the error
	 * @param arguments the arguments
	 * @return the exception
	 */
	@SuppressWarnings("unchecked")
	private <T extends BaseException> T getException(Class<? extends BaseException> clazz, Exception cause,
			ErrorEnum error, Object[] arguments) {

		BaseException exeption = null;

		try {
			exeption = clazz.newInstance();
			exeption.setLocale(localeService.getCurrentLocale());
			if (cause != null) {
				ExceptionUtils.setCause(exeption, cause);
			}
			if (error != null) {
				exeption.setError(error);
			}
			if (arguments != null) {
				exeption.setArguments(arguments);
			}
		} catch (InstantiationException | IllegalAccessException e) {
			exeption = new SystemException(e);
			exeption.setError(ErrorEnum.ERR_SYSTEM_CREATE);
			exeption.setLocale(localeService.getCurrentLocale());
		}

		return (T) exeption;
	}
}
