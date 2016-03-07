define(function (require) {
	
	angular.module('App')
	.filter('cityName', ['address', function (address) {

		var cityMap = {};

		var filterFun = function (cid, pid) {

			if (cityMap[pid]) {
				return cityMap[pid][cid] || '-';
			}
			cityMap[pid] = address.getCitysByPid(pid);
			return '-';

		};

		filterFun.$stateful = true;

		return filterFun;

	}]);

});