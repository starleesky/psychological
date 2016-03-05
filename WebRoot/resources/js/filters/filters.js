define(function (require) {
	
	// var module = require('js/filters/filter-module');

	var module = angular.module('App');

	module
	.filter('percentage', function () {
		return function (input) {
			// if (input === 0) {
			// 	return '0.00%';
			// }
			// if (!input) {
			// 	return '';
			// }
			input = input || 0;
			input = parseFloat(input);
			return (input * 100).toFixed(2) + '%';
		};
	})
	.filter('discount', function () {
		return function (input) {
			if (!input) {
				return '';
			}
			input = parseFloat(input);
			return (input * 10).toFixed(2) + '折';
		};
	})
	.filter('milliseconds', function () {
		return function (input) {
			if (!input) {
				return '';
			}
			var date = new Date();
			date.setTime(input);
			return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
		};
	});

});