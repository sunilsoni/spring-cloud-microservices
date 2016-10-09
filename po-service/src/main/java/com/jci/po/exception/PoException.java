package com.jci.po.exception;

import com.jci.enums.ModuleEnum;
import com.jci.exception.BusinessException;

public class PoException  extends BusinessException {

	private static final long serialVersionUID = 8275229972675314205L;

	@Override
	public ModuleEnum getModule() {
		return ModuleEnum.PO;
	}
}