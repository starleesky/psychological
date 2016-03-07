define(function (require) {
	
	require('js/service/commonList');
	require('js/service/common/citys');
	require('js/service/common/activeCompany');
	require('js/service/common/activeCompany');
	require('js/service/common/monitor');
	require('js/directives/filter-select');

	angular.module('App')

	.controller('mainCtrl', ['$scope', 'commonList', '$timeout', 'citys', 'activeCompanys','monitor',
		function ($scope, commonList, $timeout, citys, activeCompanys,monitor) {

			$scope.cityList = citys.getAllCitys();

			var list = $scope.list = commonList;
			list.filter.carNum = '';
			list.filter.cityId = '';
			list.filter.type = 1;
			list.url = "/car/list/getdata";

			setTimeout(function () {
				console.log(list.data);
			}, 2000);
			
			list.fetch();

			monitor.startUp();

		}
	]);

});	