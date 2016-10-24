define(['app', 'service', 'sysCode'], function (app) {
    app.controller('audiListCtrl', function (service, $scope, $location, $state, $stateParams, $rootScope) {
        $scope.init = function () {
            service.post2SRV("auditingList.do", null, function (data, status) {
                $scope.auditingList = data;
            }, 4000);
        };


        $scope.gotoDetails = function (obj) {
            service.setData(obj);
            $state.go("Main.audiDetails");
        };
        $scope.agree = function (obj) {
            var formData = angular.fromJson(obj.auditingData);
            formData["auditingTrans"] = obj.auditingTrans;
            formData["auditingSeq"] = obj.auditingSeq;
            service.post2SRV("audiAgree.do", formData, function (data, status) {
                alert("复合成功");
                $scope.init();
            }, 4000);
        };
        $scope.refuse = function (obj) {
            var formData = angular.fromJson(obj.auditingData);
            formData["auditingTrans"] = obj.auditingTrans;
            formData["auditingSeq"] = obj.auditingSeq;
            service.post2SRV("audiRefuse.do", formData, function (data, status) {
                alert("复合成功");
                $scope.init();
            }, 4000);
        };

        $scope.init();
    });
});