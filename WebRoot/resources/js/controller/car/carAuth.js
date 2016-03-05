/**
 * @author : 叶阳
 * @created : 2015/04/01
 * @version : v1.0
 * @desc : 车辆认证
 */
define(function(require){
    var http = require('bower_components/angularext/angularExt').http;
    var utils = require("bower_components/utils/BrowserUtils");
                require('js/service/car/carManager');
                require('js/directives/image-viewer');
                require('js/directives/specialChar');

    var myApp = angular.module("App");
    myApp.controller("mainCtrl",["$scope","$http","carManager","$timeout",function($scope,$http,carManager,$timeout){

        $scope.carManager = carManager;
        $scope.submitted = false;

        var item = $scope.item = {
            popover: false,
            licenseShow: true,
            showSelfReason: true,
            hideSuccess: true,
            hideError: true,
            hideAgreeBtn:false
        };
        item.optionTypes = [
            {code: "通过", value: 2},
            {code: "不通过", value: 3}
        ];
        var licenseInfo = $scope.licenseInfo = {
            vehicleTypeName: "0",
            useCharacterName: "0",
            engineNo: '',
            auditStatus: item.optionTypes[0]
        };
            $scope.format = 'yyyy-MM-dd';

        var carId = utils.getUrlParam("carId");
        if(carId == null){
            return ;
        }
        item.detailHref = angular.path + "/car/detail?carId="+carId;

        //初始化过滤的条件
        var carCondition = {
            carId:carId
        };
        //获取车辆信息
        carManager.getCar(carCondition)
            .then(function () {
                licenseInfo.plateNumber = carManager.carDetail.carNum;
                licenseInfo.userPhone = carManager.carDetail.userPhone;
            });
        carManager.getVehicleUse();
        carManager.getTypes();
        http($http).post(angular.path+"/car/detail/get/failreason",{})
                    .success(function(resp){
                        if(resp.success){
                            $scope.failReason = resp.data || {};
                        }
                    });

        /**
         * [输入车架号查询是否已认证过]
         */
        $scope.vinBlur = function($event){
            if(licenseInfo.vin!=""&&typeof licenseInfo.vin !="undefined"){
                carManager.getLicenseInfo({vin:licenseInfo.vin},function(){
                    if(carManager.license != null && carManager.license.id>0){
                        $scope.item.licenseShow = false;
                    }
                });
            }else{
                $scope.item.licenseShow = true;
            }
        };

        $scope.open = function($event, type) {
            $event.preventDefault();
            $event.stopPropagation();

            $scope[type + 'opened'] = true;
          };

        /**
         * [selectAudit 选择通过或者不通过]
         */
        $scope.selectAudit = function(){
            if(licenseInfo.auditStatus.value == 3){
                item.reasonShow = true;
                item.hideAgreeBtn = true;
            }else{
                item.reasonShow = false;
                item.showSelfReason = true;
                item.hideAgreeBtn = false;
            }
            item.showMessage = true;
        };

        /**
         * [reasonChange description]
         */
        $scope.reasonChange = function($event){
            if($scope.failReason[licenseInfo.failReason-0].fieldValue == "自定义"){
                item.showSelfReason = false;
            }else{
                item.showSelfReason = true;
            }
            item.showMessage = true;
        };

        /**
         * [issueDateChange 选择发证日期]
         * @return {[type]} [description]
         */
        $scope.issueDateChange = function(){
            item.showMessage = true;
        };

        /**
         * [submitData 提交数据]
         */
        $scope.submitData = function(){

            $scope.submitted = true;

            if ($scope.form.$invalid && licenseInfo.auditStatus.value == 2) {
                return false;
            }

            if(item.isAjax == true){
                return false;
            }

            var status = licenseInfo.auditStatus.value;
            //获取失败原因
            var failReason = "";
            var saveData = {};
            if(status == 3){
                if(typeof licenseInfo.failReason== "undefined" || licenseInfo.failReason=="" || licenseInfo.failReason==null){
                    item.showMessage = false;
                    item.message = "请选择失败原因";
                    return ;
                }

                failReason = $scope.failReason[licenseInfo.failReason-0].fieldValue;
                if(failReason == "自定义"){
                    if(licenseInfo.selfFailReason == "" || typeof licenseInfo.selfFailReason == "undefined" || licenseInfo.selfFailReason==null){
                         item.showMessage = false;
                         item.message = "请填写失败原因";
                         return ;
                    }
                    failReason = licenseInfo.selfFailReason;
                }
                saveData = {
                    status: status,
                    userCarId: carId,
                    failReason: failReason
                };
            }else{
                //发证日期不能早于已认证的发证日期
                var orgLicenseInfo = carManager.license;
                if(typeof orgLicenseInfo != "undefined"&&orgLicenseInfo!=null){
                    var issueDate = orgLicenseInfo.issueDate;
                    if(issueDate>licenseInfo.issueDate.getTime()){
                        item.showMessage = false;
                        item.message = "新发证日期不能早于已认证的发证日期";
                        return ;
                    }
                }

                var types = carManager.types[licenseInfo.vehicleTypeName - 0].fieldKey;
                var vehicleUse = carManager.vehicleUse[licenseInfo.useCharacterName - 0].fieldKey;
                var issueDate = getDataStr(licenseInfo.issueDate);
                var registerDate = getDataStr(licenseInfo.registerDate);
                    saveData = {
                        status: status,
                        userCarId: carId,
                        id: carManager.license.id > 0 ? carManager.license.id : "",
                        plateNumber: licenseInfo.plateNumber,
                        vehicleTypeKey: types,
                        useCharacterKey: vehicleUse,
                        owner: licenseInfo.owner,
                        address: licenseInfo.address,
                        model: licenseInfo.model,
                        vin: licenseInfo.vin,
                        engineNo: licenseInfo.engineNo,
                        registerDate: registerDate,
                        issueDate: issueDate,
                        failReason: failReason
                    };
            }

            

            //save
            item.isAjax = true;
            http($http).post(angular.path+"/car/license/do",saveData)
                    .success(function(resp){
                        if(resp.success){
                            $scope.item.hideError = true;
                            $scope.item.hideSuccess = false;
                            $timeout(function(){
                                window.history.go(-1);
                            },1000,false);
                        }else{
                            item.isAjax = false;
                            $scope.item.hideSuccess = true;
                            $scope.item.hideError = false;
                            $scope.item.saveErrorMsg = resp.message || "认证失败";
                        }
                    });
        };

        function getDataStr(d){
            return d.getFullYear() + '-' + ((d.getMonth() + 1)<10?"0"+(d.getMonth() + 1):(d.getMonth() + 1)) + '-' + (d.getDate());
        }

    }]);
});