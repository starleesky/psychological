define(['jquery', 'url', 'plug/ajax', 'plug/box', 'plug/validate/validateMethod','plug/uploader/uploader-list'], function ($, url, ajax, box, Validator,Uploader) {

    var oBigGoodsCatagory = $(".bigGoodsCatagory");
    var oMiddleGoodsCatagory = $(".middleGoodsCatagory");
    var oSmallGoodsCatagory = $(".smallGoodsCatagory");
    
    var _bigCataId = $("#_bigCataId").val();
    var _midCataId = $("#_midCataId").val();
    var _CataId = $("#_CataId").val();
    var _brandId = $("#_brandId").val();
    var _modelId = $("#_modelId").val();

    var oBrand = $(".brand");
    var oModels = $(".models");

    //产品大类
    var getBig = function () {
        $.getJSON(url.listGoodsCatagory, {id: '0'}, function (data) {
            var oBig_html;
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
        getMiddle();
    });

    //产品组
    var getMiddle = function () {
        var n = oBigGoodsCatagory.val();
        $.getJSON(url.listGoodsCatagory, {id: n}, function (data) {
            var oMiddle_html;
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
        getSmall();
    });

    //产品类型
    var getSmall = function () {
        var n = oMiddleGoodsCatagory.val();
        $.getJSON(url.listGoodsCatagory, {id: n}, function (data) {
            var oSamll_html;
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
        getBrand();
    });

    //品牌
    var getBrand = function () {
        var n = oSmallGoodsCatagory.val();
        $.getJSON(url.listBrand, {catagoryId: n}, function (data) {
            var oBrand_html;
            $.each(data.object, function (i, data) {
            	if(_brandId == data.id) {
            		oBrand_html += "<option value='" + data.id + "' selected>" + data.firstLetter +" "+ data.brandName + "</option>";
            	}else {
            		oBrand_html += "<option value='" + data.id + "'>" + data.firstLetter +" "+ data.brandName + "</option>";
            	}
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
            	if(_modelId == data.id) {
            		oModels_html += "<option value='" + data.id + "' selected>" + data.modelsName + "</option>";
            	}else {
            		oModels_html += "<option value='" + data.id + "'>" + data.modelsName + "</option>";
            	}
            });
            oModels.html(oModels_html);
        });
    }

    //初始化产品大类
    getBig();


    var oRegionProvice = $(".regionProvice");
    var oRegionCity = $(".regionCity");
    var oEquipmentLocation = $("#equipmentLocation");
    //省市
    var getProvice = function () {
        $.getJSON(url.listRegion, {id: '0'}, function (data) {
            var oProvice_html;
            var oldProvince = (oEquipmentLocation.val()).split("|")[0]
            $.each(data.object, function (i, data) {
            	if(oldProvince == data.regionName) {
            		oProvice_html += "<option value='" + data.id + "' selected>" + data.regionName + "</option>";
            	}else {
            		oProvice_html += "<option value='" + data.id + "'>" + data.regionName + "</option>";
            	}
            });
            oRegionProvice.html(oProvice_html);
            getCity();
        });
    }

    oRegionProvice.change(function () {
        getCity();
    });

    //产品组
    var getCity = function () {
        var n = oRegionProvice.val();
        $.getJSON(url.listRegion, {id: n}, function (data) {
            var oCity_html;
            var oldCity = (oEquipmentLocation.val()).split("|")[1];
            $.each(data.object, function (i, data) {
            	if(oldCity == data.regionName) {
            		oCity_html += "<option value='" + data.id + "' selected>" + data.regionName + "</option>";
            	}else {
            		oCity_html += "<option value='" + data.id + "'>" + data.regionName + "</option>";
            	}
            });
            oRegionCity.html(oCity_html);
        });
    };

    var initOtherSel = function() {
    	$("select[name='sellTypeSel']").val($("#sellType").val());
    	$("select[name='equipmentConditionSel']").val($("#equipmentCondition").val());
    	$("select[name='proceduresSel']").val($("#procedures").val());
    	$("select[name='srcSel']").val($("#src").val());
    	$("select[name='equipYearSel']").val($("#equipYear").val());
    	$("select[name='validTimeSel']").val($("#validTime").val());
    	
    }

    var initNewBrand = function() {
    	if(_brandId == null ||  _brandId == "" || _modelId == null || _modelId == "") {
	    	var _brandName = "<%=_brandName%>";
	    	var _modelName = "<%=_modelName%>";
	    	
	    	$('.desc-child').addClass('isHide');
	    	$(this).removeClass('open');
    	}
    }
    
    var initPriceUnit = function() {
    	var sellType = $('select[name=sellTypeSel]').val();
    	var priceUnit = $('input[name=priceUnit]').val();
    	initPriceUnitOpt(sellType,priceUnit);
    }
    
    var initPriceUnitOpt = function(sellType,priceUnit) {
    	var unitOptHtml = "";
        if(sellType == '0') {	//出售
        	unitOptHtml = "<option value='元'>元</option>";
        }else if(sellType == '1') {	//租赁
        	unitOptHtml += priceUnit =="元/天" ? "<option value='元/天' selected>元/天</option>" : "<option value='元/天'>元/天</option>";
        	unitOptHtml += priceUnit =="元/月" ? "<option value='元/月' selected>元/月</option>" : "<option value='元/月'>元/月</option>";
        	unitOptHtml += priceUnit =="元/小时" ? "<option value='元/小时' selected>元/小时</option>" : "<option value='元/小时'>元/小时</option>";
        	unitOptHtml += priceUnit =="元/亩" ? "<option value='元/亩' selected>元/亩</option>" : "<option value='元/亩'>元/亩</option>";
        	unitOptHtml += priceUnit =="元/吨" ? "<option value='元/吨' selected>元/吨</option>" : "<option value='元/吨'>元/吨</option>";
        	unitOptHtml += priceUnit =="元/立方" ? "<option value='元/立方' selected>元/立方</option>" : "<option value='元/立方'>元/立方</option>";
        	unitOptHtml += priceUnit =="元/公里" ? "<option value='元/公里' selected>元/公里</option>" : "<option value='元/公里'>元/公里</option>";
        }else if(sellType == '2') {	//求购
        	unitOptHtml = "<option value='元左右'>元左右</option>";
        }else if(sellType == '3') {	//求租
        	unitOptHtml += priceUnit =="元/天" ? "<option value='元/天' selected>元/天</option>" : "<option value='元/天'>元/天</option>";
        	unitOptHtml += priceUnit =="元/月" ? "<option value='元/月' selected>元/月</option>" : "<option value='元/月'>元/月</option>";
        	unitOptHtml += priceUnit =="元/小时" ? "<option value='元/小时' selected>元/小时</option>" : "<option value='元/小时'>元/小时</option>";
        	unitOptHtml += priceUnit =="元/亩" ? "<option value='元/亩' selected>元/亩</option>" : "<option value='元/亩'>元/亩</option>";
        	unitOptHtml += priceUnit =="元/吨" ? "<option value='元/吨' selected>元/吨</option>" : "<option value='元/吨'>元/吨</option>";
        	unitOptHtml += priceUnit =="元/立方" ? "<option value='元/立方' selected>元/立方</option>" : "<option value='元/立方'>元/立方</option>";
        	unitOptHtml += priceUnit =="元/公里" ? "<option value='元/公里' selected>元/公里</option>" : "<option value='元/公里'>元/公里</option>";
        }
        $("select[name='priceUnitSel']").html(unitOptHtml);
    }
    
    //初始省市
    getProvice();
    
    //初始化其他下拉框
    initOtherSel();
    
    //初始化手动添加品牌型号内容（如果有）
    initNewBrand();
    
    //初始化价格单位
    initPriceUnit();

    var status = 0;
    //信息提交
    Validator.validate('#informationFrom', {
        rules: {
            price: {
                required: true
            },
            remark: {
                maxlength: 140
            }
        },
        messages: {
            price: {
                required: '价格不能为空'
            },
            remark: {
                maxlength: '附言不能大于140个字'
            }

        },
        submitHandler: function (form) {
            $.post(
                url.saveInfo,
                {
                    id : $(form).find('input[name=id]').val(),
                    catagoryBigId: $(form).find('select[name=catagoryBig]').val(),
                    catagoryBigName: $(form).find('select[name=catagoryBig]').find("option:selected").text(),
                    catagoryMidId: $(form).find('select[name=catagoryMid]').val(),
                    catagoryMidName: $(form).find('select[name=catagoryMid]').find("option:selected").text(),
                    catagoryId: $(form).find('select[name=catagorySmall]').val(),
                    catagoryName: $(form).find('select[name=catagorySmall]').find("option:selected").text(),
                    brandId: $(form).find('select[name=brand]').val(),
                    brandName: $(form).find('select[name=brand]').find("option:selected").text(),
                    modelId: $(form).find('select[name=models]').val(),
                    modelName: $(form).find('select[name=models]').find("option:selected").text(),
                    newBrand: $(form).find('input[name=newBrand]').val(),
                    newModel: $(form).find('input[name=newModels]').val(),
                    sellType: $(form).find('select[name=sellTypeSel]').val(),
                    equipmentCondition: $(form).find('select[name=equipmentConditionSel]').val(),
                    procedures: $(form).find('select[name=proceduresSel]').val(),
                    src: $(form).find('select[name=srcSel]').val(),
                    equipYear: $(form).find('select[name=equipYearSel]').val(),
                    remark: $(form).find('textarea[name=remark]').val(),
                    provinceId: $(form).find('select[name=regionProvice]').val(),
                    provinceName: $(form).find('select[name=regionProvice]').find("option:selected").text(),
                    cityId: $(form).find('select[name=regionCity]').val(),
                    cityName: $(form).find('select[name=regionCity]').find("option:selected").text(),
                    validTime: $(form).find('select[name=validTime]').find("option:selected").val(),
                    price: $(form).find('input[name=price]').val(),
                    workTime: $(form).find('input[name=workTime]').val(),
                    serialNum: $(form).find('input[name=serialNum]').val(),
                    priceUnit: $(form).find('select[name=priceUnitSel]').val(),
                    imgUrl:array,
                    status:status
                },
                function (data) {
                    var str= JSON.parse(data);
                    submitIng=false;
                    if (str.result) {
                        box.ok(data.message);
                        window.location.href = ctx+url.infoList+"?status=0";

                    } else {
                        box.error(str.message);
                    }

                });
        },
        showError: function (elem, msg) {
            box.error(msg, elem);
        },
        success: null
    });
    var submitIng=false;
    var array="";
    $("#jSave").click(function () {
        if(submitIng){
            box.error('不能重复提交！');
            return;
        }
        status = 0;
        var modelsId = oModels.val();
        if (modelsId == null || modelsId == "") {
            alert('型号未选择');
            return;
        }
        var  _text = $("input[name^='_UPLOAD_']");
        for(var i=0;i<_text.length;i++){
            array+=_text[i].value+",";
        }
        submitIng=true;
        ValidBrandAndModel();
        $('#informationFrom').submit();

    });

    $("#jSubmit").click(function () {
        //alert('提交之前请先保存');
        status = 1;
        if(submitIng){
            box.error('不能重复提交！');
            return;
        }
        var   _text = $("input[name^='_UPLOAD_']");
        for(var i=0;i<_text.length;i++){
            array+=_text[i].value+",";
        }
        submitIng=true;
        ValidBrandAndModel();
        $('#informationFrom').submit();
    });

    if(isNew=="0"){
        $('.desc-child').addClass('isHide');
        $(this).removeClass('open');
    }else{
        $('.desc-child').removeClass('isHide');
        $(this).addClass('open');
    }

    var ValidBrandAndModel=function (){
        if($('.desc-child').hasClass('isHide')){
            $('input[name=newBrand]').val('');
            $('input[name=newModels]').val('');
        }
    }


    //图片管理
    var clickHandlers = {
        deleteImgBtn: function(obj) {
            var $parent =  $(obj).parents('.file-uploaded'),index = $parent.index();
            var $upBtn = $parent.parent().siblings('.ui-button-upload');
            $parent.remove();
            $upBtn.find('input[type=hidden]:eq('+index+')').remove();
            clickHandlers.changeInputName($upBtn);
        },
        changeInputName:function($obj){
            $.each($obj.find('input[type=hidden]'),function(i){
                $(this).attr('name','_UPLOAD_'+i);
            });
        }
    };

    $('[node-type="deleteImgBtn"]').on('click',function(){
        clickHandlers.deleteImgBtn(this);
    });
    //file uploader buton installations  失败：-1 初始值0 正在上传1 成功2
    var uploadArray = [];
    $('[node-type="uploadButton"]').each(function(i, el) {
        uploadArray[i] = 0;
        var $el = $(el),
            fieldName = $el.data('name');

        /*$el.append(fieldInput);*/

        // initialize file upload component
        var uploader = Uploader(el, {
            endpoint:  url.demoUploadUrl
        });



        uploader.on('fileselect', function(e) {
            var html = '<div class="progressbar"><div></div></div>';
            $el.append(html);
            uploadArray[i] = 1;
        });

        uploader.on('uploadprogress', function(e) {
            var percent = Math.min(e.percentLoaded, 99) + '%';
            $el.find('.progressbar div').css('width', percent).text(percent);
        });

        uploader.on('uploadcomplete', function(e) {
            var res = e.data || {},fieldInput = $('<input type="hidden" />');
            if (res.code == "1") {
                uploadArray[i] = 2;
                fieldInput.val(res.url);
                $el.append(fieldInput);
                clickHandlers.changeInputName($el);
            } else {
                var isFirst = true;
                var timer = setInterval(function() {
                    var eImg = $el.next().find('.up-img-box ').last();
                    if (isFirst && eImg.length > 0) {
                        box.tips(res.msg || '图片上传失败');
                        eImg.remove();
                        isFirst = false;
                        clearInterval(timer);
                    }
                }, 100);
            }
        });
        uploader.on('uploaderror', function(e) {
            uploadArray[i] = -1;
            box.tips('图片上传失败!');
        });

    });
    
    $('body').on('click','.jAddProType',function(){
        if($(this).hasClass('open')){
            $('.desc-child').addClass('isHide');
            $(this).removeClass('open');
        }else{
            $('.desc-child').removeClass('isHide');
            $(this).addClass('open');
        }
    });
    
    //根据不同的销售方式，确定价格的单位
    $('body').on('change','select[name=sellTypeSel]',function(){
        var val = $(this).val();
        initPriceUnitOpt(val);
    });
})
;