define(['app', 'service'], function (app) {
    app.controller('mainCtrl', function (service, $scope, $location, $state, $stateParams) {
        $scope.init = function () {
            var user = service.getUser();
            $scope.userName = user.userName
            service.post2SRV("lodeMenu.do", null, function (data, status) {
                $scope.menuList = data;
            }, 4000);
        }
        $scope.menuOne = function (menu) {
            $scope.menuTwo1 = null;
            $scope.menuOne1 = menu;
        };

        $scope.menuTwo = function (menu) {
            $scope.menuTwo1 = menu;
        };

        $scope.logout = function(){
            service.post2SRV("logout.do", null, function (data, status) {
                $state.go("Login");
            }, 4000);
        }
        $scope.init();
    });
});