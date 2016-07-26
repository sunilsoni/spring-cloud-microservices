package com.jci.po.azure.data;

import java.util.Date;

public class AzureRequest {
	
    private String partitionValue;
    private Date timestamp;
    private int  status;
    private String tableName;

    public String getPartitionValue() {
        return this.partitionValue;
    }

    public void setPartitionValue(String partitionValue) {
        this.partitionValue = partitionValue;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }


    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "AzureRequest [ partitionValue=" + partitionValue
				+ ", timestamp=" + timestamp + ", status=" + status + ", tableName=" + tableName + "]";
	}

}
