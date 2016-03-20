<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext['request'].contextPath}" />
<style type="text/css">
	h3{
		border-bottom: 1px solid #ddd;
		padding: 8px 0;
	}
	.tab-head{
		width:15%;
	}
	.tab-content-head{
		width:35%;
	}
</style>

<div class="line ng-hide" ng-show="carManager.carDetail.failReason">
	<p class="alert alert-warning"><strong>失败原因：</strong><span ng-bind="carManager.carDetail.failReason"></span></p>
</div>

<div class="line">
	<h3 class="clearfix">车辆信息<span class="pull-right">状态：{{carManager.carDetail.auditStatusText}}</span></h3>
	<table class="table table-bordered">
		<tbody>
			<tr>
				<td class="tab-head">用户名</td>
				<td class="tab-content-head" ng-bind="carManager.carDetail.userName"></td>
				<td class="tab-head">用户电话</td>
				<td ng-bind="carManager.carDetail.userPhone"></td>
			</tr>
			<tr>
				<td class="tab-head">车牌号</td>
				<td class="tab-content-head" ng-bind="carManager.carDetail.carNum"></td>
				<td class="tab-head">行驶城市</td>
				<td ng-bind="carManager.carDetail.cityName"></td>
			</tr>
			<tr>
				<td>品牌</td>
				<td ng-bind="carManager.carDetail.carBrandName"></td>
				<td>车系</td>
				<td ng-bind="carManager.carDetail.carSeriesName"></td>
			</tr>
			<tr>
				<td>车型</td>
				<td ng-bind="carManager.carDetail.carModelName"></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td>交强险到期时间</td>
				<td><span ng-bind="carManager.carDetail.mandatoryExpireDate|date:'yyyy-MM-dd'"></span></td>
				<td>商业险到期时间</td>
				<td><span ng-bind="carManager.carDetail.commercialExpireDate|date:'yyyy-MM-dd'"></span></td>
			</tr>
		</tbody>
	</table>
	<div class="clearfix" ng-if="carManager.carDetail.auditStatus==1">
		<a ng-if="preAudit==0" href="${ctx}/license/auth/list/detail/preAudit?carId={{carManager.carDetail.carId}}" class="btn btn-primary pull-right">认证</a>
		<a ng-if="preAudit==1" href="${ctx}/license/auth/list/detail?carId={{carManager.carDetail.carId}}" class="btn btn-primary pull-right">认证</a>
	</div>
</div>

<div class="line" ng-show="carManager.carDetail.license">
	<h3 class="clearfix">行驶证信息
		<a href="javascript:;" ng-click="openModal(carManager.carDetail.carId)"
		   class="btn btn-primary pull-right">认证失败</a>
		<a href="${ctx}/license/auth/list/do/license/update?carId={{carManager.carDetail.carId}}"
		   class="btn btn-primary pull-right">修改</a></h3>
	<table class="table table-bordered">
		<tbody>
			<tr>
				<td class="tab-head">车主</td>
				<td class="tab-content-head">
					{{carManager.carDetail.license.owner}}
					<a ng-click="showImg(carManager.carDetail.license.idcardImg)" ng-if="carManager.carDetail.license.idcardImg">查看身份证照片</a>
				</td>
				<td class="tab-head">品牌型号</td>
				<td ng-bind="carManager.carDetail.license.model"></td>
			</tr>
			<tr>
				<td>车辆类型</td>
				<td ng-bind="carManager.carDetail.license.vehicleTypeName"></td>
				<td>使用性质</td>
				<td ng-bind="carManager.carDetail.license.useCharacterName"></td>
			</tr>
			<tr>
				<td>注册登记日期</td>
				<td ng-bind="carManager.carDetail.license.registerDate | date:'yyyy-MM-dd'"></td>
				<td>发动机号</td>
				<td ng-bind="carManager.carDetail.license.engineNo"></td>
			</tr>
			<tr>
				<td>发证日期</td>
				<td ng-bind="carManager.carDetail.license.issueDate|date:'yyyy-MM-dd'"></td>
				<td>车架号</td>
				<td ng-bind="carManager.carDetail.license.vin"></td>
			</tr>
			<tr>
				<td>年检日期</td>
				<td colspan="3" ng-bind="carManager.carDetail.license.inspectionDate|date:'yyyy-MM-dd'"></td>
			</tr>
		</tbody>
	</table>
	
	<table class="table table-bordered">
		<tbody>
			<tr>
				<td class="tab-head">核定载客</td>
				<td class="tab-content-head" ng-bind="carManager.carDetail.license.vehicleSeats + '人'"></td>
				<td class="tab-head">核定载质量</td>
				<td ng-bind="carManager.carDetail.license.quotaWeight + 'Kg'"></td>
			</tr>
			<tr>
				<td>排量</td>
				<td ng-bind="carManager.carDetail.license.emission + '毫升'"></td>
				<td>功率</td>
				<td ng-bind="carManager.carDetail.license.power + 'kw'"></td>
			</tr>
			<tr>
				<td>新车购置价</td>
				<td ng-bind="carManager.carDetail.license.buyPriceWan + '万'"></td>
				<!-- <td>行驶证照片</td>
				<td>
					<a ng-click="showImg(carManager.carDetail.carLiscenseImg)" ng-if="carManager.carDetail.carLiscenseImg">点击查看</a>
				</td> -->
			</tr>
		</tbody>
	</table>


	<!-- <p><img src="{{carManager.carDetail.carLiscenseImg}}" alt="" style="max-width: 100%;"></p> -->

</div>

<div>
	<h3 class="clearfix">修改记录</h3>
	<div class="col-md-5">
		<table class="table table-bordered">
			<thead>
			<tr>
				<th class="tab-head">修改时间</th>
				<th class="tab-head">修改人员</th>
				<th class="tab-head">解绑原因</th>
			</tr>
			</thead>
			<tbody>
			<tr ng-repeat="log in carManager.carDetailModifyLogs" class="ng-scope">
				<td ng-bind="log.dateValue | date : 'yyyy-MM-dd HH:mm:ss'"></td>
				<td ng-bind="log.operator"></td>
				<td ng-bind="switchReason(log.rejectCode)"></td>
			</tr>
			</tbody>
		</table>
	</div>


</div>
<p>
	<span ng-if="!carManager.carDetail.carLiscenseImg">没有行驶证图片</span>
	<img ng-src="{{carManager.carDetail.carLiscenseImg}}" style="max-width: 100%;" ng-if="carManager.carDetail.carLiscenseImg" alt="">
	<img ng-src="{{carManager.carDetail.carLiscenseImgCopy}}" style="max-width: 100%;" ng-if="carManager.carDetail.carLiscenseImgCopy" alt="">
</p>

<script>
	angular.companyId = "${id}";
	seajs.use(['js/controller/common/app','js/controller/vehicle/vehicleDetail'], function () {
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
