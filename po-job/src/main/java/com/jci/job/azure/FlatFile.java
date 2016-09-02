/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.job.azure;

/**
 * The Class FlatFile.
 */
public class FlatFile {
	
	 /** The e 2 open url. */
 	private  String e2openUrl;
	  
  	/** The po url. */
  	private  String poUrl;
	  
  	/** The item url. */
  	private  String itemUrl;
	  
  	/** The supp url. */
  	private  String suppUrl;
	  
  	/** The region url. */
  	private  String regionUrl;
	  
  	/** The sender duns. */
  	private  String senderDuns;
	  
  	/** The receiver duns. */
  	private  String receiverDuns;
	  
  	/** The message type. */
  	private  String messageType;
	  
  	/** The version. */
  	private  String version;
	  
  	/** The site id. */
  	private  String siteId;
	  
  	/** The date format. */
  	private  String dateFormat;
	  
  	/** The time zone. */
  	private  String timeZone;
	  
	  /**
  	 * Instantiates a new flat file.
  	 *
  	 * @param e2openUrl the e 2 open url
  	 * @param poUrl the po url
  	 * @param itemUrl the item url
  	 * @param suppUrl the supp url
  	 * @param senderDuns the sender duns
  	 * @param receiverDuns the receiver duns
  	 * @param messageType the message type
  	 * @param version the version
  	 * @param siteId the site id
  	 * @param dateFormat the date format
  	 * @param timeZone the time zone
  	 * @param regionUrl the region url
  	 */
  	FlatFile(String e2openUrl, String poUrl, String itemUrl, String suppUrl, String senderDuns,String receiverDuns, String messageType, String version,String siteId, String dateFormat, String timeZone,String regionUrl) {
		  this.e2openUrl  = e2openUrl;
		  this.poUrl  = poUrl;
		this.itemUrl  = itemUrl;
		this.suppUrl  = suppUrl;
		
		this.senderDuns  = senderDuns;
		this.receiverDuns  = receiverDuns;
		this.messageType  = messageType;
		this.version  = version;
		this.siteId  = siteId;
		this.dateFormat  = dateFormat;
		this.timeZone  = timeZone;
		this.regionUrl  = regionUrl;
	  }
	  
		/**
		 * Gets the po url.
		 *
		 * @return the po url
		 */
		public String getPoUrl() {
		return poUrl;
	}

	/**
	 * Gets the e 2 open url.
	 *
	 * @return the e 2 open url
	 */
	public String getE2openUrl() {
			return e2openUrl;
		}

		/**
		 * Sets the e 2 open url.
		 *
		 * @param e2openUrl the new e 2 open url
		 */
		public void setE2openUrl(String e2openUrl) {
			this.e2openUrl = e2openUrl;
		}

	/**
	 * Sets the po url.
	 *
	 * @param poUrl the new po url
	 */
	public void setPoUrl(String poUrl) {
		this.poUrl = poUrl;
	}

	/**
	 * Gets the item url.
	 *
	 * @return the item url
	 */
	public String getItemUrl() {
		return itemUrl;
	}

	/**
	 * Sets the item url.
	 *
	 * @param itemUrl the new item url
	 */
	public void setItemUrl(String itemUrl) {
		this.itemUrl = itemUrl;
	}

		
		/**
		 * Gets the sender duns.
		 *
		 * @return the sender duns
		 */
		public String getSenderDuns() {
			return senderDuns;
		}

		/**
		 * Sets the sender duns.
		 *
		 * @param senderDuns the new sender duns
		 */
		public void setSenderDuns(String senderDuns) {
			this.senderDuns = senderDuns;
		}

		/**
		 * Gets the receiver duns.
		 *
		 * @return the receiver duns
		 */
		public String getReceiverDuns() {
			return receiverDuns;
		}

		/**
		 * Sets the receiver duns.
		 *
		 * @param receiverDuns the new receiver duns
		 */
		public void setReceiverDuns(String receiverDuns) {
			this.receiverDuns = receiverDuns;
		}

		/**
		 * Gets the message type.
		 *
		 * @return the message type
		 */
		public String getMessageType() {
			return messageType;
		}

		/**
		 * Sets the message type.
		 *
		 * @param messageType the new message type
		 */
		public void setMessageType(String messageType) {
			this.messageType = messageType;
		}

		/**
		 * Gets the version.
		 *
		 * @return the version
		 */
		public String getVersion() {
			return version;
		}

		/**
		 * Sets the version.
		 *
		 * @param version the new version
		 */
		public void setVersion(String version) {
			this.version = version;
		}

		/**
		 * Gets the site id.
		 *
		 * @return the site id
		 */
		public String getSiteId() {
			return siteId;
		}

		/**
		 * Sets the site id.
		 *
		 * @param siteId the new site id
		 */
		public void setSiteId(String siteId) {
			this.siteId = siteId;
		}

		/**
		 * Gets the date format.
		 *
		 * @return the date format
		 */
		public String getDateFormat() {
			return dateFormat;
		}

		/**
		 * Sets the date format.
		 *
		 * @param dateFormat the new date format
		 */
		public void setDateFormat(String dateFormat) {
			this.dateFormat = dateFormat;
		}

		/**
		 * Gets the time zone.
		 *
		 * @return the time zone
		 */
		public String getTimeZone() {
			return timeZone;
		}

		/**
		 * Sets the time zone.
		 *
		 * @param timeZone the new time zone
		 */
		public void setTimeZone(String timeZone) {
			this.timeZone = timeZone;
		}

		/**
		 * Gets the region url.
		 *
		 * @return the region url
		 */
		public String getRegionUrl() {
			return regionUrl;
		}

		/**
		 * Sets the region url.
		 *
		 * @param regionUrl the new region url
		 */
		public void setRegionUrl(String regionUrl) {
			this.regionUrl = regionUrl;
		}

		/**
		 * Gets the supp url.
		 *
		 * @return the supp url
		 */
		public String getSuppUrl() {
			return suppUrl;
		}

		/**
		 * Sets the supp url.
		 *
		 * @param suppUrl the new supp url
		 */
		public void setSuppUrl(String suppUrl) {
			this.suppUrl = suppUrl;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "FlatFile [e2openUrl=" + e2openUrl + ", poUrl=" + poUrl + ", itemUrl=" + itemUrl + ", suppUrl="
					+ suppUrl + ", regionUrl=" + regionUrl + ", senderDuns=" + senderDuns + ", receiverDuns="
					+ receiverDuns + ", messageType=" + messageType + ", version=" + version + ", siteId=" + siteId
					+ ", dateFormat=" + dateFormat + ", timeZone=" + timeZone + "]";
		}
		
		
		  
}
