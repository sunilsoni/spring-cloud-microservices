package com.jci.supp.exception;

import com.jci.exception.BusinessException;
import com.jci.utils.ModuleEnum;

public class SuppException  extends BusinessException {

	private static final long serialVersionUID = -1456338665127362928L;

	@Override
	public ModuleEnum getModule() {
		return ModuleEnum.SUPPLIER;
	}
}
