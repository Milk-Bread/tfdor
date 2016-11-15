define(['app', 'service', 'sysCode'], function (app) {
    app.controller('restPasdCtrl', function (service, $scope, $location, $state, $stateParams) {
        $scope.doIt = function() {
            var password = $scope.password;
            if (password == undefined || password == '') {
                showError("错误提示：请输入新密码");
                return;
            };
            var confirmPassword = $scope.confirmPassword;
            if (confirmPassword == undefined || confirmPassword == '') {
                showError("错误提示：请输入确认密码");
                return;
            };
            if(password != confirmPassword){
                showError("错误提示：两次密码不一致");
                return;
            };
            var formData = {
                "password": password,
                "confirmPassword": confirmPassword
            };
            service.post2SRV("resetLoginPasd.do", formData, function (data, status) {
                service.setUser(data);
                $state.go("Main");
            }, 4000);
        }
    });
});