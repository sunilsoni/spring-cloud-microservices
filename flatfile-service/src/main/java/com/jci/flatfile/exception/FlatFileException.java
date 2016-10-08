package com.jci.flatfile.exception;

import com.jci.exception.BusinessException;
import com.jci.utils.ModuleEnum;

public class FlatFileException extends BusinessException {

	private static final long serialVersionUID = -7933828539020366247L;

	@Override
	public ModuleEnum getModule() {
		return ModuleEnum.FLATFILE;
	}
}