define(['app'], function (app) {
    app.filter('sysCode', function () {
        return function (code, type) {
            var messages = {};
            messages["channelState"] = {
                "N": "正常",
                "C": "销户",
                "S": "停用"
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