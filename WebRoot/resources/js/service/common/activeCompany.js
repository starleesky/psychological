define(function (require) {

  angular.module('App')
    .service('activeCompanys', ['$http', '$q', function ($http, $q) {

      var companys;

      this.getAllActiveCompanys = function () {

        if (!companys) {
          companys = [];
          $http.get(angular.path + '/common/get/activeCompany')
            .success(function (resp) {
              if (resp instanceof Array) {
                angular.forEach(resp, function (c) {
                  companys.push(c);
                });
              }
            })
            .error(function () {
              alert('获取所有公司列表失败');
            });
        }

        return companys;

      };

      this.getCompanysByCityId = function (cid) {

        return $q(function (resolve, reject) {
          $http.get(angular.path + '/common/get/activeCompany?cityId=' + cid)
            .success(function (resp) {
              if (resp instanceof Array) {
                resolve(resp);
              } else {
                reject(resp.message);
              }
            })
            .error(function (resp) {
              reject(resp.message);
            });
        });

      };

    }]);

});