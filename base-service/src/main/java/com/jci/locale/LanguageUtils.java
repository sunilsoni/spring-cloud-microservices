package com.jci.locale;

import java.util.Locale;



/**
 * The Class LanguageUtils.
 */
public class LanguageUtils {

	/** The Constant ENGLISH. */
	private static final Locale ENGLISH = Locale.ENGLISH;

	/**
	 * Gets the language by locale.
	 *
	 * @param locale the locale
	 * @return the language by locale
	 */
	public static String getLanguageByLocale(Locale locale) {
		if (locale != null && locale.getLanguage() != null) {
			switch (locale.getLanguage()) {
			// Azerbaijani
			case "az":
				// Armenian
			case "hy":
				// Byelorussia
			case "be":
				// Kazakh
			case "kk":
				// Kirghiz
			case "ky":
				// Moldavian
			case "mo":
				// Russian
			case "ru":
				// Tajik
			case "tg":
				// Turkmen
			case "tk":
				// Uzbek
			case "uz":
				// Ukrainian
			case "uk":
				return "ru";
			default:
				return "en";
			}
		} else {
			return "en";
		}
	}

	/**
	 * Gets the english locale.
	 *
	 * @return the english locale
	 */
	public static Locale getEnglishLocale() {
		return ENGLISH;
	}


}
