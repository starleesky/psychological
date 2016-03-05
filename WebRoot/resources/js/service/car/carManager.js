/**
 * @author : 叶阳
 * @created : 2015/03/25
 * @version : v1.0
 * @desc : 车辆列表服务层
 */
define(function(require){
    var http = require('bower_components/angularext/angularExt').http;

    angular.module("App").
        service("carManager",["$http", '$q', function($http, $q){

            function CarManager(){
                this.status = 0;//0正常, 1交互中, 2错误
                this.totalSize = 0;
                this.license = {};
                this.vehicleUse = {};
                this.types = {};
                this.statusEnum = ["未认证","待认证","认证成功","认证失败","行驶证已失效"];
                this.filterData = {
                    carNum:"",//车牌号
                    startDate:"",
                    endDate:"",
                    auditStatus:"",
                    cityId:"",
                    pageSize:12,
                    page:1
                };
            }

            /**
             * [filterParam 过滤值为空的参数]
             * @param  {[object]} params 需要过滤的参数对象 
             * @return {[object]}
             */
            function filterParam(params){
                var tmpParams = params;
                for(k in tmpParams){
                    if(tmpParams[k] == "" && typeof tmpParams[k]=="string"){
                        delete tmpParams[k];
                    }
                }
                return tmpParams;
            }

            /**
             * 获取车辆列表
             * @param filterData 过滤信息
             * {
             *       status:"",//车辆认证状态
             *       carNo:"",//车牌号
             *       pageSize:1,
             *       page:1
             *   }
             */
            CarManager.prototype.getCars = function(filterData){
                var self = this;
                self.status = 1;
                self.filterData = angular.extend(self.filterData,filterData);
                self.filterData.type = angular.listType;
                http($http).post(angular.path+"/car/list/getdata",filterParam(self.filterData))
                    .success(function(resp){
                            self.cars = resp.data;
                            self.totalSize = resp.totalSize;
                        
                    })
                    .error(function(){
                        self.status = 2;
                    });
            };

            /**
             * 获取单量车信息
             * @param condition 条件
             * {
             *    id://可为空
             *    carNo://可为空
             * }
             */
            CarManager.prototype.getCar = function(condition){
                var self = this;
                self.status = 1;
                return $q(function (resolve, reject) {
                    http($http).post(angular.path+"/car/detail/get",filterParam(condition))
                        .success(function(resp){
                            if(resp.success&&resp.data!=null){
                                resp.data.auditStatusText = self.statusEnum[resp.data.auditStatus]
                                self.carDetail = resp.data;
                                resolve();
                            }
                            
                        })
                        .error(function(resp){
                            self.status = 2;
                            reject(resp.message);
                        });
                });
            };

             /**
             * 获取单量车被修改信息的日志
             * @param condition 条件
             * {
             *    id://可为空
             *    carNo://可为空
             * }
             */
            CarManager.prototype.getCarModifyLogs = function(condition){
                var self = this;
                self.status = 1;
                http($http).post(angular.path+"/car/detail/get/modifyLogs",filterParam(condition))
                    .success(function(resp){
                        if(resp.success&&resp.data!=null){
                            self.carDetailModifyLogs = resp.data;
                        }
                        
                    })
                    .error(function(resp){
                        self.status = 2;
                    });
            };

            /**
             * [getLicenseInfo 获取车辆行驶证信息]
             * @param  {[object]} {id,carNum} 行驶证id或者
             */
            CarManager.prototype.getLicenseInfo = function(condition,callback){
                var self = this;
                self.status = 1;
                http($http).post(angular.path+"/car/detail/get/license",filterParam(condition))
                    .success(function(resp){
                        if(resp.success){
                            self.license = resp.data || {};
                            if(typeof callback == "function"){
                                callback();
                            }
                        }
                    })
                    .error(function(){
                        self.status = 2;
                    });
            };

            /**
             * [getCarUse 获取车辆用途]
             */
            CarManager.prototype.getVehicleUse = function(){
                var self = this;
                self.status = 1;
                http($http).post(angular.path+"/car/detail/get/vehicleuse",{})
                    .success(function(resp){
                        if(resp.success){
                            self.vehicleUse = resp.data || {};
                        }
                    })
                    .error(function(){
                        self.status = 2;
                    });
            };

            /**
             * [getCarUse 获取车辆类型]
             */
            CarManager.prototype.getTypes = function(){
                var self = this;
                self.status = 1;
                http($http).post(angular.path+"/car/detail/get/vehicletype",{})
                    .success(function(resp){
                        if(resp.success){
                            self.types = resp.data || {};
                        }
                    })
                    .error(function(){
                        self.status = 2;
                    });
            };

            return new CarManager();

        }]);
});

