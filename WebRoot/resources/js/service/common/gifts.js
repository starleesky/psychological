define(function (require) {

  angular.module('App')
    .service('gifts', ['$http', function ($http) {

      var fits, wash;

      this.getAllMaintainCouponTypes = function () {

        if (!fits) {
          fits = [];
          $http.get(angular.path + '/common/get/promotion/maintainCouponTypes')
            .success(function (resp) {
              angular.forEach(resp.data, function (c) {
                fits.push(c);
              });
            })
            .error(function () {
              alert('获取保养券活动赠品列表失败');
            });
        }

        return fits;

      };

      this.getAllWashcarCouponTypes = function () {

        if (!wash) {
          wash = [];
          $http.get(angular.path + '/common/get/promotion/washcarCouponTypes')
            .success(function (resp) {
              angular.forEach(resp.data, function (c) {
                wash.push(c);
              });
            })
            .error(function () {
              alert('获取洗车券活动赠品列表失败');
            });
        }

        return wash;

      };

    }]);

});