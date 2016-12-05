define(['app', 'service', 'sysCode','kindeditorAll','kindeditorditor'], function (app) {
    app.controller('materialCtrl', function (service, $scope, $state) {
        $scope.indexList;
        $scope.init = function () {
            $scope.materList = [{
             "img":"","title":"aaaassss"
            },{
                "img":"","title":"bbb"
            }];
        }
        $scope.addMaterial = function(){
            $scope.materList.push({"img":"","title":""});
        };
        $scope.deleteMaterial = function(index){
            $scope.materList.splice(index,1);
        };
        $scope.clickMaterial = function(index){
            $scope.indexList = 'title'+index;
            if($("#div"+index).attr("class").indexOf("materialDiv")>=0){
                return;
            }
            $("div[name=materialDiv]").attr("class","material_imgdiv");
            $("#div"+index).addClass("materialDiv");
            $("input[name=titleContext]").val("");
        };
        $scope.$watch('titleContext',function(scope){
            $("#"+$scope.indexList).text($scope.titleContext);
        });
        $scope.init();
    });
});