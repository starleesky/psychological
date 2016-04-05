<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext['request'].contextPath}"/>
<!DOCTYPE html>
<html>
<body>
<div class="footer">
    <div class="ft-nav">
        <a  href="${ctx}/about-us.html">关于我们</a>
        <a  href="${ctx}/term-conditions.html">一般条款</a>
        <a  href="${ctx}/contact-us.html">联系我们</a>
    </div>
    <div class="copyright">
        Copyright@2015 湘ICP 14013012号-1 Tangsons(Hunan) Trading Co.Ltd
    </div>
</div>
</body>
</html>