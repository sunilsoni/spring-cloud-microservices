package com.jci.mail;




/**
 * Service to work with system journal.
 */
public interface JournalService {

	/**
	 * Add exception to journal.
	 *
	 * @param e            exception
	 * @return journal record
	 */
	public JournalRecord addError(Throwable e);

}
