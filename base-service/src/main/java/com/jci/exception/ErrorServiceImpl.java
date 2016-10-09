package com.jci.exception;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jci.enums.ErrorEnum;
import com.jci.locale.LocaleService;

@Service
public class ErrorServiceImpl implements ErrorService {

	@Autowired
	private LocaleService localeService;

	@Override
	public <T extends BaseException> T createException(Class<? extends BaseException> clazz) {
		return getException(clazz, null, null, null);
	}

	@Override
	public <T extends BaseException> T createException(Class<? extends BaseException> clazz, ErrorEnum error) {
		return getException(clazz, null, error, null);
	}

	@Override
	public <T extends BaseException> T createException(Class<? extends BaseException> clazz, ErrorEnum error,
			Object... arguments) {
		return getException(clazz, null, error, arguments);
	}

	@Override
	public <T extends BaseException> T createException(Class<? extends BaseException> clazz, Exception cause) {
		return getException(clazz, cause, null, null);
	}

	@Override
	public <T extends BaseException> T createException(Class<? extends BaseException> clazz, Exception cause,
			ErrorEnum error) {
		return getException(clazz, cause, error, null);
	}

	@Override
	public <T extends BaseException> T createException(Class<? extends BaseException> clazz, Exception cause,
			ErrorEnum error, Object... arguments) {
		return getException(clazz, cause, error, arguments);
	}

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
