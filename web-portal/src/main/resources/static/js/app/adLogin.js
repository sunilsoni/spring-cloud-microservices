var loginDashboard = (function($) {
	var getData = '';
	var access_token = '';
	var id_token = '';
	var refresh_token = '';
	$(document).ready(function() {

		$.ajax({
			type : 'GET',
			url : 'http://13.68.114.134:8765/web-portal/.auth/me',
			success : function(data) {
				getData = data[0]["user_claims"][10]["val"];
				access_token = data[0]["access_token"];
				id_token = data[0]["id_token"];
				refresh_token = data[0]["refresh_token"];
				$('#username').text(getData);
				$('.username-msg').show();
				$('.logout').show();

				console.log("data", data);
			}
		});
	});
	$(document)
			.on(
					'click',
					'#logout',
					function(e) {
						refreshTokens();
						// console.log('log out called');
						$.ajax({
							type : 'GET',
							url : '/logout',
							crossDomain : true,
							headers : {
								'cache-control' : 'no-cache',
								'Access-Control-Allow-Origin' : '*',
							},
							success : function(data) {

							},
							error : (function(data) {
								console.log("application logs for details.");
							})
						});
						deleteAllCookies(access_token, id_token, refresh_token);
						console.log("accessToekn value----->", access_token);
						console.log("id_token value ---->", id_token);
						console.log("refresh_token value ------->",
								refresh_token);
						window.location.href = "https://login.microsoftonline.com/common/oauth2/logout?post_logout_redirect_uri";

						console.log("accessToekn value---1-->", access_token);
						console.log("id_token value --2-->", id_token);
						console.log("refresh_token value ----3--->",
								refresh_token);
					});
	function deleteAllCookies(access_token, id_token, refresh_token) {
		var cookies = document.cookie.split(";");

		for (var i = 0; i < cookies.length; i++) {
			var cookie = cookies[i];
			var eqPos = cookie.indexOf("=");
			var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
			document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT";
		}
		access_token = undefined;
		id_token = undefined;
		refresh_token = undefined;

	}

	function refreshTokens() {
		var refreshUrl = "http://13.68.114.134:8765/web-portal/.auth/refresh";
		$
				.ajax(refreshUrl)
				.done(function() {
					console.log("Token refresh completed successfully.");
				})
				.fail(
						function() {
							console
									.log("Token refresh failed. See application logs for details.");
						});
	}
})(jQuery);
/*
 * var loginDashboard = (function($){ var getData=''; var access_token=''; var
 * id_token=''; var refresh_token='';
 * 
 * function ajaxCall(){ $.ajax({                 type: 'GET',
 *                 url:'https://supplier-collaboration-portal.azurewebsites.net/.auth/me',
 *                 success: function(data) { getData =
 * data[0]["user_claims"][10]["val"]; access_token = data[0]["access_token"];
 * id_token = data[0]["id_token"]; refresh_token = data[0]["refresh_token"];
 * $('#username').text(getData); $('.username-msg').show(); $('.logout').show();
 *                 }            }); } ajaxCall();
 * 
 * $(document).on('click','#logout', function(e) { //refreshTokens(); //
 * console.log('log out called'); $.ajax({                 type: 'POST',
 *                 url:'/logout', //dataType: 'jsonp', data: { 'access_token' :
 * access_token, 'refresh_token' : refresh_token, }, crossDomain : true,
 * headers: { 'Accept': 'application/json', 'Content-Type': 'application/json',
 * 'cache-control': 'no-cache', 'Access-Control-Allow-Origin': '*', },
 * success: function(data) { console.log("See data--->",data);
 * },error:(function(data){ console.log("See error"); }) });
 * //deleteAllCookies(access_token,id_token,refresh_token); deleteAllCookies();
 * 
 * access_token = undefined; id_token = undefined; refresh_token = undefined;
 * 
 * console.log("accessToekn value----->",access_token); console.log("id_token
 * value ---->",id_token); console.log("refresh_token value
 * ------->",refresh_token); //window.location.href =
 * "https://login.microsoftonline.com/common/oauth2/logout?post_logout_redirect_uri=https://supplier-collaboration-portal.azurewebsites.net/";
 * console.log("accessToekn value----->",access_token); console.log("id_token
 * value ---->",id_token); console.log("refresh_token value
 * ------->",refresh_token); }); //function
 * deleteAllCookies(access_token,id_token,refresh_token) { function
 * deleteAllCookies() { var cookies = document.cookie.split(";");
 * 
 * for (var i = 0; i < cookies.length; i++) { var cookie = cookies[i]; var eqPos =
 * cookie.indexOf("="); var name = eqPos > -1 ? cookie.substr(0, eqPos) :
 * cookie; document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT"; }
 *  }
 * 
 * 
 * 
 * 
 * function refreshTokens() { var refreshUrl =
 * "https://graph.windows.net/supplier-collaboration-portal.azurewebsites.net/.auth/refresh";
 * $.ajax(refreshUrl).done(function() { console.log("Token refresh completed
 * successfully."); }).fail(function() { console.log("Token refresh failed. See
 * application logs for details."); }); } })(jQuery);
 */

