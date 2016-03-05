define(function (require) {

	require('js/service/order/order');
	require('js/directives/ddcx-order');	
	
	angular.module('App')

	.controller('mainCtrl', ['$scope', 'ddcxOrder',
		function($scope, ddcxOrder) {

			$scope.order = new ddcxOrder.Order(angular.orderId, 'underwriter');
			
			var queryPic = new ddcxOrder.Order(angular.orderId, 'service');

			$scope.attachlist ;

			queryPic.getPagelistData(angular.orderId)
				.then(function(data){
					$scope.attachlist = data;
				},function(){
					alert('获取数据失败');
				})

			$scope.order.fetch(angular.path + '/order/verify/detail/getdata')
				.then(function () {

				}, function (msg) {
					alert(msg);
					window.location.href = angular.redirectUrl;
				});

			$scope.type = 'underwriter';

			// 订单过期
			// $scope.expire = function () {
			// 	if (confirm('确定过期？一旦提交无法修改')) {
			// 		$scope.order.expire()
			// 			.then(function () {
			// 				window.location.href = angular.redirectUrl;
			// 			}, function (msg) {
			// 				alert(msg);
			// 			});
			// 	}
			// };

			// 订单失效
			// $scope.invalid = function () {
			// 	if (confirm('确定失效？一旦提交无法修改')) {
			// 		$scope.order.invalid()
			// 			.then(function () {
			// 				window.location.href = angular.redirectUrl;
			// 			}, function (msg) {
			// 				alert(msg);
			// 			});
			// 	}
			// };

			

		}
	]);

});