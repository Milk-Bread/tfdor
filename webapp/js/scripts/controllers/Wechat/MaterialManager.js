define(['app', 'service', 'sysCode', 'kindeditorAll', 'kindeditorditor'], function (app) {
    app.controller('materialCtrl', function (service, $scope, $state) {
        $scope.indexList;
        $scope.init = function () {
            $scope.materList = [{
                "img": "", "title": "这里是标题"
            }, {
                "img": "", "title": "这个也是标题"
            }];
        }
        $scope.addMaterial = function () {
            $scope.materList.push({"img": "", "title": ""});
        };
        $scope.deleteMaterial = function (index) {
            if ($("#div" + index).attr("class").indexOf("materialDiv") >= 0) {
                if($("input[name=titleContext]").val() != ""){
                    if(!confirm("是否放弃修改")){
                        return;
                    }else{
                        $("input[name=titleContext]").val("");
                        $scope.materList.splice(index, 1);
                    }
                }else{
                    $("input[name=titleContext]").val("");
                    $scope.materList.splice(index, 1);
                }
            }
        };

        $scope.clickMaterial = function (index) {
            if ($("#div" + index).attr("class").indexOf("materialDiv") >= 0) {
                return;
            }
            if($("input[name=titleContext]").val() != ""){
                if(!confirm("是否放弃修改")){
                    return;
                }else{
                    $("#" + $scope.oldTital.id).text($scope.oldTital.content);
                }
            }
            $scope.oldTital = {"id":"title"+index,"content":$('#title' + index).text()};
            $scope.indexList = 'title' + index;
            $("div[name=materialDiv]").attr("class", "material_imgdiv");
            $("#div" + index).addClass("materialDiv");
            $("input[name=titleContext]").val("");
        };
        $scope.$watch('titleContext', function (scope) {
            if ($scope.count == undefined) {
                $scope.count = 2;
                return;
            } else if ($scope.count > 1) {
                if($scope.indexList == undefined){
                    showError("请选择图文信息");
                    $("input[name=titleContext]").val("");
                    return;
                }
                $("#" + $scope.indexList).text($scope.titleContext);
                if($("input[name=titleContext]").val() == ""){
                    $("#" + $scope.oldTital.id).text($scope.oldTital.content);
                }
            }
        });
        $scope.init();
    });
});