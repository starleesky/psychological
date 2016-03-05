define(function (require) {
	
	var http = require('bower_components/angularext/angularExt').http;

	angular.module('App')

	.service('activity', ['$http', '$q',
		function ($http, $q) {

			/**
			 * 新建活动
			 * @param {Json} data 
			 */
			this.add = function (data) {
				var self = this;
				return $q(function (resolve, reject) {
					$http.post(angular.path + '/promotion/activity/add/do',angular.toJson(data))
						.success(function (resp) {
							if (resp.success) {
								resp.data.activityId = resp.data.id;
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

			// 获取活动详情
			this.get = function (aid) {
				var self = this;
				return $q(function (resolve, reject) {
					$http.get(angular.path + '/promotion/activity/detail/getdata?activityId=' + aid)
						.success(function (resp) {
							resolve(resp);
						})
						.error(function (resp) {
							reject(resp.message);
						});
				});
			};

			// 修改活动信息
			this.edit = function (data) {
				var self = this;
				return $q(function (resolve, reject) {
					$http.post(angular.path + '/promotion/activity/update/do',angular.toJson(data))
						.success(function (resp) {
							if (resp.success) {
								resolve();
							} else {
								reject(resp.message);
							}
						})
						.error(function (resp) {
							reject(resp.message);
						});
				});
			};

		}
	]);

});