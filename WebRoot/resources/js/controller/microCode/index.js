define(function (require) {

	require('js/service/microCode/microCode');

	var myApp = angular.module('App');

	myApp.controller('mainCtrl', ['$scope', '$modal', 'microCode',
		function ($scope, $modal, microCode) {

			$scope.codes = microCode.getCodes();
			$scope.choosedCode = null;
			$scope.values = [];

			$scope.choose = function (code) {
				$scope.values = microCode.getValuesByCode(code.code);
				$scope.choosedCode = code;
			};

			$scope.addCodeValue = function () {
				if (!$scope.choosedCode) {
					alert('请先选择一个CODE');
					return;
				}

				var modal = $modal.open({
					templateUrl: angular.path + '/resources/templates/microCode/addValue-modal.html',
					controller: 'addNewCtrl',
					backdrop: 'static',
					resolve: {
						code: function () {
							return $scope.choosedCode;
						}
					}
				});

				modal.result.then(function (data) {
					$scope.values.unshift(data);
				});

			};

			$scope.editCodeValue = function (value) {
				var modal = $modal.open({
					templateUrl: angular.path + '/resources/templates/microCode/editValue-modal.html',
					controller: 'editValueCtrl',
					backdrop: 'static',
					resolve: {
						code: function () {
							return $scope.choosedCode;
						},
						value: function () {
							return value;
						}
					}
				});

				modal.result.then(function (data) {
					$scope.values.unshift(data);
				});
			};

			$scope.deleteCode = function (code) {
				if (confirm('确定删除' + code.fieldValue + '？')) {
					microCode.deleteCode(code)
						.then(function () {
							angular.forEach($scope.values, function (v, k) {
								if (v.id == code.id) {
									$scope.values.splice(k, 1);
								}
							});
						}, function (msg) {
							alert(msg);
						});
				}
			};

		}

	])
	.controller('addNewCtrl', ['$scope', '$modalInstance', 'code', 'microCode',
		function ($scope, $modalInstance, code, microCode) {

			$scope.code = code;

			$scope.data = {
				fieldKey: '',
				fieldValue: '',
				remark: ''
			};

			$scope.add = function () {
				microCode.saveCodeValue($scope.data, code)
					.then(function (data) {
						$modalInstance.close(data);
					}, function (msg) {
						alert(msg);
					});
			};

			// 取消按钮对应的方法
			$scope.cancel = function () {
				$modalInstance.dismiss();
			};
		}
	])
	.controller('editValueCtrl', ['$scope', '$modalInstance', 'code', 'value', 'microCode',
		function ($scope, $modalInstance, code, value, microCode) {

			$scope.code = code;

			$scope.data = angular.copy(value);

			$scope.edit = function () {
				microCode.saveCodeValue($scope.data, code)
					.then(function (data) {
						value.fieldValue = $scope.data.fieldValue;
						value.fieldKey = $scope.data.fieldKey;
						value.isValid = $scope.data.isValid;
						$modalInstance.dismiss();
					}, function (msg) {
						alert(msg);
					});
			};

			// 取消按钮对应的方法
			$scope.cancel = function () {
				$modalInstance.dismiss();
			};
		}
	]);

});