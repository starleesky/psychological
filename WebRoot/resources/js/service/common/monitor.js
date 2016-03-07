/**
 * 监控
 */
define(function (require) {

	var http = require('bower_components/angularext/angularExt').http;
	
	angular.module('App').
	service('monitor', ['$http', '$q','$interval', function ($http, $q,$interval) {
		
		var getData = function(){
			$http.get(angular.path+"/car/list/getAuditCount").success(function(resp){
				if(resp.success && resp.data>0){
					alert("您有新的审核任务，请及时处理，当前审核任务"+resp.data+"条");
				}
			});
		}

		this.startUp = function(){
			$interval(function(){
				getData();
			},60*1000);
			//getData();
		}
	}]);
});