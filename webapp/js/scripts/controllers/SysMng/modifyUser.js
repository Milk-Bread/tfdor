/**
 * Created by chepeiqing on 16/10/13.
 */
define(['app', 'service','sysCode'], function (app) {
    "use strict";
    app.controller('modifyUserCtrl', function (service, $scope, $state) {
        $scope.userInfo = service.getData();
        // 是否显示选择渠道
        $scope.isShow = false;
        // 当前渠道ID
        $scope.channelId = service.getUser().channel.channelId;
        if ($scope.channelId != null && $scope.channelId == 'tfdor') {
            $scope.isShow = true;
        }
        $scope.init = function(){
            $scope.userName = $scope.userInfo.userName;
            $scope.age = $scope.userInfo.age;
            $scope.idNo = $scope.userInfo.idNo;
            $scope.mobilePhone = $scope.userInfo.mobilePhone;
            $scope.phone = $scope.userInfo.phone;
            $scope.addr = $scope.userInfo.addr;
            $scope.sex = $scope.userInfo.sex;

            // 如果为内置渠道，则不用查询所以渠道
            if ($scope.isShow) {
                service.post2SRV("queryChannel.do", null, function (data, status) {
                    $scope.channelInfoList = data;
                    for (var key in $scope.channelInfoList) {
                        if ($scope.userInfo.channelId == $scope.channelInfoList[key].channelId) {
                            $scope.channel = $scope.channelInfoList[key];
                        }
                    }
                }, 1000);
            }

            var formData = {
                "channelId" : $scope.channelId
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

        $scope.queryRole = function () {
            var channelId = $scope.channel.channelId;
            var formData = {
                "channelId" : channelId,
                "flag" : $scope.channelId
            };
            service.post2SRV("queryRole.do", formData,function(data,status) {
                $scope.roleList = data;
            },4000);
        }

        $scope.doIt = function () {
            if ($scope.userName == null || $scope.userName == '') {
                showError("用户名称错误,请输入用户名称");
                return;
            }
            if ($scope.isShow) {
                if ($scope.channel == null || $scope.channel == 0) {
                    showError("错误提示,请选择渠道");
                    return;
                }
                $scope.channelId = $scope.channel.channelId;
            }

            if ($scope.role == null || $scope.role.length == 0) {
                showError("错误提示,请选择角色");
                return;
            }
            if ($scope.age == null || $scope.age == '') {
                showError("年龄错误,请输入年龄");
                return;
            }
            if ($scope.idNo == null || $scope.idNo == '') {
                showError("身份证错误,请输入身份证");
                return;
            }
            if ($scope.mobilePhone == null || $scope.mobilePhone.length < 11) {
                showError("手机号码错误,请输入手机号码");
                return;
            }
            if ($scope.phone == null || $scope.phone.length < 11) {
                showError("联系电话错误,请输入联系电话");
                return;
            }
            if ($scope.addr == null || $scope.addr == '') {
                showError("地址错误,请输入地址");
                return;
            }
            if ($scope.person == null || $scope.person == '') {
                showError("错误提示,请选择复合人");
                return;
            }
            if ($scope.sex == null) {
                $scope.sex = 'M';
            }
            if ($scope.isShow) {
                if ($scope.customerType == null) {
                    $scope.customerType = 'O';
                }
            } else {
                $scope.customerType = 'O';
            }

            var formData = {
                "channelId": $scope.channelId,
                "userSeq": $scope.userInfo.userSeq,
                "userId": $scope.userId,    // 账号
                "userName": $scope.userName,   // 用户名
                "roleSeq": $scope.role.roleSeq,  // 角色
                "age": $scope.age,  // 年龄
                "idNo": $scope.idNo,    // 身份证
                "mobilePhone": $scope.mobilePhone,  // 手机号码
                "phone": $scope.phone,  // 联系电话
                "addr": $scope.addr,    // 地址
                "sex" : $scope.sex,
                "customerType" : $scope.customerType,
                "auditPersonSeq":$scope.person.userSeq,//复合人Seq
                "auditPerson":$scope.person.userName//复合人名称
            }
            service.post2SRV("modifyUser.do", formData, function (data, status) {
                $state.go("Main.UserManager");
            }, 4000);
        }

        $scope.init();
    });
});