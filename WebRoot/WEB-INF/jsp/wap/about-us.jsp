<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext['request'].contextPath}"/>


<!DOCTYPE html>
<html>
<head>
    <title>汤森机械网-关于我们</title>
    <meta charset="utf-8" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name='apple-touch-fullscreen' content='yes'>
<!-- <meta name="full-screen" content="yes"> -->
<meta content="fullscreen=yes,preventMove=no" name="ML-Config">
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<!-- <meta name="format-detection" content="telephone=no,email=no,address=no" /> -->
    <link rel="stylesheet" href="css/module/about-us.css" type="text/css" charset="utf-8">
</head>
<body>
<%@ include file="header.jsp" %>
<div class="page-view">
</div>
<div class="footer">
    <div class="copyright">
        Copyright@2015 湘ICP 14013012号-1 Tangsons(Hunan) Trading Co.Ltd
    </div> 
</div>
<script type="text/javascript" src="${ctx}/wap/js/require.js"></script>
<script type="text/javascript" src="${ctx}/wap/js/app.js"></script>
<script type="text/javascript">
var ctx = "${ctx}"
    require(['conf/about-us']);
</script>
</body></html>
