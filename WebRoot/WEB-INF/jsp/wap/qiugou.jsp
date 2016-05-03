<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="hd">设备要求</div>
            <div class="bd">
                <div class="pro-view-box">
                    <h3>${bean.brandName} ${bean.modelName}求购详细信息</h3>
                    <ul>
                        <li>设备情况: <c:if test="${bean.equipmentCondition == '0'}">
                        新设备
                        </c:if>
                        <c:if test="${bean.equipmentCondition == '1'}">
                        二手
                        </c:if>
                        <c:if test="${bean.equipmentCondition == '2'}">
                        再制造
                        </c:if>
                        <c:if test="${bean.equipmentCondition == '3'}">
                       库存机
                        </c:if>
                        </li>
                        <li>销售方式:
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
                        <li>品牌: ${bean.brandName}</li>
                        <li>型号: ${bean.modelName}</li>
                        <li>年份: ${bean.equipYear}以后</li>
                        <li >工时: ${bean.workTime}以内</li>
                        <li class="w100">设备位置: ${bean.equipmentLocation}</li>
                        <%-- <li  class="w100">出厂编号: ${bean.serialNum}</li> --%>
                        <li class="w100">发布日期:<fmt:formatDate value="${bean.pubTime}" pattern="yyyy/MM/dd" /></li>
                        <li  class="w100">有效期至: ${bean.validTime}</li>
                        <li class="w100 cl-1" style="margin-top:5px;" >价格: <fmt:formatNumber value="${bean.price}" maxFractionDigits="0" />${bean.priceUnit}</li>
                    </ul>
                </div>
                <div class="pro-view-box">
                    <h3>买家附言</h3>
                    <div id = "box">
                    	<p>${bean.remark}</p>
                    </div>
                </div>
            </div>
</body>
</html>