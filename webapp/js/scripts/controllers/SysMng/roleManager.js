define(['app', 'service','sysCode'], function (app) {
	app.controller('roleMngCtrl', function (service,$scope,$location,$state,$stateParams,$rootScope) {
		$scope.isShow = false;
	    $scope.init = function(){
			$scope.channelId = service.getUser().channel.channelId;
			if ($scope.channelId != null && $scope.channelId == 'tfdor') {
				$scope.isShow = true;
				$scope.channelId = '';
			}
			var formData = {
				"roleName" : $scope.roleName,
				"channelId" : $scope.channelId
			};
			service.post2SRV("queryRole.do", formData,function(data,status) {
				$scope.roleList = data;
			},4000);
	    };
		$scope.addRole = function(){
			$state.go("Main.addRole");
		};
		$scope.modifyRole = function(obj,name){
			service.setData({"roleSeq":obj,"roleName":name});
			$state.go("Main.modifyRole");
		};
		$scope.init();
	});
});