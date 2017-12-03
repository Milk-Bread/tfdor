define(['app', 'service', 'sysCode'], function (app) {
    "use strict";
    app.controller('audiResultCtrl', function (service, $scope, $state) {
        //当前页
        $scope.pageNo = 1;
        //每页显示数量
        $scope.pageSize = 10;
        $scope.init = function () {
            var formData = {
                "pageNo":$scope.pageNo,
                "pageSize":$scope.pageSize
            }
            service.post2SRV("audiResultList.do", formData, function (data, status) {
                //记录总条数
                $scope.total = data.total;
                //总页数
                $scope.pages = data.pages;
                //数据list
                $scope.auditingList = data.list;
            }, 4000);
        };
        $scope.gotoDetails = function (obj) {
            obj["result"] = false;
            service.setSessionDate(obj);
            $state.go("Main.audiDetails");
        };
        $scope.init();
    });
});
