define(function (require) {
	
	require('js/service/commonList');
	require('js/service/common/orderStatus');
	require('js/service/common/citys');
	require('js/service/common/activeCompany');
	require('js/directives/filter-select');
	require('js/service/common/verify');

	angular.module('App')

	.controller('mainCtrl', ['$scope', 'commonList', '$timeout', 'orderStatus', 'citys', 'activeCompanys','verifyWay',
		function ($scope, commonList, $timeout, orderStatus, citys, activeCompanys,verifyWay) {

			$scope.statusList = [
				{
					'code':7,
					'desc':'已支付'
				},
				{
					'code':8,
					'desc':'待出保单'
				},
				{
					'code':10,
					'desc':'待送保单'
				},
				{
					'code':11,
					'desc':'保单已寄出'
				},
				{
					'code':16,
					'desc':'退款成功'
				},
				{
					'code':27,
					'desc':'退保成功'
				}
				];
				
			$scope.insTypeList = [
				{
					'code':1,
					'desc':'交强险'
				},
				{
					'code':2,
					'desc':'商业险'
				}];
			
			$scope.thisMonth = function () {
						var myDate = new Date();
				    var year = myDate.getFullYear();
				    var month = myDate.getMonth()+1;
				    if (month<10){
				        month = "0"+month;
				    }
				    var firstDay = year +"-" + month+"-01";
				    myDate = new Date(year,month,0);
				    var lastDay =year +"-" + month+"-"+myDate.getDate();
	        	list.filter.startDate = new Date(firstDay);
	        	list.filter.endDate = new Date(lastDay);
	        	list.fetch();	        	
			 };
			 
			 
			 $scope.lastMonth = function () {
						var myDate = new Date();
				    var year = myDate.getFullYear();
				    var month = myDate.getMonth();
						if(month == 0){
							 year = parseInt(year) - 1;
							 month = 12;
						}	
				    if (month<10){
				        month = "0"+month;
				    }
				    var firstDay = year +"-" + month+"-01";
				    myDate = new Date(year,month,0);
				    var lastDay =year +"-" + month+"-"+myDate.getDate();
	        	list.filter.startDate = new Date(firstDay);
	        	list.filter.endDate = new Date(lastDay);
	        	list.fetch();	        	
			 };
			 
			 $scope.thisDate = function () {
						var myDate = new Date();
	        	list.filter.startDate = myDate;
	        	list.filter.endDate = myDate;
	        	list.fetch();	        	
			 };
			 
			 $scope.cumulatively = function () {

	        	list.filter.startDate = null;
	        	list.filter.endDate = null;
	        	list.fetch();	        	
			 };
			
				
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
			list.filter.platNoOrOrderNo = '';
			list.filter.cityId = '';
			list.filter.orderStatus = '';
			var myDate = new Date();
	    list.filter.startDate = myDate;
	    list.filter.endDate = myDate;
			list.url = "/commission/daily/list/getData";
			
			list.fetch();

		}
	]);

});	