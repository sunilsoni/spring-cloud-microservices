/*
file: process-error.js
date: May 27, 2016
file purpose: set of functions for processing PO's with status "Error In Process"
server calls: none
*/

/*
* Functions encapsulated in IIFE referenced by variable e2OpenProcessError
* @param {Object} jQuery 
*/
var e2OpenProcessError = (function($){
    
    var checkedRecords = [], mainGridErr;
    
    /*
     * To make the call to the service layer with the data for processind PO
     */
    function processPoData() {
        var sendData = {};
        
        //sending the error grid dataview and the checked record to the get the processed data
        sendData = commonUtil.getProcessData(mainGridErr.dataView,checkedRecords, true);
        console.log(sendData);
        
        //sending the error grid dataview and the checked record to the get the processed data
        serviceObj.processPoData(sendData).then(function( data, textStatus, jqXHR ) {
            // removing the loading screen
            commonUtil.removeLoader();
            console.log(data);
            
            //delete the processed record from the grid
            mainGridErr.deleteRecords(data.poNumToStatus);
            
            //update the dashboard to display the error record as transaction complete record
            var dashboardGrid = commonUtil.getDashboardGridReference();
            dashboardGrid.updateRecords(data.poNumToStatus);
            
            // update the error count label
            commonUtil.updateErrorCount(dashboardGrid.errorCount);
            
            //to get the plot data for creating the graph
            var plotData = commonUtil.prepareGraphData(dashboardGrid.processedCount, dashboardGrid.inTransitCount, dashboardGrid.errorCount);
            
            //plotting the graph based on plot data
            commonUtil.plotGraph(plotData);
            
            //empty the PO details 
            emptyPoDataInput();
         });
    }; //processPoData

    /*
     * To populate the PO details input boxes on checking a record from the grid
     * @param {Object} record 
     */
    function populatePoData(record){
        var poNum = record['poNum'];
        var supplier = record['supplierId'];

        $('#txtSupplierId').val(supplier);
        $('#txtPoNum').val(poNum);
        //Sunil: puting the date in the change log area
        var date1 = new Date();
        var date2 =  date1.getFullYear() + '-' + (date1.getMonth()+1) + '-' +date1.getDate() + ' ' + date1.getHours() + ':' + date1.getMinutes() + ':' + date1.getSeconds();
        date2 = date2+":";
        $('#txtDescErr').val(date2);
    }//populatePoData
    
    /*
     * To make the PO details information blank after a record has been processed
     */
    function emptyPoDataInput(){
        $('#txtSupplierId').val('');
        $('#txtPoNum').val('');
        $('#txtDescErr').val('');
    }//emptyPoDataInput
    
    /**************************** events ****************************/
    
    

    //click function defined to hide the process error screen and showing the dashboard screen
    $("#goBack").click(function(e){
        // preventing default behavior of the anchor tag
        e.preventDefault();
        
        // Using the commonUtil API to hide the process screen and showing the dashboard screen
        commonUtil.handleHideShow('dashboardScreen');
        
        // Using the commonUtil API to get the reference to the dashboard grid; Since this grid is modified while it is in hidden state, it needs to be resized using the resizeCanvas method provided by SlichGrid API.
        //commonUtil.getDashboardGridReference().grid.resizeCanvas();
        
        // Using the commonUtil API to adjust the graph container size
        graphObj.adjustGraphDimention();
    });

    //click function defined on the checkboxes in the grid
    $("#myGridErr").on('click', '.checkbox-err' , function(e){
        var $this = $(this);
        
        //getting the id of the json checked in the grid
        var recordId = $this.attr('record-id');

        //on check of a record
        if($this.prop('checked')){
            checkedRecords.push(recordId);
        }
        else{
            //on uncheck removing it from checked records
            for(var i=0,l=checkedRecords.length;i<l;i++){
                if(checkedRecords[i] === recordId){
                    checkedRecords.splice(i,1);
                    break;
                }
            }
        }
        
        //allowing only 1 record to be checked at a time
        $('#myGridErr').find('.checkbox-err').prop('checked',false);
        $(this).prop('checked',true);
        
        //getting the json of the checked item(through the method provided by SlickGrid DataView API method - getItemById) in the grid; here mainGridErr is the reference to the instance of Slick Grid
        var record = mainGridErr.dataView.getItemById(recordId);
        populatePoData(record);
        
    });

    //click function defined on the checkboxes in the grid
    $("#submitErrBtn").click(function(e){
        // for putting a loading sign on the screen
        commonUtil.addLoader();
        
        // for pprocessing the checked record in the grid
        processPoData();
        
        // emptying the checkedRecords after the record has been processed
        checkedRecords = [];
    });
    
})(jQuery);