<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext['request'].contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>汤森机械网-发布销售</title>
    <%@ include file = "meta.jsp" %>
    <link rel="stylesheet" href=${ctx}/wap/css/module/want.css?v=1" type="text/css" charset="utf-8">
    <style>
    .mod-want-buy{margin-top:0;}
    .ui-form .upload-img .up-img-list .up-img-box{width:100px;height:100px;margin:0 5px;}
    .ui-form .upload-img .up-img-list .up-img-box img{width:100px;height:100px;max-width:none;max-height:none;}
    </style>
</head>
<body>
<!--head begin-->
<%@ include file="header.jsp" %>
<!--head end-->
<div class="page-view">
    <div class="page-view-body">
        <section class="mod-info">
            <div class="hd">
                <h3 class="title">
                    <c:if test="${type==1}">发布求购</c:if>
                    <c:if test="${type==2}"> 发布销售</c:if>
                </h3>
            </div>

        </section>
        <section class="mod-want-buy">
            <form class="ui-form" id="informationFrom">
                <%--<div class="ui-form-title"><img src="${ctx}/wap/images/photo_icon.png"/> 上传图片</div>--%>
                <%--<div class="ui-form-mod upload-img">--%>
                    <%--<div class="img-sp">--%>
                        <%--<input type="file" name="file[]" value="aaa" />--%>
                        <%--<img src="${ctx}/wap/images/camera_load_icon.png" />--%>
                        <%--<span>照片</span>--%>
                    <%--</div>--%>
                    <%--<div class="img-sp">--%>
                        <%--<input type="file" name="file[]" value="aaa" />--%>
                        <%--<img src="${ctx}/wap/images/image_load_icon.png" />--%>
                        <%--<span>上传图片</span>--%>
                    <%--</div>--%>

                <%--</div>--%>
                <c:if test="${type==2}">
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
                <div class="ui-form-mod">
                    <div class="ui-form-bd more-select">
                        <select class="bigGoodsCatagory"  name="catagoryBig" id="catagoryBig" validate="required:true" ><option>请选择产品大类</option></select>
                        <select class="middleGoodsCatagory" name="catagoryMid" id="catagoryMid" validate="required:true"><option>请选择产品子类</option></select>
                        <select class="smallGoodsCatagory" name="catagorySmall" id="catagorySmall" validate="required:true"><option>请选择产品</option></select>
                    </div>
                </div>
                <div class="ui-form-title">选择品牌和 & 型号</div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">品牌和 & 型号</label>
                    <div class="ui-form-bd more-select">
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
                        <input type="text" name="newBrand" placeholder="请输入...">
                    </div>
                </div>
                <div class="ui-form-mod isHide desc-child">
                    <label class="ui-form-hd">添加型号</label>
                    <div class="ui-form-bd">
                        <input type="text" name="newModels" placeholder="请输入...">
                    </div>
                </div>
                <div class="ui-form-title">设备详情</div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">销售方式</label>
                    <div class="ui-form-bd">
                        <select name="sellType">
                            <c:if test="${type==1}">
                                <option value="2">求购</option>
                                <option value="3">求租</option>
                            </c:if>
                            <c:if test="${type==2}">
                                <option value="0">出售</option>
                                <option value="1">租赁</option>
                            </c:if>
                        </select>
                    </div>
                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">设备情况</label>
                    <div class="ui-form-bd">
                        <select name="equipmentCondition">
                            <option value="0">全新</option>
                            <option value="1">二手</option>
                            <option value="2">再制造</option>
                            <option value="3">库存</option>
                        </select>
                    </div>
                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">年份</label>
                    <div class="ui-form-bd">
                        <select name="equipYear">
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
                    <label class="ui-form-hd">工时</label>
                    <div class="ui-form-bd">
                        <input type="text"   name="workTime" id="workTime"   placeholder="请输入...">
                    </div>
                </div>
                 <c:if test="${type==2}">
                <div class="ui-form-mod">
                    <label class="ui-form-hd">出厂编码</label>
                    <div class="ui-form-bd">
                        <input type="text"   name="serialNum" id="serialNum"   placeholder="请输入...">
                    </div>
                </div> 
                </c:if>
                    <div class="ui-form-mod">
                        <label class="ui-form-hd">价格</label>
                       
                        <div class="ui-form-bd">
                            <input type="text"   name="price" id="price"   placeholder="请输入价格，只能为整数">
                        </div>
                    </div>
                    <div class="ui-form-mod">
                        <label class="ui-form-hd">单位</label>
                        <div class="ui-form-bd">
                             <select name="priceUnitSel">
	                            <option value="元">元</option>
	                        </select>
                        </div>
                    </div>

                <div class="ui-form-title">卖家附言</div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">附言</label>
                    <div class="ui-form-bd">
                        <textarea placeholder="请输入...（限定140个字符）"   name="remark" id="remark" ></textarea>
                    </div>
                </div>
                <div class="ui-form-title"><i class="icon iconfont">&#xe60e;</i>设备位置</div>
					 <input type="hidden" name="equipmentLocation" id="equipmentLocation" value="${info.equipmentLocation}" />
                <div class="ui-form-mod">
                    <label class="ui-form-hd">省份</label>
                    <div class="ui-form-bd">
                        <select class="regionProvice"  name="regionProvice" id="regionProvice" validate="required:true" ><option>请选择省份</option></select>
                	</div>
                	<label class="ui-form-hd">城市</label>
	                <div class="ui-form-bd">
	                    <select class="regionCity"  name="regionCity" id="regionCity" validate="required:true" ><option>请选择城市</option></select>                    </div>
	                    </div>
					<div class="ui-form-title"><i class="icon iconfont">&#xe60d;</i>上传有效期</div>
                <div class="ui-form-mod">
                    <div class="ui-form-bd">
                        <select id="validTime" name="validTime" >
                            <option value="30">30天</option>
                            <option value="60">60天</option>
                            <option value="90">90天</option>
                        </select>
                        <%--<input type="text" name="validTime1" placeholder="请输入..." />--%>
                    </div>

                </div>
                <div class="ui-form-mod">
                    <div class="ui-form-bd" style="text-align:center;">有效期至:<span class="jDate"></span></div>
                </div>
                <div class="field-submit">
                    <a href="javascript:;" class="ui-button ui-button-blue" id="jSave">保存</a>
                    <a href="javascript:;"  class="ui-button ui-button-submit" id="jSubmit">提交</a>
                </div>
            </form>
        </section>
    </div>
</div>

<script type="text/javascript" src="${ctx}/wap/js/require.js"></script>
<script type="text/javascript" src="${ctx}/wap/js/app.js"></script>
<script type="text/javascript">
    var ctx = "${ctx}";
    require(['module/want-release']);
</script>
</body></html>
