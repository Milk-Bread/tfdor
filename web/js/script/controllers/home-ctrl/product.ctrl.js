define(['app', 'sysCode', 'directive', 'jquery'], function (app) {
    "use strict";
    app.controller('ProductCtrl', function ($scope, $ionicHistory) {
        $scope.content = "敬请期待";

        $scope.goBack = function(){
            $ionicHistory.goBack();
        }
    });
})



