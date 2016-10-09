package com.jci.flatfile.exception;

import com.jci.enums.ModuleEnum;
import com.jci.exception.Journalable;
import com.jci.exception.Mailable;
import com.jci.exception.SystemException;

public class FlatFileException extends SystemException  implements Journalable, Mailable {

	private static final long serialVersionUID = -7933828539020366247L;

	@Override
	public ModuleEnum getModule() {
		return ModuleEnum.FLATFILE;
	}
}