define(function (require) {
	
	require('js/service/commonList');
	require('js/service/common/orderStatus');
	require('js/service/common/citys');
	require('js/service/common/activeCompany');
	require('js/directives/filter-select');
	require('js/service/common/verify');

	angular.module('App')

	.controller('mainCtrl', ['$scope', '$http','commonList', '$timeout', 'orderStatus', 'citys', 'activeCompanys','verifyWay', '$modal','Upload',
		function ($scope,$http, commonList, $timeout, orderStatus, citys, activeCompanys,verifyWay, $modal,$upload) {

			$scope.statusList = [
				{
					'code':7,
					'desc':'已支付'
				},
				{
					'code':8,
					'desc':'待出保单'
				},
				{
					'code':10,
					'desc':'待送保单'
				},
				{
					'code':11,
					'desc':'保单已寄出'
				},
				{
					'code':16,
					'desc':'退款成功'
				},
				{
					'code':27,
					'desc':'退保成功'
				}
				];
			$scope.dzstatusList = [
				{
					'code':1,
					'desc':'未对账'
				},
				{
					'code':2,
					'desc':'已对账'
				},
				{
					'code':3,
					'desc':'已对账-正常'
				},
				{
					'code':4,
					'desc':'已对账-异常'
				}
				];
			$scope.cityList = citys.getAllCitys();
			$scope.companys = activeCompanys.getAllActiveCompanys();
			$scope.verifyList = verifyWay.getAllVerify();

			function getFormatedDate(date) {
				return date instanceof Date ? date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate() : null;
			}

			$scope.startDate = "";
			$scope.endDate = "";
			$scope.$watch("list.filter.startDate",function(newValue,oldValue){
				$scope.startDate = getFormatedDate(newValue);
			});

			$scope.$watch("list.filter.endDate",function(newValue,oldValue){
				$scope.endDate = getFormatedDate(newValue);
			});
			
				
			$scope.startCompareDate = "";
			$scope.endCompareDate = "";
			$scope.$watch("list.filter.startCompareDate",function(newValue,oldValue){
				$scope.startCompareDate = getFormatedDate(newValue);
			});

			$scope.$watch("list.filter.endCompareDate",function(newValue,oldValue){
				$scope.endCompareDate = getFormatedDate(newValue);
			});

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
			list.filter.orderStatus = '';

			list.url = "/commission/compare/list/getData";
			
			list.fetch();

			$scope.confirmDownload = function(e){
				console.log($scope.platNoOrOrderNo);
				if (!confirm('确定下载？')) {
					e.preventDefault();
				}
			}
			
			$scope.upLoadFunc = function(){
                    $modal.open({
                        templateUrl: angular.path + '/resources/templates/commission/compare/upLoad.html',
                        controller: 'upLoadCtrl',
						scope:$scope,
                        backdrop: 'static',
                        size: 'lg'
                    });
            }
			
			var modal = null,index = null;
			$scope.editRemark = function (rw,$index) {
				$scope.param = angular.copy(rw);
				index = $index;
	            modal = $modal.open({
	                templateUrl: angular.path + '/resources/templates/commission/compare/editRemark.html',
	                backdrop: 'static',
	                scope:$scope,
	                size: 'lg'
	            });
	        };
			
			$scope.cancel = function(){
	            modal.close();
	        };
			
			
			//更新
	        $scope.updateParam = function(){
	        	$http.post(angular.path+"/commission/compare/update",$scope.param).success(function(resp){
	        		if(resp.success){
	        			if(index>=0&&list.data.data.data.data.length>0){
	        				list.data.data.data.data[index] = angular.copy($scope.param);
	        			}
	        			modal.close();
	        		}else{
	        			alert(resp.message);
	        		}
	        	});
	        };
			
			$scope.compareLoadFunc = function(){
				$scope.list.filter.version = '';
				$scope.list.fetch();
			};
			
		}
	])
	.controller('upLoadCtrl',['$scope','$modalInstance','$modal','Upload',function($scope,$modalInstance,$modal,$upload){
            
			$scope.setFile = function (files) {
                $scope.file = files[0];
                console.log($scope.file);
            };
            $scope.upload = function(files){
                if ($scope.add_form.$invalid) {
                    return ;
                }
                if (!$scope.file) {
                    alert('请选择文件');
                    return;
                }

                else{
                    var t = $scope.file.name.split('.');
                    var fileType = t[t.length-1];
                    if(fileType.trim() == "xlsx" && $scope.file.size<=(3*1024*1024)){

                        var ext = $scope.file.name.substr($scope.file.name.lastIndexOf('.') + 1, $scope.file.name.length);
                        $scope.uploading = true;
                        $upload.upload({
                            url: angular.path + '/commission/compare/import',
                            file: $scope.file,
                            fileFormDataName:'file'
                        })
                            .success(function (resp) {
                                if(resp.success){
                                    alert(resp.data.resultMess);
                                    $modalInstance.dismiss();
									$scope.list.fetch();
                                } else {
                                    alert(resp.message);
                                }
                                $scope.uploading = false;
                            })
                            .error(function (resp) {
                                alert(resp.message);
                            });
                    }
                    else{
                        alert("限定上传Excel .xlsx格式文件");
                    }

                }

            }

            $scope.cancel = function ($event) {
                $event.preventDefault();
                $modalInstance.dismiss();
            };

    }])
	.filter("percent",function(){
        return function(input){
            return input+"%";
        }
    });

});	