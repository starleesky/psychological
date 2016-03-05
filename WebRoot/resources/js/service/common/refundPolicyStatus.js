define(function (require) {
	
	angular.module('App')
	.service('refundPolicyStatus', ['$http', function ($http) {

		var status;

		this.getRefundPolicyStatus = function () {

			if (!status) {
				status = [];
				$http.get(angular.path + '/common/get/order/refundPolicyStatus')
					.success(function (resp) {
						if (resp.success) {
							angular.forEach(resp.data, function (s) {
								status.push(s);
							});
						}
					})
					.error(function () {
						alert('获取所有城市列表失败');
					});
			}

			return status;

		};

	}]);

});