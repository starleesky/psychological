<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext['request'].contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>汤森机械网-公司信息</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width,user-scalable=no" />
    <link rel="stylesheet" href="css/module/company-info.css?v=1" type="text/css" charset="utf-8">
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
            <div class="pro-nav">
                <a href="${ctx }/infomation/infoList?status=2">
                    <span class="num">${cnt_sj}</span>
                    <span class="num-desc">上架</span>
                </a>
                <a href="${ctx }/infomation/infoList?status=3" >
                    <span class="num">${cnt_ys}</span>
                    <span class="num-desc">已售</span>
                </a>
                <a href="${ctx }/infomation/infoList?status=4" >
                    <span class="num">${cnt_xj}</span>
                    <span class="num-desc">下架</span>
                </a>
                <a href="${ctx }/infomation/infoList?status=0" >
                    <span class="num">${cnt_cg}</span>
                    <span class="num-desc">草稿</span>
                </a>
            </div>
        </section>
        <section class="mod-add-company-info">
            <form class="ui-form" id="companyForm">
                <label class="ui-form-hd">公司LOGO<em>*</em></label>
                <div class="ui-form-bd upload-img edit">
                    <a href="javascript:;" node-type="uploadButton">
                        <span class="upload-txt">选择图片</span>
                    </a>
                    <div class="upload-div">
                        <img src="http://img4.bbgstatic.com/14fa1ec9e18_bc_2999191fce3a29942c38d72f42b40f53_464x785.jpeg">
                        <b class="icon-delete" node-type="deleteImgBtn"></b>
                    </div>

                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">公司名称<em>*</em></label>
                    <div class="ui-form-bd">
                        <input type="text" name="companyName" class="ui-input"/>
                    </div>
                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">电话</label>
                    <div class="ui-form-bd">
                        <input type="text" name="telephone"/>
                    </div>
                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">传真</label>
                    <div class="ui-form-bd">
                        <input type="text" name="fax"/>
                    </div>
                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">所在省份</label>
                    <div class="ui-form-bd">
                        <select class="regionProvice"  name="regionProvice" id="regionProvice" validate="required:true" ><option>请选择省份</option></select>
                    </div>
                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">所在城市</label>
                    <div class="ui-form-bd">
                        <select class="regionCity"  name="regionCity" id="regionCity" validate="required:true" ><option>请选择城市</option></select>
                    </div>
                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">详细地址<em>*</em></label>
                    <div class="ui-form-bd">
                        <input type="text" name="address"/>
                    </div>
                </div>
                <div class="ui-form-mod">
                    <label class="ui-form-hd">公司介绍<em>*</em></label>
                    <div class="ui-form-bd">
                        <textarea name="introduction"></textarea>
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
                    <div class="ui-form-bd upload-img">
                        <a href="javascript:;" node-type="uploadButton">
                            <span class="upload-txt">选择图片</span>
                        </a>

                    </div>
                </div>
                <div class="ui-form-mod">

                    <label class="ui-form-hd">组织机构代码证<em>*</em></label>
                    <div class="ui-form-bd upload-img edit">
                        <a href="javascript:;" node-type="uploadButton">
                            <span class="upload-txt">选择图片</span>
                        </a>
                        <div class="upload-div">
                            <img src="http://img4.bbgstatic.com/14fa1ec9e18_bc_2999191fce3a29942c38d72f42b40f53_464x785.jpeg">
                            <b class="icon-delete" node-type="deleteImgBtn"></b>
                        </div>

                    </div>
                </div>
                <div class="field-submit">
                    <input type="button" class="ui-button ui-button-blue " id="jSubmit" value="提交">
                </div>
            </form>
        </section>
    </div>
</div>
<%@ include file="footer.jsp" %>
<script type="text/javascript" src="js/require.js"></script>
<script type="text/javascript" src="js/app.js"></script>
<script type="text/javascript">
    var ctx = "${ctx}";
    require(['module/company-info']);
</script>
</body>
</html>
