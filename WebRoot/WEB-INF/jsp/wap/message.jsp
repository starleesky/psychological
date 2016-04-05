<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext['request'].contextPath}"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>汤森机械网-消息</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width,user-scalable=no" />
    <link rel="stylesheet" href="${ctx }/wap/css/module/message.css?v=1" type="text/css" charset="utf-8" media="all">
</head>
<body>
<!--head begin-->
<%@ include file="header.jsp" %>
<!--head end-->
<div class="page-view">
    <div class="page-view-body">
        <section class="mod-info">
            <div class="hd">
                <h3 class="title">消息</h3>
            </div>

        </section>
        <section class="msg-list">
            <ul class="clearfix">

                <c:forEach items="${notices}" var="notice">
                <li>
                    <img src="${ctx }/wap/images/blank.gif" class="jImg" data-url="${ctx }/wap/images/face.png" />
                    <div class="msg-info">
                        <p class="msg-title">${notice.title}</p>
                        <p class="msg-txt">${notice.content}</p>
                        <p class="msg-date"><fmt:formatDate value="${notice.createTime}" pattern="yyyy/MM/dd  HH:mm:ss" />
                        </p>
                    </div>
                    <%--<div class="msg-btn">--%>
                        <%--<a href="javascript:;" class="jDelMsg">--%>
                            <%--<span class="icon iconfont">&#xe629;</span>--%>
                        <%--</a>--%>
                    <%--</div>--%>
                </li>
                </c:forEach>
            </ul>
        </section>
    </div>
</div>
<%@ include file="footer.jsp" %>
<script type="text/javascript" src="${ctx }/wap/js/require.js"></script>
<script type="text/javascript" src="${ctx }/wap/js/app.js"></script>
<script type="text/javascript">
    var ctx = "${ctx}";
    require(['module/message']);
</script>
</body></html>
