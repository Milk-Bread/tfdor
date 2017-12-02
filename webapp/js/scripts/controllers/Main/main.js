define(['app', 'service', 'sysCode'], function (app) {
    "use strict";
    app.controller('mainCtrl', function (service, $scope, $state, $rootScope) {
        $scope.initMain = function () {
            var user = service.getUser();
            if (user != null) {
                $scope._userName = user.userName;
            }
            service.post2SRV("lodeMenu.do", null, function (data, status) {
                $scope.menuList = data;
            }, 4000);
            $scope.menuTwo1 = service.getData("menuTwo1") == 'null' ? null : service.getData("menuTwo1");
            $scope.menuOne1 = service.getData("menuOne1") == 'null' ? null : service.getData("menuOne1");
            $rootScope.menuThree1 = service.getData("menuThree1") == 'null' ? null : service.getData("menuThree1");
            $scope.urlTwo = service.getData("urlTwo") == 'null' ? null : service.getData("urlTwo");
        }
        $scope.menuOne = function (menu) {
            $scope.menuTwo1 = null;
            $scope.menuOne1 = menu;
            $rootScope.menuThree1 = null;
            service.setData("menuTwo1", null);
            service.setData("menuOne1", menu);
            service.setData("menuThree1", null);

        };

        $scope.menuTwo = function (menu, url) {
            $scope.menuTwo1 = menu;
            $scope.urlTwo = url;
            $rootScope.menuThree1 = null;
            service.setData("menuTwo1", menu);
            service.setData("urlTwo", url);
            service.setData("menuThree1", null);
        };

        $scope.goMenu = function () {
            var menu = "Main." + $scope.urlTwo;
            $rootScope.menuThree1 = null;
            service.setData("menuThree1", null);
            $state.go(menu);
        };

        $rootScope.menuThree = function (menu) {
            $rootScope.menuThree1 = menu;
            service.setData("menuThree1", menu);
        };
        $scope.logout = function () {
            service.post2SRV("logout.do", null, function (data, status) {
                $state.go("Login");
            }, 4000);
        }
        $scope.showMain = function (id) {
            if ($('#mainId' + id).css('display') == 'none') {
                $('.groupLi').slideUp(500);
                $('#mainId' + id).slideDown(500);
            }
        }
        $scope.initMain();
    });
});
