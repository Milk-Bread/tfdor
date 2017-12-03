/**
 * Created by chepeiqing on 16/10/13.
 */
define(['app', 'service', 'sysCode'], function (app) {
    "use strict";
    app.controller('addQrcodeCtrl', function (service, $scope, $state,$rootScope) {
        $scope.isMerch = false;
        $scope.submitBt = false;
        $scope.init = function () {
            //查询复合人
            service.post2SRV("queryAuditPerson.do", null, function (data, status) {
                $scope.auditPerson = data;
            }, 4000);
            //查询商户信息
            service.post2SRV("queryMerchant.do", null, function (data, status) {
                if(data != null && data.length == 0){
                    showError("请添加商户");
                    $rootScope.menuThree1 = null;
                    $state.go("Main.QrCodeManager");
                }else if (data.length > 1) {
                    $scope.isMerch = true;
                    $scope.merchantList = data;
                } else {
                    $scope.appId = data[0].appId;
                    $scope.queryRedPackList(data[0].mchId);
                }
            }, 4000);
        };

        $scope.queryRedPack = function(){
            $scope.queryRedPackList($scope.merchant.mchId);
        };

        $scope.queryRedPackList = function(mchId){
            var formData = {
                "mchId": mchId
            };
            service.post2SRV("queryRedPackList.do", formData, function (data, status) {
                if(data.length == 0){
                    showError("请先添加红包参数");
                    $scope.submitBt = true;
                }else{
                    $scope.redPackList = data;
                    $scope.submitBt = false;
                };
            }, 4000);
        }

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
                    $scope.appName = $scope.merchant.mchName;
                }
            }
            if($scope.redPack == null || $scope.redPack == undefined || $scope.redPack == ''){
                showError("错误提示：请选择活动名称");
                return;
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
            var formData = {
                "actionName": $scope.actionName,
                "expireSeconds": $scope.expireSeconds,
                "beginDate": $("#beginDate").val(),
                "endDate": $("#endDate").val(),
                "number": $scope.number,
                "redPackSeq":$scope.redPack.redPackSeq,
                "actName":$scope.redPack.actName,
                "state": $scope.state,
                "appId": $scope.appId,
                "appName":$scope.appName
            };
            service.setSessionDate(formData);
            $state.go("Main.addQrcodeCofm");
        }
        $scope.init();
    });
});
