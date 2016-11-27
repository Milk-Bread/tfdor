define([], function () {
    return {
        defaultRoute: '/tabs/home',
        routes: {
            'tabs': {
                url: '/tabs',
                templateUrl: 'templates/tabs.html',
                abstract:true,
                allowAnonymous: true
            },'tabs.home': {
                content: "tabs_home",
                url: '/home',
                templateUrl: 'templates/home.html',
                dependencies: ['controllers/homeCtrl'],
                allowAnonymous: true
            }
        }
    };
});