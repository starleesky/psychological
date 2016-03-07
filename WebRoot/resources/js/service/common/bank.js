define(function (require) {
	
	angular.module('App')
	.service('banks', ['$http', function ($http) {

		var banks;

		this.getAllBanks = function () {

			if (!banks) {
				banks = {};
				$http.get(angular.path + '/common/get/getTenpayBank')
					.success(function (resp) {
						if (resp.success) {
							angular.forEach(resp.data, function (b) {
								banks[b.code] = b;
							});
						}
					})
					.error(function () {
						alert('获取所有城市列表失败');
					});
			}

			return banks;

		};

	}]);

});