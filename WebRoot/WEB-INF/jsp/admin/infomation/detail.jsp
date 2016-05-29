<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext['request'].contextPath}"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="imgHost" value="${initParam.imgHost}"/>
<style type="text/css">
    h3 {
        border-bottom: 1px solid #ddd;
        padding: 8px 0;
    }

    .tab-head {
        width: 15%;
    }

    .tab-content-head {
        width: 35%;
    }
</style>

<div class="line">
    <h3 class="clearfix">发布信息详细
<span class="pull-left">(
<c:if test="${bean.status=='0'}"><span>草稿</span>
</c:if>
    <c:if test="${bean.status=='1'}"><span>待审核</span></c:if>
    <c:if test="${bean.status=='2'}"><span>发布</span></c:if>
    <c:if test="${bean.status=='3'}"><span>下架</span></c:if>
    <c:if test="${bean.status=='4'}"><span>已售</span></c:if>
)
</span>
        <span class="pull-right">
    <c:if test="${bean.sellType=='0'}"><span>出售</span></c:if>
    <c:if test="${bean.sellType=='1'}"><span>租赁</span></c:if>
    <c:if test="${bean.sellType=='2'}"><span>求购</span></c:if>
    <c:if test="${bean.sellType=='3'}"><span>求租</span></c:if>

</span>
        <c:if test="${bean.status=='1'}"><br/>
            <div class="btn-group btn-large">
                <button class="btn btn-primary  " ng-click="openModel(1)">通过</button>
                <button class="btn btn-primary " ng-click="openModel(2)">不通过</button>
            </div>
        </c:if>
        <c:if test="${bean.auditType=='1'}"><br/>
            <div class="btn-group btn-large">
                <button class="btn btn-primary " ng-click="openModel(2)">不通过</button>
            </div>
        </c:if>
        <c:if test="${bean.status=='2'}">
            <div class="btn-group btn-large">
                <a class="btn btn-primary pull-left " href="${ctx}/admin/infomation/input?id=${bean.id}&status=4">下架</a>
                <c:if test="${bean.isTop=='0'||bean.isTop==null}">
                    <a class="btn btn-primary pull-left " href="${ctx}/admin/infomation/input?id=${bean.id}&top=1">推荐</a>
                </c:if>
                <c:if test="${bean.isTop!='0'&&bean.isTop!=null}">
                    <a class="btn btn-primary pull-left " href="${ctx}/admin/infomation/input?id=${bean.id}&top=0">取消推荐</a>
                </c:if>
            </div>
        </c:if>
        <c:if test="${bean.status=='4'}">
            <div class="btn-group btn-large">
                <a class="btn btn-primary pull-left "  href="${ctx}/admin/infomation/input?id=${bean.id}&status=2">上架</a>
            </div>
        </c:if>
    </h3>
    <div class="btn-group btn-large">
        <button class="btn btn-primary " ng-click="editInfomation1()">修改产品类别</button>
    </div>
    <div class="btn-group btn-large">
        <button class="btn btn-primary " ng-click="editInfomation()">修改设备信息</button>
    </div>
</div>
<table class="table table-bordered">
    <tbody>
    <tr>
        <td class="tab-head">产品大类</td>
        <td class="tab-content-head" ng-model="company">${bean.catagoryBigName}</td>
        <td class="tab-head">产品组</td>
        <td>${bean.catagoryMidName}</td>

    </tr>
    <tr>
        <td class="tab-head">产品类</td>
        <td>${bean.catagoryName}</td>
        <td class="tab-head">品牌</td>
        <td class="tab-content-head">${bean.brandName}</td>
    </tr>
    <tr>
        <td class="tab-head">型号</td>
        <td class="tab-content-head">${bean.modelName}</td>
        <td class="tab-head">用户自定义新增</td>
        <td>
            <c:if test="${bean.isNew==0}">否</c:if>
            <c:if test="${bean.isNew==1}">是</c:if>
        </td>
    </tr>
    </tbody>
</table>

<div class="line">
    <h3 class="clearfix">设备信息
    </h3>
</div>
<table class="table table-bordered">
    <tbody>
    <tr>
        <td class="tab-head">设备情况</td>
        <td class="tab-content-head" ng-model="company">
            <c:if test="${bean.equipmentCondition==0}">全新</c:if>
            <c:if test="${bean.equipmentCondition==1}">二手</c:if>
            <c:if test="${bean.equipmentCondition==2}">再制造</c:if>
            <c:if test="${bean.equipmentCondition==3}">库存</c:if>
        </td>
        <td class="tab-head">手续资料</td>
        <td>
            <c:if test="${bean.procedures==0}">手续齐全</c:if>
            <c:if test="${bean.procedures==1}">无手续</c:if>
            <c:if test="${bean.procedures==2}">有无手续均可</c:if>
        </td>

    </tr>
    <tr>
        <td class="tab-head">设备来源</td>
        <td>
            <c:if test="${bean.src==0}">个人</c:if>
            <c:if test="${bean.src==1}">单位</c:if>
            <c:if test="${bean.src==2}">抵押</c:if>
            <c:if test="${bean.src==3}">法务</c:if>
        </td>
        <td class="tab-head">设备年份</td>
        <td class="tab-content-head">${bean.equipYear}</td>
    </tr>
    <tr>
        <td class="tab-head">工时</td>
        <td class="tab-content-head">${bean.workTime}</td>
        <td class="tab-head">价格</td>
        <td>${bean.price}</td>
    </tr>
    <tr>
        <td class="tab-head">出厂编码</td>
        <td class="tab-content-head">${bean.serialNum}</td>
        <td class="tab-head">内部库存编码</td>
        <td class="tab-content-head">${bean.inStockCode}</td>
    </tr>
    <td class="tab-head">设备位置</td>出厂编码
    <td class="tab-content-head">${bean.equipmentLocation}</td>
    <td class="tab-head">发布日期</td>
    <td class="tab-content-head"><fmt:formatDate value="${bean.pubTime}" pattern="yyyy/MM/dd" /></td>
    </tr>
    <tr>
        <td class="tab-head">到期日期</td>
        <td class="tab-content-head"><fmt:formatDate value="${bean.endTime}" pattern="yyyy/MM/dd" />(${bean.validTime})</td>
        <td class="tab-head">库存数量</td>
        <td class="tab-content-head">${bean.stockCount}</td>
    </tr>
    <tr>
        <td class="tab-head">已售数量</td>
        <td class="tab-content-head">${bean.sellCount}</td>
        <td class="tab-head">是否推荐</td>
        <td class="tab-content-head">${bean.isTop}</td>
    </tr>
    </tbody>
