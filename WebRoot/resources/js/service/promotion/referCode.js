define(function (require) {
	
	var http = require('bower_components/angularext/angularExt').http;

	angular.module('App')

	.service('referCode', ['$http', '$q',
		function ($http, $q) {

			this.add = function (data) {
				var self = this;
				return $q(function (resolve, reject) {
					http($http).post(angular.path + '/promotion/referrer/referrercode/update/do', data)
						.success(function (resp) {
							if (resp.success) {
								resolve();
							} else {
								reject(resp.message);
							}
						})
						.error(function (resp) {
							reject(resp.message);							
						})
				});
			};

		}
	]);

});