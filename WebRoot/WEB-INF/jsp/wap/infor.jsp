<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext['request'].contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>汤森机械网-首页</title>
    <%@ include file = "meta.jsp" %>
    <link rel="stylesheet" href="${ctx}/wap/css/module/infor.css?v=1" type="text/css" charset="utf-8">
    <style>
    .recommended-mod, .collect-mod{padding:0 0 10px 0;}
    .collect-mod .collect-hd .icon{font-size:26px;}
    .recommended-mod .recommended-hd, .recommended-mod .collect-hd, .collect-mod .recommended-hd, .collect-mod .collect-hd{height:60px;line-height:60px;}
    .recommended-mod .recommended-hd .icon,.collect-mod .collect-hd .icon{line-height:60px;}
    .recommended-mod .recommended-hd .icon{line-height:63px;}
    .recommended-mod .recommended-bd ul li, .recommended-mod .collect-bd ul li, .collect-mod .recommended-bd ul li, .collect-mod .collect-bd ul li{margin-right:15px;}
    .recommended-mod .recommended-bd ul li span.price, .recommended-mod .collect-bd ul li span.price, .collect-mod .recommended-bd ul li span.price, .collect-mod .collect-bd ul li span.price{text-align:center;font-size:16px;}
    </style>
</head>
<body>
<!--head begin-->
<%@ include file = "header.jsp" %>
<!--head end-->
<div class="page-view">
    <div class="page-view-body">
    <c:if test="${not empty sessionScope.user.id}">
    <section class="login-info">
            <div class="hd">
                <div class="f-l">
                   <img src="${ctx}/wap/images/dashboard_icon.png" />账号管理
                </div>
                <a href="javascript:;" class="f-r jIsHide">隐藏</a>
                
            </div>
            <div class="bd">
                <div class="info clearfix">
                    <img src="${initParam.imgHost}${userInfo.headIcon}/small" class="f-l"  />
                    <div class="info-desc f-l">
                        <h2>${userInfo.realName}</h2>
                        <p>注册时间:<span class="date"> 
                         <fmt:formatDate value="${userInfo.createTime}" pattern="yyyy/MM/dd  HH:mm:ss" />
                         </span>
                         </p>
                    </div>
                    <a href="${ctx}/user/input/my.htm?id=${userInfo.id}" class="up-info f-l">修改个人信息</a>
                </div>
                <div class="pro-nav">
                    <a href="${ctx }/infomation/infoList/my?status=2">
                        <span class="num">${cnt_sj}</span>
                        <span class="num-desc">上架</span>
                    </a>
                    <a href="${ctx }/infomation/infoList/my?status=3" >
                        <span class="num">${cnt_ys}</span>
                        <span class="num-desc">已售</span>
                    </a>
                    <a href="${ctx }/infomation/infoList/my?status=4" >
                        <span class="num">${cnt_xj}</span>
                        <span class="num-desc">下架</span>
                    </a>
                    <a href="${ctx }/infomation/infoList/my?status=0" >
                        <span class="num">${cnt_cg}</span>
                        <span class="num-desc">草稿</span>
                    </a>
                </div>
                <a href="${ctx}/infomation/sale/my.htm" class="ui-button ui-button-submit">发布销售</a>
                <a href="${ctx}/infomation/pub/my.htm" class="ui-button ui-button-blue">我要求购</a>
                <div class="info-url">
                    <a href="${ctx}/wap/companyInfo/my.htm" class="clearfix">
                        <img src="${ctx}/wap/images/message_comp.png"/><span>公司信息</span>
                        <span class="icon iconfont f-r">&#xe60b;</span>
                    </a>
                    <a href="${ctx }/wap/message/my.htm" class="clearfix">
                        <img src="${ctx}/wap/images/message_icon.png" /><span >消息</span>
                        <span class="icon iconfont f-r">&#xe60b;</span>
                    </a>
                </div>
            </div>
        </section>
    </c:if>
   
        
        <section class="search-type-mod" id="jSearch">
            <div class="search-type-hd">
                <!-- <span>42000</span>台出售 -->
            </div>
            <div class="search-type-bd">
                <form class="ui-form" id="infoSearchForm" action="${ctx}/infomation/search">
                    <div class="ui-form-mod">
                        <label class="ui-form-hd">搜索类型</label>
                        <div class="ui-form-bd">
                            <select id = "sellType" name = "sellType">
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
                        <input type="hidden" name="catagoryBigId"  id="catagoryBigId" value="${info.catagoryBigId}"/>
                    	<input type="hidden" name="catagoryBigName" id="catagoryBigName" value="${info.catagoryBigName}"/>
                        <select class="bigGoodsCatagory"  name="bigGoodsCatagory" id="bigGoodsCatagory" validate="required:true" ><option value="" selected>请选择产品大类</option></select>
                    
                        </div>
                    </div>
                        
                    <div class="ui-form-mod">
                        <label class="ui-form-hd">产品子类</label>
                        <div class="ui-form-bd">
                        <input type="hidden" name="catagoryMidId"  id="catagoryMidId" value="${info.catagoryMidId}"/>
                    	<input type="hidden" name="catagoryMidName" id="catagoryMidName" value="${info.catagoryMidName}"/>
                            <select class="middleGoodsCatagory"  name="middleGoodsCatagory" id="middleGoodsCatagory" ><option>请选择产品子类</option>
                       </select>
                        </div>
                    </div>
                    <div class="ui-form-mod">
                        <label class="ui-form-hd">产品详细</label>
                        <div class="ui-form-bd">
                        <input type="hidden" name="catagoryId" id="catagoryId" value="${info.catagoryId}"/>
                    	<input type="hidden" name="catagoryName" id="catagoryName" value="${info.catagoryName}"/>
                       <select class="smallGoodsCatagory" name="catagorySmall" id="catagorySmall" validate="required:true"><option>请选择产品</option></select>
                           </select>
                        </div>
                    </div>
                    <div class="ui-form-mod">
                        <label class="ui-form-hd">品牌</label>
                        <div class="ui-form-bd">
                        <input type="hidden" name="brandId" id="brandId" value="${info.brandId}"/>
                    	<input type="hidden" name="brandName" id="brandName" value="${info.brandName}"/>
                            <select class="brand" name="brand" id="brand"  ><option>请选择品牌</option></select>
                        </div>
                    </div>
                    <div class="ui-form-mod">
                        <label class="ui-form-hd">型号</label>
                        <div class="ui-form-bd">
                           <input type="hidden" name="modelId" id="modelId" value="${info.modelId}"/>
                    	<input type="hidden" name="modelName" id="modelName" value="${info.modelName}"/>
                       		<select class="models"  name="models" id="models" validate="required:true"><option>请选择型号</option></select>
                        </div>
                    </div>
                    <div class="field-submit">
                        <a href="javascript:;" class="ui-button ui-button-submit ui-button-blue" id="jSave">搜  索</a>
                    </div>
                </form>
            </div>
        </section>
        <section class="recommended-mod" id="jRecommend">
            <div class="recommended-hd">
                <span class="icon iconfont">&#xe604;</span>热门推荐
            </div>
            <div class="recommended-bd">
                <ul class="clearfix jProList">
                   <c:forEach var="item" items="${Tops}">
                    <li>
                        <a href="${ctx}/infomation/input.htm?id=${item.id}">
                            <span class="pro-img"><img src="${initParam.imgHost}${item.imgUrl}/small" class="jImg" data-url="${initParam.imgHost}${item.imgUrl}" /></span>
                            <span class="name">${item.brandName}${item.modelName}</span>
                            <span class="price"><fmt:formatNumber value="${item.price}" maxFractionDigits="0" />元</span>
                        </a>
                    </li>
                   </c:forEach>
                </ul>
            </div>
        </section>
        

        <section class="collect-mod" id="jCollect">
            <div class="collect-hd">
                <span class="icon iconfont">&#xe60c;</span>最新发布
            </div>
            <div class="collect-bd">
                <ul class="clearfix jProList" >
                    <c:forEach var="item" items="${News}">
                        <li>
                        <a href="${ctx}/infomation/input.htm?id=${item.id}">
                        <span class="pro-img"><img src="${initParam.imgHost}${item.imgUrl}/small" class="jImg" data-url="${initParam.imgHost}${item.imgUrl}" /></span>
                        <span class="name">${item.brandName}${item.modelName}</span>
                        <span class="price"><fmt:formatNumber value="${item.price}" maxFractionDigits="0" />元</span>
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
