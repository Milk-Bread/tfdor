require.config({
    //配置总路径
    baseUrl: "js/script",
    paths: {
        // 其他模块会依赖他
        'ionic': '../lib/ionic/js/ionic.bundle.min',
        'service': 'service/service',
        'sysCode': 'service/sysCode',
        'directive': 'service/directive',
        'angularAMD': '../lib/angularAMD/angularAMD',
        'jquery': '../lib/jquery/jquery.min'
    },
    waitSeconds: 0,
    shim: {
        // 表明该模块依赖angular
        'angularAMD': ['ionic'],
        'sysCode': ['ionic'],
        'directive': ['ionic','jquery'],
        'service': ['ionic']
    },
    urlArgs: "v=" + new Date().getTime(),
    // 启动程序 js/script/app.js
    deps: ['app']
});


