define(function (require) {

  require('js/service/common/gifts');

  angular.module('App')
  .controller('washCtrl', ['$scope', 'gifts',
    function($scope, gifts) {

      $scope.data.functionActivityRule = $scope.data.functionActivityRule || {
        criticalValueFlag: 1,
        parameters: [
          {
            start: 0,
            end: '',
            values: []
          }
        ]
      };

      var rule = $scope.data.functionActivityRule;
      var list = rule.parameters;

      $scope.giftList = gifts.getAllWashcarCouponTypes();

      // 当有end值变化时检查下一个item
      // 如果下一个item的end小于上一个的end直接删除
      // 删除之后再次check，直到找到下一个end大于上一个的
      // 然后让下一个的start = 上一个的end
      function checkNext(item) {
        var index = list.indexOf(item);
        var next = list[index+1];
        if (!next) {
          return true;
        }
        else if (next.end && next.end <= item.end) {
          list.splice(index+1, 1);
          checkNext(item);
        } else {
          next.start = item.end;
        }

      }

      // 修改起始值，在blur的时候触发
      $scope.editStart = function (item) {
        if (item.start >= item.end) {
          item.end = item.start + 1;
          checkNext(item);
        }
      };

      // 修改结束值，在blur时触发
      $scope.editEnd = function (item) {
        var index = list.indexOf(item);
        if (item.end <= item.start) {
          if (!item.end && !list[index+1]) {
          }
          else if (list[index+1]) {
            alert('结束值不能小于起始值');
            item.end = item.start + 1;
            checkNext(item);
          } else {
            item.end = '';
          }
          return;
        }
        if (list[index+1]) {
          checkNext(item);
        } else {
          list.push({
            start: item.end,
            end: '',
            values: []
          })
        }
      };

      // 删除一个区间
      $scope.remove = function (e, item) {
        e.preventDefault();
        var index = list.indexOf(item);
        if (list.length < 2) {
          alert('至少要保存一个');
          return;
        }
        if (list[index+1] && list[index-1]) {
          list[index+1].start = list[index-1].end;
        } else if (list[index-1]) {
          list[index-1].end = '';
        }
        list.splice(index, 1);
      };

      // handle keydown on end
      $scope.keydown = function (e) {
        if (e.which == 13) {
          e.target.blur();
          e.preventDefault();
        }
      };

    }
  ])
    .controller('maintenanceCtrl', ['$scope', 'gifts',
      function($scope, gifts) {

        $scope.data.functionActivityRule = $scope.data.functionActivityRule || {
          criticalValueFlag: 1,
          parameters: [
            {
              start: 0,
              end: '',
              values: []
            }
          ]
        };

        var rule = $scope.data.functionActivityRule;
        var list = rule.parameters;

        $scope.giftList = gifts.getAllMaintainCouponTypes();

        // 当有end值变化时检查下一个item
        // 如果下一个item的end小于上一个的end直接删除
        // 删除之后再次check，直到找到下一个end大于上一个的
        // 然后让下一个的start = 上一个的end
        function checkNext(item) {
          var index = list.indexOf(item);
          var next = list[index+1];
          if (!next) {
            return true;
          }
          else if (next.end && next.end <= item.end) {
            list.splice(index+1, 1);
            checkNext(item);
          } else {
            next.start = item.end;
          }

        }

        // 修改起始值，在blur的时候触发
        $scope.editStart = function (item) {
          if (item.start >= item.end) {
            item.end = item.start + 1;
            checkNext(item);
          }
        };

        // 修改结束值，在blur时触发
        $scope.editEnd = function (item) {
          var index = list.indexOf(item);
          if (item.end <= item.start) {
            if (!item.end && !list[index+1]) {
            }
            else if (list[index+1]) {
              alert('结束值不能小于起始值');
              item.end = item.start + 1;
              checkNext(item);
            } else {
              item.end = '';
            }
            return;
          }
          if (list[index+1]) {
            checkNext(item);
          } else {
            list.push({
              start: item.end,
              end: '',
              values: []
            })
          }
        };

        // 删除一个区间
        $scope.remove = function (e, item) {
          e.preventDefault();
          var index = list.indexOf(item);
          if (list.length < 2) {
            alert('至少要保存一个');
            return;
          }
          if (list[index+1] && list[index-1]) {
            list[index+1].start = list[index-1].end;
          } else if (list[index-1]) {
            list[index-1].end = '';
          }
          list.splice(index, 1);
        };

        // handle keydown on end
        $scope.keydown = function (e) {
          if (e.which == 13) {
            e.target.blur();
            e.preventDefault();
          }
        };

      }
    ])
    // 多选的指令
    .directive('muiltSelect', ['$timeout',
      function($timeout) {
        return {
          templateUrl: angular.path + '/resources/templates/utils/muiltSelect.html',
          scope: {
            model: '=ngModel',
            o: '=options',
            muilt: '='
          },
          require: 'ngModel',
          link: function ($scope, element, attrs, ngModelCtrl) {
            var selected = $scope.selected = [];
            var model = $scope.model;

            $scope.placeholder = attrs.placeholder;
            $scope.options = angular.copy($scope.o);

            $scope.$watch('o', function (newVal) {
              $scope.options = angular.copy($scope.o);
              $scope.selected.splice(0, $scope.selected.length);
              checkModel();
            }, true);

            // config the ngModel validation
            ngModelCtrl.$options = {allowInvalid : true};
            ngModelCtrl.$validators.need = function (value) {
              return value instanceof Array && value.length > 0;
            };

            // 根据model数据初始化
            function checkModel() {
              if (model) {
                angular.forEach($scope.options, function (o) {
                  if (model.indexOf(o.key) > -1) {
                    o.selected = true;
                    selected.push(o);
                  }
                });
              }
            }
            checkModel();

            var input = element.find('input')[0];
            $scope.focused = false;
            $scope.focus = function () {
              input.focus();
            };
            $scope.unfocus = function () {
              $timeout(function () {
                $scope.focused = false;
              }, 300);
            };


            $scope.select = function (obj) {
              if (obj.selected) {
                return;
              }
              obj.selected = true;
              if ($scope.muilt) {
                selected.push(obj)
              } else {
                selected[0] ? selected[0].selected = false : void(0);
                selected[0] = obj;
              }
              updateModel();
            };
            $scope.unselect = function (obj) {
              obj.selected = false;
              if (!$scope.muilt) {
                selected.splice(0,1);
              } else {
                var index = selected.indexOf(obj);
                selected.splice(index, 1);
              }
              updateModel();
            };
            function updateModel() {
              if ($scope.muilt) {
                $scope.model = [];
                angular.forEach(selected, function (v) {
                  $scope.model.push(v.key);
                });
              } else {
                selected[0] ? $scope.model[0] = selected[0].key : $scope.model.splice(0, 1);
              }
              ngModelCtrl.$validate();
            }

            $scope.$on('$destroy', function () {
              angular.forEach($scope.options, function (opt) {
                delete opt.selected;
              });
            });
            
            
          }
        };
      }
    ]);

});