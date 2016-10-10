/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.azure;

import org.apache.commons.lang3.StringUtils;

import com.microsoft.azure.storage.ResultContinuation;
import com.microsoft.azure.storage.ResultContinuationType;



/**
 * <p>
 * <strong>The Class DataUtil for Azure.</strong>
 * <p>
 *
 * @author csonisk
 */
public class DataUtil {
	
	/**
	 * Gets the continuation token.
	 *
	 * @param param the param
	 * @return the continuation token
	 */
	public static ResultContinuation getContinuationToken(ScrollingParam param) {
		ResultContinuation token = null ;
		if(param == null ) {
			return null ;
		} else {
			token = new ResultContinuation();
			token.setContinuationType(ResultContinuationType.TABLE);
		}
		
		if(param != null && !StringUtils.isBlank(param.getPartition())) {
			token.setNextPartitionKey(param.getPartition());
		}
		
		if(param != null && !StringUtils.isBlank(param.getRow())) {
			token.setNextRowKey(param.getRow());
		}
		
		return token ;
	}
}
