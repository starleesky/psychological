<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<!DOCTYPE html>
	<html>
	<head>
	<%@ include file = "meta.jsp" %>
</head>
<body>
	<div class="hd">
        <h3 class="title">${statusMc }</h3>
        <input type="hidden" id="curStatus" value="${status }">
    </div>
	<div class="bd">
	    <a href="${ctx }/infomation/infoList?status=2" status="2">
	        <span class="num">${cnt_sj }</span>
	        <span class="num-desc">上架</span>
	    </a>
	    <a href="${ctx }/infomation/infoList?status=3" status="3">
	        <span class="num">${cnt_ys }</span>
	        <span class="num-desc">已售</span>
	    </a>
	    <a href="${ctx }/infomation/infoList?status=4" status="4">
	        <span class="num">${cnt_xj }</span>
	        <span class="num-desc">下架</span>
	    </a>
	    <a href="${ctx }/infomation/infoList?status=0" status="0">
	        <span class="num">${cnt_cg }</span>
	        <span class="num-desc">草稿</span>
	    </a>
	    <a href="${ctx }/infomation/colleInfoList?status=9" status="9">
	        <span class="num">${cnt_sc }</span>
	        <span class="num-desc">收藏</span>
	    </a>
    </div>
</body>
</html>