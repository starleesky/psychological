<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="hd">设备要求</div>
            <div class="bd">
                <div class="pro-view-box">
                    <h3>${bean.brandName} ${bean.modelName}求租详细信息</h3>
                    <ul>
                        <li>设备情况: <c:if test="${bean.equipmentCondition == '0'}">
                        全新
                        </c:if>
                        <c:if test="${bean.equipmentCondition == '1'}">
                        二手
                        </c:if>
                        <c:if test="${bean.equipmentCondition == '2'}">
                        再制造
                        </c:if>
                        <c:if test="${bean.equipmentCondition == '3'}">
                       库存
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
                        <li>品&nbsp;&nbsp;牌: ${bean.brandName}</li>
                        <li>型&nbsp;&nbsp;号: ${bean.modelName}</li>
                        <li>年&nbsp;&nbsp;份: ${bean.equipYear}以后</li>
                        <li >工&nbsp;&nbsp;时: ${bean.workTime}以内</li>
                        <li class="w100">设备位置: ${bean.equipmentLocation}</li>
                        <%-- <li  class="w100">出厂编号: ${bean.serialNum}</li> --%>
                        <li class="w100">发布日期: <fmt:formatDate value="${bean.pubTime}" pattern="yyyy/MM/dd" /></li>
                        <li  class="w100">有效期至: ${bean.validTime}</li>
                        <li class="w100 cl-1" style="margin-top:5px;" >价格: <fmt:formatNumber value="${bean.price}" groupingUsed="false" maxFractionDigits="0" />${bean.priceUnit}</li>
                    </ul>
                </div>
                <div class="pro-view-box">
                    <h3>买家附言</h3>
                    <div id = "box">
                    	<p>${bean.remark}</p>
                    </div>
                </div>
            </div>
