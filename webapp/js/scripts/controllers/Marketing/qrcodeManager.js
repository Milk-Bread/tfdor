/**
 * Created by pengyuming on 16/10/12.
 */
define(['app', 'service','sysCode'], function (app) {
    app.controller('qrCodeManager', function (service,$scope,$location,$state,$stateParams) {
        //当前页
        $scope.pageNo = 1;
        //每页显示数量
        $scope.pageSize = 10;
        $scope.channelId = service.getUser().channel.channelId;
        $scope.init = function() {
            var formData = {
                pageNo:$scope.pageNo,
                pageSize:$scope.pageSize
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
        $scope.init();

        $scope.addQrcode = function (){
            $state.go("Main.addQrcode");
        }
    });
});