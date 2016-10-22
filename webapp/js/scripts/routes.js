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
            },'Main.addRole': {
                url: '/addRole',
                templateUrl: 'views/SysMng/addRole.html',
                dependencies: ['controllers/SysMng/addRole'],
                allowAnonymous: true
            },'Main.modifyRole': {
                url: '/modifyRole/:roleSeq/:roleName',
                templateUrl: 'views/SysMng/modifyRole.html',
                dependencies: ['controllers/SysMng/modifyRole'],
                allowAnonymous: true
            },'Main.QrCodeManager': {
                url: '/QrCodeManager',
                templateUrl: 'views/Marketing/qrcodeManager.html',
                dependencies: ['controllers/Marketing/qrcodeManager'],
                allowAnonymous: true
            }, 'Main.addUser': {
                url: '/addUser',
                templateUrl: 'views/SysMng/addUser.html',
                dependencies: ['controllers/SysMng/addUser'],
                allowAnonymous: true
            },'Main.CheckQuery': {
                url: '/CheckQuery',
                templateUrl: 'views/Auditing/auditingList.html',
                dependencies: ['controllers/Auditing/auditingList'],
                allowAnonymous: true
            },'Main.modifyUser': {
                url: '/modifyUser',
                templateUrl: 'views/SysMng/modifyUser.html',
                dependencies: ['controllers/SysMng/modifyUser'],
                allowAnonymous: true
            },'Main.audiDetails': {
                url: '/audiDetails',
                templateUrl: 'views/Auditing/auditingDetails.html',
                dependencies: ['controllers/Auditing/auditingDetails'],
                allowAnonymous: true
            }
        }
    };
});