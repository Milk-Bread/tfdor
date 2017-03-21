define(['app', 'sysCode', 'directive', 'jquery'], function (app) {
    "use strict";
    app.controller('HomeCtrl', function ($scope, $state, $ionicSlideBoxDelegate) {
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

        $scope.notice = [
            {"noticeUrl": "http://www.baidu.com", "noticeName": "黄金搭档，墙漆腻子（面料）"},
            {"noticeUrl": "http://www.baidu.com", "noticeName": "特级粘粉，低价出售"},
            {"noticeUrl": "http://www.baidu.com", "noticeName": "全能耐水腻子粉"},
            {"noticeUrl": "http://www.baidu.com", "noticeName": "别墅级外墙腻子粉"},
            {"noticeUrl": "http://www.baidu.com", "noticeName": "超亮爽滑腻子王"}
        ];

        //公告滚动效果
        $scope.noticeScroll = function () {
            $scope.x = $('.news-li');
            $scope.h = $('.news-li li').length * 35; //25为每个li的高度
            $scope.t = parseInt($scope.x.css('margin-top'));
            if (Math.abs($scope.t) == Math.abs($scope.h) - 35) { //25为每个li的高度
                $scope.x.css('margin-top', '0px');
            } else {
                $scope.x.animate({'margin-top': '-' + Math.abs($scope.t) - 35 + 'px'}, 'slow');	//25为每个li的高度
            }
            setTimeout($scope.noticeScroll, 4000);//滚动间隔时间 现在是3秒
        };
        setTimeout($scope.noticeScroll, 1000);

        //搜索框滑动显示特效
        $scope.onSearchUp = function () {
            var position = -$("#slideBox").offset().top;
            if (position > 40) {
                var pos = position - 40;
                var tmd = Math.abs((pos / 100).toFixed(1));
                if (tmd >= 0.8) {
                    tmd = 0.8;
                } else if (Math.abs(position) <= 40) {
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



