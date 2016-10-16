define([ 'routes', 'loader', 'angularAMD', 'ui-bootstrap','angular-sanitize', 'blockUI', 'ui.route' ,'loading-bar'], function(config, loader,angularAMD) {
	var app = angular.module("webapp", [  'ngSanitize','ui.bootstrap', 'ui.router','angular-loading-bar']);
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
/**定义User对象**/
var _USER = {
		userId:null,
		userName:null,
		sex:null
};
