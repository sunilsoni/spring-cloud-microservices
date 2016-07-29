package com.jci.job.entity;

import com.microsoft.azure.storage.table.TableServiceEntity;

public class ItemEntity extends TableServiceEntity {

	public ItemEntity(String partitionKey, String rowKey) {
		this.partitionKey = partitionKey;
		this.rowKey = rowKey; 
	}

	public ItemEntity() {
	}

	private String enterpriseCode;
	private String siteName;
	private String supplierId;
	private String customerItemId;
	private String customerItemDescription;
	private String supplierItemId;
	private String supplierItemDescription;
	private String globalCustomerId;
	private String globalSupplierId;
	private String globalItemId;
	private String eolGuidence;
	private String purchasingGroup;
	private String commodityCode;
	private String classificationCode;
	private Double unitPrice;
	private Double priceBasis;
	private String currency;
	private String uomppo;
	private String criticalItem;
	private int leadTimeDays;
	private int forcastGracePeriod;
	private int blanketExpiryTolerance;
	private int orderGracePeriod;
	private int RequestOrPromiseDateTolerance;
	private Double commitQuantityToleranceMin;
	private Double commitQuantityToleranceMax;
	private Double minDos;
	private Double maxDos;
	private Double TargetDos;
	private Double minToleranceRequestOrPromiseQuantity;
	private Double maxToleranceRequestOrPromiseQuantity;
	private Double minToleranceRequestOrShippedQuantity;
	private Double maxToleranceRequestOrShippedQuantity;
	private Double minToleranceRequestOrReceivedQuantity;
	private Double maxToleranceRequestOrReceivedQuantity;
	private Double minToleranceRequestOrInvoicedQuantity;
	private Double maxToleranceRequestOrInvoicedQuantity;
	private Double minToleranceReceivedOrInvoicedQuantity;
	private Double maxToleranceReceivedOrInvoicedQuantity;
	private Double minToleranceInvoicePriceMismatch;
	private Double maxToleranceInvoicePriceMismatch;
	private int lateShipmentsTolerance;
	private int lateDeliveriesTolerance;
	private Double customerMOQ;
	private Double supplierShipmentSize;
	private String itemHierarchyLevel1;
	private String itemHierarchyLevel2;
	private String itemHierarchyLevel3;
	private String itemHierarchyLevel4;
	private String itemHierarchyLevel5;
	private String planningBucketType;
	private String productLine;
	private String planningBuckets;
	private String serialNumberFlag;
	private String oldPartNumber;
	private String materialType;
	private String materialGroup;
	private String bulkItemFlag;
	private String flexStringCollab04;
	private String flexStringCollab05;
	private String blockShipments;
	private String earlyShipmentTolerance;
	private String overShipmentTolerance;
	private String priceMismatchToleranceMin;
	private String priceMismatchToleranceMax;
	public String getEnterpriseCode() {
		return enterpriseCode;
	}

