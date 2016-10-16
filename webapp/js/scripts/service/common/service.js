define([ 'app'], function(app) {
	app.service('service',function($http,cfpLoadingBar,$state){
		this.getUser = function(){
			return angular.fromJson(sessionStorage.getItem("_USER"));
		};
		this.setUser = function(user){
			sessionStorage.setItem("_USER",angular.toJson(user));
		};
		this.post2SRV = function(action, formData, callBack, timeOut){
			if(formData == null || formData == ''){
				formData = {};
			}
			formData["actionName"] = action;
            transFn = function(formData) {
            	return $.param(formData);
            },
            postCfg = {
                headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
                transformRequest: transFn
            };
			cfpLoadingBar.start();
	        $http.post(
	        	action,
	        	formData,
	        	postCfg
	        ).success(function(data,header,config,status){
				cfpLoadingBar.complete();
		    	if(data._exceptionCode != null || data._exceptionCode == 'false'){
		    		showError("错误提示",""+data._exceptionMsg);
					if(data._exceptionCode == "please.log.in.again"){
						$state.go("Login");
					}
	        	}else{
	        		callBack(data,header,config,status);
	        	}
		    }).error(function(data,header,config,status){
				cfpLoadingBar.complete();
		    	var errorStr = "网络错误";
	        	if(data._exceptionMsg != null && data._exceptionMsg != undefined && data._exceptionMsg != ''){
	        		errorStr = data._exceptionMsg;
	        	}
	        	showError("错误提示",errorStr+action);
				if(data._exceptionCode == "please.log.in.again"){
					$state.go("Login");
				}
		    });
		};
		/** 统一页面跳转方法 **/
		this.skipPage = function (url){
			if(window.XMLHttpRequest){
		        XMLHttpR = new XMLHttpRequest();
		    }else if(window.ActiveXObject){
		        try{
		            XMLHttpR = new ActiveXObject("Msxml2.XMLHTTP");
		        }catch(e){
		            try{
		                XMLHttpR = new ActiveXObject("Microsoft.XMLHTTP");
		            }catch(e){
		            }
		        }
		    }
		    XMLHttpR.open("GET",url,true);
		    XMLHttpR.setRequestHeader("Content-Type","text/html;charset=utf-8");
		    XMLHttpR.onreadystatechange = function(){
			    if(XMLHttpR.readyState ==4 && XMLHttpR.status == 200){
			        document.body.innerHTML = XMLHttpR.responseText;
			    }
		    };
		    XMLHttpR.send(null);
		};
	});
});
var time;
var showError = function(title,intro){
	clearTimeout(time);
	$("#popTitle a").html(title);
	$("#popIntro").html(intro);
	$('#pop').slideDown(1000);
	time = setTimeout(function(){
		$('#pop').fadeOut(400);
	},5000);
};
function closePop(){
	$('#pop').fadeOut(500);
}