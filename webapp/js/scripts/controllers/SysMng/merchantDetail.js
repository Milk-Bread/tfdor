/**
 * Created by chepeiqing on 16/10/13.
 */
define(['app', 'service','sysCode'], function (app) {
    app.controller('merchantDetailCtrl', function (service, $scope, $state) {
        $scope.merchantInfo = service.getData();
        $scope.modifyMerchant = function (obj) {
            service.setData(obj);
            $state.go("Main.modifyMerchant");
        }
    });
});