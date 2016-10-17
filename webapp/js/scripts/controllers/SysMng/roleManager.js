define(['app', 'service'], function (app) {
	app.controller('roleMngCtrl', function (service,$scope,$location,$state,$stateParams,$rootScope) {
	    $scope.init = function(){
			var formData = {
				"roleName" : $scope.roleName
			};
			service.post2SRV("queryRole.do", formData,function(data,status) {
				$scope.roleList = data;
			},4000);
	    };
		$scope.addRole = function(){
			$state.go("Main.addRole");
		};
		$scope.modifyRole = function(obj,name){
			$state.go("Main.modifyRole",{"roleSeq":obj,"roleName":name});
		};
		$scope.init();
	});
});