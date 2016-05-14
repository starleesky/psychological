define(function (require) {

    require('js/service/commonList');
    //require('js/directives/filter-select');
    angular.module('App')

        .controller('mainCtrl', ['$scope', '$http', 'commonList', '$modal',
            function ($scope, $http, commonList, $modal) {

                var list = $scope.list = commonList;
                list.filter.key = '';
                list.url = "/admin/company/list/getData";

                setTimeout(function () {
                    console.log(list.data);
                }, 2000);

                list.fetch();

                var param = $scope.param = {};

                var modal = null, index = null;
                $scope.edit = function (rw, $index) {
                    $scope.param = rw;
                    index = $index;
                    modal = $modal.open({
                        templateUrl: angular.path + '/resources/templates/system/params/params-modal.html',
                        backdrop: 'static',
                        scope: $scope,
                        size: 'lg'
                    });
                };

                //取消处理
                $scope.cancel = function () {
                    modal.close();
                }

                //更新
                $scope.updateParam = function () {
                    $http.post(angular.path + "/system/param/update", $scope.param).success(function (resp) {
                        if (resp.success) {
                            if (index >= 0 && list.data.length > 0) {
                                list.data[index] = angular.copy($scope.param);
                            }
                            modal.close();
                        } else {
                            alert(resp.message);
                        }
                    });
                }
                //删除
                $scope.deleteOne = function (data, $index) {
                    if (confirm("确认删除？")) {
                        $http.get(angular.path + "/admin/company/del?id=" + data.id)
                            .success(function (resp) {
                                if (resp.result) {
                                    alert("删除成功！");
                                    //$scope.list.data.data.splice($index,1);
                                    list.fetch();
                                } else {
                                    alert("删除失败，请重试！");
                                }
                            })
                            .error(function () {
                                alert("删除失败，请重试！");
                            });
                    }
                }


            }
        ]);
});