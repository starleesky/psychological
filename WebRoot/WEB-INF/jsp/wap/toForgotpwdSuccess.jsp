<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext['request'].contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>汤森机械网-找回密码</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width,user-scalable=no" />
    <link rel="stylesheet" href="${ctx}/wap/css/module/register.css?v=1" type="text/css" charset="utf-8">
</head>
<body>
<!--head begin-->
<%@ include file="header.jsp" %>
<!--head end-->
<div class="page-view">
    <section class="register-success">
        <h3>您的密码我们以发送至你的邮箱</h3>
        <p>登录后请修改您的密码</p>
    </section>
</div>
<%@ include file="footer.jsp" %>
<script type="text/javascript" src="${ctx}/wap/js/require.js"></script>
<script type="text/javascript" src="${ctx}/wap/js/app.js"></script>
<script type="text/javascript">
var ctx ="${ctx}";
    require(['module/register']);
</script>
</body></html>
