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
                $scope.openModel = function (data) {
                    $scope.infomation.status = data;
                    modal = $modal.open({
                        templateUrl: angular.path + '/resources/templates/infomation/infomation_audit.html',
                        backdrop: 'static',
                        scope: $scope,
                        size: 'md'
                    });
                };

                $scope.auditInfomation = function () {
                    $scope.submitted = true;
                    //if ($scope.edit_car_form.$invalid) {
                    //    return;
                    //}
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
        ]);
});