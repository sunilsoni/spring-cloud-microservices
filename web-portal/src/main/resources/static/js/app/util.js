/*
file: util.js
date: May 26, 2016
file purpose: common methods encapsulated in IIFE. 
server calls: none
*/

/*
* To expose an API for use by other sections of code via variable commonUtil
* @param {Object} jQuery 
* @return {Object} an object which exposes the methods
*/
var commonUtil = (function($){
    
    
   /*
    * To add a loading sign on the page
    */
    function addLoader(){
        $('#loadingindicator').addClass('wait');
    }
    
    /*
     * To remove a loading sign from the page
     */
    function removeLoader(){
        $('#loadingindicator').removeClass('wait');
    }
    
    /*
     * To handle hide and show of the 2 sections (Dashboard and process error screen)
     * @param {String} screen 
     */
    function handleHideShow(screen){
        if(screen === 'dashboardScreen'){
            $("#main1").css("display","block");
            $("#main2").css("display","none");
			$("#main2").removeClass('active');
			$("#main1").addClass('active');
        }
        else if(screen === 'processErrorScreen'){
            $("#main1").css("display","none");
            $("#main2").css("display","block");
			$("#main2").addClass('active');
			$("#main1").removeClass('active');
        }		
		//Sunil: --Add if else here
    }
    
    /*
     * To get the column definition for the dashboard grid
     * @return {Array} column definition array of objects
     */
    function getDashboardGridColumns(){
        return [{
            id: "poId",
            name: "Select",
            field: "poId",////initially it was poId
            formatter:checkBoxFormatter,
            width: 200,
			cssClass:"poId",
			sortable:false,
			filterValues : [],
			headerCssClass : "hide-arrow-btn"
        }, {
            id: "poNum",
            name: "PO Number",
            field: "OrderNumber", //initially it was poNum
            sortable : true,
            width: 150,
			cssClass:"poNumber",
			sortable:false,
			filterValues : []
        }, {
            id: "poDesc",
            name: "PO Description",
            field: "Description",//initially it was poDesc
            sortable : true,
            width: 300,
			cssClass:"poDesc",
			sortable:false,
			filterValues : []
        }, 
		{
            id: "dataSource",
            name: "Data Source",
            field: "SourceErpName",//initially it was dataSource
            sortable : true,
            width: 200
        },
		{
            id: "statusVal",
            name: "Status",
            field: "Status",//initially it was statusVal
            sortable : true,
            width: 250,
			cssClass:"statusVal",
			sortable:false,
			filterValues : []
        }
		];
    }
	
	function getSupplierGridColumns(){
        return [{
            id: "SupplierNumber",
            name: "Supplier Number",
            field: "SupplierNumber", //initially it was poNum
            sortable : true,
            width: 200
        },
		{
            id: "Status",
            name: "Status",
            field: "Status",//initially it was poDesc
            sortable : true,
            width: 200
        }, 
		{
            id: "Message",
            name: "Message",
            field: "Message",//initially it was poDesc
            sortable : true,
            width: 200
        }, 
		{
            id: "Date",
            name: "Date",
            field: "Date",//initially it was poDesc
            sortable : true,
            width: 200
        }, 
		{
            id: "EnterpriseCode",
            name: "Enterprise Code",
            field: "EnterpriseCode", //initially it was poNum
            sortable : true,
            width: 200
        }, 
		{
            id: "SupplierID",
            name: "Supplier ID",
            field: "SupplierID",//initially it was statusVal
            sortable : true,
            width: 150
        },
		{
            id: "SupplierDescription",
            name: "Supplier Description",
            field: "SupplierDescription",//initially it was statusVal
            sortable : true,
            width: 250
        },
		{
            id: "DUNS",
            name: "DUNS",
            field: "DUNS",//initially it was statusVal
            sortable : true,
            width: 250
        },
		{
            id: "GlobalSupplierID",
            name: "Global Supplier ID",
            field: "GlobalSupplierID",//initially it was statusVal
            sortable : true,
            width: 300
        },
		{
            id: "SupplierTaxNumber",
            name: "Supplier Tax Number",
            field: "SupplierTaxNumber",//initially it was statusVal
            sortable : true,
            width: 300
        },
		{
            id: "SupplierAddressExternalId",
            name: "Supplier Address External Id",
            field: "SupplierAddressExternalId",//initially it was statusVal
            sortable : true,
            width: 350
        },
		{
            id: "SupplierAddressDescriptor",
            name: "Supplier Address Descriptor",
            field: "SupplierAddressDescriptor",//initially it was statusVal
            sortable : true,
            width: 350
        },
		{
            id: "SupplierAddress1",
            name: "Supplier Address 1",
            field: "SupplierAddress1",//initially it was statusVal
            sortable : true,
            width: 250
        },
		{
            id: "SupplierAddress2",
            name: "Supplier Address 2",
            field: "SupplierAddress2",//initially it was statusVal
            sortable : true,
            width: 250
        },
		{
            id: "SupplierAddress3",
            name: "Supplier Address 3",
            field: "SupplierAddress3",//initially it was statusVal
            sortable : true,
            width: 250
        },
		{
            id: "SupplierAddress4",
            name: "Supplier Address 4",
            field: "SupplierAddress4",//initially it was statusVal
            sortable : true,
            width: 250
        },
		{
            id: "SupplierAddress5",
            name: "Supplier Address 5",
            field: "SupplierAddress5",//initially it was statusVal
            sortable : true,
            width: 250
        },
		{
            id: "SupplierCity",
            name: "Supplier City",
            field: "SupplierCity",//initially it was statusVal
            sortable : true,
            width: 250
        },
		{
            id: "SupplierCounty",
            name: "Supplier County",
            field: "SupplierCounty",//initially it was statusVal
            sortable : true,
            width: 250
        },
		{
            id: "SupplierState",
            name: "Supplier State",
            field: "SupplierState",//initially it was statusVal
            sortable : true,
            width: 200
        },
		{
            id: "SupplierCountry",
            name: "Supplier Country",
            field: "SupplierCountry",//initially it was statusVal
            sortable : true,
            width: 250
        },
		{
            id: "SupplierZip",
            name: "Supplier Zip",
            field: "SupplierZip",//initially it was statusVal
            sortable : true,
            width: 250
        },
		{
            id: "RemitToAddressExternalId",
            name: "Remit To Address External Id",
            field: "RemitToAddressExternalId",//initially it was statusVal
            sortable : true,
            width: 350
        },
		{
            id: "RemitToAddressDescriptor",
            name: "Remit To Address Descriptor",
            field: "RemitToAddressDescriptor",//initially it was statusVal
            sortable : true,
            width: 350
        },
		{
            id: "RemitToAddress1",
            name: "Remit To Address 1",
            field: "RemitToAddress1",//initially it was statusVal
            sortable : true,
            width: 350
        },	
		{
            id: "RemitToAddress2",
            name: "Remit To Address 2",
            field: "RemitToAddress1",//initially it was statusVal
            sortable : true,
            width: 350
        },	
		{
            id: "RemitToAddress3",
            name: "Remit To Address 3",
            field: "RemitToAddress1",//initially it was statusVal
            sortable : true,
            width: 350
        },	
		{
            id: "RemitToAddress4",
            name: "Remit To Address 4",
            field: "RemitToAddress1",//initially it was statusVal
            sortable : true,
            width: 350
        },	
		{
            id: "RemitToAddress5",
            name: "Remit To Address 5",
            field: "RemitToAddress1",//initially it was statusVal
            sortable : true,
            width: 350
        },	
		{
            id: "RemitToCity",
            name: "Remit To City",
            field: "RemitToCity",//initially it was statusVal
            sortable : true,
            width: 350
        },	
		{
            id: "RemitToCounty",
            name: "Remit To County",
            field: "RemitToCounty",//initially it was statusVal
            sortable : true,
            width: 350
        },	
		{
            id: "RemitToState",
            name: "Remit To State",
            field: "RemitToState",//initially it was statusVal
            sortable : true,
            width: 350
        },	
		{
            id: "RemitToCountry",
            name: "Remit To Country",
            field: "RemitToCountry",//initially it was statusVal
            sortable : true,
            width: 350
        },	
		{
            id: "RemitToZip",
            name: "Remit To Zip",
            field: "RemitToZip",//initially it was statusVal
            sortable : true,
            width: 350
        },	
		{
            id: "FlexStringSupplier01",
            name: "Flex String Supplier 01",
            field: "FlexStringSupplier01",//initially it was statusVal
            sortable : true,
            width: 350
        },	
		{
            id: "FlexStringSupplier02",
            name: "Flex String Supplier 02",
            field: "FlexStringSupplier02",//initially it was statusVal
            sortable : true,
            width: 350
        },	
		{
            id: "FlexStringSupplier03",
            name: "Flex String Supplier 03",
            field: "FlexStringSupplier03",//initially it was statusVal
            sortable : true,
            width: 350
        },	
		{
            id: "FlexStringSupplier04",
            name: "Flex String Supplier 04",
            field: "FlexStringSupplier04",//initially it was statusVal
            sortable : true,
            width: 350
        },	
		{
            id: "FlexStringSupplier05",
            name: "Flex String Supplier 05",
            field: "FlexStringSupplier05",//initially it was statusVal
            sortable : true,
            width: 350
        },	
		
		{
            id: "SupplierPromiseNeeded",
            name: "Supplier Promise Needed",
            field: "SupplierPromiseNeeded",//initially it was statusVal
            sortable : true,
            width: 350
        },
		{
            id: "SupplierHoldFlag",
            name: "Supplier Hold Flag",
            field: "SupplierHoldFlag",//initially it was statusVal
            sortable : true,
            width: 350
        },		
		{
            id: "SupplierStatus",
            name: "Supplier Status",
            field: "SupplierStatus",//initially it was statusVal
            sortable : true,
            width: 350
        },	
		{
            id: "BPORemainingQtyTol",
            name: "BPO Remaining Qty Tol",
            field: "BPORemainingQtyTol",//initially it was statusVal
            sortable : true,
            width: 350
        },	
		{
            id: "SupplierShipFromSiteInfo",
            name: "Supplier Ship From Site Info",
            field: "SupplierShipFromSiteInfo",//initially it was statusVal
            sortable : true,
            width: 350
        },
		{
            id: "SupplierShipFromSiteDescription",
            name: "Supplier Ship From Site Description",
            field: "SupplierShipFromSiteDescription",//initially it was statusVal
            sortable : true,
            width: 350
        },
		{
            id: "TransitLeadTime",
            name: "Transit Lead Time",
            field: "TransitLeadTime",//initially it was statusVal
            sortable : true,
            width: 300
        }
		];
    }
	
	function getItemGridColumns(){
        return [{
            id: "MaterialNumber",
            name: "Material Number",
            field: "MaterialNumber", //initially it was poNum
            sortable : true,
            width: 200
        },
		{
            id: "Status",
            name: "Status",
            field: "Status", //initially it was poNum
            sortable : true,
            width: 200
        },
		{
            id: "Message",
            name: "Message",
            field: "Message", //initially it was poNum
            sortable : true,
            width: 200
        },
		{
            id: "Date",
            name: "Date",
            field: "Date", //initially it was poNum
            sortable : true,
            width: 200
        },
		{
            id: "EnterpriseCode",
            name: "Enterprise Code",
            field: "EnterpriseCode", //initially it was poNum
            sortable : true,
            width: 200
        },
		{
            id: "SiteName",
            name: "Site Name",
            field: "SiteName",//initially it was statusVal
            sortable : true,
            width: 250
        },
		{
            id: "SupplierID",
            name: "Supplier Id",
            field: "SupplierID",//initially it was statusVal
            sortable : true,
            width: 250
        },
		{
            id: "CustomerItemID",
            name: "Customer Item ID",
            field: "CustomerItemID",//initially it was statusVal
            sortable : true,
            width: 250
        },
		{
            id: "CustomerItemDescription",
            name: "Customer Item Description",
            field: "CustomerItemDescription",//initially it was statusVal
            sortable : true,
            width: 350
        },
		{
            id: "SupplierItemID",
            name: "Supplier Item ID",
            field: "SupplierItemID",//initially it was statusVal
            sortable : true,
            width: 250
        },
		{
            id: "SupplierItemDescription",
            name: "Supplier Item Description",
            field: "SupplierItemDescription",//initially it was statusVal
            sortable : true,
            width: 300
        },
		{
            id: "GlobalCustomerID",
            name: "Global Customer ID",
            field: "GlobalCustomerID",//initially it was statusVal
            sortable : true,
            width: 300
        },
		{
            id: "GlobalSupplierID",
            name: "Global Supplier ID",
            field: "GlobalSupplierID",//initially it was statusVal
            sortable : true,
            width: 300
        },
		{
            id: "GlobalItemID",
            name: "Global Item ID",
            field: "GlobalItemID",//initially it was statusVal
            sortable : true,
            width: 300
        },
		{
            id: "EOLGuidance",
            name: "EOL Guidance",
            field: "EOLGuidance",//initially it was statusVal
            sortable : true,
            width: 300
        },
		{
            id: "PurchasingGroup",
            name: "Purchasing Group",
            field: "PurchasingGroup",//initially it was statusVal
            sortable : true,
            width: 300
        },
		{
            id: "CommodityCode",
            name: "Commodity Code",
            field: "CommodityCode",//initially it was statusVal
            sortable : true,
            width: 250
        },
		{
            id: "ClassificationCode",
            name: "Classification Code",
            field: "ClassificationCode",//initially it was statusVal
            sortable : true,
            width: 250
        },
		{
            id: "UnitPrice",
            name: "Unit Price",
            field: "UnitPrice",//initially it was statusVal
            sortable : true,
            width: 250
        },
		{
            id: "PriceBasis",
            name: "Price Basis",
            field: "PriceBasis",//initially it was statusVal
            sortable : true,
            width: 250
        },
		{
            id: "Currency",
            name: "Currency",
            field: "Currency",//initially it was statusVal
            sortable : true,
            width: 250
        },
		{
            id: "UOMPO",
            name: "UOM - PO",
            field: "UOMPO",//initially it was statusVal
            sortable : true,
            width: 250
        },
		{
            id: "CriticalItem",
            name: "Critical Item",
            field: "CriticalItem",//initially it was statusVal
            sortable : true,
            width: 200
        },
		{
            id: "LeadTimeDays",
            name: "Lead Time (Days)",
            field: "LeadTimeDays",//initially it was statusVal
            sortable : true,
            width: 250
        },
		{
            id: "ForecastGracePeriod",
            name: "Forecast Grace Period",
            field: "ForecastGracePeriod",//initially it was statusVal
            sortable : true,
            width: 300
        },
		{
            id: "BlanketExpiryTolerance",
            name: "Blanket Expiry Tolerance",
            field: "BlanketExpiryTolerance",//initially it was statusVal
            sortable : true,
            width: 300
        },
		{
            id: "OrderGracePeriod",
            name: "Order Grace Period",
            field: "OrderGracePeriod",//initially it was statusVal
            sortable : true,
            width: 250
        },
		{
            id: "RequestOrPromiseDateTolrance",
            name: "Request Or Promise Date Tolrance",
            field: "RequestOrPromiseDateTolrance",//initially it was statusVal
            sortable : true,
            width: 300
        },
		{
            id: "CommitQuantityToleranceMin",
            name: "Commit Quantity Tolerance Min",
            field: "CommitQuantityToleranceMin",//initially it was statusVal
            sortable : true,
            width: 400
        },
		{
            id: "CommitQuantityToleranceMax",
            name: "Commit Quantity Tolerance Max",
            field: "CommitQuantityToleranceMax",//initially it was statusVal
            sortable : true,
            width: 300
        },
		{
            id: "MinDOS",
            name: "Min DOS",
            field: "MinDOS",//initially it was statusVal
            sortable : true,
            width: 200
        },
		{
            id: "MaxDOS",
            name: "Max DOS",
            field: "MaxDOS",//initially it was statusVal
            sortable : true,
            width: 250
        },
		{
            id: "TargetDOS",
            name: "Target DOS",
            field: "TargetDOS",//initially it was poDesc
            sortable : true,
            width: 200
        }, 
		{
            id: "MinToleranceRequestOrPromiseQuantity",
            name: "Min Tolerance Request Or Promise Quantity",
            field: "MinToleranceRequestOrPromiseQuantity",//initially it was statusVal
            sortable : true,
            width: 400
        },
		
		{
            id: "MaxToleranceRequestOrPromiseQuantity",
            name: "Max Tolerance Request Or Promise Quantity",
            field: "MaxToleranceRequestOrPromiseQuantity",//initially it was statusVal
            sortable : true,
            width: 400
        },
		{
            id: "MinToleranceRequestOrShippedQuantity",
            name: "Min Tolerance Request Or Shipped Quantity",
            field: "MinToleranceRequestOrShippedQuantity",//initially it was statusVal
            sortable : true,
            width: 400
        },
		{
            id: "MaxToleranceRequestOrShippedQuantity",
            name: "Max Tolerance Request Or Shipped Quantity",
            field: "MaxToleranceRequestOrShippedQuantity",//initially it was statusVal
            sortable : true,
            width: 400
        },
		{
            id: "MinToleranceRequestOrReceivedQuantity",
            name: "Min Tolerance Request Or Received Quantity",
            field: "MinToleranceRequestOrReceivedQuantity",//initially it was statusVal
            sortable : true,
            width: 400
        },
		{
            id: "MaxToleranceRequestOrReceivedQuantity",
            name: "Max Tolerance Request Or Received Quantity",
            field: "MaxToleranceRequestOrReceivedQuantity",//initially it was statusVal
            sortable : true,
            width: 400
        },
		{
            id: "MinToleranceRequestOrInvoicedQuantity",
            name: "Min Tolerance Request Or Invoiced Quantity",
            field: "MinToleranceRequestOrInvoicedQuantity", //initially it was poNum
            sortable : true,
            width: 400
        }, 
		{
            id: "MaxToleranceRequestOrInvoicedQuantity",
            name: "Max Tolerance Request Or Invoiced Quantity",
            field: "MaxToleranceRequestOrInvoicedQuantity",//initially it was statusVal
            sortable : true,
            width: 400
        },
		{
            id: "MinToleranceReceivedOrInvoicedQuantity",
            name: "Min Tolerance Received Or Invoiced Quantity",
            field: "MinToleranceReceivedOrInvoicedQuantity",//initially it was statusVal
            sortable : true,
            width: 250
        },
		{
            id: "MaxToleranceReceivedOrInvoicedQuantity",
            name: "Max Tolerance Received Or Invoiced Quantity",
            field: "MaxToleranceReceivedOrInvoicedQuantity",//initially it was statusVal
            sortable : true,
            width: 400
        },
		{
            id: "MinToleranceInvoicePriceMismatch",
            name: "Min Tolerance Invoice Price Mismatch",
            field: "MinToleranceInvoicePriceMismatch",//initially it was statusVal
            sortable : true,
            width: 400
        },
		{
            id: "MaxToleranceInvoicePriceMismatch",
            name: "Max Tolerance Invoice Price Mismatch",
            field: "MaxToleranceInvoicePriceMismatch",//initially it was statusVal
            sortable : true,
            width: 400
        },
		{
            id: "LateShipmentsTolerance",
            name: "Late Shipments Tolerance",
            field: "LateShipmentsTolerance",//initially it was statusVal
            sortable : true,
            width: 300
        },
		{
            id: "LateDeliveriesTolerance",
            name: "Late Deliveries Tolerance",
            field: "LateDeliveriesTolerance",//initially it was statusVal
            sortable : true,
            width: 350
        },
		{
            id: "CustomerMOQ",
            name: "Customer MOQ",
            field: "CustomerMOQ",//initially it was statusVal
            sortable : true,
            width: 150
        },
		{
            id: "SupplierShipmentSize",
            name: "Supplier Shipment Size",
            field: "SupplierShipmentSize",//initially it was statusVal
            sortable : true,
            width: 300
        },
		{
            id: "ItemHierarchyLevel1",
            name: "Item Hierarchy Level 1",
            field: "ItemHierarchyLevel1",//initially it was statusVal
            sortable : true,
            width: 300
        },
		{
            id: "ItemHierarchyLevel2",
            name: "Item Hierarchy Level 2",
            field: "ItemHierarchyLevel2",//initially it was statusVal
            sortable : true,
            width: 300
        },
		{
            id: "ItemHierarchyLevel3",
            name: "Item Hierarchy Level 3",
            field: "ItemHierarchyLevel3",//initially it was statusVal
            sortable : true,
            width: 300
        },
		{
            id: "ItemHierarchyLevel4",
            name: "Item Hierarchy Level 4",
            field: "ItemHierarchyLevel4",//initially it was statusVal
            sortable : true,
            width: 300
        },
		
		{
            id: "ItemHierarchyLevel5",
            name: "Item Hierarchy Level 5",
            field: "ItemHierarchyLevel5",//initially it was statusVal
            sortable : true,
            width: 300
        },
		{
            id: "PlanningBucketType",
            name: "Planning Bucket Type",
            field: "PlanningBucketType",//initially it was statusVal
            sortable : true,
            width: 300
        },
		{
            id: "ProductLine",
            name: "Product Line",
            field: "ProductLine",//initially it was statusVal
            sortable : true,
            width: 300
        },
		{
            id: "PlanningBuckets",
            name: "Planning Buckets",
            field: "PlanningBuckets",//initially it was statusVal
            sortable : true,
            width: 300
        },
		{
            id: "SerialNumberFlag	",
            name: "Serial Number Flag",
            field: "SerialNumberFlag",//initially it was statusVal
            sortable : true,
            width: 300
        },
		{
            id: "OldPartNumber",
            name: "Old Part Number",
            field: "OldPartNumber",//initially it was statusVal
            sortable : true,
            width: 300
        },
		{
            id: "MaterialType",
            name: "Material Type",
            field: "MaterialType",//initially it was statusVal
            sortable : true,
            width: 300
        },
		{
            id: "MaterialGroup",
            name: "Material Group",
            field: "MaterialGroup",//initially it was statusVal
            sortable : true,
            width: 300
        },
		{
            id: "BulkItemFlag",
            name: "Bulk Item Flag",
            field: "BulkItemFlag",//initially it was statusVal
            sortable : true,
            width: 300
        },
		{
            id: "FlexStringCollab04",
            name: "Flex String Collab 04",
            field: "FlexStringCollab04",//initially it was statusVal
            sortable : true,
            width: 300
        },
		{
            id: "FlexStringCollab05",
            name: "Flex String Collab 05",
            field: "FlexStringCollab05",//initially it was statusVal
            sortable : true,
            width: 300
        },
		{
            id: "BlockShipments",
            name: "Block Shipments",
            field: "BlockShipments",//initially it was statusVal
            sortable : true,
            width: 300
        },
		{
            id: "EarlyShipmentTolerance",
            name: "Early Shipment Tolerance",
            field: "EarlyShipmentTolerance",//initially it was statusVal
            sortable : true,
            width: 350
        },
		{
            id: "OverShipmentTolerance",
            name: "Over Shipment Tolerance",
            field: "OverShipmentTolerance",//initially it was statusVal
            sortable : true,
            width: 350
        },
		{
            id: "PriceMismatchToleranceMin",
            name: "Price Mismatch Tolerance Min",
            field: "PriceMismatchToleranceMin",//initially it was statusVal
            sortable : true,
            width: 350
        },
		{
            id: "PriceMismatchToleranceMax",
            name: "Price Mismatch Tolerance Max",
            field: "PriceMismatchToleranceMax",//initially it was statusVal
            sortable : true,
            width: 350
        }];
    }
    
    /*
     * To get the column definition for the process error grid
     * @return {Array} column definition array of objects
     */
    function getProcessErrorGridColumns(){
        return [/*{
            id: "poId",
            name: "Select",
            field: "poId",
            formatter: checkBoxFormatterErr,
            minWidth: 200,
            resizable:true,
			cssClass:"poId"
          },*/{
            id: "poNum",
            name: "PO Number",
            field: "OrderNumber",
            minWidth: 150,
            resizable:true,
			cssClass:"poNumber"
          }, {
            id: "poDesc",
            name: "PO Description",
            field: "Description",
            minWidth: 300,
            resizable:true,
			cssClass:"poDesc"
          }, 
		  {
            id: "dataSourceName",
            name: "Data Source",
            field: "dataSourceName",
            minWidth: 200,
            resizable:true
          }, 
		  {
            id: "statusVal",
            name: "Status",
            field: "Status",
            minWidth: 250,
            resizable:true,
			cssClass:"statusVal"
          }];
    }
	
	
    
    /*
     * To get the options for the dashboard grid
     * @return {Object} options object
     */
    function getDashboardGridOptions(){
      return {
            enableCellNavigation: true,
            enableColumnReorder: false
			//explicitInitialization: true
        };
    }
    
    /* 
     * To get the options for the process error grid
     * @return {Object} options object
     */
    function getProcessErrorGridOptions(){
       return {
            enableCellNavigation: true,
            enableColumnReorder: false
        };
    }
    
    /*
     * To get the reference to the dashboard grid instance
     * @return {Object} dashboard grid reference instance
     */
    function getDashboardGridReference(){
        return e2OpenDashboard.mainGrid();
    }
    
    /*
     * To get the data in the format required by the method to process PO data
     * @param {Object} dataView 
     * @param {Array} records
     * @param {Boolean} descFlag
     * @return {Object} processed data
     */
    function getProcessData(dataView,records, descFlag){
        var dataToProcess = [], idToPoNumMap = {},descGlobalText = {};
        var gridData = dataView.getItems() || [];
        for(var i=0, l = records.length; i< l ; i++){
            var item = dataView.getItemById(records[i]);
            idToPoNumMap[item["poNum"]] = parseInt(item["poId"]);
            dataToProcess.push(item["poNum"]);
            if(descFlag){
                descGlobalText[item["poNum"]] = parseInt(item["poId"]);
            }
        }

        return(JSON.stringify({
            poNums: dataToProcess,
            poNumToIdMap : idToPoNumMap,
            Description : descGlobalText
        }));
    }
    
    /*
     * To update the error count label in the dashboard screen
     * @param {String} errorCount 
     */
    function updateErrorCount(errorCount){
        $('#errorCount').text(errorCount);
    }
    
    function resizeCanvas(grid){
        setTimeout(function(){
            e2OpenDashboard.mainGrid()[grid].grid.resizeCanvas();
        },300);
        
    }
    
    /*
     * To prepare graph data, to be used to plot the graph showing the statistics based on the Status field
     * @param {Number} processedCount 
     * @param {Number} inTransitCount
     * @param {Number} errorCount
     * @return {Array} graph data
     */
	 
	 //Sunil:Pass dynamically-step1
	
   function prepareGraphData(data){
        return [{
                name: 'Processed',
                data: data.error,
                color: "#90ed7d"
            }, {
                name: 'In-Transit',
                data: data.transit,
                color: "#7cb5ec"

            }, {
                name: 'Errored',
                data: data.process,
                color: "#AA4643"
            }];
    }
    
    
    function createGraphData(graphDataObject){
        return graphDataObject;
        
    }
    
    /*
     * To get the status text based on the status code
     * @param {Number} statusCode 
     * @return {String} status text
     */
    function getStatusText(statusCode){
        if(statusCode === 1){
            return "In-transit";
        }
        else if(statusCode === 2){
            return "Transaction Completed";
        }
        else if(statusCode === 3){
            return "Error in Process";
        }
    }
    
    /*
     * To get the graph container id
     * @return {String} graph container id
     */
    function getGraphContainer(){
        return "highchartContainer";
    }
    
    /*
     * To plot the graph showing the statistics based on the Status field
     * @param {Array} plotData 
     */
    function plotGraph(plotData,categoryArr){
        var highchartContainer = getGraphContainer();
        if(plotData){
            graphObj.createChart(highchartContainer, plotData, categoryArr);
        }
        else{
            graphObj.createChart(highchartContainer,null,categoryArr);
        }
        
    }
    
    /*
     * 
     * @param {Object} dashboardData 
     */
    function dashboardErp(dashboardData){
        var erpData = [];
        for(var d in dashboardData){
            erpData.push(d);
        }
        return erpData;
    }
    
    $(document).on('click','.tabUl li a', function(e){
        var grid = $(this).attr('grid');
		var maingrid = e2OpenDashboard.mainGrid();
		var curGrid = maingrid[grid].grid;
		
		var filterPlugin = new Ext.Plugins.HeaderFilter({});

		// This event is fired when a filter is selected
		filterPlugin.onFilterApplied.subscribe(function () {
			maingrid[grid].dataView.refresh();
			curGrid.resetActiveCell();

			// Excel like status bar at the bottom
			var status;

			if (maingrid[grid].dataView.getLength() === maingrid[grid].dataView.getItems().length) {
				status = "";
			} else {
				status = maingrid[grid].dataView.getLength() + ' OF ' + maingrid[grid].dataView.getItems().length + ' RECORDS FOUND';
			}
			$('#status-label').text(status);
		});

		// Event fired when a menu option is selected
		filterPlugin.onCommand.subscribe(function (e, args) {
			maingrid[grid].dataView.fastSort(args.column.field, args.command === "sort-asc");
		});

		curGrid.registerPlugin(filterPlugin);

		curGrid.init();
        resizeCanvas(grid);
    })
    
    return{
        addLoader : addLoader,
        removeLoader : removeLoader,
        handleHideShow : handleHideShow,
        getDashboardGridColumns : getDashboardGridColumns,
        getSupplierGridColumns : getSupplierGridColumns,
        getItemGridColumns : getItemGridColumns,
        getProcessErrorGridColumns : getProcessErrorGridColumns,
        getDashboardGridOptions : getDashboardGridOptions,
        getProcessErrorGridOptions : getProcessErrorGridOptions,
        getDashboardGridReference : getDashboardGridReference,
        getProcessData : getProcessData,
        updateErrorCount : updateErrorCount,
        prepareGraphData : prepareGraphData,
        getStatusText : getStatusText,
        getGraphContainer : getGraphContainer,
        plotGraph : plotGraph,
        dashboardErp : dashboardErp,
		resizeCanvas:resizeCanvas
//        createGraphData : createGraphData
    }

})(jQuery);