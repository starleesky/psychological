<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext['request'].contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>汤森机械网-发布销售</title>
    <%@ include file = "meta.jsp" %>
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
                <h3 class="title">发布销售</h3>
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
                    <label class="ui-form-hd">求购方式</label>
                    <div class="ui-form-bd">
                        <select name="sellType"><option value="2">求购</option><option value="3">求租</option></select>
                    </div>
                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">设备情况</label>
                    <div class="ui-form-bd">
                        <select name="equipmentCondition">
                            <option value="0">新设备</option>
                            <option value="1">二手设备</option>
                            <option value="2">再制造</option>
                            <option value="3">库存机</option>
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
                <div class="ui-form-title"><i class="icon iconfont">&#xe60e;</i>设备要求所在地</div>
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
                <div class="ui-form-title"><i class="icon iconfont">&#xe60d;</i>上传有效期</div>
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
                    <a href="javascript:;" class="ui-button ui-button-blue" id="jSave">保存</a>
                    <a href="javascript:;"  class="ui-button ui-button-submit" id="jSubmit">提交</a>
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
    require(['module/want-release']);
</script>
</body></html>
