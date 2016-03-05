define(function (require) {

	var http = require('bower_components/angularext/angularExt').http;
	
	angular.module('App')
	.service('ddcxOrder', ['$http', '$q',
		function ($http, $q) {

			function Order(id, userType) {
				if (!id) throw new Error('创建order必须要id', 'order.js', '10');
				this.id = id;
				this.status = 0; // 0正常，1失败
				this.type = userType;
				this.init();
			}

			Order.prototype.init = function() {
				var type = this.type;
				this.api = {
					updateUserinfo: '/order/verify/' + type + '/update/userinfo',
					updateCarinfo: '/order/verify/' + type + '/update/carinfo',
					updateMandatory: '/order/verify/' + type + '/update/mandatory',
					addCommercial: '/order/verify/' + type + '/commercial/additem/dosubmit',
					updateCommercial: '/order/verify/' + type + '/commercial/updateitem',
					deleteCommercial: '/order/verify/' + type + '/commercial/deleteitem'
				}
			};

			Order.prototype.fetch = function(url) {
				var self = this;
				return $q(function (resolve, reject) {
					http($http).post(url, {orderId: self.id})
						.success(function (resp) {
							self.data = resp;
							if (resp.hasOwnProperty('success')) {
								reject(resp.message);
							} else {
								resolve();
							}
							self.data.userCarDto.vehicleLicense.buyPrice = self.data.userCarDto.vehicleLicense.buyPrice / 100;
						})
						.error(function (resp) {
							self.status = 1;
							reject(resp.message);
						});
				});
			};

			// 更新用户信息
			Order.prototype.updateUser = function(order) {
				var self = this;
				return $q(function (resolve, reject) {
					http($http).post(angular.path + '/order/audit/update/do/userinfo', getUserInfo(order))
						.success(function (resp) {
							if (resp.success) {
								self.data = order;
								resolve();
							}
							else {
								reject(resp.message);
							}
						})
						.error(function () {
							reject('修改用户信息出错');
						});
				});
			};

			// 更新用户信息使用的帮助方法，用于封装form数据格式
			function getUserInfo(order) {
				var formData = {};

				formData['orderId'] = order.id;
				// formData['recommendCode'] = order.recommendCode;

				formData['policy.insuredInfoId'] = order.insuredInfoId;
				formData['policy.idcardNo'] = order.idCardNo;
				formData['policy.insured'] = order.userName;
				formData['policy.phone'] = order.phone;

				formData['orderAddress.name'] = order.receiverName;
				formData['orderAddress.phone'] = order.receiverPhone;
				formData['orderAddress.provinceId'] = order.provinceId;
				formData['orderAddress.province'] = order.province;
				formData['orderAddress.cityId'] = order.cityId;
				formData['orderAddress.city'] = order.city;
				formData['orderAddress.districtId'] = order.districtId;
				formData['orderAddress.district'] = order.district;
				formData['orderAddress.detail'] = order.detail;

				return formData;
			};

			// 跟新保险车辆信息接口
			Order.prototype.updateCarInfo = function(order) {
				var self = this;
				return $q(function (resolve, reject) {
					http($http).post(angular.path + '/order/audit/update/do/carinfo', getCarInfo(order))
						.success(function (resp) {
							if (resp.success) {
								self.data = order;
								resolve();
							} else {
								reject(resp.message);
							}
						})
						.error(function () {
							reject('更新车辆信息失败');
						});
				});
			};

			// 更新车辆信息需要的表单数据
			function getCarInfo(order) {
				var formData = {};

				order.vehicleLicense = order.vehicleLicense || {};

				formData['cityId'] = order.userCarDto.cityId;
				formData['car.carId'] = order.userCarDto.carId;
				formData['car.carNum'] = order.userCarDto.carNum
				formData['car.carBrand'] = order.userCarDto.carBrand;
				formData['car.carSeries'] = order.userCarDto.carSeries;
				formData['car.carModel'] = order.userCarDto.carModel;
				formData['license.id'] = order.userCarDto.vehicleLicense.id;
				formData['license.buyPrice'] = order.userCarDto.vehicleLicense.buyPrice * 100;
				formData['license.address'] = order.userCarDto.vehicleLicense.address;
				formData['license.quotaWeight'] = order.userCarDto.vehicleLicense.quotaWeight;
				formData['license.model'] = order.userCarDto.vehicleLicense.model;
				formData['license.vehicleSeats'] = order.userCarDto.vehicleLicense.vehicleSeats;
				formData['license.useCharacterKey'] = order.userCarDto.vehicleLicense.useCharacterKey;
				formData['license.emission'] = order.userCarDto.vehicleLicense.emission;
				formData['license.vin'] = order.userCarDto.vehicleLicense.vin;
				formData['license.engineNo'] = order.userCarDto.vehicleLicense.engineNo;
				formData['license.power'] = order.userCarDto.vehicleLicense.power;
				formData['license.registerDate'] = order.userCarDto.vehicleLicense.registerDate;
				formData['license.owner'] = order.userCarDto.vehicleLicense.owner;
				formData['license.idcardNo'] = order.userCarDto.vehicleLicense.idcardNo;

				// formData['carUserCarExtId'] = order.userCarDto.carUserCarExtId;

				return formData;
			};

			//获取当前订单下用户车辆配置信息
			Order.prototype.getPagelistData = function(orderId) {
				var self = this;
				return $q(function(resolve,reject){
					//http($http).post(angular.path+'/companycity/getCarAttachConfig')
					http($http).post(angular.path+'/order/audit/getAttachConfigByOrderId?orderId='+ orderId)
						.success(function(resp){
							resolve(resp);
						})
						.error(function(){
							reject();
						})
				})

			};


			// 客服更新交强险和车船险的方法
			Order.prototype.serviceUpdatejqcc = function(order) {
				var self = this;
				return $q(function (resolve, reject) {
					http($http).post(angular.path + '/order/audit/update/do/mandatory', getServiceJqCcInfo(order))
						.success(function (resp) {
							if (resp.success) {
								self.data = order;
								resolve(resp.data);
							}
							else {
								reject(resp.message);
							}
						})
						.error(function (resp) {
							reject(resp.message || '修改车船税加交强险出错');
						});
				});
			};

			// 更新车船税和交强险的帮助方法，用于获取表单数据格式
			function getServiceJqCcInfo(order) {
				var formData = {};

				formData['mandatoryEffectDate'] = order.mandatoryEffectDate;
				formData['policyId'] = order.policyId;

				return formData;
			};

			// 客服更新商业险数据：目前只有生效日期
			Order.prototype.serviceUpdateCommercial= function(data) {
				var self = this;
				data.policyId = self.data.policyId;
				data.orderId = self.data.id;
				return $q(function (resolve, reject) {
					http($http).post(angular.path + '/order/audit/update/do/commercial', data)
						.success(function (resp) {
							if (resp.success) {
								self.data.commercialEffectDate = data.commercialEffectDate;
								resolve(resp.data);
							} else {
								reject(resp.message);
							}
						})
						.error(function (resp) {
							reject(resp.message);
						});
				});
			};

			// 增加商业险条目的接口方法
			Order.prototype.addCommercial = function(comm) {
				var self = this;
				return $q(function (resolve, reject) {
					http($http).post(angular.path + '/order/audit/update/do/commercial/additem', comm)
						.success(function (resp) {
							if (resp.success) {
								comm.policyItemId = resp.data.id;
								comm.trialAmount = resp.data.trialAmount;
								comm.verifyAmount = resp.data.verifyAmount;
								self.data.policyItems.push(comm);
								resolve(resp.data.trailAmountTotal);
							}
							else {
								reject(resp.message);
							}
						})
						.error(function () {
							reject('添加险种操作失败');
						});
				});
			};

			// 修改商业险条目默认值
			Order.prototype.editCommercialItem = function(data) {
				var self = this;
				return $q(function (resolve, reject) {
					http($http).post(angular.path + '/order/audit/update/do/commercial/updateitem', data)
						.success(function (resp) {
							if (resp.success) {
								resolve(resp.data);
							} else {
								reject(resp.message);
							}
						})
						.error(function (resp) {
							reject(resp.message);
						});
				});
			};

			// 更新商业险条目数据
			Order.prototype.updateComm = function(data) {
				var self = this;
				return $q(function (resolve, reject) {
					http($http).post(angular.path + self.api.updateCommercial, getUpdateCommData(self.data, data))
						.success(function (resp) {
							if (resp.success) {
								angular.forEach(self.data.policyItems, function (p, index) {
									if (p.policyItemId == data.policyItemId) {
										self.data.policyItems[index] = data;
									}
								})
								resolve();
							}
							else {
								reject(resp.message);
							}
						})
						.error(function () {
							reject('修改险种操作失败');
						});
				});
			};

			// 更新商业险条目数据时构造表单数据
			function getUpdateCommData(order, data) {
				var formData = angular.copy(data);

				formData.id = data.policyItemId;
				formData.policyId = order.policyId;
				formData.orderId = order.id;
				if (data.selectValue) {
					formData.condSelectValue = data.selectValue.selectValue;
					formData.condSelectName = data.selectValue.selectName;
				}
				delete formData.selectValue;

				return formData;
			};

			// 删除商业险条目的接口方法
			Order.prototype.deleteCommercialItem = function(cid, tid) {
				var self = this;
				return $q(function (resolve, reject) {
					http($http).post(angular.path + '/order/audit/update/do/commercial/deleteitem', {id: cid, insTypeId: tid, policyId: self.data.policyId})
						.success(function (resp) {
							if (resp.success) {
								angular.forEach(self.data.policyItems, function (value, index) {
									if (value.policyItemId == cid) {
										self.data.policyItems.splice(index, 1);
									}
								});
								resolve(resp.data.trailAmountTotal);
							}
							else {
								reject(resp.message);
							}
						})
						.error(function () {
							reject('删除失败');
						});
				});
			};

			// 客服核保通过
			Order.prototype.serviceSubmit = function() {
				var self = this;
				return $q(function (resolve, reject) {
					http($http).post(angular.path + '/order/audit/update/do', {orderId: self.id})
						.success(function (resp) {
							if (resp.success) {
								resolve();
							} else {
								reject(resp.message);
							}
						})
						.error(function () {
							reject('操作失败，请稍后再试');
						});
				})
			};

			// 核保人员更新交强险和车船险的方法
			Order.prototype.updatejqcc = function(order) {
				var self = this;
				return $q(function (resolve, reject) {
					http($http).post(angular.path + '/order/verify/update/do/mandatory', getJqCcInfo(order))
						.success(function (resp) {
							if (resp.success) {
								self.data = order;
								self.data.mandatoryVerifyAmount = resp.data.mandatoryVerifyAmount;
								self.data.mandatoryBenefitPrice = resp.data.mandatoryBenefitPrice;
								self.data.mandatoryVerifyBeniftAmount = resp.data.mandatoryVerifyBeniftAmount;
								self.data.vehicleTaxNeedTotal = resp.data.vehicletaxVerifyAmount;
								resolve();
							}
							else {
								reject(resp.message);
							}
						})
						.error(function () {
							reject('修改车船税加交强险出错');		
						});
				});
			};

			// 更新车船税和交强险的帮助方法，用于获取表单数据格式
			function getJqCcInfo(order) {
				var formData = {};

				formData['policyId'] = order.policyId;
				formData['insCompanyCityId'] = order.companyCityId;
				// formData['mandatoryEffectDate'] = order.mandatoryEffectDate;
				formData['mandatoryAmountTotal'] = order.mandatoryAmountTotal;
				// formData['car.carNum'] = order.userCarDto.carNum;
				formData['vehicletaxNeedPayment'] = order.vehicleTaxNeedPayment;
				formData['vehicletaxFillupPayment'] = order.vehicleTaxFillupPayment;
				formData['vehicletaxLateFee'] = order.vehicleTaxLateFee;

				return formData;
			};

			// 核保人提交核保结果接口
			Order.prototype.underwriterSubmit = function(data) {
				var self = this;
				data.orderId = self.data.id;
				return $q(function (resolve, reject) {
					http($http).post(angular.path + '/order/verify/update/do', data)
						.success(function (resp) {
							if (resp.success) {
								resolve();
							} else {
								reject(resp.message);
							}
						})
						.error(function () {
							reject('提交保单出错');
						});
				});
			};

			// 录入交易单号
			Order.prototype.inputDealNum = function(num) {
				var self = this;
				return $q(function (resolve, reject) {
					http($http).post(angular.path + '/order/paid/tradeno/do', {
						tradeNo: num,
						id: self.data.policyId,
						orderId: self.data.id
					})
						.success(function (resp) {
							if (resp.success) {
								resolve();
							} else {
								reject(resp.message);
							}
						})
						.error(function (resp) {
							reject(resp.message || '录入保单错误');
						});
				});
			};

			// 录入保单号
			Order.prototype.inputPolicyNum = function(mnum, cnum) {
				var self = this;
				return $q(function (resolve, reject) {
					http($http).post(angular.path + '/order/produceinsurance/policyno/do', {
						id: self.data.policyId,
						orderId: self.data.id,
						mandatoryPolicyNo: mnum,
						commercialPolicyNo: cnum
					})
						.success(function (resp) {
							if (resp.success) {
								resolve();
							} else {
								reject(resp.message);
							}
						})
						.error(function (resp) {
							reject(resp.message);
						});
				});
			};

			// 录入快递单号
			Order.prototype.saveExpressNum = function(company, exnum, userCarId, mandatoryEffectDate, commercialEffectDate) {
				var self = this;
				return $q(function (resolve, reject) {
					http($http).post(angular.path + '/order/delivery/expressno/do', {
						orderId: self.data.id,
						expressNo: exnum,
						expressCompanyId: company,
						userCarId: userCarId,
						mandatoryEffectDate: mandatoryEffectDate,
						commercialEffectDate: commercialEffectDate
					})
						.success(function (resp) {
							if (resp.success) {
								resolve();
							} else {
								reject(resp.message);
							}
						})
						.error(function (resp) {
							reject(resp.message);
						});
				});
			};

			// 获取试算金额
			Order.prototype.getCalcAmount = function(insTypeId, condSelectValue, policyId, type) {
				var self = this;
				return $q(function (resolve, reject) {
					http($http).post(angular.path + '/order/audit/update/do/commercial/item/gettrialamount', {
						insTypeId: insTypeId,
						condSelectValue: condSelectValue,
						orderId: self.data.id,
						policyId: policyId,
						operateType: type
					})
						.success(function (resp) {
							if (resp.success) {
								resolve(resp.data.trialAmount);
							} else {
								reject(resp.message);
							}
						})
						.error(function (resp) {
							reject(resp.message);
						});
				});
			};

			// 批量修改商业险核保金额的接口
			Order.prototype.updateVerifyAmount = function(order) {
				var self = this;
				return $q(function (resolve, reject) {
					http($http).post(angular.path + '/order/verify/update/do/commercial/updateitems', getVerifyAmountData(order))
						.success(function (resp) {
							if (resp.success) {
								self.data = order;
								self.data.commercialItemVerifyTotalAmount = resp.data.commercialItemVerifyTotalAmount;
								self.data.commercialVerifyBeniftPrice = resp.data.commercialVerifyAmount;
								self.data.commercialVerifyAmount = resp.data.commercialVerifyAmount;
								self.refreshGift();
								resolve();
							} else {
								reject(resp.message);
							}
						})
						.error(function (resp) {
							reject(resp.message);
						});
				});
			};

			Order.prototype.refreshGift = function () {
				var self = this;
				http($http).post(angular.path + '/order/verify/update/do/promotiongift/refresh/get', {
					orderId: self.id
				})
					.success(function (resp) {
						if (resp.success) {
							angular.extend(self.data, resp.data);
						} else {
							alert(resp.message || '更新赠礼信息失败');
						}
					})
					.error(function (resp) {
						alert(resp.message || '更新赠礼信息失败');
					});
			};

			// 批量修改商业险核保金额时编码数据的帮助方法
			function getVerifyAmountData(order) {
				var formData = {};

				formData.orderId = order.id;
				formData.policyId = order.policyId;

				angular.forEach(order.policyItems, function (p, i) {
					formData['items[' + i + '].id'] = p.policyItemId;
					formData['items[' + i + '].verifyAmount'] = p.verifyAmount;
				});

				return formData;

			};

			// 退款审核时获取相关数据
			Order.prototype.getRefundInfo = function() {
				var self = this;
				return $q(function (resolve, reject) {
					http($http).post(angular.path + '/order/refund/verify/getdata', {refundOrderId: self.data.refundOrderId})
						.success(function (resp) {
							if (resp.success) {
								resolve(resp.data);
							} else {
								reject(resp.message);
							}
						})
						.error(function (resp) {
							reject(resp.message);
						});
				});
			};

			// 提交退款审核
			Order.prototype.submitAuditRefund = function(data) {
				var self = this;
				data.refundOrderId = self.data.refundOrderId;
				return $q(function (resolve, reject) {
					http($http).post(angular.path + '/order/refund/verify/do', data)
						.success(function (resp) {
							if (resp.success) {
								alert('审核成功');
								window.location.reload();
							} else {
								reject(resp.message);	
							}
						})
						.error(function (resp) {
							reject(resp.message);
						});
				});
			};

			// 退款
			Order.prototype.refund = function() {
				var self = this;
				return $q(function (resolve, reject) {
					http($http).post(angular.path + '/order/refund/confirm/do', {refundOrderId: self.data.refundOrderId})
						.success(function (resp) {
							if (resp.success) {
								resolve(resp);
							} else {
								reject(resp.message);
							}
						})
						.error(function (resp) {
							reject(resp.message);
						});
				});
			};

			// 添加交强险
			Order.prototype.addMand = function() {
				var self = this;
				return $q(function (resolve, reject) {
					http($http).post(angular.path + '/order/audit/update/do/mandatory/add', {orderId: self.data.id})
						.success(function (resp) {
							if (resp.success) {
								angular.extend(self.data, resp.data);
								resolve();
							} else {
								reject(resp.message);
							}
						})
						.error(function (resp) {
							reject(resp.message);
						});
				})
			};

			// 删除交强险
			Order.prototype.deleteMand = function() {
				var self = this;
				return $q(function (resolve, reject) {
					http($http).post(angular.path + '/order/audit/update/do/mandatory/delete', {orderId: self.data.id})
						.success(function (resp) {
							if (resp.success) {
								resolve();
							} else {
								reject(resp.message);
							}
						})
						.error(function (resp) {
							reject(resp.message);
						});
				});
			};

			// 初始化商业险
			Order.prototype.initCommercial = function() {
				var self = this;
				return $q(function (resolve, reject) {
					http($http).post(angular.path + '/order/audit/update/do/commercial/add', {orderId: self.data.id, policyId: self.data.policyId})
						.success(function (resp) {
							if (resp.success) {
								angular.extend(self.data, resp.data);
								self.data.insType = 0;
								resolve();
							} else {
								reject(resp.message);
							}
						})
						.error(function (resp) {
							reject(resp.message);
						});
				});
			};

			// 修改承保条件
			Order.prototype.editCondition = function(data) {
				var self = this;
				data.orderId = this.data.id;
				return $q(function (resolve, reject) {
					http($http).post(angular.path + '/order/audit/update/do/conditions', getConditionData(data))
						.success(function (resp) {
							if (resp.success) {
								// angular.extend(self.data, data);
								self.data.specifyCity = resp.data.specifyCity;
								self.data.specifyDrivers = resp.data.specifyDrivers;
								resolve();
							} else {
								reject(resp.message);
							}
						})
						.error(function (resp) {
							reject(resp.message);
						});
				});
			};

			// 获取修改承保条件的数据
			function getConditionData(order) {
				var data = {};
				data.orderId = order.id;
				data.specifyCity = order.specifyCity;
				angular.forEach(order.specifyDrivers, function (d, i) {
					data['specifyDrivers[' + i + '].id'] = d.id;
					data['specifyDrivers[' + i + '].name'] = d.name;
					data['specifyDrivers[' + i + '].idcardNo'] = d.idcardNo;
					data['specifyDrivers[' + i + '].drivingClass'] = d.drivingClass;
					data['specifyDrivers[' + i + '].firstissuedate'] = typeof d.firstissuedate == 'string' ? d.firstissuedate :
						d.firstissuedate.getFullYear() + '-' + (d.firstissuedate.getMonth() + 1) + '-' + d.firstissuedate.getDate() ;
				});
				return data;
			}

			// 删除指定驾驶员
			Order.prototype.removeDriver = function(id) {
				var self = this;
				return $q(function (resolve, reject) {
					http($http).post(angular.path + '/order/audit/update/do/conditions/specifydriver/delete', {id: id})
						.success(function (resp) {
							if (resp.success) {
								resolve();
							} else {
								reject(resp.message);
							}
						})
						.error(function (resp) {
							reject(resp.message);
						});
				});
			};

			// 订单撤回
			Order.prototype.rollBack = function () {
				var self = this;
				return $q(function (resolve, reject) {
					http($http).post(angular.path + '/order/unpaid/afreshVerify/do', {orderId: self.data.id})
						.success(function (resp) {
							if (resp.success) {
								resolve();
							} else {
								reject(resp.message);
							}
						})
						.error(function (resp) {
							reject(resp.message);
						});
				});
			};

			// 订单过期
			Order.prototype.expire = function () {
				var self = this;
				return $q(function (resolve, reject) {
					http($http).post(angular.path + '/order/verify/expireOrder/do', {orderId: self.data.id})
						.success(function (resp) {
							if (resp.success) {
								resolve();
							} else {
								reject(resp.message);
							}
						})
						.error(function (resp) {
							reject(resp.message);
						});
				});
			};

			// 订单失效
			Order.prototype.invalid = function () {
				var self = this;
				return $q(function (resolve, reject) {
					http($http).post(angular.path + '/order/verify/invalidOrder/do', {orderId: self.data.id})
						.success(function (resp) {
							if (resp.success) {
								resolve();
							} else {
								reject(resp.message);
							}
						})
						.error(function (resp) {
							reject(resp.message);
						});
				});
			};

			// 获取被保人列表
			Order.prototype.getInsuredList = function () {
				var self = this;
				self.insuredList = [];
				return $q(function (resolve, reject) {
					$http.get(angular.path + '/order/audit/update/get/alluserinfo?userId=' + self.data.userId)
						.success(function (resp) {
							if (resp.success) {
								self.insuredList = resp.data;
								resolve();
							} else {
								self.insuredList = null;
								reject();
							}
						})
						.error(function () {
							self.insuredList = null;
							reject();
						});
				});
			};

			// 加油卡折现
			Order.prototype.gasCardDiscount = function(flag) {
				var self = this;
				http($http).post(angular.path + '/order/verify/update/do/gascard/discount/do', {
					id: self.data.gasCard.id,
					isDiscount: flag
				})
					.success(function (resp) {

						if (resp.success) {
							self.data.gasCard.isDiscount = flag;
						} else {
							alert(resp.message);
						}

					})
					.error(function (resp) {
						alert(resp.message);
					});
			};

			Order.prototype.cancel = function () {
				var self = this;
				return $q(function (resolve, reject) {
					http($http).post(angular.path + '/order/common/cancelOrder/do', {orderId: self.data.id})
						.success(function (resp) {
							if (resp.success) {
								resolve();
							} else {
								reject(resp.message);
							}
						})
						.error(function (resp) {
							reject(resp.message);
						});
				});
			};

			Order.prototype.underPay = function(data) {
				var self = this;
				return $q(function (resolve, reject) {
					http($http).post(angular.path + '/order/unpaid/topay/do', getUnderPayData(data))
						.success(function (resp) {
							if (resp.success) {
								resolve();
							} else {
								reject(resp.message);
							}
						})
						.error(function (resp) {
							reject(resp.message);
						});
				});
			};

			function getUnderPayData(data) {
				var d = angular.copy(data);
				delete d.payBankCard;
				if (data.payBankCard) {
					angular.forEach(data.payBankCard, function (value, key) {
						d['payBankCard.' + key] = value;
					});
				}
				return d;
			}

			Order.prototype.applyReFund = function() {
				var self = this;
				return $q(function (resolve, reject) {
					http($http).post(angular.path + '/order/common/refundApply/do', {orderId: self.data.id})
						.success(function (resp) {
							if (resp.success) {
								resolve(resp.data);
							} else {
								reject(resp.message);
							}
						})
						.error(function (resp) {
							reject(resp.message);
						});
				});
			};

			Order.prototype.applyReFundPolicy = function() {
				var self = this;
				return $q(function (resolve, reject) {
					http($http).post(angular.path + '/order/common/refundPolicyApply/do', {orderId: self.data.id})
						.success(function (resp) {
							if (resp.success) {
								resolve(resp.data);
							} else {
								reject(resp.message);
							}
						})
						.error(function (resp) {
							reject(resp.message);
						});
				});
			};

			this.Order = Order;

		}
	]);

});