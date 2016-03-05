define(function (require) {
	
	angular.module('App')
	.directive('specialChar', ['$timeout',function ($timeout) {
		return {
			restrict: 'AE',
			require: 'ngModel',
			link: function postLink(scope, element, attrs,ngModel) {

				/*element.on("keydown",function(e){
					if(e.keyCode == 32 || !((e.keyCode >=48 && e.keyCode <=57) || (e.keyCode >=65 && e.keyCode <=90) || e.keyCode == 8)){
						return false;
					}
				});*/

				element.on("keyup",function(e){
					//console.log(e.keyCode);
					var value = element.val();
					value = value.toUpperCase();
					element.val(value);
					if(ngModel){
						$timeout(function(){
							ngModel.$setViewValue(value);
						},50);
					}
				});

				element.on("blur",function(e){
					//console.log(e.keyCode);
					var value = element.val();
					value = value.toUpperCase();
					element.val(value);
					if(ngModel){
						$timeout(function(){
							ngModel.$setViewValue(value);
						},50);
					}
				});

			}
		};
	}]);

});