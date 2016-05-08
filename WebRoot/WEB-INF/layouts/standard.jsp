<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<c:set var="ctx" value="${pageContext['request'].contextPath}"/>
<!DOCTYPE HTML>
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="${ctx}/wap/images/favicon.ico" type="image/x-icon" />
    <title>汤森机械管理后台系统</title>

    <!-- Bootstrap Core CSS -->
    <link href="${ctx}/resources/admin/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/resources/bower_components/eonasdan-bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.min.css"
          rel="stylesheet"/>
    <link href="${ctx}/resources/styles/admin.css" rel="stylesheet"/>
    <link href="${ctx}/resources/styles/style.css" rel="stylesheet"/>

    <!-- Custom CSS -->
    <link href="${ctx}/resources/admin/css/sb-admin.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="${ctx}/resources/admin/css/plugins/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="${ctx}/resources/admin/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- Mainly scripts -->
    <script src="${ctx}/resources/sea.js"></script>
    <script src="${ctx}/resources/bower_components/jquery/dist/jquery.min.js"></script>
    <script src="${ctx}/resources/bower_components/angular/angular.min.js"></script>
    <script>
        angular.path = '${ctx}';
    </script>
    <script src="${ctx}/resources/bower_components/angular-animate/angular-animate.min.js"></script>
    <script src="${ctx}/resources/bower_components/AngularJS-Toaster/toaster.js"></script>
    <script src="${ctx}/resources/bower_components/angular-sanitize/angular-sanitize.min.js"></script>
    <script src="${ctx}/resources/bower_components/angular-bootstrap/ui-bootstrap-tpls.min.js"></script>
    <script src="${ctx}/resources/bower_components/zeroclipboard/dist/ZeroClipboard.min.js"></script>
    <script src="${ctx}/resources/bower_components/angular-zeroclipboard/dist/angular-zeroclipboard.min.js"></script>

    <script src="${ctx}/resources/bower_components/ng-file-upload/ng-file-upload.min.js"></script>

    <script src="${ctx}/resources/bower_components/moment/min/moment.min.js"></script>
    <script src="${ctx}/resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="${ctx}/resources/bower_components/eonasdan-bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js"></script>

    <script src="${ctx}/resources/config.js"></script>


</head>

<body>

