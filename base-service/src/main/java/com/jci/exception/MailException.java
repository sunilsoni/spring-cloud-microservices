package com.jci.exception;

import com.jci.enums.ModuleEnum;

public class MailException extends SystemException implements Journalable {

	private static final long serialVersionUID = 5463957881998636824L;

	@Override
	public ModuleEnum getModule() {
		return ModuleEnum.MAIL;
	}
}
