define(['app', 'service', 'sysCode'], function (app) {
    app.controller('userMngCtrl', function (service, $scope, $location, $state, $stateParams) {
        $scope.init = function () {
            service.post2SRV("queryUserInfo.do", null, function (data, status) {
                console.log(data);
                $scope.userInfoList = data;
            }, 1000);
        }
        $scope.addUser = function () {
            $state.go("Main.addUser");
        }
        $scope.modifyUser = function (obj) {
            service.setData(obj);
            $state.go("Main.modifyUser", null);
        };
        $scope.init();
    });
});