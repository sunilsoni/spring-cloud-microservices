package com.jci.mail;


import java.util.Locale;
import java.util.Map;

import com.jci.locale.LanguageUtils;



/**
 * The Class MailTemplate.
 */
public class MailTemplate {

    /** The subject. */
    private Map<String, String> subject;

    /** The text. */
    private Map<String, String> text;

    /**
     * Gets the subject.
     *
     * @return the subject
     */
    public Map<String, String> getSubject() {
        return subject;
    }

    /**
     * Sets the subject.
     *
     * @param subject the subject
     */
    public void setSubject(Map<String, String> subject) {
        this.subject = subject;
    }

    /**
     * Gets the text.
     *
     * @return the text
     */
    public Map<String, String> getText() {
        return text;
    }

    /**
     * Sets the text.
     *
     * @param text the text
     */
    public void setText(Map<String, String> text) {
        this.text = text;
    }

    /**
     * Gets the subject.
     *
     * @param locale the locale
     * @return the subject
     */
    public String getSubject(Locale locale) {
        String lang = LanguageUtils.getLanguageByLocale(locale);
        return subject.get(lang);
    }

    /**
     * Gets the text.
     *
     * @param locale the locale
     * @return the text
     */
    public String getText(Locale locale) {
        String lang = LanguageUtils.getLanguageByLocale(locale);
        return text.get(lang);
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MailTemplate [subject=" + subject + ", text=" + text + "]";
	}
    
    
}
