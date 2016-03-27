define(function (require) {
	
	angular.module('App')
	.directive('cityList', ['address', function (address) {
		return {
			restrict: 'E',
			require: 'ngModel',
			template: '<select ng-options="id*1 as name for (id, name) in citys"></select>',
			replace: true,
			scope: {
				provinceId: '=provice'
			},
			link: function postLink(scope, element, attrs) {

				scope.citys = address.getCitysByPid(scope.provinceId);

				scope.$watch('provinceId', function (newVal, oldVal) {
					if (newVal !== oldVal) {
						scope.citys = address.getCitysByPid(newVal);					
					}
				}, true);

			}
		};
	}]);

});