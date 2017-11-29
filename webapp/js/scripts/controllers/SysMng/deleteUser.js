/**
 * Created by yizilong on 16/11/04.
 */
define(['app', 'service','sysCode'], function (app) {
    "use strict";
    app.controller('deleteUserCtrl', function (service, $scope, $state) {
        $scope.userInfo = service.getDataMap();
        $scope.init = function(){
            $scope.userSeq = $scope.userInfo.userSeq;

            var formData = {
                "roleName" : $scope.roleName
            };
            service.post2SRV("queryRole.do", formData,function(data,status) {
                $scope.roleList = data;
                for (var key in $scope.roleList) {
                    if ($scope.userInfo.roleSeq == $scope.roleList[key].roleSeq) {
                        $scope.role = $scope.roleList[key];
                    }
                }
            },4000);
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

            var formData = {
                "userSeq": $scope.userInfo.userSeq,
                "auditPersonSeq":$scope.person.userSeq,//复合人Seq
                "auditPerson":$scope.person.userName//复合人名称
            }
            service.post2SRV("deleteUser.do", formData, function (data, status) {
                $state.go("Main.UserManager");
            }, 4000);
        }

        $scope.init();
    });
});
