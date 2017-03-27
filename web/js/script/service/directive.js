define(['app'], function (app) {
    "use strict";
    //设置ion-scroll高度自适应
    app.directive('scrollHeight',function($window){
        return{
            restrict:'AE',
            link:function(scope,element,attr){
                element[0].style.height=($window.innerHeight-50-44)+'px';
            }
        }
    })
});