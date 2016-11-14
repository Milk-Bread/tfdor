/**
 * Created by chepeiqing on 16/10/13.
 */
define(['app', 'service', 'sysCode'], function (app) {
    app.controller('addRedPackCtrl', function (service, $scope, $location, $state, $stateParams, $rootScope) {
        $scope.isAmountType = false;
        $scope.isMerch = false;
        $scope.init = function () {
            //查询复合人
            service.post2SRV("queryAuditPerson.do", null, function (data, status) {
                $scope.auditPerson = data;
            }, 4000);
            service.post2SRV("queryMerchant.do", null, function (data, status) {
                if (data.length > 1) {
                    $scope.isMerch = true;
                    $scope.merchantList = data;
                } else {
                    $scope.mchId = data[0].mchId;
                }
            }, 4000);
        };

        $scope.switchAmountType = function () {
            if ($scope.amountType == '2') {
                $scope.isAmountType = true;
            } else {
                $scope.isAmountType = false;
            }
        }

        $scope.doIt = function () {
            if($scope.isMerch && ($scope.merchant == null || $scope.merchant == '' || $scope.merchant == undefined)){
                showError("错误提示：请选择生成二维码的商户");
                return;
            }else{
                if($scope.mchId == null){
                    $scope.mchId = $scope.merchant.mchId;
                }
            }
            if ($scope.amountType == 'FDAT') {
                if ($scope.totalAmount1 == null || $scope.totalAmount1 == '') {
                    showError("错误提示：请输入红包金额");
                    return;
                }
                $scope.totalAmount = $scope.totalAmount1;
            } else {
                if ($scope.totalAmount1 == null || $scope.totalAmount1 == '' || $scope.totalAmount2 == null || $scope.totalAmount2 == '') {
                    showError("错误提示：请输入红包金额");
                    return;
                }
                $scope.totalAmount = $scope.totalAmount1 + '-' + $scope.totalAmount2;
            }
            if ($scope.wishing == null || $scope.wishing == '') {
                showError("错误提示：请填写红包祝福语");
                return;
            }
            if ($scope.actName == null || $scope.actName == '') {
                showError("错误提示：请填写活动名称");
                return;
            }
            if ($scope.remark == null || $scope.remark == '') {
                showError("错误提示：请填写备注");
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
                "redPackSeq": $scope.redPackSeq,
                "redPackType": $scope.redPackType,
                "amountType": $scope.amountType,
                "mchId": $scope.mchId,
                "totalAmount": $scope.totalAmount,
                "wishing": $scope.wishing,
                "actName": $scope.actName,
                "remark": $scope.remark,
                "state": $scope.state,
                "auditPersonSeq": $scope.person.userSeq,//复合人Seq
                "auditPerson": $scope.person.userName//复合人名称
            };
            service.post2SRV("addRedPack.do", formData, function (data, status) {
                $state.go("Main.RedPackManager");
            }, 4000);
        };
        $scope.init();
    });
});