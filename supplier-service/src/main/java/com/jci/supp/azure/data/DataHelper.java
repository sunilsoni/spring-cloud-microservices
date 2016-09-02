/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.supp.azure.data;

import java.util.Date;


/**
 * The Class DataHelper.
 */
public class DataHelper {
	
    /** The partition value. */
    private String partitionValue;
    
    /** The timestamp. */
    private Date timestamp;
    
    /** The status. */
    private int  status;
    
    /** The table name. */
    private String tableName;
    
    /** The erp name. */
    private String erpName;
    
    /** The is error data required. */
    private boolean isErrorDataRequired;
    
    /**
     * Gets the partition value.
     *
     * @return the partition value
     */
    public String getPartitionValue() {
        return this.partitionValue;
    }

    /**
     * Gets the erp name.
     *
     * @return the erp name
     */
    public String getErpName() {
		return erpName;
	}

	/**
	 * Sets the erp name.
	 *
	 * @param erpName the new erp name
	 */
	public void setErpName(String erpName) {
		this.erpName = erpName;
	}

	/**
	 * Sets the partition value.
	 *
	 * @param partitionValue the new partition value
	 */
	public void setPartitionValue(String partitionValue) {
        this.partitionValue = partitionValue;
    }

    /**
     * Gets the timestamp.
     *
     * @return the timestamp
     */
    public Date getTimestamp() {
        return this.timestamp;
    }

    /**
     * Sets the timestamp.
     *
     * @param timestamp the new timestamp
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }


    /**
     * Gets the table name.
     *
     * @return the table name
     */
    public String getTableName() {
        return this.tableName;
    }

    /**
     * Sets the table name.
     *
     * @param tableName the new table name
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * Checks if is error data required.
	 *
	 * @return true, if is error data required
	 */
	public boolean isErrorDataRequired() {
		return isErrorDataRequired;
	}

	/**
	 * Sets the error data required.
	 *
	 * @param isErrorDataRequired the new error data required
	 */
	public void setErrorDataRequired(boolean isErrorDataRequired) {
		this.isErrorDataRequired = isErrorDataRequired;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DataHelper [partitionValue=" + partitionValue + ", timestamp=" + timestamp + ", status=" + status
				+ ", tableName=" + tableName + ", erpName=" + erpName + ", isErrorDataRequired=" + isErrorDataRequired
				+ "]";
	}




}
