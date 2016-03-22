<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext['request'].contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>汤森机械网-公司信息</title>
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

    <link rel="stylesheet" href="css/module/company-info.css?v=1" type="text/css" charset="utf-8">
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
    <div class="page-view-body">
        <section class="mod-info">
            <div class="hd">
                <h3 class="title">添加公司信息</h3>
            </div>
            <div class="bd">
                <a class="bd-l" href="orders.html">
                    <span class="num">35</span>
                    <span class="num-desc">上架</span>
                </a>
                <a class="bd-m" href="logistics.html">
                    <span class="num">35</span>
                    <span class="num-desc">已售</span>
                </a>
                <a class="bd-m" href="logistics.html">
                    <span class="num">35</span>
                    <span class="num-desc">下架</span>
                </a>
                <a class="bd-r" href="coupon.html">
                    <span class="num">35</span>
                    <span class="num-desc">草稿</span>
                </a>
            </div>
        </section>
        <section class="mod-add-company-info">
            <form class="ui-form">
                <div class="ui-form-mod info clearfix">
                    <img src="images/icon_2.png" class="f-l"  />
                    <div class="info-desc f-l">
                        <a href="javascript:;" class="current jAddImg">上传LOGO</a>
                        <a href="javascript:;" class="jDelImg">删除LOGO</a>
                    </div>

                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">公司名称<em>*</em></label>
                    <div class="ui-form-bd">
                        <input type="text" name="companyName" class="ui-input"/>
                    </div>
                </div> 
                <div class="ui-form-mod">
                    <label class="ui-form-hd">电话</label>
                    <div class="ui-form-bd">
                        <input type="text" name="companyMobile" />
                    </div>
                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">传真</label>
                    <div class="ui-form-bd">
                        <input type="text" name="companyFax" />
                    </div>
                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">所在省份</label>
                    <div class="ui-form-bd">
                        <input type="text" name="companyProvince" />
                    </div>
                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">所在城市</label>
                    <div class="ui-form-bd">
                        <input type="text" name="companyCity" />
                    </div>
                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">详细地址<em>*</em></label>
                    <div class="ui-form-bd">
                        <input type="text" name="address" />
                    </div>
                </div>
				<div class="ui-form-mod">
                    <label class="ui-form-hd">公司介绍<em>*</em></label>
                    <div class="ui-form-bd">
                        <textarea  ></textarea>
                    </div>
                </div>
                <!--div class="ui-form-mod">
                    <label class="ui-form-hd">营业执照副本<em>*</em></label>
                    <div class="ui-form-bd">
                        <span class=" ">
                            <input type="file" name="file[]" />
                            上传营业执照副本
                        </span>
                        <a href="javascript:;" class="jDelFile" style="display: none">删除副本</a>
                    </div>
                </div>
                <div class="ui-form-mod">

                    <label class="ui-form-hd">组织机构代码证<em>*</em></label>
                    <div class="ui-form-bd">
                        <span class=" ">
                            <input type="file" name="file[]" />
                            上传组织机构代码证
                        </span>
                        <a href="javascript:;" class="jDelFile" style="display: none">删除代码证</a>
                    </div>
                </div-->
                <div class="ui-form-mod">
                    <label class="ui-form-hd">营业执照副本<em>*</em></label>
                    <div class="ui-form-bd upload-img">
                        <a href="javascript:;"   node-type="uploadButton">
                            <span class="upload-txt">选择图片</span>
                        </a>

                    </div>
                </div>
                <div class="ui-form-mod">

                    <label class="ui-form-hd">组织机构代码证<em>*</em></label>
                    <div class="ui-form-bd upload-img edit">
                        <a href="javascript:;"  node-type="uploadButton">
                            <span class="upload-txt">选择图片</span>
                        </a>
                        <div class="upload-div">
                            <img src="http://img4.bbgstatic.com/14fa1ec9e18_bc_2999191fce3a29942c38d72f42b40f53_464x785.jpeg">
                            <b class="icon-delete" node-type="deleteImgBtn"></b>
                        </div>

                    </div>
                </div>
                <div class="field-submit">
                    <input type="submit" class="ui-button ui-button-blue " id="jSubmit" value="提交">
                </div>
            </form>
        </section>
    </div>
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
    var ctx = "${ctx}";
    require(['module/company-info']);
</script>
</body></html>
