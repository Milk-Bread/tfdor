/**
 * Created by chepeiqing on 16/10/13.
 */
define(['app', 'service','sysCode'], function (app) {
    app.controller('modifyUserCtrl', function (service, $scope, $location, $state, $stateParams, $rootScope) {
        $scope.userInfo = service.getData();
        $scope.init = function(){
            $scope.userName = $scope.userInfo.userName;
            $scope.age = $scope.userInfo.age;
            $scope.idNo = $scope.userInfo.idNo;
            $scope.mobilePhone = $scope.userInfo.mobilePhone;
            $scope.phone = $scope.userInfo.phone;
            $scope.addr = $scope.userInfo.addr;
            $scope.sex = $scope.userInfo.sex;


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
            if ($scope.userName == null || $scope.userName == '') {
                showError("用户名称错误", "请输入用户名称");
                return;
            }
            if ($scope.role == null || $scope.role.length == 0) {
                showError("错误提示", "请选择角色");
                return;
            }
            if ($scope.age == null || $scope.age == '') {
                showError("年龄错误", "请输入年龄");
                return;
            }
            if ($scope.idNo == null || $scope.idNo == '') {
                showError("身份证错误", "请输入身份证");
                return;
            }
            if ($scope.mobilePhone == null || $scope.mobilePhone.length < 11) {
                showError("手机号码错误", "请输入手机号码");
                return;
            }
            if ($scope.phone == null || $scope.phone.length < 11) {
                showError("联系电话错误", "请输入联系电话");
                return;
            }
            if ($scope.addr == null || $scope.addr == '') {
                showError("地址错误", "请输入地址");
                return;
            }
            if ($scope.person == null || $scope.person == '') {
                showError("错误提示", "请选择复合人");
                return;
            }
            if ($scope.sex == null) {
                $scope.sex = 'M';
            }

            var formData = {
                "userSeq": $scope.userInfo.userSeq,
                "userId": $scope.userId,    // 账号
                "userName": $scope.userName,   // 用户名
                "roleId": $scope.role.roleSeq,  // 角色
                "age": $scope.age,  // 年龄
                "idNo": $scope.idNo,    // 身份证
                "mobilePhone": $scope.mobilePhone,  // 手机号码
                "phone": $scope.phone,  // 联系电话
                "addr": $scope.addr,    // 地址
                "sex" : $scope.sex,
                "auditPersonSeq":$scope.person.userSeq,//复合人Seq
                "auditPerson":$scope.person.userName//复合人名称
            }
            service.post2SRV("modifyUser.do", formData, function (data, status) {
                alert("提交成功,请等待审核");
                $state.go("Main.UserManager");
            }, 4000);
        }

        $scope.init();
    });
});