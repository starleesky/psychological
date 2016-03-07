define(function (require) {
	
	var http = require('bower_components/angularext/angularExt').http;

	angular.module('App')

	.service('referrer', ['$http', '$q',
		function ($http, $q) {

			/**
			 * 新增推荐人
			 * @param {json} data 商户id，佣金比例
			 */
			this.add = function (data) {
				var self = this;
				return $q(function (resolve, reject) {
					http($http).post(angular.path + '/promotion/referrer/add/do', data)
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

			/**
			 * 修改推荐人
			 * @param {json} data
			 */
			this.update = function (data) {
				var self = this;
				return $q(function (resolve, reject) {
					http($http).post(angular.path + '/promotion/referrer/update/do', data)
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

			this.getData = function (rid) {
				return $q(function (resolve, reject) {
					$http.get(angular.path + '/promotion/referrer/detail/getdata?referrerId=' + rid)
						.success(function (resp) {
							resolve(resp);
						})
						.error(function (resp) {
							reject(resp.message);
						});
				});
			};

			this.getRecords = function (rid, filter) {
				filter.referrerId = rid;
				return $q(function (resolve, reject) {
					http($http).post(angular.path + '/promotion/referrer/detail/withdrawbill/getdata', filter)
						.success(function (resp) {
							resolve(resp);
						})
						.error(function (resp) {
							reject(resp.message);
						});
				});
			};

			this.searchShop = function (keyword) {
				return $q(function (resolve, reject) {
					$http.get(angular.path + '/user/get/namelike?name=' + keyword)
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

			this.getIdioms = function (filter) {
				return $q(function (resolve, reject) {

					http($http).post(angular.path + '/promotion/referrer/idiom/getdata', filter)
						.success(function (resp) {
							if (resp.data instanceof Array) {
								resolve(resp);
							} else {
								reject(resp.message);
							}
						})
						.error(function (resp) {
							reject(resp.message);
						});
				});
			};

			// 获取佣金详情
			this.getCommissionDetail = function (filter) {
				return $q(function (resolve, reject) {

					http($http).post(angular.path + '/promotion/referrer/commission/detail/getdata', filter)
						.success(function (resp) {
							if (resp.data instanceof Array) {
								resolve(resp);
							} else {
								reject(resp.message);
							}
						})
						.error(function (resp) {
							reject(resp.message);
						});

				});
			}

			this.saveOpenFunc = function(params){
				return $q(function (resolve, reject) {
					http($http).post(angular.path +'/promotion/referrer/activate/do',params)
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
			}
		}
	]);

});