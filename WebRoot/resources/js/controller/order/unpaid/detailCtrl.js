define(function (require) {

	require('js/service/order/order');
	require('js/directives/ddcx-order');	
	
	angular.module('App')

	.controller('mainCtrl', ['$scope', 'ddcxOrder', '$modal',
		function($scope, ddcxOrder, $modal) {

			$scope.order = new ddcxOrder.Order(angular.orderId, 'unpaid');

			$scope.order.fetch(angular.path + '/order/unpaid/detail/getdata')
				.then(function () {

				}, function (msg) {
					alert(msg);
					window.location.href = angular.redirectUrl;
				});

			$scope.type = 'unpaid';

			var queryPic = new ddcxOrder.Order(angular.orderId, 'service');

			$scope.attachlist ;

			queryPic.getPagelistData(angular.orderId)
				.then(function(data){
					$scope.attachlist = data;
				},function(){
					alert('获取数据失败');
				})
			
			$scope.rollBack = function () {
				$modal.open({
					templateUrl: 'rollBackTmpl.html',
					controller: 'rollBackCtrl',
					backdrop: 'static',
					resolve: {
						order: function () {
							return $scope.order;
						}
					}
				});
			};

			$scope.underPay = function () {
				$modal.open({
					templateUrl: angular.path + '/resources/templates/order/under-pay-modal.html',
					controller: 'underPayCtrl',
					backdrop: 'static',
					resolve: {
						order: function () {
							return $scope.order;
						}
					}
				});
			};

		}
	])
		.controller('rollBackCtrl', ['$scope', '$modalInstance', 'order',
			function ($scope, $modalInstance, order) {
				$scope.order = order;

				$scope.submitting = false;
				$scope.submit = function () {
					if (confirm('确定重新核保？')) {
						$scope.submitting = true;
						order.rollBack()
							.then(function () {
								window.location.href = angular.redirectUrl;
								$scope.submitting = false;
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
		/**
		 * 线下支付
		 */
		.controller('underPayCtrl', ['$scope', '$modalInstance', 'order', '$filter',
			function ($scope, $modalInstance, order, $filter) {

				$scope.hstep = 1;
				$scope.mstep = 1;

				var now = new Date();

				$scope.data = {
					orderId: order.data.id,
					tradeNo: order.data.tradeNo,
					payAmount: order.data.paidAmount ? (parseFloat(order.data.orderAmount) - parseFloat(order.data.paidAmount)).toFixed(2) : order.data.orderAmount,
					orderDesc: '',
					payBankCard: {
						bankName: '',
						bankUser: '',
						bankNo: '',
						bankBranch: '',
						bankAccType: '1'
					}
				};

				$scope.ifBank = 0;

				$scope.date = $filter('date')(now, 'yyyy-MM-dd');
				$scope.time = now;

				function getTime(){
					if (typeof $scope.date == 'string') {
						return $scope.date + ' ' + $scope.time.getHours() + ':' + $scope.time.getMinutes() + ':00';
					}
					return $scope.date.getFullYear() + '-' + ($scope.date.getMonth() + 1) + '-' + $scope.date.getDate() + ' ' + $scope.time.getHours() + ':' + $scope.time.getMinutes() + ':00';
				}

				$scope.submitting = $scope.submitted = false;
				$scope.pay = function () {
					$scope.submitted = true;
					if ($scope.pay_form.$invalid) {
						return;
					}

					var data = angular.copy($scope.data);
					if ($scope.ifBank == 0) {
						data.payBankCard = null;
					}
					data.orderPaidTime = getTime();

					if (confirm('确定支付？')) {
						$scope.submitting = true;
						$scope.data.orderPaidTime = getTime();
						order.underPay(data)
							.then(function () {
								window.location.href = angular.redirectUrl;
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
		]);

});