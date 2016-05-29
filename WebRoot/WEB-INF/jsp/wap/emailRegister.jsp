<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<c:set var="ctx" value="${pageContext['request'].contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <title>汤森机械网-注册</title>
    <%@ include file = "meta.jsp" %>
    <link rel="stylesheet" href="${ctx}/wap/css/module/register.css?v=1" type="text/css" charset="utf-8">
    <style>
    .logo{padding:0 0 20px;}
    .logo img{margin: 20px auto 0;}
    section{margin-bottom:0;}
    .logo span{text-align: center;}
    .ui-form-mod .ui-form-bd{position:relative;}
    .ui-form-mod .ui-form-bd.dx-box input{width: 68%;}
    .ui-form-mod .ui-form-bd .ui-button{position: absolute;top:0;right: 0;display: inline-block;width: 30%;}
    </style>
</head>
<body>
<!--head begin-->
<header class="ui-header">
    <a href="${ctx}/wap/index.htm" class="ui-left">
        <img src="${ctx}/wap/images/logo.png" class="ui-logo" />
    </a>
    <a  href="${ctx}/wap/login.htm" class="ui-right ui-login">
        <span class="icon iconfont">&#xe600;</span>登录
    </a>
</header>
<!--head end-->
<div class="page-view">
    <div class="logo">
        <img src="images/logo2.png" />
        <span>注册</span>
    </div>
    <section class="ui-login">
        <form action="${ctx}/wap/register" class="ui-form" id="registerEmail" method="post">
            <div class="ui-form-mod">
                <label class="ui-form-hd">邮箱</label>
                <div class="ui-form-bd">
                    <input type="text" id="email" name="email" placeholder="请输入邮箱" value="">
                </div>
            </div>
            <div class="ui-form-mod">
                <label class="ui-form-hd">密码</label>
                <div class="ui-form-bd">
                    <input type="password" id="password" name="password" placeholder="请输入密码" value="">
                </div>
            </div>
            <div class="ui-form-mod">
                <label class="ui-form-hd">确认密码</label>
                <div class="ui-form-bd">
                    <input type="password" id="confirmPwd" name="confirmPwd" placeholder="请输入确认密码" value="">
                </div>
            </div>
            <div class="field-submit">
                    <a href="javascript:;" class="ui-button   ui-button-submit" id="jSubmitEmail">下一步</a>
            </div>
        </form>
    </section>
</div>

<script type="text/javascript" src="${ctx}/wap/js/require.js"></script>
<script type="text/javascript" src="${ctx}/wap/js/app.js"></script>
<script type="text/javascript">
	var ctx = "${ctx}";
    require(['module/registerEmail']);
</script>
</body></html>
