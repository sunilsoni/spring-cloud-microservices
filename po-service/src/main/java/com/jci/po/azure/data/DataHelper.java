package com.jci.po.azure.data;

public class DataHelper {
	
    private String partitionValue;
    private String poNum;
    private String tableName;
    private String erpName;
    private boolean isErrorDataRequired;
    
    public String getPartitionValue() {
        return this.partitionValue;
    }

    public String getErpName() {
		return erpName;
	}

	public void setErpName(String erpName) {
		this.erpName = erpName;
	}

	public void setPartitionValue(String partitionValue) {
        this.partitionValue = partitionValue;
    }



    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }


	public boolean isErrorDataRequired() {
		return isErrorDataRequired;
	}

	public void setErrorDataRequired(boolean isErrorDataRequired) {
		this.isErrorDataRequired = isErrorDataRequired;
	}

	public String getPoNum() {
		return poNum;
	}

	public void setPoNum(String poNum) {
		this.poNum = poNum;
	}

	@Override
	public String toString() {
		return "DataHelper [partitionValue=" + partitionValue + ", poNum=" + poNum + ", tableName=" + tableName
				+ ", erpName=" + erpName + ", isErrorDataRequired=" + isErrorDataRequired + "]";
	}

	




}
