/*
file: dashboard.js
date: May 27, 2016
file purpose: set of functions for processing PO's with status "Error In Process"
server calls: none
*/

		var isFirstSearch='';
    	var lastPartition='';
    	var lastRow='';
    	var nextPartition='';
    	var nextRow='';
    	var startRowKey='';
    	var tableName='';
    	var endRowKey='';
    	var partition='';
    	var size='';
    	var obj2={};
/*
* Functions encapsulated in IIFE referenced by variable e2OpenProcessError
* @param {Object} jQuery 
*/
var e2OpenDashboard = (function($){
    var checkedRecords = [], mainGrid = {}, resultSetData={}, erpData;
    
    /*
     * To create grid and graph on load (O records) and to adjust the graph height based on screen height
     */
    function init(){
        
        var obj = JSON.parse('{"firstRequest":true,"size":100,"erpName":"SYMIX"}');
    	JSON.stringify(obj);
    	var obj2 = JSON.parse('{"paginationParam":{"lastPartition":null,"lastRow":null,"nextPartition":null,"nextRow":null}}');
    	JSON.stringify(obj2);
    	var finalObj = $.extend(obj, obj2);
    	var reqObj = JSON.stringify(finalObj);
        console.log("reqObj-->"+reqObj);
		
        serviceObj.pullPoData(reqObj).then(function( data, textStatus, jqXHR ) {
            // remove the loading screen
            commonUtil.removeLoader();
            
            if(!data.error){
                var dashboardData = data.resultSet;
                erpData = commonUtil.dashboardErp(dashboardData);
                buildContainer('dashboard',erpData);
                var errorCount = buildDashboardGrids(erpData, data.resultSet);
                buildGraph(data.graphData);
                resultSetData = data.resultSet;
                
                commonUtil.updateErrorCount(errorCount);
            }
            console.log(erpData);
        });
        
        /*//creating a grid and assigning it to mainGrid
        mainGrid = Object.create(BuildGrid.prototype);
        
        //getting the columns and grid options via the commonUtil API
        var columns = commonUtil.getDashboardGridColumns();
        var options = commonUtil.getDashboardGridOptions();

        // passing data to the constructor for the dashboard grid
        mainGrid.constructor('#myGrid',[],columns,options,'#pager');
        
        // adjusting the graph height dynamically (to make sure it comes in first fold for desktop screens
        graphObj.adjustGraphHeight();
        
        // create the dashboard grid
        mainGrid.createGrid();
        
        //plot the graph based on the status
        commonUtil.plotGraph();*/
    }
    
    function buildContainer(type,erpData){
        var ul = $('#'+type+'-ul-tabs');
        var tabContent = $('#'+type+'-tab-content');
        var li = '';
        var tabChilds = '';
        var avtiveClass='';
        var activeTab = '';
        for(var i=0;i<erpData.length;i++){
            if(i== 0){
                avtiveClass='active';
                activeTab = 'in active';
            }
            li += '<li class="'+avtiveClass+'"><a data-toggle="tab" href="#'+type+erpData[i]+'" grid="'+type+'-'+erpData[i]+'-grid'+'">'+erpData[i]+'</a></li>';
            tabChilds += '<div id="'+type+erpData[i]+'" class="tab-pane fade '+activeTab+'"><div class="gridContainer"><div class="row"><div class="col-sm-12"><div id="'+type+'-'+erpData[i]+'-grid'+'" class="grid-style"></div><div id="'+type+'-'+erpData[i]+'-pager'+'" class="pager-style"></div></div></div></div></div>';
            avtiveClass = '';
            activeTab = '';
        }
        ul.html(li);
        tabContent.html(tabChilds);
    }
    
    function buildDashboardGrids(erpData, resultSet){
        //var mainGrid = {};
        
         //getting the columns and grid options via the commonUtil API
        var columns = commonUtil.getDashboardGridColumns();
        var options = commonUtil.getDashboardGridOptions();
        var errorCount = 0;
        
        for(var i=0;i<erpData.length;i++){
            //creating a grid and assigning it to mainGrid
            mainGrid['dashboard-'+erpData[i]+'-grid'] = Object.create(BuildGrid.prototype);
            
             // passing data to the constructor for the dashboard grid
             mainGrid['dashboard-'+erpData[i]+'-grid'].constructor('#dashboard-'+erpData[i]+'-grid',resultSet[erpData[i]].series,columns,options,'#dashboard-'+erpData[i]+'-pager');
             mainGrid['dashboard-'+erpData[i]+'-grid'].createGrid();
            
            errorCount += resultSet[erpData[i]].errorData.length;
        }
        
        return errorCount;
    }
    
    function buildGraph(graphData){
        var graphDataObject={},categoryArr=[], categoryData={},errorGraphData=[], transitGraphData=[],processGraphData=[];
            graphDataObject = graphData;
                
            for(var grph in graphDataObject){
                categoryArr.push(grph);
                errorGraphData.push(graphDataObject[grph][1]);
                transitGraphData.push(graphDataObject[grph][0]);
                processGraphData.push(graphDataObject[grph][2]);
                 
            }
            categoryData['error'] = errorGraphData;
            categoryData['transit'] = transitGraphData;
            categoryData['process'] = processGraphData;
        
            var plotData = commonUtil.prepareGraphData(categoryData);

//          var plotData = commonUtil.createGraphData(graphDataObject);

            
            // plot the graph
            commonUtil.plotGraph(plotData,categoryArr);
    }
    
    function buildErrorGrids(resultSet,erpData){
        //var mainGrid = {};
        
         //getting the columns and grid options via the commonUtil API
       
         
       
        buildContainer('error',erpData);
        var columnsErr = commonUtil.getProcessErrorGridColumns();
             var optionsErr = commonUtil.getProcessErrorGridOptions();
        for(var i=0;i<erpData.length;i++){
             mainGrid['error-'+erpData[i]+'-grid'] = Object.create(BuildGrid.prototype);
             
             mainGrid['error-'+erpData[i]+'-grid'].constructor('#error-'+erpData[i]+'-grid', resultSet[erpData[i]].errorData,columnsErr,optionsErr,'#error-'+erpData[i]+'-pager');
             mainGrid['error-'+erpData[i]+'-grid'].createGrid();
        }
    }
    
    /*
     * To make the call to the service layer with the data for processind PO
     */
    function pullPoData() {
    	
    	var obj = JSON.parse('{"firstRequest":true,"size":100,"erpName":"SYMIX"}');
    	JSON.stringify(obj);
    	var obj2 = JSON.parse('{"paginationParam":{"lastPartition":null,"lastRow":null,"nextPartition":null,"nextRow":null}}');
    	JSON.stringify(obj2);
    	var finalObj = $.extend(obj, obj2);
    	var reqObj = JSON.stringify(finalObj);
    	
    	 
    	
//    	console.log('obj2',obj2);
    			
        // sending the data to the service layer for getting the pull po data (using the serviceObj)
        //the call returns a promise object which executes the .then method which has the server response
//        serviceObj.pullPoData('{"isFirstSearch":true,"paginationParam":{"lastPartition":null,"lastRow":null,"nextPartition":"","nextRow":""},"startRowKey": "","tableName":"" ,"endRowKey":"" ,"partition": "SYMIX_PO","size":100}').then(function( data, textStatus, jqXHR ) {
        
        	serviceObj.pullPoData(reqObj).then(function( data, textStatus, jqXHR ) {
        	// remove the loading screen
            commonUtil.removeLoader();
            
            // assign the gridData property of the grid, to the poDetails
            console.log('data-----',data);
            mainGrid.gridData = data.resultSet.series;
            
            var graphDataObject={},categoryArr=[], categoryData={},errorGraphData=[], transitGraphData=[],processGraphData=[];
            graphDataObject = data.resultSet.graphData;
                
            for(var grph in graphDataObject){
                categoryArr.push(grph);
                errorGraphData.push(graphDataObject[grph][1]);
                transitGraphData.push(graphDataObject[grph][0]);
                processGraphData.push(graphDataObject[grph][2]);
                 
            }
            categoryData['error'] = errorGraphData;
            categoryData['transit'] = transitGraphData;
            categoryData['process'] = processGraphData;
            console.log("graphDataObject====",graphDataObject);
            
            var errObj = data.resultSet.errorData;
            var errCount = Object.keys(errObj).length;
            console.log(errCount);
            
            
//            var graphObj = data.resultSet.graphData;
//            console.log(graphObj.SYMIX);
            
            // prepare the data accordingly (format it)
            mainGrid.prepareData();
            
            // to invalidate the current records in the grid
            mainGrid.grid.invalidate();
            
            // update the received records from the grid
            mainGrid.updateGrid(mainGrid.gridData);

            // update the error count label
            commonUtil.updateErrorCount(mainGrid.errorCount);
            
            // prepare the graph data based on the status
			
			 /*var graphObj={};
			 graphObj.processedCount1= mainGrid.processedCount;
			 graphObj.processedCount2=1;
			 
			 graphObj.inTransitCount1=mainGrid.inTransitCount;
			 graphObj.inTransitCount2=2;
			 
			 graphObj.errorCount1=mainGrid.errorCount;
			 graphObj.errorCount2=3;*/
			 
            var plotData = commonUtil.prepareGraphData(categoryData);

//          var plotData = commonUtil.createGraphData(graphDataObject);

            
            // plot the graph
            commonUtil.plotGraph(plotData,categoryArr);

        });
    }; //pullPoData


    /*
     * To make the call to the service layer with the data for processind PO
     */
    function processPoData() {
        var sendData = {};
        if(checkedRecords && checkedRecords.length > 0){
            sendData = commonUtil.getProcessData(mainGrid.dataView,checkedRecords);
        }
        else{
            toastr.error('Select PO to be processed');
            commonUtil.removeLoader();
            return;
        }
        
        console.log(sendData);
        serviceObj.processPoData(sendData).then(function( data, textStatus, jqXHR ) {
            commonUtil.removeLoader();
            console.log(data);
            mainGrid.updateRecords(data.poNumToStatus);
            
            commonUtil.updateErrorCount(mainGrid.errorCount);
            var plotData = commonUtil.prepareGraphData(mainGrid.processedCount, mainGrid.inTransitCount, mainGrid.errorCount);

            commonUtil.plotGraph(plotData);
            
         });
    }; //processPoData
    
    /*
     * To provide the reference to the instance of the dashboard grid
     */
    function getMainGrid(){
        return mainGrid;
    }


    /************************ events on dashboard screen start ****************************/
    
    //click function defined on the pullPoDataBtn - to pull the data
    
    //kush code 
   /* $('#pullPoDataBtn').click(function() {
        commonUtil.addLoader();
        pullPoData();
    });
    */
    

    //click function defined on the processPoDataBtn - to process the checked records
    $('#processPoDataBtn').click(function() {
        commonUtil.addLoader();
        processPoData();
    });

    //click function defined on the checkboxes in the grid
    $("#myGrid").on('click', ".checkbox-button", function() {

        var $this = $(this);


        if($this.prop('checked')){
            checkedRecords.push($this.attr('record-id'));
        }
        else{
            for(var i=0,l=checkedRecords.length;i<l;i++){
                if(checkedRecords[i] === $this.attr('record-id')){
                    checkedRecords.splice(i,1);
                    break;
                }
            }
        }

    });
    
    //click function defined to submit the checked PO(to process it) in the grid
    $("#errorBtn").click(function(e){
        e.preventDefault();

        var dashboardDataView = commonUtil.getDashboardGridReference().dataView;
        //if( dashboardDataView && dashboardDataView.length < 1 || !dashboardDataView){
            //toastr.error('No Data to Process');
            //return;
        //}

        commonUtil.handleHideShow('processErrorScreen');

        /*var allRecords = dashboardDataView.getItems();
        var errorRecords = [];

        for(var g=0; g < allRecords.length; g++){
            if(allRecords[g]["status"] === 3){
                errorRecords.push(allRecords[g]);
            }
        }
        
        mainGridErr = Object.create(BuildGrid.prototype);
        var columnsErr = commonUtil.getProcessErrorGridColumns();
        var optionsErr = commonUtil.getProcessErrorGridOptions();
        mainGridErr.constructor('#myGridErr',errorRecords,columnsErr,optionsErr,'#pagerErr');
        mainGridErr.createGrid();*/
        buildErrorGrids(resultSetData,erpData);
    });
    
    //code to be run on load
    $(document).ready(function(){
        init();
        commonUtil.addLoader();
        //pullPoData();
    });
    
    $(document).on('click','.autopager', function(e) {
          e.stopPropagation();
    	  
         
          var currentData = mainGrid[$(this).attr('grid')].dataView.getItems();
        
          //get new data here and replace with newData
          var newData = [{
                "Status": "3",
                "Description": "test",
                "OrderNumber": "test",
                "SourceErpName": "1",
                "id":1000001
            },
            {
                "Status": "3",
                "Description": "test",
                "OrderNumber": "test",
                "SourceErpName": "1",
                "id":1000002
            },
            {
                "Status": "3",
                "Description": "test",
                "OrderNumber": "3713933",
                "SourceErpName": "1",
                "id":1000003
            },
            {
                "Status": "3",
                "Description": "test",
                "OrderNumber": "test",
                "SourceErpName": "1",
                "id":1000004
            }];
        
            $.merge(currentData,newData);
            mainGrid[$(this).attr('grid')].grid.invalidate();
            mainGrid[$(this).attr('grid')].updateGrid(currentData);
            
          
    });
    
    /************************ events on dashboard screen end ****************************/
    
    /****************** exposing API for use from other parts of appilication start *************************/
    return {
        mainGrid : getMainGrid
    }
    /****************** exposing API for use from other parts of appilication end *************************/

})(jQuery);