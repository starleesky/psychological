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
                    <input type="text" class="form-control" placeholder="产品大类" ng-model="list.filter.catagoryBigName">
                </div>
            </div>
            <button type="submit" class="btn btn-primary">搜索</button>

            <div class="form-group">
                <div class="input-group">
                    <input type="text" class="form-control" datepicker-popup is-open="list.startopened"
                           ng-model="list.filter.startDate" ng-change="list.fetch()"/>
		        <span class="input-group-btn">
		          <button type="button" class="btn btn-default" ng-click="list.openCal($event, 'start')">
                      <i class="glyphicon glyphicon-calendar"></i>
                  </button>
		        </span>
                </div>
            </div>

            <span>至</span>

            <div class="form-group">
                <div class="input-group">
                    <input type="text" class="form-control" datepicker-popup is-open="list.endopened"
                           ng-model="list.filter.endDate" ng-change="list.fetch()"/>
		        <span class="input-group-btn">
		          <button type="button" class="btn btn-default" ng-click="list.openCal($event, 'end')"><i
                          class="glyphicon glyphicon-calendar"></i></button>
		        </span>
                </div>
            </div>

            <div class="form-group pull-left">
                <select class="form-control" name="auditStatus" ng-change="list.fetch()"
                        ng-model="list.filter.sellType">
                    <option value="">销售方式</option>
                    <option value="0">出售</option>
                    <option value="1">租赁</option>
                    <option value="2">求购</option>
                    <option value="3">求租</option>
                </select>
            </div>
            <div class="form-group pull-left">
                <select class="form-control" name="auditStatus" ng-change="list.fetch()" ng-model="list.filter.status">
                    <option value="">认证状态</option>
                    <option value="1">待审核</option>
                    <option value="2">发布</option>
                    <option value="3">下架</option>
                    <option value="4">已售</option>
                    <option value="0">草稿箱</option>
                </select>
            </div>

        </form>
        <!-- </div> -->
    </div>
</div>

<div class="line">
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>产品大类</th>
            <th>产品组</th>
            <th>产品类</th>
            <th>品牌</th>
            <th>型号</th>
            <th>销售方式</th>
            <th>时间</th>
            <th>信息状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <tr ng-repeat="c in list.data.items">
            <td ng-bind="c.catagoryBigName"></td>
            <td ng-bind="c.catagoryMidName"></td>
            <td ng-bind="c.catagoryName"></td>
            <td ng-bind="c.brandName"></td>
            <td ng-bind="c.modelName"></td>
            <td>
                <div ng-switch on="c.sellType">
                    <span ng-switch-when="0">出售</span>
                    <span ng-switch-when="1">租赁</span>
                    <span ng-switch-when="2">求购</span>
                    <span ng-switch-when="3">求租</span>
                </div>
            </td>
            <td ng-bind="c.createTime | date : 'yyyy-MM-dd HH:mm:ss'"></td>
            <td>
                <div ng-switch on="c.status">
                    <span ng-switch-when="0">草稿</span>
                    <span ng-switch-when="1">待审核</span>
                    <span ng-switch-when="2">发布</span>
                    <span ng-switch-when="3">下架</span>
                    <span ng-switch-when="4">已售</span>
                </div>
            </td>
            <td>
                <div  ng-show="c.status==1">
                <a href="${ctx}/admin/infomation/getDetail?id={{c.id}}"  class="btn btn-primary">审核</a>
                </div>

                <div  ng-show="c.status==2">
                    <a href="${ctx}/admin/infomation/getDetail?id={{c.id}}"  class="btn btn-primary">下架</a>
                </div>
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
    seajs.use(['js/controller/common/app', 'js/controller/infomation/list'], function () {
        angular.bootstrap(document, ['App']);
    });

</script>