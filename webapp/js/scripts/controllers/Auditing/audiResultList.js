define(['app', 'service', 'sysCode'], function (app) {
    app.controller('audiResultCtrl', function (service, $scope, $state) {
        $scope.init = function () {
            service.post2SRV("audiResultList.do", null, function (data, status) {
                $scope.auditingList = data;
            }, 4000);
        };
        $scope.gotoDetails = function (obj) {
            obj["result"] = false;
            service.setData(obj);
            $state.go("Main.audiDetails");
        };
        $scope.init();
    });
});