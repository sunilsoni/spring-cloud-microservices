var loginDashboard = (function($){
	$(document).ready(function(){
		var getData='';
		$.ajax({
	                type: 'GET',
	                url:'https://supplier-collaboration-portal.azurewebsites.net/.auth/me',
	                success: function(data) {
						getData=data[0]["user_id"];
						var atIdx = getData.indexOf("@");
						getData = getData.substring(0,atIdx);
						$('#username').text(getData);
	        			//$('#username').attr('data-global-id',data.userData.GlobalId);
	        			$('.username-msg').show();
	        			$('.logout').show();
	        			//console.log('success data---->',response);
						//console.log('access_token--->',data[0]["access_token"]);
					             /* $.ajax({                    
					                type: "GET",
					                url:'https://graph.microsoft.com/v1.0/me',
					    	        headers: {
					    	            'Authorization':"Bearer "+getData
					    	        },
					                success: function(response){
					                	
					        			$('#username').text(response.displayName);
					        			//$('#username').attr('data-global-id',data.userData.GlobalId);
					        			$('.username-msg').show();
					        			$('.logout').show();
					        			console.log('success data---->',response);		        			
					        			 
					                }
					
					});  */
	                    //console.log(this.accepts);
						//console.log('data---->',data);
						
                }
           }); 

				/* function refreshTokens() {
					  $.ajax({
			                type: 'GET',
			                url:'https://graph.windows.net/supplier-collaboration-portal.azurewebsites.net/.auth/refresh',
			                success: function(response) {
								console.log("refresh response: "+response);
							}
					  });
					}
				
				refreshTokens(); */

	});	
	$(document).on('click','#logout', function(e) {
		console.log('log out called');
			$.ajax({
	                type: 'GET',
	                url:'/logout',
					dataType: 'jsonp',
					crossDomain : true,
					headers: {
			            'cache-control': 'no-cache',
			            'Access-Control-Allow-Origin': '*'
			        },
					success: function(data) {
						console.log('logout data--->',data)
					}
			}); 
		}); 
		
})(jQuery);