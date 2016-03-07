define(function (require) {

    require('js/service/common/citys');
    require('js/service/companyCityRate/cityRate')

    var myApp = angular.module('App');

    myApp.controller('mainCtrl', ['$scope', 'cityRate', 'citys', '$modal',
        function ($scope, cityRate, citys, $modal) {

            var vm = $scope.vm = cityRate;
            vm.activeTab ==1;

            $scope.cityList = citys.getAllCitys();
            vm.filter.companyName ='';
            vm.filter.cityId ='';
            vm.filter.orderFlag='';
            vm.filter.pageSize =10;
            vm.url = '/companycity/list/getpage';
            vm.searchFunc();
            console.log(vm);
            $scope.showDetail = function (company) {
                $modal.open({
                    templateUrl: angular.path + '/resources/templates/companyCityRate/hisInfo.html',
                    controller: 'detailCtrl',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        company: function () {
                            return company;
                        }
                    }
                });
            };

        }
    ])

        .controller('detailCtrl', ['$scope', '$modalInstance', '$modal', 'cityRate',
            function ($scope, $modalInstance, $modal, cityRate) {

                var vm = $scope.vm = cityRate;
                vm.activeTab ==1;

                $scope.cityList = citys.getAllCitys();
                vm.filter.companyName ='';
                vm.filter.cityId ='';
                vm.filter.orderFlag='';
                vm.filter.pageSize =10;
                vm.url = '/companycity/list/getpage';
                vm.searchFunc();

                $scope.cancel = function () {
                    $modalInstance.dismiss();
                };

            }
        ])



});