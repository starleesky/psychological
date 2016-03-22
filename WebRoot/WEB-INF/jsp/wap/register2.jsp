<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<c:set var="ctx" value="${pageContext['request'].contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <title>汤森机械网-注册</title>
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
    <div class="logo">
        <span>个人信息</span>
    </div>
    <section class="ui-login">
        <form action="" class="ui-form" id="register2" method="post">
        <input type="hidden" id = "id" name = "id" value= "16" />
            <div class="ui-border">
                <fieldset>
                    <div class="ui-form-mod">
                        <label class="ui-form-hd">省份</label>
                        <div class="ui-form-bd">
                            <select name = "provinceId">
                                <option value="500">重庆</option>
                                <option value="0">租赁</option>
                                <option value="0">求购</option>
                                <option value="979">求租</option>
                            </select>
                        </div>
                    </div>
                    <div class="ui-form-mod">
                        <label class="ui-form-hd">城市</label>
                        <div class="ui-form-bd">
                            <select name = "cityId"> 
                                <option value="500226">重庆</option>
                                <option value="0">租赁</option>
                                <option value="0">求购</option>
                                <option value="979">求租</option>
                            </select>
                        </div>
                    </div>
                    <div class="ui-form-mod">
                        <label class="ui-form-hd">经营范围</label>
                        <div class="ui-form-bd">
                            <select name = "businessScope">
                                <option value="1">工程机械</option>
                                <option value="2">农业机械</option>
                                <option value="3">矿山机械</option>
                                <option value="4">林业机械</option>
                                <option value="5">工程车辆</option>
                            </select>
                        </div>
                    </div>
                    <div class="ui-form-mod">
                        <label class="ui-form-hd">经营性质</label>
                        <div class="ui-form-bd">
                            <select name = "businessNature">
                                <option value="1">出售</option>
                                <option value="2">租赁</option>
                                <option value="3">求购</option>
                                <option value="4">求租</option>
                            </select>
                        </div>
                    </div>
                    <div class="ui-form-mod">
                        <label class="ui-form-hd"><input type="checkbox" /> 同意条款<em>*</em> </label>

                    </div>
                </fieldset>
            </div>
            <div class="field-submit">
                <input type="submit" class="ui-button  ui-button-blue" id="jSubmit" value="下一步">
            </div>
        </form>
    </section>
</div>
<%@ include file="footer.jsp" %>
<script type="text/javascript" src="${ctx}/wap/js/require.js"></script>
<script type="text/javascript" src="${ctx}/wap/js/app.js"></script>
<script type="text/javascript">
var ctx = "${ctx}";
    require(['module/register2']);
</script>
</body></html>
