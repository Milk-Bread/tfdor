require.config({
    //配置总路径
    baseUrl: "js/scripts",
    paths: {
        // 其他模块会依赖他
        'ui.route': '../lib/angular-plugins/angular-ui-router/angular-ui-router.min',
        'angular': '../lib/angular/angular.min',
        'angular-route': '../lib/angular-route/angular-route.min',
        'angularAMD': '../lib/angular-plugins/angularAMD',
        'jquery': '../lib/jquery.min',
        'wdatePicker': '../lib/WdatePicker/WdatePicker',
        'loading-bar': '../lib/angular-plugins/angular-loading-bar/loading-bar.min',
        'bootstrap': '../lib/angular-plugins/bootstrap/js/bootstrap.min',
        'kindeditorAll' : '../lib/kindeditor/kindeditor-all-min',
        'kindeditorditor' : '../lib/kindeditor/angular-kindeditor',
        'service': '../scripts/service/common/service',
        'sysCode': '../scripts/service/common/sysCode',
        'paging': '../scripts/service/common/directive',
        'encryption': '../scripts/service/common/encryption'
    },

    shim: {
        // 表明该模块依赖angular
        'angularAMD': ['angular'],
        'angular-route': ['angular'],
        'ui.route': ['angular'],
        'wdatePicker': ['jquery'],
        'loading-bar': ['angular'],
        'sysCode': ['angular'],
        'service': ['jquery', 'angular'],
        'bootstrap': ['jquery'],
        'paging': ['angular']
    },
    urlArgs: "v=" + new Date().getTime(),
    // 启动程序 js/scripts/app.js
    deps: ['app', 'paging', 'wdatePicker']
});


