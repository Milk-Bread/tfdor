define(['app', 'service', 'sysCode'], function (app) {
    app.controller('merchantMngCtrl', function (service, $scope, $location, $state, $stateParams) {
        $scope.init = function () {
            var formData = {
                "channelId" : service.getUser().channel.channelId
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