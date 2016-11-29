/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.entity;

import com.microsoft.azure.storage.table.TableServiceEntity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

/**
 * The Class MiscDataEntity.
 */
@Data
@Builder
public class MiscDataEntity   extends TableServiceEntity {

	/**
	 * Instantiates a new misc data entity.
	 *
	 * @param partitionKey the partition key
	 * @param rowKey the row key
	 */
	@Tolerate
	public MiscDataEntity(String partitionKey, String rowKey) {
		this.partitionKey = partitionKey;
		this.rowKey = rowKey; 
	}

	/** The po intransit count. */
	private Integer poIntransitCount;//1
	
	/** The po processed count. */
	private Integer poProcessedCount;//2
	
	/** The po error count. */
	private Integer poErrorCount;//3
	
	/** The gr intransit count. */
	private Integer grIntransitCount;//1
    
    /** The gr processed count. */
    private Integer grProcessedCount;//2
    
    /** The gr error count. */
    private Integer grErrorCount;
	
    /** The item intransit count. */
  	private Integer itemIntransitCount;//1
  	
  	/** The item processed count. */
  	private Integer itemProcessedCount;//2
  	
  	/** The item error count. */
  	private Integer itemErrorCount;//3
  	
  	/** The supp intransit count. */
  	private Integer suppIntransitCount;//1
  	
  	/** The supp processed count. */
  	private Integer suppProcessedCount;//2
  	
  	/** The supp error count. */
  	private Integer suppErrorCount;//3
  	
	    /* (non-Javadoc)
    	 * @see java.lang.Object#equals(java.lang.Object)
    	 */
    	@Override
	    public boolean equals(Object obj) {
	        if (this == obj)
	            return true;
	        if (obj == null)
	            return false;
	        if (getClass() != obj.getClass())
	            return false;
	        MiscDataEntity other = (MiscDataEntity) obj;
	        if (partitionKey == null) {
	            if (other.partitionKey != null)
	                return false;
	        } else if (!partitionKey.equals(other.partitionKey))
	            return false;
	        if (rowKey == null) {
	            if (other.rowKey != null)
	                return false;
	        } else if (!rowKey.equals(other.rowKey))
	            return false;
	        return true;
	    }

}
