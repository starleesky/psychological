define(function (require) {
	
	require('js/service/commonList');
	require('js/service/common/orderStatus');
	require('js/service/common/citys');

	angular.module('myApp', ['ui.bootstrap', 'ddcx.common.list', 'ddcx.common.service'])

	.controller('mainCtrl', ['$scope', 'commonList',
		function ($scope, commonList) {

			var list = $scope.list = commonList;
			list.url = "/system/agencycompany/list/getdata";
			
			list.filter.name = '';

			list.fetch();

		}
	])

});	