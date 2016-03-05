define(function (require) {
	
	angular.module('App')
	.service('expresscompany', ['$http', function ($http) {

		var companys;

		this.getAllCompanys = function () {

			if (!companys) {
				companys = [];
				$http.get(angular.path + '/common/get/expresscompany')
					.success(function (resp) {
						if (resp.success) {
							angular.forEach(resp.data, function (c) {
								companys.push(c);
							});
						}
					})
					.error(function () {
						alert('获取快递公司列表失败');
					});
			}

			return companys;

		};

	}]);

});