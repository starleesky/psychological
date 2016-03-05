define(function (require) {

    var http = require('bower_components/angularext/angularExt').http;

    angular.module('App').
        service('compCityRateServ', ['$http', '$q', function ($http) {
            return {
                showHis:function(param){
                    return http($http).post(angular.path+'/companyCityRate/rateLog',param);
                }
            }
        }])



});