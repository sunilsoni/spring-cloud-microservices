/*
file: util.js
date: May 26, 2016
file purpose: Methods related to the graph, encapsulated in IIFE. 
server calls: none
*/

/*
* To expose an API for use by other sections of code(to manipulate the graph) via variable graphObj
* @param {Object} jQuery 
* @return {Object} an object which exposes the methods
*/
var graphObj = (function($){
    var chart;
    
    /*
     * To create the graph based on the container id and the given data
     * @param {String} id
     * @param {Array} plotData 
     */
    function createChart(id, plotData,name,categoryArr){
        if(id && id != null && id != undefined){
            $('#'+id).empty();
        }
        
       if(!plotData){
    	   plotData = [{
                    name: 'Processed',
                    data: [0, 0, 0, 0],
                    color: "#90ed7d"

                }, {
                    name: 'In-Transit',
                    data: [0, 0, 0, 0],
                    color: "#7cb5ec"

                }, {
                    name: 'Errored',
                    data: [0, 0, 0, 0],
                    color: "#AA4643"

                }];
        }
        
        var options = {
            chart: {
                type: 'column',
                renderTo : id
            },
            title: {
                text: name.title
            },
            xAxis: {
                categories: categoryArr,
                crosshair: true
				
            },
            yAxis: {
                min: 0,
                tickInterval:1,
                allowDecimals:false,
                title: {
                    text: name["y-axis"]
                }
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                    '<td style="padding:0"><b>{point.y}</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
            },
            credits: {
                   enabled: false
            },
            series: plotData
			
        };
        
        /*var graphCategoriesData = plotData;
        if( typeof graphCategoriesData !='undefined'){
        	options.xAxis.categories = graphCategoriesData.SYMIX;
        	options.series[0] = graphCategoriesData.SYMIX[0];
        	options.series[1] = graphCategoriesData.SYMIX[1];
        	options.series[2] = graphCategoriesData.SYMIX[2];
        	
        }*/
        
        
        
        chart = new Highcharts.Chart(options);
         //$(id).highcharts();
    }
    
    /*
     * To adjust the graph container height
     */
    function adjustGraphHeight(){
        var graphContainer = commonUtil.getGraphContainer();
        if($(document).innerWidth() >= 1100){
            $('#'+graphContainer).height(window.innerHeight - $('#'+graphContainer).offset().top)
        }
        
    }
    
    /*
     * To get the reference to the built graph
     * @return {Object} graph reference 
     */
    function getChartInstance(){
        return chart;
    }
    
    /*
     * To adjust the graph dimention after updating in hidden mode 
     */
    function adjustGraphDimention(){
        var chart = getChartInstance();
        chart.reflow();
    }
    
    return{
       createChart : createChart,
       adjustGraphHeight : adjustGraphHeight,
       getChartInstance : getChartInstance,
       adjustGraphDimention : adjustGraphDimention
    }
    
})(jQuery);