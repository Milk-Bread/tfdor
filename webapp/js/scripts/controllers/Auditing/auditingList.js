define(['app', 'service'], function (app) {
    app.controller('audiListCtrl', function (service, $scope, $location, $state, $stateParams,$rootScope) {
        $scope.init = function () {
            service.post2SRV("auditingList.do", null, function (data, status) {
                $scope.auditingList = data;
            }, 4000);
        }
        $scope.init();
    });
});