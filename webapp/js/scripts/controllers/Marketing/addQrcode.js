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
            service.post2SRV("queryMerchant.do", null, function (data, status) {
                if (data.length > 1) {
                    $scope.isMerch = true;
                    $scope.merchantList = data;
                } else {
                    $scope.appId = data[0].appId;
                }
            }, 4000);
        };

        $scope.doIt = function () {
            if ($scope.actionName == null || $scope.actionName == '' || $scope.actionName == undefined) {
                $scope.actionName = 'QR_LIMIT_STR_SCENE';
            } else if('QR_SCENE' == $scope.actionName){
                if ($scope.expireSeconds == null || $scope.expireSeconds == '') {
                    showError("错误提示：请输入有效时间");
                    return;
                }else if($scope.expireSeconds <= 0 && $scope.expireSeconds >= 30){
                    showError("错误提示：最大仅支持30天，请输入0至30之间到数字");
                    return;
                }
            }
            if($scope.isMerch && ($scope.merchant == null || $scope.merchant == '' || $scope.merchant == undefined)){
                showError("错误提示：请选择生成二维码的商户");
                return;
            }else{
                if($scope.appId == null){
                    $scope.appId = $scope.merchant.appId;
                }
            }
            if ($("#beginDate").val() == null || $("#beginDate").val() == '') {
                showError("错误提示：请选择二维码生效时间");
                return;
            }
            if ($("#endDate").val() == null || $("#endDate").val() == '') {
                showError("错误提示：请选择二维码失效时间");
                return;
            }
            if ($scope.number == null || $scope.number == '') {
                showError("错误提示：请二维码数量");
                return;
            }
            if ($scope.state == null) {
                $scope.state = 'N';
            }
            if ($scope.person == null || $scope.person == '') {
                showError("错误提示：请选择复合人");
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
            service.post2SRV("createQrcodeImg.do", formData, function (data, status) {
                $state.go("Main.QrCodeManager");
            }, 4000);
        }
        $scope.init();
    });
});