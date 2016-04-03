<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext['request'].contextPath}"/>
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
    <link rel="stylesheet" href="css/module/infor.css?v=1" type="text/css" charset="utf-8">
</head>
<body>
<!--head begin-->
<%@ include file = "header.jsp" %>
<!--head end-->
<div class="page-view">
    <div class="page-view-body">
        <section class="login-info">
            <div class="hd">
                <div class="f-l">
                   <img src="images/dashboard_icon.png" />我的账号
                </div>
                <a href="javascript:;" class="f-r">隐藏</a>
            </div>
            <div class="bd">
                <div class="info clearfix">
                    <img src="images/icon_2.png" class="f-l"  />
                    <div class="info-desc f-l">
                        <h2>${userInfo.userName}</h2>
                        <p>注册时间:<span class="date"> 
                         <fmt:formatDate value="${userInfo.createTime}" pattern="yyyy/MM/dd  HH:mm:ss" />
                         </span>
                         </p>
                    </div>
                    <a href="${ctx}/user/input.htm?id=${userInfo.id}" class="up-info f-l">修改</a>
                </div>
                <div class="pro-nav">
                    <a href="${ctx }/infomation/infoList?status=2">
                        <span class="num">${cnt_sj}</span>
                        <span class="num-desc">上架</span>
                    </a>
                    <a href="${ctx }/infomation/infoList?status=3" >
                        <span class="num">${cnt_ys}</span>
                        <span class="num-desc">已售</span>
                    </a>
                    <a href="${ctx }/infomation/infoList?status=4" >
                        <span class="num">${cnt_xj}</span>
                        <span class="num-desc">下架</span>
                    </a>
                    <a href="${ctx }/infomation/infoList?status=0" >
                        <span class="num">${cnt_cg}</span>
                        <span class="num-desc">草稿</span>
                    </a>
                </div>
                <a href="${ctx}/infomation/sale.htm" class="ui-button ui-button-submit">我要销售</a>
                <a href="${ctx}/infomation/pub.htm" class="ui-button ui-button-blue">我要求购</a>
                <div class="info-url">
                    <a href="${ctx}/wap/companyInfo.htm" class="clearfix">
                        <img src="images/message_comp.png"/><span>公司信息</span>
                        <span class="icon iconfont f-r">&#xe60e;</span>
                    </a>
                    <a href="${ctx }/wap/message.htm" class="clearfix">
                        <img src="images/message_icon.png" /><span >消息</span>
                        <span class="icon iconfont f-r">&#xe60e;</span>
                    </a>
                </div>
            </div>
        </section>
        <section class="search-type-mod" id="jSearch">
            <div class="search-type-hd">
                <span>42000</span>台出售
            </div>
            <div class="search-type-bd">
                <form class="ui-form" id="" action="${ctx}/infomation/search">
                    <div class="ui-form-mod">
                        <label class="ui-form-hd">搜索类型</label>
                        <div class="ui-form-bd">
                            <select>
                                <option value="0">出售</option>
                                <option value="1">租赁</option>
                                <option value="2">求购</option>
                                <option value="3">求租</option>
                            </select>
                        </div>
                    </div>
					<div class="ui-form-mod">
                        <label class="ui-form-hd">产品大类</label>
                        <div class="ui-form-bd">
                            <select class="bigGoodsCatagory"  name="catagoryBigId" id="catagoryBigId">
                </select>
                        </div>
                    </div>
                        
                    <div class="ui-form-mod">
                        <label class="ui-form-hd">产品子类</label>
                        <div class="ui-form-bd">
                            <select class="middleGoodsCatagory"  name="catagoryMidId" id="catagoryMidId" >
                       </select>
                        </div>
                    </div>
                    <div class="ui-form-mod">
                        <label class="ui-form-hd">产品</label>
                        <div class="ui-form-bd">
                            <select class="catagorySmall" name="catagoryId" id="catagoryId" >
                           </select>
                        </div>
                    </div>
                    <div class="ui-form-mod">
                        <label class="ui-form-hd">品牌</label>
                        <div class="ui-form-bd">
                            <select class="brand" name="brandId" id="brandId"  >
    </select>
                        </div>
                    </div>
                    <div class="ui-form-mod">
                        <label class="ui-form-hd">型号</label>
                        <div class="ui-form-bd">
                            <select class="models" name="modelId" id="modelId" >
     </select>
                        </div>
                    </div>
                    <div class="field-submit">
                        <input type="submit" class="ui-button ui-button-submit ui-button-blue" id="jSave" value="搜索">
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
                   <c:forEach var="item" items="${Tops}">
                    <li>
                        <a href="${ctx}/infomation/input.htm?id=${item.id}">
                            <span class="pro-img"><img src="images/blank.gif" class="jImg" data-url="images/img1.jpg" /></span>
                            <span class="name">${item.brandName}${item.modelName}</span>
                            <span class="price"><b>价格：</b>${item.price}元</span>
                        </a>
                    </li>
                   </c:forEach>
                </ul>
            </div>
        </section>
        <section class="collect-mod" id="jCollect">
            <div class="collect-hd">
                <span class="icon iconfont">&#xe603;</span>我的收藏
            </div>
            <div class="collect-bd">
                <ul class="clearfix" style="display: none;">
                    <c:forEach var ="item" items = "${collections}" varStatus="status">
                    <li>
                        <a href="${ctx}/infomation/input.htm?id=${item.id}">
                            <span class="pro-img"><img src="images/blank.gif" class="jImg" data-url="images/img1.jpg" /></span>
                            <span class="name">${item.brandName}${item.modelName}</span>
                            <span class="price"><b>价格：</b>${item.price}元</span>
                        </a>
                    </li>
					</c:forEach>
                </ul>
            </div>
        </section>
    </div>
</div>

<script type="text/html" id="recommendTpl">
     
</script>
<script type="text/html" id="collectTpl">
     
</script>
<%@ include file = "footer.jsp" %>
<script type="text/javascript" src="${ctx}/wap/js/require.js"></script>
<script type="text/javascript" src="${ctx}/wap/js/app.js"></script>
<script type="text/javascript">
var ctx = "${ctx}";
    window.TS = {

    }
    require(['module/infor']);
</script>
</body></html>
