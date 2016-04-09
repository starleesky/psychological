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

                $scope.auditInfomation = function () {
                    $scope.submitted = true;
                    //if ($scope.edit_car_form.$invalid) {
                    //    return;
                    //}
                    var remark="";
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
        ]);
});