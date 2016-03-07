define(function (require) {
	
	angular.module('App')
	.service('usenatures', ['$http', function ($http) {

		var usenatures;

		this.getAllUsenatures = function () {

			if (!usenatures) {
				usenatures = [];
				$http.get(angular.path + '/common/get/car/usenatures')
					.success(function (resp) {
						if (resp instanceof Array) {
							angular.forEach(resp, function (u) {
								usenatures.push(u);
							});
						}
					})
					.error(function () {
						alert('获取所有城市列表失败');
					});
			}

			return usenatures;

		};

	}]);

});