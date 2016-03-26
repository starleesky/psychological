define(function (require) {

    require('js/service/commonList');
    require('js/service/common/citys');
    require('js/service/common/address');
    angular.module('App')

        .controller('mainCtrl', ['$scope', '$http', 'commonList', '$modal',
            function ($scope, $http, commonList, $modal) {

                var list = $scope.list = commonList;
                list.filter.key = '';
                list.url = "/admin/user/list/getData";

                list.fetch();

                var param = $scope.param = {};

                $scope.vaildScore = false;
                var modal = null,index = null;
                $scope.edit = function (rw,$index) {
                    $scope.user = angular.copy(rw);
                    index = $index;
                    modal = $modal.open({
                        templateUrl: angular.path + '/resources/templates/user/user_edit.html?data='+new Date(),
                        backdrop: 'static',
                        scope:$scope,
                        size: 'lg'
                    });
                };

                //打开新增窗口
                $scope.addNew = function(){
                    var modal = $modal.open({
                        templateUrl: angular.path + '/resources/templates/user/user_add.html?data='+new Date(),
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
                $scope.deleteOne = function(data,$index){
                    if(confirm("确认删除？")){
                        $http.get(angular.path+"/admin/user/del?id="+data.id)
                            .success(function(resp){
                                if(resp.result){
                                    alert("删除成功！");
                                    //$scope.list.data.data.splice($index,1);
                                    list.fetch();
                                }else{
                                    alert("删除失败，请重试！");
                                }
                            })
                            .error(function(){
                                alert("删除失败，请重试！");
                            });
                    }
                }

                //更新
                $scope.updateParam = function(){
                    $http.post(angular.path+"/admin/user/update",$scope.user)
                        .success(function(resp){
                            if(resp.result){
                                //if(index>=0&&list.data.data.length>0){
                                //    list.data.data[index] = angular.copy($scope.data);
                                //}
                                list.fetch();
                                modal.close();
                            }else{
                                alert(resp.message);
                            }
                        });
                }

                //取消处理
                $scope.cancel = function () {
                    modal.close();
                }

                $scope.initList = function(){
                    list.fetch();
                }
            }
        ]).controller('addNewCtrl', ['$scope', '$http','$modalInstance','scope',
        function ($scope, $http ,$modalInstance, scope) {

            var data = $scope.user = {
                isActivate:'T'
            };

            $scope.submitting = $scope.submitted = false;

            $scope.add = function () {
                $scope.submitting = true;
                $http.post(angular.path + '/admin/user/add', $scope.user)
                    .success(function (resp) {
                        if(resp.result){
                            scope.initList();
                        }else{
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
    ]);
});