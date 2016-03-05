define(function (require) {
    require('js/service/commonList');
    require('js/service/common/citys');
    require('js/service/companyCityRate/cityRate');
    require('js/service/companyCityRate/compCityRateServ')
    var myApp = angular.module('App');

    myApp.controller('mainCtrl', ['$scope', 'cityRate', 'citys', '$modal','Upload',
        function ($scope, cityRate, citys, $modal,$upload) {
            var vm = $scope.vm = cityRate;
            vm.activeTab ==1;
            $scope.cityList = citys.getAllCitys();
            vm.filter.companyName ='';
            vm.filter.cityId ='';
            vm.filter.orderFlag='';
            vm.filter.pageSize =10;
            vm.url = '/companycity/list/getpage';
            vm.searchFunc();




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
            $scope.upLoadFunc = function(company){
                $modal.open({
                    templateUrl: angular.path + '/resources/templates/companyCityRate/upLoad.html',
                    controller: 'upLoadCtrl',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        company: function () {
                            return company;
                        }
                    }
                });
            }

            $scope.downLoadFunc = function(){
                window.location.href=vm.data.downUrl;
                console.log(vm.data.downUrl);
            }
            $scope.uploading = false;
            $scope.progress = {
                loaded: 100,
                totalSize: 500
            };
            //$scope.commissionRate = 0;
            $scope.setFile = function (files) {
                $scope.file = files[0];
                console.log($scope.file);
            };

        }
    ])


        .controller('detailCtrl', ['$scope', '$modalInstance', '$modal', 'cityRate','citys','company','commonList','compCityRateServ',
            function ($scope, $modalInstance, $modal, cityRate,citys,company,commonList,compCityRateServ) {
                var list = $scope.list = commonList;
                $scope.company = company;
                $scope.showFlg =0;
                var companyCityId = company.companyCityId;
                $scope.companyName = company.companyName;
                $scope.companyCityName = company.cityName;
                list.url = '/companyCityRate/rateDetail';
                list.filter.pageSize =10;
                list.filter.companyCityId = companyCityId;
                list.filter.useCharacterKey ='0';
                list.filter.isInit =1;
                list.filter.page =1;
                list.fetch();

                $scope.tabChange = function(type){
                    list.url = '/companyCityRate/rateDetail';
                    list.filter.pageSize =10;
                    list.filter.page =1;
                    list.filter.companyCityId = companyCityId;
                    list.filter.useCharacterKey = type.toString() ? type.toString():"0";
                    list.fetch();
                }

                $scope.downLoadFunc = function(){
                    window.location.href=list.data.downUrl;
                }

                $scope.showHistory  = function(){
                    $modal.open({
                        templateUrl: angular.path + '/resources/templates/companyCityRate/showHis.html',
                        controller: 'showHisCtrl',
                        backdrop: 'static',
                        size: 'lg',
                        resolve: {
                            company: function () {
                                return list.data.logs;
                            }
                        }
                    });
                }

                $scope.upLoadFunc = function(company){
                    $modal.open({
                        templateUrl: angular.path + '/resources/templates/companyCityRate/upLoad.html',
                        controller: 'upLoadCtrl',
                        backdrop: 'static',
                        size: 'lg',
                        resolve: {
                            company: function () {
                                return companyCityId;
                            }
                        }
                    });
                }

                $scope.cancel = function () {
                    $modalInstance.dismiss();
                };

            }
        ])
        .controller('showHisCtrl',['$scope','$modalInstance','$modal','commonList','company','commonList','compCityRateServ',function($scope,$modalInstance,$modal,commonList,company,commonList,compCityRateServ){
            var vm = $scope.vm =[];
            vm.hisData = company;
            $scope.downLoadFunc = function(url){
                window.location.href=url;
            }
            $scope.cancel = function () {
                $modalInstance.dismiss();
            };
        }])

        .controller('upLoadCtrl',['$scope','$modalInstance','$modal','Upload','company',function($scope,$modalInstance,$modal,$upload,company){
            $scope.setFile = function (files) {
                $scope.file = files[0];
                console.log($scope.file);
            };

            $scope.companyCityIdParam = company.companyCityId?company.companyCityId:company;
            $scope.upload = function(files){

                if ($scope.add_form.$invalid) {
                    return ;
                }
                if (!$scope.file) {
                    alert('请选择文件');
                    return;
                }



                else{
                    var t = $scope.file.name.split('.');
                    var fileType = t[t.length-1];
                    if(fileType.trim() == "xlsx" && $scope.file.size<=(3*1024*1024)){

                        var ext = $scope.file.name.substr($scope.file.name.lastIndexOf('.') + 1, $scope.file.name.length);
                        $scope.uploading = true;
                        $upload.upload({
                            url: angular.path + '/companyCityRate/addRate?companyCityId='+$scope.companyCityIdParam,
                            file: $scope.file,
                            fileFormDataName:'file'
                        })
                            .success(function (resp) {
                                if(resp.success){
                                    alert("上传成功");
                                    $modalInstance.dismiss();
                                } else {
                                    alert(resp.message);
                                }
                                $scope.uploading = false;
                            })
                            .error(function (resp) {
                                alert(resp.message);
                            });
                    }
                    else{
                        alert("限定上传Excel .xlsx格式文件");
                    }

                }

            }

            $scope.cancel = function ($event) {
                $event.preventDefault();
                $modalInstance.dismiss();
            };

        }])
});