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
                    <input type="text" class="form-control" placeholder="型号名称" ng-model="list.filter.modelsName">
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="品牌ID" ng-model="list.filter.brandId">
                </div>
            </div>

            <button type="submit" class="btn btn-primary">搜索</button>
            <div class="form-group pull-right">
                <button class="btn btn-primary" ng-click="add()">新增型号</button>
            </div>

            <%--<div class="form-group pull-right">--%>
                <%--<button class="btn btn-primary" ng-click="upLoadFunc()">导入EXCEL基础数据</button>--%>
            <%--</div>--%>

        </form>
        <!-- </div> -->
    </div>
</div>

<div class="line">
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>型号ID</th>
            <th>型号名称</th>
            <th>品牌ID</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <tr ng-repeat="c in list.data.items">
            <td ng-bind="c.id"></td>
            <td ng-bind="c.modelsName"></td>
            <td ng-bind="c.brandId"></td>
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
    seajs.use(['js/controller/common/app', 'js/controller/models/list'], function () {
        angular.bootstrap(document, ['App']);
    });

</script>