package com.jci.locale;


import java.util.Locale;

public interface LocaleService {

	Locale getCurrentLocale();

	String getMessage(String key);

}