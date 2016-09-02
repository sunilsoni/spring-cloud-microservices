package com.jci.supp.azure.data;

import org.apache.commons.lang3.StringUtils;

import com.jci.supp.azure.query.ScrollingParam;
import com.microsoft.azure.storage.ResultContinuation;
import com.microsoft.azure.storage.ResultContinuationType;

public class DataUtil {
	
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
