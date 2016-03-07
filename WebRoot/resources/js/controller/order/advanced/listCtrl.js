define(function (require) {
	
	require('js/service/commonList');
	require('js/service/common/orderStatus');
	require('js/service/common/citys');

	angular.module('myApp', ['ui.bootstrap', 'ddcx.common.list', 'ddcx.common.service'])

	.controller('mainCtrl', ['$scope', 'commonList', '$timeout', 'orderStatus', 'citys',
		function ($scope, commonList, $timeout, orderStatus, citys) {

			$scope.statusList = orderStatus.getAllStatus();
			$scope.cityList = citys.getAllCitys();

			var list = $scope.list = commonList;
			list.filter.platNoOrOrderNo = '';
			list.filter.cityId = '';
			list.filter.orderStatus = '';
			list.filter.orderFlag = '';
			list.url = "/order/advanced/list/getdata";
			
			list.fetch();

		}
	])

});	