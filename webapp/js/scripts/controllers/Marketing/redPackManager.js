/**
 * Created by pengyuming on 16/10/12.
 */
define(['app', 'service', 'sysCode'], function (app) {
    app.controller('redEnvelopeMngCtrl', function (service, $scope, $state) {
        //当前页
        $scope.pageNo = 1;
        //每页显示数量
        $scope.pageSize = 10;
        $scope.loginchannelId = service.getUser().channel.channelId;
        $scope.init = function () {
            if ($scope.merchant != undefined) {
                $scope.channelId = $scope.merchant.channelId
            } else {
                $scope.channelId = "";
            }
            var formData = {
                "pageNo": $scope.pageNo,
                "pageSize": $scope.pageSize,
                "channelId": $scope.channelId
            };
            service.post2SRV("queryRedPack.do", formData, function (data, status) {
                //记录总条数
                $scope.total = data.total;
                //总页数
                $scope.pages = data.pages;
                //数据list
                $scope.redPackList = data.list;
            }, 1000);
        };
        //查询商户信息
        var formData1 = {
            "channelId": service.getUser().channel.channelId
        };

        service.post2SRV("queryChannel.do", null, function (data, status) {
            $scope.merchantList = data;
        }, 1000);

        $scope.modifyRedPack = function (obj) {
            service.setData(obj);
            $state.go("Main.modifyRedPack");
        };

        $scope.addRedPack = function () {
            $state.go("Main.addRedPack");
        };
        $scope.init();
    });
});