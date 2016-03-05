define(function (require) {
	
	angular.module('App')
	.directive('imageViewer', ['$timeout',
		function ($timeout) {

			return {
				templateUrl: angular.path + '/resources/templates/utils/image-viewer.html',
				replace: true,
				scope: {
					src: '=src'
				},
				link: function ($scope, element, attrs) {

					// info about image
					var img = $scope.img = {
						ele: null,
						width: 0,
						height: 0,
						clientWidth: 0,    // 实际显示宽度（根据旋转角度替换width和height）
						clientHeight: 0,   // 实际显示高度（根据旋转角度替换width和height）
						percent: 1,
						hovered: false
					};

					// 循环父级直到找到第一个有值得结果
					function getClientWidth(ele) {
						if (ele.clientWidth) {
							return ele.clientWidth;
						} else {
							return getClientWidth(ele.parentElement);
						}
					}

					// info about container
					var container = $scope.container = {
						width: getClientWidth(element[0]),
						//width: element[0].clientWidth,
						hovered: false
					};

					// 移动了多少距离
					// 图片定位计算公式：(e代表鼠标对于图片的位置)
					// left = e% - img.clientWidth*e% + action.left
					// top = e% - img.hclientHeight*e% + action.top
					var action = $scope.action = {
						left: 0,
						top: 0,
						deg: 0
					};


					// $scope.deg = 0;
					$scope.img.ele = new Image();
					img.ele = element.find('img')[0];

					// 当图片加载成功时执行计算图片的原始尺寸
					img.ele.onload = function () {
						$scope.$apply(function () {
							$scope.loaded = true;
							$scope.error = false;
							img.width = img.ele.naturalWidth;
							img.height = img.ele.naturalHeight;
							init();
						});
					};

					img.ele.onerror = function () {
						$scope.$apply(function () {
							$scope.loaded = true;
							$scope.error = true;
						});
					};

					// 获取元素相对窗口的位置
					// 传入一般为Event对象
					// layer为鼠标在当前节点的位置
					function getLayer(e) {
						if (e.layerX && e.layerY) {
							return e;
						}
						// 设置自有实现
						e.layerX = (function (ele) {
							var x = 0;
							while (ele) {
								x += ele.offsetLeft;
								ele = ele.offsetParent;
							}
							return e.pageX - x;
						}(e.target));
						e.layerY = (function (ele) {
							var y = 0;
							while (ele) {
								y += ele.offsetTop;
								ele = ele.offsetParent;
							}
							return e.pageY - y;
						}(e.target));
						return e;
					}

					// 计算图片显示的尺寸，根据外部容器计算，结果是不管是否旋转都可以整好在容器内
					function init() {

						// 重新计算容器尺寸
						container.width = getClientWidth(element[0]);

						// 计算图片压缩百分比
						var w = img.width > img.height;
						img.percent = w ?
							(img.width < container.width ? 1 : container.width / img.width) :
							(img.height < container.width ? 1 : container.width / img.height);

						// 计算图片显示尺寸
						calcClientSize();
						initPosition();

						action.deg = 0;

					}
					$scope.init = init;

					// 计算图片位置
					function initPosition() {
						action.left = container.width / 2 - img.clientWidth / 2;
						action.top = container.width / 2 - img.clientHeight / 2;
					}

					// 计算图片显示长宽
					function calcClientSize() {
						img.clientWidth = img.width * img.percent;
						img.clientHeight = img.height * img.percent;
					}

					// 当src属性变化的时候，重新加载图片
					$scope.$watch('src', function () {
						$scope.loaded = false;
						$scope.img.ele.src = $scope.src;
					});

					/*********************  以下为操作  ***********************/

					// 旋转
					$scope.turn = function (deg) {
						if (!deg) action.deg = 0;
						else action.deg += deg;
					};

					// 屏幕重新调整尺寸的时候重新进行计算
					window.addEventListener('resize', function () {
						$scope.$apply(function () {
							init();
						});
					});

					angular.element(img.ele).on('mousewheel', function (e) {
						$scope.$apply(function () {
							e.preventDefault();
							e = getLayer(e);
							img.percent += ((e.wheelDeltaY?e.wheelDeltaY:e.originalEvent.wheelDeltaY) > 0 ? 0.1 : -0.1);
						});
					});

					angular.element(img.ele).on('DOMMouseScroll', function (e) {
						$scope.$apply(function () {
							e.preventDefault();
							e = getLayer(e);
							img.percent += (e.detail < 0 ? 0.1 : -0.1);
						});
					});

					//img.ele.addEventListener('mousewheel', function (e) {
					//	$scope.$apply(function () {
					//		e.preventDefault();
					//		e = getLayer(e);
					//		//console.log(e);
					//		img.percent += (e.wheelDeltaY > 0 ? 0.1 : -0.1);
					//	});
					//});

					$scope.$watch('img.percent', function () {
						img.clientWidth = img.width * img.percent;
						img.clientHeight = img.height * img.percent;
					});

					// 移动操作
					var down = null;
					img.ele.addEventListener('mousedown', function (e) {
						e.preventDefault();
						down = {
							x: e.pageX,
							y: e.pageY
						}
					});
					document.body.addEventListener('mouseup', function (e) {
						down = null;
					});
					element.on('mousemove', function (e) {
						e.preventDefault();
						$scope.$apply(function () {
							if (!down) {
								return;
							}
							action.left += e.pageX - down.x;
							action.top += e.pageY - down.y;
							down = {
								x: e.pageX,
								y: e.pageY
							};
						});
					});

				}
			};

		}
	])


});