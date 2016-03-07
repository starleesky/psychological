define(function (require) {
	
	require('js/service/commonList');
	require('js/service/promotion/referCode');

	angular.module('App')

	.controller('mainCtrl', ['$scope', 'commonList', '$modal',
		function ($scope, commonList, $modal) {

			var list = $scope.list = commonList;
			list.filter.cityId = '';
			list.filter.phoneOrRecommandCode = '';
			list.url = "/promotion/referrer/referrercode/list/getdata";
			
			list.fetch();

			$scope.addCode = function () {
				var modal = $modal.open({
					templateUrl: angular.path + '/resources/templates/promotion/addCode-modal.html',
					controller: 'addReferCodeCtrl',
					backdrop: 'static',
					resolve: {
						detail: function () {
							return $scope.referrerDetail;
						}
					}
				});
				modal.result.then(function (code) {
					list.data.data.unshift(code);
				})
			};

		}
	])

	.controller('addReferCodeCtrl', ['$scope', '$modalInstance', 'referCode',
		function ($scope, $modalInstance, referCode) {

			$scope.addData = {
				cashValue: '',
				expireValue: ''
			};

			$scope.submitted = false;
			$scope.submitting = false;
			$scope.add = function () {
				$scope.submitted = true;
				if ($scope.add_form.$invalid) {
					return;
				}
				if (confirm('新的规则会覆盖旧的，确定添加？')) {
					$scope.submitting = true;
					referCode.add($scope.addData)
						.then(function () {
							var date = new Date();
							var code = {
								validDays: $scope.addData.expireValue,
								amount: $scope.addData.cashValue,
								editTime: date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate()
							};
							$modalInstance.close(code);
							$scope.submitting = false;
						}, function (msg) {
							alert(msg);
							$scope.submitting = false;
						});
				}
			};

			$scope.cancel = function () {
				$modalInstance.dismiss();
			};

		}
	]);

});