define(function (require) {

    require('js/service/system/companycityagency/copAgencyObj');
    require('js/service/system/companycityagency/copAgency');
    require('js/service/companycity/companyCity');
    require('js/filters/filters');
    require('js/service/common/companys');
    require('js/service/common/citys');

    var myApp = angular.module('App');

    myApp.controller('mainCtrl', ['$rootScope', '$scope', '$http', 'companyCity', '$modal', 'copAgencyObj',
        function ($rootScope, $scope, $http, companyCity, $modal, copAgencyObj) {


            var citys = $scope.citys = companyCity.getCitys();
            var vm = $scope.vm = {};
            vm.rel = new Array();
            vm.lists = [];
            vm.rowSpanMap = {};
            $scope.p = {};
            vm.r1 = [];
            var scope = $rootScope.$new();
            vm.temp = {};
            vm.payIdAry = [];

            $scope.copAgencyObj = copAgencyObj;

            $scope.filterData = {
                city: '',
                searchText: ''
            };

            $scope.filter = function () {
                console.log(copAgencyObj);
            };

            $scope.showCompList = function () {
                copAgencyObj.getCompanys()
                    .then(function (data) {
                        vm.r1 = data;
                        var state1 = "已开启";
                        var state0 = "未开启";
                        vm.lists = [];
                        vm.r1.forEach(function (a) {
                            vm.payIdAry = new Array();    // 存放当前车险公司所有代理ID
                            vm.temp = a.agencyCompanys.length > 0 ? a.agencyCompanys : a;
                            //Object.prototype.toString.call(vm.temp) === '[object Array]'
                            if (Object.prototype.toString.call(vm.temp) === '[object Array]') {
                                vm.temp.forEach(function (item, index) {
                                    if (index == 0) {
                                        item.rowSpan = a.agencyCompanys.length;
                                    }
                                    item.companyName = a.companyName;
                                    item.cityNameParam = a.cityName;
                                    item.parentCityId = a.cityId;
                                    item.parentCityName = a.cityName;
                                    item.isDefault = item.isDefault == 1 ? state1 : state0;
                                    vm.payIdAry.push(item.id);
                                    item.payIdAry = vm.payIdAry;
                                    item.parentId = a.companyCityId;
                                    item.parentId_NotKey = a.companyId;
                                    vm.lists.push(item);
                                });
                            }
                            else {
                                //item.parentCityId = a.cityId;
                                var t = {
                                    companyName: a.companyName,
                                    parentCityName: a.cityName,
                                    name: '-',
                                    cityName: '-',
                                    orders: '-',
                                    amounts: '-',
                                    isDefault: '-',
                                    rowSpan: 1,
                                    parentCityId: a.cityId,
                                    payIdAry: vm.payIdAry,
                                    parentId: a.companyCityId
                                }
                                vm.lists.push(t);
                            }
                        })
                    }, function (msg) {
                        alert(msg);
                    });
            }

            // 分页
            $scope.pagination = {
                currentPage: $scope.copAgencyObj.page,
                totalItems: $scope.copAgencyObj.totalSize,
                pageSize: $scope.copAgencyObj.filter.pageSize
            };

            $scope.pageChanged = function () {
                console.log(pagination);
            };

            $scope.open = function ($event, type) {
                $event.preventDefault();
                $event.stopPropagation();

                $scope[type + 'opened'] = true;
            };

            // 关联代付功能     7-2    xz
            $scope.relationFunc = function (param) {
                scope.param = param;
                var modal = $modal.open({
                    templateUrl: 'relatePay.html',
                    controller: 'relateCtrl',
                    size: 'lg',
                    backdrop: 'static',
                    keyboard: 'false',
                    scope: scope
                })
            }

            // 详细信息  支付账户信息  7-2 xz
            $scope.accountInfo = function (param) {
                scope.cityIds = $scope.copAgencyObj.filter.cityId;
                scope.param0 = param;
                var modal = $modal.open({
                    templateUrl: 'accountInfo.html',
                    controller: 'relateCtrl',
                    size: 'lg',
                    backdrop: 'static',
                    keyboard: 'false',
                    scope: scope
                })
            }

            $scope.showCompList();
        }

    ])

        //模态框对应控制器---
        .controller('relateCtrl', ['$rootScope', '$scope', 'copAgency', 'copAgencyObj', '$modalInstance', '$modal','companyCity', function ($rootScope, $scope, copAgency, copAgencyObj, $modalInstance, $modal,companyCity) {
            var vm = $scope.vm = {};
            vm.rel2 = new Array();
            vm.selInfo = new Array();
            vm.r = {};
            vm.payIdAry = $scope.param && $scope.param.payIdAry.length > 0 ? $scope.param.payIdAry : [];
            var scope = $rootScope.$new();
            var state1 = "企业";
            var state0 = "个人";
            $scope.agCompName = new Array();
            if (typeof($scope.param) != undefined && $scope.param != null && $scope.param != '') {
                vm.parentCityName = $scope.param.parentCityName || {};
                vm.parentCityId = $scope.param.parentCityId || {};    // 车险公司城市ID
                vm.compName = $scope.param.companyName || {};
            }

            //vm.parentId = $scope.param.companyCityId || {};        // 车险公司ID
            var t_parentId = $scope.param?$scope.param.parentId:null;
            var t_parentId_NotKey = $scope.param?$scope.param.parentId_NotKey:null;
            var t_parentCityId = $scope.param?$scope.param.parentCityId:null;

            vm.parentId = $scope.param0 ? $scope.param0.parentId : t_parentId;        // 车险公司ID
            vm.parentId_NotKey = $scope.param0 ? $scope.param0.parentId_NotKey : t_parentId_NotKey;
            vm.parentCityId = $scope.param0 ? $scope.param0.parentCityId : t_parentCityId;


            $scope.citys = companyCity.getCitys();

            //获取代付公司名称by CityId
            copAgency.gtPayCompanyByCityId({cityId: vm.parentCityId})
                .success(function (r) {
                    vm.rel2 = r && r.data.length > 0 ? r.data : [];
                    vm.rel2.forEach(function (d) {
                        vm.payIdAry.forEach(function (t) {
                            if (d.id == t) {
                                var checked = {
                                    id: t
                                }
                                d.checked = checked;
                            }
                        })
                    })

                })
                .error(function (r) {
                    console.log("获取代付公司名称Error :" + r);
                })

            // 保存代付公司
            $scope.saveRelPayInfo = function () {
                angular.forEach(vm.rel2, function (data, i) {
                    if (data.checked) {
                        var t = {id: data.id, isDefault: 0};
                        t.isDefault = i == 0 ? 1 : 0;
                        vm.selInfo.push(t);
                    }
                })
                //vm.parentId     -- 车险公司ID
                //vm.parentCityId   -- 车险公司所在城市ID
                //vm.selInfo     -- 选中代付公司ID
                var param = {
                    companyCityId: vm.parentId,
                    companyId: vm.parentId_NotKey,
                    cityId: vm.parentCityId,
                    agencyCompanys: vm.selInfo
                };
                copAgency.saveSelectedPayInfoFunc(JSON.param)
                    .success(function (d) {
                        alert('Success');
                    })
                    .error(function (d) {
                        alert("error " + d);
                    })
                $modalInstance.close();
            }


            // 详细信息  修改账户信息模态框  7-2 xz
            $scope.changeAccountInfoFunc = function (param) {

                scope.p = param;
                scope.parentId = vm.parentId;
                scope.parentCityId = vm.parentCityId;
                scope.parentId_NotKey = vm.parentId_NotKey;
                scope.agCompName = $scope.agCompName? $scope.agCompName: [];
                var modal = $modal.open({
                    templateUrl: 'changeAccountInfo.html',
                    controller: 'changeInfoCtrl',
                    size: 'lg',
                    backdrop: 'static',
                    keyboard: 'false',
                    scope: scope
                })
            }


            //List 账户信息未修改
            copAgency.accountInfoFunc({companyCityId: vm.parentId})
                .success(function (result) {
                    $scope.agCompName =[];
                    vm.r = result || {};
                    $scope.p = vm.r.bankCard || {};
                    $scope.p.bankAccTypeCode = $scope.p.bankAccType;
                    $scope.p.bankAccType = $scope.p.bankAccType == 1 ? state0 : state1;     //账户类型  个人 企业
                    $scope.p.agencyCompanyName = vm.r.agencyCompanyName;

                    $scope.citys.forEach(function(t){
                        if(t.cityId == $scope.p.bankCity){
                            $scope.p.cityNameCodeToString = t.cityName;
                        }
                    })

                    //获取代付公司名称by CityId
                    copAgency.gtPayCompanyByCityId({cityId: $scope.p.bankCity})
                        .success(function (r) {
                            //$scope.agCompName = r && r.data.length > 0 ? r.data : [];
                            $scope.agCompName = r.data;
                        })
                        .error(function (r) {
                            console.log("获取代付公司名称Error :" + r);
                        })


                });

            $scope.cancel = function () {
                $modalInstance.dismiss();
            };

        }
        ])
        .controller('changeInfoCtrl', ['$rootScope', '$scope', 'copAgency', 'copAgencyObj', '$modalInstance','companyCity', '$modal', function ($rootScope, $scope, copAgency, copAgencyObj, $modalInstance,companyCity, $modal) {
            var vm = $scope.vm || {};
            $scope.accountCode =[{id:1,name:'个人'},{id:2,name:'企业'}];


            $scope.citys = companyCity.getCitys();

            //保存已修改账户信息
            $scope.saveAccountInfo = function () {
                var param ={
                    id:$scope.p.id,
                    bankCode:$scope.p.bankCode,
                    bankUser:$scope.p.bankUser,
                    bankNo:$scope.p.bankNo,
                    bankBranch:$scope.p.bankBranch,
                    bankCity:$scope.p.bankCity,
                    bankAccType:$scope.p.bankAccType
                };
                copAgency.saveAccount(param)
                    .success(function (r) {
                        alert(r.message);
                        $modalInstance.close();
                    })
            }
            $scope.cancel = function () {
                $modalInstance.dismiss();
            };

        }])


        });