<div id="wrapper">

    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${ctx}/admin/main">汤森机械管理后台系统</a>
        </div>
        <!-- Top Menu Items -->
        <ul class="nav navbar-right top-nav">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-envelope"></i> <b
                        class="caret"></b></a>
                <ul class="dropdown-menu message-dropdown">
                    <li class="message-preview">
                        <a href="#">
                            <div class="media">
                                    <span class="pull-left">
                                        <img class="media-object" src="http://placehold.it/50x50" alt="">
                                    </span>
                                <div class="media-body">
                                    <h5 class="media-heading"><strong>John Smith</strong>
                                    </h5>
                                    <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
                                    <p>Lorem ipsum dolor sit amet, consectetur...</p>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li class="message-preview">
                        <a href="#">
                            <div class="media">
                                    <span class="pull-left">
                                        <img class="media-object" src="http://placehold.it/50x50" alt="">
                                    </span>
                                <div class="media-body">
                                    <h5 class="media-heading"><strong>John Smith</strong>
                                    </h5>
                                    <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
                                    <p>Lorem ipsum dolor sit amet, consectetur...</p>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li class="message-preview">
                        <a href="#">
                            <div class="media">
                                    <span class="pull-left">
                                        <img class="media-object" src="http://placehold.it/50x50" alt="">
                                    </span>
                                <div class="media-body">
                                    <h5 class="media-heading"><strong>John Smith</strong>
                                    </h5>
                                    <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
                                    <p>Lorem ipsum dolor sit amet, consectetur...</p>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li class="message-footer">
                        <a href="#">Read All New Messages</a>
                    </li>
                </ul>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-bell"></i> <b
                        class="caret"></b></a>
                <ul class="dropdown-menu alert-dropdown">
                    <li>
                        <a href="#">Alert Name <span class="label label-default">Alert Badge</span></a>
                    </li>
                    <li>
                        <a href="#">Alert Name <span class="label label-primary">Alert Badge</span></a>
                    </li>
                    <li>
                        <a href="#">Alert Name <span class="label label-success">Alert Badge</span></a>
                    </li>
                    <li>
                        <a href="#">Alert Name <span class="label label-info">Alert Badge</span></a>
                    </li>
                    <li>
                        <a href="#">Alert Name <span class="label label-warning">Alert Badge</span></a>
                    </li>
                    <li>
                        <a href="#">Alert Name <span class="label label-danger">Alert Badge</span></a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#">View All</a>
                    </li>
                </ul>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                        class="fa fa-user"></i>${sessionScope.adminUser.userName} <b
                        class="caret"></b></a>
                <ul class="dropdown-menu">
                    <%--<li>--%>
                    <%--<a href="#"><i class="fa fa-fw fa-user"></i> Profile</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                    <%--<a href="#"><i class="fa fa-fw fa-envelope"></i> Inbox</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                    <%--<a href="#"><i class="fa fa-fw fa-gear"></i> Settings</a>--%>
                    <%--</li>--%>
                    <%--<li class="divider"></li>--%>
                    <%--<li>--%>
                    <a href="${ctx}/manage/logout"><i class="fa fa-fw fa-power-off"></i>退出</a>
                    </li>
                </ul>
            </li>
        </ul>
        <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
        <c:if test="${sessionScope.adminUser.userType==0}">

            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav side-nav">
                    <li ng-class="{'true': 'active', false: 'noac'}['${main}']">
                        <a href="${ctx}/admin/main"><i class="fa fa-fw fa-dashboard"></i> 首页</a>
                    </li>
                    <li ng-class="{true: 'active', false: 'noac'}['${infomation}']">
                        <a href="${ctx}/admin/infomation/list"><i class="fa fa-fw fa-bar-chart-o"></i> 发布信息审核</a>
                    </li>
                    <li ng-class="{true: 'active', false: 'noac'}['${company}']">
                        <a href="${ctx}/admin/company/list"><i class="fa fa-fw fa-table"></i> 公司认证审核</a>
                    </li>
                    <li ng-class="{true: 'active', false: 'noac'}['${adminUser}']">
                        <a href="${ctx}/admin/user/list"><i class="fa fa-fw fa-edit"></i> 用户管理</a>
                    </li>
                    <li ng-class="{true: 'active', false: 'noac'}['${notice}']">
                        <a href="${ctx}/admin/notice/list"><i class="fa fa-fw fa-desktop"></i> 系统公告管理</a>
                    </li>
                        <%--<li>--%>
                        <%--<a href="bootstrap-grid.html"><i class="fa fa-fw fa-wrench"></i> Bootstrap Grid</a>--%>
                        <%--</li>--%>
                    <li ng-class="{true: 'active', false: 'noac'}['${catagory}']">
                        <a href="${ctx}/admin/catagory/list"><i class="fa fa-fw fa-caret-down"></i> 产品数据管理</a>
                    </li>
                    <li ng-class="{true: 'active', false: 'noac'}['${brand}']">
                        <a href="${ctx}/admin/brand/list"><i class="fa fa-fw fa-caret-left"></i> 品牌数据管理</a>
                    </li>
                    <li ng-class="{true: 'active', false: 'noac'}['${models}']">
                        <a href="${ctx}/admin/models/list"><i class="fa fa-fw fa-caret-up"></i> 型号数据管理</a>
                    </li>
                        <%--<li>--%>
                        <%--<a href="javascript:;" data-toggle="collapse" data-target="#demo" aria-expanded="true"><i--%>
                        <%--class="fa fa-fw fa-arrows-v"></i> 基础数据管理 <i class="fa fa-fw fa-caret-down"></i></a>--%>
                        <%--<ul id="demo" class="collapse">--%>
                        <%--<li ng-class="{true: 'active', false: 'noac'}['${catagory}']">--%>
                        <%--<a href="#">产品大类</a>--%>
                        <%--</li>--%>
                        <%--<li>--%>
                        <%--<a href="#">产品组</a>--%>
                        <%--</li>--%>
                        <%--</ul>--%>
                        <%--</li>--%>
                    <%--<li ng-class="{true: 'active', false: 'noac'}['${infomation}']">--%>
                        <%--<a href="${ctx}/admin/infomation/list"><i class="fa fa-fw fa-bar-chart-o"></i> 发布信息管理</a>--%>
                    <%--</li>--%>
                    <%--<li ng-class="{true: 'active', false: 'noac'}['${company}']">--%>
                        <%--<a href="${ctx}/admin/company/list"><i class="fa fa-fw fa-table"></i> 公司信息管理</a>--%>
                    <%--</li>--%>
                </ul>
            </div>
        </c:if>
        <c:if test="${sessionScope.adminUser.userType==1}">
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav side-nav">
                    <li ng-class="{'true': 'active', false: 'noac'}['${main}']">
                        <a href="${ctx}/admin/main"><i class="fa fa-fw fa-dashboard"></i> 首页</a>
                    </li>
                    <li ng-class="{true: 'active', false: 'noac'}['${infomation}']">
                        <a href="${ctx}/admin/infomation/list"><i class="fa fa-fw fa-bar-chart-o"></i> 发布信息审核</a>
                    </li>
                    <li ng-class="{true: 'active', false: 'noac'}['${company}']">
                        <a href="${ctx}/admin/company/list"><i class="fa fa-fw fa-table"></i> 公司认证审核</a>
                    </li>
                </ul>
            </div>
        </c:if>
        <!-- /.navbar-collapse -->
    </nav>

    <div id="page-wrapper" class="panel panel-body" ng-controller="mainCtrl">

        <div class="container-fluid">

            <!-- Page Heading -->
            <div class="row">
                <div class="col-lg-12">
                    <%--<h1 class="page-header">--%>
                    <%--公司信息管理--%>
                    <%--<small>审核</small>--%>
                    <%--</h1>--%>
                    <ol class="breadcrumb">
                        <li class="active">
                            <%--<i class="fa fa-dashboard"></i> 公司信息审核--%>
                        </li>
                    </ol>
                </div>
            </div>
            <!-- /.row -->

            <tiles:insertAttribute name="body"/>

        </div>
        <!-- /.container-fluid -->

    </div>
    <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->


</body>

</html>
