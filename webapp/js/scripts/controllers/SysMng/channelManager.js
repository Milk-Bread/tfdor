define(['app', 'service', 'sysCode'], function (app) {
    "use strict";
    app.controller('channelMngCtrl', function (service, $scope, $state) {
        $scope.init = function () {
            $scope.channelId = service.getUser().channel.channelId;
            var formData = {
                "channelId" : $scope.channelId,
                "channelName": $scope.channelName
            };
            service.post2SRV("queryChannel.do", formData, function (data, status) {
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
        $scope.deleteUser = function (obj) {
            service.setData(obj);
            $state.go("Main.deleteUser", null);
        };
        $scope.init();
    });
});