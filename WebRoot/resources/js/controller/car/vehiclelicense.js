define(function (require) {

  require('js/directives/image-viewer');
  require('js/service/car/car');
  require('js/directives/specialChar');

  angular.module('App')
    .controller('mainCtrl', ['$scope', 'carService', '$timeout', '$filter',
      function ($scope, carService, $timeout, $filter) {

        var bak = angular.copy(angular.carInfo);
        console.log(bak);
        var licenseInfo = $scope.licenseInfo = angular.carInfo;
        licenseInfo.license.userCarId = licenseInfo.carId;
        $scope.dateObj = {
          registerDateObj: $filter('date')(licenseInfo.license.registerDate, 'yyyy-MM-dd'),
          issueDateObj: $filter('date')(licenseInfo.license.issueDate, 'yyyy-MM-dd')
        };
        //$scope.registerDateObj = $filter('date')(licenseInfo.license.registerDate, 'yyyy-MM-dd');
        //$scope.issueDateObj = $filter('date')(licenseInfo.license.issueDate, 'yyyy-MM-dd');

        $scope.today = new Date();

        function initOld() {
          $scope.old = {
            active: false,
            disabled: true,
            showImg: false,
            license: null
          };
        }
        initOld();

        carService.getVehicleUse()
          .then(function (data) {
            $scope.vehicleUse = data;
          }, function (msg) {});

        carService.getTypes()
          .then(function (data) {
            $scope.carTypes = data;
          }, function (msg) {});

        $scope.getThisVin = function (e) {
          e.preventDefault();

          // 如果vin一致，不请求
          if (licenseInfo.license.vin === bak.license.vin) {
            initOld();
            return;
          }

          carService.getCarInfoByVin($scope.licenseInfo.license.vin)
            .then(function (data) {
              $scope.old.license = data;
              if (confirm('该车辆识别代号已存在，是否替换当前数据')) {
                var sDate = licenseInfo.license.registerDate;
                var eDate = licenseInfo.license.issueDate;
                angular.extend(licenseInfo.license, $scope.old.license);
                licenseInfo.license.registerDate = sDate;
                licenseInfo.license.issueDate = eDate;
              }
            }, function () {
              initOld();
            });

        };

        function fixDate() {
          licenseInfo.license.registerDate = $filter('date')($scope.dateObj.registerDateObj, 'yyyy-MM-dd');
          licenseInfo.license.issueDate = $filter('date')($scope.dateObj.issueDateObj, 'yyyy-MM-dd');
        }

        $scope.submitted = $scope.submitting = false;
        $scope.submit = function (e) {
          e.preventDefault();

          $scope.submitted = true;
          if ($scope.edit_form.$invalid) {
            return;
          }
          fixDate();
          $scope.submitting = true;
          carService.editLicense(licenseInfo.license)
            .then(function () {
              alert('保存成功');
              $scope.submitting = false;
              window.location.href = angular.path + '/car/detail?carId=' + bak.carId;
            }, function (msg) {
              alert(msg);
              $scope.submitting = false;
            });

        };

      }
    ])

});