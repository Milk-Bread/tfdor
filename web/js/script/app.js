define(['routes', 'loader', 'angularAMD', 'ionic'], function (config, loader, angularAMD) {
    "use strict";
    var app = angular.module('starter', ['ionic']);
    app.config(function ($stateProvider, $urlRouterProvider,$ionicConfigProvider) {
        $ionicConfigProvider.platform.android.scrolling.jsScrolling(true);
        $ionicConfigProvider.platform.ios.tabs.style('standard');
        $ionicConfigProvider.platform.ios.tabs.position('bottom');
        $ionicConfigProvider.platform.android.tabs.style('standard');
        $ionicConfigProvider.platform.android.tabs.position('standard');
        $ionicConfigProvider.platform.ios.navBar.alignTitle('center');
        $ionicConfigProvider.platform.android.navBar.alignTitle('center');
        $ionicConfigProvider.platform.ios.backButton.previousTitleText('').icon('ion-ios-arrow-thin-left');
        $ionicConfigProvider.platform.android.backButton.previousTitleText('').icon('ion-android-arrow-back');
        $ionicConfigProvider.platform.ios.views.transition('ios');
        $ionicConfigProvider.platform.android.views.transition('android');
        $ionicConfigProvider.backButton.text("");
        $ionicConfigProvider.backButton.previousTitleText(false);
        //缓存
        //$ionicConfigProvider.views.maxCache(0);
        // 配置路由
        if (config.routes != undefined) {
            angular.forEach(config.routes, function (route, path) {
                var params = {};
                if(route.abstract == true){
                    params = {
                        url: route.url,
                        templateUrl: route.templateUrl,
                        abstract:route.abstract,
                        resolve: loader(route.dependencies),
                        allowAnonymous: route.allowAnonymous
                    };
                }else{
                    var content_ = route.content;
                    params["url"] = route.url;
                    var views = {};
                    views[content_] = {
                        templateUrl: route.templateUrl,
                        resolve: loader(route.dependencies),
                        allowAnonymous: route.allowAnonymous
                    }
                    params["views"] = views;
                }
                $stateProvider.state(path, params);
            });
        }
        // 默认路由
        if (config.defaultRoute != undefined) {
            $urlRouterProvider.when("", config.defaultRoute);
        }
    });
    return angularAMD.bootstrap(app);
});
