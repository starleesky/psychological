define(function (require) {

	var http = require('bower_components/angularext/angularExt').http;
	
	angular.module('App').
	service('insurancePlan', ['$http', '$q', function ($http, $q) {


		var allTypes = [];
		var normal = [];
		var economic = [];

		function Plan(type, companyCityId) { // 2是经济型，3是普通型

			this.type = type;
			this.companyCityId = companyCityId;
			this.plan = {};
			this.getPlan();

		}

		Plan.prototype.getPlan = function() { 

			var self = this;
			
			return $q(function (resolve, reject) {
				http($http).post(angular.path + '/companycity/update/do/strategytype/getdata', {
					companyCityId: self.companyCityId,
					strategyCode: self.type
				})
				.success(function (resp) {
					angular.forEach(resp, function (t) {
						self.plan[t.typeId] = t;
						self.plan[t.typeId].childs = [];
					});

					angular.forEach(self.plan, function (v, k) {
						if (v.parentId) {
							// self.plan[k].parent = self.plan[v.parentId];
							self.plan[v.parentId].childs.push(v);
							delete self.plan[k];
						}
					});
				})
				.error(function () {

				});
			});

		};

		Plan.prototype.setPlan = function() {
			var self = this;
			var postData = {}, i = 0;
			angular.forEach(self.plan, function (value, key) {
				if (value.checked) {
					postData['typeStrategies['+i+'].companyCityTypeId'] = value.companyCityTypeId;
					postData['typeStrategies['+i+'].defaultValue'] = value.defaultValueExt;
					i += 1;
				}
				angular.forEach(value.childs, function (value, key) {
					if (value.checked) {
						postData['typeStrategies['+i+'].companyCityTypeId'] = value.companyCityTypeId;
						postData['typeStrategies['+i+'].defaultValue'] = value.defaultValueExt;
						i += 1;
					}
				});
			});

			postData.companyCityId = self.companyCityId;
			postData.strategyCode = self.type;

			return $q(function (resolve, reject) {
				http($http).post(angular.path + '/companycity/update/do/strategytype', $.param(postData))
					.success(function (resp) {
						if (resp.success) {
							resolve();
						} else {
							reject(resp.message);
						}
					})
					.error(function () {
						reject('操作失败，请重试');
					});
			});
		};

		Plan.prototype.updatePlan = function(data) {
			var self = this;
			angular.forEach(data, function (value, key) {
				self.plan[key].checked = value.checked;
				self.plan[key].defaultValueExt = value.defaultValueExt;
			});
		};

		return Plan;


	}])
	.service('insuranceTyps', ['$http', '$q', function ($http, $q) {

		var allTypes = {};
		var companyCityId = '';

		return {
			getInsurances: function (cid) {
				companyCityId = cid;
				return $q(function (resolve, reject) {
					if (allTypes[cid]) {
						resolve(allTypes[cid]);
					} else {
						allTypes[cid] = {};
						http($http).post(angular.path + '/companycity/update/gettypes', {companyCityId: cid})
							.success(function (resp) {
								angular.forEach(resp, function (t) {
									allTypes[cid][t.typeId] = t;
									allTypes[cid][t.typeId].childs = [];
								});

								angular.forEach(allTypes[cid], function (v, k) {
									if (v.parentId) {
										// allTypes[cid][k].parent = allTypes[cid][v.parentId];
										allTypes[cid][v.parentId].childs.push(v);
										delete allTypes[cid][k];
									}
								});

								resolve(allTypes[cid]);
							})
							.error(function () {
								reject('操作失败，请重试');
							});
					}
				});
			},
			saveSelected: function (cid) {
				return $q(function (resolve, reject) {
					var postData = {}, i = 0;
					angular.forEach(allTypes[cid], function (value) {
						if (value.checked) {
							postData['types['+i+'].insTypeId'] = value.typeId;
							postData['types['+i+'].defaultValue'] = value.defaultValueExt;
							i += 1;
						}
						if (value.childs.length > 0) {
							angular.forEach(value.childs, function (c) {
								if (c.checked) {
									postData['types['+i+'].insTypeId'] = c.typeId;
									postData['types['+i+'].defaultValue'] = c.defaultValueExt;
									i += 1;
								}
							});
						}
					});
					postData.companyCityId = companyCityId;
					http($http).post(angular.path + '/companycity/update/do/type/1.0', postData)
						.success(function (resp) {
							if (resp.success) {
								resolve();
							} else {
								reject(resp.message);
							}
						})
						.error(function () {
							reject('操作失败，请重试');
						});
				});
			},
			updateTypes: function (cid, data) {
				angular.forEach(data, function (value, key) {
					allTypes[cid][key].checked = value.checked;
				});
			}
		};

	}]);

});