define(['routes', 'loader', 'angularAMD', 'ionic'], function (config, loader, angularAMD) {
    var app = angular.module('starter', ['ionic']);
    app.config(function ($stateProvider, $urlRouterProvider) {
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