	public void setEnterpriseCode(String enterpriseCode) {
		this.enterpriseCode = enterpriseCode;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getCustomerItemId() {
		return customerItemId;
	}

	public void setCustomerItemId(String customerItemId) {
		this.customerItemId = customerItemId;
	}

	public String getCustomerItemDescription() {
		return customerItemDescription;
	}

	public void setCustomerItemDescription(String customerItemDescription) {
		this.customerItemDescription = customerItemDescription;
	}

	public String getSupplierItemId() {
		return supplierItemId;
	}

	public void setSupplierItemId(String supplierItemId) {
		this.supplierItemId = supplierItemId;
	}

	public String getSupplierItemDescription() {
		return supplierItemDescription;
	}

	public void setSupplierItemDescription(String supplierItemDescription) {
		this.supplierItemDescription = supplierItemDescription;
	}

	public String getGlobalCustomerId() {
		return globalCustomerId;
	}

	public void setGlobalCustomerId(String globalCustomerId) {
		this.globalCustomerId = globalCustomerId;
	}

	public String getGlobalSupplierId() {
		return globalSupplierId;
	}

	public void setGlobalSupplierId(String globalSupplierId) {
		this.globalSupplierId = globalSupplierId;
	}

	public String getGlobalItemId() {
		return globalItemId;
	}

	public void setGlobalItemId(String globalItemId) {
		this.globalItemId = globalItemId;
	}

	public String getEolGuidence() {
		return eolGuidence;
	}

	public void setEolGuidence(String eolGuidence) {
		this.eolGuidence = eolGuidence;
	}

	public String getPurchasingGroup() {
		return purchasingGroup;
	}

	public void setPurchasingGroup(String purchasingGroup) {
		this.purchasingGroup = purchasingGroup;
	}

	public String getCommodityCode() {
		return commodityCode;
	}

	public void setCommodityCode(String commodityCode) {
		this.commodityCode = commodityCode;
	}

	public String getClassificationCode() {
		return classificationCode;
	}

	public void setClassificationCode(String classificationCode) {
		this.classificationCode = classificationCode;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getPriceBasis() {
		return priceBasis;
	}

	public void setPriceBasis(Double priceBasis) {
		this.priceBasis = priceBasis;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getUomppo() {
		return uomppo;
	}

	public void setUomppo(String uomppo) {
		this.uomppo = uomppo;
	}

	public String getCriticalItem() {
		return criticalItem;
	}

	public void setCriticalItem(String criticalItem) {
		this.criticalItem = criticalItem;
	}

	public int getLeadTimeDays() {
		return leadTimeDays;
	}

	public void setLeadTimeDays(int leadTimeDays) {
		this.leadTimeDays = leadTimeDays;
	}

	public int getForcastGracePeriod() {
		return forcastGracePeriod;
	}

	public void setForcastGracePeriod(int forcastGracePeriod) {
		this.forcastGracePeriod = forcastGracePeriod;
	}

	public int getBlanketExpiryTolerance() {
		return blanketExpiryTolerance;
	}

	public void setBlanketExpiryTolerance(int blanketExpiryTolerance) {
		this.blanketExpiryTolerance = blanketExpiryTolerance;
	}

	public int getOrderGracePeriod() {
		return orderGracePeriod;
	}

	public void setOrderGracePeriod(int orderGracePeriod) {
		this.orderGracePeriod = orderGracePeriod;
	}

	public int getRequestOrPromiseDateTolerance() {
		return RequestOrPromiseDateTolerance;
	}

	public void setRequestOrPromiseDateTolerance(int requestOrPromiseDateTolerance) {
		RequestOrPromiseDateTolerance = requestOrPromiseDateTolerance;
	}

	public Double getCommitQuantityToleranceMin() {
		return commitQuantityToleranceMin;
	}

	public void setCommitQuantityToleranceMin(Double commitQuantityToleranceMin) {
		this.commitQuantityToleranceMin = commitQuantityToleranceMin;
	}

	public Double getCommitQuantityToleranceMax() {
		return commitQuantityToleranceMax;
	}

	public void setCommitQuantityToleranceMax(Double commitQuantityToleranceMax) {
		this.commitQuantityToleranceMax = commitQuantityToleranceMax;
	}

	public Double getMinDos() {
		return minDos;
	}

	public void setMinDos(Double minDos) {
		this.minDos = minDos;
	}

	public Double getMaxDos() {
		return maxDos;
	}

	public void setMaxDos(Double maxDos) {
		this.maxDos = maxDos;
	}

	public Double getTargetDos() {
		return TargetDos;
	}

	public void setTargetDos(Double targetDos) {
		TargetDos = targetDos;
	}

	public Double getMinToleranceRequestOrPromiseQuantity() {
		return minToleranceRequestOrPromiseQuantity;
	}

	public void setMinToleranceRequestOrPromiseQuantity(Double minToleranceRequestOrPromiseQuantity) {
		this.minToleranceRequestOrPromiseQuantity = minToleranceRequestOrPromiseQuantity;
	}

	public Double getMaxToleranceRequestOrPromiseQuantity() {
		return maxToleranceRequestOrPromiseQuantity;
	}

	public void setMaxToleranceRequestOrPromiseQuantity(Double maxToleranceRequestOrPromiseQuantity) {
		this.maxToleranceRequestOrPromiseQuantity = maxToleranceRequestOrPromiseQuantity;
	}

	public Double getMinToleranceRequestOrShippedQuantity() {
		return minToleranceRequestOrShippedQuantity;
	}

	public void setMinToleranceRequestOrShippedQuantity(Double minToleranceRequestOrShippedQuantity) {
		this.minToleranceRequestOrShippedQuantity = minToleranceRequestOrShippedQuantity;
	}

	public Double getMaxToleranceRequestOrShippedQuantity() {
		return maxToleranceRequestOrShippedQuantity;
	}

	public void setMaxToleranceRequestOrShippedQuantity(Double maxToleranceRequestOrShippedQuantity) {
		this.maxToleranceRequestOrShippedQuantity = maxToleranceRequestOrShippedQuantity;
	}

	public Double getMinToleranceRequestOrReceivedQuantity() {
		return minToleranceRequestOrReceivedQuantity;
	}

	public void setMinToleranceRequestOrReceivedQuantity(Double minToleranceRequestOrReceivedQuantity) {
		this.minToleranceRequestOrReceivedQuantity = minToleranceRequestOrReceivedQuantity;
	}

	public Double getMaxToleranceRequestOrReceivedQuantity() {
		return maxToleranceRequestOrReceivedQuantity;
	}

	public void setMaxToleranceRequestOrReceivedQuantity(Double maxToleranceRequestOrReceivedQuantity) {
		this.maxToleranceRequestOrReceivedQuantity = maxToleranceRequestOrReceivedQuantity;
	}

	public Double getMinToleranceRequestOrInvoicedQuantity() {
		return minToleranceRequestOrInvoicedQuantity;
	}

	public void setMinToleranceRequestOrInvoicedQuantity(Double minToleranceRequestOrInvoicedQuantity) {
		this.minToleranceRequestOrInvoicedQuantity = minToleranceRequestOrInvoicedQuantity;
	}

	public Double getMaxToleranceRequestOrInvoicedQuantity() {
		return maxToleranceRequestOrInvoicedQuantity;
	}

	public void setMaxToleranceRequestOrInvoicedQuantity(Double maxToleranceRequestOrInvoicedQuantity) {
		this.maxToleranceRequestOrInvoicedQuantity = maxToleranceRequestOrInvoicedQuantity;
	}

	public Double getMinToleranceReceivedOrInvoicedQuantity() {
		return minToleranceReceivedOrInvoicedQuantity;
	}

	public void setMinToleranceReceivedOrInvoicedQuantity(Double minToleranceReceivedOrInvoicedQuantity) {
		this.minToleranceReceivedOrInvoicedQuantity = minToleranceReceivedOrInvoicedQuantity;
	}

	public Double getMaxToleranceReceivedOrInvoicedQuantity() {
		return maxToleranceReceivedOrInvoicedQuantity;
	}

	public void setMaxToleranceReceivedOrInvoicedQuantity(Double maxToleranceReceivedOrInvoicedQuantity) {
		this.maxToleranceReceivedOrInvoicedQuantity = maxToleranceReceivedOrInvoicedQuantity;
	}

	public Double getMinToleranceInvoicePriceMismatch() {
		return minToleranceInvoicePriceMismatch;
	}

	public void setMinToleranceInvoicePriceMismatch(Double minToleranceInvoicePriceMismatch) {
		this.minToleranceInvoicePriceMismatch = minToleranceInvoicePriceMismatch;
	}

	public Double getMaxToleranceInvoicePriceMismatch() {
		return maxToleranceInvoicePriceMismatch;
	}

	public void setMaxToleranceInvoicePriceMismatch(Double maxToleranceInvoicePriceMismatch) {
		this.maxToleranceInvoicePriceMismatch = maxToleranceInvoicePriceMismatch;
	}

	public int getLateShipmentsTolerance() {
		return lateShipmentsTolerance;
	}

	public void setLateShipmentsTolerance(int lateShipmentsTolerance) {
		this.lateShipmentsTolerance = lateShipmentsTolerance;
	}

	public int getLateDeliveriesTolerance() {
		return lateDeliveriesTolerance;
	}

	public void setLateDeliveriesTolerance(int lateDeliveriesTolerance) {
		this.lateDeliveriesTolerance = lateDeliveriesTolerance;
	}

	public Double getCustomerMOQ() {
		return customerMOQ;
	}

	public void setCustomerMOQ(Double customerMOQ) {
		this.customerMOQ = customerMOQ;
	}

	public Double getSupplierShipmentSize() {
		return supplierShipmentSize;
	}

	public void setSupplierShipmentSize(Double supplierShipmentSize) {
		this.supplierShipmentSize = supplierShipmentSize;
	}

	public String getItemHierarchyLevel1() {
		return itemHierarchyLevel1;
	}

	public void setItemHierarchyLevel1(String itemHierarchyLevel1) {
		this.itemHierarchyLevel1 = itemHierarchyLevel1;
	}

	public String getItemHierarchyLevel2() {
		return itemHierarchyLevel2;
	}

	public void setItemHierarchyLevel2(String itemHierarchyLevel2) {
		this.itemHierarchyLevel2 = itemHierarchyLevel2;
	}

	public String getItemHierarchyLevel3() {
		return itemHierarchyLevel3;
	}

	public void setItemHierarchyLevel3(String itemHierarchyLevel3) {
		this.itemHierarchyLevel3 = itemHierarchyLevel3;
	}

	public String getItemHierarchyLevel4() {
		return itemHierarchyLevel4;
	}

	public void setItemHierarchyLevel4(String itemHierarchyLevel4) {
		this.itemHierarchyLevel4 = itemHierarchyLevel4;
	}

	public String getItemHierarchyLevel5() {
		return itemHierarchyLevel5;
	}

	public void setItemHierarchyLevel5(String itemHierarchyLevel5) {
		this.itemHierarchyLevel5 = itemHierarchyLevel5;
	}

	public String getPlanningBucketType() {
		return planningBucketType;
	}

	public void setPlanningBucketType(String planningBucketType) {
		this.planningBucketType = planningBucketType;
	}

	public String getProductLine() {
		return productLine;
	}

	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}

	public String getPlanningBuckets() {
		return planningBuckets;
	}

	public void setPlanningBuckets(String planningBuckets) {
		this.planningBuckets = planningBuckets;
	}

	public String getSerialNumberFlag() {
		return serialNumberFlag;
	}

	public void setSerialNumberFlag(String serialNumberFlag) {
		this.serialNumberFlag = serialNumberFlag;
	}

	public String getOldPartNumber() {
		return oldPartNumber;
	}

	public void setOldPartNumber(String oldPartNumber) {
		this.oldPartNumber = oldPartNumber;
	}

	public String getMaterialType() {
		return materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}

	public String getMaterialGroup() {
		return materialGroup;
	}

	public void setMaterialGroup(String materialGroup) {
		this.materialGroup = materialGroup;
	}

	public String getBulkItemFlag() {
		return bulkItemFlag;
	}

	public void setBulkItemFlag(String bulkItemFlag) {
		this.bulkItemFlag = bulkItemFlag;
	}

	public String getFlexStringCollab04() {
		return flexStringCollab04;
	}

	public void setFlexStringCollab04(String flexStringCollab04) {
		this.flexStringCollab04 = flexStringCollab04;
	}

	public String getFlexStringCollab05() {
		return flexStringCollab05;
	}

	public void setFlexStringCollab05(String flexStringCollab05) {
		this.flexStringCollab05 = flexStringCollab05;
	}

	public String getBlockShipments() {
		return blockShipments;
	}

	public void setBlockShipments(String blockShipments) {
		this.blockShipments = blockShipments;
	}

	public String getEarlyShipmentTolerance() {
		return earlyShipmentTolerance;
	}

	public void setEarlyShipmentTolerance(String earlyShipmentTolerance) {
		this.earlyShipmentTolerance = earlyShipmentTolerance;
	}

	public String getOverShipmentTolerance() {
		return overShipmentTolerance;
	}

	public void setOverShipmentTolerance(String overShipmentTolerance) {
		this.overShipmentTolerance = overShipmentTolerance;
	}

	public String getPriceMismatchToleranceMin() {
		return priceMismatchToleranceMin;
	}

	public void setPriceMismatchToleranceMin(String priceMismatchToleranceMin) {
		this.priceMismatchToleranceMin = priceMismatchToleranceMin;
	}

	public String getPriceMismatchToleranceMax() {
		return priceMismatchToleranceMax;
	}

	public void setPriceMismatchToleranceMax(String priceMismatchToleranceMax) {
		this.priceMismatchToleranceMax = priceMismatchToleranceMax;
	}

	@Override
	public String toString() {
		return "ItemEntity [enterpriseCode=" + enterpriseCode + ", siteName=" + siteName + ", supplierId=" + supplierId
				+ ", customerItemId=" + customerItemId + ", customerItemDescription=" + customerItemDescription
				+ ", supplierItemId=" + supplierItemId + ", supplierItemDescription=" + supplierItemDescription
				+ ", globalCustomerId=" + globalCustomerId + ", globalSupplierId=" + globalSupplierId
				+ ", globalItemId=" + globalItemId + ", eolGuidence=" + eolGuidence + ", purchasingGroup="
				+ purchasingGroup + ", commodityCode=" + commodityCode + ", classificationCode=" + classificationCode
				+ ", unitPrice=" + unitPrice + ", priceBasis=" + priceBasis + ", currency=" + currency + ", uomppo="
				+ uomppo + ", criticalItem=" + criticalItem + ", leadTimeDays=" + leadTimeDays + ", forcastGracePeriod="
				+ forcastGracePeriod + ", blanketExpiryTolerance=" + blanketExpiryTolerance + ", orderGracePeriod="
				+ orderGracePeriod + ", RequestOrPromiseDateTolerance=" + RequestOrPromiseDateTolerance
				+ ", commitQuantityToleranceMin=" + commitQuantityToleranceMin + ", commitQuantityToleranceMax="
				+ commitQuantityToleranceMax + ", minDos=" + minDos + ", maxDos=" + maxDos + ", TargetDos=" + TargetDos
				+ ", minToleranceRequestOrPromiseQuantity=" + minToleranceRequestOrPromiseQuantity
				+ ", maxToleranceRequestOrPromiseQuantity=" + maxToleranceRequestOrPromiseQuantity
				+ ", minToleranceRequestOrShippedQuantity=" + minToleranceRequestOrShippedQuantity
				+ ", maxToleranceRequestOrShippedQuantity=" + maxToleranceRequestOrShippedQuantity
				+ ", minToleranceRequestOrReceivedQuantity=" + minToleranceRequestOrReceivedQuantity
				+ ", maxToleranceRequestOrReceivedQuantity=" + maxToleranceRequestOrReceivedQuantity
				+ ", minToleranceRequestOrInvoicedQuantity=" + minToleranceRequestOrInvoicedQuantity
				+ ", maxToleranceRequestOrInvoicedQuantity=" + maxToleranceRequestOrInvoicedQuantity
				+ ", minToleranceReceivedOrInvoicedQuantity=" + minToleranceReceivedOrInvoicedQuantity
				+ ", maxToleranceReceivedOrInvoicedQuantity=" + maxToleranceReceivedOrInvoicedQuantity
				+ ", minToleranceInvoicePriceMismatch=" + minToleranceInvoicePriceMismatch
				+ ", maxToleranceInvoicePriceMismatch=" + maxToleranceInvoicePriceMismatch + ", lateShipmentsTolerance="
				+ lateShipmentsTolerance + ", lateDeliveriesTolerance=" + lateDeliveriesTolerance + ", customerMOQ="
				+ customerMOQ + ", supplierShipmentSize=" + supplierShipmentSize + ", itemHierarchyLevel1="
				+ itemHierarchyLevel1 + ", itemHierarchyLevel2=" + itemHierarchyLevel2 + ", itemHierarchyLevel3="
				+ itemHierarchyLevel3 + ", itemHierarchyLevel4=" + itemHierarchyLevel4 + ", itemHierarchyLevel5="
				+ itemHierarchyLevel5 + ", planningBucketType=" + planningBucketType + ", productLine=" + productLine
				+ ", planningBuckets=" + planningBuckets + ", serialNumberFlag=" + serialNumberFlag + ", oldPartNumber="
				+ oldPartNumber + ", materialType=" + materialType + ", materialGroup=" + materialGroup
				+ ", bulkItemFlag=" + bulkItemFlag + ", flexStringCollab04=" + flexStringCollab04
				+ ", flexStringCollab05=" + flexStringCollab05 + ", blockShipments=" + blockShipments
				+ ", earlyShipmentTolerance=" + earlyShipmentTolerance + ", overShipmentTolerance="
				+ overShipmentTolerance + ", priceMismatchToleranceMin=" + priceMismatchToleranceMin
				+ ", priceMismatchToleranceMax=" + priceMismatchToleranceMax + "]";
	}
	
	
}
