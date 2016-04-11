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
</head>
<body>
<!--head begin-->
<header class="ui-header">
    <a href="#" class="ui-left">
        <img src="${ctx}/wap/images/logo.gif" class="ui-logo" />
    </a>

<!--     <a  href="login.html" class="ui-right ui-login"> -->
<%--         <img src="${ctx}/wap/images/user_icon.png" />登录 --%>
<!--     </a> -->
</header>
<!--head end-->
<div class="page-view">
    <div class="logo">
        <img src="${ctx}/wap/images/logo2.png" />
        <span>欢迎</span>
    </div>
    <section class="ui-login">
        <form action="/wap/loginIn" class="ui-form" id="loginForm"  method="post">
            <div class="ui-border">
                <fieldset>
                    <div class="field-username filed-item">
                        <div class="ui-form-item">
                            <i class="icon iconfont">&#xe607;</i>
                            <input type="text" name="UserName" id="UserName" placeholder="请输入邮箱" value="">
                        </div>
                    </div>
                    <div class="field-password filed-item">
                        <div class="ui-form-item">
                            <i class="icon iconfont">&#xe633;</i>
                            <input type="password" id="Pwd"  name="Pwd" placeholder="请输入密码" value="">
                        </div>
                    </div>
                    <!--<div class="field-captcha filed-item">
                        <div class="ui-form-item">
                            <i class="icon iconfont">&#xe62c;</i>
                            <input type="text" id="captcha" name="captchaCode" placeholder="验证码" value="">
                            <img src="https://ssl.yunhou.com/bubugao-passport/captcha?type=0&amp;seq=ABE41819E72E5784A160999BF2550DA6&amp;t=1443658215899" data-type="0">
                        </div>
                    </div>-->
                </fieldset>
            </div>
            <div class="field-submit">
                <input type="button" value="登录" class="ui-button   ui-button-blue" id="jSubmit">
            </div>

            <div class="field-forgetpassword">
                <p>还没有账号！<br/>赶快免费注册吧!</p>
                <a  href="${ctx}/wap/toRegister.htm">注册</a>
                <a  href="${ctx}/wap/forgotpwd.htm">忘记密码</a>
            </div>
        </form>
    </section>
</div>
<%@ include file="footer.jsp" %>
<script type="text/javascript" src="${ctx}/wap/js/require.js"></script>
<script type="text/javascript" src="${ctx}/wap/js/app.js"></script>
<script type="text/javascript">
var ctx = "${ctx}";
    require(['module/login']);
</script>
</body></html>
