define(function (require) {
	
	var http = require('bower_components/angularext/angularExt').http;

	angular.module('App')
	.service('microCode', ['$http', '$q', function ($http, $q) {

		this.getCodes = function () {
			var	codes = [];
			$http.get(angular.path + '/microcode/list/getdata?pageSize=30')
				.success(function (resp) {

					if (resp.data instanceof Array) {
						angular.forEach(resp.data, function (c) {
							codes.push(c);
						});
					}

				})
				.error(function (resp) {
					alert(resp.message || '获取code列表失败');
				});

			return codes;
		};

		this.getValuesByCode = function (code) {
			var values = [];
			$http.get(angular.path + '/microcode/field/list/getdata?code=' + code)
				.success(function (resp) {
					if (resp.success) {
						angular.forEach(resp.data, function (v) {
							values.push(v);
						});
					}
				})
				.error(function (resp) {
					alert(resp.message || "获取" + code + '列表失败')
				});

			return values;
		};

		this.saveCodeValue = function (data, code) {
			if (code) {
				data.code = code.code;
			}

			return $q(function (resolve, reject) {
				http($http).post(angular.path + '/microcode/field/add/do', data)
					.success(function (resp) {
						if (resp.success) {
							resolve(resp.data);
						} else {
							reject(resp.message);
						}
					})
					.error(function (resp) {
						reject(resp.message);
					});
			});

		};

		this.deleteCode = function (code) {
			console.log(code);
			return $q(function (resolve, reject) {
				http($http).post(angular.path + '/microcode/field/delete/do', code)
					.success(function (resp) {
						if (resp.success) {
							resolve(resp.data);
						} else {
							reject(resp.message);
						}
					})
					.error(function (resp) {
						reject(resp.message);
					});
			});
		};


	}]);

});