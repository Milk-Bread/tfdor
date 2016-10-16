define([], function () {
    return {
        defaultRoute: '/Login',
        routes: {
            'Login': {
            	url: '/Login',
                templateUrl: 'views/Login/login.html',
                dependencies: ['controllers/Login/login'],
                allowAnonymous: true
            },
            'Main': {
            	url: '/Main',
                templateUrl: 'views/Main/main.html',
                dependencies: ['controllers/Main/main'],
                allowAnonymous: true
            },'Main.UserManager': {
            	url: '/UserManager',
                templateUrl: 'views/SysMng/userManager.html',
                dependencies: ['controllers/SysMng/userManager'],
                allowAnonymous: true
            },'Main.RoleManager': {
            	url: '/RoleManager',
                templateUrl: 'views/SysMng/roleManager.html',
                dependencies: ['controllers/SysMng/roleManager'],
                allowAnonymous: true
            },'Main.QrCodeManager': {
                url: '/QrCodeManager',
                templateUrl: 'views/Marketing/qrcodeManager.html',
                dependencies: ['controllers/Marketing/qrcodeManager'],
                allowAnonymous: true
            }
        }
    };
});