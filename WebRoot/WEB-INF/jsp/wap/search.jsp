<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext['request'].contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>汤森机械网-搜索&高级搜索</title>
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

    <link rel="stylesheet" href="${ctx }/wap/css/module/search.css?v=1" type="text/css" charset="utf-8">
</head>
<body>
<!--head begin-->
<%@ include file="header.jsp" %>
<!--head end-->
<div class="page-view">
    <div class="page-view-body">
        <section class="search-title">
            <a href="javascript:;" class="ui-button   ui-button-white op-button-higSearch" >高级搜索</a>
            <select>
                <option value="2622">Aerial Platform</option>
                <option value="903">Aggregate</option>
                <option value="1500">Air Compressor</option>
            </select>
        </section>
        <section class="contact-mod">
            <div class="no-login"><a href="./login.html">登录后可以收藏</a></div>
        </section>
        <section class="search-mod">
            <div class="search-hd"></div>
            <div class="pro-list search-bd">
                <ul class="jPage">
                    <div data-page="1">
	                    <c:forEach items="${pager}" var="info">
		                    <li class="pro-box">
		                        <a href="#" class="pro-img">
		                            <img src="" class="jImg" data-url="" />
		                        </a>
		                        <div class="pro-info">
		                            <a href="#" class="pro-title">${info.brandName }/${info.modelName }</a>
		                            <strong class="pro-price">${info.price } 元</strong>
		                            <p class="pro-date">
		                                <span class="year f-l">${info.equipYear }年</span>
		                                <span class="hourth f-r">${info.workTime }小时</span>
		                            </p>
		                            <p>
		                                <span class="ready-num f-l">浏览<em class="jRNum"></em></span>
		                                <span class="pro-addr f-r">${info.equipmentLocation }</span>
		                            </p>
		                        </div>
		                    </li>
	                   	</c:forEach>
                	</div>
                </ul>
            </div>
        </section>
        <section class="search-form">
            <div class="search-form-title">
                <h2>高级搜索</h2>
                <a href="javascript:;" class="f-r op-button-return">返回</a>
            </div>
            <form class="ui-form" id="infoSearchForm">
                <div class="ui-form-mod">
                    <label class="ui-form-hd">设备地点</label>
                    <div class="ui-form-bd">
                       
                    </div>
                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">产品大类</label>
                    <div class="ui-form-bd">
                    	<input type="hidden" name="catagoryBigId" id="catagoryBigId"/>
                    	<input type="hidden" name="catagoryBigName" id="catagoryBigName"/>
                        <select class="bigGoodsCatagory"  name="catagoryBig" id="catagoryBig" validate="required:true" ><option value="" selected>请选择产品大类</option></select>
                    </div>
                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">产品子类</label>
                    <div class="ui-form-bd">
                    	<input type="hidden" name="catagoryMidId" id="catagoryMidId"/>
                    	<input type="hidden" name="catagoryMidName" id="catagoryMidName"/>
                        <select class="middleGoodsCatagory" name="catagoryMid" id="catagoryMid" validate="required:true"><option>请选择产品子类</option></select>
                    </div>
                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">全部产品</label>
                    <div class="ui-form-bd">
                    	<input type="hidden" name="catagoryId" id="catagoryId"/>
                    	<input type="hidden" name="catagoryName" id="catagoryName"/>
                       <select class="smallGoodsCatagory" name="catagorySmall" id="catagorySmall" validate="required:true"><option>请选择产品</option></select>
                    </div>
                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">品牌</label>
                    <div class="ui-form-bd">
                    	<input type="hidden" name="brandId" id="brandId"/>
                    	<input type="hidden" name="brandName" id="brandName"/>
                        <select class="brand" name="brand" id="brand" validate="required:true"><option>请选择品牌</option></select>
                    </div>
                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">型号</label>
                    <div class="ui-form-bd">
                    	<input type="hidden" name="modelId" id="modelId"/>
                    	<input type="hidden" name="modelName" id="modelName"/>
                       	<select class="models"  name="models" id="models" validate="required:true"><option>请选择型号</option></select>
                    </div>
                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">销售方式</label>
                    <div class="ui-form-bd">
                    	<input type="hidden" name="sellType" id="sellType"/>
                        <select name="sellType">
                        	<option value="" selected></option>
                        	<option value="2">求购</option>
                       		<option value="3">求租</option>
                       	</select>
                    </div>
                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">设备情况</label>
                    <div class="ui-form-bd">
                   	 	<input type="hidden" name="equipmentCondition" id="equipmentCondition"/>
                        <select name="equipmentCondition">
                        	<option value="" selected></option>
                            <option value="0">新设备</option>
                            <option value="1">二手设备</option>
                            <option value="2">再制造</option>
                        </select>
                    </div>
                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">手续资料</label>
                    <div class="ui-form-bd">
                    	<input type="hidden" name="procedures" id="procedures"/>
                        <select name="procedures">
                        	<option value="" selected></option>
                            <option value="0">手续齐全</option>
                            <option value="1">无手续</option>
                            <option value="2">有无手续均可</option>
                        </select>
                    </div>
                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">年份</label>
                    <div class="ui-form-bd">
                        <select>
                            
                        </select>
                    </div>
                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">工时</label>
                    <div class="ui-form-bd ui-small-select">
                        <select>
                           
                        </select>
                        <span>TO</span>
                        <select>
                            
                        </select>
                    </div>
                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">价格</label>
                    <div class="ui-form-bd ui-small-select">
                        <select>
                          
                        </select>
                        <span>TO</span>
                        <select>
                           
                        </select>
                    </div>
                </div>
                <div class="field-submit">
                    <a href="#" class="ui-button ui-button-submit ui-button-blue" id="searchResult">搜索</a>
                </div>
            </form>
        </section>
    </div>
</div>
<%@ include file="footer.jsp" %>
<script type="text/javascript" src="${ctx}/wap/js/require.js"></script>
<script type="text/javascript" src="${ctx}/wap/js/app.js"></script>
<script type="text/javascript">
    var ctx = "${ctx}";
    require(['module/search','module/want-release']);
</script>
</body></html>
