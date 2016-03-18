<!DOCTYPE html>
<html>
<head>
    <title>汤森机械网-注册</title>
    <meta charset="utf-8" />
<script>
// flexible.js
!function(e){function h(){var a=f.getBoundingClientRect().width;640<a/b&&(a=640*b);a/=16;f.style.fontSize=a+"px";e.rem=a}function k(a,b,c,e){var d;return function(){var f=e||this,g=arguments,h=c&&!d;clearTimeout(d);d=setTimeout(function(){d=null;c||a.apply(f,g)},b);h&&a.apply(f,g)}}var b,a,d,c=e.document,g=e.navigator,f=c.documentElement,i=c.querySelector('meta[name="viewport"]');d=c.querySelector('meta[name="flexible"]');i?(d=i.getAttribute("content").match(/initial\-scale=(["']?)([\d\.]+)\1?/))&&(a=parseFloat(d[2]),b=parseInt(1/a)):d&&(d=d.getAttribute("content").match(/initial\-dpr=(["']?)([\d\.]+)\1?/))&&(b=parseFloat(j[2]),a=parseFloat((1/b).toFixed(2)));!b&&!a&&(b=e.devicePixelRatio,b=g.appVersion.match(/android/gi)||g.appVersion.match(/iphone/gi)?3<=b?3:2<=b?2:1:1,a=1/b);f.setAttribute("data-dpr",b);i||(a='<meta name="viewport" content="width=device-width, target-densitydpi=high-dpi, initial-scale='+a+", maximum-scale="+a+", minimum-scale="+a+', user-scalable=no" />',f.firstElementChild?(g=c.createElement("div"),g.innerHTML=a,f.firstElementChild.appendChild(g.firstChild)):c.write(a));e.dpr=b;e.addEventListener("resize",k(h,300),!1);e.addEventListener("pageshow",k(function(a){a.persisted&&h()},300),!1);"complete"===c.readyState?c.body.style.fontSize=12*b+"px":c.addEventListener("DOMContentLoaded",function(){c.body.style.fontSize=12*b+"px"},!1);h()}(window);
</script>
<c:set var="ctx" value="${pageContext['request'].contextPath}"/>

<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name='apple-touch-fullscreen' content='yes'>
<!-- <meta name="full-screen" content="yes"> -->
<meta content="fullscreen=yes,preventMove=no" name="ML-Config">
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<!-- <meta name="format-detection" content="telephone=no,email=no,address=no" /> -->

    <link rel="stylesheet" href="css/module/register.css?v=1" type="text/css" charset="utf-8">
</head>
<body>
<!--head begin-->
<header class="ui-header">
    <a href="#" class="ui-left">
        <img src="images/logo.gif" class="ui-logo" />
    </a>

    <a  href="login.html" class="ui-right ui-login">
        <img src="images/user_icon.png" />登录
    </a>
</header>
<!--head end-->
<div class="page-view">
    <div class="logo">
        <img src="images/logo2.png" />
        <span>注册</span>
    </div>
    <section class="ui-login">
        <form action="${ctx}/user/register.htm" class="ui-form" id="login" method="post">
            <div class="ui-border">
                <fieldset>
                    <div class="field-password filed-item">
                        <div class="ui-form-item">
                            <i class="icon iconfont">&#xe610;</i>
                            <input type="text" id="email" name="email" placeholder="请输入邮箱" value="">
                        </div>
                    </div>
                    <div class="field-username filed-item">
                        <div class="ui-form-item">
                            <i class="icon iconfont">&#xe631;</i>
                            <input type="text" name="loginName" id="loginName" placeholder="请输入手机号码" value="">
                        </div>
                    </div>
                    <div class="field-password filed-item">
                        <div class="ui-form-item">
                            <i class="icon iconfont">&#xe633;</i>
                            <input type="password" id="password" name="password" placeholder="请输入密码" value="">
                        </div>
                    </div>
                    <div class="field-password filed-item">
                        <div class="ui-form-item">
                            <i class="icon iconfont">&#xe626;</i>
                            <input type="password" id="rePassword" name="rePassword" placeholder="请输入确认密码" value="">
                        </div>
                    </div>
                </fieldset>
            </div>
            <div class="field-submit">
                <input type="submit" class="ui-button  ui-button-blue" id="jSubmit" value="下一步">
            </div>
        </form>
    </section>
</div>
<div class="footer">

    <div class="ft-nav">
        <a  href="about-us.html">关于我们</a>
        <a  href="term-conditions.html">一般条款</a>
        <a  href="contact-us.html">联系我们</a>
    </div>
    <div class="copyright">
        Copyright@2015 湘ICP 14013012号-1 Tangsons(Hunan) Trading Co.Ltd
    </div> 
</div>
<script type="text/javascript" src="js/require.js"></script>
<script type="text/javascript" src="js/app.js"></script>
<script type="text/javascript">
    require(['module/register']);
</script>
</body></html>