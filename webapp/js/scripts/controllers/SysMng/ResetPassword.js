define(['app', 'service', 'sysCode'], function (app) {
    "use strict";
    app.controller('restPasdCtrl', function (service, $scope, $state) {
        $scope.doIt = function () {
            if ($scope.password == undefined || $scope.password == '') {
                showError("错误提示：请输入新密码");
                return;
            };
            if ($scope.confirmPassword == undefined || $scope.confirmPassword == '') {
                showError("错误提示：请输入确认密码");
                return;
            };
            if ($scope.password != $scope.confirmPassword) {
                showError("错误提示：两次密码不一致");
                return;
            };
            service.post2SRV("getPublicKey.do", null,function(data,status) {
                setMaxDigits(131);
                var key = new RSAKeyPair(data.exponent,"", data.modulus);
                var enpassword = data.random + $scope.password;
                var password = encryptedString(key,enpassword);
                var formData = {
                    "password": password
                };
                service.post2SRV("resetLoginPasd.do", formData, function (data, status) {
                    service.setUser(data);
                    $state.go("Main");
                }, 4000);
            },4000);
        }
    });
});
