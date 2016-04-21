<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<body>
<header class="ui-header">
     
    <c:if test="${not empty sessionScope.user.id}">
    	<c:if test="${not empty sessionScope.isNewMessage}">
    	</c:if>
    	<a href="${ctx }/wap/message/my.htm" class="ui-left">
        <img src="${ctx}/wap/images/icon_2.png"  />
    </a>
    <a href="${ctx}/user/infor/my.htm" class="ui-title"><img src="${ctx}/wap/images/icon_2.png" /> </a>
    <a  href="${ctx}/wap/loginOut.htm" class="ui-right">
        <img src="${ctx}/wap/images/upload_cloud.png" />
    </a>
    </c:if>
    <c:if test="${ empty sessionScope.user.id}">
    <a href="${ctx}/wap/index.htm" class="ui-left">
        <img src="${ctx}/wap/images/logo.png" class="ui-logo" />
    </a>
    <a  href="${ctx}/wap/login.htm" class="ui-right ui-login">
        <span class="icon iconfont">&#xe600;</span>登录
    </a>
    </c:if>   
</header>
</body>
</html>