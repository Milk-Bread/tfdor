require.config({
	//配置总路径
	baseUrl : "js/scripts",
	paths : {
		// 其他模块会依赖他
		'ui.route':'../lib/angular-plugins/angular-ui-router/angular-ui-router.min',
		'angular' : '../lib/angular/angular.min',
		'angular-route' : '../lib/angular-route/angular-route.min',
		'angularAMD' : '../lib/angular-plugins/angularAMD',
		'jquery' : '../lib/jquery.min',
		'ueditorConfig' : '../lib/ueditor/ueditor.config',
		'ueditorAll' : '../lib/ueditor/ueditor.all.min',
		'wdatePicker' : '../lib/My97DatePicker/WdatePicker',
		'blockUI':'../lib/angular-plugins/angular-block-ui/angular-block-ui.min',
		'loading-bar':'../lib/angular-plugins/angular-loading-bar/loading-bar.min',
		'ngload':'../lib/angular-plugins/ngload',
		'ui-bootstrap':'../lib/angular-plugins/angular-ui-bootstrap/ui-bootstrap-tpls-0.12.1.min',
		'bootstrap':'../lib/angular-plugins/bootstrap/bootstrap.min',
		'angular-sanitize':'../lib/angular-plugins/angular-sanitize.min',
		'service':'../scripts/service/common/service'
	},

	shim : {
		// 表明该模块依赖angular
		'angularAMD' : [ 'angular'],
		'angular-route' : [ 'angular'],
		'ui.route':['angular'],
		'ueditorConfig' : ['jquery'],
		'ueditorAll' : ['jquery'],
		'wdatePicker' : ['jquery'],
		'ui-bootstrap' : [ 'angular'],
		'blockUI' : [ 'angular'],
		'loading-bar':['angular'],
        'angular-sanitize' : [ 'angular' ],
        'service':['jquery','angular'],
		'bootstrap':['jquery']
	},
	urlArgs : "v=" + new Date().getTime(),
	// 启动程序 js/scripts/app.js
	deps : [ 'app' ]
});


