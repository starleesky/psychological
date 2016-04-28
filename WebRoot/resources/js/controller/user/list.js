define(function (require) {

    require('js/service/commonList');
    require('js/service/common/citys');
    require('js/service/common/address');
    require('js/directives/address');
    require('js/filters/address');

    var http = require('bower_components/angularext/angularExt').http;

    angular.module('App')

        .controller('mainCtrl', ['$scope', '$http', 'commonList', '$modal', 'address',
            function ($scope, $http, commonList, $modal, address) {

                $scope.provinceList = address.getProvinceList();
                var list = $scope.list = commonList;
                list.filter.key = '';
                list.url = "/admin/user/list/getData";

                list.fetch();

                var param = $scope.param = {};

                $scope.vaildScore = false;
                var modal = null, index = null;
                $scope.edit = function (rw, $index) {
                    $scope.user = angular.copy(rw);
                    index = $index;
                    modal = $modal.open({
                        templateUrl: angular.path + '/resources/templates/user/user_edit.html?data=' + new Date(),
                        controller: 'editNewCtrl',
                        backdrop: 'static',
                        resolve: {
                            user: function () {
                                return $scope.user;
                            }
                        }
                    });
                    modal.result.then(function (user) {
                        list.data.data.unshift(user);
                    });

                };

                //打开新增窗口
                $scope.addNew = function () {
                    var modal = $modal.open({
                        templateUrl: angular.path + '/resources/templates/user/user_add.html?data=' + new Date(),
                        controller: 'addNewCtrl',
                        backdrop: 'static',
                        resolve: {
                            scope: function () {
                                return $scope;
                            }
                        }
                    });
                }

                //删除
                $scope.deleteOne = function (data, $index) {
                    if (confirm("确认删除？")) {
                        $http.get(angular.path + "/admin/user/del?id=" + data.id)
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

                //更新
                $scope.updateParam = function () {
                    //$scope.submitted = true;
                    //if ($scope.edit_form.$invalid) {
                    //    return;
                    //}
                    //alert($scope.user.userName);
                    //alert($scope.user.mobile);
                    //alert($scope.user.qq);
                    //return;
                    $http.post(angular.path + "/admin/user/update", $scope.user)
                        .success(function (resp) {
                            if (resp.result) {
                                //if(index>=0&&list.data.data.length>0){
                                //    list.data.data[index] = angular.copy($scope.data);
                                //}
                                list.fetch();
                                modal.close();
                            } else {
                                alert(resp.message);
                            }
                        });
                }

                //取消处理
                $scope.cancel = function () {
                    modal.close();
                }

                $scope.initList = function () {
                    list.fetch();
                }
            }
        ]).controller('addNewCtrl', ['$scope', '$http', '$modalInstance', 'address', 'scope',
        function ($scope, $http, $modalInstance, address, scope) {

            $scope.provinceList = address.getProvinceList();

            //$scope.cityList = address.getCitysByPid();
            $scope.$watch('user.provinceId', function () {
                $scope.user.provinceName = $scope.provinceList[$scope.user.provinceId];
                $scope.citys=address.getCitysByPid($scope.user.provinceId);
            }, true);
            $scope.$watch('user.cityId', function () {
                $scope.user.cityName =$scope.citys[$scope.user.cityId];
            }, true);
            var data = $scope.user = {
                isActivate: 'T'
            };

            $scope.submitting = $scope.submitted = false;

            $scope.add = function () {
                $scope.submitted = true;
                if ($scope.add_form.$invalid) {
                    return;
                }
                $http.post(angular.path + '/admin/user/add', $scope.user)
                    .success(function (resp) {
                        if (resp.result) {
                            scope.initList();
                        } else {
                            $scope.submitting = false;
                            alert("创建失败，请重试");
                        }
                    })
                    .error(function () {
                            $scope.submitting = false;
                            alert("创建失败，请重试");
                        }
                    );

                $modalInstance.close(data);

            };

            $scope.cancel = function () {
                $modalInstance.dismiss();
            };

        }
    ]).controller('editNewCtrl', ['$scope', '$http', '$modalInstance', 'address', 'user',
        function ($scope, $http, $modalInstance, address, user) {

            $scope.user =  angular.copy(user);
            $scope.provinceList = address.getProvinceList();
            console.log($scope.provinceList);
            //$scope.cityList = address.getCitysByPid();
            $scope.$watch('user.provinceId', function () {
                $scope.user.provinceName = $scope.provinceList[$scope.user.provinceId];
                $scope.citys=address.getCitysByPid($scope.user.provinceId);
            }, true);
            $scope.$watch('user.cityId', function () {
                $scope.user.cityName =$scope.citys[$scope.user.cityId];
            }, true);

            //alert($scope.user.provinceName);
            //var data = $scope.user = {
            //    isActivate: 'T'
            //};

            $scope.submitting = $scope.submitted = false;
            //更新
            $scope.updateParam = function () {
                $scope.submitted = true;
                if ($scope.edit_form.$invalid) {
                    return;
                }
                $http.post(angular.path + "/admin/user/update", $scope.user)
                    .success(function (resp) {
                        if (resp.result) {
                            //if(index>=0&&list.data.data.length>0){
                            //    list.data.data[index] = angular.copy($scope.data);
                            //}
                            $modalInstance.close($scope.user);
                        } else {
                            alert(resp.message);
                        }
                    });
            }



            $scope.cancel = function () {
                $modalInstance.dismiss();
            };

        }
    ]);
});