define([ 'routes', 'loader', 'angularAMD', 'ui-bootstrap','angular-sanitize', 'blockUI', 'ui.route' ,'loading-bar'], function(config, loader,angularAMD) {
	var app = angular.module("webapp", [  'ngSanitize','ui.bootstrap', 'ui.router','angular-loading-bar']);
	app.run(['$rootScope','$templateCache', function ($rootScope,$templateCache) {//不记录缓存
		$rootScope.$on('$stateChangeSuccess', function ($rootScope) {
			$templateCache.removeAll();
		});
	}]);
	app.filter('sysCode',function() {
		return function(code,type) {
			var messages = {};
			messages["auditingState"] = {
				"I": "待审核",
				"S": "审核通过",
				"F": "审核不通过"
			};
			if (type) {
			    var sysCodeCon = messages;
			    var msg = sysCodeCon[type][code];
			    if (msg) {
			        return msg;
			    }
			}
			return code;
		};
	});
	app.config(function($stateProvider, $urlRouterProvider) {
		// 配置路由
		if (config.routes != undefined) {
			angular.forEach(config.routes, function(route, path) {
				$stateProvider.state(path, {
					templateUrl : route.templateUrl,
					url : route.url,
					cache:false,
					resolve : loader(route.dependencies),
					allowAnonymous: route.allowAnonymous
				});
			});
		}
		// 默认路由
		if (config.defaultRoute != undefined) {
			$urlRouterProvider.when("", config.defaultRoute);
		}
	});
	return angularAMD.bootstrap(app);
});