package com.jci.service;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.LocaleResolver;

import com.jci.locale.LocaleService;



/**
 * The Class LocaleServiceImpl.
 */
@Service
public class LocaleServiceImpl implements LocaleService { // NO_UCD (unused code)

	/** The request. */
	@Autowired(required = false)
	private HttpServletRequest request;

	/** The locale resolver. */
	@Autowired(required = false)
	private LocaleResolver localeResolver;

	/** The message source. */
	@Resource(name = "messageSource")
	private MessageSource messageSource;

	/* (non-Javadoc)
	 * @see com.jci.locale.LocaleService#getCurrentLocale()
	 */
	@Override
	public Locale getCurrentLocale() {
		return (request != null && localeResolver != null) ? localeResolver.resolveLocale(request) : Locale
				.getDefault();
	}

	/* (non-Javadoc)
	 * @see com.jci.locale.LocaleService#getMessage(java.lang.String)
	 */
	@Override
	public String getMessage(String key) {
		String result = null;
		try {
			result = messageSource.getMessage(key, new Object[0], getCurrentLocale());
		} catch (NoSuchMessageException e) {
			result = "?" + key + "?";
		}
		return result;
	}
}
