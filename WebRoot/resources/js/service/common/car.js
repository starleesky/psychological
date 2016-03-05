define(function (require) {
	
	angular.module('App')
	.service('cartype', ['$http', function ($http) {

		var brands, series = {}, models = {};

		this.getBrands = function () {
			if (!brands) {
				brands = {};
				$http.get(angular.path + '/common/get/car/getbrands')
					.success(function (resp) {
						if (resp instanceof Array) {
							angular.forEach(resp, function (b) {
								brands[b.brandId] = b;
							});
						} 
					})
					.error(function () {
						alert('获取车辆品牌列表出错');
					});
			}

			return brands;
		};

		this.getSeriesByBid = function (bid) {

			if (!series[bid]) {
				series[bid] = {};
				$http.get(angular.path + '/common/get/car/getseries?brandId=' + bid)
					.success(function (resp) {
						if (resp instanceof Array) {
							angular.forEach(resp, function (s) {
								series[bid][s.seriesId] = s;
							});
						}
					})
					.error(function () {
						alert('获取车系列表出错');
					});
			}

			return series[bid];

		};

		this.getModelsBySid = function (sid) {

			if (!models[sid]) {
				models[sid] = {};
				$http.get(angular.path + '/common/get/car/getmodels?seriesId=' + sid)
					.success(function (resp) {
						if (resp instanceof Array) {
							angular.forEach(resp, function (m) {
								models[sid][m.modelId] = m;
							});
						}
					})
					.error(function () {
						alert('获取车型列表出错');
					});
			}

			return models[sid];

		};

	}]);


});