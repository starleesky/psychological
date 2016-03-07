define(function (require) {

    require('js/service/commonList');
    require('js/service/common/citys');
    require('js/service/common/bank');
    require('js/service/common/address');
    require('js/directives/address');
    require('js/filters/address');
    require('js/service/common/activeCompany');
    require('js/directives/citys-list');

    var http = require('bower_components/angularext/angularExt').http;

    angular.module('App')

        .controller('mainCtrl', ['$scope', 'commonList', 'citys', '$modal',
            function ($scope, commonList, citys, $modal) {

                $scope.cityList = citys.getAllCitys();

                var list = $scope.list = commonList;
                // list.filter.companyName = '';
                // list.filter.cityId = '';
                // list.filter.orderFlag = '';
                list.filter.pageSize = 20;
                list.filter.condition = {
                    name: '',
                    cityId: ''
                };
                list.url = "/system/user/userList/getdata";

                list.fetch();
                $scope.testUserList =[
                    {
                        'userId':110,
                        "userName": "user1",
                        'flower':'Disay1',
                        'ownToCityId':1,
                        'ownToCityName':'杭州',
                        'cities':[
                            {
                                'citiesId':1,
                                'citiesName':'杭州'
                            },{
                                'citiesId':2,
                                'citiesName':'北京'
                            },{
                                'citiesId':3,
                                'citiesName':'上海'
                            },{
                                'citiesId':4,
                                'citiesName':'深圳'
                            }],
                        'telPhone':15326303385,
                        'state':1,
                        'email':'hello000@163.com',
                        'relateComp':''
                    },{
                        'userId':111,
                        "userName": "user2",
                        'flower':'Tom',
                        'ownToCityId':3,
                        'ownToCityName':'上海',
                        'cities':[{
                                'citiesId':3,
                                'citiesName':'上海'
                            },{
                                'citiesId':4,
                                'citiesName':'深圳'
                            }],
                        'telPhone':15803534441,
                        'state':0,
                        'email':'world@163.com'

                    },{
                        'userId':112,
                        "userName": "user3",
                        'flower':'Jason Bourne',
                        'ownToCityId':4,
                        'ownToCityName':'深圳',
                        'cities':[
                            {
                                'citiesId':1,
                                'citiesName':'杭州'
                            },{
                                'citiesId':4,
                                'citiesName':'深圳'
                            }],
                        'telPhone':13279328335,
                        'state':1

                    },{
                        'userId':113,
                        "userName": "user4",
                        'flower':'Benjamin',
                        'ownToCityId':4,
                        'ownToCityName':'深圳',
                        'cities':[
                            {
                                'citiesId':1,
                                'citiesName':'杭州'
                            },{
                                'citiesId':2,
                                'citiesName':'北京'
                            },{
                                'citiesId':3,
                                'citiesName':'上海'
                            },{
                                'citiesId':4,
                                'citiesName':'深圳'
                            }],
                        'telPhone':13488156869,
                        'state':''

                    },{
                        'userId':114,

                        "userName": "user5",
                        'flower':'Tracy',
                        'ownToCityId':3,
                        'ownToCityName':'上海',
                        'cities':[
                            {
                                'citiesId':1,
                                'citiesName':'杭州'
                            },{
                                'citiesId':2,
                                'citiesName':'北京'
                            },{
                                'citiesId':3,
                                'citiesName':'上海'
                            },{
                                'citiesId':4,
                                'citiesName':'深圳'
                            }],
                        'telPhone':15349476455,
                        'state':0

                    },{
                        'userId':115,

                        "userName": "user6",
                        'flower':'Kobe',
                        'ownToCityId':2,
                        'ownToCityName':'北京',
                        'cities':[
                            {
                                'citiesId':1,
                                'citiesName':'杭州'
                            },{
                                'citiesId':2,
                                'citiesName':'北京'
                            },{
                                'citiesId':3,
                                'citiesName':'上海'
                            },{
                                'citiesId':4,
                                'citiesName':'深圳'
                            }],
                        'telPhone':13279328335,
                        'state':0

                    }];


                $scope.showDetail = function (user) {
                    $modal.open({
                        templateUrl: angular.path + '/resources/templates/system/user/detail-tmpl.html',
                        controller: 'detailCtrl',
                        backdrop: 'static',
                        size: 'lg',
                        resolve: {
                            user: function () {
                                return user;
                            }
                        }
                    });
                };

                $scope.connectAgency = function (user) {
                    $modal.open({
                        templateUrl: angular.path + '/resources/templates/system/user/connect-tmpl.html',
                        controller: 'connectCtrl',
                        backdrop: 'static',
                        size: 'lg',
                        resolve: {
                            user: function () {
                                return user;
                            }
                        }
                    });
                };

            }
        ])

        .filter('agencyName', [function () {

            var filterFun = function (id, array) {

                if (!array || array.length < 1) {
                    return '��';
                }

                for (var i=0; i<array.length; i++) {
                    if (array[i].id == id) {
                        return array[i].name;
                    }
                }

                return '��';

            };

            return filterFun;

        }])

        .service('accompany', ['$http', '$q', function ($http, $q) {

            var agencys;

            this.getDetail = function (id) {
                return $q(function (resolve, reject) {
                    http($http).post(angular.path + '/system/companycityagency/getBankCardInfo', {companyCityId: id})
                        .success(function (resp) {
                            if (!resp || !resp.bankCard) {
                                reject('û�����п���Ϣ');
                            } else {
                                resolve(resp);
                            }
                        })
                        .error(function (resp) {
                            reject(resp.message);
                        });
                });
            };

            this.editDetail = function (company) {
                var data = {
                    companyCityId: company.companyCityId,
                    agencyCompanyId: company.agencyCompanyId
                };
                angular.extend(data, company.bankCard);
                return $q(function (resolve, reject) {
                    http($http).post(angular.path + '/system/companycityagency/updateBankinfo', data)
                        .success(function (resp) {
                            if (resp.success) {
                                resolve();
                            } else {
                                reject(resp.message);
                            }
                        })
                        .error(function (resp) {
                            reject(resp.message);
                        });
                });
            };

            this.getAllAgency = function () {
                return $q(function (resolve, reject) {

                    if (agencys) {
                        resolve(agencys);
                    } else {
                        $http.get(angular.path + '/system/companycityagency/allagencycompany')
                            .success(function (resp) {
                                if (resp.success) {
                                    agencys = resp.data;
                                    resolve(agencys);
                                } else {
                                    reject(resp.message);
                                }
                            })
                            .error(function (resp) {
                                reject(resp.message);
                            });
                    }

                });
            };

            this.addBank = function (company) {
                var data = angular.extend({companyCityId: company.companyCityId}, company.bankCard);
                return $q(function (resolve, reject) {
                    http($http).post(angular.path + '/system/companycityagency/addBankInfo', data)
                        .success(function (resp) {
                            if (resp.success) {
                                resolve(resp.data);
                            } else {
                                reject(resp.message);
                            }
                        })
                        .error(function (resp) {
                            reject(resp.message);
                        });
                });
            };

            this.connect = function (company, list) {

                var data = {
                    companyCityId: company.companyCityId,
                    companyId: company.companyId,
                    cityId: company.cityId,
                    agencyCompanys: list
                };

                return $q(function (resolve, reject) {
                    $http.post(angular.path + '/system/companycityagency/associatedAgencyCompany', data)
                        .success(function (resp) {
                            if (resp.success) {
                                resolve();
                            } else {
                                reject(resp.message);
                            }
                        })
                        .error(function (resp) {
                            reject(resp.message);
                        });
                });

            };

        }])

        .controller('detailCtrl', ['$scope', '$modalInstance', 'user', '$modal', 'accompany',
            function ($scope, $modalInstance, user, $modal, accompany) {

                $scope.user = user;

                $scope.cancel = function () {
                    $modalInstance.dismiss();
                };

            }
        ])

        .controller('editDetailCtrl', ['$scope', '$modalInstance', 'company', 'accompany', 'address', 'created', 'banks',
            function ($scope, $modalInstance, company, accompany, address, created, banks) {

                $scope.company = angular.copy(company);
                $scope.proviceList = address.getProvinceList();
                $scope.created = created;
                $scope.bankList = banks.getAllBanks();

                if (!created) {
                    $scope.company.bankCard = {
                        bankCode: '1001',
                        bankName: '',
                        bankUser: '',
                        bankNo: '',
                        bankBranch: '',
                        bankProvinceId: 4,
                        bankCity: '',
                        bankCityId: 1,
                        bankAccType: '1'
                    };
                }

                $scope.$watch('company.bankCard.bankCode', function (newVal) {
                    // console.log(newVal);
                    $scope.company.bankCard.bankName = $scope.bankList[newVal].name;
                }, true);

                $scope.$watch('bankList', function (newVal) {
                    // console.log(newVal);
                    $scope.company.bankCard.bankName = $scope.bankList[$scope.company.bankCard.bankCode].name;
                }, true);

                $scope.submitted = $scope.submitting = false;
                $scope.submit = function () {
                    $scope.submitted = true;
                    if ($scope.edit_form.$invalid) {
                        return;
                    }
                    $scope.company.bankCard.agencyCompanyId = $scope.company.agencyCompanyId;
                    $scope.submitting = true;
                    if (created) {
                        accompany.editDetail($scope.company)
                            .then(function () {
                                angular.extend(company, $scope.company);
                                $modalInstance.dismiss();
                            }, function (msg) {
                                alert(msg);
                                $scope.submitting = false;
                            });
                    } else {
                        accompany.addBank($scope.company)
                            .then(function (id) {
                                $scope.company.bankCard.id = id;
                                angular.extend(company, $scope.company);
                                $modalInstance.close(true);
                            }, function (msg) {
                                alert(msg);
                                $scope.submitting = false;
                            });
                    }

                };

                $scope.cancel = function () {
                    $modalInstance.dismiss();
                };

            }
        ])

        .controller('connectCtrl', ['$scope', '$http', '$modalInstance', 'user', 'accompany','commonList',
            function ($scope, $http, $modalInstance, user, accompany, commonList) {
                $scope.user = angular.copy(user);

                var list = $scope.list = {
                    cityId: '',
                    selectedAll: false
                }

                $scope.selected = [];
                $scope.cityList = [];
                $scope.companyList = [];
                
                // 获取城市和公司
                $http.post( angular.path + '/system/user/companySet/save/getdata', {
                        userId: $scope.user.userId,
                        cities: $scope.user.chargeCityIds
                    })
                    .success(function (resp) {
                        if( resp && resp.data ){
                            angular.forEach( resp.data.userCityCompanys, function (c) {
                                $scope.cityList.push(c);
                            });
                            $scope.companyList = $scope.cityList;
                        }
                    })
                    .error(function (resp) {
                        alert(resp.message);
                    });


                $scope.textAlert = 'No Records!';

                // 单个全选状态
                $scope.isSelectAll = $scope.choseCity = function () {
                    var len = 0,
                        allLen = 0;

                    angular.forEach( $scope.companyList, function( obj, index ){
                        
                        if( list.cityId == '' || list.cityId == obj.cityId ){
                            
                            angular.forEach( obj.companys, function( value, key ){
                                if( value.choose == 1 ){
                                    len++;
                                }
                                allLen++;
                            })
                        }
                    })
                    // 全选状态
                    if( len == allLen ){
                        list.selectedAll = true;
                    }else{
                        list.selectedAll = false;
                    }
                };
                // 全选操作
                $scope.selectAll = function( $event ){
                    var checkbox = $event.target;

                    angular.forEach( $scope.companyList, function( obj, index ){
                        
                        if( list.cityId == '' || list.cityId == obj.cityId ){
                            
                            angular.forEach( obj.companys, function( value, key ){
                                var checked = checkbox.checked ? 1 : 0;
                                value.choose = checked;
                            })
                        }
                    })
                }

                $scope.submitted = $scope.submitting = false;

                $scope.submit = function () {
                    // 生产保存数据格式
                    var param = {
                        userId: $scope.user.userId,
                        cityCompanyInfo: []
                    };

                    angular.forEach( $scope.companyList, function(t){
                        var obj = {
                            cityId: t.cityId,
                            companys: []
                        }
                        angular.forEach( t.companys, function(p){
                            if( p.choose == 1 ){
                                obj.companys.push( p );
                            }
                        })
                        if( obj.companys.length !== 0 )
                            param.cityCompanyInfo.push( obj )
                    })

                    $http.post( angular.path + '/system/user/companySet/save/do', param )
                        .success(function (resp) {
                            if( resp.success == true ){
                                // 隐藏dialog
                                $modalInstance.dismiss();
                                alert('保存成功！');
                            }
                        })
                        .error(function () {
                            alert('保存失败！')
                        });
                };

                $scope.cancel = function () {
                    $modalInstance.dismiss();
                };

            }
        ]);

});