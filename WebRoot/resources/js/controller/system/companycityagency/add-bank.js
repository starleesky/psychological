define(function (require) {
	
	angular.module('App')
	.controller('companyCityAddBank', ['$scope', '$modalInstance', 'company',
		function ($scope, $modalInstance, company) {

			var data = $scope.data = {
				companyCityId: company.companyCityId,
				bankName: '',
				bankUser: '',
				bankNo: '',
				bankBranch: '',
				bankAreaCode: '',
				bankCity: '',
				bankCityCode: '',
				bankAccType: ''
			};

		}
	]);

});