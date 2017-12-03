/**
 * Created by chepeiqing on 16/10/13.
 */
define(['app', 'service', 'sysCode'], function (app) {
    "use strict";
    app.controller('modifyCreateQrcodeImgCtrl', function (service, $scope, $state) {
        $scope.init = function () {
            $scope.actionName = service.getSessionDate().actionName;
            $scope.mchName = service.getSessionDate().mchName;
            $scope.expireSeconds = service.getSessionDate().expireSeconds;
            $scope.beginDate = service.getSessionDate().beginDate;
            $("#minDate").val(service.getSessionDate().endDate);
            $("#endDate").val(service.getSessionDate().endDate);
            $scope.number = service.getSessionDate().number;
            $scope.state = service.getSessionDate().state;
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
            if ($scope.person == null || $scope.person == '') {
                showError("错误提示：请选择复合人");
                return;
            }
            var formData = {
                "createQISeq": service.getSessionDate().createQISeq,
                "endDate": $("#endDate").val(),
                "state": $scope.state,
                "auditPersonSeq": $scope.person.userSeq,//复合人Seq
                "auditPerson": $scope.person.userName//复合人名称
            };
            service.post2SRV("modifyCreateQrcodeImage.do", formData, function (data, status) {
                $state.go("Main.QrCodeManager");
            }, 4000);
        }
        $scope.init();
    });
});
