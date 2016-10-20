define(['app', 'service','sysCode'], function (app) {
	app.controller('userMngCtrl', function (service,$scope,$location,$state,$stateParams) {
		service.post2SRV("queryUserInfo.do", null,function(data,status) {
			console.log(data);
			$scope.userInfoList = data;
		},1000);

		$scope.addUser = function(){
			$state.go("Main.addUser");
		}
		$scope.modifyUser = function(obj){
			$state.go("Main.modifyUser",{"userInfo":obj});
		};
		$scope.init();
	});
});