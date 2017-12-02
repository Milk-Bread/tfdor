define(['app', 'service', 'sysCode'], function (app) {
    "use strict";
    app.controller('audiListCtrl', function (service, $scope, $state) {
        //当前页
        $scope.pageNo = 1;
        //每页显示数量
        $scope.pageSize = 10;
        $scope.init = function () {
            var formData = {
                "pageNo":$scope.pageNo,
                "pageSize":$scope.pageSize
            }
            service.post2SRV("auditingList.do", formData, function (data, status) {
                //记录总条数
                $scope.total = data.total;
                //总页数
                $scope.pages = data.pages;
                //数据list
                $scope.auditingList = data.list;
            }, 4000);
        };
        $scope.gotoDetails = function (obj) {
            service.setDataMap(obj);
            $state.go("Main.audiDetails");
        };
        $scope.agree = function (obj) {
            var formData = angular.fromJson(obj.auditingData);
            formData["auditingTrans"] = obj.auditingTrans;
            formData["auditingSeq"] = obj.auditingSeq;
            service.post2SRV("audiAgree.do", formData, function (data, status) {
                showInfo("复合成功");
                $scope.init();
            }, 4000);
        };
        $scope.refuse = function (obj) {
            var rm = prompt("输入提示","请输入拒绝原因");
            if(rm == null){
                return;
            }
            var obj = service.getDataMap();
            var formData = angular.fromJson(obj.auditingData);
            formData["remarks"] = rm;
            formData["auditingTrans"] = obj.auditingTrans;
            formData["auditingSeq"] = obj.auditingSeq;
            service.post2SRV("audiRefuse.do", formData, function (data, status) {
                showInfo("复合成功");
                $scope.init();
            }, 4000);
        };
        $scope.init();
    });
});
