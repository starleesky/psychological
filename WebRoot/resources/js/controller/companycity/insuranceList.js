define(function (require) {
	
	var myApp = angular.module('myApp', ['ui.bootstrap']);

	myApp.controller('insuranceListCtrl', ['$scope',
		function ($scope) {

			// 时间选择
			$scope.startDate = '';
			$scope.endDate = '';
			$scope.changeDate = function (type) {
				
				var d = $scope[type];
				companyManager.filter[type] = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + (d.getDate());
				companyManager.search();

			};

		  $scope.open = function($event, type) {
		    $event.preventDefault();
		    $event.stopPropagation();

		    $scope[type + 'opened'] = true;
		  };

		  $scope.dateOptions = {
		    formatYear: 'yy',
		    startingDay: 1
		  };

		  $scope.format = 'yyyy-MM-dd';
			// 时间选择结束

		}
	]);

});