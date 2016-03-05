define(function (require) {
	
	require('js/service/commonList');
	require('js/service/common/orderStatus');
	require('js/service/common/citys');

	angular.module('myApp', ['ui.bootstrap', 'ddcx.common.list', 'ddcx.common.service'])

	.controller('mainCtrl', ['$scope', 'commonList', 'citys',
		function ($scope, commonList, citys) {

			$scope.cityList = citys.getAllCitys();

			var list = $scope.list = commonList;
			list.filter.platNoOrOrderNo = '';
			list.filter.cityId = '';
			list.filter.orderFlag = '';
			list.url = "/order/agencypay/list/getdata";
			
			list.fetch();

		}
	])

});	