define(function (require) {

    require('js/service/commonList');
    angular.module('App')

        .controller('mainCtrl', ['$scope', '$http', 'commonList', '$modal',
            function ($scope, $http, commonList, $modal) {

                var param = $scope.param = {};
                var infomation = $scope.infomation = {};

                var modal = null;
                $scope.infomation.companyName = angular.companyName;
                $scope.infomation.id = angular.companyId;
                $scope.infomation.userId = angular.userId;
                $scope.infomation.isNew = angular.isNew;
                $scope.infomation.brandName = angular.brandName;
                $scope.infomation.modelName = angular.modelName;
                $scope.infomation.catagoryId = angular.catagoryId;
                $scope.infomation.equipmentCondition = angular.equipmentCondition;

                $scope.infomation.procedures = angular.procedures;
                $scope.infomation.src = angular.src;
                $scope.infomation.equipYear = angular.equipYear;
                $scope.infomation.workTime = angular.workTime;
                $scope.infomation.price = angular.price;
                $scope.infomation.serialNum = angular.serialNum;
                $scope.infomation.inStockCode = angular.inStockCode;
                $scope.infomation.equipmentLocation = angular.equipmentLocation;
                $scope.infomation.stockCount = angular.stockCount;
                $scope.infomation.sellCount = angular.sellCount;
                $scope.infomation.remark = angular.remark;

                $scope.openModel = function (data) {
                    $scope.infomation.auditStatus = data;
                    //if(data==1){
                    //    $scope.title = '认证成功';
                    //}else{
                    //    $scope.title = '认证失败';
                    //}
                    modal = $modal.open({
                        templateUrl: angular.path + '/resources/templates/infomation/infomation_audit.html',
                        backdrop: 'static',
                        scope: $scope,
                        size: 'md'
                    });
                };

                $scope.editInfomation = function () {
                    modal = $modal.open({
                        templateUrl: angular.path + '/resources/templates/infomation/infomation_edit.html',
                        controller: 'editNewCtrl',
                        backdrop: 'static',
                        resolve: {
                            infomation: function () {
                                return $scope.infomation;
                            }
                        }
                    });
                };

                $scope.auditInfomation = function () {
                    $scope.submitted = true;
                    //if ($scope.edit_car_form.$invalid) {
                    //    return;
                    //}
                    var remark = "";
                    if ($scope.infomation.remark2 != undefined) {
                        remark += $scope.infomation.remark2;
                    }
                    if ($scope.infomation.remark1 != undefined) {
                        remark += $scope.infomation.remark1;
                    }
                    $scope.infomation.remark = remark;
                    $http.post(angular.path + "/admin/infomation/update", $scope.infomation).success(function (resp) {
                        if (resp.result) {
                            window.location.href = angular.path + '/admin/infomation/list';
                            modal.close();
                        } else {
                            alert(resp.message);
                        }
                    });
                };


                //取消处理
                $scope.cancel = function () {
                    modal.close();
                }
            }
        ]).controller('editNewCtrl', ['$scope', '$http', '$modalInstance', 'infomation',
        function ($scope, $http, $modalInstance, infomation) {

            $scope.infomation = angular.copy(infomation);
            $scope.submitting = $scope.submitted = false;
            //更新
            $scope.updateParam = function () {
                $scope.submitted = true;
                if ($scope.edit_form.$invalid) {
                    return;
                }
                $http.post(angular.path + "/admin/infomation/update2", $scope.infomation)
                    .success(function (resp) {
                        if (resp.result) {
                            window.location.href = angular.path + '/admin/infomation/getDetail?id=' + $scope.infomation.id;
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
    ;
});