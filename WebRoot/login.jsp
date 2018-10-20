<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="${pageContext['request'].contextPath}"/>
<!DOCTYPE HTML>
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>易易安陂EAP服务后台管理理系统</title>

    <!-- Bootstrap Core CSS -->
    <link href="${ctx}/resources/admin/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${ctx}/resources/admin/css/style.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="${ctx}/resources/admin/css/plugins/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="${ctx}/resources/admin/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <!--<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>-->
    <!--<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>-->
    <!--[endif]-->

    <!-- Mainly scripts -->
    <script src="${ctx}/resources/sea.js"></script>
    <script src="${ctx}/resources/bower_components/jquery/dist/jquery.min.js"></script>
    <script src="${ctx}/resources/bower_components/angular/angular.min.js"></script>
    <script>
        angular.path = '${ctx}';
    </script>
</head>

<body>

<div id="pace-done">
    <div data-ng-controller="userLoginCtrl" class="middle-box text-center loginscreen  animated fadeInDown">
        <div>
            <div>
                <h2 class="logo-name">易易安陂EAP服务后台管理理系统</h2>
            </div>
            <form class="m-t" data-ng-submit="ctrl.loginSubmit()">
                <div class="form-group">
                    <input class="form-control" type="text" data-ng-model="ctrl.formData.userName"
                           autofocus="autofocus" placeholder="用户名" required>
                </div>
                <div class="form-group">
                    <input type="password" data-ng-model="ctrl.formData.password" focus-me="ctrl.cookieName.length > 0"
                           class="form-control" placeholder="密码" required>
                </div>
                <button type="submit" class="btn btn-primary block full-width m-b">登录
                </button>
                <code id="loginError"></code>
            </form>
            <p class="m-t">
                <small>Copyright@2015 湘ICP 14013012号-1 Tangsons(Hunan) Trading Co.Ltd</small>
            </p>
        </div>
    </div>
</div>
<script>
    var myApp = angular.module("myApp", []);
    myApp.directive('focusMe', function ($timeout, $parse) {
        return {
            link: function (scope, element, attrs) {
                var model = $parse(attrs.focusMe);
                scope.$watch(model, function (value) {
                    if (value === true) {
                        $timeout(function () {
                            element[0].focus();
                        });
                    }
                });
                element.bind('blur', function () {
//				scope.$apply(model.assign(scope, false));
                });
            }
        };
    });
    seajs.use('admin/js/login', function (UserLogin) {
        myApp.controller("userLoginCtrl", function ($scope, $http) {
            $scope.ctrl = new UserLogin($scope, $http, '${target}');
        });
        angular.bootstrap(document, ['myApp']);
    });
</script>
</body>

</html>
