define(function (require) {

    require('js/service/commonList');
    //require('js/directives/filter-select');

    require('js/service/common/uploadFileHandler');

    angular.module('App')

        .controller('mainCtrl', ['$scope', '$http', 'commonList', '$modal',
            function ($scope, $http, commonList, $modal) {

                var list = $scope.list = commonList;
                list.filter.key = '';
                list.url = "/admin/infomation/list/getData";

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

                $scope.upLoadFunc = function () {
                    $modal.open({
                        templateUrl: angular.path + '/resources/templates/infomation/upLoad.html',
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
                        $http.get(angular.path + "/admin/infomation/del?id=" + data.id)
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
                            url: angular.path + '/admin/infomation/uploadExcle',
                            success: function (resp, config) {
                                alert(resp.result);
                                if (resp.result) {
                                    alert("上传成功");
                                    $modalInstance.dismiss();
                                } else {
                                    alert(resp.message);
                                }
                                $scope.uploading = false;
                            },
                            error: function (resp, config) {
                                alert(resp.message);
                                $scope.uploading = false;
                                $modalInstance.dismiss();
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