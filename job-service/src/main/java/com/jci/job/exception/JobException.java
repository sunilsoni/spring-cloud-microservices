package com.jci.job.exception;

import com.jci.exception.BusinessException;
import com.jci.utils.ModuleEnum;

public class JobException extends BusinessException {

	private static final long serialVersionUID = 120751912746735745L;

	@Override
	public ModuleEnum getModule() {
		return ModuleEnum.JOB;
	}
}