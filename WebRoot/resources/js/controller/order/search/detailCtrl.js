define(function (require) {

	require('js/service/order/order');
	require('js/directives/ddcx-order');	
	
	angular.module('App')

	.controller('mainCtrl', ['$scope', 'ddcxOrder',
		function($scope, ddcxOrder) {

			$scope.order = new ddcxOrder.Order(angular.orderId, 'global');

			$scope.order.fetch(angular.path + '/order/search/detail/getdata');

			$scope.type = 'global';

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