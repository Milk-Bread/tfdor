/**
 * Created by pengyuming on 16/10/12.
 */
define(['app', 'service','sysCode'], function (app) {
    app.controller('redEnvelopeMngCtrl', function (service,$scope,$location,$state,$stateParams) {
        //当前页
        $scope.pageNo = 1;
        //每页显示数量
        $scope.pageSize = 10;
        $scope.init = function () {
            console.log(service.getUser());
            var formData = {
                "pageNo":$scope.pageNo,
                "pageSize":$scope.pageSize
                //"channelId" : service.getUser().channel.channelId
            };
            service.post2SRV("queryRedPack.do", formData,function(data,status) {
                //记录总条数
                $scope.total = data.total;
                //总页数
                $scope.pages = data.pages;
                //数据list
                $scope.redPackList = data.list;
            },1000);
        }
        $scope.init();
    });
});