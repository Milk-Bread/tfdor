/**
 * Created by chepeiqing on 16/10/13.
 */
define(['app', 'service','sysCode'], function (app) {
    "use strict";
    app.controller('merchantDetailCtrl', function (service, $scope, $state) {
        $scope.merchantInfo = service.getSessionDate();
        $scope.modifyMerchant = function (obj) {
            service.setSessionDate(obj);
            $state.go("Main.modifyMerchant");
        }
    });
});
