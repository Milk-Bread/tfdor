define(['app', 'service','sysCode','encryption'], function (app) {
	"use strict";
	app.controller('LoginCtrl', function (encryption,service,$scope,$state) {
    	$scope.doIt = function(){
    		var userId = $scope.userId;
    		if(userId == undefined || userId == ''){
    			showError("用户名错误,请输入你的用户名");
    			return;
    		}

			if($scope.password == undefined || $scope.password == ''){
				showError("密码错误,请输入你的密码");
				return;
			}
			service.post2SRV("getPublicKey.do", null,function(data,status) {
				setMaxDigits(131);
				var key = new RSAKeyPair(data.exponent,"", data.modulus);
				var enpassword = data.random + $scope.password;
				var password = encryptedString(key,enpassword);
				var formData = {
					"userId" : userId,
					"password" : password
				};
				service.post2SRV("login.do", formData,function(data,status) {
					service.setUser(data);
					$state.go("Main");
				},4000);
			},4000);
    	};
    });
});
