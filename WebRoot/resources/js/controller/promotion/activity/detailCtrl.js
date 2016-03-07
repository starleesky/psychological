define(function (require) {

	require('js/service/promotion/activity');
	require('js/controller/promotion/activity/activity-config');
	
	angular.module('App')

	.controller('mainCtrl', ['$scope', 'activity', '$modal',
		function($scope, activity, $modal) {

			// 初始化数据
			var act;

			activity.get(angular.activityId)
				.then(function (data) {
					act = $scope.act = data.data;
				}, function (msg) {
					alert(msg);
				});
			// 初始化数据结束

			// 修改活动
			$scope.editActivity = function (activity) {
				var modal = $modal.open({
					templateUrl: angular.path + '/resources/templates/promotion/editActivity-modal.html',
					controller: 'editActivityCtrl',
					backdrop: 'static',
					size: 'lg',
					resolve: {
						editData: function () {
							return act;
						}
					}
				});
			};


		}
	])

	.controller('editActivityCtrl', ['$scope', '$modalInstance', 'editData', 'activity', '$filter',
		function ($scope, $modalInstance, editData, activity, $filter){

			$scope.prioritys = (function () {
				var arr = [];
				for (var i=0; i<10; i++) {
					arr.push(i);
				}
				return arr;
			})();

			var data = $scope.data = angular.copy(editData);

			// 不同活动的配置修改
			$scope.templates = [
				angular.path + '/resources/templates/promotion/activities/wash.html',
				angular.path + '/resources/templates/promotion/activities/maintenance.html',
				''
			];
			$scope.loadTmpl = function () {
				$scope.tmplLoadded = false;
			};
			$scope.loaded = function () {
				$scope.tmplLoadded = true;
			};

			$scope.clearEndTime = function () {
				$scope.endTime = '';
			};

			// 时间选择设置
			$scope.beginOpened = false;
			$scope.endOpened = false;
			$scope.beginTime = data.promotionActivity.beginTime;
			$scope.endTime = data.promotionActivity.endTime;

			$scope.toggleCal = function (type, value) {
				$scope[type + 'Opened'] = value;
			};
			// 时间选择结束

			// 把date类型转换成时间format
			function fixDate() {
				data.promotionActivity.beginTime = $filter('date')($scope.beginTime, 'yyyy-MM-dd');
				data.promotionActivity.endTime = $filter('date')($scope.endTime, 'yyyy-MM-dd');
			}

			$scope.submitted = false;
			$scope.submitting = false;
			$scope.edit = function () {
				$scope.submitted = true;
				if ($scope.edit_form.$invalid) {
					return;
				}
				fixDate();
				$scope.submitting = true;
				activity.edit(data)
					.then(function () {
						angular.extend(editData, data);
						$modalInstance.dismiss();
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