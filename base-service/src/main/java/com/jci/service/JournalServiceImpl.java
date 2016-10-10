package com.jci.service;

import java.util.Date;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.stereotype.Service;

import com.jci.enums.ModuleEnum;
import com.jci.enums.RecordLevelEnum;
import com.jci.exception.BaseException;
import com.jci.mail.JournalRecord;
import com.jci.mail.JournalService;



/**
 * The Class JournalServiceImpl.
 */
@Service
public class JournalServiceImpl implements JournalService { // NO_UCD (unused code)

	/*@Autowired
	private JournalRepository journalRepository;

	@Override
	public JournalRecord addRecord(JournalRecord record) {
		return journalRepository.save(record);
	}*/

	/* (non-Javadoc)
	 * @see com.jci.mail.JournalService#addError(java.lang.Throwable)
	 */
	@Override
	public JournalRecord addError(Throwable e) {
		JournalRecord record = new JournalRecord();
		record.setLevel(RecordLevelEnum.ERROR);
		record.setTrace(ExceptionUtils.getStackFrames(e));
		record.setMessage(e.getLocalizedMessage());
		record.setCreated(new Date());
		if (e instanceof BaseException) {
			record.setModule(((BaseException) e).getModule());
		} else {
			record.setModule(ModuleEnum.UNKNOWN);
		}
		//return addRecord(record);
		return null;
	}

}
