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
        <div class="row">
            ...
        </div>
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

            <div class="form-group">
                <div class="input-group">
                    <input type="text" class="form-control" datepicker-popup is-open="list.startopened"  placeholder="开始时间"
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
                    <input type="text" class="form-control" datepicker-popup is-open="list.endopened" placeholder="结束时间"
                           ng-model="list.filter.endDate" ng-change="list.fetch()"/>
                    <span class="input-group-btn">
		          <button type="button" class="btn btn-default" ng-click="list.openCal($event, 'end')"><i
                          class="glyphicon glyphicon-calendar"></i></button>
		        </span>
                </div>
            </div>

            <button type="submit" class="btn btn-primary">搜索</button>
            <div class="form-group pull-right">
                <button class="btn btn-primary" ng-click="batchOperate2(1)">导出</button>
                <button class="btn btn-primary" ng-click="batchOperate2(2)">导出全部</button>
            </div>
            <%--<div class="form-group pull-left">--%>
                <%--<select class="form-control" name="auditStatus" ng-change="list.fetch()"--%>
                        <%--ng-model="list.filter.isActivate">--%>
                    <%--<option value="">是否激活</option>--%>
                    <%--<option value="T">是</option>--%>
                    <%--<option value="F">否</option>--%>
                <%--</select>--%>
            <%--</div>--%>
            <div class="form-group pull-left">
                <select class="form-control" name="evaType" ng-change="list.fetch()"
                        ng-model="list.filter.evaType">
                    <option value="">量表分类</option>
                    <option value="1">MBTI</option>
                    <option value="2">OQ45</option>
                    <option value="3">SCL90</option>
                </select>
            </div>
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
            <th>-</th>
            <th>评测编号</th>
            <th>测试量量表名称</th>
            <th WIDTH="200px">测试时间</th>
            <th>测试者</th>
            <th WIDTH="300px">测试结果</th>
            <th WIDTH="300px">评分标准</th>
            <th>手机号码</th>
        </tr>
        </thead>
        <tbody>
        <tr ng-repeat="c in list.data.items">
            <th><input data-ng-model="chk" type="checkbox" data-ng-click="check(c.id,chk)"/></th>

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
            <td ng-bind="c.result"></td>
            <td>
                <div ng-switch on="c.evaType">
                    <span ng-switch-when="1"> </span>
                    <span ng-switch-when="2">
                    ·总分＞63分，存在临床意义的症状<br>
·情绪＞35分，提示存在情绪不适<br>
·人际关系＞15分，提示存在人际关系不适应<br>
·社会功能＞13分，提示存在社会功能适应问题

</span>
                    <span ng-switch-when="3">躯体化 1.37+0.48，敌对性 1.46+0.55，强迫 1.62+0.58，恐怖 1.23+0.41，
人际关系 1.65+0.61，偏执 1.43+0.57
抑郁1.5+0.59，精神病性 1.29+0.42，焦虑 1.39+0.43
</span>
                </div>
            </td>
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