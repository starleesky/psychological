define(function (require) {
	
	angular.module('App')
	.service('commercial', ['$http', function ($http) {

		var commercials = {};

		this.getCommercialsById = function (id) {

			if (!commercials[id]) {
				commercials[id] = {};
				$http.get(angular.path + '/order/audit/update/do/commercial/item/getdata?companyCityId=' + id)
					.success(function (resp) {
						if (resp instanceof Array) {
							angular.forEach(resp, function (c) {
								commercials[id][c.typeId] = c;
							});
						}
					})
					.error(function () {
						alert('获取险种列表失败');
					});
			}

			return commercials[id];

		};

	}]);

});