/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.azure;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

/**
 * <p>
 * <strong>The Class DataHelper for Azure.</strong>
 * <p>
 *
 * @author csonisk
 */
@Data
@Builder
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

    @Tolerate
	public DataHelper() {
		super();
	}
    
  
}
