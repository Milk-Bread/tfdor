define(['app', 'service', 'sysCode'], function (app) {
    app.controller('businessMngCtrl', function (service, $scope, $location, $state, $stateParams) {
        $scope.init = function () {
            var formData = {
                "channelId" : service.getUser().channel.channelId
            };
            service.post2SRV("queryBusiness.do", formData, function (data, status) {
                console.log(data);
                $scope.businessList = data;
            }, 1000);
        }
        $scope.addBusiness = function () {
            $state.go("Main.addBusiness");
        }
        $scope.gotoDetails = function () {

        }
        $scope.modifyBusiness = function (obj) {
            service.setData(obj);
        };
        $scope.deleteBusiness = function (obj) {

        }
        $scope.init();
    });
});