/**
 * Created by pengyuming on 16/10/12.
 */
define(['app', 'service','sysCode'], function (app) {
    app.controller('redEnvelopeMngCtrl', function (service,$scope,$location,$state,$stateParams) {
        $scope.init = function () {
            console.log(service.getUser());
            var formData = {
                "channelId" : service.getUser().channel.channelId
            };
            service.post2SRV("queryRedPack.do", formData,function(data,status) {
                console.log(data);
                $scope.redPackList = data;
            },1000);
        }
        $scope.init();
    });
});