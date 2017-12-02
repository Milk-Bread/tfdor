/**
 * Created by chepeiqing on 16/10/13.
 */
define(['app', 'service','sysCode'], function (app) {
    "use strict";
    app.controller('merchantDetailCtrl', function (service, $scope, $state) {
        $scope.merchantInfo = service.getDataMap();
        $scope.modifyMerchant = function (obj) {
            service.setDataMap(obj);
            $state.go("Main.modifyMerchant");
        }
    });
});
