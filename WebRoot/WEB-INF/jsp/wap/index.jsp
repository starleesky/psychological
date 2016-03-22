<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<c:set var="ctx" value="${pageContext['request'].contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <title>汤森机械网-首页</title>
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

    <link rel="stylesheet" href="css/module/index.css?v=1" type="text/css" charset="utf-8">
</head>
<body>
<!--head begin-->
<%@ include file="header.jsp" %>
<!--head end-->
<div class="page-view">
    <div class="page-view-body">
        <section class="search-type-mod" id="jSearch">
            <div class="search-type-hd">
                <span>42000</span>台出售
            </div>
            <div class="search-type-bd">
                <form class="ui-form">
                    <div class="ui-form-mod">
                        <label class="ui-form-hd">搜索类型</label>
                        <div class="ui-form-bd">
                            <select>
                                <option value="1">出售</option>
                                <option value="0">租赁</option>
                                <option value="0">求购</option>
                                <option value="979">求租</option>
                            </select>
                        </div>
                    </div>
                    <div class="ui-form-mod">
                        <label class="ui-form-hd">产品子类</label>
                        <div class="ui-form-bd">
                            <select>
                                <option value="2622">Aerial Platform</option>
                                <option value="903">Aggregate</option>
                                <option value="1500">Air Compressor</option>
                            </select>
                        </div>
                    </div>
                    <div class="ui-form-mod">
                        <label class="ui-form-hd">品牌</label>
                        <div class="ui-form-bd">
                            <select>
                                <option value="2622">Aerial Platform</option>
                                <option value="903">Aggregate</option>
                                <option value="1500">Air Compressor</option>
                            </select>
                        </div>
                    </div>
                    <div class="ui-form-mod">
                        <label class="ui-form-hd">型号</label>
                        <div class="ui-form-bd">
                            <select>
                                <option value="2622">Aerial Platform</option>
                                <option value="903">Aggregate</option>
                                <option value="1500">Air Compressor</option>
                            </select>
                        </div>
                    </div>

                    <div class="field-submit">
                        <a href="search.html" class="ui-button ui-button-submit ui-button-blue">搜索</a>
                    </div>
                </form>
            </div>
        </section>
        <section class="recommended-mod" id="jRecommend">
            <div class="recommended-hd">
                <span class="icon iconfont">&#xe619;</span>今日推荐
            </div>
            <div class="recommended-bd">
                <ul class="clearfix">
                    <li>
                        <a href="#">
                            <span class="pro-img"><img src="images/blank.gif" class="jImg" data-url="images/img1.jpg" /></span>
                            <span class="name">品牌加型号</span>
                            <span class="price"><b>价格：</b>50000元</span>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <span class="pro-img"><img src="images/blank.gif" class="jImg" data-url="images/img1.jpg" /></span>
                            <span class="name">品牌加型号</span>
                            <span class="price"><b>价格：</b>50000元</span>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <span class="pro-img"><img src="images/blank.gif" class="jImg" data-url="images/img1.jpg" /></span>
                            <span class="name">品牌加型号</span>
                            <span class="price"><b>价格：</b>50000元</span>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <span class="pro-img"><img src="images/blank.gif" class="jImg" data-url="images/img1.jpg" /></span>
                            <span class="name">品牌加型号</span>
                            <span class="price"><b>价格：</b>50000元</span>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <span class="pro-img"><img src="images/blank.gif" class="jImg" data-url="images/img1.jpg" /></span>
                            <span class="name">品牌加型号</span>
                            <span class="price"><b>价格：</b>50000元</span>
                        </a>
                    </li>
                </ul>
            </div>
        </section>
        <section class="collect-mod" id="jCollect">
            <div class="collect-hd">
                <span class="icon"><img src="images/bookmark_icon.png" /> </span>我的收藏
            </div>
            <div class="collect-bd">
            <c:if test="${not empty sessionScope.user.id}">
				<ul class="clearfix" style="display: ;">
                    <li>
                        <a href="#">
                            <span class="pro-img"><img src="images/blank.gif" class="jImg" data-url="images/img1.jpg" /></span>
                            <span class="name">品牌加型号</span>
                            <span class="price"><b>价格：</b>50000元</span>
                        </a>
                    </li>
                </ul>
		    </c:if>
		    <c:if test="${ empty sessionScope.user.id}">
	            <div class="no-login">登录后查看我的收藏</div>
		    </c:if> 
                
            </div>
        </section>
    </div>
</div>

<script type="text/html" id="recommendTpl">

</script>
<%@ include file="footer.jsp" %>
<script type="text/javascript" src="${ctx}/wap/js/require.js"></script>
<script type="text/javascript" src="${ctx}/wap/js/app.js"></script>
<script type="text/javascript">
var ctx = "${ctx}";
    require(['module/index']);
</script>
</body></html>
