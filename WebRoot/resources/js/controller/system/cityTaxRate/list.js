define(function (require) {
	
	require('js/service/commonList');
	require('js/service/common/citys');
	require('js/service/common/activeCompany');
	require('js/directives/filter-select');
	require('js/service/common/monitor');

	angular.module('App')

	.controller('mainCtrl', ['$scope','$http', 'commonList', '$timeout','$modal',
		function ($scope,$http, commonList, $timeout,$modal) {

			var list = $scope.list = commonList;
			list.filter.key = '';
			list.url = "/system/cityTaxRate/list/getData";
			
			list.fetch();

			var param = $scope.param = {};

			var modal = null,index = null;
			$scope.edit = function (rw,$index) {
				$scope.param = angular.copy(rw);
				$scope.param.amount /= 100;
				index = $index;
	            modal = $modal.open({
	                templateUrl: angular.path + '/resources/templates/system/cityTaxRate/edit-cityTaxRate.html?data='+new Date(),
	                backdrop: 'static',
	                scope:$scope,
	                size: 'lg'
	            });
	        };

	        $scope.addNew = function () {
	        	if($scope.canBeAddCarCityList.length == 0){
	        		alert("没有可添加车船税城市。");
	        		return ;
	        	}
			  	var modal = $modal.open({
			  		templateUrl: angular.path + '/resources/templates/system/cityTaxRate/new-cityTaxRate.html?data='+new Date(),
			  		controller: 'addNewCtrl',
			  		backdrop: 'static',
			  		resolve: {
			  			scope: function () {
 							return $scope;
 						}
			  		}
			  	});
			 };


	        //取消处理
	        $scope.cancel = function(){
	            modal.close();
	        }

	        //更新
	        $scope.updateParam = function(){
	       		$scope.param.amount *= 100;
	        	$http.post(angular.path+"/system/cityTaxRate/update",$scope.param).success(function(resp){
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
	        $scope.getCanBeAddCarCityList = function(){
	        	var citys = [];
				$http.get(angular.path + '/system/cityTaxRate/getCanBeAddCarCityList?data='+new Date())
					.success(function (data) {
						angular.forEach(data, function (value) {
							citys.push(value);
						});
						$scope.canBeAddCarCityList = citys;

					})
					.error(function () {
						alert("获取可创建车险城市列表失败");
					});
			}

			//获取可拷贝车险城市列表
	        $scope.getCanBeCopyCarCityList = function(){
	        	var citys = [];
				$http.get(angular.path + '/system/cityTaxRate/getCanBeCopyCarCityList?data='+new Date())
					.success(function (data) {
						angular.forEach(data, function (value) {
							citys.push(value);
						});
						$scope.canBeCopyCarCityList = citys;
						$scope.selectCitys = citys;

					})
					.error(function () {
						alert("获取可创建车险城市列表失败");
					});
			}

			$scope.getCanBeAddCarCityList();
			$scope.getCanBeCopyCarCityList();

			$scope.initList = function(){
				list.fetch();
				$scope.getCanBeAddCarCityList();
				$scope.getCanBeCopyCarCityList();
			}
		}
	])
	.controller('addNewCtrl', ['$scope', '$http','$modalInstance','scope',
		function ($scope, $http ,$modalInstance, scope) {

			$scope.canBeAddCarCityList = scope.canBeAddCarCityList;
			$scope.canBeCopyCarCityList = scope.canBeCopyCarCityList;

			if($scope.canBeCopyCarCityList.length == 0){
				alert("没有可拷贝车船税城市。");
	        		return ;
			}
			var data = $scope.data = {
				addCityId : 0,
				copyCityId : 0
			};

			if(null != scope.canBeAddCarCityList && scope.canBeAddCarCityList.length > 0){
				data.addCityId = scope.canBeAddCarCityList[0].cityId;
			}

			if(null != scope.canBeCopyCarCityList && scope.canBeCopyCarCityList.length > 0){
				data.copyCityId = scope.canBeCopyCarCityList[0].cityId;
			}

			$scope.add = function () {
				$http.post(angular.path + '/system/cityTaxRate/add?addCityId='+$scope.data.addCityId+"&copyCityId="+$scope.data.copyCityId)
					.success(function (resp) {
						if(resp.success){
							scope.initList();
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