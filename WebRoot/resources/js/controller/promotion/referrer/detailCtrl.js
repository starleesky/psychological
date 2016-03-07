define(function (require) {

	require('js/service/promotion/referrer');
	require('js/filters/filters');
	
	angular.module('App')

	.controller('mainCtrl', ['$scope', 'referrer',	'$modal',
		function ($scope, referrer, $modal) {

			// 初始化数据
			$scope.referrerDetail = {};
			// $scope.records = {data: {}};
			referrer.getData(angular.referrerId)
				.then(function (data) {
					$scope.referrerDetail = data;
				}, function (msg) {
					alert(msg);
				});
			// 初始化数据结束

			// 修改推荐人信息
			$scope.editReferrer = function () {
				$modal.open({
					templateUrl: angular.path + '/resources/templates/promotion/editReferrer-modal.html',
					controller: 'editReferrerCtrl',
					backdrop: 'static',
					size: 'lg',
					resolve: {
						detail: function () {
							return $scope.referrerDetail;
						}
					}
				});
			};
			// 修改推荐人信息结束

			// 分页显示取现记录
			// var pagination = $scope.pagination = {
			// 	page: 1,
			// 	pageSize: 20
			// };

			// $scope.changePage = function () {
			// 	referrer.getRecords(angular.referrerId, pagination)
			// 		.then(function (data) {
			// 			$scope.records = data;
			// 		}, function (msg) {
			// 			alert(msg);
			// 		});
			// };

			// $scope.changePage();
			// 分页显示取现记录结束
			$scope.openFunc = function(act){
				var param={
					referrerId:angular.referrerId,
					activate:1
				}
				referrer.saveOpenFunc(param)
					.then(function(data){
						//$scope.msg = data;
						alert(data);
						$scope.referrerDetail.activate =1;
					},function(msg){
						alert(msg);
					})

			}
		}
	])
	
	.controller('editReferrerCtrl', ['$scope', '$modalInstance', 'detail', 'referrer',
		function ($scope, $modalInstance, detail, referrer) {
			// 初始化
			$scope.detail = angular.copy(detail);
			// 初始化结束

			var idiomFilter = $scope.idiomFilter = {
				page: 1,
				pageSize: 30,
				idiomName: ''
			};
			$scope.pager = {
				totalSize: 0
			};
			var selectedIdiom = $scope.selectedIdiom = {idiomName: detail.idiom};
			$scope.searching = false;
			$scope.getIdioms = function () {
				$scope.searching = true;
				referrer.getIdioms(idiomFilter)
					.then(function (idioms) {
						$scope.idioms = idioms.data;
						$scope.pager.totalSize = idioms.totalSize;
						$scope.searching = false;
					}, function (msg) {
						alert(msg);
						$scope.searching = false;
					});
			};
			var searchTimer;
			$scope.dealySearch = function () {
				if (searchTimer) {
					clearTimeout(searchTimer);
				}
				if (selectedIdiom.idiomName === '') {
					return;
				}
				searchTimer = setTimeout(function () {
					$scope.$apply(function () {
						$scope.getIdioms();
					})
				}, 500);
			};

			$scope.chooseIdiom = function (idiom) {
				$scope.selectedIdiom = idiom;
				$scope.detail.idiom = idiom.idiomName;
			};
			$scope.disSelect = function () {
				$scope.selectedIdiom = {};
				$scope.detail.idiom = '';
			};

			$scope.submitted = false;
			$scope.submitting = false;
			$scope.edit = function () {
				$scope.submitted = true;
				if ($scope.add_form.$invalid) {
					return;
				}
				$scope.submitting = true;
				referrer.update({
					commissionRate: $scope.detail.commissionRate,
					referrerId: $scope.detail.referrerId,
					idiomName: $scope.detail.idiom
				})
					.then(function () {
						detail.commissionRate = $scope.detail.commissionRate;
						detail.idiom = $scope.detail.idiom;
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
	]);

});