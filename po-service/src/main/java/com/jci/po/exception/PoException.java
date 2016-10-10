package com.jci.po.exception;

import com.jci.enums.ModuleEnum;
import com.jci.exception.Journalable;
import com.jci.exception.Mailable;
import com.jci.exception.SystemException;

public class PoException  extends SystemException  implements Journalable, Mailable {

	private static final long serialVersionUID = 8275229972675314205L;

	@Override
	public ModuleEnum getModule() {
		return ModuleEnum.PO;
	}
}