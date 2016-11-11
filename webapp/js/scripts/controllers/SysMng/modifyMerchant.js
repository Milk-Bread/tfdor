/**
 * Created by chepeiqing on 16/10/13.
 */
define(['app', 'service','sysCode'], function (app) {
    app.controller('modifyMerchantCtrl', function (service, $scope, $location, $state, $stateParams, $rootScope) {
        $scope.selectMerchant = service.getData();

        $scope.init = function () {
            $scope.appId = $scope.selectMerchant.appId;
            $scope.wxToken = $scope.selectMerchant.wxToken;
            $scope.appSecret = $scope.selectMerchant.appSecret;
            $scope.signatureKey = $scope.selectMerchant.signatureKey;
            $scope.encodingAesKey = $scope.selectMerchant.encodingAesKey;
            $scope.state = $scope.selectMerchant.state;
            $scope.mchId = $scope.selectMerchant.mchId;
            $scope.mchName = $scope.selectMerchant.mchName;
            $scope.channelName = $scope.selectMerchant.channelName;

            $scope.channelId = service.getUser().channel.channelId;
            if ($scope.channelId != null && $scope.channelId == 'tfdor') {
                $scope.channelId = '';
            }
            var formData = {
                "channelId" : $scope.channelId
            };
            service.post2SRV("queryChannel.do", formData, function (data, status) {
                $scope.channelInfoList = data;
                for (var key in $scope.channelInfoList) {
                    if ($scope.selectMerchant.channelId == $scope.channelInfoList[key].channelId) {
                        $scope.channel = $scope.channelInfoList[key];
                    }
                }
            }, 1000);
            //查询复合人
            service.post2SRV("queryAuditPerson.do",null,function(data, status){
                $scope.auditPerson = data;
            },4000);
        };

        $scope.doId = function () {
            if ($scope.channel == null || $scope.channel == 0) {
                showError("错误提示：请选择渠道");
                return;
            }
            if ($scope.appId == null || $scope.appId == '') {
                showError("错误提示：请输入公众号ID");
                return;
            }
            if ($scope.wxToken == null || $scope.wxToken == '') {
                showError("错误提示：请输入公众号Token");
                return;
            }
            if ($scope.appSecret == null || $scope.appSecret == '') {
                showError("错误提示：请输入公众号密钥");
                return;
            }
            if ($scope.mchId == null || $scope.mchId == '') {
                showError("错误提示：请输入商户ID");
                return;
            }
            if ($scope.signatureKey == null || $scope.signatureKey == '') {
                showError("错误提示：请输入商户签名密钥");
                return;
            }
            if ($scope.encodingAesKey == null || $scope.encodingAesKey == '') {
                showError("错误提示：请输入微信加解密密钥");
                return;
            }
            if ($scope.person == null || $scope.person == '') {
                showError("错误提示：请选择复合人");
                return;
            }
            if ($scope.state == null) {
                $scope.state = 'N';
            }
            var formData = {
                "channelId": $scope.channel.channelId,
                "appId": $scope.appId,
                "wxToken": $scope.wxToken,
                "appSecret": $scope.appSecret,
                "mchId": $scope.mchId,
                "signatureKey": $scope.signatureKey,
                "encodingAesKey": $scope.encodingAesKey,
                "state": $scope.state,
                "auditPersonSeq":$scope.person.userSeq,//复合人Seq
                "auditPerson":$scope.person.userName//复合人名称
            }
            service.post2SRV("modifyMerchant.do", formData, function (data, status) {
                $state.go("Main.MerchantManager");
            }, 4000);
        }
        $scope.init();
    });
});