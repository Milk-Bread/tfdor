define(['app'], function (app) {
    "use strict";
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
            messages["qrCodeState"] = {
                "I": "未使用",
                "S": "使用成功",
                "F": "使用失败"
            };
            messages["actionName"] = {
                "QR_LIMIT_STR_SCENE": "永久二维码",
                "QR_SCENE": "临时二维码"
            };
            messages["redPackType"] = {
                "OYRK": "普通红包",
                "FNRK": "裂变红包"
            };
            messages["amountType"] = {
                "FDAT": "固定金额",
                "RMAT": "随机金额"
            };
            messages["state"] = {
                "N": "正常",
                "C": "销户",
                "S": "停用",
                "H": "关闭"
            };
            messages["customerType"] = {
                "A": "管理员",
                "O": "操作员"
            };
            messages["customerSex"] = {
                "1": "男",
                "2": "女",
                "0": "未知"
            };
            messages["auditingData"] = {
                "transName": "交易名称",
                "actionName": "二维码类型",
                "state": "状态",
                "channelName": "渠道名称",
                "mchId": "商户ID",
                "mchName": "商户名称",
                "appSecret": "微信appSecret",
                "encodingAesKey": "微信加解密密钥(随机数)",
                "signatureKey": "商户签名密钥(微信支付)",
                "wxToken": "微信token",
                "roleName": "权限名称",
                "customerType": "用户类型",
                "roleArr": "功能菜单",
                "channelId": "所属渠道",
                "auditPerson": "复合人",
                "roleSeq": "权限ID",
                "userId": "用户名",
                "userName": "用户姓名",
                "sex": "性别",
                "mobilePhone": "手机号码",
                "phone": "传真",
                "addr": "地址",
                "age": "年龄",
                "idNo": "身份证",
                "createTime": "交易日期",
                "remark": "备注",
                "actName": "活动名称",
                "wishing": "红包祝福语",
                "totalAmount": "红包金额",
                "totalNum": "红包发放总人数",
                "amountType": "金额类型",
                "beginDate": "生效时间",
                "endDate": "失效时间",
                "number": "数量",
                "appId": "微信ID",
                "redPackType": "红包类型",
                "modifyRole.do": "角色修改",
                "modifyUser.do": "用户信息修改",
                "addUser.do": "新增用户",
                "addChannel.do": "添加渠道",
                "modifyChannel.do": "修改渠道",
                "addRole.do": "新增角色",
                "deleteUser.do": "删除用户",
                "deleteRole.do": "删除角色",
                "modifyCreateQrcodeImage.do": "修改二维码参数",
                "createQrcodeImg.do": "生成二维码",
                "addRedPack.do": "新增红包参数",
                "addMerchant.do": "新增商户",
                "modifyRedPack.do": "修改红包参数",
                "deleteQrCode.do":"二维码删除"
            };
            if (type) {
                var msg = messages[type][code];
                if (msg) {
                    return msg;
                }
            }
            return code;
        };
    });
});
