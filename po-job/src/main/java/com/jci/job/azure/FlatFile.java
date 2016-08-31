package com.jci.job.azure;

public class FlatFile {
	
	 private  String e2openUrl;
	  private  String poUrl;
	  private  String itemUrl;
	  private  String supplierUrl;
	  private  String regionUrl;
	  private  String senderDuns;
	  private  String receiverDuns;
	  private  String messageType;
	  private  String version;
	  private  String siteId;
	  private  String dateFormat;
	  private  String timeZone;
	  
	  FlatFile(String e2openUrl, String poUrl, String itemUrl, String supplierUrl, String senderDuns,String receiverDuns, String messageType, String version,String siteId, String dateFormat, String timeZone,String regionUrl) {
		  this.e2openUrl  = e2openUrl;
		  this.poUrl  = poUrl;
		this.itemUrl  = itemUrl;
		this.supplierUrl  = supplierUrl;
		
		this.senderDuns  = senderDuns;
		this.receiverDuns  = receiverDuns;
		this.messageType  = messageType;
		this.version  = version;
		this.siteId  = siteId;
		this.dateFormat  = dateFormat;
		this.timeZone  = timeZone;
		this.regionUrl  = regionUrl;
	  }
	  
		public String getPoUrl() {
		return poUrl;
	}

	public String getE2openUrl() {
			return e2openUrl;
		}

		public void setE2openUrl(String e2openUrl) {
			this.e2openUrl = e2openUrl;
		}

	public void setPoUrl(String poUrl) {
		this.poUrl = poUrl;
	}

	public String getItemUrl() {
		return itemUrl;
	}

	public void setItemUrl(String itemUrl) {
		this.itemUrl = itemUrl;
	}

		public String getSupplierUrl() {
			return supplierUrl;
		}

		public void setSupplierUrl(String supplierUrl) {
			this.supplierUrl = supplierUrl;
		}

		public String getSenderDuns() {
			return senderDuns;
		}

		public void setSenderDuns(String senderDuns) {
			this.senderDuns = senderDuns;
		}

		public String getReceiverDuns() {
			return receiverDuns;
		}

		public void setReceiverDuns(String receiverDuns) {
			this.receiverDuns = receiverDuns;
		}

		public String getMessageType() {
			return messageType;
		}

		public void setMessageType(String messageType) {
			this.messageType = messageType;
		}

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}

		public String getSiteId() {
			return siteId;
		}

		public void setSiteId(String siteId) {
			this.siteId = siteId;
		}

		public String getDateFormat() {
			return dateFormat;
		}

		public void setDateFormat(String dateFormat) {
			this.dateFormat = dateFormat;
		}

		public String getTimeZone() {
			return timeZone;
		}

		public void setTimeZone(String timeZone) {
			this.timeZone = timeZone;
		}

		public String getRegionUrl() {
			return regionUrl;
		}

		public void setRegionUrl(String regionUrl) {
			this.regionUrl = regionUrl;
		}
		
		
		  
}
