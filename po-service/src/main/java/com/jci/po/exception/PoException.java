package com.jci.po.exception;

import com.jci.enums.ModuleEnum;
import com.jci.exception.Journalable;
import com.jci.exception.Mailable;
import com.jci.exception.SystemException;


/**
 * The Class PoException.
 */
public class PoException  extends SystemException  implements Journalable, Mailable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8275229972675314205L;

	/* (non-Javadoc)
	 * @see com.jci.exception.BaseException#getModule()
	 */
	@Override
	public ModuleEnum getModule() {
		return ModuleEnum.PO;
	}
}