define(['app', 'service','sysCode'], function (app) {
    app.controller('mainCtrl', function (service, $scope, $location, $state, $stateParams,$rootScope) {
        $scope.initMain = function () {
            var user = service.getUser();
            if(user != null){
                $scope._userName = user.userName;
            }
            service.post2SRV("lodeMenu.do", null, function (data, status) {
                $scope.menuList = data;
            }, 4000);
        }
        $scope.menuOne = function (menu) {
            $scope.menuTwo1 = null;
            $scope.menuOne1 = menu;
            $rootScope.menuThree1 = null;
        };

        $scope.menuTwo = function (menu,url) {
            $scope.menuTwo1 = menu;
            $scope.url = url;
            $rootScope.menuThree1 = null;
        };

        $rootScope.menuThree = function(menu){
            $rootScope.menuThree1 = menu;
        };
        $scope.logout = function(){
            service.post2SRV("logout.do", null, function (data, status) {
                $state.go("Login");
            }, 4000);
        }
        $scope.showMain = function (id) {
            if($('#mainId' + id).css('display') == 'none'){
                $('.groupLi').slideUp(500);
                $('#mainId' + id).slideDown(500);
            }
        }
        $scope.initMain();
    });
});