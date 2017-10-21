define(['app', 'sysCode', 'directive', 'jquery', 'service'], function (app) {
    "use strict";
    app.controller('ProductCtrl', function ($scope, $ionicHistory, service) {
        $scope.content = "敬请期待";
        service.post2SRV("getJsConfigData.action", null, function (data) {
            console.log(data);
            wx.config({
                debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
                appId: data.appId, // 必填，公众号的唯一标识
                timestamp: data.timestamp, // 必填，生成签名的时间戳
                nonceStr: data.noncestr, // 必填，生成签名的随机串
                signature: data.signature,// 必填，签名，见附录1
                jsApiList: ['onMenuShareTimeline', 'onMenuShareAppMessage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
            });
            //// config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
            wx.ready(function () {
                console.log("config初始化成功");
                wx.checkJsApi({
                    jsApiList: ['onMenuShareTimeline', 'onMenuShareAppMessage'], // 需要检测的JS接口列表，所有JS接口列表见附录2,
                    success: function (res) {
                        // 以键值对的形式返回，可用的api值true，不可用为false
                        // 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
                        console.log("是否支持js接口");
                        console.log("是否支持 onMenuShareTimeline:" + res.checkResult.onMenuShareTimeline);
                        console.log("是否支持 onMenuShareAppMessage:" + res.checkResult.onMenuShareAppMessage);
                    }
                });

                if (wx) {
                    console.log("分享到好友")
                    wx.onMenuShareAppMessage({
                        title: '微信分享测试', // 分享标题
                        desc: '就是测试', // 分享描述
                        link: 'https://www.tfdor.cc/web/index.html', // 分享链接
                        imgUrl: '', // 分享图标
                        type: 'link', // 分享类型,music、video或link，不填默认为link
                        dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
                        success: function () {
                            alert("分享成功了");
                        },
                        cancel: function () {
                            alert("取消分享了");
                        }
                    });
                } else {
                    alert("微信接口调用失败");
                }
            });

            if (wx) {
                wx.onMenuShareTimeline({
                    title: '微信分享测试', // 分享标题
                    link: 'https://www.tfdor.cc/web/index.html', // 分享链接
                    imgUrl: '', // 分享图标
                    success: function () {
                        alert("分享成功了");
                    },
                    cancel: function () {
                        alert("取消分享了");
                    }
                });
            }
            // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
            wx.error(function (res) {
                alert("config信息验证失败了");
            });
        });
        $scope.goBack = function () {
            $ionicHistory.goBack();
        }
    });
})



