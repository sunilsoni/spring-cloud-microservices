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
        }
        else if(screen === 'processErrorScreen'){
            $("#main1").css("display","none");
            $("#main2").css("display","block");
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
            width: 200
        }, {
            id: "poNum",
            name: "PO Number",
            field: "OrderNumber", //initially it was poNum
            sortable : true,
            width: 150
        }, {
            id: "poDesc",
            name: "PO Description",
            field: "Description",//initially it was poDesc
            sortable : true,
            width: 300
        }, {
            id: "dataSource",
            name: "Data Source",
            field: "SourceErpName",//initially it was dataSource
            sortable : true,
            width: 200
        }, {
            id: "statusVal",
            name: "Status",
            field: "Status",//initially it was statusVal
            sortable : true,
            width: 250
        }];
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
            resizable:true
          },{
            id: "poNum",
            name: "PO Number",
            field: "poNum",
            minWidth: 150,
            resizable:true
          }, {
            id: "poDesc",
            name: "PO Description",
            field: "poDesc",
            minWidth: 300,
            resizable:true
          }, {
            id: "dataSourceName",
            name: "Data Source",
            field: "dataSourceName",
            minWidth: 200,
            resizable:true
          }, {
            id: "statusVal",
            name: "Status",
            field: "statusVal",
            minWidth: 250,
            resizable:true
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
                data: [data.processedCount1,data.processedCount2],
                color: "#90ed7d"
            }, {
                name: 'In-Transit',
                data: [data.inTransitCount1,data.inTransitCount2],
                color: "#7cb5ec"

            }, {
                name: 'Errored',
                data: [data.errorCount1,data.errorCount2],
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
    function plotGraph(plotData){
        var highchartContainer = getGraphContainer();
        if(plotData){
            graphObj.createChart(highchartContainer, plotData);
        }
        else{
            graphObj.createChart(highchartContainer);
        }
        
    }
    
    
    
    
    return{
        addLoader : addLoader,
        removeLoader : removeLoader,
        handleHideShow : handleHideShow,
        getDashboardGridColumns : getDashboardGridColumns,
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
//        createGraphData : createGraphData
    }

})(jQuery);