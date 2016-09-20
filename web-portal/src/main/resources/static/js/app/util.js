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
        return [/*{
            id: "poId",
            name: "Select",
            field: "poId",////initially it was poId
            formatter:checkBoxFormatter,
            width: 65,
			cssClass:"poId",
			sortable:false,
			filterValues : [],
			headerCssClass : "hide-arrow-btn"
        },*/ {
            id: "poNum",
            name: "PO Number",
            field: "OrderNumber", //initially it was poNum
            sortable : true,
            width: 309,
			cssClass:"poNumber",
			formatter:hrefFormatter,
			sortable:false,
			filterValues : []
        }, /*{
            id: "poDesc",
            name: "PO Description",
            field: "Description",//initially it was poDesc
            sortable : true,
            width: 288,
			cssClass:"poDesc",
			sortable:false,
			filterValues : []
        },*/ 
		/*{
            id: "dataSource",
            name: "Data Source",
            field: "SourceErpName",//initially it was dataSource
            sortable : true,
            width: 200
        },*/
		{
            id: "statusVal",
            name: "Status",
            field: "Status",//initially it was statusVal
            sortable : true,
            width: 309,
			cssClass:"statusVal",
			sortable:false,
			filterValues : []
        }
		];
    }
	
	function getSupplierGridColumns(){
        return [
				{
		            id: "SupplierID",
		            name: "Supplier ID",
		            field: "supplierID",//initially it was statusVal
		            sortable : true,
		            width: 150
		        },
				{
		            id: "SupplierDescription",
		            name: "Supplier Description",
		            field: "supplierDescription",//initially it was statusVal
		            sortable : true,
		            width: 250
		        },
				{
		            id: "GlobalSupplierID",
		            name: "Global Supplier ID",
		            field: "globalSupplierID",//initially it was statusVal
		            sortable : true,
		            width: 250
		        },
				{
		            id: "SupplierTaxNumber",
		            name: "Supplier Tax Number",
		            field: "supplierTaxNumber",//initially it was statusVal
		            sortable : true,
		            width: 230
		        },
				{
		            id: "SupplierAddressDescriptor",
		            name: "Supplier Address Descriptor",
		            field: "supplierAddressDescriptor",//initially it was statusVal
		            sortable : true,
		            width: 300
		        },
				{
		            id: "SupplierAddress1",
		            name: "Supplier Address 1",
		            field: "supplierAddress1",//initially it was statusVal
		            sortable : true,
		            width: 250
		        },
				{
		            id: "SupplierAddress2",
		            name: "Supplier Address 2",
		            field: "supplierAddress2",//initially it was statusVal
		            sortable : true,
		            width: 250
		        },
				{
		            id: "SupplierCity",
		            name: "Supplier City",
		            field: "supplierCity",//initially it was statusVal
		            sortable : true,
		            width: 200
		        },
				{
		            id: "SupplierState",
		            name: "Supplier State",
		            field: "supplierState",//initially it was statusVal
		            sortable : true,
		            width: 180
		        },
				{
		            id: "SupplierCountry",
		            name: "Supplier Country",
		            field: "supplierCountry",//initially it was statusVal
		            sortable : true,
		            width: 180
		        },
				{
		            id: "SupplierZip",
		            name: "Supplier Zip",
		            field: "supplierZip",//initially it was statusVal
		            sortable : true,
		            width: 180
		        },{
		            id: "SupplierStatus",
		            name: "Supplier Status",
		            field: "supplierStatus",//initially it was statusVal
		            sortable : true,
		            width: 180
		        }];
    }
	
	function getItemGridColumns(){
        return [
				{
				    id: "SiteName",
				    name: "Site Name",
				    field: "siteName",//initially it was statusVal
				    sortable : true,
				    width: 150
				},{
		            id: "customerItemID",
		            name: "Customer Item ID",
		            field: "customerItemID",//initially it was statusVal
		            sortable : true,
		            width: 200
		        },{
		            id: "CustomerItemDescription",
		            name: "Customer Item Description",
		            field: "customerItemDescription",//initially it was statusVal
		            sortable : true,
		            width: 350
		        },{
		            id: "GlobalItemID",
		            name: "Global Item ID",
		            field: "globalItemID",//initially it was statusVal
		            sortable : true,
		            width: 200
		        },{
		            id: "PurchasingGroup",
		            name: "Purchasing Group",
		            field: "purchasingGroup",//initially it was statusVal
		            sortable : true,
		            width: 190
		        },{
		            id: "CommodityCode",
		            name: "Commodity Code",
		            field: "commodityCode",//initially it was statusVal
		            sortable : true,
		            width: 180
		        },{
		            id: "ClassificationCode",
		            name: "Classification Code",
		            field: "classificationCode",//initially it was statusVal
		            sortable : true,
		            width: 220
		        },{
		            id: "UnitPrice",
		            name: "Unit Price",
		            field: "unitPrice",//initially it was statusVal
		            sortable : true,
		            width: 120
		        },
				{
		            id: "PriceBasis",
		            name: "Price Basis",
		            field: "priceBasis",//initially it was statusVal
		            sortable : true,
		            width: 140
		        },
				{
		            id: "Currency",
		            name: "Currency",
		            field: "currency",//initially it was statusVal
		            sortable : true,
		            width: 150
		        },
				{
		            id: "UOMPO",
		            name: "UOM - PO",
		            field: "uOMPO",//initially it was statusVal
		            sortable : true,
		            width: 150
		        },
				{
		            id: "CriticalItem",
		            name: "Critical Item",
		            field: "criticalItem",//initially it was statusVal
		            sortable : true,
		            width: 180
		        },{
		            id: "ForecastGracePeriod",
		            name: "Forecast Grace Period",
		            field: "forecastGracePeriod",//initially it was statusVal
		            sortable : true,
		            width: 200
		        },
				{
		            id: "OrderGracePeriod",
		            name: "Order Grace Period",
		            field: "orderGracePeriod",//initially it was statusVal
		            sortable : true,
		            width: 200
		        },{
		            id: "SerialNumberFlag",
		            name: "Serial Number Flag",
		            field: "serialNumberFlag",//initially it was statusVal
		            sortable : true,
		            width: 200
		        },
				{
		            id: "MaterialType",
		            name: "Material Type",
		            field: "materialType",//initially it was statusVal
		            sortable : true,
		            width: 180
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
            minWidth: 284,
            resizable:true,
			cssClass:"poNumber"
          }, /*{
            id: "poDesc",
            name: "PO Description",
            field: "Description",
            minWidth: 268,
            resizable:true,
			cssClass:"poDesc"
          },*/ 
		  /*{
            id: "SourceErpName",
            name: "Data Source",
            field: "SourceErpName",
            minWidth: 200,
            resizable:true
          }, */
		  {
            id: "statusVal",
            name: "Status",
            field: "Status",
            minWidth: 285,
            resizable:true,
			cssClass:"statusVal"
          }];
    }
	
	function getECNGridColumns(){
		return [{
			id: "ECNNumber",
            name: "Change Number",
            field: "ECNNumber",
            minWidth: 150,
            resizable:true
		},
		{
			id: "Description",
            name: "Description",
            field: "Description",
            minWidth: 200,
            resizable:true
		},
		{
			id: "Plant",
            name: "Plant",
            field: "Plant",
            minWidth: 130,
            resizable:true
		},
		{
			id: "Error",
            name: "Status",
            field: "Error",
            minWidth: 150,
            resizable:true
		}
		];
	}
	
	function getECNErrorGridColumns(){
		return [{
			id: "ECNNumber",
            name: "Change Number",
            field: "ECNNumber",
            minWidth: 150,
            resizable:true
		},
		{
			id: "Plant",
            name: "Plant",
            field: "Plant",
            minWidth: 130,
            resizable:true
		},
		{
			id: "ChangeType",
            name: "Change Type",
            field: "ChangeType",
            minWidth: 150,
            resizable:true
		},
		{
			id: "Error",
            name: "Status",
            field: "Error",
            minWidth: 150,
            resizable:true
		},
		{
			id: "EcnRequestor",
            name: "ECN Requestor",
            field: "EcnRequestor",
            minWidth: 150,
            resizable:true
		},
		{
			id: "CreatedDate",
            name: "ESI TRX Date",
            field: "CreatedDate",
            minWidth: 200,
            resizable:true
		},
		{
			id: "ProcessedDate",
            name: "Processed Date",
            field: "ProcessedDate",
            minWidth: 200,
            resizable:true
		}
		];
	}
	
	function getPOItemDetailsColumns(){
		return [
		{
			id:"OrderNumber",
			name:"Order Number",
			field:"orderNumber",
			sortable:true,
			width:200
		},
		{
			id:"OrderCreationDate",
			name:"Order Creation Date",
			field:"orderCreationDate",
			sortable:true,
			width:200
		},{
			id:"OrderStatus",
			name:"Order Status",
			field:"orderStatus",
			sortable:true,
			width:200
		},
		{
			id:"SupplierID",
			name:"Supplier ID",
			field:"supplierID",
			sortable:true,
			width:200
		},{
            id: "SupplierDescription",
            name: "Supplier Description",
            field: "supplierDescription",//initially it was statusVal
            sortable : true,
            width: 250
        },{
            id: "SupplierZip",
            name: "Supplier Zip",
            field: "supplierZip",//initially it was statusVal
            sortable : true,
            width: 250
        },
        {
            id: "BuyerCode",
            name: "Buyer Code",
            field: "buyerCode",//initially it was statusVal
            sortable : true,
            width: 250
        },{
            id: "PaymentTerms",
            name: "Payment Terms",
            field: "paymentTerms",//initially it was statusVal
            sortable : true,
            width: 250
        },{
            id: "TotalOrderAmount",
            name: "Total Order Amount",
            field: "totalOrderAmount",//initially it was statusVal
            sortable : true,
            width: 250
        },{
            id: "SupplierItemID",
            name: "Supplier Item ID",
            field: "supplierItemID",//initially it was statusVal
            sortable : true,
            width: 250
        },
		{
			id:"UnitPrice",
			name:"Unit Price",
			field:"unitPrice",
			minWidth:200
		},{
            id: "PriceBasis",
            name: "Price Basis",
            field: "priceBasis",//initially it was statusVal
            sortable : true,
            width: 250
        },{
            id: "PaymentCurrency",
            name: "Payment Currency",
            field: "paymentCurrency",//initially it was statusVal
            sortable : true,
            width: 250
        },{
			id:"TotalLineAmount",
			name:"Total Line Amount",
			field:"totalLineAmount",
			sortable:true,
			width:200
		},{
            id: "UOM",
            name: "UOM",
            field: "uOM",//initially it was statusVal
            sortable : true,
            width: 250
        },{
			id:"ShipToLocation",
			name:"Ship To Location",
			field:"shipToLocation",
			minWidth:200
		},{
            id: "KanbanFlag",
            name: "kanban Flag",
            field: "kanbanFlag",
            sortable : true,
            width: 250
        },{
			id:"requestQty",
			name:"Request Qty",
			field:"requestQty",
			minWidth:400
		},{
			id:"RequestDate",
			name:"Request Date",
			field:"requestDate",
			minWidth:400
		},,{
			id:"requestedShipDate",
			name:"Requested Ship Date",
			field:"requestedShipDate",
			minWidth:200
		},{
            id: "Carrier",
            name: "Carrier",
            field: "carrier",//initially it was statusVal
            sortable : true,
            width: 150
        },{
			id:"CustomerSite",
			name:"Customer Site",
			field:"customerSite",
			minWidth:200
		},{
			id:"PromiseQty",
			name:"Promise Qty",
			field:"promiseQty",
			minWidth:400
		},{
            id: "PromiseDate",
            name: "Promise Date",
            field: "promiseDate",//initially it was statusVal
            sortable : true,
            width: 250
        },{
			id:"promisedShipDate",
			name:"Promised Ship Date",
			field:"promisedShipDate",
			minWidth:250
		}]
	}
	
	
    
    /*
     * To get the options for the dashboard grid
     * @return {Object} options object
     */
    function getDashboardGridOptions(){
      return {
            enableCellNavigation: true,
            enableColumnReorder: false,
			enableAddRow: false,
			//rowHeight:30
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
            enableColumnReorder: false,
			enableAddRow: false,
			//rowHeight:30
        };
    }
    function getECNProcessErrorGridOptions(){
        return {
             enableCellNavigation: true,
             enableColumnReorder: false,
 			enableAddRow: false,
 			//rowHeight:30
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
	
	function prepareECNGraphData(data){
        return [{
                name: 'Processed',
                data: data.process,
                color: "#90ed7d"
            }, {
                name: 'Errored',
                data: data.error,
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
    function plotGraph(plotData,name,categoryArr,container){
        var highchartContainer = "";
		if(container)
			highchartContainer = container;
		else
			highchartContainer = getGraphContainer();
		
        if(plotData){
            graphObj.createChart(highchartContainer, plotData,name,categoryArr);
        }
        else{
            graphObj.createChart(highchartContainer,null,name,categoryArr);
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
		resizeCanvas:resizeCanvas,
		getECNGridColumns:getECNGridColumns,
		prepareECNGraphData:prepareECNGraphData,
		getPOItemDetailsColumns:getPOItemDetailsColumns,
		getECNErrorGridColumns:getECNErrorGridColumns,
		getECNProcessErrorGridOptions:getECNProcessErrorGridOptions
//        createGraphData : createGraphData
    }

})(jQuery);