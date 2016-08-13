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
    var checkedRecords = [], mainGrid = {}, resultSetData={}, errorData = {}, erpData,allGridsData = {};
    
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
			allGridsData = data;
            // remove the loading screen
            commonUtil.removeLoader();
            $('#username').text(data.userData.UserName);
            $('#username').attr('data-global-id',data.userData.GlobalId);
			$('.username-msg').show();
            if(!data.error){
				$('#main-tabs li').off('click').on('click',function(){
					$(this).siblings().removeClass('active');
					$(this).addClass('active');
					var tab = $(this).find('a').attr('data-tab');
					switch(tab){
						case "home":
							fngenerateHomeHtml(data.userData.Role);	
							var dashboardData = data.resultSet;
							erpData = commonUtil.dashboardErp(dashboardData);
							buildContainer('dashboard',erpData,dashboardData);
							errorData = data.errorData;
							var errorCount = buildDashboardGrids(erpData, data.resultSet);
							buildGraph(data.graphData);
							resultSetData = data.resultSet;
							$('#main1 .tabUl li a:first').trigger('click');
							console.log(mainGrid);
							break;
						
						case "supplier":
							fngenerateSupplierHtml();
							commonUtil.handleHideShow('dashboardScreen');
							var supplierData = data.supplierData;
							erpData = commonUtil.dashboardErp(supplierData);
							buildContainer('supplier',erpData,supplierData);
							buildSupplierGrids(erpData, supplierData);
							$('#main1 .tabUl li a:first').trigger('click');
							break;
							
						case "item":
							fngenerateItemHtml();
							commonUtil.handleHideShow('dashboardScreen');
							var itemData = data.itemData;
							erpData = commonUtil.dashboardErp(itemData);
							buildContainer('item',erpData,itemData);
							var errorCount = buildItemGrids(erpData, itemData);
							$('#main1 .tabUl li a:first').trigger('click');
							break;
							
					}
				});
				$('#main-tabs li:first').trigger('click');
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
	
	
	function fngenerateHomeHtml(role){
		var html = "";
		
		var col = 'col-sm-12';
		var errorContainer = "";
		
		if(role == "Admin"){
			col = "col-sm-6";
			errorContainer = "<div class='col-sm-6'>"+
								"<div class='errorActionContainer'>"+
									"<span>Action Required:</span>"+
									"<button id='errorBtn' class='btn btn-danger' click='errorinProcess()'>Errors in Process - <span id='errorCount'>0</span></button>"+
								"</div>"+
							"</div>";
		}
			
		
		html += "<div id='dashboardGridContainer'>"+
					"<div class='row'>"+
						"<div class='col-sm-2 col-xs-6'></div>"+
						"<div class='col-sm-2 col-xs-6'></div>"+
					"</div>"+
					"<div class='clearfix'></div>"+
					"<div id='dashboard-tabs'>"+
						"<div class='row'>"+
							"<div class='"+col+"'>"+
								"<ul class='nav nav-pills tabUl' id='dashboard-ul-tabs'></ul>"+
							"</div>"+
							errorContainer+
						"</div>"+
						"<div class='tab-content' id='dashboard-tab-content'></div>"+
					"</div>"+
				"</div>"+
				
				"<div class='row'>"+
					"<div class='col-sm-12'>"+
						//"<div class='margin-top-10'>"+
							"<div id='highchartContainer'></div>"+
						//"</div>"+
					"</div>"+
				"</div>";

		$('#main1').html(html);	
						
	}
	
	function fngenerateSupplierHtml(){
		var html = "";
		
		html += "<div id='supplierGridContainer'>"+
					"<div class='row'>"+
						"<div class='col-sm-2 col-xs-6'></div>"+
						"<div class='col-sm-2 col-xs-6'></div>"+
					"</div>"+
					"<div class='clearfix'></div>"+
					"<div id='supplier-tabs'>"+
						"<div class='row'>"+
							"<div class='col-sm-12 col-xs-12'>"+
								"<ul class='nav nav-pills tabUl' id='supplier-ul-tabs'></ul>"+
							"</div>"+
						"</div>"+
						"<div class='tab-content' id='supplier-tab-content'></div>"+
					"</div>"+
				"</div>";
				
		$('#main1').html(html);	
	}
	
	function fngenerateItemHtml(){
		var html = "";
		
		html += "<div id='itemGridContainer'>"+
					"<div class='row'>"+
						"<div class='col-sm-2 col-xs-6'></div>"+
						"<div class='col-sm-2 col-xs-6'></div>"+
					"</div>"+
					"<div class='clearfix'></div>"+
					"<div id='item-tabs'>"+
						"<div class='row'>"+
							"<div class='col-sm-12 col-xs-12'>"+
								"<ul class='nav nav-pills tabUl' id='item-ul-tabs'></ul>"+
							"</div>"+
							
						"</div>"+
						"<div class='tab-content' id='item-tab-content'></div>"+
					"</div>"+
				"</div>";
		$('#main1').html(html);	
	}
    
    function buildContainer(type,erpData,resultSet){
        var ul = $('#'+type+'-ul-tabs');
        var tabContent = $('#'+type+'-tab-content');
        var li = '';
        var tabChilds = '';
        var avtiveClass='';
        var activeTab = '';
		
		var className = "";
		if(type == "item" || type == "supplier")
			className = "max-height";
		
        for(var i=0;i<erpData.length;i++){
            if(i== 0){
                avtiveClass='active';
                activeTab = 'in active';
            }
            li += '<li class="'+avtiveClass+'"><a data-toggle="tab" href="#'+type+erpData[i]+'" grid="'+type+'-'+erpData[i]+'-grid'+'">'+erpData[i]+'</a></li>';
            tabChilds += '<div id="'+type+erpData[i]+'" class="tab-pane fade '+activeTab+'"><div class="pagination-data" style="display:none;"><span class="lastPartition"></span><span class="lastRow"></span><span class="nextPartition"></span><span class="nextRow"></span></div><div class="gridContainer"><div class="row"><div class="col-sm-12"><div id="'+type+'-'+erpData[i]+'-grid'+'" class="grid-style '+className+'"></div><div id="'+type+'-'+erpData[i]+'-pager'+'" class="pager-style"></div></div></div></div></div>';
            avtiveClass = '';
            activeTab = '';
        }
        ul.html(li);
        tabContent.html(tabChilds);
		
		for(var i=0;i<erpData.length;i++){
			var tab = $('#'+type+erpData[i]);
			
			var paginationData = resultSet[erpData[i]].pagination;
			
			$(tab).find('.pagination-data .lastPartition').text(paginationData.lastPartition)
			$(tab).find('.pagination-data .lastRow').text(paginationData.lastRow)
			$(tab).find('.pagination-data .nextPartition').text(paginationData.nextPartition)
			$(tab).find('.pagination-data .nextRow').text(paginationData.nextRow);
			
		}
		
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
            
            errorCount += errorData[erpData[i]].series.length;
        }
        return errorCount;
    }
	
	function buildSupplierGrids(erpData, resultSet){
		//var mainGrid = {};
        
         //getting the columns and grid options via the commonUtil API
        var columns = commonUtil.getSupplierGridColumns();
        var options = commonUtil.getDashboardGridOptions();
       
        
        for(var i=0;i<erpData.length;i++){
            //creating a grid and assigning it to mainGrid
            mainGrid['supplier-'+erpData[i]+'-grid'] = Object.create(BuildGrid.prototype);
            
             // passing data to the constructor for the dashboard grid
            mainGrid['supplier-'+erpData[i]+'-grid'].constructor('#supplier-'+erpData[i]+'-grid',resultSet[erpData[i]].series,columns,options,'#supplier-'+erpData[i]+'-pager');
			 
            mainGrid['supplier-'+erpData[i]+'-grid'].createGrid();
            
        }
	}
	
	function buildItemGrids(erpData, resultSet){
		//var mainGrid = {};
        
         //getting the columns and grid options via the commonUtil API
        var columns = commonUtil.getItemGridColumns();
        var options = commonUtil.getDashboardGridOptions();
        var errorCount = 0;
        
        for(var i=0;i<erpData.length;i++){
            //creating a grid and assigning it to mainGrid
            mainGrid['item-'+erpData[i]+'-grid'] = Object.create(BuildGrid.prototype);
            
             // passing data to the constructor for the dashboard grid
             mainGrid['item-'+erpData[i]+'-grid'].constructor('#item-'+erpData[i]+'-grid',resultSet[erpData[i]].series,columns,options,'#item-'+erpData[i]+'-pager');
             mainGrid['item-'+erpData[i]+'-grid'].createGrid();
            
            //errorCount += resultSet[erpData[i]].errorData.length;
        }
        
        return errorCount;
	}
    
    function buildGraph(graphData){
		var errorCount = 0 ;
		for(var i = 0; i < erpData.length ; i++ ){
			errorCount += graphData[erpData[i]][2];
		}
		commonUtil.updateErrorCount(errorCount);
		
		if(errorCount == 0)
			$('#errorCount').attr('disabled','true');
		else
			$('#errorCount').removeAttr('disabled');
		
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
       
        buildContainer('error',erpData,resultSet);
        var columnsErr = commonUtil.getProcessErrorGridColumns();
        var optionsErr = commonUtil.getProcessErrorGridOptions();
        for(var i=0;i<erpData.length;i++){
             mainGrid['error-'+erpData[i]+'-grid'] = Object.create(BuildGrid.prototype);
             
             mainGrid['error-'+erpData[i]+'-grid'].constructor('#error-'+erpData[i]+'-grid', resultSet[erpData[i]].series,columnsErr,optionsErr,'#error-'+erpData[i]+'-pager');
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
    $(document).on('click','#errorBtn',function(e){	
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
        buildErrorGrids(errorData,erpData);
		console.log(mainGrid);
		$('#main2 .tabUl li a:first').trigger('click');
    });
    
    //code to be run on load
    $(document).ready(function(){
        init();
        commonUtil.addLoader();
        //pullPoData();
    });
	
	
	
	$('#submitErrBtn').off('click').on('click',function(){
		var tab = $('#main2 .tab-pane.fade.in.active');
		
		var poNumArray = [];
		
		var activeGrid = $('#main2 .tab-pane.fade.in.active .grid-style').attr('id');
		
		var selectedRows = mainGrid[activeGrid].grid.getSelectedRows();
		
		if(selectedRows.length == 0){
			toastr.error('Select PO to be processed');
			return;
		}
		
		for(var i = 0 ; i < selectedRows.length ;i++){
			var item = mainGrid[activeGrid].grid.getDataItem(selectedRows[i])
			
			poNumArray.push(item.OrderNumber);
		}
		var comment = $('#txtDescErr').val();
		
		var erp = activeGrid.split('-')[1];	
		console.log(poNumArray);
		var userName = $('#username').text();
		var globalId = $('#username').attr('data-global-id');
		var sendObj = JSON.stringify({"erpName":erp,"poNo":poNumArray,"globalId": globalId,"userName": userName,"comment":comment});
		commonUtil.addLoader();
		$.ajax({
			  url :"api/po/processErrorPos",
			  data :sendObj,
			  async:true,
			  crossDomain:true,
			  method:"POST",
		      headers: {
				'content-type': "application/json",
				'cache-control': "no-cache"
			  },
			  success:function(result,status,xhr){
				commonUtil.removeLoader();  
				console.log(result);  
				var successList = result.successList;
				var errorList = result.errorList;
				if(successList.length > 0 || errorList.length > 0){
					
					if(successList.length > 0){
						mainGrid[activeGrid].deleteItems(successList);
						$('#main2 .tab-pane.fade.in.active input[type="checkbox"]').each(function(){
							$(this).attr('checked',false);
						});
						var dashboardGrid = activeGrid.replace('error','dashboard');
						mainGrid[dashboardGrid].updateItems(successList);
						buildGraph(result.graphData);
					}
					var sl = "",slMsg = "",el="",elMsg="";
					if(successList.length > 0){
						sl = successList.join(",");
						slMsg = "Processing Error Request success for "+sl;
					}
					if(errorList.length > 0){
						el = errorList.join(",")
						elMsg = "Error occured while processing process "+el;
					}
					
					if(slMsg)
						toastr.success(slMsg);
					
					if(elMsg)
						toastr.error(elMsg);
					
				}
			  },
			  error:function(xhr,status,error){
				console.log("error");    
			  }
		})	  
		
		
	});
    
    $(document).on('click','.autopager', function(e) {
		  
          e.stopPropagation();
		  var selfEle = $(this);
    	  var activeGrid = $(this).attr('grid');
		  var url = "";
		  var postObj = {};
		  var mainTab = $('.nav-tabs.nav-justified li.active a').attr('data-tab');
		  var subTab = "";
		  if(mainTab == "home"){
			subTab = $('section.active').attr('data-item-type');
			if(subTab == "home")
				url = "api/po/getSegmentedPoDetails"; 
			else
				url = "api/po/getSegmentedErrorDetails";	
		  }  
		  else if(mainTab == "supplier")
			  url = "api/supplier/getSegmentedSupplierDetails"
		  else
			  url = "api/item/getSegmentedItemDetails";
		  
		  var erp = ($(this).attr('grid')).split('-')[1];
			  
		  
		  var paginationData = $(this).closest('.tab-pane').find('.pagination-data');
		  
		   if($(paginationData).find('.nextPartition').text() == "null" || $(paginationData).find('.nextRow').text() == "null"){
			  toastr.error('No further data to show');
			  return;
		  }
		  
		  commonUtil.addLoader();	
		  
		  var pagination = {};
		  pagination.lastPartition = $(paginationData).find('.lastPartition').text();
		  pagination.lastRow = $(paginationData).find('.lastRow').text();
		  pagination.nextPartition = $(paginationData).find('.nextPartition').text();
		  pagination.nextRow = $(paginationData).find('.nextRow').text();
		  
		  
		 
		  postObj.paginationParam = pagination;
		  postObj.erpName = erp;
		  postObj.size = 10;
         
          var currentData = mainGrid[$(this).attr('grid')].dataView.getItems();
		  
		  var newData = "";
		  var newpaginationData = "";
		  $.ajax({
			  url : url,
			  data : JSON.stringify(postObj),
			  async: true,
			  crossDomain: true,
			  method:"POST",
		      headers: {
				'content-type': "application/json",
				'cache-control': "no-cache"
			  },
			  success:function(result,status,xhr){
				  console.log("success");
				  commonUtil.removeLoader();
				  var data = result ;
				  if(mainTab == "home"){
					  if(subTab == "home"){
						newData = data.resultSet[erp].series; 
						newpaginationData = data.resultSet[erp].pagination;  
					  }
					  else{
						newData = data.errorData[erp].series; 
						newpaginationData = data.errorData[erp].pagination;  
					  }
					  newData = fnChangeStatus(newData); 		
					  
				  }  
				  else if(mainTab == "supplier"){
					 newData = data.supplierData[erp].series; 
					 newpaginationData = data.supplierData[erp].pagination; 
				  }  
				  else{
					 newData = data.itemData[erp].series; 
					 newpaginationData = data.itemData[erp].pagination; 
				  }
				  
					$(paginationData).find('.lastPartition').text(newpaginationData.lastPartition);
					$(paginationData).find('.lastRow').text(newpaginationData.lastRow);
					$(paginationData).find('.nextPartition').text(newpaginationData.nextPartition);
					$(paginationData).find('.nextRow').text(newpaginationData.nextRow);
					
					$.merge(currentData,newData);
					mainGrid[activeGrid].grid.invalidate();
					mainGrid[activeGrid].updateGrid(currentData);
					commonUtil.resizeCanvas(activeGrid);
					
					if(newpaginationData.nextRow == "null" && newpaginationData.nextPartition == "null" )
						$(selfEle).remove();
				  
			  },
			  error:function(xhr,status,error){
				  console.log("error");
			  }
		  })
        
          //get new data here and replace with newData
          /*var newData = [{
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
            mainGrid[$(this).attr('grid')].updateGrid(currentData);*/
            
          
    });
	
	function fnChangeStatus(newData){
		for(var i = 0; i< newData.length; i++){
          
          var statusCode = newData[i]['Status'];
          if(statusCode === "1"){
              newData[i]['Status'] = "In-transit";
             
          }
          else if(statusCode === "2"){
              newData[i]['Status'] = "Transaction Completed";
              
          }
          else if(statusCode === "3"){
              newData[i]['Status'] = "Error in Process";
              
          }
		}
		
		return newData;
	}	
	
	
    
    /************************ events on dashboard screen end ****************************/
    
    /****************** exposing API for use from other parts of appilication start *************************/
    return {
        mainGrid : getMainGrid
    }
    /****************** exposing API for use from other parts of appilication end *************************/

})(jQuery);