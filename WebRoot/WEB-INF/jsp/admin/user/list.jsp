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
                    <input type="text" class="form-control" placeholder="手机号码" ng-model="list.filter.mobile">
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="用户名称" ng-model="list.filter.userName">
                </div>
            </div>
            <button type="submit" class="btn btn-primary">搜索</button>

            <div class="form-group pull-left">
                <select class="form-control" name="auditStatus" ng-change="list.fetch()"
                        ng-model="list.filter.isActivate">
                    <option value="">是否激活</option>
                    <option value="T">是</option>
                    <option value="F">否</option>
                </select>
            </div>
            <div class="form-group pull-left">
                <select class="form-control" name="auditStatus" ng-change="list.fetch()"
                        ng-model="list.filter.userType">
                    <option value="">用户类型</option>
                    <option value="0">系统管理员</option>
                    <option value="1">普通管理员</option>
                    <option value="2">普通用户</option>
                    <option value="3">企业管理员</option>
                </select>
            </div>
            <div class="form-group pull-right">
                <button class="btn btn-primary" ng-click="addNew()">新增</button>
            </div>

        </form>
        <!-- </div> -->
    </div>
</div>

<div class="line">
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>用户名称</th>
            <th>手机号码</th>
            <th>邮箱</th>
            <th>省份</th>
            <th>城市</th>
            <th>经营范围</th>
            <th>经营性质</th>
            <th>是否激活</th>
            <th>用户类型</th>
            <th>最后登录时间</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <tr ng-repeat="c in list.data.items">
            <td ng-bind="c.userName"></td>
            <td ng-bind="c.mobile"></td>
            <td ng-bind="c.email"></td>
            <td ng-bind="c.provinceName"></td>
            <td ng-bind="c.cityName"></td>
            <td>
                <div ng-switch on="c.businessScope">
                    <span ng-switch-when="1">工程机械</span>
                    <span ng-switch-when="2">矿山机械</span>
                    <span ng-switch-when="3">林业机械</span>
                    <span ng-switch-when="4">农历机械</span>
                    <span ng-switch-when="5">运输机械</span>
                </div>
            </td>
            <td>
                <div ng-switch on="c.businessNature">
                    <span ng-switch-when="0">出售</span>
                    <span ng-switch-when="1">租赁</span>
                    <span ng-switch-when="2">求购</span>
                    <span ng-switch-when="3">求租</span>
                </div>
            </td>
            <td>
                <div ng-switch on="c.isActivate">
                    <span ng-switch-when="T">激活</span>
                    <span ng-switch-when="F">未激活</span>
                </div>
            </td>
            <td>
                <div ng-switch on="c.userType">
                    <span ng-switch-when="0">系统管理员</span>
                    <span ng-switch-when="1">普通管理员</span>
                    <span ng-switch-when="2">普通用户</span>
                    <span ng-switch-when="3">企业管理员</span>
                </div>
            </td>
            <td ng-bind="c.lastLoginTime | date : 'yyyy-MM-dd'"></td>
            <td>
                <%--<button class="btn btn-primary" ng-click="showDetail(c,$index)">查询详情</button>--%>
                <button class="btn btn-primary" ng-click="edit(c,$index)">修改</button>
                <button class="btn btn-primary" ng-click="deleteOne(c,$index)">删除</button>
            </td>
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