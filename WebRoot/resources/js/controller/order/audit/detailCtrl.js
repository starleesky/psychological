define(function (require) {

	require('js/service/order/order');
	require('js/directives/ddcx-order');
	var http = require('bower_components/angularext/angularExt').http;
	
	angular.module('App')

	.controller('mainCtrl', ['$scope', 'ddcxOrder', '$http','$modal',
		function($scope, ddcxOrder, $http, $modal) {

			$scope.order = new ddcxOrder.Order(angular.orderId, 'service');

			var queryPic = new ddcxOrder.Order(angular.orderId, 'service');

			$scope.attachlist ;

			queryPic.getPagelistData(angular.orderId)
				.then(function(data){
					$scope.attachlist = data;
				},function(){
					alert('获取数据失败');
				})

			$scope.order.fetch(angular.path + '/order/audit/detail/getdata')
				.then(function () {

				}, function (msg) {
					alert(msg);
					window.location.href = angular.redirectUrl;
				});

			$scope.type = 'service';

			$scope.makeUnOpen = function () {
				if (confirm('确定修改？')) {
					http($http).post(angular.path + '/order/audit/update/do/cityopen/update', {orderId: $scope.order.data.id})
						.success(function (resp) {
							if (resp.success) {
								window.location.href = angular.redirectUrl;
							} else {
								alert(resp.message);
							}
						})
						.error(function (resp) {
							alert(resp.message);
						});				
				}
			};

		}
	]);

});