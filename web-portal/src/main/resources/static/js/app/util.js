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
            formatter: checkBoxFormatter,
            width: 200,
			cssClass:"poId"
        }, {
            id: "poNum",
            name: "PO Number",
            field: "OrderNumber", //initially it was poNum
            sortable : true,
            width: 150,
			cssClass:"poNumber"
        }, {
            id: "poDesc",
            name: "PO Description",
            field: "Description",//initially it was poDesc
            sortable : true,
            width: 300,
			cssClass:"poDesc"
        }/*, {
            id: "dataSource",
            name: "Data Source",
            field: "SourceErpName",//initially it was dataSource
            sortable : true,
            width: 200
        }*/, {
            id: "statusVal",
            name: "Status",
            field: "Status",//initially it was statusVal
            sortable : true,
            width: 250,
			cssClass:"statusVal"
        }];
    }
	
	function getSupplierGridColumns(){
        return [{
            id: "SupId",
            name: "Select",
            field: "SupId",////initially it was poId
            formatter: checkBoxFormatter,
            width: 100
        }, {
            id: "TransitLeadTime",
            name: "Transit Lead Time",
            field: "TransitLeadTime", //initially it was poNum
            sortable : true,
            width: 250
        }, {
            id: "SupplierAddressDescriptor",
            name: "Supplier Address Descriptor",
            field: "SupplierAddressDescriptor",//initially it was poDesc
            sortable : true,
            width: 300
        }, {
            id: "SupplierZip",
            name: "Supplier Zip",
            field: "SupplierZip",//initially it was statusVal
            sortable : true,
            width: 150
        },
		{
            id: "SupplierCity",
            name: "Supplier City",
            field: "SupplierCity",//initially it was statusVal
            sortable : true,
            width: 200
        },
		{
            id: "SupplierDescription",
            name: "Supplier Description",
            field: "SupplierDescription",//initially it was statusVal
            sortable : true,
            width: 250
        },
		{
            id: "SupplierDescription",
            name: "Supplier Description",
            field: "SupplierDescription",//initially it was statusVal
            sortable : true,
            width: 250
        },
		{
            id: "SupplierId",
            name: "Supplier Id",
            field: "SupplierId",//initially it was statusVal
            sortable : true,
            width: 250
        },
		{
            id: "SupplierStatus",
            name: "Supplier Status",
            field: "SupplierStatus",//initially it was statusVal
            sortable : true,
            width: 150
        },
		{
            id: "SupplierCountry",
            name: "Supplier Country",
            field: "SupplierCountry",//initially it was statusVal
            sortable : true,
            width: 200
        },
		{
            id: "SupplierAddress5",
            name: "Supplier Address 5",
            field: "SupplierAddress5",//initially it was statusVal
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
            id: "SupplierAddress1",
            name: "Supplier Address 1",
            field: "SupplierAddress1",//initially it was statusVal
            sortable : true,
            width: 250
        },
		{
            id: "EnterpriseCode",
            name: "Enterprise Code",
            field: "EnterpriseCode",//initially it was statusVal
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
            id: "SupplierState",
            name: "Supplier State",
            field: "SupplierState",//initially it was statusVal
            sortable : true,
            width: 250
        },
		{
            id: "SupplierAddress2",
            name: "Supplier Address 2",
            field: "SupplierAddress2",//initially it was statusVal
            sortable : true,
            width: 250
        }
		];
    }
	
	function getItemGridColumns(){
        return [{
            id: "ItemId",
            name: "Select",
            field: "ItemId",////initially it was poId
            formatter: checkBoxFormatter,
            width: 100
        }, {
            id: "CriticalItem",
            name: "Critical Item",
            field: "CriticalItem", //initially it was poNum
            sortable : true,
            width: 150
        }, {
            id: "SiteName",
            name: "Site Name",
            field: "SiteName",//initially it was poDesc
            sortable : true,
            width: 150
        }, {
            id: "SupplierItemId",
            name: "Supplier Item Id",
            field: "SupplierItemId",//initially it was statusVal
            sortable : true,
            width: 200
        },
		{
            id: "LateDeliveriesTolerance",
            name: "Late Deliveries Tolerance",
            field: "LateDeliveriesTolerance",//initially it was statusVal
            sortable : true,
            width: 250
        },
		{
            id: "RequestOrPromiseDateTolerance",
            name: "Request Or Promise Date Tolerance",
            field: "RequestOrPromiseDateTolerance",//initially it was statusVal
            sortable : true,
            width: 350
        },
		{
            id: "Uomppo",
            name: "Uomppo",
            field: "Uomppo",//initially it was statusVal
            sortable : true,
            width: 100
        },
		{
            id: "BlanketExpiryTolerance",
            name: "Blanket Expiry Tolerance",
            field: "BlanketExpiryTolerance",//initially it was statusVal
            sortable : true,
            width: 250
        },
		{
            id: "MinToleranceRequestOrReceivedQuantity",
            name: "Min Tolerance Request Or Received Quantity",
            field: "MinToleranceRequestOrReceivedQuantity",//initially it was statusVal
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
            id: "CommitQuantityToleranceMin",
            name: "Commit Quantity Tolerance Min",
            field: "CommitQuantityToleranceMin",//initially it was statusVal
            sortable : true,
            width: 400
        },
		{
            id: "SupplierAddress4",
            name: "Supplier Address 4",
            field: "SupplierAddress4",//initially it was statusVal
            sortable : true,
            width: 250
        },
		{
            id: "CommitQuantityToleranceMax",
            name: "Commit Quantity Tolerance Max",
            field: "CommitQuantityToleranceMax",//initially it was statusVal
            sortable : true,
            width: 300
        },
		{
            id: "ForcastGracePeriod",
            name: "Forcast Grace Period",
            field: "ForcastGracePeriod",//initially it was statusVal
            sortable : true,
            width: 250
        },
		{
            id: "MaxToleranceRequestOrReceivedQuantity",
            name: "Max Tolerance Request Or Received Quantity",
            field: "MaxToleranceRequestOrReceivedQuantity",//initially it was statusVal
            sortable : true,
            width: 400
        },
		{
            id: "ClassificationCode",
            name: "Classification Code",
            field: "ClassificationCode",//initially it was statusVal
            sortable : true,
            width: 250
        },
		{
            id: "CommodityCode",
            name: "Commodity Code",
            field: "CommodityCode",//initially it was statusVal
            sortable : true,
            width: 250
        },
		{
            id: "SerialNumberFlag",
            name: "Serial Number Flag",
            field: "SerialNumberFlag",//initially it was statusVal
            sortable : true,
            width: 250
        },
		{
            id: "EnterpriseCode",
            name: "Enterprise Code",
            field: "EnterpriseCode",//initially it was statusVal
            sortable : true,
            width: 250
        },
		{
            id: "ProductLine",
            name: "Product Line",
            field: "ProductLine",//initially it was statusVal
            sortable : true,
            width: 250
        },
		{
            id: "PurchasingGroup",
            name: "Purchasing Group",
            field: "PurchasingGroup",//initially it was statusVal
            sortable : true,
            width: 250
        },
		{
            id: "MaxToleranceRequestOrPromiseQuantity",
            name: "Max Tolerance Request Or Promise Quantity",
            field: "MaxToleranceRequestOrPromiseQuantity",//initially it was statusVal
            sortable : true,
            width: 400
        },
		{
            id: "OrderGracePeriod",
            name: "Order Grace Period",
            field: "OrderGracePeriod",//initially it was statusVal
            sortable : true,
            width: 250
        },
		{
            id: "PlanningBuckets",
            name: "Planning Buckets",
            field: "PlanningBuckets",//initially it was statusVal
            sortable : true,
            width: 250
        },
		{
            id: "SupplierShipmentSize",
            name: "Supplier Shipment Size",
            field: "SupplierShipmentSize",//initially it was statusVal
            sortable : true,
            width: 250
        },
		{
            id: "LeadTimeDays",
            name: "Lead Time Days",
            field: "LeadTimeDays",//initially it was statusVal
            sortable : true,
            width: 250
        },
		{
            id: "LateShipmentsTolerance",
            name: "Late Shipments Tolerance",
            field: "LateShipmentsTolerance",//initially it was statusVal
            sortable : true,
            width: 250
        },
		{
            id: "MinToleranceRequestOrPromiseQuantity",
            name: "Min Tolerance Request Or Promise Quantity",
            field: "MinToleranceRequestOrPromiseQuantity",//initially it was statusVal
            sortable : true,
            width: 400
        },
		{
            id: "CustomerItemDescription",
            name: "Customer Item Description",
            field: "CustomerItemDescription",//initially it was statusVal
            sortable : true,
            width: 250
        },
		{
            id: "OldPartNumber",
            name: "OldPartNumber",
            field: "OldPartNumber",//initially it was statusVal
            sortable : true,
            width: 250
        },
		{
            id: "SupplierId",
            name: "Supplier Id",
            field: "SupplierId",//initially it was statusVal
            sortable : true,
            width: 250
        },
		{
            id: "CustomerItemId",
            name: "Customer Item Id",
            field: "CustomerItemId",//initially it was statusVal
            sortable : true,
            width: 250
        },
		{
            id: "MaxToleranceRequestOrShippedQuantity",
            name: "Max Tolerance Request Or Shipped Quantity",
            field: "MaxToleranceRequestOrShippedQuantity",//initially it was statusVal
            sortable : true,
            width: 400
        },
		{
            id: "CustomerMOQ",
            name: "Customer MOQ",
            field: "CustomerMOQ",//initially it was statusVal
            sortable : true,
            width: 150
        },
		{
            id: "PlanningBucketType",
            name: "Planning Bucket Type",
            field: "PlanningBucketType",//initially it was statusVal
            sortable : true,
            width: 200
        },
		{
            id: "SupplierItemDescription",
            name: "Supplier Item Description",
            field: "SupplierItemDescription",//initially it was statusVal
            sortable : true,
            width: 250
        }
		];
    }
    
    /*
     * To get the column definition for the process error grid
     * @return {Array} column definition array of objects
     */
    function getProcessErrorGridColumns(){
        return [{
            id: "poId",
            name: "Select",
            field: "poId",
            formatter: checkBoxFormatterErr,
            minWidth: 200,
            resizable:true,
			cssClass:"poId"
          },{
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
          }/*, {
            id: "dataSourceName",
            name: "Data Source",
            field: "dataSourceName",
            minWidth: 200,
            resizable:true
          }*/, {
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
            enableColumnReorder: false,
			explicitInitialization: true
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
        dashboardErp : dashboardErp
//        createGraphData : createGraphData
    }

})(jQuery);