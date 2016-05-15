define(function (require) {

    require('js/service/commonList');
    require('js/service/common/citys');
    require('js/service/common/address');
    require('js/directives/address');
    require('js/filters/address');
    angular.module('App')

        .controller('mainCtrl', ['$scope', '$http', 'commonList', '$modal', 'address',
            function ($scope, $http, commonList, $modal, address) {

                var param = $scope.param = {};
                var company = $scope.company = {};

                var modal = null;
                $scope.company.companyName = angular.companyName;
                $scope.company.id = angular.companyId;
                $scope.company.telephone = angular.telephone;
                $scope.company.fax = angular.fax;
                $scope.company.provinceId = angular.provinceId;
                $scope.company.provinceName = angular.provinceName;
                $scope.company.cityId = angular.cityId;
                $scope.company.cityName = angular.cityName;
                $scope.company.address = angular.address;
                $scope.company.introduction = angular.introduction;

                $scope.openModel = function (data) {
                    $scope.company.auditStatus = data;
                    //if(data==1){
                    //    $scope.title = '认证成功';
                    //}else{
                    //    $scope.title = '认证失败';
                    //}
                    modal = $modal.open({
                        templateUrl: angular.path + '/resources/templates/company/company_audit.html',
                        backdrop: 'static',
                        scope: $scope,
                        size: 'md'
                    });
                };

                $scope.editCompany = function () {
                    modal = $modal.open({
                        templateUrl: angular.path + '/resources/templates/company/company_edit.html',
                        controller: 'editNewCtrl',
                        backdrop: 'static',
                        resolve: {
                            company: function () {
                                return $scope.company;
                            }
                        }
                    });
                };

                $scope.auditCompany = function () {
                    $scope.submitted = true;
                    //if ($scope.edit_car_form.$invalid) {
                    //    return;
                    //}
                    $http.post(angular.path + "/admin/company/update", $scope.company).success(function (resp) {
                        if (resp.result) {
                            window.location.href = angular.path + '/admin/company/list';
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
        ])
        .controller('editNewCtrl', ['$scope', '$http', '$modalInstance', 'address', 'company',
            function ($scope, $http, $modalInstance, address, company) {

                $scope.company = angular.copy(company);
                $scope.provinceList = address.getProvinceList();
                $scope.cityList=address.getCitysByPid($scope.company.provinceId);
                $scope.$watch('company.provinceId', function () {
                    $scope.company.provinceName = $scope.provinceList[$scope.company.provinceId];
                    $scope.cityList = address.getCitysByPid($scope.company.provinceId);
                }, true);
                $scope.$watch('company.cityId', function () {
                    $scope.company.cityName = $scope.cityList[$scope.company.cityId];
                }, true);
                $scope.submitting = $scope.submitted = false;
                //更新
                $scope.updateParam = function () {
                    $scope.submitted = true;
                    if ($scope.edit_form.$invalid) {
                        return;
                    }
                    $http.post(angular.path + "/admin/company/update2", $scope.company)
                        .success(function (resp) {
                            if (resp.result) {
                                window.location.href = angular.path + '/admin/company/getDetail?id='+$scope.company.id;
                                $modalInstance.close($scope.company);
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