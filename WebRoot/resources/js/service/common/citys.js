define(function (require) {
	
	angular.module('App')
	.service('citys', ['$http', function ($http) {

		var citys;

		this.getAllCitys = function () {

			if (!citys) {
				citys = [];
				$http.get(angular.path + '/common/get/getcities')
					.success(function (resp) {
						if (resp instanceof Array) {
							angular.forEach(resp, function (c) {
								citys.push(c);
							});
						}
					})
					.error(function () {
						alert('获取所有城市列表失败');
					});
			}

			return citys;

		};

	}]);

});