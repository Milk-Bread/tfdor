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