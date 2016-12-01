define(['app'], function (app) {
    app.directive('slideFollow', function ($timeout) {
        return {
            restrict: 'E',
            replace: true,
            scope: {
                id: "@",
                datasetData: "="
            },
            template: "<div class='slide'>" +
                            "<ul class='slideUl'>"+
                                "<li ng-repeat = 'data in datasetData'>{{data.option}}</li>"+
                            "</ul>"+
                        "</div>",
            link: function (scope, elem, attrs) {
                $timeout(function () {
                    var className = $("." + $(elem)[0].className);
                    var i = 0, sh;
                    var liLength = className.children("li").length;
                    var liHeight = className.children("li").height() + parseInt(className.children("li").css('border-bottom-width'));
                    className.html(className.html() + className.html());
                    // 开启定时器
                    sh = setInterval(slide, 4000);
                    function slide() {
                        if (parseInt(className.css("margin-top")) > (-liLength * liHeight)) {
                            i++;
                            className.animate({
                                marginTop: -liHeight * i + "px"
                            }, "slow");
                        } else {
                            i = 0;
                            className.css("margin-top", "0px");
                        }
                    }

                    // 清除定时器
                    className.hover(function () {
                        clearInterval(sh);
                    }, function () {
                        clearInterval(sh);
                        sh = setInterval(slide, 4000);
                    })

                }, 0)
            }
        }
    });
});