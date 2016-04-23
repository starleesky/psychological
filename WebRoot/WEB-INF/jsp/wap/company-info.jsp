<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext['request'].contextPath}"/>
<c:set var="imgHost" value="${initParam.imgHost}"/>
<!DOCTYPE html>
<html>
<head>
    <title>汤森机械网-公司信息</title>
    <%@ include file = "meta.jsp" %>
    <link rel="stylesheet" href="${ctx}/wap/css/module/company-info.css?v=1" type="text/css" charset="utf-8">
</head>
<body>
<!--head begin-->
<%@ include file="header.jsp" %>
<!--head end-->
<div class="page-view">
    <div class="page-view-body">
        <section class="mod-info">
            <div class="hd">
                <h3 class="title">添加公司信息</h3>
            </div>
            <div class="bd">
                <a href="${ctx }/infomation/infoList/my?status=2" status="2">
                    <span class="num">${cnt_sj }</span>
                    <span class="num-desc">上架</span>
                </a>
                <a href="${ctx }/infomation/infoList/my?status=3" status="3">
                    <span class="num">${cnt_ys }</span>
                    <span class="num-desc">已售</span>
                </a>
                <a href="${ctx }/infomation/infoList/my?status=4" status="4">
                    <span class="num">${cnt_xj }</span>
                    <span class="num-desc">下架</span>
                </a>
                <a href="${ctx }/infomation/infoList/my?status=0" status="0">
                    <span class="num">${cnt_cg }</span>
                    <span class="num-desc">草稿</span>
                </a>
                <a href="${ctx }/infomation/colleInfoList/my?status=9" status="9">
                    <span class="num">${cnt_sc }</span>
                    <span class="num-desc">收藏</span>
                </a>
            </div>
        </section>
        <section class="mod-add-company-info">
            <form class="ui-form" id="companyForm">
                <%--<div class="ui-form-mod">--%>
                <%--<label class="ui-form-hd">公司LOGO<em>*</em></label>--%>

                <%--<div class="ui-form-bd upload-img<c:if test="${company.createBy!=null}">edit</c:if>">--%>
                    <%--<a href="javascript:;" node-type="uploadButton">--%>
                        <%--<span class="upload-txt">选择图片</span>--%>
                    <%--</a>--%>
                    <%--<c:if test="${company.createBy!=null}">--%>
                        <%--<div class="upload-div">--%>
                            <%--<img src="${ctx}${company.createBy}">--%>
                            <%--<b class="icon-delete" node-type="deleteImgBtn"></b>--%>
                        <%--</div>--%>
                    <%--</c:if>--%>


                <%--</div>--%>
                <%--</div>--%>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">公司LOGO<em>*</em></label>
                    <div class="ui-form-bd upload-img <c:if test="${company.createBy!=null}">edit</c:if>">
                        <a href="javascript:;" node-type="uploadButton">
                            <span class="upload-txt">选择图片</span>
                            <input type="hidden" name="_UPLOAD_00" value="${company.createBy}">
                        </a>
                        <c:if test="${company.createBy!=null}">
                            <div class="upload-div">
                                <img src="${initParam.imgHost}${company.createBy}">
                                <b class="icon-delete" node-type="deleteImgBtn"></b>
                            </div>
                        </c:if>
                    </div>
                </div>



                <div class="ui-form-mod">
                    <label class="ui-form-hd">公司名称<em>*</em></label>
                    <div class="ui-form-bd">
                        <input type="text" name="companyName" class="ui-input" value="${company.companyName}"/>
                    </div>
                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">电话</label>
                    <div class="ui-form-bd">
                        <input type="text" name="telephone" value="${company.telephone}"/>
                    </div>
                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">传真</label>
                    <div class="ui-form-bd">
                        <input type="text" name="fax" value="${company.fax}"/>
                    </div>
                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">所在省份</label>
                    <div class="ui-form-bd">
                        <select class="regionProvice" name="regionProvice" id="regionProvice" validate="required:true">
                            <c:if test="${company.provinceName!=null}">
                                <option value="${company.provinceId}">${company.provinceName}</option>
                            </c:if>
                            <option>请选择省份</option>
                        </select>
                    </div>
                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">所在城市</label>
                    <div class="ui-form-bd">
                        <select class="regionCity" name="regionCity" id="regionCity" validate="required:true" >
                            <c:if test="${company.cityName!=null}">
                                <option value="${company.cityId}">${company.cityName}</option>
                            </c:if>
                            <option>请选择城市</option>
                        </select>
                    </div>
                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">详细地址<em>*</em></label>
                    <div class="ui-form-bd">
                        <input type="text" name="address" value="${company.address}"/>
                    </div>
                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">公司介绍<em>*</em></label>
                    <div class="ui-form-bd">
                        <textarea name="introduction">${company.introduction}</textarea>
                    </div>
                </div>
                <!--div class="ui-form-mod">
                    <label class="ui-form-hd">营业执照副本<em>*</em></label>
                    <div class="ui-form-bd">
                        <span class=" ">
                            <input type="file" name="file[]" />
                            上传营业执照副本
                        </span>
                        <a href="javascript:;" class="jDelFile" style="display: none">删除副本</a>
                    </div>
                </div>
                <div class="ui-form-mod">

                    <label class="ui-form-hd">组织机构代码证<em>*</em></label>
                    <div class="ui-form-bd">
                        <span class=" ">
                            <input type="file" name="file[]" />
                            上传组织机构代码证
                        </span>
                        <a href="javascript:;" class="jDelFile" style="display: none">删除代码证</a>
                    </div>
                </div-->
                <div class="ui-form-mod">
                    <label class="ui-form-hd">营业执照副本<em>*</em></label>
                    <div class="ui-form-bd upload-img <c:if test="${company.businessLicenseImageUrl!=null}">edit</c:if>">
                        <a href="javascript:;" node-type="uploadButton">
                            <span class="upload-txt">选择图片</span>
                            <input type="hidden" name="_UPLOAD_01" value="${company.businessLicenseImageUrl}/small">
                        </a>
                        <c:if test="${company.businessLicenseImageUrl!=null}">
                            <div class="upload-div">
                                <img src="${initParam.imgHost}${company.businessLicenseImageUrl}/small">
                                <b class="icon-delete" node-type="deleteImgBtn"></b>
                            </div>
                        </c:if>
                    </div>
                </div>
                <div class="ui-form-mod">

                    <label class="ui-form-hd">组织机构代码证<em>*</em></label>
                    <div class="ui-form-bd upload-img <c:if test="${company.organizationCodeImageUrl!=null}">edit</c:if>">
                        <a href="javascript:;" node-type="uploadButton">
                            <span class="upload-txt">选择图片</span>
                            <input type="hidden" name="_UPLOAD_02" value="${company.organizationCodeImageUrl}/small">
                        </a>
                        <c:if test="${company.organizationCodeImageUrl!=null}">
                            <div class="upload-div">
                                <img src="${initParam.imgHost}${company.organizationCodeImageUrl}/small">
                                <b class="icon-delete" node-type="deleteImgBtn"></b>
                            </div>
                        </c:if>


                    </div>
                </div>
                <div class="field-submit">
                    <a href="javascript:;" class="ui-button ui-button-blue " id="jSubmit">提交</a>
                </div>
            </form>
        </section>
    </div>
</div>
<%@ include file="footer.jsp" %>
<script type="text/javascript" src="${ctx}/wap/js/require.js"></script>
<script type="text/javascript" src="${ctx}/wap/js/app.js"></script>
<script type="text/javascript">
    var ctx = "${ctx}";
    var status = "${company.status}";
    var companyId = "${company.id}";
    require(['module/company-info']);
</script>
</body>
</html>
