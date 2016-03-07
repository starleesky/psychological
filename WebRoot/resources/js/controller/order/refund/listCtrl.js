define(function (require) {
	
	require('js/service/commonList');
	require('js/service/common/refundStatus');
	require('js/service/common/citys');
	require('js/service/common/activeCompany');
	require('js/directives/filter-select');
	require('js/service/common/verify');

	angular.module('App')

	.controller('mainCtrl', ['$scope', 'commonList', '$timeout', 'refundStatus', 'citys', 'activeCompanys','verifyWay',
		function ($scope, commonList, $timeout, refundStatus, citys, activeCompanys,verifyWay) {

			$scope.statusList = refundStatus.getRefundStatus();
			$scope.cityList = citys.getAllCitys();
			$scope.companys = activeCompanys.getAllActiveCompanys();
			$scope.verifyList = verifyWay.getAllVerify();

			$scope.choseCity = function () {
				activeCompanys.getCompanysByCityId(list.filter.cityId)
					.then(function (list) {
						$scope.companys = list;
					}, function (msg) {
						alert(msg);
					});
				$scope.list.fetch();
			};

			var list = $scope.list = commonList;
			list.filter.orderStatus = '';
			list.filter.auditStatus = '';
			list.filter.platNoOrOrderNo = '';
			list.filter.cityId = '';
			list.filter.orderFlag = '';
			list.filter.verifyWay = '';

			list.url = "/order/refund/list/getdata";
			
			list.fetch();

		}
	])

});	