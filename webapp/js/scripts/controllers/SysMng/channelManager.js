define(['app', 'service', 'sysCode'], function (app) {
    app.controller('channelMngCtrl', function (service, $scope, $location, $state, $stateParams) {
        $scope.init = function () {
            service.post2SRV("queryChannel.do", null, function (data, status) {
                console.log(data);
                $scope.channelInfoList = data;
            }, 1000);
        }
        $scope.addChannel = function () {
            $state.go("Main.addChannel");
        }
        $scope.modifyChannel = function (obj) {
            service.setData(obj);
            $state.go("Main.modifyChannel", null);
        };
        $scope.deleteChannel = function (obj) {
            service.setData(obj);
            $state.go("Main.deleteChannel", null);

        }
        $scope.deleteChannel = function (obj) {
            service.setData(obj);
            $state.go("Main.deleteChannel", null);

        }

        $scope.deleteUser = function (obj) {
            service.setData(obj);
            $state.go("Main.deleteUser", null);
        };
        $scope.init();
    });
});