define(function (require) {

	var http = require('bower_components/angularext/angularExt').http;

	angular.module('App').
	service('commonList', ['$http', function ($http) {

		function getFormatedDate(date) {
			return date instanceof Date ? date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate() : null;
		}

		function getFilterData(filter) {
			var f = angular.copy(filter);

			// if( !f['condition'] ){
				f.startDate = getFormatedDate(f.startDate);
				f.endDate = getFormatedDate(f.endDate);
				f.startCompareDate = getFormatedDate(f.startCompareDate);
				f.endCompareDate = getFormatedDate(f.endCompareDate);
			// }
			return f;
		}
		
		this.url = '';
		this.dateFormat = 'yyyy-MM-dd';
		this.startopened = this.endopened = false;
		this.startCompareopened = this.endCompareopened = false;
		this.fetching = false;

		this.data = {
			totalSize: 1,
			totalPage: 1
		};

		this.filter = {
			page: 1,
			pageSize: 20,
			startDate: '',
			endDate: '',
			startCompareDate: '',
			endCompareDate: ''
		};

		this.fetch = function () {

			// 过滤空参数
			var theFilter = angular.copy(this.filter);
			for (var key in theFilter) {
				if (!theFilter[key]) {
					delete theFilter[key];
				}
			}

			var self = this,
				httpObj;
			self.fetching = true;
			// Content-Type josn
			if( theFilter['condition'] ){
				httpObj = $http
			}else{
				httpObj = http($http)
			}
			httpObj.post(angular.path + this.url, getFilterData(theFilter))
				.success(function (resp) {
					self.data = resp;
					self.fetching = false;
				})
				.error(function () {
					self.fetching = false;
				});
		};

		this.search = function () {
			this.filter.page = 1;
			this.fetch();
		};

		this.openCal = function (event, type) {
			event.preventDefault();
			event.stopPropagation();

			this[type + 'opened'] = true;
		};

		// return this;

	}]);

});