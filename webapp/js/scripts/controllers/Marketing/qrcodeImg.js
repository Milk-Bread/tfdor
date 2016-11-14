/**
 * Created by pengyuming on 16/10/12.
 */
define(['app', 'service','sysCode'], function (app) {
    app.controller('qrCodeImgCtrl', function (service,$scope,$location,$state,$stateParams) {
        //当前页
        $scope.pageNo = 1;
        //每页显示数量
        $scope.pageSize = 10;
        $scope.init = function() {
            var formData = {
                pageNo:$scope.pageNo,
                pageSize:$scope.pageSize,
                createQISeq:service.getData().createQISeq,
                state:$scope.state
            };
            $scope.preservation = service.getData().preservation;
            service.post2SRV("getQrcodeImg.do", formData, function (data, status) {
                //记录总条数
                $scope.total = data.total;
                //总页数
                $scope.pages = data.pages;
                //数据list
                $scope.List = data.list;
            }, 1000);
        };
        $scope.init();
    });
});