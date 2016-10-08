package com.jci.flatfile.service;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.LocaleResolver;

import com.jci.locale.LocaleService;

@Service
public class LocaleServiceImpl implements LocaleService {

	@Autowired(required = false)
	private HttpServletRequest request;

	@Autowired(required = false)
	private LocaleResolver localeResolver;

	@Resource(name = "messageSource")
	private MessageSource messageSource;

	@Override
	public Locale getCurrentLocale() {
		return (request != null && localeResolver != null) ? localeResolver.resolveLocale(request) : Locale
				.getDefault();
	}

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
