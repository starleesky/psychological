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
                    <input type="text" class="form-control" placeholder="公告标题" ng-model="list.filter.title">
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="用户ID" ng-model="list.filter.userId">
                </div>
            </div>
            <button type="submit" class="btn btn-primary">搜索</button>

            <div class="form-group pull-left">
                <select class="form-control" name="auditStatus" ng-change="list.fetch()" ng-model="list.filter.noticeType">
                    <option value="">全部</option>
                    <option value="0">系统公告</option>
                    <option value="1">用户消息</option>
                </select>
            </div>
            <div class="form-group pull-right">
                <button class="btn btn-primary" ng-click="addNew()">新增</button>
            </div>

        </form>
        <!-- </div> -->
    </div>
</div>

<div class="line" style="overflow-y:auto; overflow-x:auto;">
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>公告类型</th>
            <th>公告标题</th>
            <th>公告内容</th>
            <th>用户ID</th>
            <th>创建时间</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <tr ng-repeat="c in list.data.items">
            <td>
                <div ng-switch on="c.noticeType">
                    <span ng-switch-when="0">系统公告</span>
                    <span ng-switch-when="1">用户消息</span>
                </div>
            </td>
            <td ng-bind="c.title"></td>
            <td ng-bind="c.content" width="50%"></td>
            <td ng-bind="c.userId"></td>
            <td ng-bind="c.createTime | date : 'yyyy-MM-dd HH:mm:ss'"></td>
            <td>
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
    seajs.use(['js/controller/common/app', 'js/controller/notice/list'], function () {
        angular.bootstrap(document, ['App']);
    });

</script>