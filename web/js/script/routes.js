define([], function () {
    "use strict";
    return {
        defaultRoute: '/tabs/home',
        routes: {
            'tabs': {//tab页面
                url: '/tabs',
                templateUrl: 'views/tabs.html',
                abstract:true,
                allowAnonymous: true
            },'tabs.home': {//首页
                content: "tabs-home",
                url: '/home',
                templateUrl: 'views/home-views/tab.home.html',
                dependencies: ['controllers/home-ctrl/home.ctrl'],
                allowAnonymous: true
            },'tabs.product':{//产品展示页面
                content: "tabs-home",
                url: '/product',
                templateUrl: 'views/home-views/tab.product.html',
                dependencies: ['controllers/home-ctrl/product.ctrl'],
                allowAnonymous: true
            }

        }
    };
});