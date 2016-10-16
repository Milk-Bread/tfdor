/**
 * Created by pengyuming on 16/10/12.
 */
define(['app', 'service'], function (app) {
    app.controller('qrCodeManager', function (service,$scope,$location,$state,$stateParams) {
        service.post2SRV("getQrcodeImg.do", null,function(data,status) {
            console.log(data);
            $scope.qrcodeimgList = data;
        },1000);
    });
});