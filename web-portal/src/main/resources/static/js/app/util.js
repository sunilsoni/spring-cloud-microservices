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
            sortable : false,
            width: 309,
			cssClass:"poNumber",
			formatter:hrefFormatter,
			filterValues : []
        }, /*{
            id: "poDesc",
            name: "PO Description",
            field: "Description",//initially it was poDesc
            sortable : false,
            width: 288,
			cssClass:"poDesc",
			sortable:false,
			filterValues : []
        },*/ 
		/*{
            id: "dataSource",
            name: "Data Source",
            field: "SourceErpName",//initially it was dataSource
            sortable : false,
            width: 200
        },*/
		{
            id: "statusVal",
            name: "Status",
            field: "Status",//initially it was statusVal
            sortable : false,
            width: 309,
			cssClass:"statusVal",
			sortable:false,
			formatter:statusFormatter,
			filterValues : []
        }
		];
    }
    
    function supplierJsonFormatter(row, cell, value, columnDef, dataContext){
		 var data = "application/json;charset=utf-8," + encodeURIComponent(JSON.stringify(dataContext, null, "\t"));
		 return '<a href="data:' + data + '" download="'+dataContext.supplierID+'.json">'+dataContext.supplierID+'</a>';
    }
    
    function itemJsonFormatter(row, cell, value, columnDef, dataContext){
		 var data = "application/json;charset=utf-8," + encodeURIComponent(JSON.stringify(dataContext, null, "\t"));
		 return '<a href="data:' + data + '" download="'+dataContext.customerItemID+'.json">'+dataContext.customerItemID+'</a>';
   }
	
	function getSupplierGridColumns(){
        return [
				{
		            id: "SupplierID",
		            name: "Supplier ID",
		            field: "supplierID",//initially it was statusVal
		            sortable : false,
		            width: 100
		        },
				{
		            id: "SupplierDescription",
		            name: "Supplier Description",
		            field: "supplierDescription",//initially it was statusVal
		            sortable : false,
		            width: 250
		        },
				{
		            id: "GlobalSupplierID",
		            name: "Global Supplier ID",
		            field: "globalSupplierID",//initially it was statusVal
		            sortable : false,
		            width: 180
		        },
				{
		            id: "SupplierTaxNumber",
		            name: "Supplier Tax Number",
		            field: "supplierTaxNumber",//initially it was statusVal
		            sortable : false,
		            width: 230
		        },
				{
		            id: "SupplierAddressDescriptor",
		            name: "Supplier Address Descriptor",
		            field: "supplierAddressDescriptor",//initially it was statusVal
		            sortable : false,
		            width: 250
		        },
				{
		            id: "SupplierAddress1",
		            name: "Supplier Address 1",
		            field: "supplierAddress1",//initially it was statusVal
		            sortable : false,
		            width: 250
		        },
				{
		            id: "SupplierAddress2",
		            name: "Supplier Address 2",
		            field: "supplierAddress2",//initially it was statusVal
		            sortable : false,
		            width: 250
		        },
				{
		            id: "SupplierCity",
		            name: "Supplier City",
		            field: "supplierCity",//initially it was statusVal
		            sortable : false,
		            width: 150
		        },
				{
		            id: "SupplierState",
		            name: "Supplier State",
		            field: "supplierState",//initially it was statusVal
		            sortable : false,
		            width: 150
		        },
				{
		            id: "SupplierCountry",
		            name: "Supplier Country",
		            field: "supplierCountry",//initially it was statusVal
		            sortable : false,
		            width: 180
		        },
				{
		            id: "SupplierZip",
		            name: "Supplier Zip",
		            field: "supplierZip",//initially it was statusVal
		            sortable : false,
		            width: 180
		        },{
		            id: "SupplierStatus",
		            name: "Supplier Status",
		            field: "supplierStatus",//initially it was statusVal
		            sortable : false,
		            width: 180
		        },{
		            id : "supplierJsonDownload",
		            name : "Download JSON",
		            field : "jsonDownload",
		            sortable : false,
		            width : 150,
		            formatter : supplierJsonFormatter,
		            cssClass : "alignColumnCenter"
		        }];
    }
	
	function getItemGridColumns(){
        return [
				{
				    id: "SiteName",
				    name: "Site Name",
				    field: "siteName",//initially it was statusVal
				    sortable : false,
				    width: 110
				},{
		            id: "customerItemID",
		            name: "Customer Item ID",
		            field: "customerItemID",//initially it was statusVal
		            sortable : false,
		            width: 150
		        },{
		            id: "CustomerItemDescription",
		            name: "Customer Item Description",
		            field: "customerItemDescription",//initially it was statusVal
		            sortable : false,
		            width: 300
		        },{
		            id: "GlobalItemID",
		            name: "Global Item ID",
		            field: "globalItemID",//initially it was statusVal
		            sortable : false,
		            width: 150
		        },{
		            id: "PurchasingGroup",
		            name: "Purchasing Group",
		            field: "purchasingGroup",//initially it was statusVal
		            sortable : false,
		            width: 160
		        },{
		            id: "CommodityCode",
		            name: "Commodity Code",
		            field: "commodityCode",//initially it was statusVal
		            sortable : false,
		            width: 160
		        },{
		            id: "ClassificationCode",
		            name: "Classification Code",
		            field: "classificationCode",//initially it was statusVal
		            sortable : false,
		            width: 180
		        },{
		            id: "UnitPrice",
		            name: "Unit Price",
		            field: "unitPrice",//initially it was statusVal
		            sortable : false,
		            width: 100
		        },
				{
		            id: "PriceBasis",
		            name: "Price Basis",
		            field: "priceBasis",//initially it was statusVal
		            sortable : false,
		            width: 100
		        },
				{
		            id: "Currency",
		            name: "Currency",
		            field: "currency",//initially it was statusVal
		            sortable : false,
		            width: 100
		        },
				{
		            id: "UOMPO",
		            name: "UOM - PO",
		            field: "uOMPO",//initially it was statusVal
		            sortable : false,
		            width: 90
		        },
				{
		            id: "CriticalItem",
		            name: "Critical Item",
		            field: "criticalItem",//initially it was statusVal
		            sortable : false,
		            width: 100
		        },{
		            id: "ForecastGracePeriod",
		            name: "Forecast Grace Period",
		            field: "forecastGracePeriod",//initially it was statusVal
		            sortable : false,
		            width: 170
		        },
				{
		            id: "OrderGracePeriod",
		            name: "Order Grace Period",
		            field: "orderGracePeriod",//initially it was statusVal
		            sortable : false,
		            width: 160
		        },{
		            id: "SerialNumberFlag",
		            name: "Serial Number Flag",
		            field: "serialNumberFlag",//initially it was statusVal
		            sortable : false,
		            width: 160
		        },
				{
		            id: "MaterialType",
		            name: "Material Type",
		            field: "materialType",//initially it was statusVal
		            sortable : false,
		            width: 140
		        },{
		            id : "itemJsonDownload",
		            name : "Download JSON",
		            field : "jsonDownload",
		            sortable : false,
		            width : 150,
		            formatter : itemJsonFormatter,
		            cssClass : "alignColumnCenter"
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
			cssClass:"statusVal",
			formatter:statusFormatter
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
	
	 function jsonFormatter(row, cell, value, columnDef, dataContext) {
		 var data = "application/json;charset=utf-8," + encodeURIComponent(JSON.stringify(dataContext, null, "\t"));
		 var fileNameToSaveAs = dataContext.orderNumber+'.json';
		 var textToWrite = JSON.stringify(dataContext, null, "\t");
		 
			  var ie = navigator.userAgent.match(/MSIE\s([\d.]+)/),
			      ie11 = navigator.userAgent.match(/Trident\/7.0/) && navigator.userAgent.match(/rv:11/),
			      ieEDGE = navigator.userAgent.match(/Edge/g),
			      ieVer=(ie ? ie[1] : (ie11 ? 11 : (ieEDGE ? 12 : -1)));

			  if (ie && ieVer<10) {
			    console.log("No blobs on IE ver<10");
			    return;
			  }

			  var textFileAsBlob = new Blob([textToWrite], {
			    type: 'application/json'
			  });

			  //var isFlagEnabled = false;
			  //if (ieVer<-1) {
					  //window.navigator.msSaveBlob(textFileAsBlob, fileNameToSaveAs);
				 //return '<div onclick="ieDownload('+textFileAsBlob+','+fileNameToSaveAs+')">'+dataContext.orderNumber+'</div>';
				return '<a href="data:' + data + '" download="'+fileNameToSaveAs+'" class="poLink" onclick="ieDownload('+textFileAsBlob+','+fileNameToSaveAs+')">'+dataContext.orderNumber+'</a>'
			//}
			/*   else {
				return '<a href="data:' + data + '" download="'+fileNameToSaveAs+'" class="poLink" onclick="">'+dataContext.orderNumber+'</a>'
			    /*var downloadLink = document.createElement("a");
			    downloadLink.download = fileNameToSaveAs;
			    downloadLink.href = window.URL.createObjectURL(textFileAsBlob);
			    downloadLink.onclick = function(e) { document.body.removeChild(e.target); };
			    downloadLink.style.display = "none";
			    document.body.appendChild(downloadLink);
			    downloadLink.click();
			    return '<a href="data:' + data + '" download="'+fileNameToSaveAs+'" class="poLink">'+dataContext.orderNumber+'</a>';
			  }*/
		 //saveTextAsFile(dataContext.orderNumber+'.json', encodeURIComponent(JSON.stringify(dataContext, null, "\t")));*/ 
	    }
	 
	 function ieDownload(textFileAsBlob, fileNameToSaveAs){
		 window.navigator.msSaveBlob(textFileAsBlob, fileNameToSaveAs);
	 }
	
	function getPOItemDetailsColumns(){
		return [
		{
			id:"OrderNumber",
			name:"Order Number",
			field:"orderNumber",
			sortable : false,
			width:150
		},
		{
			id:"OrderCreationDate",
			name:"Order Creation Date",
			field:"orderCreationDate",
			sortable : false,
			width:180
		},{
			id:"OrderStatus",
			name:"Order Status",
			field:"orderStatus",
			sortable : false,
			width:150
		},
		{
			id:"SupplierID",
			name:"Supplier ID",
			field:"supplierID",
			sortable : false,
			width:150
		},{
            id: "SupplierDescription",
            name: "Supplier Description",
            field: "supplierDescription",//initially it was statusVal
            sortable : false,
            width: 250
        },{
            id: "SupplierZip",
            name: "Supplier Zip",
            field: "supplierZip",//initially it was statusVal
            sortable : false,
            width: 150
        },
        {
            id: "BuyerCode",
            name: "Buyer Code",
            field: "buyerCode",//initially it was statusVal
            sortable : false,
            width: 180
        },{
            id: "PaymentTerms",
            name: "Payment Terms",
            field: "paymentTerms",//initially it was statusVal
            sortable : false,
            width: 160
        },{
            id: "TotalOrderAmount",
            name: "Total Order Amount",
            field: "totalOrderAmount",//initially it was statusVal
            sortable : false,
            width: 180
        },{
            id: "SupplierItemID",
            name: "Supplier Item ID",
            field: "supplierItemID",//initially it was statusVal
            sortable : false,
            width: 170
        },
		{
			id:"UnitPrice",
			name:"Unit Price",
			field:"unitPrice",
			minWidth:120
		},{
            id: "PriceBasis",
            name: "Price Basis",
            field: "priceBasis",//initially it was statusVal
            sortable : false,
            width: 120
        },{
            id: "PaymentCurrency",
            name: "Payment Currency",
            field: "paymentCurrency",//initially it was statusVal
            sortable : false,
            width: 180
        },{
			id:"TotalLineAmount",
			name:"Total Line Amount",
			field:"totalLineAmount",
			sortable : false,
			width:180
		},{
            id: "UOM",
            name: "UOM",
            field: "uOM",//initially it was statusVal
            sortable : false,
            width: 90
        },{
			id:"ShipToLocation",
			name:"Ship To Location",
			field:"shipToLocation",
			minWidth:200
		},{
            id: "KanbanFlag",
            name: "Kanban Flag",
            field: "kanbanFlag",
            sortable : false,
            width: 120
        },{
			id:"requestQty",
			name:"Request Qty",
			field:"requestQty",
			minWidth:120
		},{
			id:"RequestDate",
			name:"Request Date",
			field:"requestDate",
			minWidth:150
		},{
			id:"requestedShipDate",
			name:"Requested Ship Date",
			field:"requestedShipDate",
			minWidth:200
		},{
            id: "Carrier",
            name: "Carrier",
            field: "carrier",//initially it was statusVal
            sortable : false,
            width: 120
        },{
			id:"CustomerSite",
			name:"Customer Site",
			field:"customerSite",
			minWidth:120
		},{
			id:"PromiseQty",
			name:"Promise Qty",
			field:"promiseQty",
			minWidth:120
		},{
            id: "PromiseDate",
            name: "Promise Date",
            field: "promiseDate",//initially it was statusVal
            sortable : false,
            width: 180
        },{
			id:"promisedShipDate",
			name:"Promised Ship Date",
			field:"promisedShipDate",
			minWidth:160
		},{
            id : "poJsonDownload",
            name : "Download JSON",
            field : "jsonDownload",
            sortable : false,
            width : 150,
            formatter : jsonFormatter,
            cssClass : "alignColumnCenter",
            onClick : function(attr1){
            	console.log(attr1);
            }
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