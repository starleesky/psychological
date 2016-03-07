define(function (require) {
	
	require('js/service/commonList');
	require('js/service/common/citys');
	require('js/service/common/activeCompany');
	require('js/directives/filter-select');
	require('js/service/common/monitor');

	angular.module('App')

	.controller('mainCtrl', ['$scope','$http', 'commonList','$timeout','$modal',
		function ($scope,$http, commonList, $timeout,$modal) {

			var list = $scope.list = commonList;
			list.filter.key = '';
			list.url = "/system/city/list/getData";
			
			list.fetch();

			var param = $scope.param = {};

			var modal = null,index = null;
			$scope.edit = function (rw,$index) {
				$scope.param = angular.copy(rw);
				if($scope.param.cityIdOther == 0){
					$scope.param.cityIdOther = '';
				}
				index = $index;
	            modal = $modal.open({
	                templateUrl: angular.path + '/resources/templates/system/city/edit-city.html?data='+new Date(),
	                backdrop: 'static',
	                scope:$scope,
	                size: 'lg'
	            });
	        };

	        $scope.addNew = function () {
	        	if($scope.carCitys.length == 0){
	        		alert("没有可添加城市");
	        		return ;
	        	}
			  	var modal = $modal.open({
			  		templateUrl: angular.path + '/resources/templates/system/city/new-city.html?data='+new Date(),
			  		controller: 'addNewCtrl',
			  		backdrop: 'static',
			  		resolve: {
			  			scope: function () {
 							return $scope;
 						}
			  		}
			  	});
			  	// modal.result.then(function (data) {
			  	// 	companyManager.companys.unshift(data);
			  	// });
			 };

	        //取消处理
	        $scope.cancel = function(){
	            modal.close();
	        }

	        //更新
	        $scope.updateParam = function(){
	       		$scope.invalidCityIdOther = false;
				$scope.invalidBuyExpireDate = false;
				$scope.invalidActivityName = false;
				if($scope.param.cityIdOther !=""){
					if(isNaN($scope.param.cityIdOther)){
						$scope.invalidCityIdOther = true;
					}
				}
				//填写促销url后名称不能为空
				if ($scope.param.activityUrl !="" && $scope.param.activityName == "") {
					$scope.invalidActivityName = true;
				}

				if($scope.param.buyExpireDate == "" ){
					$scope.invalidBuyExpireDate = true;
				}else{
					if(isNaN($scope.param.buyExpireDate) || $scope.param.buyExpireDate > 365 || $scope.param.buyExpireDate <= 0){
						$scope.invalidBuyExpireDate = true;
					}
				}

				if($scope.invalidCityIdOther || $scope.invalidBuyExpireDate || $scope.invalidActivityName){
					return ;
				}

				if($scope.param.cityIdOther == "" || typeof $scope.param.cityIdOther=="undefined"){
					$scope.param.cityIdOther = 0;
				}

	        	$http.post(angular.path+"/system/city/update",$scope.param).success(function(resp){
	        		if(resp.success){
	        			if(index>=0&&list.data.data.length>0){
	        				list.data.data[index] = angular.copy($scope.param);
	        			}
	        			modal.close();
	        		}else{
	        			alert(resp.message);
	        		}
	        	});
	        }

			//获取可创建车险城市列表
	        $scope.getCarCitys = function(){
	        	var citys = [];
				$http.get(angular.path + '/system/city/list/getCarCity?data='+new Date())
					.success(function (data) {
						angular.forEach(data, function (value) {
							citys.push(value);
						});
						$scope.carCitys = citys;

					})
					.error(function () {
						alert("获取可创建车险城市列表失败");
					});
			}

			$scope.getViewCityList = function(){
				$http.get(angular.path + '/system/city/list/getSelectCityList')
				.success(function (data) {
					$scope.selectCitys = data;
				})
				.error(function () {
					alert("获取城市列表异常");
				});
			}

			$scope.getCarCitys();
			$scope.getViewCityList();
		}
	])
	.controller('addNewCtrl', ['$scope', '$http','$modalInstance','scope',
		function ($scope, $http ,$modalInstance, scope) {

			$scope.carCitys = scope.carCitys;
			var data = $scope.data = {
				cityId: 0,
				cityName: '',
				cityIdOther: '',
				buyExpireDate: '',
				valid: 0,
				activityName: '',
				activityUrl: '',
			};

			if(null != scope.carCitys && scope.carCitys.length > 0){
				data.cityId = scope.carCitys[0].cityId;
			}

			$scope.add = function () {
				$scope.invalidCityIdOther = false;
				$scope.invalidBuyExpireDate = false;
				$scope.invalidActivityName = false;
				if(data.cityIdOther !="" || typeof data.cityIdOther !="undefined"){
					if(isNaN(data.cityIdOther)){
						$scope.invalidCityIdOther = true;
					}
				}
				//填写促销url后名称不能为空
				if (data.activityUrl !="" &&  data.activityName == "") {
					$scope.invalidActivityName = true;
				}

				if(data.buyExpireDate == "" || typeof data.buyExpireDate=="undefined"){
					$scope.invalidBuyExpireDate = true;
				}else{
					if(isNaN(data.buyExpireDate)|| data.buyExpireDate > 365 || data.buyExpireDate <= 0){
						$scope.invalidBuyExpireDate = true;
					}
				}

				if($scope.invalidCityIdOther || $scope.invalidBuyExpireDate || $scope.invalidActivityName){
					return ;
				}
				
				if(data.cityIdOther == "" || typeof data.cityIdOther=="undefined"){
					data.cityIdOther = 0;
				}

				angular.forEach($scope.carCitys, function (c) {
					if (data.cityId == c.cityId) {
						data.cityName = c.cityName;
					}
				});
				$http.post(angular.path + '/system/city/add',$scope.data)
					.success(function (resp) {
						if(resp.success){
							data.id = resp.data;
							scope.list.data.data.unshift(data);
							scope.getCarCitys();
						}else{
							alert("创建失败，请重试");
						}
					})
					.error(function () {
						alert("创建失败，请重试");
					}
				);

				$modalInstance.close(data);

			};

			$scope.cancel = function () {
				$modalInstance.dismiss();
			};

		}
	]);

});	