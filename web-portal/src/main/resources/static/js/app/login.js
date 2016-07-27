function onSubmit(e){
	e.preventDefault();
	var userName = $("#userName");
	var password = $("#password");
	if(userName.val() === ''){
		toastr.error('Please enter Username');
		return;
	}
	
	if(password.val() === ''){
		toastr.error('Please enter Password');
		return;
	}
	
	if($.inArray(userName.val(), userArr) === -1){
		toastr.error('Please enter the correct Username');
		return;
	}
	
	if($.inArray(userName.val(), userArr) !== -1){
			if(password.val() === "jcipoc"){
				onLogin(userName.val());
			}
			else{
				toastr.error('Please enter the correct Password');
			}
	}
}


$("#submitBtn").click(onSubmit);

$('.loginEnter').on('keypress', function(e){
	if(e.which == 13){
		onSubmit(e);
	}
});


function setLabel(userName){
	var userArr = ['sunil','ajay','todd','bharathan'];
	var roleObj = {'sunil':"IT Analyst",'ajay':"IT Analyst",'todd':"Bus Relationship Sr Mgr IT GISC HQ IT",'bharathan':"Sr Solution Architect Info Technology Architecture"};
	$("#roleLabel").text(roleObj[userName]);
	var userStr = userName.charAt(0).toUpperCase() + userName.slice(1);
	$("#userLabel").text(userStr);
	
}

function successLogin(){
	$("#loginContent").css("display","none");
	$("#main1,.headerDiv,#logOff").css("display","block");
	//createHighcharts();
    graphObj.createChart('#highchartContainer');
}


function onLogin(username, signOut){
	var isLogin, refresh = true;
	if(username){
		isLogin = true;
	}
	else{
		username = null;
		isLogin = false
	}
	if(signOut){
		refresh = false;
		isLogin = false;
	}
	var objLogin = {refresh: refresh, login:isLogin,username:username};
	jQuery.ajax({
    headers: { 
        'Accept': 'application/json',
        'Content-Type': 'application/json' 
    },
    'type': 'POST',
    'url': 'isLogin',
    'data': JSON.stringify(objLogin),
    'dataType': 'json',
    'success': function(data){
		if(data.login){
			setLabel(data.username);
			successLogin();
		}
		else{
			if(!signOut){
				$("#loginContent").css("display","block");
			}
			
		}
		
		if(signOut){
			location.reload();
		}
	 }
	});
	
}

$("#logOff").click(function(e){
	e.preventDefault();
	onLogin($("#userLabel").text().toLowerCase(),true);
	
});

onLogin();
