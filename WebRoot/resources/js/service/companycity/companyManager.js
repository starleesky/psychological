define(function (require) {
	var http = require('bower_components/angularext/angularExt').http;
	angular.module('App').
	service('companyManager', ['$q', '$http', function ($q, $http) {
		
		// 一个公司管理的类，该service返回一个这个类的实例
		function CompanyManager() {

			this.status = 0; // 0正常, 1交互中, 2错误
			this.totalSize = 0;
			this.filter = {
				pageSize: 20,   // default 20
				page: 1,
				startDate: '',
				endDate: '',
				companyName: '',
				cityId: ''
			};

			this.companys = [];

		};

		// 根据过滤器中的内容来跟服务端请求公司列表
		CompanyManager.prototype.getCompanys = function() {
			var self = this;
			self.status = 1;
			http($http).post(angular.path + '/companycity/list/getpage', self.filter)
				.success(function (resp) {
					self.companys = resp.data;
					self.status = 0;
					self.totalSize = parseInt(resp.totalSize);
				})
				.error(function () {
					self.status = 2;
				});
		};

		// 换页
		CompanyManager.prototype.toPage = function(page) {
			// this.page = page;
			this.getCompanys();
		};

		// 搜索的时候页码跳回1
		CompanyManager.prototype.search = function() {
			this.page = 1;
			this.getCompanys();
		};

		return new CompanyManager();

	}]);
});