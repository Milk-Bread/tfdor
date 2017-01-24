define([], function () {
    "use strict";
    return {
        defaultRoute: '/Login',
        routes: {
            'Login': {
                url: '/Login',
                templateUrl: 'views/Login/login.html',
                dependencies: ['controllers/Login/login'],
                allowAnonymous: true
            }, 'ResetPasd': {
                url: '/ResetPasd',
                templateUrl: 'views/SysMng/resetPassword.html',
                dependencies: ['controllers/SysMng/resetPassword'],
                allowAnonymous: true
            }, 'Main': {
                url: '/Main',
                templateUrl: 'views/Main/main.html',
                dependencies: ['controllers/Main/main'],
                allowAnonymous: true
            }, 'Main.UserManager': {
                url: '/UserManager',
                templateUrl: 'views/SysMng/userManager.html',
                dependencies: ['controllers/SysMng/userManager'],
                allowAnonymous: true
            }, 'Main.RoleManager': {
                url: '/RoleManager',
                templateUrl: 'views/SysMng/roleManager.html',
                dependencies: ['controllers/SysMng/roleManager'],
                allowAnonymous: true
            }, 'Main.addRole': {
                url: '/addRole',
                templateUrl: 'views/SysMng/addRole.html',
                dependencies: ['controllers/SysMng/addRole'],
                allowAnonymous: true
            }, 'Main.modifyRole': {
                url: '/modifyRole',
                templateUrl: 'views/SysMng/modifyRole.html',
                dependencies: ['controllers/SysMng/modifyRole'],
                allowAnonymous: true
            }, 'Main.QrCodeManager': {
                url: '/QrCodeManager',
                templateUrl: 'views/Marketing/qrcodeManager.html',
                dependencies: ['controllers/Marketing/qrcodeManager'],
                allowAnonymous: true
            }, 'Main.addUser': {
                url: '/addUser',
                templateUrl: 'views/SysMng/addUser.html',
                dependencies: ['controllers/SysMng/addUser'],
                allowAnonymous: true
            }, 'Main.CheckQuery': {
                url: '/CheckQuery',
                templateUrl: 'views/Auditing/audiList.html',
                dependencies: ['controllers/Auditing/audiList'],
                allowAnonymous: true
            }, 'Main.modifyUser': {
                url: '/modifyUser',
                templateUrl: 'views/SysMng/modifyUser.html',
                dependencies: ['controllers/SysMng/modifyUser'],
                allowAnonymous: true
            }, 'Main.audiDetails': {
                url: '/audiDetails',
                templateUrl: 'views/Auditing/audiDetails.html',
                dependencies: ['controllers/Auditing/audiDetails'],
                allowAnonymous: true
            }, 'Main.CheckResultQuery': {
                url: '/CheckResultQuery',
                templateUrl: 'views/Auditing/audiResultList.html',
                dependencies: ['controllers/Auditing/audiResultList'],
                allowAnonymous: true
            }, 'Main.ChannelManager': {
                url: '/ChannelManager',
                templateUrl: 'views/SysMng/channelManager.html',
                dependencies: ['controllers/SysMng/channelManager'],
                allowAnonymous: true
            }, 'Main.addChannel': {
                url: '/addChannel',
                templateUrl: 'views/SysMng/addChannel.html',
                dependencies: ['controllers/SysMng/addChannel'],
                allowAnonymous: true
            }, 'Main.modifyChannel': {
                url: '/modifyChannel',
                templateUrl: 'views/SysMng/modifyChannel.html',
                dependencies: ['controllers/SysMng/modifyChannel'],
                allowAnonymous: true
            }, 'Main.RedPackManager': {
                url: '/RedPackManager',
                templateUrl: 'views/Marketing/redPackManager.html',
                dependencies: ['controllers/Marketing/redPackManager'],
                allowAnonymous: true
            }, 'Main.MerchantManager': {
                url: '/MerchantManager',
                templateUrl: 'views/SysMng/merchantManager.html',
                dependencies: ['controllers/SysMng/merchantManager'],
                allowAnonymous: true
            }, 'Main.deleteUser': {
                url: '/deleteUser',
                templateUrl: 'views/SysMng/deleteUser.html',
                dependencies: ['controllers/SysMng/deleteUser'],
                allowAnonymous: true
            }, 'Main.addMerchant': {
                url: '/addMerchant',
                templateUrl: 'views/SysMng/addMerchant.html',
                dependencies: ['controllers/SysMng/addMerchant'],
                allowAnonymous: true
            }, 'Main.addQrcode': {
                url: '/addQrcode',
                templateUrl: 'views/Marketing/addQrcode.html',
                dependencies: ['controllers/Marketing/addQrcode'],
                allowAnonymous: true
            }, 'Main.merchantDetail': {
                url: '/merchantDetail',
                templateUrl: 'views/SysMng/merchantDetail.html',
                dependencies: ['controllers/SysMng/merchantDetail'],
                allowAnonymous: true
            }, 'Main.modifyMerchant': {
                url: '/modifyMerchant',
                templateUrl: 'views/SysMng/modifyMerchant.html',
                dependencies: ['controllers/SysMng/modifyMerchant'],
                allowAnonymous: true
            }, 'Main.qrCodeImg': {
                url: '/qrCodeImg',
                templateUrl: 'views/Marketing/qrcodeImg.html',
                dependencies: ['controllers/Marketing/qrcodeImg'],
                allowAnonymous: true
            }, 'Main.modifyRedPack': {
                url: '/modifyRedPack',
                templateUrl: 'views/Marketing/modifyRedPack.html',
                dependencies: ['controllers/Marketing/modifyRedPack'],
                allowAnonymous: true
            }, 'Main.addRedPack': {
                url: '/addRedPack',
                templateUrl: 'views/Marketing/addRedPack.html',
                dependencies: ['controllers/Marketing/addRedPack'],
                allowAnonymous: true
            }, 'Main.modifyCreateQrcodeImage': {
                url: '/modifyCreateQrcodeImage',
                templateUrl: 'views/Marketing/modifyCreateQrcodeImage.html',
                dependencies: ['controllers/Marketing/modifyCreateQrcodeImage'],
                allowAnonymous: true
            }, 'Main.PasswordManager': {
                url: '/PasswordManager',
                templateUrl: 'views/SysMng/passwordManager.html',
                dependencies: ['controllers/SysMng/passwordManager'],
                allowAnonymous: true
            }, 'Main.deleteRole': {
                url: '/deleteRole',
                templateUrl: 'views/SysMng/deleteRole.html',
                dependencies: ['controllers/SysMng/deleteRole'],
                allowAnonymous: true
            }, 'Main.MaterialManager': {
                url: '/MaterialManager',
                templateUrl: 'views/Wechat/materialManager.html',
                dependencies: ['controllers/Wechat/materialManager'],
                allowAnonymous: true
            }, 'Main.WechatMenuManager': {
                url: '/WechatMenuManager',
                templateUrl: 'views/Wechat/wechatMenuManager.html',
                dependencies: ['controllers/wechat/WechatMenuManager'],
                allowAnonymous: true
            }
        }
    };
});