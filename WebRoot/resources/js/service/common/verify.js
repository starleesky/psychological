define(function (require) {

    angular.module('App')
        .service('verifyWay', ['$http', function ($http) {

            var verifyWay;

            this.getAllVerify = function () {

                if (!status) {
                    verifyWay = [];
                    $http.get(angular.path + '/common/get/verify/allverify')
                        .success(function (resp) {
                            if (resp instanceof Array) {
                                angular.forEach(resp, function (s) {
                                    verifyWay.push(s);
                                });
                            }
                        })
                        .error(function () {
                            alert('获取核保状态失败');
                        });
                }
                return verifyWay;

            };

        }]);

});