define(['app'], function (app) {
    app.service('service', function ($http, cfpLoadingBar, $state) {
        this.getUser = function () {
            return angular.fromJson(sessionStorage.getItem("_USER"));
        };
        this.setUser = function (user) {
            sessionStorage.setItem("_USER", angular.toJson(user));
        };
        this.post2SRV = function (action, formData, callBack, timeOut) {
            if (formData == null || formData == '') {
                formData = {};
            }
            formData["transName"] = action;
            transFn = function (formData) {
                return $.param(formData);
            },
                postCfg = {
                    headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
                    transformRequest: transFn
                };
            $(".mask").show();
            cfpLoadingBar.start();
            $http.post(
                action,
                formData,
                postCfg,
                timeOut
            ).success(function (data, header, config, status) {
                cfpLoadingBar.complete();
                $(".mask").hide();
                if (data._exceptionCode != null || data._exceptionCode == 'false') {
                    showError("错误提示：" + data._exceptionMsg);
                    if (data._exceptionCode == "please.log.in.again") {
                        $state.go("Login");
                    } else if (data._exceptionCode == "please.reset.the.password.for.the.first.time.login") {
                        $state.go("ResetPasd");
                    }
                } else {
                    callBack(data, header, config, status);
                }
            }).error(function (data, header, config, status) {
                cfpLoadingBar.complete();
                $(".mask").hide();
                var errorStr = "网络错误";
                if (data._exceptionMsg != null && data._exceptionMsg != undefined && data._exceptionMsg != '') {
                    errorStr = data._exceptionMsg;
                }
                showError("错误提示：" + errorStr + action);
                if (data._exceptionCode == "please.log.in.again") {
                    $state.go("Login");
                } else if (data._exceptionCode == "please.reset.the.password.for.the.first.time.login") {
                    $state.go("ResetPasd");
                }
            });
            if (action != 'audiAgree.do' && action != 'audiRefuse.do' && formData['auditPersonSeq'] != null && formData['auditPersonSeq'] != '') {
                showInfo("提交成功，请等待【" + formData['auditPerson'] + "】复合");
            }
        };
        this.getData = function () {
            return angular.fromJson(sessionStorage.getItem("paramData"));
        };
        this.setData = function (data) {
            sessionStorage.setItem("paramData", angular.toJson(data));
        };
    });
});
var time;
var showError = function (intro) {
    clearTimeout(time);
    $("#intro").html(intro);
    $('#errorDiv').slideDown(700);
    time = setTimeout(function () {
        $('#errorDiv').fadeOut(400);
    }, 6000);
};

var showInfo = function (intro) {
    clearTimeout(time);
    $("#info").html(intro);
    $('#alertInfo').slideDown(700);
    time = setTimeout(function () {
        $('#alertInfo').fadeOut(400);
    }, 6000);
};

function closeInfo() {
    $('#alertInfo').fadeOut(500);
}

function closeError() {
    $('#errorDiv').fadeOut(500);
}