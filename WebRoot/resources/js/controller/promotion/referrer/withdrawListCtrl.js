define(function (require) {
	
	require('js/service/commonList');
	require('js/service/common/citys');
	require('js/service/promotion/referrer');
	require('js/service/common/address');
	require('js/service/promotion/referrer');
	var http = require('bower_components/angularext/angularExt').http;

	angular.module('App')

	.filter('commission', function () {

		return function (data, flag) {
			if (flag == 1 || flag == 2) {
				if (data.commissionType == flag) {
					return (parseFloat(data.commission)/100).toFixed(2);
				}
				return '-';
			} else if (flag < 6){
				if (parseInt(data.commissionType) > 2 && parseInt(data.commissionType) < 6) {
					return (parseFloat(data.commission)/100).toFixed(2);
				}
				return '-';
			} else {
				if (parseInt(data.commissionType) == 6) {
					return (parseFloat(data.commission)/100).toFixed(2);
				}
				return '-';
			}
		};

	})
	.filter('filterType', function () {

		return function (input) {
			switch (input) {
				case 1:
					return '返佣';
				case 2:
					return '返现';
				case 3:
					return '结算中';
				case 4:
					return '结算成功';
				case 5:
					return '结算失败';
				case 6:
					return '退款';
			}
		};

	})

	.controller('mainCtrl', ['$scope', 'commonList', 'citys', '$modal', 'referrer', '$http',
		function ($scope, commonList, citys, $modal, referrer, $http) {

			$scope.cityList = citys.getAllCitys();

			var list = $scope.list = commonList;
			list.filter.cityId = '';
			list.filter.phoneOrRecommandCode = '';
			list.url = "/promotion/referrer/withdraw/list/getdata";
			
			list.fetch();

			// 修改推荐人状态
			$scope.toggleStatus = function (r) {

				var status = 1 - r.validStatus;
				referrer.update({
					referrerId: r.referrerId,
					valid: status
				})
					.then(function () {
						r.validStatus = status;
					}, function (msg) {
						alert(msg);
					});

			};

			$scope.refund = function (draw) {
				if (!confirm('确定重新打款？')) {
					return;
				}
				draw.withdrawStatus = 0;
				http($http).post(angular.path + '/promotion/referrer/settle/resettle/do', {withdrawId: draw.id})
					.success(function (resp) {
						if (resp.success) {
							draw.remark = resp.data;
							draw.withdrawStatus = draw.remark ? 2 : 0;
						} else {
							draw.remark = resp.message;
							draw.withdrawStatus = 2;
						}
					})
					.error(function (resp) {
						draw.withdrawStatus = 2;
					});
			};
			// 根据手机号查找用户
			$scope.findUser = function () {
				$modal.open({
					templateUrl: angular.path + '/resources/templates/promotion/find-user-modal.html',
					controller: 'findUserCtrl',
					backdrop: 'static',
					size: 'lg'
				});
			};

		}
	])
	.service('DetailHelper', ['$http', '$q', '$filter',
		function ($http, $q, $filter) {

			Helper.prototype.fetch = function() {
				var self = this;
				self.filter.startDate = $filter('date')(self.filter.startDate, 'yyyy-MM-dd');
				self.filter.endDate = $filter('date')(self.filter.endDate, 'yyyy-MM-dd');
				self.fetching = true;
				http($http).post(this.url, this.filter)
					.success(function (resp) {
						if (resp.success) {
							self.data = resp.data.data.data;
							self.result = resp;
							self.fetching = false;
						} else {
							self.result = resp;
							self.fetching = false;
						}
					})
					.error(function (resp) {
						self.result = resp;
					});
			};

			function Helper(url, id) {
				this.url = url;
				this.id = id;
				this.filter = {
					refId: id,
					pageSize: 10,
					page: 1,
					startDate: '',
					endDate: ''
				};
				this.fetch();
			}

			return Helper;

		}
	])
	.controller('referrerDetailCtrl', ['$scope', '$modalInstance', 'refId', 'DetailHelper', '$http',
		function ($scope, $modalInstance, refId, DetailHelper, $http) {

			var tabs = $scope.tabs = {
				'yjmx': {
					store: new DetailHelper(angular.path + '/promotion/referrer/commission/detail/getdata', refId),
					searchHolder: '输入车牌号/手机号'
				},
				'kymx': {
					store: new DetailHelper(angular.path + '/promotion/referrer/commission/detail/getrefund', refId),
					searchHolder: '车牌号/手机号'
				},
				'txmx': {
					store: new DetailHelper(angular.path + '/promotion/referrer/commission/detail/getwithdraw', refId),
					searchHolder: '流水号'
				}
			};

			$scope.store = tabs.yjmx;
			$scope.tabName = 'yjmx';

			$scope.timeFlag = {
				start: false,
				end: false
			};
			$scope.toggleCal = function (e, flag) {
				e.preventDefault();
				e.stopPropagation();
				$scope.timeFlag[flag] = !$scope.timeFlag[flag];
			};

			$scope.refund = function (draw) {
				if (!confirm('确定重新打款？')) {
					return;
				}
				draw.withdrawStatus = 0;
				http($http).post(angular.path + '/promotion/referrer/settle/resettle/do', {withdrawId: draw.id})
					.success(function (resp) {
						if (resp.success) {
							draw.remark = resp.data;
							draw.withdrawStatus = draw.remark ? 2 : 0;
						} else {
							draw.remark = resp.message;
							draw.withdrawStatus = 2;
						}
					})
					.error(function (resp) {
						draw.withdrawStatus = 2;
					});
			};

			$scope.switchTab = function (key) {
				$scope.tabName = key;
				$scope.store = tabs[key].store;
				console.log($scope.store);
			};

			$scope.cancel = function () {
				$modalInstance.dismiss();
			};

		}
	])
	.controller('findUserCtrl', ['$scope', '$modalInstance', '$http',
		function ($scope, $modalInstance, $http) {

			$scope.phoneNo = '';
			$scope.user = '';

			$scope.searching = false;
			$scope.search = function () {
				if (!$scope.phoneNo) {
					alert('请输入手机号');
				}
				$scope.user = '';
				$scope.searching = true;
				$http.post(angular.path + '/promotion/referrer/cashcoupon/query/getdata?phone=' + $scope.phoneNo)
					.success(function (resp) {
						if (resp.success && resp.data) {
							$scope.user = resp.data;
						} else {
							alert(resp.message || '没有对应用户');
						}
						$scope.searching = false;
					})
					.error(function (resp) {
						alert(resp.message);
						$scope.searching = false;
					});
			};

			$scope.cancel = function () {
				$modalInstance.dismiss();
			};

		}
	]);

});