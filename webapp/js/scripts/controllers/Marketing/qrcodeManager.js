/**
 * Created by pengyuming on 16/10/12.
 */
define(['app', 'service','sysCode'], function (app) {
    "use strict";
    app.controller('qrCodeManager', function (service,$scope,$state) {
        //当前页
        $scope.pageNo = 1;
        //每页显示数量
        $scope.pageSize = 10;
        $scope.loginchannelId = service.getUser().channel.channelId;
        $scope.init = function() {
            if($scope.merchant != undefined){
                $scope.channelId = $scope.merchant.channelId
            }else{
                $scope.channelId = "";
            }
            var formData = {
                pageNo:$scope.pageNo,
                pageSize:$scope.pageSize,
                channelId:$scope.channelId,
                beginDate:$("#beginDate").val(),
                endDate:$("#endDate").val()
            };
            service.post2SRV("qCreateQrcodeImg.do", formData, function (data, status) {
                //记录总条数
                $scope.total = data.total;
                //总页数
                $scope.pages = data.pages;
                //数据list
                $scope.List = data.list;
            }, 1000);
        }
        //查询商户信息
        var formData1 = {
            "channelId": service.getUser().channel.channelId
        };
        service.post2SRV("queryChannel.do", null, function (data, status) {
            $scope.merchantList = data;
        }, 1000);

        $scope.addQrcode = function (){
            $state.go("Main.addQrcode");
        };

        $scope.modifyCreateQrcodeImage = function (obj){
            service.setDataMap(obj);
            $state.go("Main.modifyCreateQrcodeImage");
        };

        $scope.goDetail = function (createQISeq,preservation){
            service.setDataMap({"createQISeq":createQISeq,"preservation":preservation});
            $state.go("Main.qrCodeImg");
        }
        $scope.init();
    });
});
