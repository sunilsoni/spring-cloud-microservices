function BuildGrid(gridId, gridData, columnDefinition,gridOptions, paginationId,graphData){
    this.gridId = gridId;
    this.gridData = gridData;
    this.gridOptions = gridOptions;
    this.columnDefinition = columnDefinition;
    this.paginationId = paginationId;
    this.grid;
    this.dataView;
	this.graphData=graphData;
}

BuildGrid.prototype.createGrid = function(){
    
    var grid, dataView, gridOptions = this.gridOptions, gridId = this.gridId, paginationId = this.paginationId, gridData = this.gridData, columnDefinition = this.columnDefinition;
    
    this.dataView = new Slick.Data.DataView();
    this.grid = new Slick.Grid(gridId, this.dataView, columnDefinition, gridOptions);
	 grid = this.grid, dataView = this.dataView;
    var pager = new Slick.Controls.Pager(dataView, grid, $(paginationId));
    var columnpicker = new Slick.Controls.ColumnPicker(columnDefinition, grid, gridOptions);
    
    var sortcol = "";
    var sortdir = 1;
    
    function comparer(a, b) {
      var x = a[sortcol], y = b[sortcol];
      return (x == y ? 0 : (x > y ? 1 : -1));
    }


	  
	  grid.onKeyDown.subscribe(function (e) {
		// select all rows on ctrl-a
		if (e.which != 65 || !e.ctrlKey) {
		  return false;
		}
		var rows = [];
		for (var i = 0; i < dataView.getLength(); i++) {
		  rows.push(i);
		}
		grid.setSelectedRows(rows);
		e.preventDefault();
	  });
    
      grid.onSort.subscribe(function (e, args) {
        sortdir = args.sortAsc ? 1 : -1;
        sortcol = args.sortCol.field;

        
        dataView.sort(comparer, args.sortAsc);
        
      });

	  // wire up model events to drive the gridErr
	  dataView.onRowCountChanged.subscribe(function (e, args) {
		grid.updateRowCount();
		grid.render();
	  });
	  dataView.onRowsChanged.subscribe(function (e, args) {
		grid.invalidateRows(args.rows);
		grid.render();
	  });
	  dataView.onPagingInfoChanged.subscribe(function (e, pagingInfo) {
		var isLastPage = pagingInfo.pageNum == pagingInfo.totalPages - 1;
		var enableAddRow = isLastPage || pagingInfo.pageSize == 0;
		var options = grid.getOptions();
		if (options.enableAddRow != enableAddRow) {
		  grid.setOptions({enableAddRow: enableAddRow});
		}
	  });

      if(gridData && gridData.length > 0){
          this.prepareData();
      }
      
	  
	  //dataView.beginUpdate();

	  //dataView.endUpdate();
	  // if you don't want the items that are not visible (due to being filtered out
	  // or being on a different page) to stay selected, pass 'false' to the second arg
	  //dataView.syncGridSelection(grid, true);
	  //$("#gridContainer").resizable();
	  //END

	  // initialize the model after all the events have been hooked up
	  dataView.beginUpdate()
	  dataView.setItems(gridData);
      dataView.setFilter(filter);
	  dataView.setPagingOptions({pageSize: 10}); 
	  dataView.endUpdate();
    
      var filterPlugin = new Ext.Plugins.HeaderFilter({});

            // This event is fired when a filter is selected
            filterPlugin.onFilterApplied.subscribe(function () {
                dataView.refresh();
                grid.resetActiveCell();

                // Excel like status bar at the bottom
                var status;

                if (dataView.getLength() === dataView.getItems().length) {
                    status = "";
                } else {
                    status = dataView.getLength() + ' OF ' + dataView.getItems().length + ' RECORDS FOUND';
                }
                $('#status-label').text(status);
            });

            // Event fired when a menu option is selected
            filterPlugin.onCommand.subscribe(function (e, args) {
                dataView.fastSort(args.column.field, args.command === "sort-asc");
            });

            grid.registerPlugin(filterPlugin);

            grid.init();

            // Filter the data (using userscore's _.contains)
            function filter(item) {
                var columns = grid.getColumns();

                var value = true;

                for (var i = 0; i < columns.length; i++) {
                    var col = columns[i];
                    var filterValues = col.filterValues;

                    if (filterValues && filterValues.length > 0) {
                        value = value & _.contains(filterValues, item[col.field]);
                    }
                }
                return value;
            }
	  
    
}



