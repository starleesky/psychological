define(['jquery', 'url', 'plug/ajax','plug/box','plug/load/lazyload','plug/load/lazystream','plug/selectPro'], function ($, url, ajax,box,Lazyload,LazyStream) {
	//$, url, ajax, box, Validator,Uploader
	//'jquery', 'url', 'plug/ajax', 'plug/box', 'plug/validate/validateMethod','plug/uploader/uploader-list','plug/imgLoading','plug/selectPro'
	 var oBigGoodsCatagory = $(".bigGoodsCatagory");
	    var oMiddleGoodsCatagory = $(".middleGoodsCatagory");
	    var oSmallGoodsCatagory = $(".smallGoodsCatagory");

	    var oBrand = $(".brand");
	    var oModels = $(".models");
	    //产品大类
	    var getBig = function () {
	        $.getJSON(url.listGoodsCatagory, {id: '0'}, function (data) {
	            var oBig_html;
	            $.each(data.object, function (i, data) {
	                oBig_html += "<option value='" + data.id + "'>" + data.catagoryName + "</option>";
	            });
	            oBigGoodsCatagory.html(oBig_html);
	            getMiddle();
	        });
	    }

	    oBigGoodsCatagory.change(function () {
	        getMiddle();
	    });

	    //产品组
	    var getMiddle = function () {
	        var n = oBigGoodsCatagory.val();
	        $.getJSON(url.listGoodsCatagory, {id: n}, function (data) {
	            var oMiddle_html;
	            $.each(data.object, function (i, data) {
	                oMiddle_html += "<option value='" + data.id + "'>" + data.catagoryName + "</option>";
	            });
	            oMiddleGoodsCatagory.html(oMiddle_html);
	            getSmall();
	        });
	    }

	    oMiddleGoodsCatagory.change(function () {
	        getSmall();
	    });

	    //产品类型
	    var getSmall = function () {
	        var n = oMiddleGoodsCatagory.val();
	        $.getJSON(url.listGoodsCatagory, {id: n}, function (data) {
	            var oSamll_html;
	            $.each(data.object, function (i, data) {
	                oSamll_html += "<option value='" + data.id + "'>" + data.catagoryName + "</option>";
	            });
	            oSmallGoodsCatagory.html(oSamll_html);
	            getBrand();
	        });
	    }

	    oSmallGoodsCatagory.change(function () {
	        getBrand();
	    });

	    //品牌
	    var getBrand = function () {
	        var n = oSmallGoodsCatagory.val();
	        $.getJSON(url.listBrand, {catagoryId: n}, function (data) {
	            var oBrand_html;
	            $.each(data.object, function (i, data) {
	                oBrand_html += "<option value='" + data.id + "'>" + data.brandName + "</option>";
	            });
	            oBrand.html(oBrand_html);
	            getModels();
	        });
	    }

	    oBrand.change(function () {
	        getModels();
	    });

	    //型号
	    var getModels = function () {
	        var n = oBrand.val();
	        $.getJSON(url.listModels, {brandId: n}, function (data) {
	            var oModels_html;
	            $.each(data.object, function (i, data) {
	                oModels_html += "<option value='" + data.id + "'>" + data.modelsName + "</option>";
	            });
	            oModels.html(oModels_html);
	        });
	    }

	    //初始化产品大类
	    getBig();
	    
	    
	//根据当前选中的信息状态，设定class=current
	var curStatus = $("#curStatus").val();
	var curClass = function() {
		
		$("a[status='" + curStatus + "']").attr("class","current");
	}
	
	curClass();
	
	$('body').on('click','.jNewUp',function(){
        var url = $(this).attr('data-url');
        box.confirm('是否确认重新上架',function(){
            window.location.href = url;
        },function(){},this);
    });

    //var Lazyload = require(['plug/load/lazyload']);
    //var LazyStream = require(['plug/load/lazystream']);

    var $view = $('.page-view-body')

    $view.on('click','.op-button-higSearch',function(){
        $view.find('.search-form').show();
    });

    $view.on('click','.op-button-return',function(){
        $view.find('.search-form').hide();
    });

    var lazyMore = new LazyStream('.jPage', {
        plUrl: url.moreInfo ,
        paramFormater: function(n) {
            var data = {};
            data.pageNo = n;
            data.isAjax = '1';
            data.groupsType = $("input[name='group-type']").val();
            data.status = curStatus;
            return data;
        },
        
        page:1,
        
        errorText: '<div class="loading">网络错误，点击重试</div>',
        loadingClass: 'loading',
        loadingText: '<div class="loading"><img src="' +ctx + '/wap/images/loading2.gif" class="load-gif" />正在加载，请稍后...</div>',
        //
        load: function(el) {
            Lazyload.load($(el).find('.jImg'));
        },
        noAnyMore:'<div class="loading">sorry,已经没有下一页了...</div>'
    });
});