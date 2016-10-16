define(['app', 'service'], function (app) {
	app.controller('userMngCtrl', function (service,$scope,$location,$state,$stateParams) {
		$scope.userName = '用户管理';
	    $scope.doIt = function(){
	    	$state.go("Main.RoleManager");
	    };
	});
});