<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext['request'].contextPath}"/>
<style type="text/css">
    .container-fluid {
        padding-left: 0px;
        padding-right: 0px;
    }
</style>

<div class="line">
    <div class="container-fluid clearfix">
        <!-- <div class="search-box pull-left"> -->
        <form class="form-inline" ng-submit="list.fetch()">
            <div class="form-group">
                <div class="input-group">
                    用户：<input type="text" class="form-control" placeholder="姓名或⼿机号" ng-model="list.filter.userName">
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    单位：<input type="text" class="form-control" placeholder="服务单位" ng-model="list.filter.company">
                </div>
            </div>
            <button type="submit" class="btn btn-primary">搜索</button>

            <%--<div class="form-group pull-left">--%>
                <%--<select class="form-control" name="auditStatus" ng-change="list.fetch()"--%>
                        <%--ng-model="list.filter.isActivate">--%>
                    <%--<option value="">是否激活</option>--%>
                    <%--<option value="T">是</option>--%>
                    <%--<option value="F">否</option>--%>
                <%--</select>--%>
            <%--</div>--%>
            <%--<div class="form-group pull-left">--%>
                <%--<select class="form-control" name="auditStatus" ng-change="list.fetch()"--%>
                        <%--ng-model="list.filter.userType">--%>
                    <%--<option value="">用户类型</option>--%>
                    <%--<option value="0">系统管理员</option>--%>
                    <%--<option value="1">普通管理员</option>--%>
                    <%--<option value="2">普通用户</option>--%>
                    <%--<option value="3">企业管理员</option>--%>
                <%--</select>--%>
            <%--</div>--%>
            <%--<select name="provnice" required ng-model="list.filter.provinceId" class="form-control" ng-options="id as name for (id, name) in provinceList" ng-change="list.fetch()">--%>
            <%--</select>--%>
            <%--<city-list name="city" required ng-model="list.filter.cityId" provice="list.filter.provinceId" class="form-control" ng-change="list.fetch()"></city-list>--%>
            <%--<div class="form-group pull-right">--%>
                <%--<button class="btn btn-primary" ng-click="addNew()">新增</button>--%>
            <%--</div>--%>

        </form>
        <!-- </div> -->
    </div>
</div>

<div class="line">
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>评测编号</th>
            <th>测试量量表名称</th>
            <th>测试时间</th>
            <th>测试者</th>
            <th>测试结果</th>
            <th>手机号码</th>
        </tr>
        </thead>
        <tbody>
        <tr ng-repeat="c in list.data.items">
            <td ng-bind="c.id"></td>
            <td>
                <div ng-switch on="c.evaType">
                    <span ng-switch-when="1"> MBTI</span>
                    <span ng-switch-when="2"> OQ45</span>
                    <span ng-switch-when="3"> SCL90</span>
                </div>
            </td>
            <td ng-bind="c.createTime| date : 'yyyy-MM-dd HH:mm:ss'"></td>
            <td ng-bind="c.userName"></td>
            <td ng-bind="c.score"></td>
            <td ng-bind="c.userPhone"></td>
        </tr>
        </tbody>
    </table>
</div>

<div class="line" style="text-align: right;">
    <pagination total-items="list.data.totalCount" ng-model="list.filter.pageNo" ng-change="list.fetch()"
                items-per-page="list.filter.pageSize"></pagination>
    <p>共{{list.data.totalCount}}条 | {{list.data.pageCount}}页 | 每页{{list.filter.pageSize}}条</p>
</div>

<!-- <div class="line">
<pagination total-items="list.data.totalSize" ng-model="list.filter.page" ng-change="list.fetch()" class="pull-right" previous-text="&lsaquo;" next-text="&rsaquo;" items-per-page="list.filter.pageSize"></pagination>
</div> -->

<script>

    angular.just = true;
    angular.listType = 1;
    seajs.use(['js/controller/common/app', 'js/controller/user/evaluating'], function () {
        angular.bootstrap(document, ['App']);
    });

</script>