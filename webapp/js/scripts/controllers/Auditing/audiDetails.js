define(['app', 'service', 'sysCode'], function (app) {
    app.controller('audiDetailsCtrl', function (service, $scope, $state) {
        $scope.init = function () {
            console.log(service.getData());
            if(service.getData().result == false){
                $scope.result = false;
            }else{
                $scope.result = true;
            }
            var auditingData = $scope.getAudiData();
            $scope.audiData = auditingData;
            var roleArr = auditingData.roleArr;
            if (roleArr) {
                service.post2SRV("lodeAudiMenu.do", null, function (data, status) {
                    $scope.menuListA = data;
                }, 4000);
                setTimeout(function () {
                    var roleId = roleArr.split(",");
                    angular.forEach(roleId, function (value, index) {
                        document.getElementById(value).checked = true;
                    });
                }, 500);
            }
        };
        $scope.show = function (id) {
            $('#aa' + id).slideToggle(500);
        };


        /**
         * 不需要展示的字段配置
         */
        $scope.notShow = [
            "auditPersonSeq",
            "roleSeq",
            "roleId",
            "redPackSeq",
            "userSeq"
        ];
        $scope.getAudiData = function () {
            var auditingData = angular.fromJson(service.getData().auditingData);
            var returnData = {};
            var notShowStr = $scope.notShow.join(",");
            angular.forEach(auditingData, function (value, key) {
                if (notShowStr.indexOf(key) < 0 && value != null && value != '') {
                    returnData[key] = value;
                }
            });
            return returnData;
        };
        $scope.remote = function (array, obj) {
            var index = $.inArray(obj, array);
            if (index >= 0) {
                array.splice(index, 1);
            }
        };
        $scope.agree = function () {
            var obj = service.getData();
            var formData = angular.fromJson(obj.auditingData);
            formData["auditingTrans"] = obj.auditingTrans;
            formData["auditingSeq"] = obj.auditingSeq;
            service.post2SRV("audiAgree.do", formData, function (data, status) {
                showInfo("复合成功");
                $state.go("Main.CheckQuery");
            }, 4000);
        };
        $scope.refuse = function () {
            var rm = prompt("输入提示","请输入拒绝原因");
            if(rm == null){
                return;
            }
            var obj = service.getData();
            var formData = angular.fromJson(obj.auditingData);
            formData["remarks"] = rm;
            formData["auditingTrans"] = obj.auditingTrans;
            formData["auditingSeq"] = obj.auditingSeq;
            service.post2SRV("audiRefuse.do", formData, function (data, status) {
                showInfo("复合成功");
                $state.go("Main.CheckQuery");
            }, 4000);
        };
        $scope.getAudiData();


        $scope.init();
    });
});