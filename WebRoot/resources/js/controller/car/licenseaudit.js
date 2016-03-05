/**
 * @author : 叶阳
 * @created : 2015/03/25
 * @version : v1.0
 * @desc : 车辆认证列表
 */
define(function(require){
    require('js/service/car/carManager');
    require('js/service/commonList');
    require('js/service/common/monitor');

    var myApp = angular.module("App");
    myApp.controller("mainCtrl",["$scope","$http","carManager", '$filter', 'commonList','monitor',
    function($scope,$http,carManager,$filter, commonList,monitor){


        var list = $scope.list = commonList;
        list.filter.carNum = '';
        list.filter.auditStatus = '';
        list.filter.type = 1;
        list.url = "/car/license/audit/list/getdata";
        
        list.fetch();

        $scope.searchTypeTexts = {
            1: '车牌号',
            2: '操作人'
        };

        $scope.searchType = function (type) {
            list.filter.type = type;
            list.fetch();
        };

        monitor.startUp();
    }]);
});
