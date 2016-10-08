/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */
package com.jci.entity;

import com.microsoft.azure.storage.table.TableServiceEntity;



/**
 * The Class MiscDataEntity.
 */
public class MiscDataEntity   extends TableServiceEntity {

	/**
	 * Instantiates a new misc data entity.
	 *
	 * @param partitionKey the partition key
	 * @param rowKey the row key
	 */
	public MiscDataEntity(String partitionKey, String rowKey) {
		this.partitionKey = partitionKey;
		this.rowKey = rowKey; 
	}

	/**
	 * Instantiates a new misc data entity.
	 */
	public MiscDataEntity() {
		
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
  	
	/**
	 * Gets the gr intransit count.
	 *
	 * @return the gr intransit count
	 */
	public Integer getGrIntransitCount() {
        return grIntransitCount;
    }

    /**
     * Sets the gr intransit count.
     *
     * @param grIntransitCount the new gr intransit count
     */
    public void setGrIntransitCount(Integer grIntransitCount) {
        this.grIntransitCount = grIntransitCount;
    }

    /**
     * Gets the gr processed count.
     *
     * @return the gr processed count
     */
    public Integer getGrProcessedCount() {
        return grProcessedCount;
    }

    /**
     * Sets the gr processed count.
     *
     * @param grProcessedCount the new gr processed count
     */
    public void setGrProcessedCount(Integer grProcessedCount) {
        this.grProcessedCount = grProcessedCount;
    }

    /**
     * Gets the gr error count.
     *
     * @return the gr error count
     */
    public Integer getGrErrorCount() {
        return grErrorCount;
    }

    /**
     * Sets the gr error count.
     *
     * @param grErrorCount the new gr error count
     */
    public void setGrErrorCount(Integer grErrorCount) {
        this.grErrorCount = grErrorCount;
    }


	/**
	 * Gets the po intransit count.
	 *
	 * @return the po intransit count
	 */
	public Integer getPoIntransitCount() {
		return poIntransitCount;
	}

	/**
	 * Sets the po intransit count.
	 *
	 * @param poIntransitCount the new po intransit count
	 */
	public void setPoIntransitCount(Integer poIntransitCount) {
		this.poIntransitCount = poIntransitCount;
	}

	/**
	 * Gets the po processed count.
	 *
	 * @return the po processed count
	 */
	public Integer getPoProcessedCount() {
		return poProcessedCount;
	}

	/**
	 * Sets the po processed count.
	 *
	 * @param poProcessedCount the new po processed count
	 */
	public void setPoProcessedCount(Integer poProcessedCount) {
		this.poProcessedCount = poProcessedCount;
	}

	/**
	 * Gets the po error count.
	 *
	 * @return the po error count
	 */
	public Integer getPoErrorCount() {
		return poErrorCount;
	}

	/**
	 * Sets the po error count.
	 *
	 * @param poErrorCount the new po error count
	 */
	public void setPoErrorCount(Integer poErrorCount) {
		this.poErrorCount = poErrorCount;
	}

	/**
	 * Gets the item intransit count.
	 *
	 * @return the item intransit count
	 */
	public Integer getItemIntransitCount() {
		return itemIntransitCount;
	}

	/**
	 * Sets the item intransit count.
	 *
	 * @param itemIntransitCount the new item intransit count
	 */
	public void setItemIntransitCount(Integer itemIntransitCount) {
		this.itemIntransitCount = itemIntransitCount;
	}

	/**
	 * Gets the item processed count.
	 *
	 * @return the item processed count
	 */
	public Integer getItemProcessedCount() {
		return itemProcessedCount;
	}

	/**
	 * Sets the item processed count.
	 *
	 * @param itemProcessedCount the new item processed count
	 */
	public void setItemProcessedCount(Integer itemProcessedCount) {
		this.itemProcessedCount = itemProcessedCount;
	}

	/**
	 * Gets the item error count.
	 *
	 * @return the item error count
	 */
	public Integer getItemErrorCount() {
		return itemErrorCount;
	}

	/**
	 * Sets the item error count.
	 *
	 * @param itemErrorCount the new item error count
	 */
	public void setItemErrorCount(Integer itemErrorCount) {
		this.itemErrorCount = itemErrorCount;
	}

	/**
	 * Gets the supp intransit count.
	 *
	 * @return the supp intransit count
	 */
	public Integer getSuppIntransitCount() {
		return suppIntransitCount;
	}

	/**
	 * Sets the supp intransit count.
	 *
	 * @param suppIntransitCount the new supp intransit count
	 */
	public void setSuppIntransitCount(Integer suppIntransitCount) {
		this.suppIntransitCount = suppIntransitCount;
	}

	/**
	 * Gets the supp processed count.
	 *
	 * @return the supp processed count
	 */
	public Integer getSuppProcessedCount() {
		return suppProcessedCount;
	}

	/**
	 * Sets the supp processed count.
	 *
	 * @param suppProcessedCount the new supp processed count
	 */
	public void setSuppProcessedCount(Integer suppProcessedCount) {
		this.suppProcessedCount = suppProcessedCount;
	}

	/**
	 * Gets the supp error count.
	 *
	 * @return the supp error count
	 */
	public Integer getSuppErrorCount() {
		return suppErrorCount;
	}

	/**
	 * Sets the supp error count.
	 *
	 * @param suppErrorCount the new supp error count
	 */
	public void setSuppErrorCount(Integer suppErrorCount) {
		this.suppErrorCount = suppErrorCount;
	}

	   /* (non-Javadoc)
   	 * @see java.lang.Object#hashCode()
   	 */
   	@Override
	    public int hashCode() {
	        final int prime = 31;
	        int result = 1;
	        result = prime * result + ((partitionKey == null) ? 0 : partitionKey.hashCode());
	        result = prime * result + ((rowKey == null) ? 0 : rowKey.hashCode());
	        return result;
	    }

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

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "MiscDataEntity [poIntransitCount=" + poIntransitCount + ", poProcessedCount=" + poProcessedCount
					+ ", poErrorCount=" + poErrorCount + ", grIntransitCount=" + grIntransitCount
					+ ", grProcessedCount=" + grProcessedCount + ", grErrorCount=" + grErrorCount
					+ ", itemIntransitCount=" + itemIntransitCount + ", itemProcessedCount=" + itemProcessedCount
					+ ", itemErrorCount=" + itemErrorCount + ", suppIntransitCount=" + suppIntransitCount
					+ ", suppProcessedCount=" + suppProcessedCount + ", suppErrorCount=" + suppErrorCount + "]";
		}


}
