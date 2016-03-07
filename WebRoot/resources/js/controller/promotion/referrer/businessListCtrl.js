define(function (require) {
	
	require('js/service/commonList');
	require('js/service/common/citys');
	require('js/service/promotion/referrer');
	require('js/service/common/address');

	angular.module('App')

	.controller('mainCtrl', ['$scope', 'commonList', 'citys', '$modal',
		function ($scope, commonList, citys, $modal) {

			$scope.cityList = citys.getAllCitys();

			var list = $scope.list = commonList;
			list.filter.cityId = '';
			list.filter.name = '';
			list.url = "/promotion/referrer/business/list/getdata";
			
			list.fetch();

			// $scope.allSelected = false;
			// $scope.toggleAll = function () {
			// 	$scope.allSelected = !$scope.allSelected;
			// 	angular.forEach(list.data.data, function (s) {
			// 		s.selected = $scope.allSelected;
			// 	});
			// };

			// $scope.toggleChecked = function (s) {
			// 	s.selected = !s.selected;
			// };


			// 批量添加新的
			// $scope.batchNew = function () {

			// 	var arr = [];
			// 	angular.forEach(list.data.data, function (s) {
			// 		if (s.selected) {
			// 			arr.push(s);
			// 		}
			// 	});
			// 	if (arr.length < 1) {
			// 		alert('请至少选择一个');
			// 		return;
			// 	}

			// 	$modal.open({
			// 		templateUrl: angular.path + '/resources/templates/promotion/batchNew-modal.html',
			// 		controller: 'batchNewCtrl',
			// 		backdrop: 'static',
			// 		size: 'lg',
			// 		resolve: {
			// 			shopList: function () {
			// 				return arr;
			// 			}
			// 		}
			// 	});

			// };
			
			// 上传excel文件导入
			$scope.batchNew = function () {
				$modal.open({
					templateUrl: angular.path + '/resources/templates/promotion/import-excel-modal.html',
					controller: 'importExcelCtrl',
					backdrop: 'static',
					size: 'lg',
					resolve: {
						// shop: function () {
						// 	return shop;
						// }
					}
				});
			};

			// 添加新的
			$scope.addNew = function (shop) {

				$modal.open({
					templateUrl: angular.path + '/resources/templates/promotion/addNew-modal.html',
					controller: 'cerateNewCtrl',
					backdrop: 'static',
					size: 'lg',
					resolve: {
						shop: function () {
							return shop;
						}
					}
				});

			};

		}
	])

	.controller('cerateNewCtrl', ['$scope', '$modalInstance', 'shop', 'referrer',
		function ($scope, $modalInstance, shop, referrer) {

			// 初始化数据
			$scope.shop = shop;

			$scope.addData = {
				businessIds: shop.bId,
				commissionRate: 0
			};
			// 初始化结束

			// 提交修改
			$scope.submitted = false;
			$scope.submitting = false;
			$scope.add = function () {
				$scope.submitted = true;
				if ($scope.add_form.$invalid) {
					return;
				}
				$scope.submitting = true;
				referrer.add($scope.addData)
					.then(function (data) {
						shop.added = true;
						$modalInstance.dismiss();
						$scope.submitting = false;
					}, function (msg) {
						alert(msg);
						$scope.submitting = false;
					});
			};
			// 提交修改结束
			
			// 帮助方法，关闭modal
			$scope.cancel = function () {
				$modalInstance.dismiss();
			};

		}
	])

	// .controller('batchNewCtrl', ['$scope', '$modalInstance', 'shopList', 'referrer',
	// 	function ($scope, $modalInstance, shopList, referrer) {

	// 		$scope.shopList = shopList;

	// 		$scope.addData = {
	// 			businessIds: (function (shopList) {
	// 				var arrStr = '';
	// 				angular.forEach(shopList, function (s) {
	// 					arrStr += (s.bId + ',');
	// 				});
	// 				arrStr = arrStr.substring(0, arrStr.length - 1);
	// 				return arrStr;
	// 			})(shopList),
	// 			commissionRate: 0
	// 		};

	// 		$scope.submitted = false;
	// 		$scope.submitting = false;
	// 		$scope.addAll = function () {
	// 			$scope.submitted = true;
	// 			if ($scope.add_form.$invalid) {
	// 				return;
	// 			}
	// 			$scope.submitting = true;
	// 			referrer.add($scope.addData)
	// 				.then(function (data) {
	// 					angular.forEach(shopList, function (s) {
	// 						s.added = true;
	// 					});
	// 					$modalInstance.dismiss();
	// 					$scope.submitting = false;
	// 				}, function (msg) {
	// 					alert(msg);
	// 					$scope.submitting = false;
	// 				});
	// 		};
			
	// 		// 帮助方法，关闭modal
	// 		$scope.cancel = function () {
	// 			$modalInstance.dismiss();
	// 		};

	// 	}
	// ]);

	.controller('importExcelCtrl', ['$scope', '$modalInstance', 'Upload',
		function ($scope, $modalInstance, $upload) {



			// $scope.uploader = new FileUploader();

			console.log($upload);

			$scope.uploading = false;
			$scope.progress = {
				loaded: 100,
				totalSize: 500
			};
			$scope.commissionRate = 0;

			$scope.setFile = function (files) {
				$scope.file = files[0];
				console.log($scope.file);
			};

			$scope.upload = function(e){
				e.preventDefault();

				if ($scope.add_form.$invalid) {
					return;
				}

				if (!$scope.file) {
					alert('请选择文件');
					return;
				}

				// var ext = $scope.file.name.substr($scope.file.name.lastIndexOf('.') + 1, $scope.file.name.length);

				$scope.uploading = true;
				$upload.upload({
			    url: angular.path + '/promotion/referrer/batch/upload/do',
			    fields: {commissionRate: $scope.commissionRate},
			    file: $scope.file,
			    fileFormDataName:'file'
			    // headers: {'Content-Type': 'application/vnd.ms-excel'}
				})
				.progress(function (evt) {
					console.log(evt);
				   $scope.progress = evt;
				})
				.success(function (resp, status, headers, config) {

			    if(resp.success){
			    	// if(self.saveObj.delImgUrls != ""){
			    	// 	self.saveObj.delImgUrls = self.saveObj.delImgUrls +","+ self.saveObj.imgUrl;
			    	// }else{
			    	// 	self.saveObj.delImgUrls = self.saveObj.imgUrl;
			    	// }
			     //    if(self.saveObj.oldImgUrl != ""){
			     //        self.saveObj.oper = 2;
			     //    }else{
			     //        self.saveObj.oper = 1;
			     //    }
			    	// self.saveObj.imgUrl = resp.data.path;

			    	// if(typeof callback == "function"){
			    	// 	callback(resp.data,config);
			    	// }
			    	if (resp.data.length < 1) {
			    		alert('全部添加成功');
			    	}
			    	$scope.failList = resp.data;
			    	// $modalInstance.dismiss();
			    } else {
			    	alert(resp.message);
			    }

			    $scope.uploading = false;

				})
				.error(function (resp) {
					alert(resp.message);
					$scope.uploading = false;
				});
			}

			$scope.cancel = function () {
				$modalInstance.dismiss();
			};

		}
	])

});