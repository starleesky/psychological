<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext['request'].contextPath}" />
<style type="text/css">
	.container-fluid{
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
			      <input type="text" class="form-control" placeholder="企业名称" ng-model="list.filter.companyName">
			    </div>
			  </div>
				<div class="form-group">
					<div class="input-group">
						<input type="text" class="form-control" placeholder="用户姓名" ng-model="list.filter.userName">
					</div>
				</div>
			  <button type="submit" class="btn btn-primary">搜索</button>

			  <div class="form-group">
			    <div class="input-group">
			      <input type="text" class="form-control" datepicker-popup	 is-open="list.startopened" ng-model="list.filter.startDate" ng-change="list.fetch()"/>
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
			      <input type="text" class="form-control" datepicker-popup is-open="list.endopened" ng-model="list.filter.endDate" ng-change="list.fetch()"/>
		        <span class="input-group-btn">
		          <button type="button" class="btn btn-default" ng-click="list.openCal($event, 'end')"><i class="glyphicon glyphicon-calendar"></i></button>
		        </span>
			    </div>
			  </div>

			  <div class="form-group pull-left">
			  	<select class="form-control" name="auditStatus" ng-change="list.fetch()" ng-model="list.filter.status">
			  		<option value="">认证状态</option>
			  		<option value="0" >待审核</option>
			  		<option value="1" >认证成功</option>
			  		<option value="2" >认证失败</option>
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
				<th>用户姓名</th>
				<th>企业名称</th>
				<th>联系电话</th>
				<th>传真</th>
				<th>省份</th>
				<th>城市</th>
				<th>详细地址</th>
				<th>时间</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<tr ng-repeat="c in list.data.items">
				<td ng-bind="c.userName"></td>
				<td ng-bind="c.companyName"></td>
				<td ng-bind="c.telephone"></td>
				<td ng-bind="c.fax"></td>
				<td ng-bind="c.provinceName"></td>
				<td ng-bind="c.cityName"></td>
				<td ng-bind="c.address"></td>
				<td ng-bind="c.createTime | date : 'yyyy-MM-dd HH:mm:ss'"></td>
				<td>
					<div ng-switch on="c.status">
						<span ng-switch-when="0">待审核</span>
						<span ng-switch-when="1">审核成功</span>
						<span ng-switch-when="2">审核失败</span>
					</div>
				</td>
				<td>
					<div ng-switch on="c.status">
					<a href="${ctx}/admin/company/getDetail?id={{c.id}}" class="btn btn-primary">
						<div ng-switch on="c.status">
							<span ng-switch-when="0">审核</span>
							<span ng-switch-when="1">查看详情</span>
							<span ng-switch-when="2">查看详情</span>
						</div>
					</a>
						<button class="btn btn-primary" ng-click="deleteOne(c,$index)">删除</button>
						<div ng-switch on="c.status">
				</td>
			</tr>
		</tbody>
	</table>
</div>

<div class="line" style="text-align: right;">
	<pagination total-items="list.data.totalCount" ng-model="list.filter.pageNo" ng-change="list.fetch()" items-per-page="list.filter.pageSize"></pagination>
	<p>共{{list.data.totalCount}}条 | {{list.data.pageCount}}页 | 每页{{list.filter.pageSize}}条</p>
</div>

<!-- <div class="line">
	<pagination total-items="list.data.totalSize" ng-model="list.filter.page" ng-change="list.fetch()" class="pull-right" previous-text="&lsaquo;" next-text="&rsaquo;" items-per-page="list.filter.pageSize"></pagination>
</div> -->

<script>

	angular.just = true;
	angular.listType = 1;
	seajs.use(['js/controller/common/app','js/controller/company/list'], function () {
		angular.bootstrap(document, ['App']);
	});

</script>