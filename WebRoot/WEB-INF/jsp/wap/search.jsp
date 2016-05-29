<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext['request'].contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>汤森机械网-搜索&高级搜索</title>
    <%@ include file="meta.jsp" %>
    <link rel="stylesheet" href="${ctx }/wap/css/module/search.css?v=1" type="text/css" charset="utf-8">
    <style>
        .pro-list .pro-box .pro-info {
            width: 63%;
        }

        .ui-form-mod .ui-small-txt input, .ui-form-mod .ui-small-txt span {
            display: inline-block;
            width: 45%;
        }

        .ui-form-mod .ui-small-txt span {
            width: 6%;
            text-align: center;
            color: #fff;
        }

        .pro-list .pro-box .pro-img img {
            width: 120px;
            height: 120px;
        }

        @media only screen and (max-device-width: 1024px) and (min-device-width: 768px) {
            .pro-list .pro-box {
                width: 46%;
            }
        }

        @media only screen and (min-device-width: 320px) {
            .pro-list .pro-box .pro-info {
                width: 56%;
            }
        }
    </style>
</head>
<body>
<!--head begin-->
<%@ include file="header.jsp" %>
<!--head end-->
<div class="page-view">
    <div class="page-view-body">
        <section class="search-title">
            <a href="javascript:;" class="ui-button   ui-button-white op-button-higSearch">高级搜索</a>
            <select name="orderSel">
                <option value="price_h">价格从高到低</option>
                <option value="price_l">价格从低到高</option>
                <option value="pub_h">发布时间</option>
            </select>
        </section>
        <c:if test="${empty sessionScope.user.id}">

            <section class="contact-mod">
                <div class="no-login"><a href="${ctx}/login.htm">登录后可以收藏</a></div>
            </section>
        </c:if>
        <section class="search-mod">
            <div class="search-hd"></div>
            <div class="pro-list search-bd">
                <ul class="jPage">
                    <div data-page="1">
                        <c:forEach items="${pager}" var="info">
                            <li class="pro-box">
                                <a href="${ctx}/infomation/input.htm?id=${info.id}" class="pro-img">
                                    <img src="${initParam.imgHost}${info.imgUrl}/small" class="jImg"
                                         data-url="${initParam.imgHost}${info.imgUrl}"/>
                                </a>
                                <div class="pro-info">
                                    <a href="#" class="pro-title">${info.brandName }${info.modelName }</a>
                                    <strong class="pro-price"><fmt:formatNumber value="${info.price }"
                                                                                groupingUsed="false"
                                                                                maxFractionDigits="0"/>${info.priceUnit }</strong>
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
                <input type="hidden" name="order" id="order" value="${order}">
                <div class="ui-form-mod">
                    <label class="ui-form-hd">销售方式</label>
                    <div class="ui-form-bd">
                        <input type="hidden" name="sellType" id="sellType" value="${info.sellType}"/>
                        <select name="sellTypeSel" onchange="sellType.value=this.value">
                            <option value="" selected></option>
                            <option value="0">出售</option>
                            <option value="1">租赁</option>
                            <option value="2">求购</option>
                            <option value="3">求租</option>

                        </select>
                    </div>
                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">设备地点</label>
                    <div class="ui-form-bd">
                        <input type="hidden" name="equipmentLocation" id="equipmentLocation"
                               value="${info.equipmentLocation}"/>
                        <select class="regionProvice" name="equipmentLocationSel" id="equipmentLocationSel"
                                validate="required:true">
                            <option>请选择省份</option>
                        </select>
                    </div>
                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">产品大类</label>
                    <div class="ui-form-bd">
                        <input type="hidden" name="catagoryBigId" id="catagoryBigId" value="${info.catagoryBigId}"/>
                        <input type="hidden" name="catagoryBigName" id="catagoryBigName"
                               value="${info.catagoryBigName}"/>
                        <select class="bigGoodsCatagory" name="catagoryBig" id="catagoryBig" validate="required:true">
                            <option value="" selected>请选择产品大类</option>
                        </select>
                    </div>
                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">产品子类</label>
                    <div class="ui-form-bd">
                        <input type="hidden" name="catagoryMidId" id="catagoryMidId" value="${info.catagoryMidId}"/>
                        <input type="hidden" name="catagoryMidName" id="catagoryMidName"
                               value="${info.catagoryMidName}"/>
                        <select class="middleGoodsCatagory" name="catagoryMid" id="catagoryMid"
                                validate="required:true">
                            <option>请选择产品子类</option>
                        </select>
                    </div>
                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">全部产品</label>
                    <div class="ui-form-bd">
                        <input type="hidden" name="catagoryId" id="catagoryId" value="${info.catagoryId}"/>
                        <input type="hidden" name="catagoryName" id="catagoryName" value="${info.catagoryName}"/>
                        <select class="smallGoodsCatagory" name="catagorySmall" id="catagorySmall"
                                validate="required:true">
                            <option>请选择产品</option>
                        </select>
                    </div>
                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">品牌</label>
                    <div class="ui-form-bd">
                        <input type="hidden" name="brandId" id="brandId" value="${info.brandId}"/>
                        <input type="hidden" name="brandName" id="brandName" value="${info.brandName}"/>
                        <select class="brand" name="brand" id="brand" validate="required:true">
                            <option>请选择品牌</option>
                        </select>
                    </div>
                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">型号</label>
                    <div class="ui-form-bd">
                        <input type="hidden" name="modelId" id="modelId" value="${info.modelId}"/>
                        <input type="hidden" name="modelName" id="modelName" value="${info.modelName}"/>
                        <select class="models" name="models" id="models" validate="required:true">
                            <option>请选择型号</option>
                        </select>
                    </div>
                </div>

                <div class="ui-form-mod">
                    <label class="ui-form-hd">设备情况</label>
                    <div class="ui-form-bd">
                        <input type="hidden" name="equipmentCondition" id="equipmentCondition"
                               value="${info.equipmentCondition}"/>
                        <select name="equipmentConditionSel" onchange="equipmentCondition.value=this.value">
                            <option value="" selected></option>
                            <option value="0">全新</option>
                            <option value="1">二手</option>
                            <option value="2">再制造</option>
                            <option value="3">库存</option>
                        </select>
                    </div>
                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">年份</label>
                    <div class="ui-form-bd ui-small-txt">
                        <input type="text" placeholder="请输入..."/>
                        <span>TO</span>
                        <input type="text" placeholder="请输入..."/>
                    </div>
                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">工时</label>
                    <div class="ui-form-bd ui-small-txt">
                        <input type="text" placeholder="请输入..."/>
                        <span>TO</span>
                        <input type="text" placeholder="请输入..."/>
                    </div>
                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">价格</label>
                    <div class="ui-form-bd ui-small-txt">
                        <input type="text" placeholder="请输入..."/>
                        <span>TO</span>
                        <input type="text" placeholder="请输入..."/>
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
    require(['module/search']);
</script>
</body>
</html>
