/*
file: dashboard.js
date: May 27, 2016
file purpose: set of functions for processing PO's with status "Error In Process"
server calls: none
*/

/*
* Functions encapsulated in IIFE referenced by variable e2OpenProcessError
* @param {Object} jQuery 
*/
var e2OpenDashboard = (function($){
    var checkedRecords = [], mainGrid;
    
    /*
     * To create grid and graph on load (O records) and to adjust the graph height based on screen height
     */
    function init(){
        //creating a grid and assigning it to mainGrid
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
        commonUtil.plotGraph();
    }
    
    /*
     * To make the call to the service layer with the data for processind PO
     */
    function pullPoData() {
        // sending the data to the service layer for getting the pull po data (using the serviceObj)
        //the call returns a promise object which executes the .then method which has the server response
        serviceObj.pullPoData('{"isFirstSearch":true}').then(function( data, textStatus, jqXHR ) {
            // remove the loading screen
            commonUtil.removeLoader();
            
            // assign the gridData property of the grid, to the poDetails
            mainGrid.gridData = data.poDetails;
            
            // prepare the data accordingly (format it)
            mainGrid.prepareData();
            
            // to invalidate the current records in the grid
            mainGrid.grid.invalidate();
            
            // update the received records from the grid
            mainGrid.updateGrid(mainGrid.gridData);

            // update the error count label
            commonUtil.updateErrorCount(mainGrid.errorCount);
            
            // prepare the graph data based on the status
            var plotData = commonUtil.prepareGraphData(mainGrid.processedCount, mainGrid.inTransitCount, mainGrid.errorCount);
            
            // plot the graph
            commonUtil.plotGraph(plotData);

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
        if( dashboardDataView && dashboardDataView.length < 1 || !dashboardDataView){
            toastr.error('No Data to Process');
            return;
        }

        commonUtil.handleHideShow('processErrorScreen');

        var allRecords = dashboardDataView.getItems();
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
        mainGridErr.createGrid();
    });

    //code to be on load
    $(document).ready(function(){
        init();
        commonUtil.addLoader();
        pullPoData();
    });
    
    /************************ events on dashboard screen end ****************************/
    
    /****************** exposing API for use from other parts of appilication start *************************/
    return {
        mainGrid : getMainGrid
    }
    /****************** exposing API for use from other parts of appilication end *************************/

})(jQuery);