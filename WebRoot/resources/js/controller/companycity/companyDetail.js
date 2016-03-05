define(function (require) {

	require('js/service/companycity/companyDetail');
	require('js/service/insurance');
	require('js/service/companycity/blacklist');
	// require('js/filters/filters');

	var myApp = angular.module('App');

	myApp.controller('mainCtrl', ['$scope', '$modal', 'CompanyDetail', 'insurancePlan', 'insuranceTyps', 'blackList','$http',
		function ($scope, $modal, CompanyDetail, insurancePlan, insuranceTyps, blackList,$http) {

			var detail = $scope.detail = new CompanyDetail(angular.companyId);
			detail.getData();

			var listPic = $scope.listPic = new CompanyDetail(angular.companyId);

			listPic.getPagelistData(angular.companyId)
				.then(function(data){
					$scope.listPic = data;
				},function(){
					alert('获取数据失败');
				})

			$scope.editCompanyInfo = function () {

				var modal = $modal.open({
					templateUrl: 'companyInfoModal.html',
					controller: 'companyInfoCtrl',
					size: 'lg',
					backdrop: 'static',
					keyboard: 'false',
					resolve: {
						detail: function () {
							return detail;
						}
					}
				});

			};

			// gift about
			$scope.newGift = {
				giftTitle: '',
				giftDesc: ''
			};
			$scope.addGift = function () {
				if ($.trim($scope.newGift.giftTitle) === '' || $.trim($scope.newGift.giftDesc) === '') {
					alert('标题和描述必须填写！');
					return;
				}
				detail.addGift($scope.newGift)
					.then(function () {
						$scope.newGift.giftTitle = '';
						$scope.newGift.giftDesc = '';
					}, function (info) {
						alert(info);
					});
			};
			$scope.editGift = function (index) {
				var gift = detail.data.gifts[index];
				if (gift.editing) {
					detail.updateGift(gift)
						.then(function () {
							gift.editing = false;
							alert('修改成功');
						}, function (info) {
							alert(info);
						});
				} else {
					gift.editing = true;
				}
			};


			// 车险类型控制
			$scope.configPlan = function (type) {

				var modal = $modal.open({
					templateUrl: 'choosePlan.html',
					controller: 'planCtrl',
					size: 'lg',
					backdrop: 'static',
					keyboard: 'false',
					resolve: {
						plan: function () {
							return type === 3 ? normalPlan : economicPlan;
						}
					}
				});

			};

			var normalPlan = $scope.normalPlan = new insurancePlan(3, angular.companyId);
			var economicPlan = $scope.economicPlan = new insurancePlan(2, angular.companyId);

			// 险种选择
			insuranceTyps.getInsurances(angular.companyId)
				.then(function (insurances) {
					$scope.insurances = insurances;
				});
			$scope.chooseKinds = function () {

				var modal = $modal.open({
					templateUrl: 'chooseKinds.html',
					controller: 'kindsCtrl',
					size: 'lg',
					backdrop: 'static',
					keyboard: 'false',
					resolve: {
						insurances: function () {
							return $scope.insurances;
						},
						cid: function () {
							return angular.companyId;
						}
					}
				});

			};

			//$scope.listPic =[{name:'车辆正面'},{name:'车辆左侧面'},{name:'车辆正面'},{name:'车辆左侧面'},{name:'车辆正面'},{name:'车辆左侧面'},{name:'车辆正面'},{name:'车辆左侧面'}];\

			$scope.drive =[{id:0,name:'无'},{id:1,name:'上传正面'},{id:2,name:'上传正反面'}];
			$scope.cdCard =[{id:0,name:'无'},{id:1,name:'上传正面'},{id:2,name:'上传正反面'}];
			$scope.verify = [{id:1,name:'线下核保'},{id:2,name:'在线核保'}];
			$scope.settle = [{id:1,name:'接口代付'},{id:2,name:'网页付款'}];

			$scope.dName = 1;
			$scope.cName = 0;
			$scope.changeDetail = function (listPic) {
				//listPic.licenseImgFlag = detail.data.licenseImgFlag;
				//listPic.idcardImgFlag = detail.data.idcardImgFlag;
				listPic.companyCityId = angular.companyId;
				//listPic.companyCityPicSettingDto = detail.data.companyCityPicSettingDto;
				//listPic.data.forEach(function(p){
				//	listPic.companyCityPicSettingDto.carMode.forEach(function(t){
				//		if(p.typeId == t.typeId)
				//			p.checked = true;
				//	})
				//})


				$modal.open({
					templateUrl: angular.path + '/resources/templates/companyCity/changeInfo.html',
					controller: 'changeCtrl',
					backdrop: 'static',
					size: 'lg',
					resolve: {
						listPic: function () {
							return listPic;
						}
					}
				});
			};
			$scope.changeSettle = function (detail) {
				$modal.open({
					templateUrl: angular.path + '/resources/templates/companyCity/changeSettle.html',
					controller: 'settleCtrl',
					backdrop: 'static',
					size: 'lg',
					resolve: {
						detail: function () {
							return detail;
						}
					}
				});
			}

			//添加传统费率配置
			$scope.addTraditionRate = function(){
				$modal.open({
					templateUrl: angular.path + '/resources/templates/companyCity/addTraditionRate.html',
					controller: 'newTraditionRateCtrl',
					backdrop: 'static',
					size: 'lg',
					resolve: {
						companyCity: function () {
							return detail;
						}
					}
				});
			}

			//删除传统费率配置
			$scope.deleteTraditionRate = function(id){
				if (confirm('确定删除?')) {
					$http.post(angular.path + '/companycity/deleteTraditionRate/do?id='+id)
					.success(function (resp) {
						if (resp.success) {
							window.location.reload();
						} else {
							alert(resp.message);
						}
						$scope.submitting = false;
					})
					.error(function (resp) {
						alert(resp.message);
						$scope.submitting = false;
					});
				}
			}

			//修改传统费率配置
			$scope.editTraditionRate = function(){
				$modal.open({
					templateUrl: angular.path + '/resources/templates/companyCity/editTraditionRate.html',
					controller: 'editTraditionRateCtrl',
					backdrop: 'static',
					size: 'lg',
					resolve: {
						tradtionRate: function () {
							return $scope.detail.data.companyCityExt;
						}
					}
				});
			}
		}

	]);
	
	myApp.controller('newTraditionRateCtrl', ['$scope', '$modal', 'CompanyDetail','$modalInstance','companyCity','$http',
		function ($scope, $modal, CompanyDetail,$modalInstance,companyCity,$http) {
			$scope.companyCity = companyCity.data;
			var data = $scope.data = {
				cityId: $scope.companyCity.cityId,
				companyCityId: $scope.companyCity.companyCityId,
				ecommerceRate: 0,
				mandatoryRebate: 0,
				commercialRebate: 0,
				commissionRate: 0,
				saleDiscount: 0,
				trialSaleDiscount: 0
			};
			$scope.submitted = false;
			$scope.submitting = false;

			$scope.add = function () {
				$scope.submitted = true;
				if ($scope.new_form.$invalid) {
					return;
				}

				var d = angular.copy(data);

				if (parseFloat(d.ecommerceRate) < parseFloat(d.saleDiscount)) {
					alert('销售折扣不能大于电子销售折扣率');
					return;
				}

				$scope.submitting = true;
				$http.post(angular.path + '/companycity/addTraditionRate/do', $scope.data)
					.success(function (resp) {
						if (resp.success) {
							$modalInstance.dismiss();
							window.location.reload();
						} else {
							alert(resp.message);
						}
						$scope.submitting = false;
					})
					.error(function (resp) {
						alert(resp.message);
						$scope.submitting = false;
					});

			};

			$scope.cancel = function () {
				$modalInstance.dismiss('cancel');
			};
		}
	]);

	myApp.controller('editTraditionRateCtrl', ['$scope', '$modal', 'CompanyDetail','$modalInstance','tradtionRate','$http',
		function ($scope, $modal, CompanyDetail,$modalInstance,tradtionRate,$http) {
			var data = $scope.data = tradtionRate;

			$scope.submitted = false;
			$scope.submitting = false;

			$scope.add = function () {
				$scope.submitted = true;
				if ($scope.new_form.$invalid) {
					return;
				}

				var d = angular.copy(data);

				if (parseFloat(d.ecommerceRate) < parseFloat(d.saleDiscount)) {
					alert('销售折扣不能大于电子销售折扣率');
					return;
				}

				$scope.submitting = true;
				$http.post(angular.path + '/companycity/updateTraditionRate/do', $scope.data)
					.success(function (resp) {
						if (resp.success) {
							$modalInstance.dismiss();
							window.location.reload();
						} else {
							alert(resp.message);
						}
						$scope.submitting = false;
					})
					.error(function (resp) {
						alert(resp.message);
						$scope.submitting = false;
					});

			};

			$scope.cancel = function () {
				$modalInstance.dismiss('cancel');
			};
		}
	]);

	myApp.controller('changeCtrl',['$scope','listPic','$modalInstance','CompanyDetail',function($scope,listPic,$modalInstance,CompanyDetail){
		$scope.listPic = listPic;
		$scope.drive =[{id:0,name:'无'},{id:1,name:'上传正面'},{id:2,name:'上传正反面'}];
		$scope.cdCard =[{id:0,name:'无'},{id:1,name:'上传正面'},{id:2,name:'上传正反面'}];
		$scope.licenseType = listPic.data.licenseType;
		$scope.identityCardType =listPic.data.identityCardType;

		$scope.changeList = angular.copy($scope.listPic);
		var companyCityId = listPic.companyCityId ;
		//var LicenseType = $scope.licenseImgFlag;
		//var IdentityCardType = $scope.idcardImgFlag;

		$scope.saveChange = function(){
			var carModel =[];
			angular.forEach($scope.changeList.data.carMode,function(t){
				if(t.checked){
					carModel.push(t);
				}
			})

			var param = {
				carMode:carModel,
				companyCityId:companyCityId,
				licenseType:$scope.licenseType,
				identityCardType:$scope.identityCardType
			}

			var comDt = new CompanyDetail(angular.companyId);
			//JSON.stringify(param)
			comDt.saveChange(JSON.stringify(param))
				.then(function(data){
					if(data.success){
						alert(data.message);
						window.location.reload();
						$modalInstance.close();

					}
					else alert(data.message);
					$modalInstance.close();

				})
		}
		$scope.cancel = function () {
			$modalInstance.dismiss('cancel');
		};


	}]);
	myApp.controller('BlackListCtrl', ['$scope', 'blackList',
		function ($scope, blackList) {

			// 黑名单
			var blist = $scope.blist = new blackList(angular.companyId);
      $scope.keyWord = '';
      var searchTimer = null;

			$scope.isEmptyObj = function (obj) {
				return Object.keys(obj).length < 1;
			};

      // 获取品牌列表
      blist.getBrandList()
        .then(function () {
          searchBrand();
        }, function () {});

      blist.getBlackedBrandList();

			//var filteredList = $scope.filteredList = blist.brands;
      var searchBrand = function () {
        var list = {};
        angular.forEach(blist.brands, function (value, key) {
          if (value.brandName.indexOf($scope.keyWord) > -1) {
            list[key] = value;
          }
        });
        $scope.filteredList = list;
      };

      $scope.timerSearch = function () {
        if (searchTimer) {
          clearTimeout(searchTimer);
        }
        searchTimer = setTimeout(function () {
          $scope.$apply(function () {
            searchBrand();
          });
        }, 500);
      };

			$scope.toggleBrand = function (bid) {
				blist.brands[bid].open = !blist.brands[bid].open;
				if (!blist.brands[bid].series) {
					blist.getSeriesListByBrandId(bid);
				}
			};

      $scope.toggleBlackBrand = function (bid) {
        blist.blackedBrands[bid].open = !blist.blackedBrands[bid].open;
        if (!blist.blackedBrands[bid].blackSeries) {
          blist.getBlackedSeriesListByBrandId(bid);
        }
      };

      $scope.addBlack = function (brand, series) {
        if (series) {
          blist.addBlack(brand.brandId, series.seriesId)
            .then(function (id) {
              series.id = id;
              blist.blackedBrands[brand.brandId] = blist.blackedBrands[brand.brandId] || brand;
              blist.blackedBrands[brand.brandId].blackSeries = blist.blackedBrands[brand.brandId].blackSeries || {};
              blist.blackedBrands[brand.brandId].blackSeries[series.seriesId] = series;
              delete blist.brands[brand.brandId].series[series.seriesId];
              delete $scope.filteredList[brand.brandId].series[series.seriesId];
              blist.getBlackedSeriesListByBrandId(brand.brandId);
            }, function (msg) {
              alert(msg);
            });
        } else {
          blist.addBlack(brand.brandId)
            .then(function (id) {
              brand.id = id;
              brand.blackSeries = {};
              blist.blackedBrands[brand.brandId] = brand;
              brand.brandOnly = true;
              delete blist.brands[brand.brandId];
              delete $scope.filteredList[brand.brandId];
            }, function (msg) {
              alert(msg);
            });
        }
      };

      function isEmptyObj(obj) {
        for (var key in obj) {
          if (obj.hasOwnProperty(key)) {
            return false;
          }
        }
        return true;
      }

      $scope.removeBlack = function (brand, series) {
        if (series) {
          blist.removeBlack(series.id)
            .then(function () {
              blist.brands[brand.brandId] = blist.brands[brand.brandId] || brand;
              blist.brands[brand.brandId].series = blist.brands[brand.brandId].series || {};
              blist.brands[brand.brandId].series[series.seriesId] = series;
              delete blist.blackedBrands[brand.brandId].blackSeries[series.seriesId];
              blist.getSeriesListByBrandId(brand.brandId);
              searchBrand();
              if (isEmptyObj(blist.blackedBrands[brand.brandId].blackSeries)) {
                delete blist.blackedBrands[brand.brandId];
              }
            }, function (msg) {
              alert(msg);
            });
        } else {
          blist.removeBlack(brand.id)
            .then(function () {
              brand.brandOnly = false;
              blist.brands[brand.brandId] = brand;
              delete blist.blackedBrands[brand.brandId];
              searchBrand();
              blist.getSeriesListByBrandId(brand.brandId);
            }, function (msg) {
              alert(msg);
            });
        }
      };

		}
	]);

	myApp.controller('companyInfoCtrl', ['$scope', '$modalInstance', 'detail',
		function ($scope, $modalInstance, detail) {

			var formData = $scope.formData = angular.copy(detail.data);

			// function watchPerData(data) {
			// 	$scope.$watch(function () {
			// 		return data.saleDiscountPer;
			// 	}, function () {
			// 		data.saleDiscount = (data.saleDiscountPer / 10).toFixed(4);
			// 	}, true);
			// }

			// formData.saleDiscount = parseFloat(formData.saleDiscount);
			// formData.saleDiscountPer = (formData.saleDiscount * 10).toFixed(2);

			// watchPerData(formData);

			$scope.cancel = function () {
				$modalInstance.dismiss('cancel');
			};

			$scope.submitted = false;
			$scope.submitting = false;
			$scope.invalidActivityName = false;
			$scope.submit = function () {
				$scope.submitted = true;
				if ($scope.edit_company.$invalid) {
					
				} else {
					if (parseFloat(formData.ecommerceRate) < parseFloat(formData.saleDiscount)) {
						alert('销售折扣不能大于电子销售折扣率');
						return;
					}

					//填写促销url后名称不能为空
					if (formData.activityUrl !="" &&  formData.activityName == "") {
						$scope.invalidActivityName = true;
						return ;
					}

					if(formData.orderLevel == "" || typeof formData.orderLevel=="undefined"){
						formData.orderLevel = 0;
					}
					$scope.submitting = true;
					detail.pushInfo(formData)
						.then(function () {
							$modalInstance.close();
							$scope.submitting = false;
							window.location.reload();
						}, function () {
							alert('操作失败，请重试');
							$scope.submitting = false;
						});
					}
				
			};

		}

	]);

	myApp.controller('planCtrl', ['$scope', '$modalInstance', 'plan',
		function ($scope, $modalInstance, plan) {

			var bak = angular.copy(plan.plan);

			$scope.plan = plan;
			console.log(plan);

			$scope.changeChilds = function (ins) {
				if (!ins.checked) {
					angular.forEach(ins.childs, function (c) {
						c.checked = false;
					});
				}
			};

			$scope.cancel = function () {
				plan.updatePlan(bak);
				$modalInstance.dismiss('cancel');
			};

			$scope.submitting = false;
			$scope.submit = function () {
				$scope.submitting = true;
				plan.setPlan()
					.then(function () {
						// $modalInstance.close();
						window.location.reload();
						$scope.submitting = false;
					}, function (info) {
						alert(info);
						$scope.submitting = false;
					});
			};
		}
	]);

	myApp.controller('kindsCtrl', ['$scope', '$modalInstance', 'insurances', 'cid', 'insuranceTyps',
		function ($scope, $modalInstance, insurances, cid, insuranceTyps) {

			var bak = angular.copy(insurances);

			$scope.insurances = insurances;

			$scope.changeChilds = function (ins) {
				if (!ins.checked) {
					angular.forEach(ins.childs, function (c) {
						c.checked = false;
					});
				}
			};

			$scope.cancel = function () {
				insuranceTyps.updateTypes(cid, bak);	
				$modalInstance.dismiss('cancel');
			};

			$scope.submitting = false;
			$scope.submit = function () {
				$scope.submitting = true;
				insuranceTyps.saveSelected(cid)
					.then(function () {
						window.location.reload();
						$scope.submitting = false;
					}, function (info) {
						alert(info);
						$scope.submitting = false;
					});
			};
		}
	]);

	myApp.controller('settleCtrl',['$scope','detail','$modalInstance',function($scope,detail,$modalInstance){
		$scope.detail = detail;
		$scope.verify = [{id:1,name:'线下核保'},{id:2,name:'在线核保'}];
		$scope.settle = [{id:1,name:'接口代付'},{id:2,name:'网页付款'}];
		$scope.verifyWay = detail.data.verifyWay;
		$scope.agencyWay = detail.data.agencyWay;

		var companyCityId = detail.data.companyCityId;

		$scope.saveSettle = function(){
			if (!$scope.verifyWay) {
				alert('请选择核保方式');
				return;
			}

			if (!$scope.agencyWay) {
				alert('请选择结算方式');
				return;
			}

			var param = {
				id:companyCityId,
				verifyWay:$scope.verifyWay,
				agencyWay:$scope.agencyWay
			};

			detail.saveSettle(JSON.stringify(param))
				.then(function(data){
					if(data.success){
						alert(data.message);
						window.location.reload();
						$modalInstance.close();
					}
					else alert(data.message);
					$modalInstance.close();
				});
		};
		$scope.cancel = function () {
			$modalInstance.dismiss('cancel');
		};
	}])

});