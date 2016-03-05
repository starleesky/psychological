define(function (require) {

	require('js/service/order/order');
	require('js/directives/ddcx-order');	
	
	angular.module('App')

	.controller('mainCtrl', ['$scope', 'ddcxOrder',
		function($scope, ddcxOrder) {

			$scope.order = new ddcxOrder.Order(angular.orderId, 'produceinsurance');

			$scope.order.fetch(angular.path + '/order/produceinsurance/detail/getdata')
				.then(function () {

				}, function (msg) {
					alert(msg);
					window.location.href = angular.redirectUrl;
				});

			$scope.type = 'produceinsurance';

			var queryPic = new ddcxOrder.Order(angular.orderId, 'service');

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