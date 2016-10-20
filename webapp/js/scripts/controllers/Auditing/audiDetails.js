define(['app', 'service', 'sysCode'], function (app) {
    app.controller('audiDetailsCtrl', function (service, $scope, $location, $state, $stateParams,$rootScope) {
        $scope.init = function () {
            var audiData = service.getData();
        };
        $scope.init();
    });
});