define(['app', 'service', 'sysCode'], function (app) {
    "use strict";
    app.controller('wechatMenuCtrl', function (service, $scope, $state) {
        $('.menuleft ul li').each(function(i){
            $(this).click(function(){
                if($(this).attr("class")!="on"){
                    $('.menuleft ul .on span').animate({bottom:-$('.menuleft ul .on span').height()},200);
                    $('.menuleft ul .on').removeClass("on");
                    $(this).addClass("on");
                    $('.menuleft ul li span').eq(i).animate({bottom:35},200);
                    //$('.footer_front').show();
                }
            });
        });
    });
});