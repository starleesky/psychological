<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/3/17
  Time: 23:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext['request'].contextPath}"/>
<tiles:insertAttribute name="body" />
