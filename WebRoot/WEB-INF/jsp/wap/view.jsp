<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext['request'].contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>汤森机械网-详情页面</title>
    <%@ include file = "meta.jsp" %>
    <link rel="stylesheet" href="${ctx}/wap/css/module/view.css?v=1" type="text/css" charset="utf-8">
</head>
<body>
<!--head begin-->
<%@ include file = "header.jsp" %>  
<!--head end-->
<div class="page-view">
    <div class="page-view-body">
        <section class="pro-view-mod img-box">
            <div class="hd">
                <a href="./index.html" class="prev-btn"><i class="icon iconfont">&#xe60d;</i></a>
                <div class="img-title">
                    <h2 class="pro-name">${bean.brandName}${bean.modelName}</h2>
                    <input type="hidden" id="id" value="${bean.id}" >
                    <p class="pro-price"><fmt:formatNumber value="${bean.price }" maxFractionDigits="0" />元</p>
                </div>
                <a href="./index.html" class="next-btn"><i class="icon iconfont">&#xe60e;</i></a>
            </div>
            <div class="bd" id="jImgBox">
                <div class="big-img">
                    <img src="${initParam.imgHost}${firstImg}" />
                </div>
                <div class="img-list">
                	<c:forEach items="${listAttch }" var="attach">
	                	<a href="javascript:;" data-url="${initParam.imgHost}${attach.attchUrl}">
	                        <img src="${initParam.imgHost}${attach.attchUrl}" />
	                    </a>
                	</c:forEach>
                   <!--  <a href="javascript:;" data-url="images/img_1.jpg">
                        <img src="images/img_1.jpg" />
                    </a>
                    <a href="javascript:;" data-url="images/img_2.jpg">
                        <img src="images/img_2.jpg" />
                    </a> -->
                </div>
            </div>
        </section>
        <section class="img-big-box">
            <a href="javascript:;" class="jClose icon iconfont">&#xe622;</a>
            <img src="images/img_1.jpg" />
        </section>
        <section class="page-view-btn">
          <input type="button" class="ui-button ui-button-submit" id="collection"  value="收藏">
                    
        </section>
        <section class="pro-set-info pro-view-mod">
            <div class="hd">设备要求</div>
            <div class="bd">
                <div class="pro-view-box">
                    <h3>${bean.brandName}${bean.modelName}</h3>
                    <ul>
                        <li>类别: ${bean.catagoryName}</li>
                        <li>品牌: ${bean.brandName}</li>
                        <li>求购类型:
                        <c:if test="${bean.sellType == '0'}">
                        	出售
                        </c:if>
                        <c:if test="${bean.sellType == '1'}">
                        	租赁
                        </c:if>
                        <c:if test="${bean.sellType == '2'}">
                        	求购
                        </c:if>
                        <c:if test="${bean.sellType == '3'}">
                        	求租
                        </c:if>
                        </li>
	
                        <li>设备类型: 
                        <c:if test="${bean.equipmentCondition == '0'}">
                        新设备
                        </c:if>
                        <c:if test="${bean.equipmentCondition == '1'}">
                        二手设备
                        </c:if>
                        <c:if test="${bean.equipmentCondition == '2'}">
                        再制造
                        </c:if>
                        </li>
                        <li>手续资料要求: 
                        <c:if test="${bean.procedures == '0'}">
                        手续齐全
                        </c:if>
                        <c:if test="${bean.procedures == '1'}">
                        无手续
                        </c:if>
                        <c:if test="${bean.procedures == '2'}">
                        有无手续均可
                        </c:if>
                        </li>
                        <li>设备来源要求: 
                        <c:if test="${bean.src == '0'}">个人
                        </c:if>
                        <c:if test="${bean.src == '1'}">单位
                        </c:if>
                        <c:if test="${bean.src == '2'}">抵押
                        </c:if>
                        <c:if test="${bean.src == '3'}">法务
                        </c:if>
                        </li>
                        <li>发布日期:<fmt:formatDate value="${bean.pubTime}" pattern="yyyy/MM/dd" /></li>
                        <li>有效期至: ${bean.validTime}</li>
                        <li>生产年份要求: ${bean.equipYear}年以后</li>
                        <li class="w100">工作小时要求: ${bean.workTime}小时以内</li>
                        <li class="w100">设备位置要求: ${bean.equipmentLocation}</li>
                        <li class="w100 cl-1">期望价格: <fmt:formatNumber value="${bean.price}" maxFractionDigits="0" />元左右</li>
                    </ul>
                </div>
                <div class="pro-view-box">
                    <h3>买家附言</h3>
                    <p>${bean.remark}</p>
                </div>
            </div>
        </section>
        <section class="buy-man-info pro-view-mod">
            <div class="hd">卖家信息</div>
            <div class="bd">
                <div class="pro-view-box">
                    <div class="pro-img"><img src="${initParam.imgHost}${user.headIcon}" /></div>
                    <h3>${user.userName}</h3>
                    <ul>
                        <li class="w100">注册时间：
               <fmt:formatDate value="${user.createTime}" pattern="yyyy/MM/dd  HH:mm:ss" />
                        </li>
                        <liclass="w100">电话：<a href="tel:${user.mobile}">${user.mobile}</a></li>
                        <li class="w100">座机：<a href="tel:${user.telephone}">${user.telephone}</a></li>
                        <li class="w100">Q  Q：${user.qq}</li>
                        <li>经营范围:
                        <c:if test="${user.businessScope == '1'}">工程机械</c:if>
                        <c:if test="${user.businessScope == '2'}">农业机械</c:if>
                        <c:if test="${user.businessScope == '3'}">矿山机械</c:if>
                        <c:if test="${user.businessScope == '4'}">林业机械</c:if>
                        <c:if test="${user.businessScope == '5'}">运输机械</c:if>
                        </li>
                        <li>经营性质：
                             <c:if test="${user.businessNature == '0'}">出售</c:if>
                             <c:if test="${user.businessNature == '1'}">租赁</c:if>
                             <c:if test="${user.businessNature == '2'}">求购</c:if>
                             <c:if test="${user.businessNature == '3'}">求租</c:if>
                        </li>
                        <c:if test="${not empty company}">
                        <li class="w100">所属公司：${company.companyName}</li>
                        <li class="w100">公司地址：${company.address}</li>
                        </c:if>
                    </ul>
                </div>
                <div class="pro-view-box">
                    <h3>库存情况</h3>
                    <div class="pro-view-box-num">
                        <p class="bg-green">现有库存：${bean.stockCount}台</p>
                        <p class="bg-gray">已售数量：${sellCount}台</p>
                    </div>
                </div>
                <c:if test="${not empty company}">
                 <div class="pro-view-box">
                    <h3>公司简介</h3>
                   <p>${company.introduction}
                </div>
                </c:if>
               
            </div>
        </section>
         <section class="page-view-btn">
            <a href="tel:${user.mobile}" class="ui-button ui-button-submit">联系卖家</a>
            <a href="javascript:;" class="ui-button ui-button-blue">库存情况</a>
             <a href="javascript:;" class="ui-button ui-button-gray jCommentInfo">客户评价</a>
        </section>

    </div>
</div>
<%@ include file="footer.jsp" %>
<script type="text/javascript" src="${ctx}/wap/js/require.js"></script>
<script type="text/javascript" src="${ctx}/wap/js/app.js"></script>
<script type="text/javascript">
var ctx = "${ctx}";
    require(['module/view']);
</script>
</body></html>
