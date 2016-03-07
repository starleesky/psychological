define(function (require) {
	
	var http = require('bower_components/angularext/angularExt').http;

	angular.module('App').
	service('CompanyDetail', ['$http', '$q', '$rootScope', function ($http, $q, $rootScope) {

		function CompanyDetail(id) {
			this.id = id;
			this.data = null;
			this.deleting = false;
		}

			//companycity/getAttachConfigByCompany?companyCityId=113
		CompanyDetail.prototype.getData = function() {
			var self = this;
			$http.get(angular.path + '/companycity/detail/getcompany?id=' + this.id)
				.success(function (resp) {

					var data = self.data = resp;

					angular.forEach(self.data.gifts, function (gift) {
						gift.editing = false;
					});

				})
				.error(function () {

				});
		};

			CompanyDetail.prototype.getPagelistData = function(companyId) {
				var self = this;
				return $q(function(resolve,reject){
					//http($http).post(angular.path+'/companycity/getCarAttachConfig')
					http($http).post(angular.path+'/companycity/getAttachConfigByCompany?companyCityId='+ companyId)
						.success(function(resp){
							resolve(resp);
						})
						.error(function(){
							reject();
						})
				})

			};


			CompanyDetail.prototype.saveChange = function(param){
				var self = this;
				return $q(function(resolve,reject){
					$http.post(angular.path+'/companycity/setPicConfig',param)
						.success(function(resp){
							resolve(resp);
						})
						.error(function(){
							reject();
						})
				})
			}
		CompanyDetail.prototype.pushInfo = function(data) {
			var self = this;
			data = getData(data);
			data.companyCityId = this.id;

			return $q(function (resolve, reject) {
				http($http).post(angular.path + '/companycity/update/do', data)
					.success(function (resp) {

						angular.forEach(data, function (value, key) {
							self.data[key] = value;
						});

						resolve();

					})
					.error(function () {
						reject();
					});
			});
			
		};

		CompanyDetail.prototype.addGift = function(gift) {
			var self = this;
			var postData = angular.copy(gift);
			postData.companyCityId = self.id;

			return $q(function (resolve, reject) {
				http($http).post(angular.path + '/companycity/update/do/gifts/add', postData)
					.success(function (resp) {
						// gift.id = resp.data.id;
						self.data.gifts.push(resp.data);
						resolve();
					})
					.error(function () {
						reject('操作失败，请稍后再试');
					});
			});

		};

		CompanyDetail.prototype.updateGift = function(gift) {
			var self = this;
			var postData = angular.copy(gift);
			postData.companyCityId = self.id;

			return $q(function (resolve, reject) {
				http($http).post(angular.path + '/companycity/update/do/gifts/update', postData)
					.success(function (resp) {
						resolve();
					})
					.error(function () {
						reject('操作失败，请稍后再试');
					});
			});

		};

		CompanyDetail.prototype.removeGift = function(index) {
			var self = this;
			var gift = self.data.gifts[index];

			self.deleting = true;

			return $q(function (resolve, reject) {
				http($http).post(angular.path + '/companycity/update/do/gifts/delete', {id: gift.id})
					.success(function (resp) {
						self.data.gifts.splice(index, 1);
						self.deleting = false;
					})
					.error(function () {
						reject();
						self.deleting = false;
					});
			});

		};

		CompanyDetail.prototype.saveSettle = function(param) {
			var self = this;
			return $q(function(resolve,reject) {
				$http.post(angular.path + '/companycity/saveSettle/do', param)
					.success(function(resp) {
						resolve(resp);
					})
					.error(function() {
						reject();
					})
			});
		}

		// help function, create the post data from all data
		function getData(data) {
			var d = {};
			var postDatas = [
				'contactName',
				'phoneNum',
				'mandatoryRebate',
				'commercialRebate',
				'commissionRate',
				'saleDiscount',
				'giftFlag',
				'address',
				'isValid',
				'ecommerceRate',
				'commercialBuyInterval',
				'discountFlag',
				'giftCouponRebate',
				'giftCardRebate',
				'idcardImgFlag',
				'trialSaleDiscount',
				'contactPhone',
				'orderLevel',
				'activityName',
				'activityUrl',
				'channel'
			];
			angular.forEach(postDatas, function (value) {
				d[value] = data[value];
			});

			return d;
		}


		return CompanyDetail;

	}]);

});