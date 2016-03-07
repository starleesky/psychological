define(function (require) {

	require('js/service/companycity/companyCity');
	require('js/service/companycity/companyManager');
	require('js/filters/filters');
	require('js/service/common/companys');
	require('js/service/common/citys');

	var myApp = angular.module('App');

	myApp.controller('mainCtrl', ['$scope', '$http', 'companyCity', 'companyManager', '$modal',
		function ($scope, $http, companyCity, companyManager, $modal) {

			var citys = $scope.citys = companyCity.getCitys();

			$scope.companyManager = companyManager;

			$scope.filterData = {
				city: '',
				startTime: '',
				endTime: '',
				searchText: ''
			};

			$scope.filter = function () {
				console.log(companyManager);
			};

			companyManager.getCompanys();

			// 分页
			$scope.pagination = {
				currentPage: $scope.companyManager.page,
				totalItems: $scope.companyManager.totalSize,
				pageSize: $scope.companyManager.filter.pageSize
			};

			$scope.pageChanged = function () {
				console.log(pagination);
			};

			$scope.startDate = '';
			$scope.endDate = '';
			$scope.changeDate = function (type) {
				
				var d = $scope[type];
				companyManager.filter[type] = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + (d.getDate());
				companyManager.search();

			};


		  $scope.open = function($event, type) {
		    $event.preventDefault();
		    $event.stopPropagation();

		    $scope[type + 'opened'] = true;
		  };

		  $scope.dateOptions = {
		    formatYear: 'yy',
		    startingDay: 1
		  };

		  $scope.format = 'yyyy-MM-dd';



		  $scope.addNew = function () {
		  	var modal = $modal.open({
		  		templateUrl: angular.path + '/resources/templates/companyCity/addNew.html',
		  		controller: 'addNewCtrl',
		  		backdrop: 'static'
		  	});
		  	modal.result.then(function (data) {
		  		companyManager.companys.unshift(data);
		  	});
		  };

		}

	])
	.controller('addNewCtrl', ['$scope', '$modalInstance', 'companys', 'citys', 'companyCity',
		function ($scope, $modalInstance, companys, citys, companyCity) {

			$scope.companys = companys.getAllCompanys();
			$scope.citys = citys.getAllCitys();

			var data = $scope.data = {
				companyId: 1,
				cityId: 1,
				contactName: '',
				contactPhone: '',
				mandatoryRebate: 0,
				commercialRebate: 0,
				commissionRate: 0,
				saleDiscount: 0,
				address: '',
				isValid: 1,
				giftFlag: 0,
				ecommerceRate: 0,
				discountFlag: 0,
				giftCouponRebate: 0,
				giftCardRebate: 0,
				idcardImgFlag: 0,
				trialSaleDiscount: 0,
				activityName: '',
				activityUrl: '',
				channel: 1
			};

			$scope.submitted = false;
			$scope.submitting = false;
			$scope.invalidActivityName = false;
			$scope.add = function () {
				$scope.submitted = true;
				if ($scope.new_form.$invalid) {
					return;
				}

				var d = angular.copy(data);
				if(d.orderLevel == "" || typeof d.orderLevel=="undefined"){
					d.orderLevel = 0;
				}
				//填写促销url后名称不能为空
				if (d.activityUrl !="" &&  d.activityName == "") {
					$scope.invalidActivityName = true;
					return ;
				}
				// d.saleDiscount = (parseFloat(d.saleDiscount) / 10).toFixed(4);

				if (parseFloat(d.ecommerceRate) < parseFloat(d.saleDiscount)) {
					alert('销售折扣不能大于电子销售折扣率');
					return;
				}
				

				$scope.submitting = true;
				companyCity.create(d)
					.then(function (data) {
						angular.forEach($scope.companys, function (c) {
							if (data.companyId == c.id) {
								data.companyName = c.name;
							}
						});
						angular.forEach($scope.citys, function (c) {
							if (data.cityId == c.cityId) {
								data.cityName = c.cityName;
							}
						});
						$modalInstance.close(data);
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
	]);

});