package com.jci.po.entity;

import com.microsoft.azure.storage.table.TableServiceEntity;

public class ItemEntity extends TableServiceEntity {

	public ItemEntity(String partitionKey, String rowKey) {
		this.partitionKey = partitionKey;
		this.rowKey = rowKey; 
	}

	public ItemEntity() {
	}

	private String materialNumber;
	private String status;
	private String message;
	private String date;
	private String EnterpriseCode;
	private String SiteName;
	private String SupplierID;
	private String CustomerItemID;//PK
	private String customerItemDescription;
	private String supplierItemID;
	private String supplierItemDescription;
	private String globalCustomerID;
	private String globalSupplierID;
	private String globalItemID;
	private String eOLGuidance;
	private String purchasingGroup;
	private String commodityCode;
	private String classificationCode;
	private String UnitPrice;
	private String priceBasis;
	private String currency;
	private String uOMPO;
	private String criticalItem;
	private int leadTimeDays;// leadTime(Days)
	private int forecastGracePeriod;
	private int blanketExpiryTolerance;
	private int orderGracePeriod;
	private int requestOrPromiseDateTolrance;// request/PromiseDateTolerance
	private double commitQuantityToleranceMin;
	private double commitQuantityToleranceMax;
	private double minDOS;
	private double maxDOS;
	private double targetDOS;
	private double minToleranceRequestOrPromiseQuantity;// minToleranceRequest/PromiseQuantity
	private double maxToleranceRequestOrPromiseQuantity;// maxToleranceRequest/PromiseQuantity
	private double maxToleranceRequestOrShippedQuantity;// minToleranceRequest/ShippedQuantity
	private double minToleranceRequestOrShippedQuantity;// minToleranceRequest/ShippedQuantity
	private double minToleranceRequestOrReceivedQuantity;// minToleranceRequest/ReceivedQuantity
	private double maxToleranceRequestOrReceivedQuantity;// maxToleranceRequest/ReceivedQuantity
	private double minToleranceRequestOrInvoicedQuantity;// minToleranceRequest/InvoicedQuantity
	private double maxToleranceRequestOrInvoicedQuantity;// maxToleranceRequest/InvoicedQuantity
	private double minToleranceReceivedOrInvoicedQuantity;// minToleranceReceived/InvoicedQuantity
	private double maxToleranceReceivedOrInvoicedQuantity;// maxToleranceReceived/InvoicedQuantity
	private double minToleranceInvoicePriceMismatch;
	private double maxToleranceInvoicePriceMismatch;
	private double lateShipmentsTolerance;
	private double lateDeliveriesTolerance;
	private double customerMOQ;
	private double supplierShipmentSize;
	private double itemHierarchyLevel1;
	private double itemHierarchyLevel2;
	private double itemHierarchyLevel3;
	private double itemHierarchyLevel4;
	private double itemHierarchyLevel5;
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
	public String getMaterialNumber() {
		return materialNumber;
	}