</table>

<div class="line">
    <h3 class="clearfix">卖家附言</h3>
    <br/>
    <span class="pull-left">
        ${bean.remark}
    </span>
</div>

<c:if test="${bean.status!=null&&bean.status!=0}">
    <%--<div>--%>
        <%--<h3 class="clearfix">审核记录</h3>--%>
        <%--<div class="col-md-12">--%>
            <%--<table class="table table-bordered">--%>
                <%--<thead>--%>
                <%--<tr>--%>
                    <%--<th class="tab-head">修改时间</th>--%>
                    <%--<th class="tab-head">修改人员</th>--%>
                    <%--<th class="tab-head">审核备注</th>--%>
                <%--</tr>--%>
                <%--</thead>--%>
                <%--<tbody>--%>
                <%--<tr>--%>
                    <%--<td ng-bind="log.dateValue | date : 'yyyy-MM-dd HH:mm:ss'"></td>--%>
                    <%--<td ng-bind="log.operator"></td>--%>
                    <%--<td ng-bind="switchReason(log.rejectCode)"></td>--%>
                <%--</tr>--%>
                <%--</tbody>--%>
            <%--</table>--%>
        <%--</div>--%>
    <%--</div>--%>
</c:if>
<div>
    <h3 class="clearfix">用户信息</h3>
    <div class="col-md-12">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th class="tab-head">用户名称</th>
                <th class="tab-head">手机号码</th>
                <th class="tab-head">地区</th>
                <th class="tab-head">经营性质</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${user.realName}</td>
                <td>${user.telephone}</td>
                <td>${user.provinceName}|${user.cityName}</td>
                <td>
                    <c:if test="${user.businessScope == 1}">工程机械</c:if>
                    <c:if test="${user.businessScope == 2}">农业机械</c:if>
                    <c:if test="${user.businessScope == 3}">矿山机械</c:if>
                    <c:if test="${user.businessScope == 4}">林业机械</c:if>
                    <c:if test="${user.businessScope == 5}">运输机械</c:if>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<div>
    <h3 class="clearfix">信息图片</h3><br/>
    <c:forEach var="img" items="${beanImg}">
        <img src="${initParam.imgHost}${img.attchUrl}/figure" style="max-width: 100%;"
             alt="">
    </c:forEach>
</div>

<%--<div>--%>
<%--<a class="btn btn-primary pull-left" ng-click="openModel(1)">通过</a>--%>
<%--<a class="btn btn-primary pull-left" ng-click="openModel(2)">不通过</a>--%>
<%--</div>--%>
<script>
    angular.companyId = "${bean.id}";
    angular.userId = "${bean.userId}";
    angular.isNew = "${bean.isNew}";
    angular.brandName = "${bean.brandName}";
    angular.brandId = "${bean.brandId}";
    angular.modelName = "${bean.modelName}";
    angular.modelId = "${bean.modelId}";
    angular.catagoryId = "${bean.catagoryId}";
    angular.catagoryName = "${bean.catagoryName}";
    angular.catagoryMidId = "${bean.catagoryMidId}";
    angular.catagoryMidName = "${bean.catagoryMidName}";
    angular.catagoryBigId = "${bean.catagoryBigId}";
    angular.catagoryBigName = "${bean.catagoryBigName}";
    angular.equipmentCondition = "${bean.equipmentCondition}";
    angular.procedures = "${bean.procedures}";
    angular.src = "${bean.src}";
    angular.equipYear = "${bean.equipYear}";
    angular.workTime = "${bean.workTime}";
    angular.price = "${bean.price}";
    angular.serialNum = "${bean.serialNum}";
    angular.inStockCode = "${bean.inStockCode}";
    angular.equipmentLocation = "${bean.equipmentLocation}";
    angular.stockCount = "${bean.stockCount}";
    angular.sellCount = "${bean.sellCount}";
    angular.remark = "${bean.remark}";
    angular.status = "${bean.status}";
    angular.sellType = "${bean.sellType}";

    seajs.use(['js/controller/common/app', 'js/controller/infomation/detail'], function () {
        angular.bootstrap(document, ['App']);
    });
</script>

<script type="text/ng-template" id="selectModalTemplate.html">
    <div style="padding-left: 10px; padding-top: 3px">
        <h3>选择理由</h3>
    </div>
    <div>
        <form style="margin-bottom: 10px; padding-left: 10px;" ng-controller="selectCtrl">
            <div ng-repeat="unLockReason in unLockReasons">
                <label class="radio-inline">
                    <input type="radio" name="radio" ng-click="setReason(unLockReason)" ng-modal="unLockReason.value">{{unLockReason.reason}}
                </label>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" ng-click="unLock()">确定</button>
                <button type="button" class="btn btn-primary" ng-click="closeModal()">关闭</button>
            </div>
        </form>
    </div>

</script>
