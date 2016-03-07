define(function (require) {

	require('js/service/order/order');
	require('js/directives/ddcx-order');	
	
	angular.module('App')

	.controller('mainCtrl', ['$scope', 'ddcxOrder',
		function($scope, ddcxOrder) {

			$scope.order = new ddcxOrder.Order(angular.orderId, 'refund');

			$scope.order.fetch(angular.path + '/order/refundPolicy/detail/getdata')
				.then(function () {

				}, function (msg) {
					alert(msg);
					window.location.href = angular.redirectUrl;
				});

			$scope.type = 'refundPolicy';

			var queryPic = new ddcxOrder.Order(angular.orderId, 'refundPolicy');

			$scope.attachlist ;

			queryPic.getPagelistData(angular.orderId)
				.then(function(data){
					$scope.attachlist = data;
				},function(){
					alert('获取数据失败');
				})
		}
	]);

});