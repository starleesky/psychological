/**
 * @author : 叶阳
 * @created : 2015/03/31
 * @version : v1.0
 * @desc : 车辆信息认证
 */
define(function(require){
    var utils = require("bower_components/utils/BrowserUtils");
                require('js/service/car/carManager');
                require('js/service/common/monitor');

    var myApp = angular.module("App");
    myApp.controller("mainCtrl",["$scope","$http","carManager", "$modal","monitor", function($scope,$http,carManager, $modal,monitor){

        $scope.carManager = carManager;

        

        var carId = utils.getUrlParam("carId");

        if(carId == null){
            return ;
        }
        
        //初始化过滤的条件
        var condition = {
            carId:carId
        };

        carManager.getCar(condition);
        carManager.getCarModifyLogs(condition);

        $scope.showImg = function (url) {
            $modal.open({
                templateUrl: angular.path + '/resources/templates/utils/show-img-modal.html',
                controller: 'showImgnCtrl',
                backdrop: 'static',
                size: 'lg',
                resolve: {
                    imgInfo: function () {
                        return {
                            url : url,
                            downloadUrl: url + '?_upd=true'
                        }
                    }
                }
            });
        };

        monitor.startUp();

    }])
      .controller('showImgnCtrl', ['$scope', '$modalInstance', 'imgInfo',
          function ($scope, $modalInstance, imgInfo) {
              $scope.imgInfo = imgInfo;

              $scope.cancel = function () {
                  $modalInstance.dismiss();
              }

          }
      ]);
});