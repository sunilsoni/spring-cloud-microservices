package com.jci.exception;

import com.jci.enums.ModuleEnum;



/**
 * The Class MailException.
 */
public class MailException extends SystemException implements Journalable { // NO_UCD (unused code)

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5463957881998636824L;

	/* (non-Javadoc)
	 * @see com.jci.exception.BaseException#getModule()
	 */
	@Override
	public ModuleEnum getModule() {
		return ModuleEnum.MAIL;
	}
}
