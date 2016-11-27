require.config({
    //配置总路径
    baseUrl: "js",
    paths: {
        // 其他模块会依赖他
        'ionic': '../lib/ionic/js/ionic.bundle.min',
        'service': 'service/service',
        'sysCode': 'service/sysCode',
        'angularAMD': '../lib/angularAMD/angularAMD'
    },
    waitSeconds: 0,
    shim: {
        // 表明该模块依赖angular
        'angularAMD': ['ionic'],
        'sysCode': ['ionic'],
        'service': ['ionic']
    },
    urlArgs: "v=" + new Date().getTime(),
    // 启动程序 js/app.js
    deps: ['app']
});


