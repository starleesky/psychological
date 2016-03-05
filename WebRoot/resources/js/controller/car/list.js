define(function (require) {
	
	require('js/service/commonList');
	require('js/service/common/citys');
	require('js/service/common/activeCompany');
	require('js/directives/filter-select');
	require('js/service/common/monitor');

	angular.module('App')

	.controller('mainCtrl', ['$scope', 'commonList', '$timeout', 'citys', 'activeCompanys','monitor',
		function ($scope, commonList, $timeout, citys, activeCompanys,monitor) {

			$scope.cityList = citys.getAllCitys();
			$scope.statusList = ["未认证","待认证","认证成功","认证失败","行驶证已失效"];

			var list = $scope.list = commonList;
			list.filter.carNum = '';
			list.filter.cityId = '';
			// list.filter.type = 1;
			list.filter.auditStatus = '';
			list.url = "/car/list/getdata";

			setTimeout(function () {
				console.log(list.data);
			}, 2000);
			
			list.fetch();
			monitor.startUp();
		}
	]);

});	