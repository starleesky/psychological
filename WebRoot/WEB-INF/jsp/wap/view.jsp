<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext['request'].contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>汤森机械网-详情页面</title>
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

    <link rel="stylesheet" href="${ctx}/wap/css/module/view.css?v=1" type="text/css" charset="utf-8">
</head>
<body>
<!--head begin-->
<%@ include file = "header.jsp" %>  
<!--head end-->
<div class="page-view">
    <div class="page-view-body">
        <section class="pro-view-mod img-box">
            <div class="hd">
                <a href="./index.html" class="prev-btn"><i class="icon iconfont">&#xe60d;</i></a>
                <div class="img-title">
                    <h2 class="pro-name">${bean.brandName}${bean.modelName}</h2>
                    <p class="pro-price">${bean.price}元</p>
                    <p class="pro-num">汤森信息编号: BNE01068</p>
                </div>
                <a href="./index.html" class="next-btn"><i class="icon iconfont">&#xe60e;</i></a>
            </div>
            <div class="bd" id="jImgBox">
                <div class="big-img">
                    <img src="${ctx}${firstImg}" />
                </div>
                <div class="img-list">
                	<c:forEach items="${listAttch }" var="attach">
	                	<a href="javascript:;" data-url="${ctx }${attach.attchUrl}">
	                        <img src="${ctx }${attach.attchUrl}" />
	                    </a>
                	</c:forEach>
                   <!--  <a href="javascript:;" data-url="images/img_1.jpg">
                        <img src="images/img_1.jpg" />
                    </a>
                    <a href="javascript:;" data-url="images/img_2.jpg">
                        <img src="images/img_2.jpg" />
                    </a> -->
                </div>
            </div>
        </section>
        <section class="img-big-box">
            <a href="javascript:;" class="jClose icon iconfont">&#xe622;</a>
            <img src="images/img_1.jpg" />
        </section>
        <section class="pro-set-info pro-view-mod">
            <div class="hd">设备要求</div>
            <div class="bd">
                <div class="pro-view-box">
                    <h3>${bean.brandName}${bean.modelName}的求购信息</h3>
                    <ul>
                        <li>类别: ${bean.catagoryName}</li>
                        <li>品牌: ${bean.brandName}</li>
                        <li>求购类型:
                        <c:if test="${bean.sellType == '0'}">
                        	出售
                        </c:if>
                        <c:if test="${bean.sellType == '1'}">
                        	租赁
                        </c:if>
                        <c:if test="${bean.sellType == '2'}">
                        	求购
                        </c:if>
                        <c:if test="${bean.sellType == '3'}">
                        	求租
                        </c:if>
                        </li>
	
                        <li>设备类型: 
                        <c:if test="${bean.equipmentCondition == '0'}">
                        新设备
                        </c:if>
                        <c:if test="${bean.equipmentCondition == '1'}">
                        二手设备
                        </c:if>
                        <c:if test="${bean.equipmentCondition == '2'}">
                        再制造
                        </c:if>
                        </li>
                        <li>手续资料要求: 
                        <c:if test="${bean.procedures == '0'}">
                        手续齐全
                        </c:if>
                        <c:if test="${bean.procedures == '1'}">
                        无手续
                        </c:if>
                        <c:if test="${bean.procedures == '2'}">
                        有无手续均可
                        </c:if>
                        </li>
                        <li>设备来源要求: 
                        <c:if test="${bean.src == '0'}">个人
                        </c:if>
                        <c:if test="${bean.src == '1'}">单位
                        </c:if>
                        <c:if test="${bean.src == '2'}">抵押
                        </c:if>
                        <c:if test="${bean.src == '3'}">法务
                        </c:if>
                        </li>
                        <li>发布日期: ${bean.pubTime}</li>
                        <li>有效期至: ${bean.validTime}</li>
                        <li>生产年份要求: ${bean.equipYear}年以后</li>
                        <li class="w100">工作小时要求: ${bean.workTime}小时以内</li>
                        <li class="w100">设备位置要求: ${bean.equipmentLocation}</li>
                        <li class="w100 cl-1">期望价格: ${bean.price}元左右</li>
                    </ul>
                </div>
                <div class="pro-view-box">
                    <h3>买家附言</h3>
                    <p>${bean.remark}</p>
                </div>
            </div>
        </section>
        <section class="buy-man-info pro-view-mod">
            <div class="hd">卖家信息</div>
            <div class="bd">
                <div class="pro-view-box">
                    <div class="pro-img"><img src="images/img1.png" /></div>
                    <h3>${user.userName}</h3>
                    <ul>
                        <li class="w100">注册时间：${user.createTime}</li>
                        <liclass="w100">电话：<a href="tel:${user.mobile}">${user.mobile}</a></li>
                        <li class="w100">座机：<a href="tel:${user.telephone}">${user.telephone}</a></li>
                        <li class="w100">Q  Q：${user.qq}</li>
                        <li>经营范围: ${user.businessScope}</li>
                        <li>经营性质：${user.businessNature}</li>
                        <li class="w100">所属公司：${company.companyName}</li>
                        <li class="w100">公司地址：${company.address}</li>
                    </ul>
                </div>
                <div class="pro-view-box">
                    <h3>公司简介</h3>
                   <p>${company.introduction}
                </div>
            </div>
        </section>

    </div>
</div>
<%@ include file="footer.jsp" %>
<script type="text/javascript" src="${ctx}/wap/js/require.js"></script>
<script type="text/javascript" src="${ctx}/wap/js/app.js"></script>
<script type="text/javascript">
var ctx = "${ctx}";
    require(['module/view']);
</script>
</body></html>
