package com.jci.flatfile.exception;

import com.jci.enums.ModuleEnum;
import com.jci.exception.Journalable;
import com.jci.exception.Mailable;
import com.jci.exception.SystemException;


/**
 * The Class FlatFileException.
 */
public class FlatFileException extends SystemException  implements Journalable, Mailable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7933828539020366247L;

	/* (non-Javadoc)
	 * @see com.jci.exception.BaseException#getModule()
	 */
	@Override
	public ModuleEnum getModule() {
		return ModuleEnum.FLATFILE;
	}
}