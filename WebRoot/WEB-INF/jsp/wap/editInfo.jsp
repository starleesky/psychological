<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
cn.com.tsjx.infomation.entity.Infomation infomation = (cn.com.tsjx.infomation.entity.Infomation)request.getAttribute("info");
String _brandName = infomation.getBrandName();
String _modelName = infomation.getModelName();
%>
<c:set var="ctx" value="${pageContext['request'].contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>汤森机械网-草稿箱</title>
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
                <a class="bd-l" href="${ctx }/infomation/infoList?status=2" status="2">
                    <span class="num">${cnt_sj }</span>
                    <span class="num-desc">上架</span>
                </a>
                <a class="bd-m" href="${ctx }/infomation/infoList?status=3" status="3">
                    <span class="num">${cnt_ys }</span>
                    <span class="num-desc">已售</span>
                </a>
                <a class="bd-m" href="${ctx }/infomation/infoList?status=4" status="4">
                    <span class="num">${cnt_xj }</span>
                    <span class="num-desc">下架</span>
                </a>
                <a class="bd-r" href="${ctx }/infomation/infoList?status=0" status="4">
                    <span class="num">${cnt_cg }</span>
                    <span class="num-desc">草稿</span>
                </a>
            </div>
        </section>
        <section class="mod-want-buy">
            <form class="ui-form" id="informationFrom">
               <%--  <div class="ui-form-title"><img src="${ctx}/wap/images/photo_icon.png"/> 上传图片</div>
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

                </div> --%>
                <input type="hidden" id="id" name="id" value="${info.id}">
                <c:if test="${info.sellType == '0' || info.sellType == '1'}">
	                <div class="ui-form-title"><img src="${ctx}/wap/images/photo_icon.png"/> 上传图片</div>
                	<div class="ui-form-mod upload-img">
                    <a href="javascript:;" node-type="uploadButton">
                        <div class="img-sp" >
                            <img src="${ctx}/wap/images/camera_load_icon.png" />
                            <span>照片</span>
                        </div>
                        <div class="img-sp">
                            <img src="${ctx}/wap/images/image_load_icon.png" />
                            <span>上传图片</span>
                        </div>
                    </a>
                </div>
                </c:if>
                <div class="ui-form-title">选择产品类别</div>
                <input type="hidden" id="_bigCataId" value="${info.catagoryBigId }">
                <input type="hidden" id="_midCataId" value="${info.catagoryMidId }">
                <input type="hidden" id="_CataId" value="${info.catagoryId }">
                <input type="hidden" id="_brandId" value="${info.brandId }">
                <input type="hidden" id="_modelId" value="${info.modelId }">
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
                        <input type="text" name="newBrand" value="" placeholder="请输入...">
                    </div>
                </div>
                <div class="ui-form-mod isHide desc-child">
                    <label class="ui-form-hd">添加型号</label>
                    <div class="ui-form-bd">
                        <input type="text" name="newModels" value="" placeholder="请输入...">
                    </div>
                </div>
                <div class="ui-form-title">设备详情</div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">求购方式</label>
                    <div class="ui-form-bd">
                    	<input type="hidden" name="sellType" id="sellType" value="${info.sellType}"/>
                        <select name="sellTypeSel" onchange="sellType.value=this.value">
                        	<c:choose>
                        		<c:when test="${info.sellType == '0' || info.sellType == '1'}">
                        			<option value="0">出售</option>
	                        		<option value="1">租赁</option>
                        		</c:when>
                        		<c:otherwise>
	                        		<option value="2">求购</option>
	                        		<option value="3">求租</option>
                        		</c:otherwise>
                        	</c:choose>
                        </select>
                    </div>
                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">设备情况</label>
                    <div class="ui-form-bd">
                    	<input type="hidden" name="equipmentCondition" id="equipmentCondition" value="${info.equipmentCondition}"/>
                        <select name="equipmentConditionSel" onchange="equipmentCondition.value=this.value">
                            <option value="0">新设备</option>
                            <option value="1">二手设备</option>
                            <option value="2">再制造</option>
                        </select>
                    </div>
                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">手续资料</label>
                    <div class="ui-form-bd">
                    	<input type="hidden" name="procedures" id="procedures" value="${info.procedures}"/>
                        <select name="proceduresSel"  onchange="procedures.value=this.value">
                            <option value="0">手续齐全</option>
                            <option value="1">无手续</option>
                            <option value="2">有无手续均可</option>
                        </select>
                    </div>
                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">设备来源</label>
                    <div class="ui-form-bd">
                    	<input type="hidden" name="src" id="src" value="${info.src}"/>
                        <select name="srcSel">
                            <option value="0">个人</option>
                            <option value="1">单位</option>
                            <option value="2">抵押</option>
                            <option value="3">法务</option>
                        </select>
                    </div>
                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">年份/以后</label>
                    <div class="ui-form-bd">
                    	<input type="hidden" name="equipYear" id="equipYear" value="${info.equipYear}"/>
                        <select name="equipYearSel">
                        	<option value="2016">2016</option>
                            <option value="2015">2015</option>
                            <option value="2014">2014</option>
                            <option value="2013">2013</option>
                            <option value="2012">2012</option>
                        </select>
                    </div>
                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">工时/小时以内</label>
                    <div class="ui-form-bd">
                        <input type="text"   name="price" id="price"  value="${info.price }" placeholder="请输入...">
                    </div>
                </div>
                <div class="ui-form-title">买家附言</div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">附言</label>
                    <div class="ui-form-bd">
                        <textarea placeholder="请输入..."   name="remark" id="remark" value="${info.remark }"></textarea>
                    </div>
                </div>
                <div class="ui-form-title"><i class="icon iconfont">&#xe620;</i>设备要求所在地</div>
                <input type="hidden" name="equipmentLocation" id="equipmentLocation" value="${info.equipmentLocation}" />
                <div class="ui-form-mod">
                    <label class="ui-form-hd">省份</label>
                    <div class="ui-form-bd">
                        <select class="regionProvice"  name="regionProvice" id="regionProvice" validate="required:true" ><option>请选择省份</option></select>
                </div>
    			<div class="ui-form-mod">
                	<label class="ui-form-hd">城市</label>
	                <div class="ui-form-bd">
	                    <select class="regionCity"  name="regionCity" id="regionCity" validate="required:true" ><option>请选择城市</option></select>                    </div>
	                    </div>
	                </div>
                	<div class="ui-form-title"><i class="icon iconfont">&#xe600;</i>上传有效期</div>
                	<div class="ui-form-mod">
	                    <div class="ui-form-bd">
	                        <select >
	                            <option value="1">30天</option>
	                            <option value="2">60天</option>
	                            <option value="3">90天</option>
	                        </select>
	                        <input type="text" name="validTime" placeholder="请输入..." />
	                    </div>
                    <div class="ui-form-ft">此信息有效期截止至:2016年5月30日</div>
                </div>
                <div class="field-submit">
                    <input type="button" class="ui-button ui-button-blue" id="jSave" value="保存">
                    <input type="button" class="ui-button ui-button-submit" id="jSubmit" value="提交">
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
    require(['module/editInfo']);
</script>
</body></html>
