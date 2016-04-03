<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext['request'].contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>汤森机械网-上架</title>
    <meta charset="utf-8" />
<script>
// flexible.js
!function(e){function h(){var a=f.getBoundingClientRect().width;640<a/b&&(a=640*b);a/=16;f.style.fontSize=a+"px";e.rem=a}function k(a,b,c,e){var d;return function(){var f=e||this,g=arguments,h=c&&!d;clearTimeout(d);d=setTimeout(function(){d=null;c||a.apply(f,g)},b);h&&a.apply(f,g)}}var b,a,d,c=e.document,g=e.navigator,f=c.documentElement,i=c.querySelector('meta[name="viewport"]');d=c.querySelector('meta[name="flexible"]');i?(d=i.getAttribute("content").match(/initial\-scale=(["']?)([\d\.]+)\1?/))&&(a=parseFloat(d[2]),b=parseInt(1/a)):d&&(d=d.getAttribute("content").match(/initial\-dpr=(["']?)([\d\.]+)\1?/))&&(b=parseFloat(j[2]),a=parseFloat((1/b).toFixed(2)));!b&&!a&&(b=e.devicePixelRatio,b=g.appVersion.match(/android/gi)||g.appVersion.match(/iphone/gi)?3<=b?3:2<=b?2:1:1,a=1/b);f.setAttribute("data-dpr",b);i||(a='<meta name="viewport" content="width=device-width, target-densitydpi=high-dpi, initial-scale='+a+", maximum-scale="+a+", minimum-scale="+a+', user-scalable=no" />',f.firstElementChild?(g=c.createElement("div"),g.innerHTML=a,f.firstElementChild.appendChild(g.firstChild)):c.write(a));e.dpr=b;e.addEventListener("resize",k(h,300),!1);e.addEventListener("pageshow",k(function(a){a.persisted&&h()},300),!1);"complete"===c.readyState?c.body.style.fontSize=12*b+"px":c.addEventListener("DOMContentLoaded",function(){c.body.style.fontSize=12*b+"px"},!1);h()}(window);
</script>
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name='apple-touch-fullscreen' content='yes'>
<!-- <meta name="full-screen" content="yes"> -->
<meta content="fullscreen=yes,preventMove=no" name="ML-Config">
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<!-- <meta name="format-detection" content="telephone=no,email=no,address=no" /> -->

    <link rel="stylesheet" href="${ctx }/wap/css/module/want.css?v=1" type="text/css" charset="utf-8" media="all">
</head>
<body>
<!--head begin-->
<%@ include file="header.jsp" %>
<!--head end-->
<div class="page-view">
    <div class="page-view-body">
        <section class="mod-info">
            <div class="hd">
                <h3 class="title">${statusMc }</h3>
                <input type="hidden" id="curStatus" value="${status }">
            </div>
            <div class="bd">
                <a href="${ctx }/infomation/infoList?status=2" status="2">
                    <span class="num">${cnt_sj }</span>
                    <span class="num-desc">上架</span>
                </a>
                <a href="${ctx }/infomation/infoList?status=3" status="3">
                    <span class="num">${cnt_ys }</span>
                    <span class="num-desc">已售</span>
                </a>
                <a href="${ctx }/infomation/infoList?status=4" status="4">
                    <span class="num">${cnt_xj }</span>
                    <span class="num-desc">下架</span>
                </a>
                <a href="${ctx }/infomation/infoList?status=0" status="0">
                    <span class="num">${cnt_cg }</span>
                    <span class="num-desc">草稿</span>
                </a>
                <a href="${ctx }/infomation/colleInfoList?status=9" status="9">
                    <span class="num">${cnt_sc }</span>
                    <span class="num-desc">收藏</span>
                </a>
            </div>
        </section>
        <section class="mod-up-pro">
            <div class="pro-search-box">
                <select>
                    <option value="order=relevance">排 序</option>
                    <option value="order=updated_at">Newest First</option>
                    <option value="order=duration_end">Ending Soonest</option>
                    <option value="order=name">Name: Z-A</option>
                    <option value="order=name">Name: A-Z</option>
                    <option value="order=price">Price: Highest First</option>
                    <option value="order=price">Price: Lowest First</option>
                    <option value="order=condition">Condition: Z-A</option>
                    <option value="order=condition">Condition: A-Z</option>
                </select>
            </div>
            <div class="no-data">
                以下设备已过期，请重新上传。
            </div>
            <div class="pro-buy-num">已售台数:<span>${cnt_ys }</span>台<a href="javascript:;" class="jSelPro pro-sel">标明已售</a> </div>
            <div class="pro-list">
                <form action="" method="post">
                 <ul class="jPage">
                  <div data-page="1">
                  <c:forEach items="${pager}" var="info">
                  <c:choose>
                  	<c:when test="${status == 3 || status == 4 }">
                  		<li class="pro-box no-buy">
                  	</c:when>
                  	<c:otherwise>
                  		<li class="pro-box">
                  	</c:otherwise>
                  </c:choose>
                        <div class="pro-select">
                            <input type="hidden" name="proSelect" value="0" />
                            <img src="" class="jProSelect" />
                        </div>
                        <a href="${ctx}/infomation/input.htm?id=${info.id}" class="pro-img">
	                  		<img src="${ctx}${info.imgUrl}"  class="jImg" data-url="" />
                        </a>
                      
                        <div class="pro-info">
                            <a href="javascript:;" class="pro-title">${info.brandName }/${info.modelName }</a>
                            <strong class="pro-price">${info.price } 元</strong>
                            <p class="pro-date">
                                <span class="year f-l">${info.equipYear }年</span>
                                <span class="hourth f-r">${info.workTime }小时</span>
                            </p>
                            <p>
                                <span class="ready-num f-l">浏览<em class="jRNum">次</em></span>
                                <span class="pro-addr f-r">${info.equipmentLocation }</span>
                            </p>
                            <!-- <p>
                                <span>信息完整度:</span>
                            </p>
                            <p class="pro-msg">
                                <span></span>
                            </p> -->
                            <p class="col-6"> 信息来源：汤森 </p>
                            <p class="col-6"> 设备序列号:<span>${info.serialNum }</span> </p>
                            <p class="col-6"> 截止日期:<span><fmt:formatDate value="${info.endTime}" pattern="yyyy/MM/dd" /></span> </p>
                        </div>
                        <c:if test="${status == 4 }">
                        	<a href="javascript:;" data-url="#" class="pro-new-up jNewUp">重新上架</a>
                        </c:if>
                        <c:if test="${status == 0 }">
                        	<a href="${ctx}/infomation/edit?id=${info.id}" data-url="#" class="pro-new-up jUpPro">修改商品</a>
                        </c:if>
                        
                    </li>
                    </c:forEach>
                    </div>
                </ul>
                </form>
            </div>
        </section>
        <section class="pro-select-info">
            <a href="#" class="button jProSelMsg">
                已售<span></span>台
            </a>
            <a href="javascript:;" class="icon iconfont jCloseProSel">&#xe622;</a>
        </section>
    </div>
</div>
<%@ include file="footer.jsp" %>
<script type="text/javascript" src="${ctx}/wap/js/require.js"></script>
<script type="text/javascript" src="${ctx}/wap/js/app.js"></script>
<script type="text/javascript">
var ctx = "${ctx}";
    require(['module/infor']);
</script>
</body></html>
