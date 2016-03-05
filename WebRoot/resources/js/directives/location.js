define(function (require) {

	require('js/service/common/address');
	
	angular.module('location', ['ddcx.common.service'])
	/**
	 * 选择地址的指令，选择省市区，以及输入详细地址
	 */
	.directive('location', ['address', '$timeout',
		function (address, $timeout) {
			return {
				templateUrl: angular.path + '/resources/templates/utils/location.html',
				scope: {
					require: '@require'
				},
				link: function ($scope, element, attr) {
					$scope.province = $scope.province || '';
					$scope.city = $scope.city || '';
					$scope.district = $scope.district || '';
					$scope.detail = $scope.detail || '';
					var authoritys = {
						province: 1,
						city: 2,
						district: 3,
						detail: 4
					};
					$scope.authority = authoritys[$scope.require || 'detail'];

					var ctrl = $scope.ctrl = {
						provinces: address.getProvinceList(),
						citys: $scope.province ? address.getCitysByPid($scope.province) : [],
						districts: $scope.city ? address.getDistrictsByCid($scope.city) : []
					};

					$scope.index = 0;
					$scope.inputing = false;

					var values = [$scope.province, $scope.city, $scope.district, $scope.detail];

					$scope.input = function (index) {
						$scope.inputing = true;
						if (index === 0) {
							$scope.index = index;
							return;
						}
						if (!values[index - 1]) {
							$scope.input(index--);
						} else {
							$scope.index = index;
						}
					};

					function checkIfFocus() {
						return $(element).find('.choice:focus').length > 0;
					};

					$scope.blur = function () {
						$timeout(function () {
							if (!checkIfFocus()) {
								$scope.inputing = false;
							}
						}, 100);
					};

					function refreshCitys() {
						$scope.city = '';
						$scope.district = '';
						$scope.detail = '';
						ctrl.citys = address.getCitysByPid($scope.province);
					};

					function refreshDistricts() {
						$scope.district = '';
						$scope.detail = '';
						ctrl.districts = address.getDistrictsByCid($scope.city);
					};

					function next() {
						// $scope.index += 1;
						$($('.choice')[$scope.index + 1]).trigger('click').focus();
					};

					function refreshDetail() {
						$scope.detail = '';
					};

					$scope.chooseP = function (pid) {
						$scope.province = pid;
						refreshCitys();
						next();
					};

					$scope.chooseC = function (cid) {
						$scope.city = cid;
						refreshDistricts();
						next();
					};

					$scope.chooseD = function (did) {
						$scope.district = did;
						refreshDetail();
						next();
					};

				}
			};
		}
	]);

});