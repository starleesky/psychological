define(function (require) {

    angular.module('App')
        .directive('citysList', ['$parse', '$timeout',
            function ($parse, $timeout) {
                return {
                    scope: {
                        changeFun: '&change',
                        selects: '=',
                        model: '=filterModel'
                    },
                    replace: true,
                    templateUrl: angular.path + '/resources/templates/utils/citys-list.html',
                    link: function ($scope, element, attrs) {

                        $scope.keyName = attrs.filterValue;
                        $scope.valueName = attrs.filterName;

                        // 搜索方法
                        $scope.searchText = '';
                        $scope.search = function () {
                            angular.forEach($scope.selects, function (value) {
                                value._hide = (value[$scope.valueName].indexOf($scope.searchText) === -1);
                            });
                        };

                        $scope.$watch('selects.length', function () {
                            if ($scope.model) {
                                $scope.selectedItem = null;
                                var m = $scope.model;
                                $scope.model = '';

                                angular.forEach($scope.selects, function (s,index) {
                                    if(index ==0)
                                    {
                                        $scope.selectedItem =s;
                                        $scope.model = m
                                    }

                                    if (s[$scope.keyName] == $scope.model) {
                                        $scope.selectedItem = s;
                                        $scope.model = m;
                                    }
                                });
                                $timeout(function () {
                                    $scope.changeFun();
                                }, 100);
                            }
                        });

                        // 选择方法
                        $scope.selectedItem = null;
                        $scope.select = function (item) {
                            $scope.model = item[$scope.keyName];
                            $scope.selectedItem = item;
                            $timeout(function () {
                                $scope.changeFun();
                            }, 100);
                        };

                        // toggle
                        $scope.toggle = true;
                        $scope.toggleSelect = function () {
                            $scope.toggle = !$scope.toggle;
                        };
                        $scope.removeSelect = function () {
                            $scope.selectedItem = null;
                            $scope.model = '';
                            $timeout(function () {
                                $scope.changeFun();
                            }, 100);
                        };

                    }
                };
            }
        ]);

});