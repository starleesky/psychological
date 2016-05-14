define(function(require,exports,module){

    var $ = require('jquery');
    var ajax = require('plug/ajax');
    var url = require('url');
    var Lazyload = require('plug/load/lazyload');
    var LazyStream = require('plug/load/lazystream');
    var Validator = require('plug/validate/validator');
    var box = require('plug/box');
    var scrollIcon = require('plug/scrollIcon');
    scrollIcon();
    var $view = $('.page-view-body')

    $view.on('click','.op-button-higSearch',function(){
        $view.find('.search-form').show();
    });

    $view.on('click','.op-button-return',function(){
        $view.find('.search-form').hide();
    });

    /** -------------  下拉页面查询数据 -----------------*/
    var lazyMore = new LazyStream('.jPage', {
    	plUrl: url.moreSearchInfo ,
        paramFormater: function(n) {
            var data = {};
            data.pageNo = n;
            data.isAjax = '1';
            data.groupsType = $('input[name=group-type]').val();
            
            /*业务数据*/
            data.status = '2';
            data.catagoryBigId = $("#catagoryBigId").val();
            data.catagoryBigName = $("#catagoryBigName").val();
            data.catagoryMidId = $("#catagoryMidId").val();
            data.catagoryMidName = $("#catagoryMidName").val();
            data.catagoryName = $("#catagoryName").val();
            data.brandId = $("#brandId").val();
            data.brandName = $("#brandName").val();
            data.modelId = $("#modelId").val();
            data.modelName = $("#modelName").val();
            data.sellType = $("#sellType").val();
            data.equipmentLocation = $("#equipmentLocation").val();
            data.equipmentCondition = $("#equipmentCondition").val();
            data.procedures = $("#procedures").val();
            data.order = $("#order").val();
            
            return data;
        },
        page:1,
        errorText: '<div class="loading">网络错误，点击重试</div>',
        loadingClass: 'loading',
        loadingText: '<div class="loading"><img src="' +ctx + '/wap/images/loading2.gif" class="load-gif" />正在加载，请稍后...</div>',
        load: function(el) {
            Lazyload.load($(el).find('.jImg'));
        },
        noAnyMore:''
    });
    /** ---------------- end --------------------------*/
   
    /** ---------------初始化查询条件下拉列表 ----------------**/
    var oBigGoodsCatagory = $(".bigGoodsCatagory");
    var oMiddleGoodsCatagory = $(".middleGoodsCatagory");
    var oSmallGoodsCatagory = $(".smallGoodsCatagory");
    
    var oRegionProvice = $(".regionProvice");
    
    var _bigCataId = $("input#catagoryBigId").val();
    var _midCataId = $("input#catagoryMidId").val();
    var _CataId = $("input#catagoryId").val();
    var _brandId = $("input#brandId").val();
    var _modelId = $("input#modelId").val();
    
    var _equipmentLocation = $("input#equipmentLocation").val();

    var oBrand = $(".brand");
    var oModels = $(".models");

    //产品大类
    var getBig = function () {
        $.getJSON(url.listGoodsCatagory, {id: '0'}, function (data) {
            var oBig_html = "";
            if(_bigCataId == '') {
            	oBig_html = "<option value='' selected></option>";	
            }else {
            	oBig_html = "<option value=''></option>";	
            }
            $.each(data.object, function (i, data) {
            	if(_bigCataId == data.id) {
            		oBig_html += "<option value='" + data.id + "' selected>" + data.catagoryName + "</option>";        		
            	}else {
            		oBig_html += "<option value='" + data.id + "'>" + data.catagoryName + "</option>";
            	}
            });
            oBigGoodsCatagory.html(oBig_html);
            getMiddle();
        });
    }

    oBigGoodsCatagory.change(function () {
    	$("#catagoryBigId").val($('#infoSearchForm').find('select[name=catagoryBig]').val());
    	$("#catagoryBigName").val($('#infoSearchForm').find('select[name=catagoryBig]').find("option:selected").text());
        getMiddle();
    });

    //产品组
    var getMiddle = function () {
        var n = oBigGoodsCatagory.val() == '' ? '-9999999' : oBigGoodsCatagory.val();
        $.getJSON(url.listGoodsCatagory, {id: n}, function (data) {
            var oMiddle_html = "<option value=''></option>";
            $.each(data.object, function (i, data) {
            	if(_midCataId == data.id) {
            		oMiddle_html += "<option value='" + data.id + "' selected>" + data.catagoryName + "</option>";
            	}else {
            		oMiddle_html += "<option value='" + data.id + "'>" + data.catagoryName + "</option>";
            	}
            });
            oMiddleGoodsCatagory.html(oMiddle_html);
            getSmall();
        });
    }

    oMiddleGoodsCatagory.change(function () {
    	$("#catagoryMidId").val($('#infoSearchForm').find('select[name=catagoryMid]').val());
    	$("#catagoryMidName").val($('#infoSearchForm').find('select[name=catagoryMid]').find("option:selected").text());
        getSmall();
    });

    //产品类型
    var getSmall = function () {
        var n = oMiddleGoodsCatagory.val() == '' ? '-9999999' : oMiddleGoodsCatagory.val();
        $.getJSON(url.listGoodsCatagory, {id: n}, function (data) {
            var oSamll_html = "<option value=''></option>";
            $.each(data.object, function (i, data) {
            	if(_CataId == data.id) {
            		 oSamll_html += "<option value='" + data.id + "' selected>" + data.catagoryName + "</option>";
            	}else {
            		 oSamll_html += "<option value='" + data.id + "'>" + data.catagoryName + "</option>";
            	}
               
            });
            oSmallGoodsCatagory.html(oSamll_html);
            getBrand();
        });
    }

    oSmallGoodsCatagory.change(function () {
    	$("#catagoryId").val($('#infoSearchForm').find('select[name=catagorySmall]').val());
    	$("#catagoryName").val($('#infoSearchForm').find('select[name=catagorySmall]').find("option:selected").text());
        getBrand();
    });

    //品牌
    var getBrand = function () {
        var n = oSmallGoodsCatagory.val() == '' ? '-9999999' : oSmallGoodsCatagory.val();
        $.getJSON(url.listBrand, {catagoryId: n}, function (data) {
            var oBrand_html = "<option value=''></option>";
            $.each(data.object, function (i, data) {
            	if(_brandId == data.id) {
            		oBrand_html += "<option value='" + data.id + "' selected>" + data.firstLetter +" "+ data.brandName + "</option>";
            	}else {
            		oBrand_html += "<option value='" + data.id + "'>" +data.firstLetter +" "+ data.brandName + "</option>";
            	}
            });
            oBrand.html(oBrand_html);
            getModels();
        });
    }

    oBrand.change(function () {
    	$("#brandId").val($('#infoSearchForm').find('select[name=brand]').val());
    	$("#brandName").val($('#infoSearchForm').find('select[name=brand]').find("option:selected").text());
        getModels();
    });

    //型号
    var getModels = function () {
        var n = oBrand.val() == '' ? '-9999999' : oBrand.val();
        $.getJSON(url.listModels, {brandId: n}, function (data) {
            var oModels_html = "<option value=''></option>";
            $.each(data.object, function (i, data) {
            	if(_modelId == data.id) {
            		oModels_html += "<option value='" + data.id + "' selected>" + data.modelsName + "</option>";
            	}else {
            		oModels_html += "<option value='" + data.id + "'>" + data.modelsName + "</option>";
            	}
            });
            oModels.html(oModels_html);
        });
    }
    
    oModels.change(function () {
    	$("#modelId").val($('#infoSearchForm').find('select[name=models]').val());
    	$("#modelName").val($('#infoSearchForm').find('select[name=models]').find("option:selected").text());
    });
    
    //初始化设备地址下拉框
    var getProvice = function () {
    	var oProvice_html;
    	if(_equipmentLocation == '') {
    		oProvice_html = "<option value='' selected></option>";	
        }else {
        	oProvice_html = "<option value=''></option>";	
        }
        $.getJSON(url.listRegion, {id: '0'}, function (data) {
            $.each(data.object, function (i, data) {
            	if(_equipmentLocation == data.regionName) {
            		oProvice_html += "<option value='" + data.regionName + "' selected>" + data.regionName + "</option>";
            	}else {
            		oProvice_html += "<option value='" + data.regionName + "'>" + data.regionName + "</option>";
            	}
            });
            oRegionProvice.html(oProvice_html);
        });
    }
    
    oRegionProvice.change(function () {
    	$("#equipmentLocation").val($('#infoSearchForm').find('select[name=equipmentLocationSel]').val());
    });
    
    var initOtherSel = function() {
    	$("select[name='sellTypeSel']").val($("#sellType").val());
    	$("select[name='equipmentConditionSel']").val($("#equipmentCondition").val());
    	$("select[name='proceduresSel']").val($("#procedures").val());
    	//$("select[name='srcSel']").val($("#src").val());
    	//$("select[name='equipYearSel']").val($("#equipYear").val());
    	
    }
    
    //初始化其他下拉框
    initOtherSel();
    
    //初始化设备地点下拉框
    getProvice();

    //初始化产品大类
    getBig();
    
    /** -------------------end ------------------------**/
    $("#searchResult").click(function () {
        $('#infoSearchForm').submit();
    });
    
    $("select[name='orderSel']").change(function(){
    	$("#order").val(this.value);
    	 $('#infoSearchForm').submit();
    });
    

});