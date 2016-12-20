define(['app', 'sysCode', 'directive', 'jquery'], function (app) {
    "use strict";
    app.controller('HomeCtrl', function ($scope, $state, $ionicSlideBoxDelegate, $timeout, $ionicScrollDelegate) {
        //为了验证属性active-slide定义的模型，angularjs是mvc模式
        $scope.model = {
            activeIndex: 0
        };
        //滑动图片的点击事件
        $scope.coverFlowClick = function () {
            var index = $ionicSlideBoxDelegate.currentIndex();
            console.log("coverFlowClick index = ", index);
        };
        //此事件对应的是pager-click属性，当显示图片是有对应数量的小圆点，这是小圆点的点击事件
        $scope.pageClick = function (index) {
            console.log("pageClick index = ", index);
            $scope.model.activeIndex = index;
        };
        //当图片切换后，触发此事件，注意参数
        $scope.slideHasChanged = function ($index) {
        };
        //这是属性delegate-handle的验证使用的，其实没必要重定义，直接使用$ionicSlideBoxDelegate就可以
        $scope.delegateHandle = $ionicSlideBoxDelegate;
        //公告滚动效果
        $scope.noticeScroll = function () {
            $scope.t = parseInt($scope.x.css('margin-top'));
            $scope.y.css('margin-top', '35px');
            $scope.x.animate({'margin-top': '-' + Math.abs($scope.t) - 35 + 'px'}, 'slow');	//25为每个li的高度
            if (Math.abs($scope.t) == Math.abs($scope.h) - 35) { //25为每个li的高度
                $scope.y.animate({'margin-top': '0px'}, 'slow');
                $scope.z = $scope.x;
                $scope.x = $scope.y;
                $scope.y = $scope.z;
            }
            setTimeout($scope.noticeScroll, 5000);//滚动间隔时间 现在是3秒
        }
        $scope.notice = [
            {"noticeUrl": "www.baidu.com", "noticeName": "1、黄金搭档，墙漆腻子（面料）"},
            {"noticeUrl": "www.baidu.com", "noticeName": "2、特级粘粉，低价出售"},
            {"noticeUrl": "www.baidu.com", "noticeName": "3、全能耐水腻子粉"},
            {"noticeUrl": "www.baidu.com", "noticeName": "4、别墅级外墙腻子粉"},
            {"noticeUrl": "www.baidu.com", "noticeName": "5、超亮爽滑腻子王"}
        ];
        $scope.startScroll = function () {
            //$('.swap').html($('.news_li').html());
            $scope.x = $('.news-li');
            $scope.h = $('.news-li li').length * 35; //25为每个li的高度
            $scope.t = parseInt($scope.x.css('margin-top'));
            if (Math.abs($scope.t) == Math.abs($scope.h) - 35) { //25为每个li的高度
                $scope.x.css('margin-top', '0px');
            } else {
                $scope.x.animate({'margin-top': '-' + Math.abs($scope.t) - 35 + 'px'}, 'slow');	//25为每个li的高度
            }
            setTimeout($scope.startScroll, 4000);//滚动间隔时间 现在是3秒
        }
        setTimeout($scope.startScroll, 1000);

        $scope.onSearchUp = function () {
            var position = -$("#slideBox").offset().top;
            if (position > 50) {
                var pos = position - 50;
                var tmd = Math.abs((pos / 100).toFixed(1));
                if (tmd >= 0.8) {
                    tmd = 0.8;
                } else if (Math.abs(position) <= 50) {
                    tmd = 0;
                }
                $scope.searchStyle = {
                    "opacity": tmd,
                    "filter": "alpha(opacity=" + tmd * 100 + ",style=0)",
                    "-moz-opacity": tmd
                }
            } else {
                $scope.searchStyle = {
                    "opacity": 0,
                    "filter": "alpha(opacity=" + 1 * 100 + ",style=0)",
                    "-moz-opacity": 0
                }
            }
        };
        //跳转方法
        $scope.goPage = function (route) {
            $state.go(route);
        }
    });
})



