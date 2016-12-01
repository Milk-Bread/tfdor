define(['app', 'sysCode', 'directive', 'jquery'], function (app) {
    app.controller('HomeCtrl', function ($scope, $ionicSlideBoxDelegate, $timeout) {
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

        //$scope.noticeScroll = function(){
        //    $scope.t = parseInt($scope.x.css('top'));
        //    $scope.y.css('top','19px');
        //    $scope.x.animate({top: $scope.t - 19 + 'px'},'slow');	//19为每个li的高度
        //    if(Math.abs($scope.t) == $scope.h-19){ //19为每个li的高度
        //        $scope.y.animate({top:'0px'},'slow');
        //        $scope.z=$scope.x;
        //        $scope.x=$scope.y;
        //        $scope.y=$scope.z;
        //    }
        //    setTimeout($scope.noticeScroll,4000);//滚动间隔时间 现在是3秒
        //}
        //$scope.init = function(){
        //    $('.swap').html($('.news_li').html());
        //    $scope.x = $('.news_li');
        //    $scope.y = $('.swap');
        //    $scope.h = $('.news_li li').length * 19; //19为每个li的高度
        //    setTimeout($scope.noticeScroll,4000);//滚动间隔时间 现在是3秒
        //}
        //
        //$scope.init();

        // JavaScript Document
        function b(){
            t = parseInt(x.css('top'));
            y.css('top','19px');
            x.animate({top: t - 19 + 'px'},'slow');	//19为每个li的高度
            if(Math.abs(t) == h-19){ //19为每个li的高度
                y.animate({top:'200px'},'slow');
                z=x;
                x=y;
                y=z;
            }
            setTimeout(b,3000);//滚动间隔时间 现在是3秒
        }
        $(document).ready(function(){
            $('.swap').html($('.news_li').html());
            x = $('.news_li');
            y = $('.swap');
            h = $('.news_li li').length * 19; //19为每个li的高度
            setTimeout(b,3000);//滚动间隔时间 现在是3秒

        })
        //$scope.noticeScroll = function(){
        //    $scope.t = parseInt($scope.x.css('top'));
        //    $scope.y.css('top','19px');
        //    $scope.x.animate({top: $scope.t - 19 + 'px'},'slow');	//19为每个li的高度
        //    if(Math.abs($scope.t) == $scope.h-19){ //19为每个li的高度
        //        $scope.y.animate({top:'0px'},'slow');
        //        $scope.z=$scope.x;
        //        $scope.x=$scope.y;
        //        $scope.y=$scope.z;
        //    }
        //    setTimeout($scope.noticeScroll,4000);//滚动间隔时间 现在是3秒
        //}
        $scope.notice = [
            {"noticeUrl":"www.baidu.com","noticeName":"黄金搭档，墙漆腻子（面料）"},
            {"noticeUrl":"www.baidu.com","noticeName":"特级粘粉，低价出售"},
            {"noticeUrl":"www.baidu.com","noticeName":"全能耐水腻子粉"},
            {"noticeUrl":"www.baidu.com","noticeName":"别墅级外墙腻子粉"},
            {"noticeUrl":"www.baidu.com","noticeName":"超亮爽滑腻子王"}
        ];
        //$scope.init = function(){
        //    $('.swap').html($('.news_li').html());
        //    $scope.x = $('.news_li');
        //    $scope.y = $('.swap');
        //    $scope.h = $('.news_li li').length * 19; //19为每个li的高度
        //    setTimeout($scope.noticeScroll,4000);//滚动间隔时间 现在是3秒
        //}
        //
        //$scope.init();
    });
})



