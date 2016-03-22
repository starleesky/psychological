define(['jquery', 'url', 'plug/ajax', 'plug/box', 'plug/validate/validateMethod'], function ($, url, ajax, box, Validator) {

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

    //信息提交
    Validator.validate('#informationFrom', {
        rules: {
            price: {
                required: true
            }
        },
        messages: {
            price: {
                required: '价格不能为空'
            }
        },
        submitHandler: function (form) {
            $.post(
                url.saveInfo,
                {
                    catagoryBigId: $(form).find('select[name=catagoryBig]').val(),
                    catagoryBigName: $(form).find('select[name=catagoryBig]').find("option:selected").text(),
                    price: $(form).find('input[name=price]').val()
                },
                function (data) {
                    if (data.result) {
                        box.ok(data.message);
                    } else {
                        box.error(data.message);
                    }

                });
        },
        showError: function (elem, msg) {
            box.error(msg, elem);
        },
        success: null
    });

    $("#jSave").click(function () {
        var modelsId = oModels.val();
        if (modelsId == null || modelsId == "") {
            alert('型号未选择');
            return;
        }
        $('#informationFrom').submit();

    });

    $("#jSubmit").click(function () {
        alert('提交之前请先保存');
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
})
;