define([], function () {
    return {
        defaultRoute: '/tabs/home',
        routes: {
            'tabs': {
                url: '/tabs',
                templateUrl: 'views/tabs.html',
                abstract:true,
                allowAnonymous: true
            },'tabs.home': {
                content: "tabs_home",
                url: '/home',
                templateUrl: 'views/home.html',
                dependencies: ['controllers/homeCtrl'],
                allowAnonymous: true
            }
        }
    };
});