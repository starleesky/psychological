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


        <%--<div class="form-group pull-left">--%>
                <%--<select class="form-control" name="auditStatus" ng-change="list.fetch()"--%>
                        <%--ng-model="list.filter.sellType">--%>
                    <%--<option value="">销售方式</option>--%>
                    <%--<option value="0">出售</option>--%>
                    <%--<option value="1">租赁</option>--%>
                    <%--<option value="2">求购</option>--%>
                    <%--<option value="3">求租</option>--%>
                <%--</select>--%>
            <%--</div>--%>
            <%--<div class="form-group pull-left">--%>
                <%--<select class="form-control" name="auditStatus" ng-change="list.fetch()" ng-model="list.filter.status">--%>
                    <%--<option value="">认证状态</option>--%>
                    <%--<option value="1">待审核</option>--%>
                    <%--<option value="2">发布</option>--%>
                    <%--<option value="3">下架</option>--%>
                    <%--<option value="4">已售</option>--%>
                    <%--<option value="0">草稿箱</option>--%>
                <%--</select>--%>
            <%--</div>--%>

        </form>
        <!-- </div> -->
    </div>
</div>
<div class="line">
    <table class="table table-bordered">
        <thead>
        <tr>
            <th WIDTH="80px">预约编号</th>
            <th WIDTH="200px">所属单位</th>
            <th WIDTH="150px">姓名</th>
            <th WIDTH="60px">性别</th>
            <th WIDTH="60px">年龄</th>
            <th>预约需求描述</th>
            <th WIDTH="200px">预约申请时间</th>
            <th WIDTH="100px">手机号码</th>
        </tr>
        </thead>
        <tbody>
        <tr ng-repeat="c in list.data.items">
            <td ng-bind="c.id"></td>
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
            <td ng-bind="c.remark"></td>
            <td ng-bind="c.subscribeTime | date : 'yyyy-MM-dd HH:mm:ss'"></td>
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

<script>

    angular.just = true;
    angular.listType = 1;
    seajs.use(['js/controller/common/app', 'js/controller/user/subscribe'], function () {
        angular.bootstrap(document, ['App']);
    });

</script>