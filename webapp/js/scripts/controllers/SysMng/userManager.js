define(['app', 'service', 'sysCode'], function (app) {
    "use strict";
    app.controller('userMngCtrl', function (service, $scope, $state) {
        $scope.init = function () {
            $scope.loginUserSeq = service.getUser().userSeq;
            $scope.channelId = service.getUser().channel.channelId;
            var formData = {
                "channelId": $scope.channelId,
                "userName": $scope.userName
            };
            service.post2SRV("queryUserInfo.do", formData, function (data, status) {
                $scope.userInfoList = data;
            }, 1000);
        }
        $scope.addUser = function () {
            $state.go("Main.addUser");
        }
        $scope.modifyUser = function (obj) {
            service.setSessionDate(obj);
            $state.go("Main.modifyUser", null);
        };

        $scope.deleteChannel = function (obj) {
            service.setSessionDate(obj);
            $state.go("Main.deleteChannel", null);
        };

        $scope.deleteUser = function (obj) {
            service.setSessionDate(obj);
            $state.go("Main.deleteUser", null);
        }

        $scope.init();
    });
});
