package com.jci.po.dto.res;

class PoDetails {

	private String key;
	private String poNo;
	private String description;
	private int status;
	private String sourceErp;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getPoNo() {
		return poNo;
	}
	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getSourceErp() {
		return sourceErp;
	}
	public void setSourceErp(String sourceErp) {
		this.sourceErp = sourceErp;
	}
	@Override
	public String toString() {
		return "PoDetails [key=" + key + ", poNo=" + poNo + ", description=" + description + ", status=" + status
				+ ", sourceErp=" + sourceErp + "]";
	}
	
	
}
