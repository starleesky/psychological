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

    <title>SB Admin - Bootstrap Admin Template</title>

    <!-- Bootstrap Core CSS -->
    <link href="${ctx}/resources/admin/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${ctx}/resources/admin/css/sb-admin.css" rel="stylesheet">

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

</head>

<body>


<div id="pace-done" class="col-sm-6 col-sm-offset-3 form-box" >

    <div data-ng-controller="userLoginCtrl" class="middle-box text-center loginscreen  animated fadeInDown ng-scope">
        <div >
            <div>
                <h1 class="logo-name">test<%--<img src="http://res.xiaokakeji.com/ups/img/ic_launcher.png">--%></h1>
            </div>
            <form class="m-t ng-invalid ng-invalid-required ng-dirty ng-valid-parse"
                  data-ng-submit="ctrl.loginSubmit()">
                <div class="form-group fa-align-center" >
                    <input class="form-control ng-touched ng-dirty ng-valid-parse ng-invalid ng-invalid-required"
                           type="text" data-ng-model="ctrl.formData.name" autofocus="autofocus" placeholder="用户名"
                           required="">
                </div>
                <div class="form-group">
                    <input type="password" data-ng-model="ctrl.formData.password" focus-me="ctrl.cookieName.length > 0"
                           class="form-control ng-pristine ng-invalid ng-invalid-required ng-touched" placeholder="密码"
                           required="">
                </div>
                <button type="submit" class="btn btn-primary block full-width m-b">登录
                </button>
                <code id="loginError"></code>
            </form>
            <p class="m-t">
                <small>汤森机械 © 2016</small>
            </p>
        </div>
    </div>

</div>
<!-- /#page-wrapper -->

<!-- /#wrapper -->

<!-- jQuery -->
<script src="${ctx}/resources/admin/js/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="${ctx}/resources/admin/js/bootstrap.min.js"></script>

<!-- Morris Charts JavaScript -->
<script src="${ctx}/resources/admin/js/plugins/morris/raphael.min.js"></script>
<script src="${ctx}/resources/admin/js/plugins/morris/morris.min.js"></script>
<script src="${ctx}/resources/admin/js/plugins/morris/morris-data.js"></script>

</body>

</html>
