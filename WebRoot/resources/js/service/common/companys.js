define(function (require) {
	
	angular.module('App')
	.service('companys', ['$http', function ($http) {

		var companys;

		this.getAllCompanys = function () {

			if (!companys) {
				companys = [];
				$http.get(angular.path + '/common/get/allcompany')
					.success(function (resp) {
						if (resp instanceof Array) {
							angular.forEach(resp, function (c) {
								companys.push(c);
							});
						}
					})
					.error(function () {
						alert('获取所有公司列表失败');
					});
			}

			return companys;

		};

	}]);

});