package com.jci.locale;


import java.util.Locale;



/**
 * The Interface LocaleService.
 */
public interface LocaleService {

	/**
	 * Gets the current locale.
	 *
	 * @return the current locale
	 */
	Locale getCurrentLocale();

	/**
	 * Gets the message.
	 *
	 * @param key the key
	 * @return the message
	 */
	String getMessage(String key); // NO_UCD (unused code)

}