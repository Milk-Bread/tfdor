define(['app', 'service','sysCode'], function (app) {
	app.controller('roleMngCtrl', function (service,$scope,$state) {
		$scope.isShow = false;
	    $scope.init = function(){
			$scope.channelId = service.getUser().channel.channelId;
			if ($scope.channelId != null && $scope.channelId == 'tfdor') {
				$scope.isShow = true;
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
		$scope.modifyRole = function(obj){
			service.setData(obj);
			$state.go("Main.modifyRole");
		};

		$scope.deleteRole = function(obj){
			alert(obj);
			var formData = {
				"roleSeq": obj
			}
			service.post2SRV("queryRoleUserInfo.do", formData, function (data, status) {
			}, 	5000);
		};

		$scope.init();
	});
});