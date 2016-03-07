/**
 * @author : 叶阳
 * @created : 2015/03/25
 * @version : v1.0
 * @desc : 车辆认证列表
 */
define(function(require){
    require('js/service/car/carManager');

    var myApp = angular.module("App");
    myApp.controller("mainCtrl",["$scope","$http","carManager", '$filter',function($scope,$http,carManager,$filter){

        $scope.carManager = carManager;

        //初始化过滤的条件
        var filterData = $scope.filterData = {
            auditStatus: angular.just ? 1 : '',
            carNum:"",//车牌号
            pageSize:20,
            page:1,
            startDate: '',
            endDate: ''
        };

        var timeFlag = $scope.timeFlag = {
            start: false,
            end: false
        };
        $scope.toggleCal = function (e, flag) {
            e.preventDefault();
            e.stopPropagation();
            timeFlag[flag] = !timeFlag[flag];
        };

        carManager.getCars(filterData);

        $scope.pagination = {
            currentPage: $scope.filterData.page,
            totalItems: $scope.carManager.totalSize,
            pageSize: $scope.carManager.filterData.pageSize
        };

        $scope.toPage = function(){
            carManager.getCars(filterData);
        };

        $scope.search = function(){
            filterData.startDate = $filter('date')(filterData.startDate, 'yyyy-MM-dd');
            filterData.endDate = $filter('date')(filterData.endDate, 'yyyy-MM-dd');
            carManager.getCars(filterData);
        };
        
        $scope.selectAuditStatus = function(){
            filterData.carNum = "";
            //var index = 0;
            //for (var i = 0; i < carManager.statusEnum.length; i++) {
            //    if(carManager.statusEnum[i] == filterData.auditStatus){
            //        index = i;
            //        break;
            //    }
            //};
            var tmpData = angular.copy(filterData);
            //angular.extend(tmpData, {auditStatus:index});
            
            carManager.getCars(tmpData);
        };
    }]);
});
