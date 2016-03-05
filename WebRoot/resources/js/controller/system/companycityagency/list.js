define(function (require) {
	
	require('js/service/commonList');
	require('js/service/common/citys');
	require('js/service/common/bank');
	require('js/service/common/address');
	require('js/directives/address');
	require('js/filters/address');
	var http = require('bower_components/angularext/angularExt').http;

	angular.module('App')

	.controller('mainCtrl', ['$scope', 'commonList', 'citys', '$modal',
		function ($scope, commonList, citys, $modal) {

			$scope.cityList = citys.getAllCitys();

			var list = $scope.list = commonList;
			list.filter.companyName = '';
			list.filter.cityId = '';
			list.filter.orderFlag = '';
			list.filter.pageSize = 10;
			list.url = "/system/companycityagency/list/getpage";
			
			list.fetch();

			$scope.showDetail = function (company) {
				$modal.open({
					templateUrl: angular.path + '/resources/templates/system/companycityagency/detail-tmpl.html',
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

			$scope.connectAgency = function (company) {
				$modal.open({
					templateUrl: angular.path + '/resources/templates/system/companycityagency/connect-tmpl.html',
					controller: 'connectCtrl',
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

	.filter('agencyName', [function () {

		var filterFun = function (id, array) {

			if (!array || array.length < 1) {
				return '无';
			}

			for (var i=0; i<array.length; i++) {
				if (array[i].id == id) {
					return array[i].name;
				}
			}

			return '无';

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
							reject('没有银行卡信息');
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

	.controller('detailCtrl', ['$scope', '$modalInstance', 'company', '$modal', 'accompany',
		function ($scope, $modalInstance, company, $modal, accompany) {

			$scope.company = company;

			$scope.created = true;

			if (!company.bankCard) {
				accompany.getDetail(company.companyCityId)
					.then(function (data) {
						angular.extend(company, data);
						$scope.created = true;
					}, function (msg) {
						$scope.created = false;
						// alert(msg);
					});
			}

			$scope.edit = function () {

				var modal = $modal.open({
					templateUrl: angular.path + '/resources/templates/system/companycityagency/edit-detail-tmpl.html',
					controller: 'editDetailCtrl',
					backdrop: 'static',
					size: 'lg',
					resolve: {
						company: function () {
							return company;
						},
						created: function () {
							return $scope.created;
						}
					}
				});

				modal.result.then(function (flag) {
					$scope.created = !!flag;
				});

			};

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

	.controller('connectCtrl', ['$scope', '$modalInstance', 'company', 'accompany',
		function ($scope, $modalInstance, company, accompany) {

			$scope.company = angular.copy(company);

			accompany.getAllAgency()
				.then(function (data) {
					$scope.agencys = angular.copy(data);
					init();
				}, function (msg) {
					alert(msg);
					$modalInstance.dismiss();
				});

			function init() {
				angular.forEach($scope.agencys, function (a) {
					angular.forEach($scope.company.agencyCompanys, function (b) {
						if (a.id == b.id) {
							a.state = 1;
						}
					});
				});
			};

			function getSelectedCompanys() {
				var arr = [];
				angular.forEach($scope.agencys, function (a) {
					if (a.state == 1) {
						a.isValid = 1;
						arr.push(a);
					}
				});
				return arr;
			}

			$scope.submitted = $scope.submitting = false;
			$scope.submit = function () {
				var all = getSelectedCompanys();
				if (all.length > 0) {
					all[0].isDefault = 1;
				} else {
					all[0] = {id: -1};
				}
				accompany.connect($scope.company, all)
					.then(function () {
						company.agencyCompanys = all;
						$modalInstance.dismiss();
					}, function (msg) {
						alert(msg);
					});
			};

			$scope.cancel = function () {
				$modalInstance.dismiss();
			};

		}
	]);

});	