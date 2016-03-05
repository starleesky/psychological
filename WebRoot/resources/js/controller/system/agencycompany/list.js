define(function (require) {
	
	require('js/service/commonList');
	require('js/service/common/citys');
	require('js/service/common/bank');
	require('js/service/common/address');
	require('js/directives/address');
	require('js/filters/address');
	var http = require('bower_components/angularext/angularExt').http;

	angular.module('App')

	.controller('mainCtrl', ['$scope', 'commonList', '$modal', 'citys','address',
		function ($scope, commonList, $modal, citys,address) {

			$scope.cityList = citys.getAllCitys();
			var list = $scope.list = commonList;
			list.filter.name = '';
			// list.filter.cityId = '';
			list.url = "/system/agencycompany/list/getdata";
			
			list.fetch();

			// 查看代付公司详细信息
			$scope.showDetail = function (company) {
				var modal = $modal.open({
					templateUrl: angular.path + '/resources/templates/system/agencycompany/detail-tmpl.html',
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

			// 新增代付公司
			$scope.addNew = function () {
				var modal = $modal.open({
					templateUrl: angular.path + '/resources/templates/system/agencycompany/add-company-tmpl.html',
					controller: 'addDetailCtl',
					backdrop: 'static',
					size: 'lg',
					resolve: {}
				});

				modal.result.then(function (company) {
					list.data.data.unshift(company);
				});
			};

		}
	])

	.service('agencyService', ['$http', '$q', function ($http, $q) {

		this.getDetailById = function (id) {
			return $q(function (resolve, reject) {
				$http.get(angular.path + '/system/agencycompany/detail?agencyId=' + id)
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

		this.updateDetail = function (info) {
			return $q(function (resolve, reject) {
				http($http).post(angular.path + '/system/agencycompany/updateBankCard',  info)
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

		this.addNew = function (info) {

			return $q(function (resolve, reject) {
				http($http).post(angular.path + '/system/agencycompany/add', info)
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

	}])

	.controller('detailCtrl', ['$scope', '$modalInstance', 'company', 'agencyService', '$modal',
		function ($scope, $modalInstance, company, agencyService, $modal) {

			$scope.updating = false;
			$scope.company = company;

			if (!company.detail) {
				$scope.updating = true;
				agencyService.getDetailById(company.id)
					.then(function (data) {
						angular.extend(company, data);
						company.detail = true;
						$scope.updating = false;
					}, function (msg) {
						alert(msg);
						$scope.updating = false;
					});
			}

			$scope.edit = function () {
				$modal.open({
					templateUrl: angular.path + '/resources/templates/system/agencycompany/edit-detail-tmpl.html',
					controller: 'editDetailCtl',
					backdrop: 'static',
					size: 'lg',
					resolve: {
						company: function () {
							return company;
						}
					}
				});
			};

			$scope.cancel = function () {
				$modalInstance.dismiss();
			};

		}
	])

	.controller('editDetailCtl', ['$scope', '$modalInstance', 'company', 'agencyService', 'address', 'banks',
		function ($scope, $modalInstance, company, agencyService, address, banks) {

			$scope.proviceList = address.getProvinceList();
			$scope.bankList = banks.getAllBanks();

			$scope.company = angular.copy(company);

			$scope.submitting = $scope.submitted = false;

			$scope.$watch('company.bankCode', function (newVal) {
				if( $scope.bankList[newVal] ){
					$scope.company.bankName = $scope.bankList[newVal].name;
				}
			}, true);

			$scope.submit = function () {
				$scope.submitted = true;
				if ($scope.edit_form.$invalid) {
					return;
				}
				$scope.submitting = true;
				agencyService.updateDetail($scope.company)
					.then(function () {
						angular.extend(company, $scope.company);
						$modalInstance.dismiss();
						$scope.submitting = false;
					}, function (msg) {
						alert(msg);
						$scope.submitting = false;
					});
			};

			$scope.cancel = function () {
				$modalInstance.dismiss();
			};

		}
	])

	.controller('addDetailCtl', ['$scope', '$modalInstance', 'address', 'agencyService', 'banks',
		function ($scope, $modalInstance, address, agencyService, banks) {

			$scope.proviceList = address.getProvinceList();
			$scope.bankList = banks.getAllBanks();

			$scope.company = {
				name: '',
				phone: '',
				address: '',
				cityId: 1,
				provinceId: '4',
				bankName: '',
				bankCode: '1001',
				bankUser: '',
				bankNo: '',
				bankBranch: '',
				bankAccType: 1,
				bankProvinceId: '4',
				bankCityId: 1
			};

			$scope.$watch('company.bankCode', function (newVal) {
				$scope.company.bankName = $scope.bankList[newVal].name;
			}, true);

			$scope.$watch('bankList', function () {
				$scope.company.bankName = $scope.bankList[$scope.company.bankCode].name;				
			}, true);

			$scope.submitting = $scope.submitted = false;

			$scope.submit = function () {
				$scope.submitted = true;
				if ($scope.add_form.$invalid) {
					return;
				}
				var citys = address.getCitysByPid($scope.company.proviceId-0);
				$scope.company.provinceName = $scope.proviceList[$scope.company.proviceId-0];
				$scope.company.bankCity = $scope.proviceList[$scope.company.bankAreaCode]

				$scope.submitting = true;
				$scope.company.cityName=citys[$scope.company.cityId-0];

				agencyService.addNew($scope.company)
					.then(function (id) {
						$scope.company.id = id;
						$modalInstance.close($scope.company);
					}, function (msg) {
						alert(msg);
						$scope.submitting = false;
					});
			};

			$scope.cancel = function () {
				$modalInstance.dismiss();
			};

		}
	]);

});	