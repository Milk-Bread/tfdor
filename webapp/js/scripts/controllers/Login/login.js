define(['app', 'service'], function (app) {
	app.controller('LoginCtrl', function (service,$scope,$location,$state,$rootScope) {
    	$scope.doIt = function(){
    		var userId = $scope.userId;
    		if(userId == undefined || userId == ''){
    			showError("用户名错误","请输入你的用户名");
    			return;
    		}
    		var password = $scope.password;
    		if(password == undefined || password == ''){
    			showError("密码错误","请输入你的密码");
    			return;
    		}
    		var formData = {
    			"userId" : userId,
    			"password" : password
    		};
    		service.post2SRV("login.do", formData,function(data,status) {
				service.setUser(data);
    			$state.go("Main");
        	},4000);
    	};
    });
});
