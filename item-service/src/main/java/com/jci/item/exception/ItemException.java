package com.jci.item.exception;

import com.jci.enums.ModuleEnum;
import com.jci.exception.Journalable;
import com.jci.exception.Mailable;
import com.jci.exception.SystemException;

public class ItemException   extends SystemException  implements Journalable, Mailable {

	private static final long serialVersionUID = -1775103803970290347L;

	@Override
	public ModuleEnum getModule() {
		return ModuleEnum.ITEM;
	}
}