define(['app', 'service', 'sysCode'], function (app) {
    app.controller('userMngCtrl', function (service, $scope, $location, $state, $stateParams) {
        $scope.init = function () {
            $scope.loginUserSeq = service.getUser().userSeq;
            $scope.channelId = service.getUser().channel.channelId;
            if ($scope.channelId != null && $scope.channelId == 'tfdor') {
                $scope.channelId = '';
            }
            var formData = {
                "channelId" : $scope.channelId
            };
            service.post2SRV("queryUserInfo.do", formData, function (data, status) {
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