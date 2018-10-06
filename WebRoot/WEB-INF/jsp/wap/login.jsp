<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<c:set var="ctx" value="${pageContext['request'].contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <title>汤森机械网-登录</title>
    <%@ include file = "meta.jsp" %>
    <link rel="stylesheet" href="${ctx}/wap/css/module/login.css?v=1" type="text/css" charset="utf-8">
    <style>
    .logo{padding:0 0 20px;}
    .logo img{margin: 20px auto 0;}
    .logo span{text-align: center;}
    </style>
</head>
<body>
<!--head begin-->
<header class="ui-header">
    <a href="/" class="ui-left">
        <img src="${ctx}/wap/images/logo.png" class="ui-logo" />
    </a>

<!--     <a  href="login.html" class="ui-right ui-login"> -->
<%--         <img src="${ctx}/wap/images/user_icon.png" />登录 --%>
<!--     </a> -->
</header>
<!--head end-->
<div class="page-view">
    <div class="logo">
        <img src="${ctx}/wap/images/logo2.png" />
        <span>登  录</span>
    </div>
    <section class="ui-login">
        <form action="/wap/loginIn" class="ui-form" id="loginForm"  method="post">
            <div class="ui-form-mod">
                <label class="ui-form-hd">账号</label>
                <div class="ui-form-bd">
                    <input type="text" name="UserName" id="UserName" placeholder="请输入手机号码或邮箱账号" value="">
                </div>
            </div>
            <div class="ui-form-mod">
                <label class="ui-form-hd">密码</label>
                <div class="ui-form-bd">
                    <input type="password" id="Pwd"  name="Pwd" placeholder="请输入密码" value="">
                </div>
            </div>
            <div class="field-submit">
                <a href="javascript:;" class="ui-button   ui-button-blue" id="jSubmit">登录</a>
            </div>

            <div class="field-forgetpassword">
                <a  href="${ctx}/wap/toRegister.htm">免费注册</a>
                <a  href="${ctx}/wap/forgotpwd2.htm">忘记密码</a>
            </div>
        </form>
    </section>
</div>

<script type="text/javascript" src="${ctx}/wap/js/require.js"></script>
<script type="text/javascript" src="${ctx}/wap/js/app.js"></script>
<script type="text/javascript">
var ctx = "${ctx}";
    require(['module/login']);
</script>
</body></html>
