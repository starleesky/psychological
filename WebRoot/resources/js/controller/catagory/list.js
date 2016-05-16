define(function (require) {

    require('js/service/commonList');
    require('js/service/common/citys');
    require('js/service/common/address');
    require('js/directives/address');
    require('js/filters/address');
    require('js/service/common/uploadFileHandler');
    var http = require('bower_components/angularext/angularExt').http;

    angular.module('App')

        .controller('mainCtrl', ['$scope', '$http', 'commonList', '$modal', 'address', 'Upload',
            function ($scope, $http, commonList, $modal, address, $upload) {

                $scope.provinceList = address.getProvinceList();
                var list = $scope.list = commonList;
                list.filter.key = '';
                list.url = "/admin/catagory/list/getData";

                list.fetch();

                var param = $scope.param = {};

                $scope.vaildScore = false;
                var modal = null, index = null;
                $scope.edit = function (rw, $index) {
                    $scope.user = angular.copy(rw);
                    index = $index;
                    modal = $modal.open({
                        templateUrl: angular.path + '/resources/templates/catagory/catagory_edit.html?data=' + new Date(),
                        backdrop: 'static',
                        scope: $scope,
                        size: 'lg',
                        resolve: {
                            user: function () {
                                return $scope.user;
                            }
                        }
                    });
                };

                //打开新增窗口
                $scope.addNew = function (compnay) {
                    $modal.open({
                        templateUrl: angular.path + '/resources/templates/companyCityRate/upLoad.html',
                        controller: 'upLoadCtrl',
                        backdrop: 'static',
                        size: 'lg',
                        resolve: {
                            company: function () {
                                return $scope.user;
                            }
                        }
                    });
                }

                $scope.upLoadFunc = function () {
                    $modal.open({
                        templateUrl: angular.path + '/resources/templates/catagory/upLoad.html',
                        controller: 'upLoadCtrl',
                        backdrop: 'static',
                        size: 'lg',
                        resolve: {
                            company: function () {
                                return "";
                            }
                        }
                    });
                }


                //删除
                $scope.deleteOne = function (data, $index) {
                    if (confirm("确认删除？")) {
                        $http.get(angular.path + "/admin/catagory/del?id=" + data.id)
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
                    $http.post(angular.path + "/admin/catagory/update", $scope.user)
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

                $scope.add = function () {
                    $scope.user = {};
                    modal = $modal.open({
                        templateUrl: angular.path + '/resources/templates/catagory/catagory_add.html?data=' + new Date(),
                        backdrop: 'static',
                        scope: $scope,
                        size: 'lg',
                        resolve: {
                            user: function () {
                                return $scope.user;
                            }
                        }
                    });
                };

                $scope.addParam = function () {
                    $http.post(angular.path + "/admin/catagory/add", $scope.user)
                        .success(function (resp) {
                            if (resp.result) {
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
        ])
        .controller('upLoadCtrl', ['$scope', '$modalInstance', '$modal', 'Upload', 'company', 'uploadFileHandler', function ($scope, $modalInstance, $modal, $upload, company, UploadFileHandler) {
            $scope.setFile = function (files) {
                $scope.file = files[0];
                console.log($scope.file);
            };

            var uploadHandler = new UploadFileHandler($upload);

            $scope.companyCityIdParam = company.companyCityId ? company.companyCityId : company;
            $scope.upload = function (files) {

                if ($scope.add_form.$invalid) {
                    return;
                }
                if (!$scope.file) {
                    alert('请选择文件');
                    return;
                }


                else {
                    var t = $scope.file.name.split('.');
                    var fileType = t[t.length - 1];
                    if (fileType.trim() == "xls" && $scope.file.size <= (3 * 1024 * 1024)) {

                        var ext = $scope.file.name.substr($scope.file.name.lastIndexOf('.') + 1, $scope.file.name.length);
                        $scope.uploading = true;

                        uploadHandler.upload($scope.file, {
                            url: angular.path + '/catagory/uploadExcle',
                            success: function (resp, config) {
                                if (resp.result) {
                                    alert(2);
                                    alert("上传成功");
                                    $modalInstance.dismiss();
                                    alert(1);
                                } else {
                                    alert(resp.message);
                                }
                                $scope.uploading = false;
                            },
                            error: function (resp, config) {
                                alert(resp.message);
                                $scope.uploading = false;
                            }
                        });
                    }
                    else {
                        alert("限定上传Excel .xlsx格式文件");
                    }

                }

            }

            $scope.cancel = function ($event) {
                $event.preventDefault();
                $modalInstance.dismiss();
            };

        }]);
});