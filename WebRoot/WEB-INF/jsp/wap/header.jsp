<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<body>
<header class="ui-header">
    <a href="#" class="ui-left">
        <img src="${ctx}/wap/images/logo.gif" class="ui-logo" />
    </a> 
    <c:if test="${not empty sessionScope.user.id}">
    			欢迎您:${sessionScope.user.userName}
    	<a  href="${ctx}/wap/loginOut.htm" class="ui-right ui-login">
        <img src="${ctx}/wap/images/user_icon.png" />退出
    </a>
    </c:if>
    <c:if test="${ empty sessionScope.user.id}">
    <a  href="${ctx}/wap/login.htm" class="ui-right ui-login">
        <img src="${ctx}/wap/images/user_icon.png" />登录
    </a>
    </c:if>   
</header>
</body>
</html>