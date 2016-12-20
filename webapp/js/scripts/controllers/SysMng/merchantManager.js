define(['app', 'service', 'sysCode'], function (app) {
    "use strict";
    app.controller('merchantMngCtrl', function (service, $scope, $state) {
        $scope.init = function () {
            var formData = {
                "channelId" : service.getUser().channel.channelId,
                "merchantName" : $scope.merchantName
            };
            service.post2SRV("queryMerchant.do", formData, function (data, status) {
                $scope.merchantList = data;
            }, 1000);
        }
        $scope.addMerchant = function () {
            $state.go("Main.addMerchant");
        }
        $scope.gotoDetails = function (obj) {
            service.setData(obj);
            $state.go("Main.merchantDetail");
        }
        $scope.modifyMerchant = function (obj) {
            service.setData(obj);
            $state.go("Main.modifyMerchant");
        };
        $scope.deleteMerchant = function (obj) {

        }
        $scope.init();
    });
});