
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<c:set var="ctx" value="${pageContext['request'].contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>汤森机械网-用户信息</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width,user-scalable=no" />
    <link rel="stylesheet" href="${ctx}/wap/css/module/user-info.css?v=1" type="text/css" charset="utf-8">
</head>
<body>
<!--head begin-->
<%@ include file = "header.jsp" %>
<!--head end-->
<div class="page-view">
    <section class="mod-add-company-info">
        <form class="ui-form" id = "userInfo" method = "POST">
        <input type="hidden" name = "id" value = "${bean.id}" >
            <div class="ui-form-mod info clearfix">
                <p>您好${bean.userName}&nbsp;&nbsp;欢迎使用汤森机械网. </p>
                <c:if test="${not empty bean.headIcon}">
                <img src="${ctx}${bean.headIcon}" class="f-l"  />
                </c:if>
                <div class="info-desc f-l">
                    <a href="javascript:;" class="current jAddImg">上传LOGO</a>
                    <a href="javascript:;" class="jDelImg">删除LOGO</a>
                </div>
 					<div class="ui-form-mod">
                    <div class="ui-form-bd upload-img">
                        <a href="javascript:;" node-type="uploadButton">
                            <span class="upload-txt">选择图片</span>
                        </a>

                    </div>
                </div>
            </div>
            <div class="ui-form-mod">
                <label class="ui-form-hd">姓名<em>*</em></label>
                <div class="ui-form-bd">
                    <input type="text"  class="ui-input" id = "realName" name = "realName" value="${bean.realName}" />
                </div>
            </div>
            <div class="ui-form-mod">
                <label class="ui-form-hd">手机</label>
                <div class="ui-form-bd">
                    <input type="text" name = "mobile"  id = "mobile" value="${bean.mobile}" />
                </div>
            </div>
            <div class="ui-form-mod">
                <label class="ui-form-hd">QQ</label>
                <div class="ui-form-bd">
                    <input type="text" name = "qq" id = "qq" value="${bean.qq}" />
                </div>
            </div>
            <div class="ui-form-mod">
                <label class="ui-form-hd">微信</label>
                <div class="ui-form-bd">
                    <input type="text" name = "weixinAccount"  id = "weixinAccount" value="${bean.weixinAccount}" />
                </div>
            </div>
            <div class="ui-form-mod">
                <label class="ui-form-hd">省份<em>*</em></label>
                <div class="ui-form-bd">
                    <select class="regionProvice"  name="regionProvice" id="regionProvice" validate="required:true" >
                    <option>请选择省份</option>
                    </select>
                </div>
            </div>

            <div class="ui-form-mod">
                <label class="ui-form-hd">城市<em>*</em></label>
                <div class="ui-form-bd">
                   <select class="regionCity"  name="regionCity" id="regionCity" validate="required:true" >
                   	<option>请选择城市</option>
                   </select>                   
                </div>
            </div>
            <div class="ui-form-mod">
                <label class="ui-form-hd"><input type="checkbox" id = "checkbox" name="upPwd"  />更改密码<em>*</em></label>
            </div>
            <div class="ui-form-mod pwd">
                <label class="ui-form-hd">当前密码<em>*</em></label>
                <div class="ui-form-bd">
                    <input type="password" name = "oldPassword" id = "oldPassword" />
                </div>
            </div>
            <div class="ui-form-mod pwd">
                <label class="ui-form-hd">新密码<em>*</em></label>
                <div class="ui-form-bd">
                    <input type="password" name = "password" id = "password"/>
                </div>
            </div>
            <div class="ui-form-mod pwd">
                <label class="ui-form-hd">确认密码<em>*</em></label>
                <div class="ui-form-bd">
                    <input type="password" name = "rePassword" id = "rePassword" />
                </div>
            </div>
            <div class="ui-form-mod">
                <label class="ui-form-hd">经营范围<em>*</em></label>
                <div class="ui-form-bd">
                     <select name = "businessScope">
                                <option <c:if test="${bean.businessScope == 1}">selected="selected"</c:if> value="1">工程机械</option>
                                <option <c:if test="${bean.businessScope == 2}">selected="selected"</c:if> value="2">农业机械</option>
                                <option <c:if test="${bean.businessScope == 3}">selected="selected"</c:if> value="3">矿山机械</option>
                                <option <c:if test="${bean.businessScope == 4}">selected="selected"</c:if> value="4">林业机械</option>
                                <option <c:if test="${bean.businessScope == 5}">selected="selected"</c:if> value="5">运输车辆</option>
                    </select>
                </div>
            </div>
            <div class="ui-form-mod">
                <label class="ui-form-hd">经营性质<em>*</em></label>
                <div class="ui-form-bd">
                     <select name = "businessNature">
                         <option <c:if test="${bean.businessNature == 0}">selected="selected"</c:if> value="0">出售</option>
                         <option <c:if test="${bean.businessNature == 1}">selected="selected"</c:if> value="1">租赁</option>
                         <option <c:if test="${bean.businessNature == 2}">selected="selected"</c:if> value="2">求购</option>
                         <option <c:if test="${bean.businessNature == 3}">selected="selected"</c:if> value="3">求租</option>
                     </select>
                </div>
            </div>

            <div class="field-submit">
                 <a href="javascrpt:;" class="ui-button ui-button-submit" id="jSubmit">保存</a>
            </div>
        </form>
    </section>
</div>
<%@ include file = "footer.jsp" %>
<script type="text/javascript" src="${ctx}/wap/js/require.js"></script>
<script type="text/javascript" src="${ctx}/wap/js/app.js"></script>
<script type="text/javascript">
var ctx = "${ctx}";
    require(['module/user-info']);
</script>
</body></html>
