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
    <style>
    .img-box .bd .img-list{margin-top:10px;}
    .pro-view-mod .bd h3{ font-size:20px;}
    .pro-view-mod .bd ul li{font-size:18px;overflow:hidden;text-overflow:ellipsis;height:30px;line-height:30px;}
    .pro-view-mod .bd ul li.cl-1{font-size:20px;}
    .pro-view-mod .bd .pro-view-box .pro-img{width:200px;height:100px;line-height:100px;}
    .pro-view-mod .bd .pro-view-box .pro-img img{max-width:200px;max-height:100px;}
    .img-big-box img{left: 50%;top: 50%;transform: translate(-50%,-50%);-moz-transform:translate(-50%,-50%) ;-webkit-transform:translate(-50%,-50%) ; -o-transform:translate(-50%,-50%) ;-ms-transform: translate(-50%,-50%); max-height: 95%;max-width: 95%;}
    </style>
</head>
<body>
<!--head begin-->
<%@ include file = "header.jsp" %>  
<!--head end-->
<div class="page-view">
    <div class="page-view-body">
        <section class="pro-view-mod img-box">
            <div class="hd">
                <a href="${ctx}/infomation/input?id=${bean.id}&page=prev" class="prev-btn"><i class="icon iconfont">&#xe603;</i></a>
                <div class="img-title">
                    <h2 class="pro-name">${bean.brandName}${bean.modelName}</h2>
                    <input type="hidden" id="id" value="${bean.id}" >
                    <p class="pro-price"><fmt:formatNumber value="${bean.price}" maxFractionDigits="0" />${bean.priceUnit}</p>
             			<p class="pro-num">汤森信息编号: TS${bean.id}</p>

                </div>
                <a href="${ctx}/infomation/input?id=${bean.id}&page=next" class="next-btn"><i class="icon iconfont">&#xe60b;</i></a>
            </div>
            <div class="bd" id="jImgBox">
                <div class="big-img">
                    <img src="${initParam.imgHost}${firstImg}/figure" />
                </div>
                <div class="img-list">
                	<c:forEach items="${listAttch }" var="attach">
	                	<a href="javascript:;" data-url="${initParam.imgHost}${attach.attchUrl}/figure">
	                        <img src="${initParam.imgHost}${attach.attchUrl}/small" />
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
            <a href="javascript:;" class="jClose icon iconfont">&#xe606;</a>
            <img src="images/img_1.jpg" />
        </section>
        <section class="page-view-btn">
            <a href="javascript:;" class="ui-button ui-button-blue">分享</a>
            <a href="javascript:;" class="ui-button ui-button-submit" id="collection" >收藏</a>
        </section>
        <section class="pro-set-info pro-view-mod">
        <c:choose>  
		   <c:when test="${bean.sellType == '0'}">
		       <%@ include file = "chushou.jsp" %> 
		   </c:when>  
		   <c:when test="${bean.sellType == '1'}">
		       <%@ include file = "chuzhu.jsp" %> 
		   </c:when>  
		   <c:when test="${bean.sellType == '2'}">
		       <%@ include file = "qiugou.jsp" %> 
		   </c:when>  
		   <c:when test="${bean.sellType == '3'}">
		       <%@ include file = "qiuzhu.jsp" %> 
		   </c:when>  
		</c:choose> 
        </section>
        <section class="buy-man-info pro-view-mod">
            <div class="hd">卖家信息</div>
            <div class="bd">
                        <c:if test="${empty company}">
                <div class="pro-view-box">
       					<c:if test="${not empty sellUser.headIcon}">
                    <div class="pro-img">
                        	<img src="${initParam.imgHost}${sellUser.headIcon}/small" />
                    </div>
                        </c:if>
                    <h3>${sellUser.realName}</h3>
                    <ul>
                        <li class="w100">注册时间：
               			<fmt:formatDate value="${sellUser.createTime}" pattern="yyyy/MM/dd  HH:mm:ss" />
                        </li>
                        <li class="w100">电话：<a href="tel:${sellUser.mobile}">${sellUser.mobile}</a></li>
                        <li class="w100">座机：<a href="tel:${sellUser.telephone}">${sellUser.telephone}</a></li>
                        <li class="w100">地址：${sellUser.provinceName }${sellUser.cityName }</li>
                        <li>经营范围:
                        <c:if test="${sellUser.businessScope == '1'}">工程机械</c:if>
                        <c:if test="${sellUser.businessScope == '2'}">农业机械</c:if>
                        <c:if test="${sellUser.businessScope == '3'}">矿山机械</c:if>
                        <c:if test="${sellUser.businessScope == '4'}">林业机械</c:if>
                        <c:if test="${sellUser.businessScope == '5'}">运输机械</c:if>
                        </li>
                        <li>经营性质：
                             <c:if test="${sellUser.businessNature == '0'}">出售</c:if>
                             <c:if test="${sellUser.businessNature == '1'}">租赁</c:if>
                             <c:if test="${sellUser.businessNature == '2'}">求购</c:if>
                             <c:if test="${sellUser.businessNature == '3'}">求租</c:if>
                        </li>
                    </ul>
                </div>
				      </c:if>         
                <c:if test="${not empty company}">
                 <div class="pro-view-box">
                	<div class="pro-img">
						<c:if test="${not empty company.createBy}">
                		<img src="${initParam.imgHost}${company.createBy}/small" class="f-l" width="100" height="100"   />
						</c:if> 
                    </div>
			         <ul>
	                    <li class="w100">所属公司：${company.companyName}</li>
                        <li class="w100">联系电话：<a href="tel:${sellUser.mobile}">${sellUser.mobile}</a></li>
                        <li class="w100">公司地址：${company.address}</li>
                     </ul>
				</div>
                 <div class="pro-view-box">
                    <h3>公司简介</h3>
                   <p>${company.introduction}
                </div>
                </c:if>
            </div>
        </section>
         <section class="page-view-btn">
            <a href="tel:${sellUser.mobile}" class="ui-button ui-button-submit">联系卖家</a>
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
