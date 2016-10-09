package com.jci.item.exception;

import com.jci.enums.ModuleEnum;
import com.jci.exception.BusinessException;

public class ItemException  extends BusinessException {

	private static final long serialVersionUID = -1775103803970290347L;

	@Override
	public ModuleEnum getModule() {
		return ModuleEnum.ITEM;
	}
}