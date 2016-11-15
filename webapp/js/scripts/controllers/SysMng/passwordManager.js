define(['app', 'service', 'sysCode'], function (app) {
    app.controller('passwordMngCtrl', function (service, $scope, $location, $state, $stateParams) {
        $scope.channelId = service.getUser().channel.channelId;
        $scope.doIt = function () {
            if ($scope.userId == null || $scope.userId == '') {
                showError("请输入登陆ID");
                return;
            }
            var formData = {
                "userId" : $scope.userId
            };
            service.post2SRV("queryUserById.do", formData, function (data, status) {
                $scope.userInfo = data;
            }, 1000);
        };

        $scope.resetPwd = function(){
            var formData = {
                userSeq:$scope.userInfo.userSeq,
                userId:$scope.userInfo.userId
            }
            service.post2SRV("resetPwd.do", formData, function (data, status) {
                alert("重置成功");
            }, 1000);
        }
    });
});