/**
 * Created by pengyuming on 16/10/12.
 */
define(['app', 'service','sysCode'], function (app) {
    app.controller('qrCodeManager', function (service,$scope,$location,$state,$stateParams) {
        //当前页
        $scope.pageNo = 1;
        //每页显示数量
        $scope.pageSize = 10;
        $scope.loginchannelId = service.getUser().channel.channelId;
        $scope.channelId = service.getUser().channel.channelId;
        $scope.init = function() {
            if($scope.merchant != undefined){
                $scope.channelId = $scope.merchant.channelId
            }
            var formData = {
                pageNo:$scope.pageNo,
                pageSize:$scope.pageSize,
                channelId:$scope.channelId,
                beginDate:$("#beginDate").val(),
                endDate:$("#endDate").val()
            };
            console.log(formData);
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
        $scope.init();

        $scope.addQrcode = function (){
            $state.go("Main.addQrcode");
        }

        $scope.goDetail = function (createQISeq,preservation){
            service.setData({"createQISeq":createQISeq,"preservation":preservation});
            $state.go("Main.qrCodeImg");
        }
    });
});