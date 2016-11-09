/**
 * Created by chepeiqing on 16/10/13.
 */
define(['app', 'service', 'sysCode'], function (app) {
    app.controller('addQrcodeCtrl', function (service, $scope, $location, $state, $stateParams, $rootScope) {
        $scope.isMerch = false;
        $scope.init = function () {
            //查询复合人
            service.post2SRV("queryAuditPerson.do", null, function (data, status) {
                $scope.auditPerson = data;
            }, 4000);
            //查询商户信息
            var formData = {
                "channelId": service.getUser().channel.channelId
            };
            service.post2SRV("queryBusiness.do", formData, function (data, status) {
                if (data.length > 1) {
                    $scope.isMerch = true;
                    $scope.merchantList = data;
                } else {
                    $scope.appId = data.appId;
                }
            }, 4000);
        };

        $scope.doIt = function () {
            if ($scope.actionName == null || $scope.actionName == '' || $scope.actionName == undefined) {
                $scope.actionName = 'QR_LIMIT_STR_SCENE';
            } else {
                if ($scope.expireSeconds == null || $scope.expireSeconds == '') {
                    showError("错误提示", "请输入有效时间");
                    return;
                }
            }
            console.log($scope.beginDate);
            if ($("#beginDate").val() == null || $("#beginDate").val() == '') {
                showError("错误提示", "请选择二维码生效时间");
                return;
            }
            if ($("#endDate").val() == null || $("#endDate").val() == '') {
                showError("错误提示", "请选择二维码失效时间");
                return;
            }
            if ($scope.number == null || $scope.number == '') {
                showError("错误提示", "请二维码数量");
                return;
            }
            if ($scope.state == null) {
                $scope.state = 'N';
            }
            if ($scope.person == null || $scope.person == '') {
                showError("错误提示", "请选择复合人");
                return;
            }
            $scope.appId = $scope.merchant.appId;
            if($scope.merchant == null || $scope.merchant == ''){
                showError("错误提示", "请选择生成二维码的商户");
                return;
            }
            var formData = {
                "actionName": $scope.actionName,
                "expireSeconds": $scope.expireSeconds,
                "beginDate": $("#beginDate").val(),
                "endDate": $("#endDate").val(),
                "number": $scope.number,
                "state": $scope.state,
                "appId": $scope.appId,
                "auditPersonSeq": $scope.person.userSeq,//复合人Seq
                "auditPerson": $scope.person.userName//复合人名称
            };
            return;
            service.post2SRV("createQrcodeImg.do", formData, function (data, status) {
                $state.go("Main.qrcodeMagager");
            }, 4000);
        }
        $scope.init();
    });
});