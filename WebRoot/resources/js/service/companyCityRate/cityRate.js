/**
 * Created by OneMTime on 15/7/14.
 */
define(function(require){
    var http = require('bower_components/angularext/angularExt').http;
    angular.module('App').
        service('cityRate',['$http',function($http){
            this.url ='';
            this.data={
                totalSize:1,
                totalPage:1
            };
            this.filter ={
                page:1,
                pageSize:20
            };

            this.searchFunc = function(){

                var temp = angular.copy(this.filter);
                for(var i in temp){
                    if(!temp[i]){
                        delete temp[i];
                    }
                }
                var self = this;

                http($http).post(angular.path + this.url, this.filter)
                    .success(function(result){
                        self.data = result;
                    })
                    .error(function(result){
                        console.log("error info : "+result);
                    })

            };

            this.search = function(){
                this.filter.page = 1;
                this.searchFunc();
            };




        }])
})