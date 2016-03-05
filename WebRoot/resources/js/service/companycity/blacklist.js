define(function (require) {

  var http = require('bower_components/angularext/angularExt').http;

  angular.module('App')
  .service('blackList', ['$http', '$q',
    function ($http, $q) {

      function BlackListService(companyCityId) {
        this.companyCityId = companyCityId;
        //this.getBrandList();
        this.brands = {};
        this.blackedBrands = {};
        this.doing = false;
      }

      // 获取没有控保的品牌列表
      BlackListService.prototype.getBrandList = function () {
        var self = this;
        self.doing = true;
        return $q(function (resolve, reject) {
          $http.get(angular.path + '/car/modelblack/brand/list?companyCityId=' + self.companyCityId)
            .success(function (resp) {
              if (resp.success) {
                angular.forEach(resp.data, function (b) {
                  b.open = false;
                  self.brands[b.brandId] = b;
                });
                resolve();
              } else {
                alert(resp.message);
                reject();
              }
              self.doing = false;
            })
            .error(function (resp) {
              alert(resp.message);
              reject();
              self.doing = false;
            });
        });
      };

      // 获取某品牌下没控保的车系列表
      BlackListService.prototype.getSeriesListByBrandId = function (bid) {
        var self = this;
        //if (!self.brands[bid].series) {
          self.brands[bid].series = {};
        //}
        self.doing = true;
        $http.get(angular.path + '/car/modelblack/series/list?companyCityId=' + self.companyCityId + '&brandId=' + bid)
          .success(function (resp) {
            if (resp.success) {
              angular.forEach(resp.data, function (s) {
                s.open = false;
                self.brands[bid].series[s.seriesId] = s;
              });
              console.log(self);
            } else {
              alert(resp.message);
            }
            self.doing = false;
          })
          .error(function (resp) {
            alert(resp.message);
            self.doing = false;
          });
      };

      // 获取已经控保的品牌列表
      BlackListService.prototype.getBlackedBrandList = function () {
        var self = this;
        self.doing = true;
        return $q(function (resolve, reject) {
          $http.get(angular.path + '/car/modelblack/list/getbrand?companyCityId=' + self.companyCityId)
            .success(function (resp) {
              if (resp.success) {
                angular.forEach(resp.data, function (b) {
                  b.open = false;
                  self.blackedBrands[b.brandId] = b;
                });
                resolve();
              } else {
                alert(resp.message);
                reject();
              }
              self.doing = false;
            })
            .error(function (resp) {
              alert(resp.message);
              reject();
              self.doing = false;
            });
        });
      };

      BlackListService.prototype.getBlackedSeriesListByBrandId = function (bid) {
        var self = this;
        //if (!self.blackedBrands[bid].blackSeries) {
          self.blackedBrands[bid].blackSeries = {};
        //}
        self.doing = true;
        $http.get(angular.path + '/car/modelblack/list/getseries?companyCityId=' + self.companyCityId + '&brandId=' + bid)
          .success(function (resp) {
            if (resp.success) {
              angular.forEach(resp.data, function (s) {
                s.open = false;
                self.blackedBrands[bid].blackSeries[s.seriesId] = s;
              });
            } else {
              alert(resp.message);
            }
            self.doing = false;
          })
          .error(function (resp) {
            alert(resp.message);
            self.doing = false;
          });
      };

      // 增加黑名单项
      BlackListService.prototype.addBlack = function (bid, sid) {
        var self = this;
        self.doing = true;
        return $q(function (resolve, reject) {
          http($http).post(angular.path + '/car/modelblack/add/do', {
            companyCityId: self.companyCityId,
            brandId: bid,
            seriesId: sid
          })
            .success(function (resp) {
              if (resp.success) {
                resolve(resp.data.id);
              } else {
                reject(resp.message);
              }
              self.doing = false;
            })
            .error(function (resp) {
              reject(resp.message);
              self.doing = false;
            });
        });
      };

      // 删除黑名单项
      BlackListService.prototype.removeBlack = function (id) {
        var self = this;
        self.doing = true;
        return $q(function (resolve, reject) {
          http($http).post(angular.path + '/car/modelblack/delete/do', {
            id: id
          })
            .success(function (resp) {
              if (resp.success) {
                resolve();
              } else {
                reject(resp.message);
              }
              self.doing = false;
            })
            .error(function (resp) {
              reject(resp.message);
              self.doing = false;
            });
        });
      };

      return BlackListService;

    }
  ])

});