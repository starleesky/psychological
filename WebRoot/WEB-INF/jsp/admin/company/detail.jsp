<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext['request'].contextPath}"/>
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
<h3 class="clearfix">公司详细信息
<span class="pull-right">
<c:if test="${bean.status=='0'}"><span>待审核</span>
    </c:if>
    <c:if test="${bean.status=='1'}"><span>审核成功</span></c:if>
    <c:if test="${bean.status=='2'}"><span>审核失败</span></c:if>
    </div>
    </span>
<c:if test="${bean.status=='0'}"><br/>
    <div class="btn-group btn-large">
        <button class="btn btn-primary  " ng-click="openModel(1)">通过</button>
        <button class="btn btn-primary " ng-click="openModel(2)">不通过</button>
    </div>
</c:if>
<div class="btn-group btn-large">
    <button class="btn btn-primary " ng-click="editCompany()">修改公司信息</button>
</div>
    </h3>
    <table class="table table-bordered">
    <tbody>
    <tr>
    <td class="tab-head">公司名称</td>
    <td class="tab-content-head" ng-model="company">${bean.companyName}</td>
    <td class="tab-head">公司电话</td>
    <td>${bean.telephone}</td>
    </tr>
    <tr>
    <td class="tab-head">公司传真</td>
    <td class="tab-content-head" colspan="3">${bean.fax}</td>
    </tr>
    <tr>
    <td class="tab-head">省份</td>
    <td class="tab-content-head">${bean.provinceName}</td>
    <td class="tab-head">城市</td>
    <td>${bean.cityName}</td>
    </tr>
    <tr>
    <td class="tab-head">详细地址</td>
    <td class="tab-content-head" colspan="3">${bean.address}</td>
    </tr>
    </tbody>
    </table>
    <div class="line">
    <h3 class="clearfix">公司介绍</h3>
    <br/>
    <span class="pull-left">
    ${bean.introduction}
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
        <h4 class="clearfix">公司logo图片</h4><br/>
        <c:if test="${bean.createBy!=null}">
            <img src="${initParam.imgHost}${bean.createBy}/oryginalny" style="max-width: 100%;"
                 alt="营业执照图片">
        </c:if>
    <h4 class="clearfix">营业执照图片</h4><br/>
    <c:if test="${bean.businessLicenseImageUrl!=null}">
        <img src="${initParam.imgHost}${bean.businessLicenseImageUrl}/oryginalny" style="max-width: 100%;"
             alt="营业执照图片">
    </c:if>
    <h4 class="clearfix">组织机构代码图片</h4><br/>
    <c:if test="${bean.organizationCodeImageUrl!=null}">
        <img src="${initParam.imgHost}${bean.organizationCodeImageUrl}/oryginalny" style="max-width: 100%;"
             alt="组织机构代码图片">
    </c:if>
    </div>

    <%--<div>--%>
    <%--<a class="btn btn-primary pull-left" ng-click="openModel(1)">通过</a>--%>
    <%--<a class="btn btn-primary pull-left" ng-click="openModel(2)">不通过</a>--%>
    <%--</div>--%>
    <script>
    angular.companyId = "${bean.id}";
    angular.companyName = "${bean.companyName}";
    angular.telephone = "${bean.telephone}";
    angular.fax = "${bean.fax}";
    angular.provinceId = "${bean.provinceId}";
    angular.provinceName = "${bean.provinceName}";
    angular.cityId = "${bean.cityId}";
    angular.cityName = "${bean.cityName}";
    angular.address = "${bean.address}";
    angular.introduction = "${bean.introduction}";
    seajs.use(['js/controller/common/app', 'js/controller/company/detail'], function () {
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
