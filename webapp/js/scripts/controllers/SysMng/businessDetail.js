/**
 * Created by chepeiqing on 16/10/13.
 */
define(['app', 'service','sysCode'], function (app) {
    app.controller('businessDetailCtrl', function (service, $scope, $location, $state, $stateParams, $rootScope) {
        $scope.businessInfo = service.getData();
        $scope.modifyBusiness = function (obj) {
            service.setData(obj);
            $state.go("Main.modifyBusiness");
        }
        $scope.init();
    });
});