	public void setMaterialNumber(String materialNumber) {
		this.materialNumber = materialNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getEnterpriseCode() {
		return EnterpriseCode;
	}

	public void setEnterpriseCode(String enterpriseCode) {
		EnterpriseCode = enterpriseCode;
	}

	public String getSiteName() {
		return SiteName;
	}

	public void setSiteName(String siteName) {
		SiteName = siteName;
	}

	public String getSupplierID() {
		return SupplierID;
	}

	public void setSupplierID(String supplierID) {
		SupplierID = supplierID;
	}

	public String getCustomerItemID() {
		return CustomerItemID;
	}

	public void setCustomerItemID(String customerItemID) {
		CustomerItemID = customerItemID;
	}

	public String getCustomerItemDescription() {
		return customerItemDescription;
	}

	public void setCustomerItemDescription(String customerItemDescription) {
		this.customerItemDescription = customerItemDescription;
	}

	public String getSupplierItemID() {
		return supplierItemID;
	}

	public void setSupplierItemID(String supplierItemID) {
		this.supplierItemID = supplierItemID;
	}

	public String getSupplierItemDescription() {
		return supplierItemDescription;
	}

	public void setSupplierItemDescription(String supplierItemDescription) {
		this.supplierItemDescription = supplierItemDescription;
	}

	public String getGlobalCustomerID() {
		return globalCustomerID;
	}

	public void setGlobalCustomerID(String globalCustomerID) {
		this.globalCustomerID = globalCustomerID;
	}

	public String getGlobalSupplierID() {
		return globalSupplierID;
	}

	public void setGlobalSupplierID(String globalSupplierID) {
		this.globalSupplierID = globalSupplierID;
	}

	public String getGlobalItemID() {
		return globalItemID;
	}

	public void setGlobalItemID(String globalItemID) {
		this.globalItemID = globalItemID;
	}

	public String geteOLGuidance() {
		return eOLGuidance;
	}

	public void seteOLGuidance(String eOLGuidance) {
		this.eOLGuidance = eOLGuidance;
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

	public String getUnitPrice() {
		return UnitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		UnitPrice = unitPrice;
	}

	public String getPriceBasis() {
		return priceBasis;
	}

	public void setPriceBasis(String priceBasis) {
		this.priceBasis = priceBasis;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getuOMPO() {
		return uOMPO;
	}

	public void setuOMPO(String uOMPO) {
		this.uOMPO = uOMPO;
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

	public int getForecastGracePeriod() {
		return forecastGracePeriod;
	}

	public void setForecastGracePeriod(int forecastGracePeriod) {
		this.forecastGracePeriod = forecastGracePeriod;
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

	public int getRequestOrPromiseDateTolrance() {
		return requestOrPromiseDateTolrance;
	}

	public void setRequestOrPromiseDateTolrance(int requestOrPromiseDateTolrance) {
		this.requestOrPromiseDateTolrance = requestOrPromiseDateTolrance;
	}

	public double getCommitQuantityToleranceMin() {
		return commitQuantityToleranceMin;
	}

	public void setCommitQuantityToleranceMin(double commitQuantityToleranceMin) {
		this.commitQuantityToleranceMin = commitQuantityToleranceMin;
	}

	public double getCommitQuantityToleranceMax() {
		return commitQuantityToleranceMax;
	}

	public void setCommitQuantityToleranceMax(double commitQuantityToleranceMax) {
		this.commitQuantityToleranceMax = commitQuantityToleranceMax;
	}

	public double getMinDOS() {
		return minDOS;
	}

	public void setMinDOS(double minDOS) {
		this.minDOS = minDOS;
	}

	public double getMaxDOS() {
		return maxDOS;
	}

	public void setMaxDOS(double maxDOS) {
		this.maxDOS = maxDOS;
	}

	public double getTargetDOS() {
		return targetDOS;
	}

	public void setTargetDOS(double targetDOS) {
		this.targetDOS = targetDOS;
	}

	public double getMinToleranceRequestOrPromiseQuantity() {
		return minToleranceRequestOrPromiseQuantity;
	}

	public void setMinToleranceRequestOrPromiseQuantity(double minToleranceRequestOrPromiseQuantity) {
		this.minToleranceRequestOrPromiseQuantity = minToleranceRequestOrPromiseQuantity;
	}

	public double getMaxToleranceRequestOrPromiseQuantity() {
		return maxToleranceRequestOrPromiseQuantity;
	}

	public void setMaxToleranceRequestOrPromiseQuantity(double maxToleranceRequestOrPromiseQuantity) {
		this.maxToleranceRequestOrPromiseQuantity = maxToleranceRequestOrPromiseQuantity;
	}

	public double getMaxToleranceRequestOrShippedQuantity() {
		return maxToleranceRequestOrShippedQuantity;
	}

	public void setMaxToleranceRequestOrShippedQuantity(double maxToleranceRequestOrShippedQuantity) {
		this.maxToleranceRequestOrShippedQuantity = maxToleranceRequestOrShippedQuantity;
	}

	public double getMinToleranceRequestOrShippedQuantity() {
		return minToleranceRequestOrShippedQuantity;
	}

	public void setMinToleranceRequestOrShippedQuantity(double minToleranceRequestOrShippedQuantity) {
		this.minToleranceRequestOrShippedQuantity = minToleranceRequestOrShippedQuantity;
	}

	public double getMinToleranceRequestOrReceivedQuantity() {
		return minToleranceRequestOrReceivedQuantity;
	}

	public void setMinToleranceRequestOrReceivedQuantity(double minToleranceRequestOrReceivedQuantity) {
		this.minToleranceRequestOrReceivedQuantity = minToleranceRequestOrReceivedQuantity;
	}

	public double getMaxToleranceRequestOrReceivedQuantity() {
		return maxToleranceRequestOrReceivedQuantity;
	}

	public void setMaxToleranceRequestOrReceivedQuantity(double maxToleranceRequestOrReceivedQuantity) {
		this.maxToleranceRequestOrReceivedQuantity = maxToleranceRequestOrReceivedQuantity;
	}

	public double getMinToleranceRequestOrInvoicedQuantity() {
		return minToleranceRequestOrInvoicedQuantity;
	}

	public void setMinToleranceRequestOrInvoicedQuantity(double minToleranceRequestOrInvoicedQuantity) {
		this.minToleranceRequestOrInvoicedQuantity = minToleranceRequestOrInvoicedQuantity;
	}

	public double getMaxToleranceRequestOrInvoicedQuantity() {
		return maxToleranceRequestOrInvoicedQuantity;
	}

	public void setMaxToleranceRequestOrInvoicedQuantity(double maxToleranceRequestOrInvoicedQuantity) {
		this.maxToleranceRequestOrInvoicedQuantity = maxToleranceRequestOrInvoicedQuantity;
	}

	public double getMinToleranceReceivedOrInvoicedQuantity() {
		return minToleranceReceivedOrInvoicedQuantity;
	}

	public void setMinToleranceReceivedOrInvoicedQuantity(double minToleranceReceivedOrInvoicedQuantity) {
		this.minToleranceReceivedOrInvoicedQuantity = minToleranceReceivedOrInvoicedQuantity;
	}

	public double getMaxToleranceReceivedOrInvoicedQuantity() {
		return maxToleranceReceivedOrInvoicedQuantity;
	}

	public void setMaxToleranceReceivedOrInvoicedQuantity(double maxToleranceReceivedOrInvoicedQuantity) {
		this.maxToleranceReceivedOrInvoicedQuantity = maxToleranceReceivedOrInvoicedQuantity;
	}

	public double getMinToleranceInvoicePriceMismatch() {
		return minToleranceInvoicePriceMismatch;
	}

	public void setMinToleranceInvoicePriceMismatch(double minToleranceInvoicePriceMismatch) {
		this.minToleranceInvoicePriceMismatch = minToleranceInvoicePriceMismatch;
	}

	public double getMaxToleranceInvoicePriceMismatch() {
		return maxToleranceInvoicePriceMismatch;
	}

	public void setMaxToleranceInvoicePriceMismatch(double maxToleranceInvoicePriceMismatch) {
		this.maxToleranceInvoicePriceMismatch = maxToleranceInvoicePriceMismatch;
	}

	public double getLateShipmentsTolerance() {
		return lateShipmentsTolerance;
	}

	public void setLateShipmentsTolerance(double lateShipmentsTolerance) {
		this.lateShipmentsTolerance = lateShipmentsTolerance;
	}

	public double getLateDeliveriesTolerance() {
		return lateDeliveriesTolerance;
	}

	public void setLateDeliveriesTolerance(double lateDeliveriesTolerance) {
		this.lateDeliveriesTolerance = lateDeliveriesTolerance;
	}

	public double getCustomerMOQ() {
		return customerMOQ;
	}

	public void setCustomerMOQ(double customerMOQ) {
		this.customerMOQ = customerMOQ;
	}

	public double getSupplierShipmentSize() {
		return supplierShipmentSize;
	}

	public void setSupplierShipmentSize(double supplierShipmentSize) {
		this.supplierShipmentSize = supplierShipmentSize;
	}

	public double getItemHierarchyLevel1() {
		return itemHierarchyLevel1;
	}

	public void setItemHierarchyLevel1(double itemHierarchyLevel1) {
		this.itemHierarchyLevel1 = itemHierarchyLevel1;
	}

	public double getItemHierarchyLevel2() {
		return itemHierarchyLevel2;
	}

	public void setItemHierarchyLevel2(double itemHierarchyLevel2) {
		this.itemHierarchyLevel2 = itemHierarchyLevel2;
	}

	public double getItemHierarchyLevel3() {
		return itemHierarchyLevel3;
	}

	public void setItemHierarchyLevel3(double itemHierarchyLevel3) {
		this.itemHierarchyLevel3 = itemHierarchyLevel3;
	}

	public double getItemHierarchyLevel4() {
		return itemHierarchyLevel4;
	}

	public void setItemHierarchyLevel4(double itemHierarchyLevel4) {
		this.itemHierarchyLevel4 = itemHierarchyLevel4;
	}

	public double getItemHierarchyLevel5() {
		return itemHierarchyLevel5;
	}

	public void setItemHierarchyLevel5(double itemHierarchyLevel5) {
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
		return "ItemEntity [materialNumber=" + materialNumber + ", status=" + status + ", message=" + message
				+ ", date=" + date + ", EnterpriseCode=" + EnterpriseCode + ", SiteName=" + SiteName + ", SupplierID="
				+ SupplierID + ", CustomerItemID=" + CustomerItemID + ", customerItemDescription="
				+ customerItemDescription + ", supplierItemID=" + supplierItemID + ", supplierItemDescription="
				+ supplierItemDescription + ", globalCustomerID=" + globalCustomerID + ", globalSupplierID="
				+ globalSupplierID + ", globalItemID=" + globalItemID + ", eOLGuidance=" + eOLGuidance
				+ ", purchasingGroup=" + purchasingGroup + ", commodityCode=" + commodityCode + ", classificationCode="
				+ classificationCode + ", UnitPrice=" + UnitPrice + ", priceBasis=" + priceBasis + ", currency="
				+ currency + ", uOMPO=" + uOMPO + ", criticalItem=" + criticalItem + ", leadTimeDays=" + leadTimeDays
				+ ", forecastGracePeriod=" + forecastGracePeriod + ", blanketExpiryTolerance=" + blanketExpiryTolerance
				+ ", orderGracePeriod=" + orderGracePeriod + ", requestOrPromiseDateTolrance="
				+ requestOrPromiseDateTolrance + ", commitQuantityToleranceMin=" + commitQuantityToleranceMin
				+ ", commitQuantityToleranceMax=" + commitQuantityToleranceMax + ", minDOS=" + minDOS + ", maxDOS="
				+ maxDOS + ", targetDOS=" + targetDOS + ", minToleranceRequestOrPromiseQuantity="
				+ minToleranceRequestOrPromiseQuantity + ", maxToleranceRequestOrPromiseQuantity="
				+ maxToleranceRequestOrPromiseQuantity + ", maxToleranceRequestOrShippedQuantity="
				+ maxToleranceRequestOrShippedQuantity + ", minToleranceRequestOrShippedQuantity="
				+ minToleranceRequestOrShippedQuantity + ", minToleranceRequestOrReceivedQuantity="
				+ minToleranceRequestOrReceivedQuantity + ", maxToleranceRequestOrReceivedQuantity="
				+ maxToleranceRequestOrReceivedQuantity + ", minToleranceRequestOrInvoicedQuantity="
				+ minToleranceRequestOrInvoicedQuantity + ", maxToleranceRequestOrInvoicedQuantity="
				+ maxToleranceRequestOrInvoicedQuantity + ", minToleranceReceivedOrInvoicedQuantity="
				+ minToleranceReceivedOrInvoicedQuantity + ", maxToleranceReceivedOrInvoicedQuantity="
				+ maxToleranceReceivedOrInvoicedQuantity + ", minToleranceInvoicePriceMismatch="
				+ minToleranceInvoicePriceMismatch + ", maxToleranceInvoicePriceMismatch="
				+ maxToleranceInvoicePriceMismatch + ", lateShipmentsTolerance=" + lateShipmentsTolerance
				+ ", lateDeliveriesTolerance=" + lateDeliveriesTolerance + ", customerMOQ=" + customerMOQ
				+ ", supplierShipmentSize=" + supplierShipmentSize + ", itemHierarchyLevel1=" + itemHierarchyLevel1
				+ ", itemHierarchyLevel2=" + itemHierarchyLevel2 + ", itemHierarchyLevel3=" + itemHierarchyLevel3
				+ ", itemHierarchyLevel4=" + itemHierarchyLevel4 + ", itemHierarchyLevel5=" + itemHierarchyLevel5
				+ ", planningBucketType=" + planningBucketType + ", productLine=" + productLine + ", planningBuckets="
				+ planningBuckets + ", serialNumberFlag=" + serialNumberFlag + ", oldPartNumber=" + oldPartNumber
				+ ", materialType=" + materialType + ", materialGroup=" + materialGroup + ", bulkItemFlag="
				+ bulkItemFlag + ", flexStringCollab04=" + flexStringCollab04 + ", flexStringCollab05="
				+ flexStringCollab05 + ", blockShipments=" + blockShipments + ", earlyShipmentTolerance="
				+ earlyShipmentTolerance + ", overShipmentTolerance=" + overShipmentTolerance
				+ ", priceMismatchToleranceMin=" + priceMismatchToleranceMin + ", priceMismatchToleranceMax="
				+ priceMismatchToleranceMax + "]";
	}
	
	
	
	
	
}
