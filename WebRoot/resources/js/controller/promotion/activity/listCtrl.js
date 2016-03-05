define(function (require) {
	
	require('js/service/commonList');
	require('js/service/promotion/activity');
  require('js/controller/promotion/activity/activity-config');

	angular.module('App')

	.controller('mainCtrl', ['$scope', 'commonList', '$modal',
		function ($scope, commonList, $modal) {

			var list = $scope.list = commonList;
			list.url = "/promotion/activity/list/getdata";
			
			list.fetch();

			// 根据不同的类型进行关键字搜索
			var searchTypes = ['activityName', 'activityCode'];
			var searchTypeTexts = $scope.searchTypeTexts = ['活动名搜索', 'code搜索'];
			$scope.type = 1;
			$scope.searchText = '';
			$scope.searchType = function (type) {
				$scope.type = type;
				$scope.search();
			};
			$scope.search = function () {
				angular.forEach(searchTypes, function (t) {
					list.filter[t] = '';
				});
				list.filter[searchTypes[$scope.type]] = $scope.searchText;
				list.search();
			};
			// 根据不同的类型进行关键字搜索结束

			// 创建新的活动
			$scope.addActivity = function () {
				var modal = $modal.open({
					templateUrl: angular.path + '/resources/templates/promotion/addActivity-modal.html',
					controller: 'addActivityCtrl',
					backdrop: 'static',
          size: 'lg'
				});
				modal.result.then(function (activity) {
					list.data.data.unshift(activity.promotionActivity);
				})
			};

		}
	])

	.controller('addActivityCtrl', ['$scope', '$modalInstance', 'activity', '$filter',
		function ($scope, $modalInstance, activity, $filter) {

			$scope.prioritys = (function () {
				var arr = [];
				for (var i=0; i<10; i++) {
					arr.push(i);
				}
				return arr;
			})();

			var data = $scope.data = {
        promotionActivity: {
          name: '',
          description: '',
          type: 0,
          subType: 0,
          beginTime: '',
          endTime: '',
          source: 1,
          sourceDesc: '',
          priority: 9,
          isValid: 0,
          isValidDesc: ''
        }
			};

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

			// 时间选择设置
			$scope.beginOpened = false;
			$scope.endOpened = false;
			$scope.beginTime = '';
			$scope.endTime = '';

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
			$scope.add = function () {
				$scope.submitted = true;
				if ($scope.add_form.$invalid) {
					return;
				}
				fixDate();
				$scope.submitting = true;
				activity.add(data)
					.then(function (data) {
						$modalInstance.close(data);
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



});