BuildGrid.prototype.prepareData = function(){
    
    var gridData = this.gridData;
    console.log("gridData val is====="+gridData);
    
    var errorCount = 0,inTransitCount = 0, processedCount = 0;
	
    for(var i = 0; i< gridData.length; i++){
          gridData[i]["id"] = i;
          var statusCode = gridData[i]['Status'], dataSourceCode = gridData[i]['SourceErpName']
          if(statusCode === "1"){
              gridData[i]['Status'] = "In-transit";
              gridData[i]['statusColor'] = 'blue';
              inTransitCount++;
          }
          else if(statusCode === "2"){
              gridData[i]['Status'] = "Transaction Completed";
              gridData[i]['statusColor'] = 'green';
              processedCount++;
          }
          else if(statusCode === "3"){
              gridData[i]['Status'] = "Error in Process";
              gridData[i]['statusColor'] = 'red';
              errorCount++;
          }
        
          if(dataSourceCode === "1"){
              gridData[i]['SourceErpName'] = "Symix";
          }
          else if(dataSourceCode === 2){
              gridData[i]['SourceErpName'] = "SAP";
          }
          else if(dataSourceCode === 3){
              gridData[i]['SourceErpName'] = "Oracle";
          }
    }
    
    this.errorCount = errorCount;
    this.processedCount = processedCount;
    this.inTransitCount = inTransitCount;
    
};

BuildGrid.prototype.updateRecords = function(obj){
    var dataView = this.dataView, dataItems = dataView.getItems();
    var errorCount = 0,inTransitCount = 0, processedCount = 0;
    for(var i=0,l=dataItems.length; i < l; i++){
        if(obj[dataItems[i]["poNum"]] && obj[dataItems[i]["poNum"]] != null && obj[dataItems[i]["poNum"]] != undefined){
            var statusCode = parseInt(obj[dataItems[i]["poNum"]])
            dataItems[i]["status"] = statusCode;
            dataItems[i]['statusVal'] = commonUtil.getStatusText(statusCode);
            dataView.updateItem(dataItems[i].id,dataItems[i]);
        }
        
        var statusCode = dataItems[i]['status'];
        if(statusCode == 1){
           inTransitCount++;
        }
        else if(statusCode == 2){
           processedCount++;
        }
        else if(statusCode == 3){
           errorCount++;
        }
    }
    
    this.errorCount = errorCount;
    this.processedCount = processedCount;
    this.inTransitCount = inTransitCount;
    
};

BuildGrid.prototype.deleteRecords = function(obj){
    var dataView = this.dataView, dataItems = dataView.getItems();
    
    for(var i=0,l=dataItems.length; i < l; i++){
        if(obj[dataItems[i]["poNum"]] && obj[dataItems[i]["poNum"]] != null && obj[dataItems[i]["poNum"]] != undefined){
            dataView.deleteItem(dataItems[i].id);
            l--;
        }
    }
    
};

BuildGrid.prototype.updateGrid = function(obj){
    var dataView = this.dataView;
    dataView.setItems(obj);
};


//function 

function highlightRow(key, rowNumber,cssClass,count) {
  //removeRowHighlight(key) //remove any previous css
  
  row = rowNumber.toString();
  var obj = {
    poId: cssClass,
    poNum:cssClass,
    poDesc:cssClass,
    dataSourceName:cssClass,
    statusVal:cssClass
  };
  var rowObj = {};
  rowObj[row] = obj;
  //var errorHighlight;

  grid.setCellCssStyles(key+""+count, rowObj);
};

function removeRowHighlight(key) {
  grid.removeCellCssStyles(key);
}


/* cell formatters */
function checkBoxFormatter(row, cell, value, columnDef, dataContext) {
  //debugger;
    var value = dataContext["poId"];//initially it was:-  var value = dataContext["poId"];
    var poNumber = dataContext["OrderNumber"]
    if (dataContext["Status"] === "In-transit") {
        return "<input type='checkbox' class='checkbox-button' record-id='" + dataContext["id"] + "'/>";
    }else if(dataContext["Status"] === "Transaction Completed" || dataContext["Status"] === "Error in Process"){
        return "<input type='checkbox' class='checkbox-button' record-id='" + dataContext["id"] + "' disabled/>";
    }
};


function checkBoxFormatterErr(row, cell, value, columnDef, dataContext) {
   return "<input type='checkbox' class='checkbox-button checkbox-err' record-id='"+dataContext['id']+"'>";
};