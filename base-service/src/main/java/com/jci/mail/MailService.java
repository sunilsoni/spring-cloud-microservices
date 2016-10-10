package com.jci.mail;




/**
 * Service for send email.
 */
public interface MailService {

	/**
	 * Send a message from address suniltct@gmail.com to single address
	 *
	 * @param to            address of the recipient
	 * @param subject            message subject
	 * @param text            message text
	 */
	public void sendNoreplyMessage(String to, String subject, String text); // NO_UCD (unused code)

	/**
	 * Send a message from address suniltct@gmail.com to multiply addresses
	 *
	 * @param to            addresses of the recipient
	 * @param subject            message subject
	 * @param text            message text
	 */
	public void sendNoreplyMessage(String[] to, String subject, String text);
}
