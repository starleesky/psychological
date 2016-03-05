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
			list.url = "/system/cityType/list/getData";
			
			list.fetch();

			var param = $scope.param = {};

			$scope.vaildScore = false;
			var modal = null,index = null;
			$scope.edit = function (rw,$index) {
				$scope.data = angular.copy(rw);
				index = $index;
				modal = $modal.open({
					templateUrl: angular.path + '/resources/templates/system/cityType/edit-cityType.html?data='+new Date(),
					backdrop: 'static',
					scope:$scope,
					size: 'lg'
				});
			};

			$scope.copy = function () {
				if($scope.canBeAddCityTypeList.length == 0){
					alert("没有城市需要添加险种。");
					return ;
				}
				var modal = $modal.open({
					templateUrl: angular.path + '/resources/templates/system/cityType/copy-cityType.html?data='+new Date(),
					controller: 'copyCtrl',
					backdrop: 'static',
					resolve: {
						scope: function () {
							return $scope;
						}
					}
				});
			};

			$scope.addNew = function(){
				var modal = $modal.open({
					templateUrl: angular.path + '/resources/templates/system/cityType/new-cityType.html?data='+new Date(),
					controller: 'addNewCtrl',
					backdrop: 'static',
					resolve: {
						scope: function () {
							return $scope;
						}
					}
				});
			}

			$scope.deleteOne = function(data,$index){
				if(confirm("确认删除"+data.cityName+"的"+data.insTypeName+"？")){
					$http.post(angular.path+"/system/cityType/delete?cityTypeId="+data.id+"&cityId="+data.cityId)
						.success(function(resp){
							if(resp.success){
								alert("删除成功！");
								$scope.list.data.data.splice($index,1);
							}else{
								alert("删除失败，请重试！");
							}
						})
						.error(function(){
							alert("删除失败，请重试！");
						});
				}
			}



			//取消处理
			$scope.cancel = function(){
				modal.close();
			}

			//更新
			$scope.updateParam = function(){
				$scope.vaildScore = false;
				if(undefined == $scope.data.score || $scope.data.score == 0 || isNaN($scope.data.score)){			
					$scope.vaildScore = true;
				}else{
					if($scope.data.score < 1 || $scope.data.score > 100){
						$scope.vaildScore = true;
					}
				}
				if($scope.vaildCityId || $scope.vaildInsTypeId || $scope.vaildScore){
					return ;
				}
				if($scope.data.isDefaultSelect){
					$scope.data.isDefaultSelect = 1;
				}else{
					$scope.data.isDefaultSelect = 0;
				}
				$http.post(angular.path+"/system/cityType/update",$scope.data)
					.success(function(resp){
						if(resp.success){
							if(index>=0&&list.data.data.length>0){
								list.data.data[index] = angular.copy($scope.data);
							}
							modal.close();
						}else{
							alert(resp.message);
						}
					});
			}

			// //获取可创建城市车险公司险种列表
			$scope.getCanBeAddCityTypeList = function(){
				var citys = [];
				$http.get(angular.path + '/system/cityType/getCanBeAddCityTypeList?data='+new Date())
					.success(function (data) {
						angular.forEach(data, function (value) {
							citys.push(value);
						});
						$scope.canBeAddCityTypeList = citys;

					})
					.error(function () {
						alert("获取可创建城市险种列表失败。");
					});
			}

			// //获取可拷贝城市车险公司险种列表
			$scope.getCanBeCopyCityTypeList = function(){
				var citys = [];
				$http.get(angular.path + '/system/cityType/getCanBeCopyCityTypeList?data='+new Date())
					.success(function (data) {
						angular.forEach(data, function (value) {
							citys.push(value);
						});
						$scope.canBeCopyCityTypeList = citys;
						$scope.selectCitys = citys;

					})
					.error(function () {
						alert("获取可拷贝城市险种列表失败");
					});
			}

			// //获取可添加险种
			$scope.getCanBeAddTypeList = function(){
				var types = [];
				$http.get(angular.path + '/system/cityType/getCanBeCopyCityTypeList?data='+new Date())
					.success(function (data) {
						angular.forEach(data, function (value) {
							citys.push(value);
						});
						$scope.canBeCopyCityTypeList = citys;

					})
					.error(function () {
						alert("获取可拷贝城市险种列表失败");
					});
			}

			$scope.getCanBeAddCityTypeList();
			$scope.getCanBeCopyCityTypeList();

			$scope.initList = function(){
				list.fetch();
				$scope.getCanBeAddCityTypeList();
				$scope.getCanBeCopyCityTypeList();
			}
		}
	])
	.controller('copyCtrl', ['$scope', '$http','$modalInstance','scope',
		function ($scope, $http ,$modalInstance, scope) {
			$scope.canBeAddCityTypeList = scope.canBeAddCityTypeList;
			$scope.canBeCopyCityTypeList = scope.canBeCopyCityTypeList;

			if($scope.canBeCopyCityTypeList.length == 0){
				alert("没有可拷贝城市险种配置。");
					return ;
			}
			var data = $scope.data = {
				addCityTypeId : 0,
				copyCityTypeId : 0
			};

			if(null != scope.canBeAddCityTypeList && scope.canBeAddCityTypeList.length > 0){
				data.addCityTypeId = scope.canBeAddCityTypeList[0].cityId;
			}

			if(null != scope.canBeCopyCityTypeList && scope.canBeCopyCityTypeList.length > 0){
				data.copyCityTypeId = scope.canBeCopyCityTypeList[0].cityId;
			}

			$scope.add = function () {
				$http.post(angular.path + '/system/cityType/copy?addCityTypeId='+$scope.data.addCityTypeId+"&copyCityTypeId="+$scope.data.copyCityTypeId)
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
	])
	.controller('addNewCtrl', ['$scope', '$http','$modalInstance','scope',
		function ($scope, $http ,$modalInstance, scope) {
			$scope.canBeAddCityTypeList = scope.canBeCopyCityTypeList;

			var data = $scope.data = {
				insTypeId:0
			};
			$scope.canBeAddTypeList = {};

			$scope.vaildCityId = false;
			$scope.vaildInsTypeId = false;
			$scope.vaildScore = false;

			$scope.add = function () {
				$scope.vaildCityId = false;
				$scope.vaildInsTypeId = false;
				$scope.vaildScore = false;
				if(undefined == $scope.data.cityId ||  $scope.data.cityId == 0){
					$scope.vaildCityId = true;
				}
				if(undefined == $scope.data.insTypeId ||  $scope.data.insTypeId == 0){
					$scope.vaildInsTypeId = true;
				}
				if(undefined == $scope.data.score || $scope.data.score == 0 || isNaN($scope.data.score)){			
					$scope.vaildScore = true;
				}else{
					if($scope.data.score < 1 || $scope.data.score > 100){
						$scope.vaildScore = true;
					}
				}
				if($scope.vaildCityId || $scope.vaildInsTypeId || $scope.vaildScore){
					return ;
				}
				$http.post(angular.path + '/system/cityType/add', $scope.data)
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

			$scope.cityChange = function(){
				if(undefined != $scope.data.cityId){
					$http.get(angular.path + '/system/cityType/getCanBeAddTypeList?cityId='+$scope.data.cityId)
						.success(function (resp) {
							if(resp.success){
								if(resp.data.length > 0){ 
									$scope.canBeAddTypeList = resp.data;
									data.insTypeId = $scope.canBeAddTypeList[0].insTypeId;
								}else{
									$scope.canBeAddTypeList = {};
									data.insTypeId = 0;
									alert("当前城市没有可添加的险种！");
								}
							}else{
								alert("获取可添加险种失败，请重试！");
							}
						})
						.error(function () {
							alert("获取可添加险种，请重试！");
						}
					);
				}else{

				}
			}
		}
	]);

});	