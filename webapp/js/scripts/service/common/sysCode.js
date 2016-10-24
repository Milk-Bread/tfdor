define(['app'], function (app) {
    app.filter('sysCode', function () {
        return function (code, type) {
            var messages = {};
            messages["auditingState"] = {
                "I": "待审核",
                "S": "审核通过",
                "F": "审核不通过"
            };
            messages["sex"] = {
                "M": "男",
                "W": "女"
            };
            messages["channelState"] = {
                "N": "正常",
                "C": "销户",
                "S": "停用"
            };
            messages["auditingData"] = {
                "actionName": "交易名称",
                "state":"状态",
                "channelName":"渠道名称",
                "wxToken":"微信token",
                "roleName": "权限名称",
                "roleArr":"功能菜单",
                "channelId":"所属渠道",
                "auditPerson":"复合人",
                "roleSeq":"权限ID",
                "userId":"用户名",
                "userName":"用户姓名",
                "sex":"性别",
                "mobilePhone":"手机号码",
                "phone":"传真",
                "createTime":"交易日期",
                "modifyRole.do":"角色修改",
                "modifyUser.do":"用户信息修改",
                "addChannel.do":"添加渠道",
                "modifyChannel.do":"修改渠道",
                "addRole.do":"新增角色",
                "addr":"地址",
                "age":"年龄",
                "idNo":"身份证"
            };
            if (type) {
                var sysCodeCon = messages;
                var msg = sysCodeCon[type][code];
                if (msg) {
                    return msg;
                }
            }
            return code;
        };
    });
});