package com.jci.job.exception;

import com.jci.enums.ModuleEnum;
import com.jci.exception.Journalable;
import com.jci.exception.Mailable;
import com.jci.exception.SystemException;

public class JobException extends SystemException  implements Journalable, Mailable {

	private static final long serialVersionUID = 120751912746735745L;

	@Override
	public ModuleEnum getModule() {
		return ModuleEnum.JOB;
	}
}