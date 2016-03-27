define(function (require) {

    require('js/service/commonList');
    angular.module('App')

        .controller('mainCtrl', ['$scope', '$http', 'commonList', '$modal',
            function ($scope, $http, commonList, $modal) {

                var list = $scope.list = commonList;
                list.filter.key = '';
                list.url = "/admin/notice/list/getData";

                list.fetch();

                var param = $scope.param = {};

                $scope.vaildScore = false;
                var modal = null,index = null;
                $scope.edit = function (rw,$index) {
                    $scope.data = angular.copy(rw);
                    index = $index;
                    modal = $modal.open({
                        templateUrl: angular.path + '/resources/templates/notice/notice_edit.html?data='+new Date(),
                        backdrop: 'static',
                        scope:$scope,
                        size: 'md'
                    });
                };

                //打开新增窗口
                $scope.addNew = function(){
                    var modal = $modal.open({
                        templateUrl: angular.path + '/resources/templates/notice/notice_add.html?data='+new Date(),
                        controller: 'addNewCtrl',
                        backdrop: 'static',
                        size:'md',
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
                        $http.get(angular.path+"/admin/notice/del?id="+data.id)
                            .success(function(resp){
                                if(resp.result){
                                    alert("删除成功！");
                                    //$scope.list.data.splice($index,1);
                                    $scope.list.fetch();
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
                    $http.post(angular.path+"/admin/notice/update",$scope.data)
                        .success(function(resp){
                            if(resp.result){
                                list.data[index] = angular.copy($scope.data);
                                //alert(list.data.data.length);
                                $scope.list.fetch();
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

            var data = $scope.data = {
                insTypeId:0
            };

            $scope.add = function () {

                $http.post(angular.path + '/admin/notice/add', $scope.data)
                    .success(function (resp) {
                        if(resp.result){
                            scope.initList();
                        }else{
                            alert("创建失败，请重试");
                        }
                    })
                    .error(function () {
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