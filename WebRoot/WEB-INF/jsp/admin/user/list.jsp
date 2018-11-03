<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext['request'].contextPath}"/>
<%--<style type="text/css">--%>
    <%--.container-fluid {--%>
        <%--padding-left: 0px;--%>
        <%--padding-right: 0px;--%>
    <%--}--%>
<%--</style>--%>

<div class="line">
    <div class="container-fluid clearfix">
        <!-- <div class="search-box pull-left"> -->
        <form class="form-inline" ng-submit="list.fetch()">
            <div class="form-group">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="用户姓名或⼿机号" ng-model="list.filter.userName">
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="服务单位" ng-model="list.filter.company">
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
            <th>用户编号</th>
            <th>来源</th>
            <th>所属单位</th>
            <th>姓名</th>
            <th>性别</th>
            <th>年龄</th>
            <th>预约咨询次数</th>
            <th>心理评测次数</th>
            <th>手机号码</th>
        </tr>
        </thead>
        <tbody>
        <tr ng-repeat="c in list.data.items">
            <td ng-bind="c.id"></td>
            <td>
                <div ng-switch on="c.src">
                    <span ng-switch-when="0"> 预约</span>
                    <span ng-switch-when="1"> 评测</span>
                </div>
            </td>
            <td ng-bind="c.company"></td>

            <td ng-bind="c.userName"></td>
            <td>
                <div ng-switch on="c.sex">
                    <span ng-switch-when="1"> 男</span>
                    <span ng-switch-when="2"> 女</span>
                    <span ng-switch-when="0"> 未知</span>
                </div>
            </td>
            <td ng-bind="c.age"></td>
            <td ng-bind="c.subscribeCount"></td>
            <td ng-bind="c.evaluatingCount"></td>
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
    seajs.use(['js/controller/common/app', 'js/controller/user/list'], function () {
        angular.bootstrap(document, ['App']);
    });

</script>