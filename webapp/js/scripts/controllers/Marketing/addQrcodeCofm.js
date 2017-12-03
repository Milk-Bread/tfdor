/**
 * Created by chepeiqing on 16/10/13.
 */
define(['app', 'service', 'sysCode'], function (app) {
    "use strict";
    app.controller('addQrcodeCofmCtrl', function (service, $scope, $state) {
        $scope.init = function () {
            $scope.formData =  service.getSessionDate();
        };

        $scope.doIt = function () {
            var formData = service.getSessionDate();
            service.post2SRV("createQrcodeImg.do", formData, function (data, status) {
                $state.go("Main.QrCodeManager");
            }, 4000);
        }
        $scope.init();
    });
});
