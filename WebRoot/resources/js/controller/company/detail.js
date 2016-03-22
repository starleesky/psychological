define(function (require) {

    require('js/service/commonList');
    angular.module('App')

        .controller('mainCtrl', ['$scope', '$http', 'commonList', '$modal',
            function ($scope, $http, commonList, $modal) {

                var param = $scope.param = {};
                var company = $scope.company = {};

                var modal = null;
                $scope.company.companyName = angular.companyName;
                $scope.company.id = angular.companyId;
                $scope.openModel = function (data) {
                    $scope.company.status = data;
                    modal = $modal.open({
                        templateUrl: angular.path + '/resources/templates/company/company_audit.html',
                        backdrop: 'static',
                        scope: $scope,
                        size: 'md'
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
        ]);
});