package com.jci.po.dto.req;

import com.jci.po.azure.query.PaginationParam;

public class PoItemDetailReq {

	private String erpName;
	private String poNum;
	private String tableName;
	private int size;
	private PaginationParam paginationParam;

	public String getErpName() {
		return erpName;
	}

	public void setErpName(String erpName) {
		this.erpName = erpName;
	}

	public String getPoNum() {
		return poNum;
	}

	public void setPoNum(String poNum) {
		this.poNum = poNum;
	}

	public PaginationParam getPaginationParam() {
		return paginationParam;
	}

	public void setPaginationParam(PaginationParam paginationParam) {
		this.paginationParam = paginationParam;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "PoItemDetailReq [erpName=" + erpName + ", poNum=" + poNum + ", tableName=" + tableName + ", size="
				+ size + ", paginationParam=" + paginationParam + "]";
	}

	


	
}
