<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext['request'].contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>汤森机械网-我要销售</title>
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

    <link rel="stylesheet" href=${ctx}/wap/css/module/want.css?v=1" type="text/css" charset="utf-8">
</head>
<body>
<!--head begin-->
<header class="ui-header">
    <a href="#" class="ui-left">
        <img src="${ctx}/wap/images/logo.gif" class="ui-logo" />
    </a>

    <a  href="login.html" class="ui-right ui-login">
        <img src="${ctx}/wap/images/user_icon.png" />登录
    </a>
</header>
<!--head end-->
<div class="page-view">
    <div class="page-view-body">
        <section class="mod-info">
            <div class="hd">
                <h3 class="title">我要销售</h3>
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
        <section class="mod-want-buy">
            <form class="ui-form" id="informationFrom">
                <div class="ui-form-title"><img src="${ctx}/wap/images/photo_icon.png"/> 上传图片</div>
                <div class="ui-form-mod upload-img">
                    <div class="img-sp">
                        <input type="file" name="file[]" value="aaa" />
                        <img src="${ctx}/wap/images/camera_load_icon.png" />
                        <span>照片</span>
                    </div>
                    <div class="img-sp">
                        <input type="file" name="file[]" value="aaa" />
                        <img src="${ctx}/wap/images/image_load_icon.png" />
                        <span>上传图片</span>
                    </div>

                </div>
                <div class="ui-form-title">选择产品类别</div>
                <div class="ui-form-mod">
                    <div class="ui-form-bd">
                        <select class="bigGoodsCatagory"  name="catagoryBig" id="catagoryBig" validate="required:true" ><option>请选择产品大类</option></select>
                        <select class="middleGoodsCatagory" name="catagoryMid" id="catagoryMid" validate="required:true"><option>请选择产品子类</option></select>
                        <select class="smallGoodsCatagory" name="catagorySmall" id="catagorySmall" validate="required:true"><option>请选择产品</option></select>
                    </div>
                </div>
                <div class="ui-form-title">选择品牌和 & 型号</div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">品牌和 & 型号</label>
                    <div class="ui-form-bd">
                        <select class="brand" name="brand" id="brand" validate="required:true"><option>请选择品牌</option></select>
                        <select class="models"  name="models" id="models" validate="required:true"><option>请选择型号</option></select>
                    </div>
                </div>
                <div class="ui-form-mod desc">
                    <p class="ui-form-hd ">注:如找不到品牌和型号请点击<img class="jAddProType" src="${ctx}/wap/images/increa_icon.png" /><em>*</em></p>

                </div>
                <div class="ui-form-mod isHide desc-child">
                    <label class="ui-form-hd">添加品牌</label>
                    <div class="ui-form-bd">
                        <input type="text" placeholder="请输入...">
                    </div>
                </div>
                <div class="ui-form-mod isHide desc-child">
                    <label class="ui-form-hd">添加型号</label>
                    <div class="ui-form-bd">
                        <input type="text" placeholder="请输入...">
                    </div>
                </div>
                <div class="ui-form-title">设备详情</div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">求购方式</label>
                    <div class="ui-form-bd">
                        <select><option>求购</option><option>求租</option></select>
                    </div>
                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">设备情况</label>
                    <div class="ui-form-bd">
                        <select>
                            <option value="37">新设备</option>
                            <option value="38">二手设备</option>
                            <option value="39">再制造</option>
                        </select>
                    </div>
                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">手续资料</label>
                    <div class="ui-form-bd">
                        <select>
                            <option value="37">手续齐全</option>
                            <option value="38">无手续</option>
                            <option value="38">有无手续均可</option>
                        </select>
                    </div>
                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">设备来源</label>
                    <div class="ui-form-bd">
                        <select>
                            <option value="37">个人</option>
                            <option value="38">单位</option>
                            <option value="38">抵押</option>
                            <option value="38">法务</option>
                        </select>
                    </div>
                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">年份/以后</label>
                    <div class="ui-form-bd">
                        <select>
                            <option value="2015">2015</option>
                            <option value="2014">2014</option>
                            <option value="2013">2013</option>
                            <option value="2012">2012</option>
                            <option value="2011">2011</option>
                            <option value="2010">2010</option>
                            <option value="2009">2009</option>
                            <option value="2008">2008</option>
                            <option value="2007">2007</option>
                            <option value="2006">2006</option>
                            <option value="2005">2005</option>
                            <option value="2004">2004</option>
                            <option value="2003">2003</option>
                            <option value="2002">2002</option>
                            <option value="2001">2001</option>
                            <option value="2000">2000</option>
                            <option value="1999">1999</option>
                            <option value="1998">1998</option>
                            <option value="1997">1997</option>
                            <option value="1996">1996</option>
                            <option value="1995">1995</option>
                            <option value="1994">1994</option>
                            <option value="1993">1993</option>
                            <option value="1992">1992</option>
                            <option value="1991">1991</option>
                            <option value="1990">1990</option>
                            <option value="1989">1989</option>
                            <option value="1988">1988</option>
                            <option value="1987">1987</option>
                            <option value="1986">1986</option>
                            <option value="1985">1985</option>
                        </select>
                    </div>
                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">工时/小时以内</label>
                    <div class="ui-form-bd">
                        <input type="text"   name="price" id="price"   placeholder="请输入...">
                    </div>
                </div>
                <div class="ui-form-title">买家附言</div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">附言</label>
                    <div class="ui-form-bd">
                        <textarea placeholder="请输入..."   name="remark" id="remark" ></textarea>
                    </div>
                </div>
                <div class="ui-form-title"><i class="icon iconfont">&#xe620;</i>设备要求所在地</div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">省份</label>
                    <div class="ui-form-bd">
                        <input type="text" placeholder="请输入..." />
                    </div>
                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">城市</label>
                    <div class="ui-form-bd">
                        <input type="text" placeholder="请输入..." />
                    </div>
                </div>
                <div class="ui-form-title"><i class="icon iconfont">&#xe600;</i>上传有效期</div>
                <div class="ui-form-mod">
                    <div class="ui-form-bd">
                        <select>
                            <option value="1">30天</option>
                            <option value="2">60天</option>
                            <option value="3">90天</option>
                        </select>
                        <input type="text" placeholder="请输入..." />
                    </div>
                    <div class="ui-form-ft">此信息有效期截止至:2015年5月30日</div>
                </div>
                <div class="field-submit">
                    <input type="button" class="ui-button ui-button-blue" id="jSave" value="保存">
                    <input type="button" class="ui-button ui-button-submit" id="jSubmit" value="提交">
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
<script type="text/javascript" src="${ctx}/wap/js/require.js"></script>
<script type="text/javascript" src="${ctx}/wap/js/app.js"></script>
<script type="text/javascript">
    var ctx = "${ctx}";
    require(['module/want-release']);
</script>
</body></html>
