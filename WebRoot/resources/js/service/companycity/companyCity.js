define(function (require) {

	var http = require('bower_components/angularext/angularExt').http;

	angular.module('App').
	service('companyCity', ['$http', '$q', function ($http, $q) {
		
		var citys = [];

		$http
			.get(angular.path + '/companycity/list/getcities')
			.success(function (data) {
				angular.forEach(data, function (value) {
					citys.push(value);
				});
			})
			.error(function () {
				
			});

		return {
			getCitys: function () {
				return citys;
			},
			create: function (data) {
				return $q(function (resolve, reject) {
					http($http).post(angular.path + '/companycity/add/do', data)
						.success(function (resp) {
							if (resp.success) {
								// citys.unshift(data);
								data.companyCityId = resp.data.id;
								resolve(data);
							} else {
								reject(resp.message);
							}
						})
						.error(function () {
							reject('创建失败，请重试');
						});
				});
			}
		};

	}]);
});
