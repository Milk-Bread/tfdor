define(['app','service'], function (app) {
    "use strict";
    app.directive('paging', function () {
        return {
            replace: true,   //template会覆盖掉自定义标签
            restrict: 'AE', //指令
            template: '<div class="text-right" style="width:95%;" ng-if="isNone">'
            + '<ul class="pagination" style="margin:0px 0px 50px 0px;">'
            + '<li class="disabled"><a href="javascript:void(0);">总共：{{pages}}页</a></li>'
            + '<li class="disabled"><a href="javascript:void(0);">{{total}}条记录</a></li>'
            + '<li ng-click="pageChange(1)" ng-class="{disabled:pageNo===1}"><a href="javascript:void(0);">首页</a></li>'
            + '<li ng-click="pageChange(pageNo - 1 > 0 ? pageNo - 1 : 1)" ng-class="{disabled:pageNo===1}"><a href="javascript:void(0);">上一页</a></li>'
            + '<li ng-repeat="pagenum in pagenums" ng-click="pageChange(pagenum)" ng-class="{active:pageNo===pagenum}"><a href="javascript:void(0);"  style="width:33px;text-align: center;padding-left:0px;padding-right: 0px; ">{{pagenum}}</a></li>'
            + '<li ng-click="pageChange(pageNo + 1 <= pages ? pageNo + 1 : pages)"  ng-class="{disabled:pageNo===pages}"><a href="javascript:void(0);">下一页</a></li>'
            + '<li ng-click="pageChange(pages)" ng-class="{disabled:pageNo===pages}"><a href="javascript:void(0);">尾页</a></li>'
            + '</ul>'
            + '</div>', //自定义标签要显示的内容
            scope: {
                pages: '=',
                total: '=',
                pageNo: '=',
                pageSize: '@',
                doIt: '&'
            },
            link: function (scope, elem, attrs) {
                var one = 1;
                scope.isNone = false;
                var visiblePageSize = Number(angular.isDefined(scope.display) ? scope.display : 7);
                if (visiblePageSize % 2 == 0) {
                    visiblePageSize++;
                }
                scope.pageChange = function (page) {
                    page = Number(page);
                    if (page >= 1 && page <= scope.pages) {
                        scope.pageNo = page;
                    } else {
                        scope.pageNo = 1;
                    }
                }
                function build() {
                    if (angular.isDefined(scope.pages) && scope.pages > 0) {
                        scope.isNone = true;
                    } else if (scope.isNone != false) {
                        showError("温馨提示：查无记录");
                        scope.isNone = false;
                    }
                    var low, high, v;
                    scope.pagenums = [];
                    if (scope.pages == 0) {
                        return;
                    }
                    if (scope.pageNo > scope.pages) {
                        scope.pageNo = 1;
                    }
                    if (scope.pages <= visiblePageSize) {
                        low = 1;
                        high = scope.pages;
                    } else {
                        v = Math.ceil(visiblePageSize / 2) - 1;
                        low = Math.max(scope.pageNo - v, 1);
                        high = Math.min(low + visiblePageSize - 1, scope.pages);
                        if (scope.pages - high < v) {
                            low = high - visiblePageSize + 1;
                        }
                    }
                    for (; low <= high; low++) {
                        scope.pagenums.push(low);
                    }
                    if (one > 2) {
                        scope.doIt();
                    } else {
                        one++;
                    }
                }

                scope.$watch('pages+pageNo', function () {
                    build();
                });
            }
        }
    });
    app.directive('auditing',function(service){
        return{
            replace: true,   //template会覆盖掉自定义标签
            restrict: 'AE', //指令
            template: '<select class="form-control btn-sm seStyle" ng-model="person" ng-options="x.userName for x in auditPerson">'
                        +'<option value="">选择复合人</option>'
                        +'</select>',
            scope: {
                person:'&'
            },
            link: function (scope, elem, attrs) {
                //查询复合人
                service.post2SRV("queryAuditPerson.do",null,function(data, status){
                    scope.auditPerson = data;
                },4000);
            }
        }
    });
});
