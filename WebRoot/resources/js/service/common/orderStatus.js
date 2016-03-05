define(function (require) {
	
	angular.module('App')
	.service('orderStatus', ['$http', function ($http) {

		var status;

		this.getAllStatus = function () {

			if (!status) {
				status = [];
				$http.get(angular.path + '/common/get/order/allstatus')
					.success(function (resp) {
						if (resp instanceof Array) {
							angular.forEach(resp, function (s) {
								status.push(s);
							});
						}
					})
					.error(function () {
						alert('获取订单状态种类列表失败');
					});
			}

			return status;

		};

	}]);

});