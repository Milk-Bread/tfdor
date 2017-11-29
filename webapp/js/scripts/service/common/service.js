define(['app'], function (app) {
    "use strict";
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
            var transFn = function (formData) {
                return $.param(formData);
            }
            var postCfg = {
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
                    if (data._exceptionCode == "please.log.in.again" || data._exceptionCode == 'account.in.other.places.you.have.to.force..off.the.assembly.line') {
                        $state.go("Login");
                    } else if (data._exceptionCode == "please.reset.the.password.for.the.first.time.login") {
                        $state.go("ResetPasd");
                    }
                } else {
                    if(action == 'queryAuditPerson.do' && data == ""){
                        showError("错误提示：该渠道无符合条件的复核人, 请联系管理员");
                    }
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
                showInfo("提交成功，请等待【 " + formData['auditPerson'] + " 】复合");
            }
        };
        this.getDataMap = function () {
            return angular.fromJson(sessionStorage.getItem("paramData"));
        };
        this.setDataMap = function (data) {
            sessionStorage.setItem("paramData", angular.toJson(data));
        };
        this.getData = function (key) {
            return sessionStorage.getItem(key);
        };
        this.setData = function (key,data) {
            sessionStorage.setItem(key, data);
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
var infotime;
var showInfo = function (intro) {
    clearTimeout(infotime);
    $("#info").html(intro);
    $('#alertInfo').slideDown(700);
    infotime = setTimeout(function () {
        $('#alertInfo').fadeOut(400);
    }, 5000);
};

function closeInfo() {
    $('#alertInfo').fadeOut(500);
}

function closeError() {
    $('#errorDiv').fadeOut(500);
}
