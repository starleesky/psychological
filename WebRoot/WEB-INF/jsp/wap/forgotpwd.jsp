<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>汤森机械网-找回密码</title>
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

    <link rel="stylesheet" href="css/module/forgotpwd.css?v=1" type="text/css" charset="utf-8">
</head>
<body>
<!--head begin-->
<header class="ui-header">
    <a href="javascript:;" class="icon iconfont ui-back">&#xe60d</a>
    <span class="ui-title">找回密码</span>
</header>
<!--head end-->
<div class="page-view">
    <section class="ui-login">
        <form action="" class="ui-form" id="login" method="post">
            <div class="ui-border">
                <fieldset>
                    <div class="field-username filed-item">
                        <div class="ui-form-item">
                            <i class="icon iconfont">&#xe607;</i>
                            <input type="text" name="loginName" id="loginName" placeholder="请输入邮箱" value="">
                        </div>
                    </div>
                    <div class="field-captcha filed-item">
                        <div class="ui-form-item">
                            <i class="icon iconfont">&#xe62c;</i>
                            <input type="text" id="captcha" name="captchaCode" placeholder="验证码" value="">
                            <img src="https://ssl.yunhou.com/bubugao-passport/captcha?type=0&amp;seq=ABE41819E72E5784A160999BF2550DA6&amp;t=1443658215899" data-type="0">
                        </div>
                    </div>
                </fieldset>
            </div>
            <div class="field-submit">
                <input type="submit" class="ui-button ui-button-submit" id="jSubmit" value="下一步">
            </div>
        </form>
    </section>
</div>
<%@ include file="footer.jsp" %>
<script type="text/javascript" src="js/require.js"></script>
<script type="text/javascript" src="js/app.js"></script>
<script type="text/javascript">
    require(['module/forgotpwd']);
</script>
</body></html>
