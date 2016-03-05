define(function (require, exports, module) {

	var ex;

	try {
		ex = angular.module('ddcx.filters');
	} catch (e) {
		ex = angular.module('ddcx.filters', []);
	}

	module.exports = ex;

});