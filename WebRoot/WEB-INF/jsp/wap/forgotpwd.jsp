<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext['request'].contextPath}" />
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>汤森机械网-找回密码</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width,user-scalable=no" />
    <link rel="stylesheet" href="css/module/forgotpwd.css?v=1" type="text/css" charset="utf-8">
</head>
<body>
<!--head begin-->
<header class="ui-header">
    <a href="javascript:;" class="icon iconfont ui-back">&#xe60d</a>
    <span class="ui-title">找回密码</span>
</header>
<!--head end-->
<div class="page-view">
    <section class="ui-login">
        <form action="" class="ui-form" id="forgotForm" method="post">
            <div class="ui-border">
                <fieldset>
                    <div class="field-username filed-item">
                        <div class="ui-form-item">
                            <i class="icon iconfont">&#xe607;</i>
                            <input type="text" name="email" id="email" placeholder="请输入邮箱" value="">
                        </div>
                    </div>
                    <div class="field-captcha filed-item">
                        <div class="ui-form-item">
                            <i class="icon iconfont">&#xe62c;</i>
                            <input type="text" id="captchaCode" name="captchaCode" placeholder="验证码" value="">
                            <img id="captchaImage" src="${ctx}/wap/getVerifyCode.htm" alt="换一张" /></li>
                            
                        </div>
                    </div>
                </fieldset>
            </div>
            <div class="field-submit">
                <input type="submit" class="ui-button ui-button-submit" id="jSubmit" value="下一步">
            </div>
        </form>
    </section>
</div>
<%@ include file="footer.jsp" %>
<script type="text/javascript" src="${ctx}/wap/js/require.js"></script>
<script type="text/javascript" src="${ctx}/wap/js/app.js"></script>
<script type="text/javascript">
var ctx = "${ctx}"
    require(['module/forgotpwd']);
</script>
</body></html>
