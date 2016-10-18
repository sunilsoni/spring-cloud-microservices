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
    var checkedRecords = [], mainGrid = {}, resultSetData={}, errorData = {}, erpData,allGridsData = {},graphData = {};
	var supplierData = {},itemData= {};
    
    /*
     * To create grid and graph on load (O records) and to adjust the graph height based on screen height
     */
    function init(){
        mainGrid = {};errorData = {};erpData={};allGridsData = {};graphData = {};
        var obj = JSON.parse('{"firstRequest":true,"size":1000,"erpName":"SYMIX"}');
    	JSON.stringify(obj);
    	var obj2 = JSON.parse('{"paginationParam":{"lastPartition":null,"lastRow":null,"nextPartition":null,"nextRow":null}}');
    	JSON.stringify(obj2);
    	var finalObj = $.extend(obj, obj2);
    	var reqObj = JSON.stringify(finalObj);
        // // console.log("reqObj-->"+reqObj);
		
        serviceObj.pullPoData(reqObj).then(function( data, textStatus, jqXHR ) {
            if(!data.error){
				allGridsData = data;
				// remove the loading screen
				data.resultSet["SYMIX"].series;
				commonUtil.removeLoader();
				/*$('#username').text(data.userData.UserName);
				$('#username').attr('data-global-id',data.userData.GlobalId);
				$('.username-msg').show();
				$('.logout').show();
				*/
				errorData = data.errorData;
				graphData = data.graphData;
				
				fnGeneratePOSubMenus(data.resultSet);
				fnGenerateSupplierSubMenus(data.resultSet);
				fnGenerateItemSubMenus(data.resultSet);
				
				
				
				
				$('.nav-second-level li a').off('click').on('click',function(e){
					$('#main3').hide();	
					$('#main1').show();			
					e.stopPropagation();
					e.stopImmediatePropagation();
					var li = $(this).closest('ul').closest('li');
					li.siblings().find('.nav-second-level li a').each(function(){
						$(this).removeClass('active');
					});
					$(this).addClass('active');
					
					li.addClass('active');
					li.siblings().removeClass('active');
					
					var tab = $(this).closest('.nav-second-level').attr('data-item');
					var grid = $(this).attr('grid');
					var erp = grid.split('-')[1];
					if(tab == "po"){
						fnGenerateGridHtml(data.resultSet,grid,"Dashboard",data.userData.Role,errorData);
						buildDashboardGrids(erp,data.resultSet);
						buildErrorGrids(errorData,erp);
					}						
					else if(tab == "supplier"){
						if(jQuery.isEmptyObject(supplierData)){
							var pagination = {};
							pagination.lastPartition = null;
							pagination.lastRow = null;
							pagination.nextPartition = null;
							pagination.nextRow = null;
							var obj = JSON.parse('{"firstRequest":true,"size":800,"erpName":"SYMIX"}');
					    	JSON.stringify(obj);
							var postObj = {} ;
							postObj.firstRequest = true; 
							postObj.paginationParam = pagination;
							postObj.erpName = erp;
							postObj.size = 800;
							var finalObj = $.extend(obj, postObj);
					    	//var reqObj = JSON.stringify(finalObj);
							
							commonUtil.addLoader();	
							serviceObj.callToSever("api/supplier/getSegmentedSupplierDetails",JSON.stringify(finalObj),"POST").then(function(result,xhr,status){
								commonUtil.removeLoader();
								supplierData = result;
								window.supplierSeriesData = result.supplierData.SYMIX.series;
								supplierGrid(supplierData,erp,grid);
								//$(".slick-header-menubutton").prop('disabled',false).on('click');
							});
						}
						else
							supplierGrid(supplierData,erp,grid);
						
					}
						
					else if(tab == "item"){
						if(jQuery.isEmptyObject(itemData)){
							var pagination = {};
							pagination.lastPartition = null;
							pagination.lastRow = null;
							pagination.nextPartition = null;
							pagination.nextRow = null;
							var obj = JSON.parse('{"firstRequest":true,"size":800,"erpName":"SYMIX"}');
					    	JSON.stringify(obj);
							var postObj = {} ;
							postObj.firstRequest = true;  
							postObj.paginationParam = pagination;
							postObj.erpName = erp;
							postObj.size = 800;
							var finalObj = $.extend(obj, postObj);
							commonUtil.addLoader();
							serviceObj.callToSever("api/item/getSegmentedItemDetails",JSON.stringify(finalObj),"POST").then(function(result,xhr,status){
								commonUtil.removeLoader();
								itemData = result;
								window.itemSeriesData = itemData.itemData.SYMIX.series;
								itemGrid(itemData,erp,grid);
							});
						}
						else
							itemGrid(itemData,erp,grid);
						
					}
						
				});
				
				
				$('#side-menu li.menu').off('click').on('click',function(e){
					
					
						$(this).siblings().find('a').removeClass('active');
						$(this).find('a').not('.nav-second-level a').addClass('active');
						
						var li = $(this);
						li.addClass('active');
						li.siblings().removeClass('active');
						
						var data_tab = $(this).attr('data-tab');
						
						if(data_tab == "home"){
							/*fngenerateGraphHtml();
							var dashboardData = data.resultSet;
							erpData = commonUtil.dashboardErp(dashboardData);
							
							buildGraph(graphData,erpData);*/
							
							var menuHtml = "";
							menuHtml +='<li data-tab="home" class="menu active">'+
											'<a href="#" class="active"><i class="fa fa-home fa-fw" ></i> Home</a>'+
										'</li>';
										
							$('#side-menu').html(menuHtml);	
							$('#main1').html('');
							$('#main1').hide();
							$('#main2').hide();
							$('#main0').show();
							$('#main3').hide();
						}
						else if(data_tab == "po"){
							fnGeneratePOHtml();
							buildGraph(graphData);
						}
						else
							$(this).find('.nav-second-level li:first a').trigger('click');
					
				});
				
				$('li[data-tab="po"]').trigger('click');
				
            }
            //// // console.log(erpData);
        });
    }
	
	function supplierGrid(data,erp,grid){
		var minHeight = "";	
		var html = '';
		if((data.supplierData[erp].series).length < 16 && (data.supplierData[erp].series).length > 10){
			minHeight = (data.supplierData[erp].series).length * 25 + 75;
		}
		fnGenerateGridHtml(data.supplierData,grid,"Supplier");
		if(minHeight)
			$('.gridContainer .grid-style').css('min-height',minHeight+"px");
		buildSupplierGrids(erp,data.supplierData);
		
		/*html +="<div class='col-sm-12'>"+(data.supplierData[erp].series).length+"</div>";
		html += "</div></div>";			
		
		$('#main1').html(html);	*/
	}
	
	function itemGrid(data,erp,grid){
		var minHeight = "";	
		if((data.itemData[erp].series).length < 16 && (data.itemData[erp].series).length > 10){
			minHeight = (data.itemData[erp].series).length * 25 + 75;
		}
		fnGenerateGridHtml(data.itemData,grid,"Item");
		if(minHeight)
			$('.gridContainer .grid-style').css('min-height',minHeight+"px");
		buildItemGrids(erp,data.itemData);
	}
	
	function fnGeneratePOHtml(){
		var html = "";
		
		html += "<div class='graph_outer'>"+
					"<div class='graph_heading'><span>Supplier Collaboration</span></div>"+
					"<div class='graph_content'>";
		
		html += "<div class='row'>"+
						"<div class='col-sm-12'>"+
							//"<div class='margin-top-10'>"+
								"<div id='highchartContainer' style='height:500px;'></div>"+
							//"</div>"+
						"</div>"+
					"</div>";
					
		html += "</div></div>";			
					
		$('#main1').html(html);				
	}
	
	function fnGenerateGridHtml(data,grid,type,role,errorData){
		
		var newGraphData = {};
		
		var html = "";
		
		var erp = grid.split('-')[1];
		
		if(graphData)
			newGraphData[erp] = graphData[erp]; 
		
		var errorCount = 0 ;
		
		
		
		if(graphData){
			/*for(var i = 0; i < erpData.length ; i++ ){
				errorCount += graphData[erpData[i]][2];
			}*/
			errorCount += graphData[erp][2];
		}
		
		var className = "";
		if(type != "error" && type != "dashboard" && type != "Dashboard")
			className = "max-height";
		
		var disabled = "";
		
		if(type == "dashboard" || type == "Dashboard" && role == "Admin"){
			html += "<div class='row'><div class='col-lg-6'>";
		}
		
		/*if(type == "dashboard" && role == "Admin"){
			
			if(errorCount <= 0)
				disabled = "disabled='true'";
			
			
			 
			html += "<div class='row'><div class='col-sm-12'>"+
						"<div class='errorActionContainer'>"+
							"<button id='errorBtn' class='btn btn-danger' "+disabled+" click='errorinProcess()'>Errors in Process - <span id='errorCount'>"+errorCount+"</span></button>"+
						"</div>"+
					"</div></div>";
		}*/
		
		var label = type +" Data";
		
		var lblMargin = "margin-left:25%;"
		
		if(label == "Dashboard Data"){
			label = "Purchase Order Data";
			lblMargin = "margin-left:30%;"
		}
			
		
		html += '<div class="row" style="margin-bottom:5px;"><div class="col-xs-9 process-error-header"><span style="'+lblMargin+'">'+label+'</span></div><div class="col-xs-3"><button id="exportBtn" grid="'+type+'-'+erp+'-grid'+'" class="btn btn-info" style="float:right;display:none;">Export To  Excel</button></div></div><div class="row"><div class="col-sm-12"><div id="'+type+erp+'" class="tab-pane fade in active"><div class="pagination-data" style="display:none;"><span class="lastPartition"></span><span class="lastRow"></span><span class="nextPartition"></span><span class="nextRow"></span></div><div class="gridContainer"><div class="row"><div class="col-sm-12"><div id="'+type+'-'+erp+'-grid'+'" class="grid-style '+className+'"></div><div id="'+type+'-'+erp+'-pager'+'" class="pager-style"></div></div></div></div></div></div></div>';
		if(type == "dashboard" || type == "Dashboard"){
			html += "<div class='graph_outer' style='margin-top:10px;'>"+
					"<div class='graph_heading' style='height:28px;'><span>"+erp+"</span><a class='graphIconClick' href='#'><img src='../img/maximize_icon.png'></a></div>"+
					"<div class='graph_content' style='padding:0px;'>";
					
			html += "<div class='row'>"+
						"<div class='col-sm-12'>"+
							//"<div class='margin-top-10'>"+
								"<div id='highchartContainer'></div>"+
							//"</div>"+
						"</div>"+
					"</div>";
					
			html += "</div></div>";		
					
			/*if(errorCount > 0 && role == "Admin"){
				var errorHtml = fnGetErrorGridHtml("error",erp);
				
				html += "</div><div class='col-lg-6 errro-data-container'>"+errorHtml+"</div></div>";
			}*/	
			var errorHtml = fnGetErrorGridHtml("error",erp);
			
			html += "</div><div class='col-lg-6 errro-data-container'>"+errorHtml+"</div></div>";
					
			/*var dashboardData = data;
			erpData = commonUtil.dashboardErp(dashboardData);*/		
					
					
		}
		
		$('#main1').html(html);	
		
		var id = "main1";
		
		/*if(type != "error")
			$('#main1').html(html);	
		else{
			$('#main2 .error-container').html(html);
			id = "main2";
		}*/
				

		var paginationData = data[erp].pagination;
		
		$('#'+id+' .tab-pane').find('.pagination-data .lastPartition').text(paginationData.lastPartition)
		$('#'+id+' .tab-pane').find('.pagination-data .lastRow').text(paginationData.lastRow)
		$('#'+id+' .tab-pane').find('.pagination-data .nextPartition').text(paginationData.nextPartition)
		$('#'+id+' .tab-pane').find('.pagination-data .nextRow').text(paginationData.nextRow);
		
		if(type == "dashboard" || type == "Dashboard"){
			buildGraph(newGraphData,erpData);
			if(errorCount > 0 && role == "Admin"){
				paginationData = errorData[erp].pagination	;
				
				$('.errro-data-container').find('.pagination-data .lastPartition').text(paginationData.lastPartition)
				$('.errro-data-container').find('.pagination-data .lastRow').text(paginationData.lastRow)
				$('.errro-data-container').find('.pagination-data .nextPartition').text(paginationData.nextPartition)
				$('.errro-data-container').find('.pagination-data .nextRow').text(paginationData.nextRow);
			}
		}
			
		
		
	}
	
	function fnGetErrorGridHtml(type,erp){
		var html = "";
		
		html += '<div class="row" style="margin-bottom:5px;"><div class="col-xs-9 process-error-header"><span style="margin-left:36%;">Process Error Data</span></div><div class="col-xs-3"><button id="exportBtn" grid="'+type+'-'+erp+'-grid'+'" class="btn btn-info" style="float:right;display:none;">Export To  Excel</button></div></div><div class="row"><div class="col-sm-12"><div id="'+type+erp+'" class="tab-pane fade in active"><div class="pagination-data" style="display:none;"><span class="lastPartition"></span><span class="lastRow"></span><span class="nextPartition"></span><span class="nextRow"></span></div><div class="gridContainer"><div class="row"><div class="col-sm-12"><div id="'+type+'-'+erp+'-grid'+'" class="grid-style"></div><div id="'+type+'-'+erp+'-pager'+'" class="pager-style"></div></div></div></div></div></div></div>';
		
		html += '<div class="tableError">'+
					'<div class="row" id="form">'+
						'<div>'+
							/*'<div class="col-sm-6 form-horizontal">'+
								'<div class="form-group">'+
									'<label for="txtBURegion" class="listview-label control-label col-sm-4 ">PO #</label>'+
									'<div class="col-sm-8">'+
										'<input type="text" class="form-control " placeholder="PO #" id="txtPoNum" disabled="true"/>'+
									'</div>'+
								'</div>'+
							'</div>'+
							'<div class="col-sm-6 form-horizontal">'+
								'<div class="form-group">'+
									'<label for="txtBURegion" class="listview-label control-label col-sm-5 ">Supplier Id</label>'+
									'<div class="col-sm-7">'+
										'<input type="text" class="form-control " placeholder="Supplier Id" id="txtSupplierId" disabled="true"/>'+
									'</div>'+
								'</div>'+
							'</div>'+*/
							'<div class="col-sm-12 form-horizontal">'+
								'<div class="form-group">'+'<p class="details">Change Log : </p>'+
									'<label for="txtBURegion" class="listview-label control-label col-sm-2"></label>'+
									'<div class="col-sm-10">'+
										'<textarea rows="4" class="form-control "  id="txtDescErr"></textarea>'+
									'</div>'+
								'</div>'+
							'</div>'+
							'<div class="col-sm-12 form-horizontal">'+
								'<div class="form-group">'+
									'<div class="col-sm-7">'+
										'<button type="button" class="btn btn-info col-sm-3 pull-right" id="submitErrBtn">Submit</button>'+
									'</div>'+
								'</div>'+
							'</div>'+
						'</div>'+
					'</div>'+
				'</div>';
				
		return html ;		
		
		
		
	}
	
	function fnGetPoDetailsGridHtml(paginationData,type,erp,poNum){
		var html = "";
		
		//html += '<div class="row" style="margin-bottom:5px;"><div class="col-xs-9 process-error-header"><span style="margin-left:15%;">PO '+poNum+' Details</span></div><div class="col-xs-3"><button id="exportBtn" grid="'+type+'-'+erp+'-grid'+'" class="btn btn-info" style="float:right;display:none;">Export To  Excel</button></div></div><div class="row"><div class="col-sm-12"><div id="'+type+erp+'" class="tab-pane fade in active"><div class="pagination-data" style="display:none;"><span class="lastPartition"></span><span class="lastRow"></span><span class="nextPartition"></span><span class="nextRow"></span></div><div class="gridContainer"><div class="row"><div class="col-sm-12"><div id="'+type+'-'+erp+'-grid'+'" class="grid-style max-height"></div><div id="'+type+'-'+erp+'-pager'+'" class="pager-style"></div></div></div></div></div></div></div>';
		html += '<div class="row" style="margin-bottom:5px;"><div class="col-xs-9 process-error-header"><span style="margin-left:15%;">PO Item Details</span></div><div class="col-xs-3"><button id="exportBtn" grid="'+type+'-'+erp+'-grid'+'" class="btn btn-info" style="float:right;display:none;">Export To  Excel</button></div></div><div class="row"><div class="col-sm-12"><div id="'+type+erp+'" class="tab-pane fade in active"><div class="pagination-data" style="display:none;"><span class="lastPartition"></span><span class="lastRow"></span><span class="nextPartition"></span><span class="nextRow"></span></div><div class="gridContainer"><div class="row"><div class="col-sm-12"><div id="'+type+'-'+erp+'-grid'+'" class="grid-style max-height"></div><div id="'+type+'-'+erp+'-pager'+'" class="pager-style"></div></div></div></div></div></div></div>';

		$('#main3 .item-details-container').html(html);
		
		var id = "main3";
		
		$('#'+id+' .tab-pane').find('.pagination-data .lastPartition').text(paginationData.lastPartition)
		$('#'+id+' .tab-pane').find('.pagination-data .lastRow').text(paginationData.lastRow)
		$('#'+id+' .tab-pane').find('.pagination-data .nextPartition').text(paginationData.nextPartition)
		$('#'+id+' .tab-pane').find('.pagination-data .nextRow').text(paginationData.nextRow);
		
		
	}
	
	function fnGeneratePOSubMenus(dashboardData){
		erpData = commonUtil.dashboardErp(dashboardData);
		
		var html = "";
		
		for(var i = 0 ; i < erpData.length ; i++){
			//html += '<li><a data-toggle="tab" href="#" grid="dashboard-'+erpData[i]+'-grid'+'">'+erpData[i]+'</a></li>';
			html += '<li><a data-toggle="tab" href="#" grid="dashboard-'+erpData[i]+'-grid'+'"><span class="menu-text">'+erpData[i]+'</span><span class="selected"></span></a></li>';
		}
		
		$('#PoSubMenu').html(html);
	}
	
	function fnGenerateSupplierSubMenus(supplierData){
		erpData = commonUtil.dashboardErp(supplierData);
		
		var html = "";
		
		for(var i = 0 ; i < erpData.length ; i++){
			html += '<li><a data-toggle="tab" href="#" grid="supplier-'+erpData[i]+'-grid'+'"><span class="menu-text">'+erpData[i]+'</span><span class="selected"></span></a></li>';
		}
		
		$('#SuppSubMenu').html(html);
	}
	
	function fnGenerateItemSubMenus(itemData){
		erpData = commonUtil.dashboardErp(itemData);
		
		var html = "";
		
		for(var i = 0 ; i < erpData.length ; i++){
			html += '<li><a data-toggle="tab" href="#" grid="item-'+erpData[i]+'-grid'+'"><span class="menu-text">'+erpData[i]+'</span><span class="selected"></span></a></li>';
		}
		
		$('#ItemSubMenu').html(html);
	}
	
	function fngenerateGraphHtml(){
		var html = "";
		 html += "<div class='row'>"+
					"<div class='col-sm-12'>"+
						//"<div class='margin-top-10'>"+
							"<div id='highchartContainer'></div>"+
						//"</div>"+
					"</div>"+
				"</div>";
				
		$('#main1').html(html);			
				
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
    
    function buildDashboardGrids(erp, resultSet){
        //var mainGrid = {};
        
         //getting the columns and grid options via the commonUtil API
        var columns = commonUtil.getDashboardGridColumns();
        var options = commonUtil.getDashboardGridOptions();
        var errorCount = 0;
        
        //for(var i=0;i<erpData.length;i++){
            //creating a grid and assigning it to mainGrid
            mainGrid['Dashboard-'+erp+'-grid'] = Object.create(BuildGrid.prototype);
            
             // passing data to the constructor for the dashboard grid
             mainGrid['Dashboard-'+erp+'-grid'].constructor('#Dashboard-'+erp+'-grid',resultSet[erp].series,columns,options,'#Dashboard-'+erp+'-pager');
             mainGrid['Dashboard-'+erp+'-grid'].createGrid();
            
            //errorCount += errorData[erp].series.length;
        //}
        return errorCount;
    }
	
	function buildSupplierGrids(erp, resultSet){
		//var mainGrid = {};
        
         //getting the columns and grid options via the commonUtil API
        var columns = commonUtil.getSupplierGridColumns();
        var options = commonUtil.getDashboardGridOptions();
       
        
        //for(var i=0;i<erpData.length;i++){
            //creating a grid and assigning it to mainGrid
            mainGrid['Supplier-'+erp+'-grid'] = Object.create(BuildGrid.prototype);
            
             // passing data to the constructor for the dashboard grid
            mainGrid['Supplier-'+erp+'-grid'].constructor('#Supplier-'+erp+'-grid',resultSet[erp].series,columns,options,'#Supplier-'+erp+'-pager');
			 
            mainGrid['Supplier-'+erp+'-grid'].createGrid();
            
       // }
	}
	
	function buildItemGrids(erp, resultSet){
		//var mainGrid = {};
        
         //getting the columns and grid options via the commonUtil API
        var columns = commonUtil.getItemGridColumns();
        var options = commonUtil.getDashboardGridOptions();
        var errorCount = 0;
        
        //for(var i=0;i<erpData.length;i++){
            //creating a grid and assigning it to mainGrid
            mainGrid['Item-'+erp+'-grid'] = Object.create(BuildGrid.prototype);
            
             // passing data to the constructor for the dashboard grid
             mainGrid['Item-'+erp+'-grid'].constructor('#Item-'+erp+'-grid',resultSet[erp].series,columns,options,'#Item-'+erp+'-pager');
             mainGrid['Item-'+erp+'-grid'].createGrid();
            
            //errorCount += resultSet[erpData[i]].errorData.length;
       // }
        
        return errorCount;
	}
    
    function buildGraph(graphData,erpData,container){
		/*if(!erpData)
			erpData = erpData;
		
		var errorCount = 0 ;
		for(var i = 0; i < erpData.length ; i++ ){
			errorCount += graphData[erpData[i]][2];
		}
		commonUtil.updateErrorCount(errorCount);
		
		if(errorCount == 0)
			$('#errorCount').attr('disabled','true');
		else
			$('#errorCount').removeAttr('disabled');*/
		
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

			var name = {"x-axis":"","y-axis":"Purchase Orders","title":"Supplier Collaboration Dashboard"};
			if(container)
				commonUtil.plotGraph(plotData,name,categoryArr,container);
			else
				commonUtil.plotGraph(plotData,name,categoryArr);
    }
	
	function buildECNGraph(graphData,erpData){
		var graphDataObject={},categoryArr=[], categoryData={},errorGraphData=[], processGraphData=[];
            graphDataObject = graphData;
                
            for(var grph in graphDataObject){
                categoryArr.push(grph);
                errorGraphData.push(graphDataObject[grph][1]);
                
                processGraphData.push(graphDataObject[grph][0]);
                 
            }
            categoryData['error'] = errorGraphData;
            
            categoryData['process'] = processGraphData;
        
            var plotData = commonUtil.prepareECNGraphData(categoryData);

			var name = {"x-axis":"","y-axis":"Purchase Orders","title":"Widchill PLM Dashboard"};
            commonUtil.plotGraph(plotData,name,categoryArr);
		
	}
    
    function buildErrorGrids(resultSet,erp){
        //var mainGrid = {};
        
         //getting the columns and grid options via the commonUtil API
       
        //buildContainer('error',erpData,resultSet);
		if($('#error-'+erp+'-grid').length > 0){
			var columnsErr = commonUtil.getProcessErrorGridColumns();
			var optionsErr = commonUtil.getProcessErrorGridOptions();
			//for(var i=0;i<erpData.length;i++){
				 mainGrid['error-'+erp+'-grid'] = Object.create(BuildGrid.prototype);
				 
				 mainGrid['error-'+erp+'-grid'].constructor('#error-'+erp+'-grid', resultSet[erp].series,columnsErr,optionsErr,'#error-'+erp+'-pager');
				 mainGrid['error-'+erp+'-grid'].createGrid();
			//}
		}
        
    }
	
	
	function buildPODetailsGrid(data,erp){
		if($('#podetails-'+erp+'-grid').length > 0){
			var PODetailscolumnsErr = commonUtil.getPOItemDetailsColumns();
			var optionsErr = commonUtil.getDashboardGridOptions();
			
			 mainGrid['podetails-'+erp+'-grid'] = Object.create(BuildGrid.prototype);
			 
			 mainGrid['podetails-'+erp+'-grid'].constructor('#podetails-'+erp+'-grid', data,PODetailscolumnsErr,optionsErr,'#podetails-'+erp+'-pager');
			 mainGrid['podetails-'+erp+'-grid'].createGrid();
			
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
    	
    	 
    	
//    	// // console.log('obj2',obj2);
    			
        // sending the data to the service layer for getting the pull po data (using the serviceObj)
        //the call returns a promise object which executes the .then method which has the server response
//        serviceObj.pullPoData('{"isFirstSearch":true,"paginationParam":{"lastPartition":null,"lastRow":null,"nextPartition":"","nextRow":""},"startRowKey": "","tableName":"" ,"endRowKey":"" ,"partition": "SYMIX_PO","size":100}').then(function( data, textStatus, jqXHR ) {
        
        	serviceObj.pullPoData(reqObj).then(function( data, textStatus, jqXHR ) {
        	// remove the loading screen
            commonUtil.removeLoader();
            
            // assign the gridData property of the grid, to the poDetails
            // // console.log('data-----',data);
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
            //// // console.log("graphDataObject====",graphDataObject);
            
            var errObj = data.resultSet.errorData;
            var errCount = Object.keys(errObj).length;
            //// // console.log(errCount);
            
            
//            var graphObj = data.resultSet.graphData;
//            // // console.log(graphObj.SYMIX);
            
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
        
        //// // console.log(sendData);
        serviceObj.processPoData(sendData).then(function( data, textStatus, jqXHR ) {
            commonUtil.removeLoader();
           console.log("podata: "+data);
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
		
		var grid = (($('.nav.nav-second-level.collapse.in a.active').attr('grid')).replace('dashboard','error'));
		var erp = grid.split('-')[1];
		
		fnGenerateGridHtml(errorData,grid,"error");
		buildErrorGrids(errorData,erp);
        //buildErrorGrids(errorData,erpData);
		// // console.log(mainGrid);
		//$('#main2 .tabUl li a:first').trigger('click');
    });
	
	$(document).on('click','#submitErrBtn',function(e){	
	//$('#submitErrBtn').off('click').on('click',function(){
		//var tab = $('#main2 .tab-pane.fade.in.active');
		
		var poNumArray = [];
		
		//var activeGrid = $('#main2 .tab-pane.fade.in.active .grid-style').attr('id');
		var activeGrid = ($('.nav.nav-second-level.collapse.in a.active').attr('grid')).replace('dashboard','error');
		
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
		if((comment.length<=28))

		{
		
		if ( this.host !== window.location.host ) {
            if ( window.confirm('Please Enter Valid Reason ') ) {
            	 comment.focus();
                
                return false;
            }
            else {
            	  // // console.log('Reprocessing The Data');
            	  return false;
            }
		
		}
		} 
		
		var erp = activeGrid.split('-')[1];	
		// // console.log(poNumArray);
		var userName = $('#username').text();
		var globalId = $('#username').attr('data-global-id');
		var sendObj = JSON.stringify({"erpName":erp,"poNo":poNumArray,"globalId": globalId,"userName": userName,"comment":comment});
		commonUtil.addLoader();
		serviceObj.callToSever("api/po/processErrorPos",sendObj,"POST").then(function(result,status,xhr){
				commonUtil.removeLoader();  
				// // console.log(result);  
				var successList = result.successList;
				var errorList = result.errorList;
				if(successList.length > 0 || errorList.length > 0){
					
					if(successList.length > 0){
						mainGrid[activeGrid].deleteItems(successList);
						$('#main1 .errro-data-container input[type="checkbox"]').each(function(){
							$(this).attr('checked',false);
						});
						var dashboardGrid = activeGrid.replace('error','dashboard');
						mainGrid[dashboardGrid].updateItems(successList);
						graphData = result.graphData;
						var newGraphData = {};
						newGraphData[erp] = graphData[erp];
						buildGraph(newGraphData);
						$('#txtDescErr').val('');
						
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
					
		});
	});
    
    $(document).on('click','.sc .autopager', function(e) {
          e.stopPropagation();
		  var selfEle = $(this);
    	  var activeGrid = $(this).attr('grid');
		  var url = "";
		  var postObj = {};
		  var mainTab = $('.nav.nav-second-level.collapse.in').attr('data-item');
		  var subTab = "";
		  if(mainTab == "po"){
			if(activeGrid.indexOf('error') != -1)
				url = "api/po/getSegmentedErrorDetails";
			else if($('#main3').hasClass('active'))
				url = "api/po/getPoItemDetail";
			else
				url = "api/po/getSegmentedPoDetails"; 
			
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
		  serviceObj.callToSever(url,JSON.stringify(postObj),"POST").then(function(result,status,xhr){
				  // // console.log("success");
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
			  
		  });
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
	
	
	$(document).on('click','.grid-style .po-number',function(e){
		e.preventDefault();
		
		/*$('.nav.nav-second-level.collapse.in li').each(function(){
			$(this).addClass('disabled');
		});*/
		
		var poNum = $(this).attr('data-value');
		var erp = ($('.nav.nav-second-level.collapse.in li a.active').attr('grid')).split('-')[1];
		
		var reqObj = {
			"poNum": poNum,
			"erpName": erp,
			"size": 100,
			"paginationParam": {
			  "lastPartition": null,
			  "lastRow": null,
			  "nextPartition": null,
			  "nextRow": null
			}
		}
		
		
		commonUtil.addLoader();
		
		serviceObj.callToSever("api/po/getPoItemDetail",JSON.stringify(reqObj),"POST").then(function(result,status,xhr){
			  $('#main1').hide();
			  $('#main3').show();
			  $('#main3').addClass('active');
			  // // console.log("success");
			  var data = result.resultSet.series;
			  window.poItemSeriesData = data;
			  var minHeight = "";
			  if(data.length < 16 && data.length > 10){
					minHeight = (data).length * 25 + 75;
			  }
			  commonUtil.removeLoader();
			  fnGetPoDetailsGridHtml(result.resultSet.pagination,"podetails",erp,poNum);
			  if(minHeight)
				$('#main3 .gridContainer .grid-style').css('min-height',minHeight+"px");
			  buildPODetailsGrid(data,erp);
		});
		
	});
	
	$(document).on('click','#showDashboard',function(e){
		$('#main3').hide();
		$('#main1').show();
		$('#main3').removeClass('active');
		$('.nav.nav-second-level.collapse.in li').each(function(){
			$(this).removeClass('disabled');
		});
	});
	
	
	$(document).on('click','#exportBtn',function(e){
		var excelOptions = {
			  headerStyle: {
				  font: {
					  bold: true,  //enable bold
					  font: 12, // font size
					  color: 'ffffff' //font color --Note: Add 00 before the color code
				  },
				  fill: {   //fill background
					  type: 'pattern', 
					  patternType: 'solid',
					  fgColor: '428BCA' //background color --Note: Add 00 before the color code
				  }
			  },
			  cellStyle: {
				  font: {
					  bold: false,  //enable bold
					  font: 12, // font size
					  color: '000000' //font color --Note: Add 00 before the color code
				  },
				  fill: {   //fill background
					  type: 'pattern',
					  patternType: 'solid',
					  fgColor: 'ffffff' //background color --Note: Add 00 before the color code
				  }
			  },
		  };
  
		var activeGrid = $(this).attr('grid');
		
		var Data = mainGrid[activeGrid].dataView.getItems();
		
		 $('body').exportToExcel("Report.xlsx", "Report", Data, excelOptions, function (response) {
			// // console.log(response);
			window.open($('#downloadLink').attr('href'));
		});
	});
	
	//code to be run on load
	
	
    $(document).ready(function(){
		  
		var menuHtml = "";
		menuHtml +='<li data-tab="home" class="menu active">'+
                        '<a href="#" class="active"><i class="fa fa-home fa-fw" ></i> Home</a>'+
                    '</li>';
					
		$('#side-menu').html(menuHtml);	
		
		$('#scBtn').off('click').on('click',function(){
			$('#main1').removeClass('plm');
			$('#main1').addClass('sc');
			
			$('#page-wrapper').removeClass('plm');
			$('#page-wrapper').addClass('sc');
			menuHtml = "";
			menuHtml += '<li data-tab="home" class="menu">'+
                            '<a href="#"><i class="fa fa-home fa-fw"></i> Home</a>'+
                        '</li>'+
                        '<li data-tab="po" class="menu active">'+
                            '<a href="#"><i class="fa fa-dashboard fa-fw"></i> PO<span class="fa arrow"></span></a>'+
                            '<ul class="nav nav-second-level collapse in" id="PoSubMenu" data-item="po"></span></ul>'+
                        '</li>'+
						'<li data-tab="supplier" class="menu">'+
                            '<a href="#"><i class="fa fa-truck"></i> Supplier<span class="fa arrow"></span></a>'+
                            '<ul class="nav nav-second-level" id="SuppSubMenu" data-item="supplier"></ul>'+
                        '</li>'+
                        '<li data-tab="item" class="menu">'+
                            '<a href="#"><i class="fa fa-files-o fa-fw"></i> Item<span class="fa arrow"></span></a>'+
                            '<ul class="nav nav-second-level" id="ItemSubMenu" data-item="item"></ul>'+
                        '</li>';
						
			$('#side-menu').html(menuHtml);
			$('#side-menu').metisMenu();	
			$('#main0').hide();
			$('#main1').show();
			$("body").scrollTop(0);
			
			
			init();
			
			commonUtil.addLoader();
		});	
        
        $('#plmBtn').off('click').on('click',function(){
			$('#main1').removeClass('sc');
			$('#main1').addClass('plm');
			
			$('#page-wrapper').removeClass('sc');
			$('#page-wrapper').addClass('plm');
			menuHtml = "";
			/*menuHtml += '<li data-tab="home" class="menu">'+
                            '<a href="#"><i class="fa fa-home fa-fw"></i> Home</a>'+
                        '</li>'+
                        '<li data-tab="ecn" class="menu active">'+
                            '<a href="#"><i class="fa fa-dashboard fa-fw"></i>ECN<span class="fa arrow"></span></a>'+
                            '<ul class="nav nav-second-level collapse in" id="ECNSubMenu" data-item="ecn"></ul>'+
                        '</li>';*/
			
			menuHtml += '<li data-tab="home" class="menu">'+
            				'<a href="#" class="active"><i class="fa fa-home fa-fw"></i> Home</a>'+
            			'</li>';
						
			$('#side-menu').html(menuHtml);
			$('#side-menu').metisMenu();	
			$('#main0').hide();
			$('#main1').show();
			$("body").scrollTop(0);	
//			commonUtil.addLoader();
        	fngenerateWIPHtml()
			EcnInit();
						
		});
    });	

	function EcnInit(){
		var obj = JSON.parse('{"firstRequest":true,"size":100,"erpName":"SYMIX"}');
    	JSON.stringify(obj);
    	var obj2 = JSON.parse('{"paginationParam":{"lastPartition":null,"lastRow":null,"nextPartition":null,"nextRow":null}}');
    	JSON.stringify(obj2);
    	var finalObj = $.extend(obj, obj2);
    	var reqObj = JSON.stringify(finalObj);
        // // console.log("reqObj-->"+reqObj);
		var type = "ecn";
		
		//serviceObj.pullPoData(reqObj,type).then(function( data, textStatus, jqXHR ) {
			data = {"message":"OK","resultSet":{"SYMIX":{"series":[{"PTCAckMsg":"PTCACkMessage","Description":"CUMMINS PART NPI","BomPayloadProcessed":"true","ModifiedDate":"2016-07-22T00:00:00Z","ProcessedDate":"2016-07-27T00:00:00Z","EcnRequestor":"Administrator","Plant":"Reynosa","UIReprocessingDate":"2016-07-27T00:00:00Z","PartError":"true","id":"0004","BomErrorMsg":"BomErrorMessage","ECNNumber":"115","PtcAck":"false","isErrorDataRequired":"false","Status":"3","OutputXMLEtag":"cdc3b234","PartPayloadProcessed":"false","ERPName":"Symix","Error":"401","BomError":"true","TxnID":"5","PartPayloadProcessedDate":"2016-07-22T00:00:00Z","InputXMLEtag":"cdc3b121","UIReprocessing":"part/bom","CreatedDate":"2016-07-22T00:00:00Z","BomPayloadProcessedDate":"2016-07-23T00:00:00Z","Region":"Reynosa","PTCAckSentDate":"2016-07-26T00:00:00Z","PartErrorMsg":"PartErrorMessage"},{"PTCAckMsg":"PTCACkMessage","Description":"CUMMINS PART NPI","BomPayloadProcessed":"true","ModifiedDate":"2016-07-22T00:00:00Z","ProcessedDate":"2016-07-27T00:00:00Z","EcnRequestor":"Administrator","Plant":"Reynosa","UIReprocessingDate":"2016-07-27T00:00:00Z","PartError":"true","id":"0006","BomErrorMsg":"BomErrorMessage","ECNNumber":"116","PtcAck":"false","isErrorDataRequired":"false","Status":"3","OutputXMLEtag":"cdc3b234","PartPayloadProcessed":"false","ERPName":"Symix","Error":"404","BomError":"true","TxnID":"6","PartPayloadProcessedDate":"2016-07-22T00:00:00Z","InputXMLEtag":"cdc3b121","UIReprocessing":"part/bom","CreatedDate":"2016-07-21T00:00:00Z","BomPayloadProcessedDate":"2016-07-23T00:00:00Z","Region":"Reynosa","PTCAckSentDate":"2016-07-26T00:00:00Z","PartErrorMsg":"PartErrorMessage"}],"pagination":{"lastPartition":null,"lastRow":null,"nextPartition":"1!8!U0FQX1BP","nextRow":"1!8!MDAwNw--"}}},"graphData":{"SYMIX":[6,4,2]},"errorData":{"SYMIX":{"series":[{"PTCAckMsg":"PTCACkMessage","Description":"CUMMINS PART NPI","BomPayloadProcessed":"true","ModifiedDate":"2016-07-22T00:00:00Z","ProcessedDate":"2016-07-27T00:00:00Z","EcnRequestor":"Administrator","Plant":"Reynosa","UIReprocessingDate":"2016-07-27T00:00:00Z","PartError":"true","id":"0004","BomErrorMsg":"BomErrorMessage","ECNNumber":"115","PtcAck":"false","isErrorDataRequired":"false","Status":"3","OutputXMLEtag":"cdc3b234","PartPayloadProcessed":"false","ERPName":"Symix","Error":"401","BomError":"true","TxnID":"5","PartPayloadProcessedDate":"2016-07-22T00:00:00Z","InputXMLEtag":"cdc3b121","UIReprocessing":"part/bom","CreatedDate":"2016-07-22T00:00:00Z","BomPayloadProcessedDate":"2016-07-23T00:00:00Z","Region":"Reynosa","PTCAckSentDate":"2016-07-26T00:00:00Z","PartErrorMsg":"PartErrorMessage"},{"PTCAckMsg":"PTCACkMessage","Description":"CUMMINS PART NPI","BomPayloadProcessed":"true","ModifiedDate":"2016-07-22T00:00:00Z","ProcessedDate":"2016-07-27T00:00:00Z","EcnRequestor":"Administrator","Plant":"Reynosa","UIReprocessingDate":"2016-07-27T00:00:00Z","PartError":"true","id":"0006","BomErrorMsg":"BomErrorMessage","ECNNumber":"116","PtcAck":"false","isErrorDataRequired":"false","Status":"3","OutputXMLEtag":"cdc3b234","PartPayloadProcessed":"false","ERPName":"Symix","Error":"404","BomError":"true","TxnID":"6","PartPayloadProcessedDate":"2016-07-22T00:00:00Z","InputXMLEtag":"cdc3b121","UIReprocessing":"part/bom","CreatedDate":"2016-07-21T00:00:00Z","BomPayloadProcessedDate":"2016-07-23T00:00:00Z","Region":"Reynosa","PTCAckSentDate":"2016-07-26T00:00:00Z","PartErrorMsg":"PartErrorMessage"}],"pagination":{"lastPartition":null,"lastRow":null,"nextPartition":"1!8!U0FQX1BP","nextRow":"1!8!MDAwNw--"}}},"userData":{"Role":"Admin","UserName":"Sunil Soni","GlobalId":"csonisk"},"error":false}
			mainGrid = {};errorData = {};erpData={};allGridsData = {};graphData = {};
			if(!data.error){
				allGridsData = data;
				
				$('#username').text("Sunil Soni");
				$('#username').attr('data-global-id',"csonisk");
				$('.username-msg').show();
				$('.logout').show();
				
				errorData = data.errorData;
				graphData = data.graphData;
				
				$('#username').text(data.userData.UserName);
				$('#username').attr('data-global-id',data.userData.GlobalId);
				$('.username-msg').show();
				$('.logout').show();
				
				commonUtil.removeLoader();
				
				fnGenerateECNSubMenus(data.resultSet);
				
				$('.nav-second-level li a').off('click').on('click',function(e){	
					e.stopPropagation();
					e.stopImmediatePropagation();
					var li = $(this).closest('ul').closest('li');
					li.siblings().find('.nav-second-level li a').each(function(){
						$(this).removeClass('active');
					});
					$(this).addClass('active');
					var tab = $(this).closest('.nav-second-level').attr('data-item');
					var grid = $(this).attr('grid');
					var erp = grid.split('-')[1];
					if(tab == "ecn"){
						fnGenerateECNGridHtml(data.resultSet,grid,"ecn",data.userData.Role,data.graphData,errorData);
						buildECNGrids(erp,data.resultSet);
						buildECNErrorGrids(errorData,erp);
						
					}	
				});
				
				
				$('#side-menu li.menu').off('click').on('click',function(e){
					$(this).siblings().find('a').removeClass('active');
					$(this).find('a').not('.nav-second-level a').addClass('active');
					
					var li = $(this);
					li.addClass('active');
					li.siblings().removeClass('active');
					
					var data_tab = $(this).attr('data-tab');
					
					if(data_tab == "home"){
						var menuHtml = "";
						menuHtml +='<li data-tab="home" class="menu active">'+
										'<a href="#" class="active"><i class="fa fa-home fa-fw" ></i> Home</a>'+
									'</li>';
									
						$('#side-menu').html(menuHtml);	
						$('#main1').html('');
						$('#main1').hide();
						$('#main2').hide();
						$('#main0').show();
					}
					else if(data_tab == "ecn"){
						$('#main2').hide();
						$('#main1').show();
						fnGenerateEcnHtml();
						buildECNGraph(graphData);
					}
					else
						$(this).find('.nav-second-level li:first a').trigger('click');
					
				});
				
				$('li[data-tab="ecn"]').trigger('click');
			}
			
		//});	
	}

	function fnGenerateECNSubMenus(ecnData){
		erpData = commonUtil.dashboardErp(ecnData);
		
		var html = "";
		
		for(var i = 0 ; i < erpData.length ; i++){
			html += '<li><a data-toggle="tab" href="#" grid="ecn-'+erpData[i]+'-grid"><span class="menu-text">'+erpData[i]+'</span><span class="selected"></span></a></li>';
		}
		$('#ECNSubMenu').html(html);
		
	}

	function fnGenerateEcnHtml(){
		var html = "";
		
		html += "<div class='graph_outer'>"+
					"<div class='graph_heading'><span>Windchill PLM Dashboard</span></div>"+
					"<div class='graph_content'>";	
		
		html += "<div class='row'>"+
						"<div class='col-sm-12'>"+
							//"<div class='margin-top-10'>"+
								"<div id='highchartContainer' style='height:500px;'></div>"+
							//"</div>"+
						"</div>"+
					"</div>";
					
		html += "</div></div>";			
					
		$('#main1').html(html);		
		
	}

	function fnGenerateECNGridHtml(ecnData,grid,type,role,errorData){
		var html = "";
		
		var newGraphData = {};
		
		var html = "";
		
		var erp = grid.split('-')[1];
		
		
		
		if(graphData)
			newGraphData[erp] = graphData[erp]; 
		
		var errorCount = 0 ;
		
		if(graphData){
			errorCount += graphData[erp][1];
		}
		
		//dividing screen 
		
		var className = "";
		if(type != "error" && type != "ecn")
			className = "max-height";
		
		var disabled = "";
		
		if(type == "ecn" && errorCount > 0 && role == "Admin"){
			html += "<div class='row'><div class='col-lg-6'>";
		}
		
			var label = type.toUpperCase()+" DATA";
			
			var lblMargin = "margin-left:25%;"
				
				if(label == "DASHBOARD DATA"){
					label = "ECN DATA";
					lblMargin = "margin-left:15%;"
				}
			
			html += '<div class="row" style="margin-bottom:5px;"><div class="col-xs-9 process-error-header"><span style="'+lblMargin+'">'+label+'</span></div><div class="col-xs-3"><button id="exportBtn" grid="'+type+'-'+erp+'-grid'+'" class="btn btn-info" style="float:right;display:none;">Export To  Excel</button></div></div><div class="row"><div class="col-sm-12"><div id="'+type+erp+'" class="tab-pane fade in active"><div class="pagination-data" style="display:none;"><span class="lastPartition"></span><span class="lastRow"></span><span class="nextPartition"></span><span class="nextRow"></span></div><div class="gridContainer"><div class="row"><div class="col-sm-12"><div id="'+type+'-'+erp+'-grid'+'" class="grid-style '+className+'"></div><div id="'+type+'-'+erp+'-pager'+'" class="pager-style"></div></div></div></div></div></div></div>';
			
			if(type == "ecn"){
				html += "<div class='graph_outer' style='margin-top:10px;'>"+
						"<div class='graph_heading' style='height:28px;'><span>"+erp+"</span><a class='graphIconClick' href='#'><img src='../img/maximize_icon.png'></a></div>"+
						"<div class='graph_content' style='padding:0px;'>";
						
				html += "<div class='row'>"+
							"<div class='col-sm-12'>"+
								//"<div class='margin-top-10'>"+
									"<div id='highchartContainer'></div>"+
								//"</div>"+
							"</div>"+
						"</div>";
						
				html += "</div></div>";		
						
				if(errorCount > 0 && role == "Admin"){
					var errorHtml = fnGetECNErrorGridHtml("error",erp);
					
					html += "</div><div class='col-lg-6 errro-data-container'>"+errorHtml+"</div></div>";
				}	
						
						
				/*var dashboardData = data;
				erpData = commonUtil.dashboardErp(dashboardData);*/		
						
						
			}
		//ends
		
		
		
		/*var lblMargin = "margin-left:25%;"
		var label = "ECN DATA";
		
		var disabled = "";
		if(errorCount <= 0)
			disabled = "disabled='true'";
		
		var ProcessErrbtn = "";
		if(role == "Admin")
			ProcessErrbtn = '<button id="ecnProcessError" class="btn btn-danger" style="float:right;" '+disabled+'>Process Error - <span id="ecrErrorCount">'+errorCount+'</span></button>';
		
		
		
		
		html += '<div class="row" style="margin-bottom:5px;"><div class="col-xs-9 process-error-header"><span style="'+lblMargin+'">'+label+'</span></div><div class="col-xs-3">'+ProcessErrbtn+'</div></div><div class="row"><div class="col-sm-12"><div id="'+type+erp+'" class="tab-pane fade in active"><div class="pagination-data" style="display:none;"><span class="lastPartition"></span><span class="lastRow"></span><span class="nextPartition"></span><span class="nextRow"></span></div><div class="gridContainer"><div class="row"><div class="col-sm-12"><div id="'+type+'-'+erp+'-grid'+'" class="grid-style"></div><div id="'+type+'-'+erp+'-pager'+'" class="pager-style"></div></div></div></div></div></div></div>';
		
		
		html += "<div class='graph_outer' style='margin-top:10px;margin-bottom:10px;'>"+
					"<div class='graph_heading'><span>"+erp+"</span><a class='graphIconClick' href='#'><img src='../img/maximize_icon.png'></a></div>"+
					"<div class='graph_content' style='padding:0px;'>";	
		
		html += "<div class='row'>"+
					"<div class='col-sm-12'>"+
						//"<div class='margin-top-10'>"+
							"<div id='highchartContainer' style='border:1px solid lightgrey;'></div>"+
						//"</div>"+
					"</div>"+
				"</div>";
				
		html += "</div></div>";	*/	
				
			
		
		$('#main1').html(html);	
		
		var id = "main1";
		
		var paginationData = ecnData[erp].pagination;
		
		$('#'+id+' .tab-pane').find('.pagination-data .lastPartition').text(paginationData.lastPartition)
		$('#'+id+' .tab-pane').find('.pagination-data .lastRow').text(paginationData.lastRow)
		$('#'+id+' .tab-pane').find('.pagination-data .nextPartition').text(paginationData.nextPartition)
		$('#'+id+' .tab-pane').find('.pagination-data .nextRow').text(paginationData.nextRow);
		
		if(type == "ecn"){
			buildECNGraph(newGraphData,erpData);
		}
		
	}
	
	function fnGetECNErrorGridHtml(type,erp){
		var html = "";
		
		//html += '<div class="row" style="margin-bottom:5px;"><div class="col-xs-9 process-error-header"><span style="margin-left:15%;">PROCESS ERROR DATA</span></div><div class="col-xs-3"><button id="exportBtn" grid="ecn'+type+'-'+erp+'-grid'+'" class="btn btn-info" style="float:right;display:none;">Export To  Excel</button></div></div><div class="row"><div class="col-sm-12"><div id="ecn'+type+erp+'" class="tab-pane fade in active"><div class="pagination-data" style="display:none;"><span class="lastPartition"></span><span class="lastRow"></span><span class="nextPartition"></span><span class="nextRow"></span></div><div class="gridContainer"><div class="row"><div class="col-sm-12"><div id="ecn'+type+'-'+erp+'-grid'+'" class="grid-style"></div><div id="ecn'+type+'-'+erp+'-pager'+'" class="pager-style"></div></div></div></div></div></div></div>';
		
		/*html += '<div class="tableError">'+
					'<p class="details">ECN Details: </p>'+
					'<div class="row" id="form">'+
						'<div>'+
							'<div class="col-sm-6 form-horizontal">'+
								'<div class="form-group">'+
									'<label for="txtBURegion" class="listview-label control-label col-sm-4 ">ECN Description</label>'+
									'<div class="col-sm-8">'+
										'<input type="text" class="form-control "  id="txtDesc" />'+
									'</div>'+
								'</div>'+
							'</div>'+
							'<div class="col-sm-6 form-horizontal">'+
								'<div class="form-group">'+
									'<label for="txtBURegion" class="listview-label control-label col-sm-5 ">Error Message</label>'+
									'<div class="col-sm-7">'+
										'<input type="text" class="form-control " placeholder="Supplier Id" id="txtErrMsg" />'+
									'</div>'+
								'</div>'+
							'</div>'+
							'<div class="col-sm-12 form-horizontal">'+
								'<div class="form-group">'+
									'<label for="txtBURegion" class="listview-label control-label col-sm-2">XML</label>'+
									'<div class="col-sm-10">'+
										'<textarea rows="4" class="form-control "  id="txtXML"></textarea>'+
									'</div>'+
								'</div>'+
							'</div>'+
							'<div class="col-sm-12 form-horizontal">'+
								'<div class="form-group">'+
									'<div class="col-sm-7">'+
										'<button type="button" class="btn btn-info col-sm-3 pull-right" id="submitECRErrBtn">ReProcess</button>'+
									'</div>'+
								'</div>'+
							'</div>'+
						'</div>'+
					'</div>'+
				'</div>';*/
		html += '<div class="row" style="margin-bottom:5px;"><div class="col-xs-9 process-error-header"><span style="margin-left:15%;">Process Error Data</span></div><div class="col-xs-3"><button id="exportBtn" grid="'+type+'-'+erp+'-grid'+'" class="btn btn-info" style="float:right;display:none;">Export To  Excel</button></div></div><div class="row"><div class="col-sm-12"><div id="'+type+erp+'" class="tab-pane fade in active"><div class="pagination-data" style="display:none;"><span class="lastPartition"></span><span class="lastRow"></span><span class="nextPartition"></span><span class="nextRow"></span></div><div class="gridContainer"><div class="row"><div class="col-sm-12"><div id="'+type+'-'+erp+'-grid'+'" class="grid-style"></div><div id="'+type+'-'+erp+'-pager'+'" class="pager-style"></div></div></div></div></div></div></div>';
		html += '<div class="tableError">'+
		'<p class="details">ECN Details: </p>'+
		'<div class="row" id="form">'+
			'<div>'+
				/*'<div class="col-sm-6 form-horizontal">'+
					'<div class="form-group">'+
						'<label for="txtBURegion" class="listview-label control-label col-sm-4 ">PO #</label>'+
						'<div class="col-sm-8">'+
							'<input type="text" class="form-control " placeholder="PO #" id="txtPoNum" disabled="true"/>'+
						'</div>'+
					'</div>'+
				'</div>'+
				'<div class="col-sm-6 form-horizontal">'+
					'<div class="form-group">'+
						'<label for="txtBURegion" class="listview-label control-label col-sm-5 ">Supplier Id</label>'+
						'<div class="col-sm-7">'+
							'<input type="text" class="form-control " placeholder="Supplier Id" id="txtSupplierId" disabled="true"/>'+
						'</div>'+
					'</div>'+
				'</div>'+*/
				'<div class="col-sm-12 form-horizontal">'+
					'<div class="form-group">'+
						'<label for="txtBURegion" class="listview-label control-label col-sm-2">Change Log</label>'+
						'<div class="col-sm-10">'+
							'<textarea rows="4" class="form-control "  id="txtDescErr"></textarea>'+
						'</div>'+
					'</div>'+
				'</div>'+
				'<div class="col-sm-12 form-horizontal">'+
					'<div class="form-group">'+
						'<div class="col-sm-7">'+
							'<button type="button" class="btn btn-info col-sm-3 pull-right" id="submitECRErrBtn">Submit</button>'+
						'</div>'+
					'</div>'+
				'</div>'+
			'</div>'+
		'</div>'+
	'</div>';		
		return html ;
		
	}
	
	function fnGenerateEcnErrorGridHtml(errorData,grid,type){
		var html = "";
		
		var lblMargin = "margin-left:25%;"
		var label = "ECN ERROR DATA";
		
		var erp = grid.split('-')[1];
		
		html += '<div class="row"><div class="col-sm-12"><div id="ecn'+type+erp+'" class="tab-pane fade in active"><div class="pagination-data" style="display:none;"><span class="lastPartition"></span><span class="lastRow"></span><span class="nextPartition"></span><span class="nextRow"></span></div><div class="gridContainer"><div class="row"><div class="col-sm-12"><div id="ecn'+type+'-'+erp+'-grid'+'" class="grid-style"></div><div id="ecn'+type+'-'+erp+'-pager'+'" class="pager-style"></div></div></div></div></div></div></div>';
		
		$('#main2 .ecn-error-container').html(html);
		
		var id = "main2";
		
		var paginationData = errorData[erp].pagination;
		
		$('#'+id+' .tab-pane').find('.pagination-data .lastPartition').text(paginationData.lastPartition)
		$('#'+id+' .tab-pane').find('.pagination-data .lastRow').text(paginationData.lastRow)
		$('#'+id+' .tab-pane').find('.pagination-data .nextPartition').text(paginationData.nextPartition)
		$('#'+id+' .tab-pane').find('.pagination-data .nextRow').text(paginationData.nextRow);
		
	}

	function buildECNGrids(erp,resultSet){
		
        var columns = commonUtil.getECNGridColumns();
        var options = commonUtil.getDashboardGridOptions();
        
        mainGrid['ecn-'+erp+'-grid'] = Object.create(BuildGrid.prototype);
             
        mainGrid['ecn-'+erp+'-grid'].constructor('#ecn-'+erp+'-grid',resultSet[erp].series,columns,options,'#ecn-'+erp+'-pager');
		
        mainGrid['ecn-'+erp+'-grid'].createGrid();
            
            
        
	}

	$(document).on('click','#ecnProcessError',function(){
		
		commonUtil.handleHideShow('processErrorScreen');
		
		var grid = (($('.nav.nav-second-level.collapse.in a.active').attr('grid')).replace('ecn','ecnerror'));
		var erp = grid.split('-')[1];
		
		fnGenerateEcnErrorGridHtml(errorData,grid,"error");
		buildECNErrorGrids(errorData,erp);
	});

	function buildECNErrorGrids(resultSet,erp){
		var columns = commonUtil.getECNErrorGridColumns();
        var options = commonUtil.getDashboardGridOptions();
        
        mainGrid['ecnerror-'+erp+'-grid'] = Object.create(BuildGrid.prototype);
             
        mainGrid['ecnerror-'+erp+'-grid'].constructor('#ecnerror-'+erp+'-grid',resultSet[erp].series,columns,options,'#ecnerror-'+erp+'-pager');
		
        mainGrid['ecnerror-'+erp+'-grid'].createGrid();
	}
	
	function buildECNGraph(graphData,erpData,container){
		var graphDataObject={},categoryArr=[], categoryData={},errorGraphData=[], processGraphData=[];
            graphDataObject = graphData;
                
            for(var grph in graphDataObject){
                categoryArr.push(grph);
                errorGraphData.push(graphDataObject[grph][1]);
                
                processGraphData.push(graphDataObject[grph][0]);
                 
            }
            categoryData['error'] = errorGraphData;
            
            categoryData['process'] = processGraphData;
        
            var plotData = commonUtil.prepareECNGraphData(categoryData);

			var name = {"x-axis":"","y-axis":"ECN Purchase Orders","title":"Widchill PLM Dashboard"};
			if(container)
				commonUtil.plotGraph(plotData,name,categoryArr,container);
			else
				commonUtil.plotGraph(plotData,name,categoryArr);
		
	}

	$(document).on('click','.plm .autopager', function(e) {
		  e.stopPropagation();
		  var selfEle = $(this);
    	  var activeGrid = $(this).attr('grid');
		  var url = "";
		  var postObj = {};
		  var mainTab = $('.nav.nav-second-level.collapse.in').attr('data-item');
		  var subTab = "";
		  if(mainTab == "ecn"){
			if($('section.active').attr('data-item-type') == "error")
				url = "";          // error ecn url
			else
				url = "";        //ecn url 
		  }  
		  
		  
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
		  serviceObj.callToSever(url,JSON.stringify(postObj),"POST").then(function(result,status,xhr){
				  // // console.log("success");
				  commonUtil.removeLoader();
				  var data = result ;
				  if(mainTab == "ecn"){
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
		  });
	});	
	
	$(document).on('click','#submitECRErrBtn', function(e) {
		var poNumArray = [];
		
		//var activeGrid = $('#main2 .tab-pane.fade.in.active .grid-style').attr('id');
		var activeGrid = ($('.nav.nav-second-level.collapse.in a.active').attr('grid')).replace('ecn','error');
		
		var selectedRows = mainGrid[activeGrid].grid.getSelectedRows();
		
		if(selectedRows.length == 0){
			toastr.error('Select PO to be processed');
			return;
		}
		
		for(var i = 0 ; i < selectedRows.length ;i++){
			var item = mainGrid[activeGrid].grid.getDataItem(selectedRows[i])
			
			poNumArray.push(item.ECNNumber);
		}
		//var comment = $('#txtDescErr').val();
		var comment = $('#txtXML').val(); 
		
		var erp = activeGrid.split('-')[1];	
		// // console.log(poNumArray);
		var userName = $('#username').text();
		var globalId = $('#username').attr('data-global-id');
		var sendObj = JSON.stringify({"erpName":erp,"poNo":poNumArray,"globalId": globalId,"userName": userName,"comment":comment});
		commonUtil.addLoader();
		serviceObj.callToSever("",sendObj,"POST").then(function(){
			commonUtil.removeLoader();  
				// // console.log(result);  
				var successList = result.successList;
				var errorList = result.errorList;
				if(successList.length > 0 || errorList.length > 0){
					
					if(successList.length > 0){
						mainGrid[activeGrid].deleteItems(successList);
						$('#main2 .tab-pane.fade.in.active input[type="checkbox"]').each(function(){
							$(this).attr('checked',false);
						});
						var dashboardGrid = activeGrid.replace('ecnerror','ecn');
						mainGrid[dashboardGrid].updateItems(successList);
						graphData = result.graphData;
						var newGraphData = {};
						newGraphData[erp] = graphData[erp];
						buildECNGraph(newGraphData);
						var errorCount = newGraphData[erp][1];
						$('#ecrErrorCount').text(errorCount);
						//$('#txtDescErr').val('');
						$('#txtXML').val(); 
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
		});
	});
	
	$(document).on('click','.graphIconClick',function(){
		var erp = ($('.nav.nav-second-level.collapse.in li a.active').attr('grid')).split('-')[1];
		
		var type = "";
		
		if($('#main1').hasClass('sc'))
			type = "sc";
		else
			type = "plm";
		
		var html = "";
		
		html += "<div class='graph_outer'>"+
					"<div class='graph_heading'><span>"+erp+"</span></div>"+
					"<div class='graph_content'>";	
		
		html += "<div class='row'>"+
						"<div class='col-sm-12'>"+
							//"<div class='margin-top-10'>"+
								"<div id='modal-highchartContainer' style='height:500px;width:70%;'></div>"+
							//"</div>"+
						"</div>"+
					"</div>";
					
		html += "</div></div>";			
					
		$('#myModal .modal-body').html(html);

		var newGraphData = {};
		newGraphData[erp] = graphData[erp];
		if(type == "sc")
			buildGraph(newGraphData,"","modal-highchartContainer");
		else
			buildECNGraph(newGraphData,"","modal-highchartContainer");
		
		$('#myModal').modal('show');
	})
	
	 function buildECNErrorGrids(resultSet,erp){
        //var mainGrid = {};
        
         //getting the columns and grid options via the commonUtil API
       
        //buildContainer('error',erpData,resultSet);
		if($('#error-'+erp+'-grid').length > 0){
			var columnsErr = commonUtil.getECNErrorGridColumns();
			var optionsErr = commonUtil.getECNProcessErrorGridOptions();
			//for(var i=0;i<erpData.length;i++){
				 mainGrid['error-'+erp+'-grid'] = Object.create(BuildGrid.prototype);
				 
				 mainGrid['error-'+erp+'-grid'].constructor('#error-'+erp+'-grid', resultSet[erp].series,columnsErr,optionsErr,'#error-'+erp+'-pager');
				 mainGrid['error-'+erp+'-grid'].createGrid();
			//}
		}
        
    }
	
	
	
	function fngenerateWIPHtml(){
		var html = "";
		
		html += "<div id='wipContainer'><p><div class=sc-image><img src=../img/wip.jpg alt=wip class=img-responsive /></div></p></span><h3><span>We are currently under construction</span></h3></div>";
				
		$('#main1').html(html);	
	}

    /************************ events on dashboard screen end ****************************/
    
    /****************** exposing API for use from other parts of appilication start *************************/
    return {
        mainGrid : getMainGrid
    }
    /****************** exposing API for use from other parts of appilication end *************************/

})(jQuery);