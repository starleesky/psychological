define(function (require) {

	require('js/service/order/order');
	require('js/directives/ddcx-order');	
	
	angular.module('myApp', ['ui.bootstrap', 'ddcx.helper.order', 'ddcx.order.service'])

	.controller('mainCtrl', ['$scope', 'ddcxOrder',
		function($scope, ddcxOrder) {

			$scope.order = new ddcxOrder.Order(angular.orderId, 'advanced');

			$scope.order.fetch(angular.path + '/order/advanced/detail/getdata');

			$scope.type = 'advanced';

		}
	]);

});