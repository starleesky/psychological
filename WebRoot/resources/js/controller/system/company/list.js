define(function (require) {
	
	require('js/service/commonList');
	require('js/service/common/citys');
	require('js/service/common/activeCompany');
	require('js/directives/filter-select');
	require('js/service/common/monitor');

	angular.module('App')

	.controller('mainCtrl', ['$scope','$http', 'commonList', '$timeout','$modal','Upload',
		function ($scope,$http, commonList, $timeout,$modal,$upload) {

			var list = $scope.list = commonList;
			list.filter.key = '';
			list.url = "/system/company/list/getData";
			
			list.fetch();

			$scope.currentDate = "?date="+new Date();

			$scope.refreshDate = function (){
				$scope.currentDate = "?date="+new Date();
			}

			var param = $scope.param = {};

			$scope.regexp = /^[A-Za-z]{4}$/;
			$scope.briefNameLengthLimit = 8;

			var modal = null,index = null;
			$scope.edit = function (rw,$index) {
				$scope.param = angular.copy(rw);
				index = $index;
	            modal = $modal.open({
	                templateUrl: angular.path + '/resources/templates/system/company/edit-company.html?data='+new Date(),
	                backdrop: 'static',
	                scope:$scope,
	                size: 'lg'
	            });
	        };

	        $scope.addNew = function () {
			  	var modal = $modal.open({
			  		templateUrl: angular.path + '/resources/templates/system/company/new-company.html?data='+new Date(),
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

	        $scope.setFile = function (files) {
                $scope.file = files[0];
            };

	        $scope.codeHaveVaild = false;
			$scope.codeExsit = false;

	        //更新
	        $scope.updateParam = function(){
	        	var haveFile = false;
	        	var t , fileType;
				$scope.nameVaild = false;
				$scope.briefNameValid = false;
				$scope.codeVaild = false;

				if($scope.param.name == '' ){
					$scope.nameVaild = true;
				}
				if($scope.param.briefName == '' ){
					$scope.briefNameValid = true;
				}else{
					if($scope.param.briefName.length > $scope.briefNameLengthLimit){
						$scope.briefNameValid = true;
					}
				}
				if($scope.param.code == ''){
					$scope.codeVaild = true;
				}else{
					if(!$scope.regexp.test($scope.param.code)){
						$scope.codeVaild = true;
					}
				}
				
				if($scope.nameVaild || $scope.briefNameValid || $scope.codeVaild || $scope.codeExsit){
					return ;
				}		
				if(!$scope.codeHaveVaild){
					$scope.checkCode();	
					alert("车险公司代码验证中，请稍等");
					return ;
				}

				if(undefined != $scope.file){
					haveFile = true;
					t = $scope.file.name.split('.');
					fileType = t[t.length-1];
				}
				//有图片的情况 图片也符合要求
				if(haveFile && fileType.trim() == "png" && $scope.file.size<=(20*1024)){
				    $scope.uploadFile();
				}
				//没有图片或者图片不符合要求
				else{
					//有图片的情况，图片不符合要求
					if(haveFile){
						alert("只能上传png格式的公司Logo,并确认图片小于20KB。");	
					}else{
						//没有图片的情况
						$scope.uploadFile();
						location.reload(true);
					}
				}

	        	$scope.codeHaveVaild = false;
				$scope.codeExsit = false;
	        }

	        $scope.uploadFile = function(){
	        	$upload.upload({
			        url: angular.path + '/system/company/update?id='+$scope.param.id+"&name="+$scope.param.name+"&briefName="+$scope.param.briefName+"&code="+$scope.param.code+"&logo="+$scope.param.logo+"&hotLine="+$scope.param.hotLine,
			        file: $scope.file == undefined ? null :  $scope.file,
			        fileFormDataName:'file'
			    })
				.success(function (resp) {
					if(resp.success){
        				if(index>=0&&list.data.data.length>0){
        					$scope.refreshDate();
        					$scope.param.logo = resp.data ;
        					list.data.data[index] = angular.copy($scope.param);
        				}
        			}else{
        				alert(resp.message);
        			}
					modal.close();
				})
				.error(function (resp) {
					alert(resp.message);
					modal.close();
				});
	        }

	        $scope.checkCode = function(){
				if($scope.param.code == ''){
					return ;
				}
				$http.get(angular.path + '/system/company/codeExist?code='+$scope.param.code+"&id="+$scope.param.id)
					.success(function (resp) {
						$scope.codeHaveVaild = true;
						//已经存在该code
						if(resp){
							$scope.codeExsit = true;
						}else{
							$scope.codeExsit = false;
						}
					})
					.error(function () {
						alert("查询是否存在该保险公司代码失败，请稍后重试！");
					}
				);
			}
		}
	])
	.controller('addNewCtrl', ['$scope', '$http','$modalInstance','scope','Upload',
		function ($scope, $http ,$modalInstance, scope, $upload) {
			$scope.setFile = function (files) {
                $scope.file = files[0];
            };

            $scope.currentDate = "?date="+new Date();

			$scope.data = {
				id : 0,
				name : '',
				briefName : '',
				code : '',
				hotLine : '',
				logo : ''
			};

			$scope.codeHaveVaild = false;
			$scope.codeExsit = false;

			$scope.add = function () {

				var haveFile = false;
	        	var t , fileType;

				$scope.nameVaild = false;
				$scope.briefNameValid = false;
				$scope.codeVaild = false;

				if($scope.data.name == '' ){
					$scope.nameVaild = true;
				}
				if($scope.data.briefName == '' ){
					$scope.briefNameValid = true;
				}else{
					if($scope.data.briefName.length > scope.briefNameLengthLimit){
						$scope.briefNameValid = true;
					}
				}
				if($scope.data.code == ''){
					$scope.codeVaild = true;
				}else{
					if(!scope.regexp.test($scope.data.code)){
						$scope.codeVaild = true;
					}
				}
				
				if($scope.nameVaild || $scope.briefNameValid || $scope.codeVaild || $scope.codeExsit){
					return ;
				}

				if(!$scope.codeHaveVaild){
					$scope.checkCode();
					alert("车险公司代码验证中，请稍等");
					return ;
				}

				if(undefined != $scope.file){
					haveFile = true;
					t = $scope.file.name.split('.');
					fileType = t[t.length-1];
				}
				//有图片的情况 图片也符合要求
				if(haveFile && fileType.trim() == "png" && $scope.file.size<=(20*1024)){
				    $scope.uploadFile();
				}
				//没有图片或者图片不符合要求
				else{
					//有图片的情况，图片不符合要求
					if(haveFile){
						alert("只能上传png格式的公司Logo,并确认图片小于20KB。");	
					}else{
						//没有图片的情况
						$scope.uploadFile();
					}
				}

	        	$scope.codeHaveVaild = false;
				$scope.codeExsit = false;

			};

			$scope.cancel = function () {
				$modalInstance.dismiss();
			};

			$scope.checkCode = function(){
				if($scope.data.code == ''){
					return ;
				}
				$http.get(angular.path + '/system/company/codeExist?code='+$scope.data.code+"&id="+$scope.data.id)
					.success(function (resp) {
						$scope.codeHaveVaild = true;
						//已经存在该code
						if(resp){
							$scope.codeExsit = true;
						}else{
							$scope.codeExsit = false;
						}
					})
					.error(function () {
						alert("查询是否存在该保险公司代码失败，请稍后重试！");
					}
				);
			}

			$scope.uploadFile = function(){
				$upload.upload({
			        url: angular.path + '/system/company/add?name='+$scope.data.name+"&briefName="+$scope.data.briefName+"&code="+$scope.data.code+"&hotLine="+$scope.data.hotLine,
			        file: $scope.file == undefined ? null : $scope.file,
			        fileFormDataName:'file'
			    })
				.success(function (resp) {
					if(resp.success){
						$scope.data.id = resp.data.id;
						$scope.data.logo = resp.data.logo ;
						scope.list.data.data.unshift($scope.data);
					}else{
						alert("创建失败，请重试");
					}
					$modalInstance.close();
				})
				.error(function (resp) {
					alert(resp.message);
					$modalInstance.close();
				});
			}

		}
	]);

});	