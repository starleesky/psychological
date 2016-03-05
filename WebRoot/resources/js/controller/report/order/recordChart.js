define(function (require) {
    require('js/service/angular-chart/angular-chart');
    require('js/service/commonList');
    require('js/service/common/orderStatus');
    require('js/service/common/citys');
    require('js/service/common/activeCompany');
    require('js/directives/filter-select');

    var myApp = angular.module('App');

    myApp.config(['ChartJsProvider', function (ChartJsProvider) {
        // Configure all charts
        ChartJsProvider.setOptions({
            //colours: ['#FF5252', '#FF8A80'],
            responsive: true
        });
        // Configure all line charts
        ChartJsProvider.setOptions('Line', {
            datasetFill: true
        });
    }])
    myApp.controller('mainCtrl', ['$scope', 'commonList', '$timeout', 'orderStatus', 'citys', 'activeCompanys','$filter',
        function ($scope,commonList, $timeout, orderStatus, citys, activeCompanys,$filter) {
            $scope.listType =0;

            $scope.statusList = orderStatus.getAllStatus();
            $scope.cityList = citys.getAllCitys();
            $scope.companys = activeCompanys.getAllActiveCompanys();
            $scope.showFlg =1;

            $scope.choseCity = function () {
                activeCompanys.getCompanysByCityId(list.filter.cityId)
                    .then(function (list) {
                        $scope.companys = list;
                    }, function (msg) {
                        alert(msg);
                    });
                $scope.list.fetch();
            };

            var list = $scope.list = commonList;
            list.filter.platNoOrOrderNo = '';
            list.filter.cityId = '';
            list.filter.orderFlag = '';
            list.url = "/order/audit/list/getdata";

            list.fetch();

            $scope.labels = ['2015-08-01','2015-08-02','2015-08-03','2015-08-04','2015-08-05','2015-08-06','2015-08-07','2015-08-08','2015-08-09','2015-08-10','2015-08-11'];
            $scope.series = ['车险订单统计'];
            $scope.series1 = ['订单金额'];
            $scope.series2 = ['订单金额','保单面额'];

            $scope.data = [
                [6, 135, 8, 11, 26, 35, 40,12,43,50,100],
                //[28, 48, 40, 19, 86, 27, 90]
            ];
            $scope.data2 = [
                [26, 35, 0, 21, 26, 65, 80],
                [28, 48, 40, 19, 86, 27, 40]
            ];
            $scope.onClick = function (points, evt) {
                console.log(points, evt);
            };
            $scope.tabChange = function(type){
                $scope.listType = type;
                $scope.list.filter.startDate ='';
                $scope.list.filter.endDate ='';
            }

            $scope.seachPoint = function(){
                var dayAry = new Array();
                var dateFilter = $filter('date');
                var st1 = $scope.list.filter.startDate ;
                var ed1 = $scope.list.filter.endDate;
                var st = angular.copy($scope.list.filter.startDate );
                var ed = angular.copy($scope.list.filter.endDate );
                dayAry.push(dateFilter($scope.list.filter.startDate,'yyyy-MM-dd'));
                while(st.setDate(st.getDate()+1) <=ed.setDate(ed.getDate())){
                    dayAry.push(dateFilter(st,'yyyy-MM-dd'));
                }
                $scope.labels = dayAry;
            }

        }])
});