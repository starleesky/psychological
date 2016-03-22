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
			      <input type="text" class="form-control" placeholder="车牌号" ng-model="list.filter.carNum">
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

			  <div class="form-group pull-right">
			  	<select class="form-control" name="auditStatus" ng-change="list.fetch()" ng-model="list.filter.auditStatus">
			  		<option value="">认证状态</option>
			  		<option value="{{$index}}" ng-repeat="x in statusList">{{x}}</option>
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
				<th>车辆ID</th>
				<th>车牌号</th>
				<th>品牌</th>
				<th>车系</th>
				<th>车型</th>
				<th>行驶城市</th>
				<th>时间</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<tr ng-repeat="c in list.data.data">
				<td ng-bind="c.carId"></td>
				<td ng-bind="c.carNum"></td>
				<td ng-bind="c.carBrandName"></td>
				<td ng-bind="c.carSeriesName"></td>
				<td ng-bind="c.carModelName"></td>
				<td ng-bind="c.cityName"></td>
				<td ng-bind="c.createTime | date : 'yyyy-MM-dd HH:mm:ss'"></td>
				<td>
					<div ng-switch on="c.auditStatus">
						<span ng-switch-when="0">未认证</span>
						<span ng-switch-when="1">待认证</span>
						<span ng-switch-when="2">认证成功</span>
						<span ng-switch-when="3">认证失败</span>
						<span ng-switch-when="4">行驶证已失效</span>
					</div>
				</td>
				<td>
					<a href="${ctx}/vehicle/list/get/detail?carId={{c.carId}}" class="btn btn-primary">详情</a>
				</td>
			</tr>
		</tbody>
	</table>
</div>

<div class="line" style="text-align: right;">
	<pagination total-items="list.data.totalSize" ng-model="list.filter.page" ng-change="list.fetch()" items-per-page="list.filter.pageSize"></pagination>
	<p>共{{list.data.totalSize}}条 | {{list.data.totalPage}}页 | 每页{{list.filter.pageSize}}条</p>
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