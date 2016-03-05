define(function (require) {

  var http = require('bower_components/angularext/angularExt').http;

  angular.module('App')
    .service('carService', ['$http', '$q',
      function ($http, $q) {

        this.getCarInfoByVin = function (vinNum) {
          return $q(function (resolve, reject) {
            http($http).post(angular.path + '/car/detail/get/license', {vin: vinNum})
              .success(function (resp) {
                if (resp.success && resp.data) {
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

        // 获取使用性质
        this.getVehicleUse = function () {
          return $q(function (resolve, reject) {
            http($http).post(angular.path+"/car/detail/get/vehicleuse",{})
              .success(function(resp){
                if(resp.success){
                  resolve(resp.data);
                } else {
                  reject(resp.message);
                }
              })
              .error(function(){
                reject(resp.message);
              });
          });
        };

        /**
         * 获取车辆类型
         */
        this.getTypes = function(){
          return $q(function (resolve, reject) {
            http($http).post(angular.path+"/car/detail/get/vehicletype",{})
              .success(function(resp){
                if(resp.success){
                  resolve(resp.data);
                } else {
                  reject(resp.message);
                }
              })
              .error(function(){
                reject(resp.message);
              });
          });
        };

        this.editLicense = function(data) {
          return $q(function (resolve, reject) {
            http($http).post(angular.path + '/car/license/update/do', data)
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

      }
    ])

});