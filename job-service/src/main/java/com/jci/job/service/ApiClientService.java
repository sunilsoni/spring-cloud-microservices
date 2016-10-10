/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.job.service;

/**
 * <p>
 * <strong> The Api Client Service.</strong>
 * <p>
 *
 * @author csonisk
 */
public interface ApiClientService {
	
	String getPoDetails(String plant, String erp, String region) ;
	
	String getGrDetails(String plant, String erp, String region) ;
	
	String getItemDetails(String plant, String erp, String region) ;
	
	String getSuppDetails(String plant, String erp, String region)  ;
	
	/**
	 * Process po flat file.
	 */
	void processPoFlatFile () ;
	
	/**
	 * Process gr flat file.
	 */
	void processGrFlatFile () ;
	
	/**
	 * Process supp flat file.
	 */
	void processSuppFlatFile () ;
	
	/**
	 * Process item flat file.
	 */
	void processItemFlatFile () ;

}
