/**
 * Created by chepeiqing on 16/10/13.
 */
define(['app', 'service', 'sysCode'], function (app) {
    app.controller('addQrcodeCtrl', function (service, $scope, $location, $state, $stateParams, $rootScope) {
        $scope.init = function () {
            $scope.actionName = service.getData().actionName;
            $scope.mchName = service.getData().mchName;
            $scope.expireSeconds = service.getData().expireSeconds;
            $scope.beginDate = service.getData().beginDate;
            $("#minDate").val(service.getData().endDate);
            $("#endDate").val(service.getData().endDate);
            $scope.number = service.getData().number;
            $scope.state = service.getData().state;
            //查询复合人
            service.post2SRV("queryAuditPerson.do", null, function (data, status) {
                $scope.auditPerson = data;
            }, 4000);
        };

        $scope.doIt = function () {
            if ($("#endDate").val() == null || $("#endDate").val() == '') {
                showError("错误提示：请选择二维码失效时间");
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
                "createQISeq": service.getData().createQISeq,
                "endDate": $("#endDate").val(),
                "state": $scope.state,
                "auditPersonSeq": $scope.person.userSeq,//复合人Seq
                "auditPerson": $scope.person.userName//复合人名称
            };
            service.post2SRV("modifyQrcode.do", formData, function (data, status) {
                $state.go("Main.QrCodeManager");
            }, 4000);
        }
        $scope.init();
    });
});