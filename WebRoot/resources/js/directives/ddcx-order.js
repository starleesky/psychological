define(function (require) {

	var http = require('bower_components/angularext/angularExt').http;
	require('js/service/common/address');
	require('js/service/common/car');
	require('js/service/common/insurance');
	require('js/service/common/useCharacter');
	require('js/service/common/citys');
	require('js/service/common/expresscompany');
	
	angular.module('App')

	.service('notPassReasons', ['$http', function ($http) {

		var reasons;

		this.getReasons = function () {
			if (!reasons) {
				reasons = [];
				$http.get(angular.path + '/order/verify/update/getfailmessages')
					.success(function (resp) {
						angular.forEach(resp, function (r) {
							reasons.push(r);
						});
					})
					.error(function () {
						alert('获取不通过信息列表失败');
					});
			}

			return reasons;
			
		};

	}])
	/**
	 * 典典车险的订单详情页面，封装成directive以供复用
	 */
	.directive('ddcxOrder', ['$modal', function ($modal) {

		return {
			restrict : 'E',
			templateUrl: angular.path + '/resources/templates/order/order.html',
			scope: {
				order: '=order',
				type: '=type',
				attachlist: '=attachlist',
			},
			link: function (scope, element, attr) {

				// scope.order.data.userCarDto.vehicleLicense.buyPrice = scope.order.data.userCarDto.vehicleLicense.buyPrice / 100;
				var authority = {
					global: 1,
					service: 3,
					underwriter: 4,
					unpaid: 5,
					paid: 6,
					produceinsurance: 7,
					delivery: 8,
					refund: 9,
					unservice: 10,
					refundPolicy: 11
				};

				scope.submitting = false;

				scope.authority = authority[scope.type];

				var templates = [
					angular.path + '/resources/templates/order/user-modal.html',
					angular.path + '/resources/templates/order/carinfo-modal.html',
					angular.path + '/resources/templates/order/jqcc-modal.html'
				];

				scope.changeAttachList = function (attachlist) {
					$modal.open({
						templateUrl: angular.path + '/resources/templates/order/changeInfo.html?time='+new Date(),
						controller: 'changeCtrl',
						backdrop: 'static',
						resolve: {
							attachlist: function () {
								return scope.attachlist;
							},
							orderScope: function(){
								return scope;
							}
						}
					});
				};

				/**
				 * 通用的修改保单数据方法
				 * 通过弹出模态框，显示对应数据进行修改，点击完成进行更新数据
				 */
				scope.edit = function (type) {
					var modal = $modal.open({
						templateUrl: templates[type],
						controller: 'editModalCtrl',
						backdrop: 'static',
						resolve: {
							order: function () {
								return scope.order;
							}
						}
					});

					modal.result.then(function (key) {
						scope.pass[key] = 1;
					});
				};

				/**
				 * 新增商业险条目的方法，弹出模态框进行添加
				 * comm参数没有什么作用
				 */
				var commercialTemplates = [
					angular.path + '/resources/templates/order/add-commercial-modal.html',
					angular.path + '/resources/templates/order/edit-commercial-modal.html'
				];
				scope.commercial = function (type, comm) {
					$modal.open({
						templateUrl: commercialTemplates[type],
						controller: 'commercialCtrl',
						backdrop: 'static',
						resolve: {
							order: function () {
								return scope.order;
							},
							comm: function () {
								return comm || {};
							}
						}
					});
				};

				/**
				 * 删除商业险条目
				 * @param  {string} cid [商业险条目ID]
				 */
				scope.deleting = false;
				scope.deleteCommercialItem = function (cid, tid) {
					if (scope.order.data.insType === 2 && scope.order.data.policyItems.length < 2) {
						alert('删除之后将没有购买任何险种，不能再删除');
						return;
					}
					if (confirm('确定删除？')) {
						scope.deleting = true;
						scope.order.deleteCommercialItem(cid, tid)
							.then(function (amount) {
								scope.deleting = false;
								scope.order.data.commercialItemTrailTotalAmount = amount;
								if (scope.order.data.policyItems.length < 1) {
									scope.order.data.insType = 1;
								}
							}, function (msg) {
								alert(msg);
								scope.deleting = false;
							});
					}
				};

				/**
				 * 修改商业险条目
				 * @param  {Json} comm 商业险条目
				 */
				scope.editCommercialItem = function (comm) {
					$modal.open({
						templateUrl: angular.path + '/resources/templates/order/edit-commercial-item-modal.html',
						controller: 'editCommercialCtrl',
						backdrop: 'static',
						resolve: {
							order: function () {
								return scope.order;
							},
							comm: function () {
								return comm;
							}
						}
					});
				};

				// 核保人员通过核保
				scope.pass = {
					buss: 0,
					jqcc: 0
				};
				scope.underwriterSubmit = function () {
					$modal.open({
						templateUrl: angular.path + '/resources/templates/order/passComment.html',
						controller: 'passCommentCtrl',
						backdrop: 'static',
						resolve: {
							order: function () {
								return scope.order;
							},
							result: function () {
								return {
									mandatoryVerifyResult: scope.pass.jqcc,
									commercialVerifyResult: scope.pass.buss,
									orderVerifyResult: 2
								};
							}
						}
					});
				};

				// 核保人员不通过的时候弹出选择原因的modal
				scope.underwriterNotPass = function () {
					$modal.open({
						templateUrl: angular.path + '/resources/templates/order/notPass.html',
						controller: 'whyNotPassCtrl',
						backdrop: 'static',
						resolve: {
							order: function () {
								return scope.order;
							}
						}
					});
				};

				// 核保人员退回给客服
				scope.backToService = function () {
					if (confirm('一旦提交成功不能修改')) {
						scope.submitting = true;
						scope.order.underwriterSubmit({
							orderVerifyResult: 4
						})
							.then(function () {
								window.location = angular.redirectUrl;
								scope.submitting = false;
							}, function (msg) {
								alert(msg);
								scope.submitting = false;
							});
					}
				};

				// 客服通过核保
				scope.serviceSubmit = function () {
					if (confirm('一旦提交不能修改')) {
						scope.submitting = true;
						scope.order.serviceSubmit()
							.then(function () {
								window.location.href = angular.redirectUrl;
								scope.submitting = false;
							}, function (msg) {
								alert(msg);
								scope.submitting = false;
							});
					}
				};

				// 录入交易单号
				// 权限：已付订单
				scope.inputDealNum = function () {
					$modal.open({
						templateUrl: angular.path + '/resources/templates/order/inputDealNum-modal.html',
						controller: 'inputDealNumCtrl',
						backdrop: 'static',
						resolve: {
							order: function () {
								return scope.order;
							}
						}
					});
				};

				// 录入保单号
				// 权限：待出保
				scope.inputPolicyNum = function () {
					$modal.open({
						templateUrl: angular.path + '/resources/templates/order/inputPolicyNum-modal.html',
						controller: 'inputPolicyNumCtrl',
						backdrop: 'static',
						resolve: {
							order: function () {
								return scope.order;
							}
						}
					});
				};

				// 录入快递单号
				// 权限：待出保
				scope.inputExpressNum = function () {
					$modal.open({
						templateUrl: angular.path + '/resources/templates/order/inputExpressNum-modal.html',
						controller: 'inputExpressNumCtrl',
						backdrop: 'static',
						resolve: {
							order: function () {
								return scope.order;
							}
						}
					});
				};

				// 客服修改交强险+车船税
				scope.editJqcc = function () {
					$modal.open({
						templateUrl: angular.path + '/resources/templates/order/serviceJqcc-modal.html',
						controller: 'serviceEditJqccCtrl',
						backdrop: 'static',
						resolve: {
							order: function () {
								return scope.order;
							}
						}
					});
				};

				// 集中修改商业险
				scope.editCommercial = function () {
					var modal = $modal.open({
						templateUrl: angular.path + '/resources/templates/order/editAllCommercial-modal.html',
						controller: 'editAllCommercialCtrl',
						backdrop: 'static',
						resolve: {
							order: function () {
								return scope.order;
							}
						}
					});
					modal.result.then(function (key) {
						scope.pass[key] = 1;
					});
				};

				// 客服修改商业险
				scope.serviceEditCommercial = function () {
					$modal.open({
						templateUrl: angular.path + '/resources/templates/order/edit-commercial-modal.html',
						controller: 'serviceEditCommercialCtrl',
						backdrop: 'static',
						resolve: {
							order: function () {
								return scope.order;
							}
						}
					});
				};

				// 退款审核
				scope.refundData = null;
				function openRefundModal() {
					$modal.open({
						templateUrl: angular.path + '/resources/templates/order/audit-refund-modal.html',
						controller: 'auditRefundCtrl',
						backdrop: 'static',
						resolve: {
							order: function () {
								return scope.order;
							},
							data: function () {
								return scope.refundData;
							}
						}
					});
				}
				scope.auditRefund = function () {
					if (scope.refundData) {
						openRefundModal();
					} else {
						scope.order.getRefundInfo()
							.then(function (data) {
								scope.refundData = data;
								openRefundModal();
							}, function (msg) {
								alert(msg);
							});						
					}
				};

				// 退款
				scope.refund = function () {
					$modal.open({
						templateUrl: angular.path + '/resources/templates/order/refund-modal.html',
						controller: 'refundCtrl',
						backdrop: 'static',
						resolve: {
							order: function () {
								return scope.order;
							}
						}
					});
				};

				// 添加交强险
				scope.addMand = function () {
					if (confirm('确定添加？')) {
						scope.submitting = true;
						scope.order.addMand()
							.then(function () {
								scope.submitting = false;
								scope.order.data.insType = 0;
							}, function (msg) {
								alert(msg);
								scope.submitting = false;
							});
					}
				};

				// 删除交强险
				scope.deleteMand = function () {
					if (scope.order.data.insType === 1) {
						alert('目前只有交强险和车船税，不能再删除');
						return;
					}
					if (confirm('确定删除？')) {
						scope.submitting = true;
						scope.order.deleteMand()
							.then(function () {
								scope.order.data.insType = 2;
								scope.submitting = false;
							}, function (msg) {
								alert(msg);
								scope.submitting = false;
							});
					}
				};

				// 初始化商业险
				scope.initCommercial = function () {
					if (confirm('确定添加？')) {
						scope.submitting = true;
						scope.order.initCommercial()
							.then(function () {
								scope.submitting = false;
							}, function (msg) {
								alert(msg);
								scope.submitting = true;
							});
					}
				};

				// 修改承保条件
				scope.editCondition = function () {
					$modal.open({
						templateUrl: angular.path + '/resources/templates/order/edit-condition-modal.html',
						controller: 'editConditionCtrl',
						backdrop: 'static',
						resolve: {
							order: function () {
								return scope.order;
							}
						}
					});
				};

				// 查看身份证
				scope.showImg = function (url) {
					$modal.open({
						templateUrl: angular.path + '/resources/templates/order/show-idcard-img-modal.html',
						controller: 'showImgnCtrl',
						backdrop: 'static',
						size: 'lg',
						resolve: {
							imgInfo: function () {
								return {
									url : url,
									downloadUrl: url + '?_upd=true'
								}
							}
						}
					});
				};

				// 加油卡折现
				scope.gasCardDiscount = function (flag) {
					scope.order.gasCardDiscount(flag);
				};

				// 返回上级的方法
				scope.goBack = function () {
					window.location.href = angular.redirectUrl;
				};

				// 取消订单
				scope.cancelOrder = function () {
					if (confirm('确定取消？一旦提交无法修改')) {
						scope.order.cancel()
							.then(function () {
								window.location.href = angular.redirectUrl;
							}, function (msg) {
								alert(msg);
							});
					}
				};

				// 申请退款
				scope.applyReFunding = false;
				scope.applyReFund = function () {
					if (confirm('是否确定申请？')) {
						scope.applyReFunding = true;
						scope.order.applyReFund()
							.then(function (data) {
								window.location.href = data.reloadUrl;
								scope.applyReFunding = false;
							}, function (msg) {
								alert(msg);
								scope.applyReFunding = false;
							});
					}
				};

				//申请退保
				scope.applyReFundPolicying = false
				scope.applyReFundPolicy = function () {
					if (confirm('是否确定申请？')) {
						scope.applyReFundPolicying = true;
						scope.order.applyReFundPolicy()
							.then(function (data) {
								window.location.href = data.reloadUrl;
								scope.applyReFundPolicying = false;
							}, function (msg) {
								alert(msg);
								scope.applyReFundPolicying = false;
							});
					}
				};

				// 退保
				scope.refundPolicy = function () {
					$modal.open({
						templateUrl: angular.path + '/resources/templates/order/refund-policy-modal.html',
						controller: 'refundPolicyCtrl',
						backdrop: 'static',
						size: 'lg',
						resolve: {
							order: function () {
								return scope.order;
							}
						}
					});
				};

				// 转单
				scope.transferPolicy = function () {
					$modal.open({
						templateUrl: angular.path + '/resources/templates/order/transfer-policy-modal.html',
						controller: 'transferPolicyCtrl',
						backdrop: 'static',
						size: 'lg',
						resolve: {
							order: function () {
								return scope.order;
							}
						}
					});
				};

			}
		};

	}])
	.controller('refundPolicyCtrl', ['$scope', '$modalInstance', 'order', '$http',
		function ($scope, $modalInstance, order, $http) {

			$scope.remark = '';
			$scope.StringValueOfChasedAmount = 0;

			$scope.submitting = false;
			$scope.submit = function () {
				if (!$scope.remark) {
					alert('备注必须填写');
					return;
				}
				if (isNaN($scope.StringValueOfChasedAmount)) {
					alert('请正确填写追回金额！');
					return;
				}
				$scope.submitting = true;
				http($http).post(angular.path + '/order/refundPolicy/confirm/do', {
					refundPolicyOrderId: order.data.refundPolicyOrderId,
					remark: $scope.remark,
					StringValueOfChasedAmount : $scope.StringValueOfChasedAmount
				})
					.success(function (resp) {
						if (resp.success) {
							if (resp.data.reload) {
								window.location.reload();
							} else {
								$modalInstance.dismiss();
							}
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
				$modalInstance.dismiss();
			};
		}
	])
	.controller('transferPolicyCtrl',['$scope', '$modalInstance', 'order', '$http',
		function ($scope, $modalInstance, order, $http) {
		
			$scope.order = order;
			$scope.cityId = '';
			$scope.companyCityId = '';
			$scope.submitting = false;
			$scope.validCity = false;
			$scope.validCompany = false;

			$scope.canbeTransferPolicyCityList = [];
			$scope.cityName = '';

			$scope.canbeTransferPolicyCompanyList = []
			$scope.companyCityId = '';

			//加载可转单城市列表
			$scope.getCanbeTransferCityList = function(){
				$http.get(angular.path + '/order/transferPolicy/getCanBeTransferPolicyCityList?orderId='+order.data.id)
					.success(function (resp) {
						if (resp.success) {
							$scope.canbeTransferPolicyCityList = resp.data;
						} else {
							$scope.canbeTransferPolicyCityList = [];
							alert("获取城市列表失败！");
						}
					})
					.error(function (resp) {
						$scope.canbeTransferPolicyCityList = [];
						alert("获取城市列表失败！");
					});
			}
			$scope.changeCity = function(cityId){
				$http.get(angular.path + '/order/transferPolicy/getCanBeTransferPolicyCompanyList?cityId='+cityId)
					.success(function (resp) {
						if (resp.success) {
							$scope.canbeTransferPolicyCompanyList = resp.data;
						} else {
							$scope.canbeTransferPolicyCompanyList = [];
							alert("当前城市没有保险公司可提供转单，请另选城市.");
						}
					})
					.error(function (resp) {
						$scope.canbeTransferPolicyCompanyList = [];
						alert("获取城市保险公司列表失败！");
					});
			}
			$scope.submit = function () {
				$scope.validCity = false;
				$scope.validCompany = false;
				if ('' == $scope.cityId) {
					$scope.validCity = true;
				};
				if('' == $scope.companyCityId){
					$scope.validCompany = true;
				}
				if($scope.validCity || $scope.validCompany){
					return;
				}else{
					$scope.submitting = true;
				}
				http($http).post(angular.path + '/order/transferPolicy/confirm/do', {
					orderId: order.data.id,
					companyCityId : $scope.companyCityId
				})
					.success(function (resp) {
						if (resp.success) {
							if (resp.data.reload) {
								alert("转单成功，将自动为您跳转到核保页面。");
								window.location.href = resp.data.reloadUrl;
							} else {
								$modalInstance.dismiss();
							}
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

			$scope.cancel = function () {
				$modalInstance.dismiss();
			};

			$scope.getCanbeTransferCityList();
		}
	])
	.controller('changeCtrl',['$scope','$modalInstance',  'attachlist', '$http', 'orderScope',
		function ($scope, $modalInstance, attachlist, $http, orderScope) {
			
			$scope.attachlist = angular.copy(attachlist);
			// for (var i = $scope.attachlist.data.orderMode.length - 1; i >= 0; i--) {
			// 	$scope.attachlist.data.orderMode[i].checked = false;
			// };

			$scope.submit = function () {
				var attach_ids = "";
				for (var i = $scope.attachlist.data.orderMode.length - 1; i >= 0; i--) {
					if($scope.attachlist.data.orderMode[i].checked){
						if(attach_ids.length < 1){
							attach_ids += ""+$scope.attachlist.data.orderMode[i].typeId;
						}else{
							attach_ids += ":"+$scope.attachlist.data.orderMode[i].typeId;
						}
					}
				};
				http($http).post(angular.path + '/order/audit/updateAttachConfig', {
					orderId: attachlist.data.orderId,
					attachIds: attach_ids
				}).success(function (resp) {
					if (resp.success) {
							orderScope.attachlist = resp;
							$modalInstance.dismiss();
						} else {
							alert(resp.message);
						}	
				})
				.error(function (resp) {
					alert(resp.message);
					$scope.submitting = false;
				});
			};

			$scope.cancel = function () {
				$modalInstance.dismiss();
			};
		}
	])
	/**
	 * 跟修改保单基础信息有关的modal操作Ctrl
	 */
	.controller('editModalCtrl', ['$scope', '$modalInstance','$interval', 'order', 'address', 'cartype', 'usenatures', 'citys',
		function ($scope, $modalInstance,$interval, order, address, cartype, usenatures, citys) {

			var o = $scope.order = angular.copy(order.data);

			$scope.o = order;

			$scope.submitting = false;

			// 如果没有被保人列表，获取一下
			if (!order.insuredList) {
				order.getInsuredList()
					.then(function () {
					}, function () {
					});
			}

			o.provinceId += '';
			o.cityId += '';
			o.districtId += '';
			// 地址选择
			var addressCtrl = $scope.addressCtrl = {
				provices : address.getProvinceList(),
				citys: o.provinceId ? address.getCitysByPid(o.provinceId) : {},
				districts: o.cityId ? address.getDistrictsByCid(o.cityId) : {}
			};

			$scope.carPro = '';  // 行驶城市使用的省信息
			$scope.selectCarP = function () {
				addressCtrl.citys = address.getCitysByPid($scope.carPro);
			};

			$scope.testId = "4";
			$scope.selectP = function () {
				addressCtrl.citys = address.getCitysByPid(o.provinceId);
				o.province = addressCtrl.provices[o.provinceId];
				// console.log($scope.testId);
				o.cityId = "";
				o.districtId = "";
			};

			$scope.selectC = function () {
				addressCtrl.districts = address.getDistrictsByCid(o.cityId);
				o.city = addressCtrl.citys[o.cityId];
				o.districtId = "";
			};

			$scope.selectD = function () {
				o.district = addressCtrl.districts[o.districtId];
			};
			// 地址选择结束

			// 选择时间
			$scope.date = o.mandatoryEffectDate;
			$scope.format = 'yyyy-MM-dd';
			$scope.opened = false;
			$scope.toggleCal = function (type) {
				$scope.opened = type;
			};
			$scope.formatDate = function () {
				o.mandatoryEffectDate = $scope.date.getFullYear() + '-' + ($scope.date.getMonth() + 1) + '-' + $scope.date.getDate();
			};
			// 选择时间结束

			$scope.usenatureList = usenatures.getAllUsenatures();

			// 选择车型车系
			var carCtrl = $scope.carCtrl = {
				brands: cartype.getBrands(),
				series: o.userCarDto.carBrand ? cartype.getSeriesByBid(o.userCarDto.carBrand) : {},
				models: o.userCarDto.carSeries ? cartype.getModelsBySid(o.userCarDto.carSeries) : {}
			};

			var selectBrand = $scope.selectBrand = function () {
				if(!o.userCarDto.carBrand){
					return ;
				}
				carCtrl.series = cartype.getSeriesByBid(o.userCarDto.carBrand);
				o.userCarDto.carBrandName = carCtrl.brands[o.userCarDto.carBrand].brandName;
			};
			var tmpInterval = $interval(function(){
				if(carCtrl.brands.length>0){
					$interval.cancel(tmpInterval);
					selectBrand();
				}
			},50);

			var selectSerie = $scope.selectSerie = function () {
				carCtrl.models = cartype.getModelsBySid(o.userCarDto.carSeries);
				o.userCarDto.carSeriesName = carCtrl.series[o.userCarDto.carSeries].seriesName;
				o.userCarDto.carModel = "";
			};

			$scope.selectModel = function () {
				o.userCarDto.carModelName = carCtrl.models[o.userCarDto.carModel].modelName;
			};
			// 选择车型车系结束
			
			$scope.citys = citys.getAllCitys();

			$scope.selectCity = function () {
				angular.forEach($scope.citys, function (v) {
					if (v.cityId == o.userCarDto.cityId) {
						o.userCarDto.cityName = v.cityName;
					}
				});
			};

			// 修改交强险+车船税信息的接口方法
			$scope.updatejqcc = function () {
				$scope.submitted = true;
				if ($scope.jqcc_form.$invalid) {
					return;
				}
				$scope.submitting = true;
				order.updatejqcc($scope.order)
					.then(function () {
						$modalInstance.close('jqcc');
						$scope.submitting = false;
					}, function (msg) {
						alert(msg);
						$scope.submitting = false;
					});
			};

			$scope.choseInsured = function (insured) {
				o.userName = insured.insured;
				o.phone = insured.phone;
				o.idCardNo = insured.idcardNo;
			};

			// 修改用户信息的接口方法
			$scope.saveUser = function () {
				$scope.submitting = true;
				order.updateUser($scope.order)
					.then(function () {
						$modalInstance.dismiss();
						$scope.submitting = false;
					}, function (msg) {
						alert(msg);
						$scope.submitting = false;
					});
			};

			// 修改车辆信息的接口方法
			$scope.updateCarInfo = function () {
				$scope.submitted = true;
				if ($scope.edit_car_form.$invalid) {
					return;
				}
				$scope.submitting = true;
				order.updateCarInfo($scope.order)
					.then(function () {
						var tmpObj = null;
						for (var i = 0; i < $scope.usenatureList.length; i++) {
							if(($scope.usenatureList[i].fieldKey-0) == $scope.order.userCarDto.vehicleLicense.useCharacterKey){
								tmpObj = $scope.usenatureList[i];
							}
						}
						$scope.order.userCarDto.vehicleLicense.useCharacterKeyName = tmpObj!=null?tmpObj.fieldValue:"";
						$modalInstance.dismiss();
						$scope.submitting = false;
					}, function (msg) {
						alert(msg);
						$scope.submitting = false;
					});
			};

			// 帮助方法
			$scope.convertToInt = function (str) {
				return parseInt(str);
			};

			// 取消按钮对应的方法
			$scope.cancel = function () {
				$modalInstance.dismiss();
			};
		}
	])
	/**
	 * 跟保单有关的modal操作Ctrl
	 */
	.controller('commercialCtrl', ['$scope', '$modalInstance', 'order', 'commercial', 'comm',
		function ($scope, $modalInstance, order, commercial, comm) {

			var c = $scope.comm = angular.copy(comm);
			$scope.submitted = false;
			$scope.submitting = false;

			var o = $scope.order = angular.copy(order.data);

			var addData = $scope.addData = {
				policyId: o.policyId, // 保单ID
				orderId: o.id, // 订单ID
				policyItemId: '',
				insTypeId: '',			// 险种ID
				insTypeName: '',
				condSelectValue: '',		// 默认值
				verifyAmount: '',   // 核保金额
				condSelectName: '',
				trialAmount: ''
			};

			$scope.selectedComm = '';

			// 获取试算金额
			function getCalcAmount() {
				order.getCalcAmount(addData.insTypeId, addData.condSelectValue, addData.policyId, 1)
					.then(function (value) {
						addData.trialAmount = value;
					}, function (msg) {
						alert(msg);
					});
			};

			$scope.confirmSelect = function () {
				$scope.addData.condSelectValue = $scope.selectedComm.selectValue;
				$scope.addData.condSelectName = $scope.selectedComm.selectName;

				getCalcAmount();

			};

			var commercials = $scope.commercials = commercial.getCommercialsById(o.companyCityId);

			$scope.changeIns = function () {
				$scope.selectedComm = '';
				$scope.addData.condSelectValue = '';
				$scope.addData.condSelectName = '';
				addData.insTypeName = commercials[addData.insTypeId].typeName;
				if (commercials[addData.insTypeId].options && 
						commercials[addData.insTypeId].options.length > 0){
					return;
				} else {
					getCalcAmount();
				}
			};

			// 添加新险种的方法
			$scope.addCommercial = function () {
				$scope.submitted = true;
				if ($scope.add_form.$invalid || !addData.trialAmount) {
					return;
				}
				$scope.submitting = true;
				order.addCommercial($scope.addData)
					.then(function (amount) {
						if (order.data.insType === 1) {
							order.data.insType = 0;
						}
						order.data.commercialItemTrailTotalAmount = amount;
						$modalInstance.dismiss();
						$scope.submitting = false;
					}, function (msg) {
						alert(msg);
						$scope.submitting = false;
					});
			};

			// 更新险种信息
			$scope.updateComm = function () {
				$scope.submitted = true;
				if ($scope.edit_form.$invalid) {
					return;
				}
				$scope.submitting = true;
				order.updateComm(c)
					.then(function () {
						$modalInstance.dismiss();
						$scope.submitting = false;
					}, function (msg) {
						alert(msg);
						$scope.submitting = false;
					});
			};

			// 取消按钮对应的方法
			$scope.cancel = function () {
				$modalInstance.dismiss();
			};
		}
	])
	/**
	 * 不通过的时候选择不通过的理由时使用的modal controller
	 */
	.controller('whyNotPassCtrl', ['$scope', '$modalInstance', 'order', 'notPassReasons',
		function ($scope, $modalInstance, order, notPassReasons) {

			var reasons = $scope.reasons = notPassReasons.getReasons();

			$scope.order = order.data;
			$scope.errorMessage = '';
			$scope.selectedReason = '';

			$scope.setErrorMsg = function () {
				if ($scope.selectedReason) {
					$scope.errorMessage = $scope.selectedReason;
					$scope.select = true;
				} else {
					$scope.errorMessage = $scope.selectedReason;
					$scope.select = false;
				}
			};

			$scope.submitting = false;
			$scope.notPass = function () {
				if (!$scope.errorMessage) {
					alert('请选择未通过原因');
					return;
				}
				if (confirm('一旦提交不能修改')) {
					$scope.submitting = true;
					order.underwriterSubmit({
						mandatoryVerifyResult: 0,
						commercialVerifyResult: 0,
						// errorMessageCode: $scope.selectedReason,
						verifyDesc: $scope.errorMessage,
						orderVerifyResult: 3
					})
						.then(function () {
							window.location.href = angular.redirectUrl;
							$scope.submitting = false;
						}, function (msg) {
							alert(msg);
							$scope.submitting = false;
						});
				}
			};

			// 取消按钮对应的方法
			$scope.cancel = function () {
				$modalInstance.dismiss();
			};

		}
	])
	/**
	 * 录入订单号的Ctrl
	 */
	.controller('inputDealNumCtrl', ['$scope', '$modalInstance', 'order',
		function ($scope, $modalInstance, order) {

			$scope.dealNum = '';

			$scope.trim = function () {
				$scope.dealNum = $.trim($scope.dealNum);
			};

			$scope.submitting = false;
			$scope.saveDealNum = function () {
				if (!$scope.dealNum || $scope.dealNum === '') {
					alert('请输入正确的订单号');
					return;
				}
				$scope.submitting = true;
				order.inputDealNum($scope.dealNum)
					.then(function () {
						window.location.href = angular.redirectUrl;
						$scope.submitting = false;
					}, function (msg) {
						alert(msg);
						$scope.submitting = false;
					});
			};

			// 取消按钮对应的方法
			$scope.cancel = function () {
				$modalInstance.dismiss();
			};

		}
	])
	/**
	 * 录入保单号的Ctrl
	 */
	.controller('inputPolicyNumCtrl', ['$scope', '$modalInstance', 'order',
		function ($scope, $modalInstance, order) {

			$scope.no = {
				mandatoryPolicyNo: '',
				commercialPolicyNo: ''
			};

			// $scope.mandatoryPolicyNo = '';
			// $scope.commercialPolicyNo = '';
			$scope.insType = order.data.insType;
			$scope.submitted = false;

			$scope.submitting = false;
			$scope.savePolicyNum = function () {
				$scope.submitted = true;
				if ($scope.policy_num_form.$invalid) {
					return;
				}
				$scope.submitting = true;
				order.inputPolicyNum($scope.no.mandatoryPolicyNo, $scope.no.commercialPolicyNo)
					.then(function () {
						// $modalInstance.dismiss();
						window.location.href = angular.redirectUrl;
						$scope.submitting = false;
					}, function (msg) {
						alert(msg);
						$scope.submitting = false;
					});
			};

			// 取消按钮对应的方法
			$scope.cancel = function () {
				$modalInstance.dismiss();
			};

		}
	])
	/**
	 * 录入快递单号的Ctrl
	 */
	.controller('inputExpressNumCtrl', ['$scope', '$modalInstance', 'order', 'expresscompany',
		function ($scope, $modalInstance, order, expresscompany) {

			$scope.expresscompanys = expresscompany.getAllCompanys();
			$scope.order = order.data;

			$scope.exnum = '';
			$scope.company = null;

			$scope.submitting = false;
			$scope.saveExpressNum = function () {
				if ($.trim($scope.exnum) == '') {
					alert('请输入正确的快递单号');
					return;
				}
				if (!$scope.company) {
					alert('请选择快递公司');
					return;
				}
				$scope.submitting = true;
				order.saveExpressNum($scope.company.id, $scope.exnum, order.data.userCarId,order.data.mandatoryEffectDate, order.data.commercialEffectDate)
					.then(function () {
						window.location.href = angular.redirectUrl;
						$scope.submitting = false;
					}, function (msg) {
						alert(msg);
						$scope.submitting = false;
					});
			};

			// 取消按钮对应的方法
			$scope.cancel = function () {
				$modalInstance.dismiss();
			};

		}
	])
	/**
	 * 客服修改交强险+车船税基础信息的modal Ctrl
	 */
	.controller('serviceEditJqccCtrl', ['$scope', '$modalInstance', 'order',
		function ($scope, $modalInstance, order) {

			$scope.date = order.data.mandatoryEffectDate;
			var formData = angular.copy(order.data);

			$scope.format = 'yyyy-MM-dd';
			$scope.opened = false;
			$scope.toggleCal = function (type) {
				$scope.opened = type;
			};

			$scope.formatDate = function () {
				formData.mandatoryEffectDate = $scope.date.getFullYear() + '-' + ($scope.date.getMonth() + 1) + '-' + $scope.date.getDate();
			};

			$scope.data = angular.copy(order.data);

			$scope.submitted = false;
			$scope.submitting = false;
			$scope.update = function () {
				$scope.submitted = true;
				if ($scope.jqcc_form.$invalid) {
					return;
				}
				$scope.submitting = true;
				order.serviceUpdatejqcc(formData)
					.then(function (data) {
						if (data.reload) {
							window.location.reload();
						}
						$modalInstance.dismiss();
						$scope.submitting = false;
					}, function (msg) {
						alert(msg);
						$scope.submitting = false;
					});
			};

			// 关闭modal的方法
			$scope.cancel = function () {
				$modalInstance.dismiss();
			};

		}
	])
	/**
	 * 批量录入商业险核保金额的modal Ctrl
	 */
	.controller('editAllCommercialCtrl', ['$scope', '$modalInstance', 'order',
		function ($scope, $modalInstance, order) {

			$scope.order = angular.copy(order.data);

			$scope.submitted = false;

			$scope.submitting = false;
			$scope.update = function () {

				$scope.submitted = true;

				if ($scope.edit_form.$invalid) {
					return;
				}
				$scope.submitting = true;
				order.updateVerifyAmount($scope.order)
					.then(function () {
						// 需要把核保总金额替换
						$modalInstance.close('buss');
						$scope.submitting = false;
					}, function (msg) {
						alert(msg);
						$scope.submitting = false;
					});

			};

			// 关闭modal的方法
			$scope.cancel = function () {
				$modalInstance.dismiss();
			};

		}
	])
	/**
	 * 核保人员核保通过之后录入附言
	 */
	.controller('passCommentCtrl', ['$scope', '$modalInstance', 'order', 'result',
		function ($scope, $modalInstance, order, result) {
			
			result.verifyDesc = '';
			$scope.result = result;

			$scope.order = order.data;

			// 4-30日修改：弹出框的保单面额是核保金额相加，不是典典让利价
			$scope.order.mandatoryVerifyAmount = $scope.order.mandatoryVerifyAmount || 0;
			$scope.order.commercialItemVerifyTotalAmount = $scope.order.commercialItemVerifyTotalAmount || 0;

			$scope.allAmount = 
				result.mandatoryVerifyResult == 1 && result.commercialVerifyResult == 1 ? 
				(
					parseFloat($scope.order.mandatoryVerifyAmount)+
					parseFloat($scope.order.commercialItemVerifyTotalAmount)
				).toFixed(2)
				:
				result.commercialVerifyResult == 1 ?
				$scope.order.commercialItemVerifyTotalAmount
				:
				$scope.order.mandatoryVerifyAmount;

			$scope.submitting = false;
			$scope.addComment = function () {
				if (confirm('一旦通过不能修改')) {
					$scope.submitting = true;
					order.underwriterSubmit(result)
						.then(function () {
							// scope.underwriterComment();
							window.location.href = angular.redirectUrl;
							$scope.submitting = false;
						}, function (msg) {
							alert(msg);
							$scope.submitting = false;
						});

				}
			};

			$scope.cancel = function () {
				$modalInstance.dismiss();
			};

		}
	])

	.controller('serviceEditCommercialCtrl', ['$scope', '$modalInstance', 'order',
		function ($scope, $modalInstance, order) {

			$scope.date = order.data.commercialEffectDate;
			var formData = {
				commercialEffectDate: $scope.date
			};

			$scope.opened = false;
			$scope.format = 'yyyy-MM-dd';
			$scope.toggleCal = function (type) {
				$scope.opened = type;
			};

			$scope.formatDate = function () {
				formData.commercialEffectDate = $scope.date.getFullYear() + '-' + (1 + $scope.date.getMonth()) + '-' + $scope.date.getDate();
			};

			$scope.submitted = false;

			$scope.submitting = false;
			$scope.update = function () {
				$scope.submitted = true;
				if ($scope.comm_form.$invalid) {
					return;
				}
				$scope.submitting = true;
				order.serviceUpdateCommercial(formData)
					.then(function (data) {
						if (data.reload) {
							window.location.reload();
						}
						$modalInstance.dismiss();
						$scope.submitting = false;
					}, function (msg) {
						alert(msg);
						$scope.submitting = false;
					});
			};

			$scope.cancel = function () {
				$modalInstance.dismiss();
			};
		}
	])
	/**
	 * 修改商业险的操作类
	 */
	.controller('editCommercialCtrl', ['$scope', '$modalInstance', 'order', 'comm', 'commercial',
		function ($scope, $modalInstance, order, comm, commercial) {

			var addData = $scope.addData = {
				id: comm.policyItemId,
				trialAmount: 0,
				condSelectName: '',
				condSelectValue: ''
			};

			$scope.comm = comm;
			$scope.commercials = commercial.getCommercialsById(order.data.companyCityId);
			$scope.selectedValue = comm.condSelectValue;

			// 获取试算金额
			$scope.calcing = false;
			$scope.getCalcAmount = function() {
				$scope.calcing = true;
				order.getCalcAmount(comm.insTypeId, $scope.selectedValue.selectValue, order.data.policyId, 2)
					.then(function (value) {
						addData.trialAmount = value;
						$scope.calcing = false;
					}, function (msg) {
						alert(msg);
						$scope.calcing = false;
					});
			};

			$scope.submitting = false;
			$scope.edit = function () {
				addData.condSelectValue = $scope.selectedValue.selectValue;
				addData.condSelectName = $scope.selectedValue.selectName;
				$scope.submitting = true;
				order.editCommercialItem(addData)
					.then(function (resp) {
						angular.extend(order.data, resp);
						// comm.condSelectValue = $scope.selectedValue.selectValue;
						// comm.condSelectName = $scope.selectedValue.selectName;
						// comm.trialAmount = addData.trialAmount;
						order.data.commercialItemTrailTotalAmount = resp.trailAmountTotal
						$modalInstance.dismiss();
						$scope.submitting = false;
					}, function (msg) {
						alert(msg);
						$scope.submitting = false;
					});
			};

			$scope.cancel = function () {
				$modalInstance.dismiss();
			};

		}
	])
	/**
	 * 退款申请审核的Ctrl
	 */
	.controller('auditRefundCtrl', ['$scope', '$modalInstance', 'order', 'data',
		function ($scope, $modalInstance, order, data) {
			$scope.data = data;

			var formData = $scope.formData = angular.extend({}, data, {
				verifyResult: 1,
				failReason: ''
			});

			$scope.calcRefundAmount = function () {
				var num = (parseFloat(formData.oriAmount) - parseFloat(formData.deductAmount)).toFixed(2);
				formData.refundAmount = isNaN(num) ? 0 : num;
			};

			$scope.submitting = $scope.submitted = false;
			$scope.submit = function () {
				$scope.submitted = true;
				if ($scope.audit_form.$invalid) {
					return;
				}
				if (parseFloat(formData.refundAmount) < 0) {
					alert('退款金额不能小于0');
					return;
				}
				$scope.submitting = true;
				order.submitAuditRefund($scope.formData)
					.then(function () {
						$modalInstance.dismiss();
						$scope.submitting = false;
					}, function (msg) {
						alert(msg);
						$scope.submitting = false;
					});
			};

			$scope.cancel = function () {
				$modalInstance.dismiss();
			};

		}
	])
	/**
	 * 退款操作的Ctrl
	 */
	.controller('refundCtrl', ['$scope', '$modalInstance', 'order',
		function ($scope, $modalInstance, order) {
			$scope.order = order.data;

			order.getRefundInfo()
				.then(function (data) {
					$scope.money = data;
				}, function () {

				});

			function openURL(url) {
				window.location.href = url;
			}

			function handlerMsg(data) {
				switch (data.paymentMethod) {
					case 3:
						if (data.code == 0) {
							alert(data.msg);
							order.orderStatus = 16;
							$modalInstance.dismiss();
						} else {
							alert(data.msg);
						}
						break;
					case 5:
						alert(data.code);
				}
			}

			$scope.submitting = false;
			$scope.submit = function () {
				$scope.submitting = true;
				var newWindow
				if ($scope.order.orderPaymentMethod == 2) {
					newWindow = window.open('', '', 'height=600, width=1000');
				}
				
				order.refund()
					.then(function (data) {
						if (newWindow) {
							newWindow.location = data.data.openUrl;
						} else {
							handlerMsg(data.data);
						}
						// switch (data.data.action) {
						// 	case 2:
						// 		// openURL(data.data.openUrl);
						// 		newWindow.location = data.data.openUrl;
						// 		break;
						// 	case 1:
						// 		handlerMsg(data.data);
						// 		break;
						// }
						$scope.submitting = false;
					}, function (msg) {
						alert(msg);
						newWindow.close();
						$scope.submitting = false;
					});
			};

			$scope.cancel = function () {
				$modalInstance.dismiss();
			};

		}
	])
	/**
	 * 修改承保条件Ctrl
	 */
	.controller('editConditionCtrl', ['$scope', '$modalInstance', 'order',
		function ($scope, $modalInstance, order) {
			$scope.order = angular.copy(order.data);
			if (!$scope.order.specifyDrivers) $scope.order.specifyDrivers = [];
			$scope.submitting = false;
			$scope.submitted = false;

			$scope.addDriver = function () {
				$scope.order.specifyDrivers.push({
					id: '',
					name: '',
					idcardNo: '',
					drivingClass: '',
					firstissuedate: ''
				});
			};

			$scope.removing = false;
			$scope.removeDriver = function (index) {
				if ($scope.order.specifyDrivers[index].id) {
					$scope.removing = true;
					order.removeDriver($scope.order.specifyDrivers[index].id)
						.then(function () {
							$scope.order.specifyDrivers.splice(index, 1);
							$scope.removing = false;
						}, function (msg) {
							alert(msg);
							$scope.removing = false;
						});
				} else {
					$scope.order.specifyDrivers.splice(index, 1);
				}
			};

			$scope.submit = function () {
				$scope.submitted = true;
				if ($scope.edit_condition.$invalid) {
					return;
				}
				$scope.submitting = true;
				order.editCondition($scope.order)
					.then(function () {
						$modalInstance.dismiss();
					}, function (msg) {
						$scope.submitting = false;
						alert(msg);
					});
			};

			$scope.cancel = function () {
				$modalInstance.dismiss();
			};


		}
	])
	.controller('showImgnCtrl', ['$scope', '$modalInstance', 'imgInfo',
		function ($scope, $modalInstance, imgInfo) {
			$scope.imgInfo = imgInfo;
			console.log(imgInfo);
			console.log(imgInfo.url);
			var imgInfoString = imgInfo.url.substring(1,imgInfo.url.length-1);
			var imgInfoList= new Array(); //定义一数组 
			imgInfoList=imgInfoString.split(","); //字符分割
			var resultList = new Array();
			for (var i = imgInfoList.length - 1; i >= 0; i--) {
				var imgSrc = imgInfoList[i];
				var resultImg = imgSrc.substring(1,imgSrc.length-1);
				resultList.push(resultImg);

			};
			$scope.imgInfo.url = resultList;

      $scope.cancel = function () {
        $modalInstance.dismiss();
      }

		}
	]);

});