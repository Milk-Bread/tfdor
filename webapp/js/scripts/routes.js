define([], function () {
    "use strict";
    return {
        defaultRoute: '/Login',
        routes: {
            'Login': {//登陆
                url: '/Login',
                templateUrl: 'views/Login/login.html',
                dependencies: ['controllers/Login/login'],
                allowAnonymous: true
            }, 'ResetPasd': {//重置密码
                url: '/ResetPasd',
                templateUrl: 'views/SysMng/resetPassword.html',
                dependencies: ['controllers/SysMng/resetPassword'],
                allowAnonymous: true
            }, 'Main': {//首页
                url: '/Main',
                templateUrl: 'views/Main/main.html',
                dependencies: ['controllers/Main/main'],
                allowAnonymous: true
            }, 'Main.UserManager': {//用户管理
                url: '/UserManager',
                templateUrl: 'views/SysMng/userManager.html',
                dependencies: ['controllers/SysMng/userManager'],
                allowAnonymous: true
            }, 'Main.RoleManager': {//角色管理
                url: '/RoleManager',
                templateUrl: 'views/SysMng/roleManager.html',
                dependencies: ['controllers/SysMng/roleManager'],
                allowAnonymous: true
            }, 'Main.addRole': {//添加角色
                url: '/addRole',
                templateUrl: 'views/SysMng/addRole.html',
                dependencies: ['controllers/SysMng/addRole'],
                allowAnonymous: true
            }, 'Main.modifyRole': {//修改角色
                url: '/modifyRole',
                templateUrl: 'views/SysMng/modifyRole.html',
                dependencies: ['controllers/SysMng/modifyRole'],
                allowAnonymous: true
            }, 'Main.QrCodeManager': {//二维码管理
                url: '/QrCodeManager',
                templateUrl: 'views/Marketing/qrcodeManager.html',
                dependencies: ['controllers/Marketing/qrcodeManager'],
                allowAnonymous: true
            }, 'Main.addUser': {//添加用户
                url: '/addUser',
                templateUrl: 'views/SysMng/addUser.html',
                dependencies: ['controllers/SysMng/addUser'],
                allowAnonymous: true
            }, 'Main.CheckQuery': {//审核查询
                url: '/CheckQuery',
                templateUrl: 'views/Auditing/audiList.html',
                dependencies: ['controllers/Auditing/audiList'],
                allowAnonymous: true
            }, 'Main.modifyUser': {//用户信息修改
                url: '/modifyUser',
                templateUrl: 'views/SysMng/modifyUser.html',
                dependencies: ['controllers/SysMng/modifyUser'],
                allowAnonymous: true
            }, 'Main.audiDetails': {//审核详情
                url: '/audiDetails',
                templateUrl: 'views/Auditing/audiDetails.html',
                dependencies: ['controllers/Auditing/audiDetails'],
                allowAnonymous: true
            }, 'Main.CheckResultQuery': {//
                url: '/CheckResultQuery',
                templateUrl: 'views/Auditing/audiResultList.html',
                dependencies: ['controllers/Auditing/audiResultList'],
                allowAnonymous: true
            }, 'Main.ChannelManager': {//渠道管理
                url: '/ChannelManager',
                templateUrl: 'views/SysMng/channelManager.html',
                dependencies: ['controllers/SysMng/channelManager'],
                allowAnonymous: true
            }, 'Main.addChannel': {//添加渠道
                url: '/addChannel',
                templateUrl: 'views/SysMng/addChannel.html',
                dependencies: ['controllers/SysMng/addChannel'],
                allowAnonymous: true
            }, 'Main.modifyChannel': {//修改渠道
                url: '/modifyChannel',
                templateUrl: 'views/SysMng/modifyChannel.html',
                dependencies: ['controllers/SysMng/modifyChannel'],
                allowAnonymous: true
            }, 'Main.RedPackManager': {//红包管理
                url: '/RedPackManager',
                templateUrl: 'views/Marketing/redPackManager.html',
                dependencies: ['controllers/Marketing/redPackManager'],
                allowAnonymous: true
            }, 'Main.MerchantManager': {//商户管理
                url: '/MerchantManager',
                templateUrl: 'views/SysMng/merchantManager.html',
                dependencies: ['controllers/SysMng/merchantManager'],
                allowAnonymous: true
            }, 'Main.deleteUser': {//删除用户
                url: '/deleteUser',
                templateUrl: 'views/SysMng/deleteUser.html',
                dependencies: ['controllers/SysMng/deleteUser'],
                allowAnonymous: true
            }, 'Main.addMerchant': {//添加商户
                url: '/addMerchant',
                templateUrl: 'views/SysMng/addMerchant.html',
                dependencies: ['controllers/SysMng/addMerchant'],
                allowAnonymous: true
            }, 'Main.addQrcode': {//添加二维码
                url: '/addQrcode',
                templateUrl: 'views/Marketing/addQrcode.html',
                dependencies: ['controllers/Marketing/addQrcode'],
                allowAnonymous: true
            }, 'Main.merchantDetail': {//商户详情
                url: '/merchantDetail',
                templateUrl: 'views/SysMng/merchantDetail.html',
                dependencies: ['controllers/SysMng/merchantDetail'],
                allowAnonymous: true
            }, 'Main.modifyMerchant': {//修改商户信息
                url: '/modifyMerchant',
                templateUrl: 'views/SysMng/modifyMerchant.html',
                dependencies: ['controllers/SysMng/modifyMerchant'],
                allowAnonymous: true
            }, 'Main.qrCodeImg': {//二维码查询
                url: '/qrCodeImg',
                templateUrl: 'views/Marketing/qrcodeImg.html',
                dependencies: ['controllers/Marketing/qrcodeImg'],
                allowAnonymous: true
            }, 'Main.modifyRedPack': {//修改红包
                url: '/modifyRedPack',
                templateUrl: 'views/Marketing/modifyRedPack.html',
                dependencies: ['controllers/Marketing/modifyRedPack'],
                allowAnonymous: true
            }, 'Main.addRedPack': {//添加红包
                url: '/addRedPack',
                templateUrl: 'views/Marketing/addRedPack.html',
                dependencies: ['controllers/Marketing/addRedPack'],
                allowAnonymous: true
            }, 'Main.modifyCreateQrcodeImage': {//修改二维码
                url: '/modifyCreateQrcodeImage',
                templateUrl: 'views/Marketing/modifyCreateQrcodeImage.html',
                dependencies: ['controllers/Marketing/modifyCreateQrcodeImage'],
                allowAnonymous: true
            }, 'Main.PasswordManager': {//密码管理
                url: '/PasswordManager',
                templateUrl: 'views/SysMng/passwordManager.html',
                dependencies: ['controllers/SysMng/passwordManager'],
                allowAnonymous: true
            }, 'Main.deleteRole': {//上次角色
                url: '/deleteRole',
                templateUrl: 'views/SysMng/deleteRole.html',
                dependencies: ['controllers/SysMng/deleteRole'],
                allowAnonymous: true
            }, 'Main.MaterialManager': {
                url: '/MaterialManager',
                templateUrl: 'views/Wechat/materialManager.html',
                dependencies: ['controllers/Wechat/materialManager'],
                allowAnonymous: true
            }, 'Main.WechatMenuManager': {//微信菜单管理
                url: '/WechatMenuManager',
                templateUrl: 'views/Wechat/wechatMenuManager.html',
                dependencies: ['controllers/wechat/WechatMenuManager'],
                allowAnonymous: true
            }
        }
    };
});
