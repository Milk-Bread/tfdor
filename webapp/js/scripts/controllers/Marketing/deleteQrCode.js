/**
 * Created by yizilong on 16/11/04.
 */
define(['app', 'service','sysCode'], function (app) {
    "use strict";
    app.controller('deleteQrCodeCtrl', function (service, $scope, $state) {
        $scope.init = function(){
            //查询复合人
            service.post2SRV("queryAuditPerson.do",null,function(data, status){
                $scope.auditPerson = data;
            },4000);
        };

        $scope.doId = function () {

            if ($scope.person == null || $scope.person == '') {
                showError("错误提示", "请选择复合人");
                return;
            }
            if (service.getSessionDate().createQISeq == null || service.getSessionDate().createQISeq == '') {
                showError("错误提示", "信息为空");
                return;
            }


            var formData = {
                "createQiSeq": service.getSessionDate().createQISeq,
                "auditPersonSeq":$scope.person.userSeq,//复合人Seq
                "auditPerson":$scope.person.userName//复合人名称
            }
            service.post2SRV("deleteQrCode.do", formData, function (data, status) {
                $state.go("Main.QrCodeManager");
            }, 4000);
        }

        $scope.init();
    });
});
