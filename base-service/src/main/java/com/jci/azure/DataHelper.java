/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.azure;




/**
 * <p>
 * <strong>The Class DataHelper for Azure.</strong>
 * <p>
 *
 * @author csonisk
 */
public class DataHelper {
	
    /** The partition value. */
    private String partitionValue;
    
    /** The po num. */
    private String poNum;
    
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

	/**
	 * Gets the po num.
	 *
	 * @return the po num
	 */
	public String getPoNum() {
		return poNum;
	}

	/**
	 * Sets the po num.
	 *
	 * @param poNum the new po num
	 */
	public void setPoNum(String poNum) {
		this.poNum = poNum;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DataHelper [partitionValue=" + partitionValue + ", poNum=" + poNum + ", tableName=" + tableName
				+ ", erpName=" + erpName + ", isErrorDataRequired=" + isErrorDataRequired + "]";
	}

	




}
