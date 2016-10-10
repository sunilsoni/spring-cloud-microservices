package com.jci.mail;

import java.util.Date;

import com.jci.enums.CollectionEnum;
import com.jci.enums.ModuleEnum;
import com.jci.enums.RecordLevelEnum;



/**
 * Journal to record errors.
 */
public class JournalRecord extends BaseObject implements Collectionable {

	/** The module. */
	// module
	private ModuleEnum module;

	/** The level. */
	// record level
	private RecordLevelEnum level;

	/** The message. */
	// message text
	private String message;

	/** The created. */
	// create date
	private Date created;

	/** The trace. */
	// stack trace
	private String[] trace;

	/**
	 * Gets the module.
	 *
	 * @return the module
	 */
	public ModuleEnum getModule() {
		return module;
	}

	/**
	 * Sets the module.
	 *
	 * @param module the new module
	 */
	public void setModule(ModuleEnum module) {
		this.module = module;
	}

	/**
	 * Gets the level.
	 *
	 * @return the level
	 */
	public RecordLevelEnum getLevel() {
		return level;
	}

	/**
	 * Sets the level.
	 *
	 * @param level the new level
	 */
	public void setLevel(RecordLevelEnum level) {
		this.level = level;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets the created.
	 *
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * Sets the created.
	 *
	 * @param created the new created
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * Gets the trace.
	 *
	 * @return the trace
	 */
	public String[] getTrace() {
		return trace;
	}

	/**
	 * Sets the trace.
	 *
	 * @param trace the new trace
	 */
	public void setTrace(String[] trace) {
		this.trace = trace;
	}

	/* (non-Javadoc)
	 * @see com.jci.mail.Collectionable#getCollectionName()
	 */
	@Override
	public String getCollectionName() {
		return CollectionEnum.JOURNAL.getName();
	}

}
