/*
file: service.js
date: May 27, 2016
file purpose: set of functions for making server calls
server calls: pullPoData,processPoData
*/

/*
* To expose an API for use by other sections of code via variable serviceObj
* @param {Object} jQuery 
* @return {Object} an object which exposes the methods
*/
var serviceObj = (function($){
    
    //settings
    var settings = {
        async: true,
        crossDomain: true,
        url: '',
        method: '',
        headers: {
            'content-type': "application/json",
            'cache-control': "no-cache"
        },
        processData: false,
        data: '',
        'error': function(data) {
            console.log(data.statusText);
            toastr.error('Server Error');
            commonUtil.removeLoader();
        }
    }; 
    //settings
    
    /*
     * To provide the data necessary for making a service call to pullPoData
     * @param {Object} data 
     * @return {Object} promise(jQuery Promise Object)
     */
    
    /*Kush Code*/
    /*function pullPoData(data){
        var promise = callToSever('pullPoData', data, 'POST');
        return promise;
    }*/
    
    function pullPoData(data,type){
		console.log(JSON.stringify(data));
        var promise = "";
		if(type && type == "ecn")
			promise = callToSever('../ecnlocal.json', data, 'GET');
		else
			promise = callToSever('api/po/getSegmentedPoDetails', data, 'POST');
		
		//var promise = callToSever('../local.json', data, 'GET');
        return promise;
    }
    
    
    /*
     * To provide the data necessary for making a service call to processPoData
     * @param {Object} data 
     * @return {Object} promise(jQuery Promise Object)
     */
    function processPoData(data){
        var promise = callToSever('processPoData', data, 'POST');
        return promise;
    }
    
    /*
     * To make the service call to processPoData/pullPoData
     * @param {String} url 
     * @param {Object} data 
     * @param {String} method 
     * @return {Object} promise(jQuery Promise Object)
     */
    function callToSever(url, data, method){
        
        settings['url'] = url;
        settings['data'] = data;
        settings['method'] = method;
		//settings['type'] = 'json';//Sunil:remove
        
        return $.when( $.ajax(settings));
    }
    
    return{
       pullPoData : pullPoData,
       processPoData : processPoData,
	   callToSever:callToSever
    }

})(jQuery);