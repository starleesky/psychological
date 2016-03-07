define(function (require) {
	
	require('js/service/commonList');
	require('js/service/common/citys');
	require('js/service/common/activeCompany');
	require('js/service/common/address');
	var http = require('bower_components/angularext/angularExt').http;

	angular.module('App')

	.controller('mainCtrl', ['$scope', 'commonList', '$modal', 'citys', 'acorder', '$filter',
		function ($scope, commonList, $modal, citys, acorder, $filter) {

			$scope.cityList = citys.getAllCitys();

			var list = $scope.list = commonList;
			list.filter.orderNo = '';
			list.filter.cityId = '';
			list.url = "/system/agencycompanyorder/list/getdata";
			
			list.fetch();

			function getFormatedDate(date) {
				return date instanceof Date ? date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate() : null;
			}

			$scope.startDate = "";
			$scope.endDate = "";

			$scope.$watch("list.filter.startDate",function(newValue,oldValue){
				$scope.startDate = getFormatedDate(newValue);
			});

			$scope.$watch("list.filter.endDate",function(newValue,oldValue){
				$scope.endDate = getFormatedDate(newValue);
			});

			// 查看代付公司详细信息
			$scope.showDetail = function (order) {
				$modal.open({
					templateUrl: angular.path + '/resources/templates/system/agencycompanyorder/detail-tmpl.html',
					controller: 'detailCtrl',
					backdrop: 'static',
					size: 'lg',
					resolve: {
						order: function () {
							return order;
						}
					}
				});
			};

			$scope.netPay = function (order) {
				var newWindow;
				newWindow = window.open('', '', 'height=600, width=1000');
				newWindow ? newWindow.location = order.payUrl : alert('打开支付页面失败!请联系管理员');
			}

			$scope.confirmDownload = function (e) {
				if (!confirm('确定下载？')) {
					e.preventDefault();
				}
			};

			$scope.repay = function (order) {
				if (confirm('你确定要重新打款代付金额' + $filter('number')(order.amount/100, 2) + '元？')) {
					order.repaying = true;
					acorder.repay(order)
						.then(function () {
							order.repaying = false;
							order.agencyStatus = 0;
							order.remark = '重新申请成功';
						}, function (msg) {
							alert(msg);
							order.repaying = false;
						});
				}
			};

		}
	])

	.service('acorder', ['$http', '$q',
		function ($http, $q) {

			// 获取详细信息
			this.getDetail = function (id) {
				return $q(function (resolve, reject) {
					http($http).post(angular.path + '/system/agencycompanyorder/detail', {agencycompanyorderId: id})
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

			this.getOrderList = function (filter) {
				return $q(function (resolve, reject) {
					http($http).post(angular.path + '/system/agencycompanyorder/list/getdata', filter)
						.success(function (resp) {
							if (resp.data instanceof Array && resp.data.length > 0) {
								resolve(resp)
							} else {
								reject(resp.message || 没有更多记录);
							}
						})
						.error(function (resp) {
							reject(resp.message);
						});
				});
			};

			this.repay = function (order) {
				return $q(function (resolve, reject) {
					http($http).post(angular.path + '/system/agencycompanyorder/repay', {
						agencyCompanyOrderId: order.id
					})
						.success(function (resp) {
							if (resp.success) {
								resolve()
							} else {
								reject(resp.message || 打款失败);
							}
						})
						.error(function (resp) {
							reject(resp.message || 打款失败);
						});
				});
			};

			this.getAgencys = function (cid, comId) {
				return $q(function (resolve, reject) {
					http($http).post(angular.path + '/system/agencycompanyorder/getSetedAgencyCompany', {
						cityId: cid,
						companyId: comId
					})
						.success(function (resp) {
							if (resp.success) {
								resolve(resp.data)	
								// if (resp.data instanceof Array && resp.data.length > 0) {
								// 	resolve(resp.data)									
								// } else {
								// 	// reject('没有配置代付公司');
								// }
							} else {
								reject(resp.message || 获取代付公司列表失败);
							}
						})
						.error(function (resp) {
							reject(resp.message);
						});
				});
			};

			this.editAgency = function (order, agency) {
				return $q(function (resolve, reject) {
					http($http).post(angular.path + '/system/agencycompanyorder/replaceAgency', {
						cityId: order.cityId,
						companyId: order.companyId,
						agencyCompanyId: order.agencyCompanyId
					})
						.success(function (resp) {
							if (resp.success) {
								resolve()
							} else {
								reject(resp.message || 修改失败);
							}
						})
						.error(function (resp) {
							reject(resp.message);
						});
				});
			};

		}
	])

	.controller('detailCtrl', ['$scope', '$modalInstance', 'order', 'acorder', '$modal',
		function ($scope, $modalInstance, order, acorder, $modal) {

			// var filter = $scope.filter = {
			// 	page: 1,
			// 	pageSize: 10,
			// 	cityId: order.cityId,
			// 	companyId: order.companyId
			// };

			// $scope.filterOrder = function () {
			// 	acorder.getOrderList(filter)
			// 		.then(function (resp) {
			// 			$scope.orderList = resp;
			// 			// console.log($scope.orderList.data);
			// 		}, function (msg) {
			// 			alert(msg);
			// 		});
			// };

			// $scope.filterOrder();

			$scope.order = order;
			if (!order.detail) {
				acorder.getDetail(order.id)
					.then(function (data) {
						angular.extend(order, data);
						order.detail = true;
					}, function (msg) {
						alert(msg);
					});
			}

			$scope.edit = function () {
				$modal.open({
					templateUrl: angular.path + '/resources/templates/system/agencycompanyorder/eidt-pay-function.html',
					controller: 'editPayCtrl',
					backdrop: 'static',
					size: 'lg',
					resolve: {
						order: function () {
							return order;
						}
					}
				});
			};

			$scope.cancel = function () {
				$modalInstance.dismiss();
			}

		}
	])

	.controller('editPayCtrl', ['$scope', '$modalInstance', 'order', 'acorder',
		function ($scope, $modalInstance, order, acorder) {

			$scope.order = angular.copy(order);
			// $scope.agency = order.agencyCompanyId;

			if (!$scope.order.agencys) {
				acorder.getAgencys(order.cityId, order.companyId)
					.then(function (data) {
						$scope.order.agencys = data;
					}, function (msg) {
						alert(msg);
						$modalInstance.dismiss();
					});
			}

			$scope.submitting = $scope.submitted = false;
			$scope.submit = function () {
				$scope.submitted = true;
				console.log($scope.order);
				acorder.editAgency($scope.order, $scope.agency)
					.then(function () {
						angular.extend(order, $scope.order);
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