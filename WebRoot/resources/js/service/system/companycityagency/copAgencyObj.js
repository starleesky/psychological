define(function (require) {
    var http = require('bower_components/angularext/angularExt').http;
    angular.module('App').
        service('copAgencyObj', ['$q', '$http', function ($q, $http) {

            // һ����˾������࣬��service����һ��������ʵ��
            function CopAgencyObj() {

                this.status = 0; // 0����, 1������, 2����
                this.totalSize = 0;
                this.filter = {
                    pageSize: 10,   // default 20
                    page: 1,
                    companyName: '',
                    cityId: ''
                };

                this.companys = [];

            };

            // ���ݹ������е������������������˾�б�
            CopAgencyObj.prototype.getCompanys = function() {
                var self = this;
                self.status = 1;
                return $q(function (resolve, reject) {
                    http($http).post(angular.path + '/system/companycityagency/list/getpage', self.filter)
                        .success(function (resp) {
                            if (resp) {
                                self.companys = resp.data;
                                self.status = 0;
                                self.totalSize = parseInt(resp.totalSize);
                                self.pageSize = parseInt(resp.pageSize);
                                resolve(resp.data);
                            } else {
                                reject(resp.message);
                            }
                        })
                        .error(function (resp) {
                            self.status = 2;
                            reject(resp.message);
                        });
                });

            };

            // ��ҳ
            CopAgencyObj.prototype.toPage = function(page) {
                // this.page = page;
                this.getCompanys();
            };

            // ������ʱ��ҳ������1
            CopAgencyObj.prototype.search = function() {
                this.page = 1;
                this.getCompanys();
            };

            return new CopAgencyObj();

        }]);
});