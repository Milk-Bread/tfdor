define(['app'], function (app) {
    "use strict";
    app.service('service', function ($http, $state) {
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
            $http.post(
                action,
                formData,
                postCfg,
                timeOut
            ).success(function (data, header, config, status) {
                if (data._exceptionCode != null || data._exceptionCode == 'false') {
                    alert("错误提示：" + data._exceptionMsg);
                } else {
                    callBack(data, header, config, status);
                }
            }).error(function (data, header, config, status) {
                var errorStr = "网络错误";
                if (data._exceptionMsg != null && data._exceptionMsg != undefined && data._exceptionMsg != '') {
                    errorStr = data._exceptionMsg;
                }
                alert(errorStr);
            });
        };
        this.getData = function () {
            return angular.fromJson(sessionStorage.getItem("paramData"));
        };
        this.setData = function (data) {
            sessionStorage.setItem("paramData", angular.toJson(data));
        };
    });
});